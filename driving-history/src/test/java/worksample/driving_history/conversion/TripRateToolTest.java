package worksample.driving_history.conversion;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

public class TripRateToolTest {
	
	private TripRateTool tripRateTool;
	private BigDecimal rate;
	private BigDecimal assertion;
	
	@Before
	public void setup() {
		tripRateTool = new TripRateTool();
	}
	
	@Test
	public void testOneMileInOneHour() {
		rate = tripRateTool.calculateTripRate(new BigDecimal(1), new BigDecimal(60.00));
		
		assertion = new BigDecimal("1");
		
		Assert.assertEquals(assertion, rate);
	}
	
	@Test
	public void testThirtyFourMilesInOneHour() {
		rate = tripRateTool.calculateTripRate(new BigDecimal(42), new BigDecimal(1.25));
		
		assertion = new BigDecimal(34);
		
		Assert.assertEquals(assertion, rate);
	}

}
