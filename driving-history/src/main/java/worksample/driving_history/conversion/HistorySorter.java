package worksample.driving_history.conversion;

import java.util.Comparator;
import java.util.Map.Entry;

import worksample.driving_history.driverdata.Driver;
import worksample.driving_history.driverdata.Trip;

public class HistorySorter implements Comparator<Entry<Driver, Trip>>{
	
	public int compare (Entry<Driver, Trip> a, Entry<Driver, Trip> b) {
		return b.getValue().getTripDistance().compareTo(a.getValue().getTripDistance());
	
	}
	
	

}
