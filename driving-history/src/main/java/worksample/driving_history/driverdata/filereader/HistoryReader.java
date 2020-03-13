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

	private String newDriver = "Driver";
	private String newTrip = "Trip";
	private final BigDecimal zero = new BigDecimal(0);

	private String driverName;

	private Driver key;
	private Trip value;

	private int lineNumber;

	private String INVALID_DATA = "Invalid data on line: ";
	private String DUPLICATE_DRIVER = "Duplicate new driver requested on line: ";
	private String DRIVER_NOT_REGISTERED = "Trip received for unregistered driver on line: ";

	protected Map<Driver, Trip> loadHistoryFromDelimitedLines(List<String> historyLines, String delimiter) {
		

		lineNumber = 0;

		for (String historyLine : historyLines) {
			String[] historyParts = historyLine.split(delimiter);

			lineNumber++;

			try {
				driverName = historyParts[1];

				if (historyParts[0].contentEquals(newDriver) && !driverHistory.containsKey(new Driver(driverName))) {
					driverHistory.put(new Driver(driverName), new Trip(zero, zero, zero));
				} else if (historyParts[0].contentEquals(newDriver)
						&& driverHistory.containsKey(new Driver(driverName))) {
					setErrorReport(DUPLICATE_DRIVER + lineNumber);
				} else if ((historyParts[0].contentEquals(newTrip))
						&& !driverHistory.containsKey(new Driver(driverName))) {
					setErrorReport(DRIVER_NOT_REGISTERED + lineNumber);
				} else if (historyParts[0].contentEquals(newTrip)) {
					driverHistory.put(new Driver(driverName), driverHistory.get(new Driver(driverName))
							.calculateTrip(historyParts[2], historyParts[3], historyParts[4]));
				} else {
					setErrorReport(INVALID_DATA + lineNumber);
				}
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
			System.out.println("-------------------------------------");
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
		historyReport = new LinkedList<>(driverHistory.entrySet());
		
		Collections.sort(historyReport, new HistorySorter());
		
		System.out.println("-------------------------------------");
		
		for(Entry<Driver, Trip> data : historyReport) {
			key = data.getKey();
			value = data.getValue();
			
			System.out.println(key.getName() + value);
		}
		System.out.println("-------------------------------------");
	}

}
