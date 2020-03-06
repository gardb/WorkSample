package worksample.driving_history.conversion;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

public class TripRateToolTest {
	
	private BigDecimal rate;
	private BigDecimal assertion;
	private TripRateTool trt;
	
	@Before
	public void setup() {
		trt = new TripRateTool();
	}
	
	@Test
	public void testOneMileInOneHour() {
		rate = trt.calculateTripRate(new BigDecimal(60.00), new BigDecimal(1));
		
		assertion = new BigDecimal("1");
		
		Assert.assertEquals(assertion, rate);
	}
	
	@Test
	public void testThirtyFourMilesInOneHour() {
		rate = trt.calculateTripRate(new BigDecimal(1.25), new BigDecimal(42));
		
		assertion = new BigDecimal(34);
		
		Assert.assertEquals(assertion, rate);
	}
	
	@Test
	public void testZeroMilesInZeroHours() {
		rate = trt.calculateTripRate(new BigDecimal(0), new BigDecimal(0));
		
		assertion = new BigDecimal(0);
		
		Assert.assertEquals(assertion, rate);
	}
	
	@Test
	public void testZerMilesInThreeHours() {
		rate = trt.calculateTripRate(new BigDecimal(3), new BigDecimal(0));
		
		assertion = new BigDecimal(0);
		
		Assert.assertEquals(assertion, rate);
	}
	
	@Test
	public void testThreeMilesInZeroHours() {
		rate = trt.calculateTripRate(new BigDecimal(0), new BigDecimal(3));
		
		assertion = new BigDecimal(0);
		
		Assert.assertEquals(assertion, rate);
	}

}
