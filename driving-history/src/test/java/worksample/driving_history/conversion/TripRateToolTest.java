package worksample.driving_history.conversion;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class TripRateToolTest {
	
	private TripRateTool tripRateTool;
	
	@Before
	public void setup() {
		tripRateTool = new TripRateTool();
	}
	
	@Test
	public void testOneMileInOneHour() {
		BigDecimal rate = tripRateTool.calculateTripRate("1.0", new BigDecimal(60.00));
		
		BigDecimal assertion = new BigDecimal("1");
		
		Assert.assertEquals(assertion, rate);
	}

}
