package worksample.driving_history.conversion;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TripRateTool {
	
	private BigDecimal tripRate;
	
	public BigDecimal calculateTripRate(BigDecimal tripTime, BigDecimal tripDistance) {
		
		tripRate = tripDistance.divide(tripTime, 0, RoundingMode.CEILING);
		
		return tripRate;
	}

}
