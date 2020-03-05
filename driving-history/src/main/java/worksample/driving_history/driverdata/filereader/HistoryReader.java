package worksample.driving_history.driverdata.filereader;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import worksample.driving_history.driverdata.Trip;

public abstract class HistoryReader {
	
	public abstract Map<String, Trip> read();
	
	private String newDriver = "Driver";
	private String newTrip = "Trip";
	private BigDecimal zero = new BigDecimal(0);
	
	private String driver;
	
	protected Map<String, Trip> loadHistoryFromDelimitedLines(List<String> historyLines, String delimiter){
		Map<String, Trip> driverHistory = new LinkedHashMap<String, Trip>();
		
		for (String historyLine : historyLines) {
			String[] historyParts = historyLine.split(delimiter);
			
			driver = historyParts[1];
			
			if(historyParts[0].contentEquals(newDriver)) {
				driverHistory.put(driver, new Trip(zero, zero, zero));
			} else if (historyParts[0].contentEquals(newTrip)) {
				driverHistory.put(driver, 
						driverHistory.get(driver).calculateTrip(historyParts[2], historyParts[3], historyParts[4]));
			}
		}
		
		for (Map.Entry<String, Trip> entry : driverHistory.entrySet()) {
			String key = entry.getKey();
			Trip value = entry.getValue();
			
			System.out.println(key + value);
		}
		
		return driverHistory;
	}

}
