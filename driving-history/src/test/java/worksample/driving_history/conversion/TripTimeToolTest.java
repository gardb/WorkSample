package worksample.driving_history.conversion;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

public class TripTimeToolTest {
	
	private TripTimeTool ttt;
	private BigDecimal difference;
	private BigDecimal assertion;
	
	@Before
	public void setup() {
		ttt = new TripTimeTool();
	}
	
	@Test
	public void testTotalHoursIsOne() {
		difference = ttt.calculateTripHours("00:00", "01:00");
		
		assertion = new BigDecimal("1.00");
		
		Assert.assertEquals(assertion, difference);
	}
	
	@Test
	public void testTotalHoursIsOneQuarter() {
		difference = ttt.calculateTripHours("00:00", "00:15");
		
		assertion = new BigDecimal("0.25");
		
		Assert.assertEquals(assertion, difference);
	}
	
	@Test
	public void testTotalHoursIsZero() {
		difference = ttt.calculateTripHours("00:00", "00:00");
		
		assertion = new BigDecimal("0.00");
		
		Assert.assertEquals(assertion, difference);
	}
	
	@Test
	public void testRandomStringReturnsNull() {
		difference = ttt.calculateTripHours("Hello", "world");
		
		assertion = null;
		
		Assert.assertEquals(assertion, difference);
	}
	
	@Test
	public void testStartTimeAfterEndTimeReturnsNull() {
		difference = ttt.calculateTripHours("01:00", "00:00");
		
		assertion = null;
		
		Assert.assertEquals(assertion, difference);
	}
	

}
