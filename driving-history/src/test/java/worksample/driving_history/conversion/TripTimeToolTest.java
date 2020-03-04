package worksample.driving_history.conversion;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class TripTimeToolTest {
	
	private TripTimeTool tripTimeTool;
	
	@Before
	public void setup() {
		tripTimeTool = new TripTimeTool();
	}
	
	@Test
	public void testTotalHoursIsOne() {
		BigDecimal difference = tripTimeTool.calculateTripHours("00:00", "01:00");
		
		BigDecimal assertion = new BigDecimal("1.00");
		
		Assert.assertEquals(assertion, difference);
	}
	
	@Test
	public void testTotalHoursIsOneQuarter() {
		BigDecimal difference = tripTimeTool.calculateTripHours("00:00", "00:15");
		
		BigDecimal assertion = new BigDecimal("0.25");
		
		Assert.assertEquals(assertion, difference);
	}
	

}
