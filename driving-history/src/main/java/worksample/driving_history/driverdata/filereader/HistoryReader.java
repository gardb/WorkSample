package worksample.driving_history.driverdata.filereader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import worksample.driving_history.conversion.HistorySorter;
import worksample.driving_history.driverdata.Driver;
import worksample.driving_history.driverdata.Trip;

public abstract class HistoryReader {

	public abstract Map<Driver, Trip> read();

	private Map<Driver, Trip> driverHistory = new LinkedHashMap<Driver, Trip>();
	private List<String> errorReport = new ArrayList<String>();
	
	private List<Entry<Driver, Trip>> historyReport;

	private final static String newDriver = "Driver";
	private final static String newTrip = "Trip";
	private final BigDecimal zero = new BigDecimal(0);

	private String driverName;

	private Driver key;
	private Trip value;

	private int lineNumber;

	private final static String INVALID_DATA = "Invalid data on line: ";
	private final static String DUPLICATE_DRIVER = "Duplicate new driver requested on line: ";
	private final static String DRIVER_NOT_REGISTERED = "Trip received for unregistered driver on line: ";
	private final static String POSSIBLE_ERROR_DETECTED = "Additional data detected. Possible error on line: ";
	private final static String DATA_BREAK = "---------------------------------------------";
	
	private final static int expectedNewTripDataSize = 5;
	private final static int expectedNewDriverDataSize = 2;

	protected Map<Driver, Trip> loadHistoryFromDelimitedLines(List<String> historyLines, String delimiter) {
		

		lineNumber = 0;

		for (String historyLine : historyLines) {
			String[] historyParts = historyLine.split(delimiter);

			lineNumber++;

			try {
				driverName = historyParts[1];
				
				// Check that additional data is not present on line				
				if ((historyParts[0].contentEquals(newDriver) && historyParts.length > expectedNewDriverDataSize) 
						|| (historyParts[0].contentEquals(newTrip) && historyParts.length > expectedNewTripDataSize)) {
					setErrorReport(POSSIBLE_ERROR_DETECTED + lineNumber);
				}
				
				// Check that new driver does not exist, if true, create new driver with zero trip data
				if (historyParts[0].contentEquals(newDriver) && !driverHistory.containsKey(new Driver(driverName))) {
					driverHistory.put(new Driver(driverName), new Trip(zero, zero, zero));
				// If new driver exists, send error report for duplicate driver
				} else if (historyParts[0].contentEquals(newDriver)
						&& driverHistory.containsKey(new Driver(driverName))) {
					setErrorReport(DUPLICATE_DRIVER + lineNumber);
				// Check that driver exists for new trip driver. If false, send error report for missing registration
				} else if ((historyParts[0].contentEquals(newTrip))
						&& !driverHistory.containsKey(new Driver(driverName))) {
					setErrorReport(DRIVER_NOT_REGISTERED + lineNumber);
				// If driver exists, perform Trip.calculateTrip
				} else if (historyParts[0].contentEquals(newTrip)) {
					driverHistory.put(new Driver(driverName), driverHistory.get(new Driver(driverName))
							.calculateTrip(historyParts[2], historyParts[3], historyParts[4]));
				// If not strictly new driver or new trip, send error report for bad data
				} else {
					setErrorReport(INVALID_DATA + lineNumber);
				}

			 // Catch ALL exceptions, including NullPointer from bad data in calculations above
			 // Readable data file should be verified in TxtHistoryReader HistoryReadException
			} catch (Exception e) {
				setErrorReport(INVALID_DATA + lineNumber);
			}
		}

		

		return driverHistory;
	}

	private void setErrorReport(String errorData) {
		errorReport.add(errorData);
	}

	public void printErrorReport(String errorRequest) {
		if (errorRequest.toUpperCase().contains("Y")) {
			System.out.println(DATA_BREAK);
			if (errorReport.isEmpty()) {
				System.out.println("No errors to report.");
			} else {
				for (String errors : errorReport) {
					System.out.println(errors);
				}
			}
		}
	}

	public void printHistoryReport() {
		// Create set list from driver history map data
		historyReport = new LinkedList<>(driverHistory.entrySet());
		
		// Sort by distance. Calculations are currently rounded to 2 decimals
		// Calculations finally rounded half-up to whole in the key-value's Trip toString method when printed
		Collections.sort(historyReport, new HistorySorter());
		
		System.out.println(DATA_BREAK);
		
		for(Entry<Driver, Trip> data : historyReport) {
			key = data.getKey();
			value = data.getValue();
			
			System.out.println(key.getName() + value);
		}
		System.out.println(DATA_BREAK);
	}

}
