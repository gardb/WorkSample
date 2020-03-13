package worksample.driving_history.conversion;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TripDistanceToolTest {
	
	private BigDecimal distance;
	private BigDecimal assertion;
	private TripDistanceTool tdt;
	
	@Before
	public void setup() {
		tdt = new TripDistanceTool();
	}
	
	@Test
	public void testOneMile() {
		distance = tdt.calculateTripDistance("1");
		
		assertion = new BigDecimal(1);
		
		Assert.assertEquals(distance, assertion);
	}
	
	@Test
	public void testRandomStringReturnsNullMiles() {
		distance = tdt.calculateTripDistance("Test");
		
		assertion = null;
		
		Assert.assertEquals(distance, assertion);
	}
	
	@Test
	public void testNegativeMilesReturnsNull() {
		distance = tdt.calculateTripDistance("-50");
		
		assertion = null;
		
		Assert.assertEquals(distance, assertion);
	}
	
	@Test
	public void testFractionalNumberRoundedToNearestHalfUp() {
		distance = tdt.calculateTripDistance("5.5");
		
		assertion = new BigDecimal(6);
		
		Assert.assertEquals(distance, assertion);
	}
	
	@Test
	public void testFractionalNumberRoundedToNearest() {
		distance = tdt.calculateTripDistance("5.2");
		
		assertion = new BigDecimal(5);
		
		Assert.assertEquals(distance, assertion);
	}

}
