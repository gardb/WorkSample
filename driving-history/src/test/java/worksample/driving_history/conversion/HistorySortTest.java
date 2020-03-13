package worksample.driving_history.conversion;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import worksample.driving_history.conversion.HistorySorter;
import worksample.driving_history.driverdata.Driver;
import worksample.driving_history.driverdata.Trip;

public class HistorySortTest {
	private String assertion;
	
	private Map<Driver, Trip> historyTestMap = new LinkedHashMap<Driver, Trip>();
	private List<Entry<Driver, Trip>> historyTestList;
	
	private BigDecimal laurenTime = new BigDecimal("1.25");
	private BigDecimal laurenDistance = new BigDecimal("42");
	private BigDecimal laurenRate = new BigDecimal("34");
	
	private BigDecimal danTime = new BigDecimal(".83");
	private BigDecimal danDistance = new BigDecimal("39");
	private BigDecimal danRate = new BigDecimal("47");
	
	private BigDecimal kumiTime = new BigDecimal("0");
	private BigDecimal kumiDistance = new BigDecimal("0");
	private BigDecimal kumiRate = new BigDecimal("0");
	
	@Before
	public void setup() {
		historyTestMap.put(new Driver("Kumi"), new Trip(kumiTime, kumiDistance, kumiRate));
		historyTestMap.put(new Driver("Lauren"), new Trip(laurenTime, laurenDistance, laurenRate));
		historyTestMap.put(new Driver("Dan"), new Trip(danTime, danDistance, danRate));
		
		historyTestList = new LinkedList<>(historyTestMap.entrySet());
	}
	
	@Test
	public void testListReturnsUnsorted() {
		assertion = "Kumi";
		
		Assert.assertEquals(assertion, historyTestList.get(0).getKey().getName());
		
	}
	
	@Test
	public void testListReturnsSorted() {
		Collections.sort(historyTestList, new HistorySorter());
		assertion = "Lauren";
		
		Assert.assertEquals(assertion, historyTestList.get(0).getKey().getName());

	}
}
