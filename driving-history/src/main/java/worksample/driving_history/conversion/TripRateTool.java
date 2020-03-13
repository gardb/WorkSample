package worksample.driving_history.conversion;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TripRateTool {

	private BigDecimal tripRate;

	public BigDecimal calculateTripRate(BigDecimal tripTime, BigDecimal tripDistance) {

		if ((tripTime.compareTo(BigDecimal.ZERO) > 0) && (tripDistance.compareTo(BigDecimal.ZERO) >= 0)) {
			tripRate = tripDistance.divide(tripTime, 0, RoundingMode.HALF_UP);
		} else {
			tripRate = null;
		}
		return tripRate;

	}

}
