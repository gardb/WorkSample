package worksample.driving_history.driverdata.filereader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import worksample.driving_history.driverdata.Driver;
import worksample.driving_history.driverdata.Trip;

public abstract class HistoryReader {

	public abstract Map<Driver, Trip> read();

	private List<String> historyReport = new ArrayList<String>();
	private List<String> errorReport = new ArrayList<String>();

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
		Map<Driver, Trip> driverHistory = new LinkedHashMap<Driver, Trip>();

		lineNumber = 0;

		for (String historyLine : historyLines) {
			String[] historyParts = historyLine.split(delimiter);

			lineNumber++;

			try {
				driverName = historyParts[1];

				if (historyParts[0].contentEquals(newDriver) 
						&& !driverHistory.containsKey(new Driver(driverName))) {
					driverHistory.put(new Driver(driverName), new Trip(zero, zero, zero));
				} 
				else if (historyParts[0].contentEquals(newDriver)
						&& driverHistory.containsKey(new Driver(driverName))) {
					setErrorReport(DUPLICATE_DRIVER + lineNumber);
				} 
				else if ((historyParts[0].contentEquals(newTrip))
						&& !driverHistory.containsKey(new Driver(driverName))) {
					setErrorReport(DRIVER_NOT_REGISTERED + lineNumber);
				} 
				else if (historyParts[0].contentEquals(newTrip)) {
					driverHistory.put(new Driver(driverName), driverHistory.get(new Driver(driverName))
							.calculateTrip(historyParts[2], historyParts[3], historyParts[4]));
				} 
				else {
					setErrorReport(INVALID_DATA + lineNumber);
				}
			} 
			catch (Exception e) {
				setErrorReport(INVALID_DATA + lineNumber);
			}
		}

		for (Map.Entry<Driver, Trip> entry : driverHistory.entrySet()) {
			key = entry.getKey();
			value = entry.getValue();

			setHistoryReport(key.getName() + value);
		}

		return driverHistory;
	}

	public void setHistoryReport(String historyData) {
		historyReport.add(historyData);
	}

	private void setErrorReport(String errorData) {
		errorReport.add(errorData);
	}

	public void printErrorReport() {
		for (String errors : errorReport) {
			System.out.println(errors);
		}
	}

	public void printHistoryReport() {
		for (String history : historyReport) {
			System.out.println(history);
		}
	}

}
