package worksample.driving_history.driverdata.filereader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import worksample.driving_history.driverdata.Driver;
import worksample.driving_history.driverdata.Trip;

public class HistoryReaderMock extends HistoryReader {
	
	@Override
	public Map<Driver, Trip> read(){
		return loadHistoryFromDelimitedLines(getHistoryMockLines(), "\\s");
	}
	
	private List<String> getHistoryMockLines() {
		List<String> lines = new ArrayList<String>();
		
		lines.add("Driver Dan");
		lines.add("Driver Lauren");
		lines.add("Driver Kumi");
		lines.add("Trip Dan 07:15 07:45 17.3");
		lines.add("Trip Dan 06:12 06:32 21.8");
		lines.add("Trip Lauren 12:01 13:16 42.0");
		
		return lines;
	}

}
