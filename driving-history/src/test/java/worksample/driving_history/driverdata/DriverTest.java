package worksample.driving_history.driverdata;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DriverTest {
	
	private String assertion;
	
	@Before
	public void setup() {
	}
	
	@Test
	public void testNameReturnsName() {
		Driver driverOne = new Driver("Test");
		
		assertion = "Test";
		
		Assert.assertEquals(assertion, driverOne.getName());
	}
	
	@Test
	public void testHashCodesAreEqual() {
		Driver driverOne = new Driver("Test");
		Driver driverTwo = new Driver("Test");
		
		Assert.assertEquals(driverOne, driverTwo);
	}
	
	@Test
	public void testHashCodesAreNotEqual() {
		Driver driverOne = new Driver("Test");
		Driver driverTwo = new Driver("Not Equal");
		
		Assert.assertNotEquals(driverOne, driverTwo);
	}

}
