package worksample.driving_history.driverdata;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TripTest {
	
	private Trip trip1;
	private Trip trip2;
	private Trip trip3;
	private Trip trip4;
	
	@Before
	public void setup() {
		trip1 = new Trip("00:00", "01:00", "45");
		trip2 = new Trip("00:00", "01:00", "4");
		trip3 = new Trip("00:00", "01:00", "101");
		trip4 = new Trip("00:00", "01:00", "35");
	}
	
	@Test
	public void trip1IsRecorded(){
		
		Assert.assertTrue(trip1.isCounted());
	}

}
