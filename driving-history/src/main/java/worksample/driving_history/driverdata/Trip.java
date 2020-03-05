package worksample.driving_history.driverdata;

import java.math.BigDecimal;

import worksample.driving_history.conversion.TripRateTool;
import worksample.driving_history.conversion.TripTimeTool;

public class Trip {
	
	private BigDecimal tripTime;
	private BigDecimal tripDistance;
	private BigDecimal tripRate;
	
	private BigDecimal newTripTime;
	private BigDecimal newTripDistance;
	private BigDecimal newTripRate;
	
	private BigDecimal minTripRate = new BigDecimal(5);
	private BigDecimal maxTripRate = new BigDecimal(100);
	
	private TripRateTool trt = new TripRateTool();
	private TripTimeTool ttt = new TripTimeTool();
	
	public Trip(BigDecimal tripTime, BigDecimal tripDistance, BigDecimal tripRate) {
		this.tripTime = tripTime;
		this.tripDistance = tripDistance;
		this.tripRate = tripRate;
	}
	
	public Trip calculateTrip(String startTimeInput, String endTimeInput, String distanceInput) {
		newTripTime = ttt.calculateTripHours(startTimeInput, endTimeInput);
		newTripDistance = new BigDecimal(distanceInput);
		newTripRate = trt.calculateTripRate(newTripTime, newTripDistance);
		
		if((newTripRate.compareTo(minTripRate) == 1) &&
				(newTripRate.compareTo(maxTripRate) == -1)) {
			
			this.tripTime = tripTime.add(newTripTime);
			this.tripDistance = tripDistance.add(newTripDistance);
			this.tripRate = trt.calculateTripRate(tripTime, tripDistance);
		}
		
		Trip trip = new Trip(tripTime, tripDistance, tripRate);
		
		return trip;
	}
	
	@Override
	public String toString() {
		if (tripDistance.compareTo(BigDecimal.ZERO) > 0) {
			return ": " + tripDistance + " miles @ " + tripRate + " mph";
		} else {
			return ": " + tripDistance + " miles";
		}
	}
	
	

}
