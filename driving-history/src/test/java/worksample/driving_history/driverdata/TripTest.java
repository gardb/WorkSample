package worksample.driving_history.driverdata;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class TripTest {
	
	private Trip trip;
	private BigDecimal totalTime;
	private BigDecimal totalDistance;
	private BigDecimal totalRate;
	
	private String tripTwoStart;
	private String tripTwoEnd;
	private String tripTwoDistance;
	
	private String tripThreeStart;
	private String tripThreeEnd;
	private String tripThreeDistance;
	
	@Before
	public void setup() {
		totalTime = new BigDecimal(1.25);
		totalDistance = new BigDecimal(42);
		totalRate = new BigDecimal(34);
		trip = new Trip(totalTime, totalDistance, totalRate);
		
		tripTwoStart = "00:00";
		tripTwoEnd = "01:00";
		tripTwoDistance = "50";
		
		tripThreeStart = "00:00";
		tripThreeEnd = "01:00";
		tripThreeDistance = "100";
	}
	
	@Test
	public void tripOneIsRecordedAndReturnsToString() {
		String assertion = ": 42 miles @ 34 mph";
		
		Assert.assertEquals(assertion, trip.toString());
	}
	
	@Test
	public void tripTwoIsCountedAndAddedToTripData() {
		trip.calculateTrip(tripTwoStart, tripTwoEnd, tripTwoDistance);
		
		String assertion = ": 92 miles @ 41 mph";
		
		Assert.assertEquals(assertion, trip.toString());
	}
	
	@Test
	public void tripThreeIsNotCounted() {
		trip.calculateTrip(tripThreeStart, tripThreeEnd, tripThreeDistance);
		
		String assertion = ": 42 miles @ 34 mph";
		
		Assert.assertEquals(assertion, trip.toString());
		
	}

}
