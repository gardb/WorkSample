package worksample.driving_history.driverdata.filereader;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import worksample.driving_history.driverdata.Driver;
import worksample.driving_history.driverdata.Trip;

public abstract class HistoryReader {

	public abstract Map<Driver, Trip> read();

	private String newDriver = "Driver";
	private String newTrip = "Trip";
	private BigDecimal zero = new BigDecimal(0);

	private String driverName;

	protected Map<Driver, Trip> loadHistoryFromDelimitedLines(List<String> historyLines, String delimiter){
		Map<Driver, Trip> driverHistory = new LinkedHashMap<Driver, Trip>();

		for (String historyLine : historyLines) {
			String[] historyParts = historyLine.split(delimiter);

			driverName = historyParts[1];
			

			if(historyParts[0].contentEquals(newDriver)) {
				driverHistory.put(new Driver(driverName), new Trip(zero, zero, zero));
			} else if (historyParts[0].contentEquals(newTrip)) {
				driverHistory.put(new Driver (driverName),
						driverHistory.get(new Driver(driverName)).calculateTrip(historyParts[2], historyParts[3], historyParts[4]));
			} else {
				continue;
			}
		}

		for (Map.Entry<Driver, Trip> entry : driverHistory.entrySet()) {
			Driver key = entry.getKey();
			Trip value = entry.getValue();

			System.out.println(key.getName() + value);
		}

		return driverHistory;
	}

}
