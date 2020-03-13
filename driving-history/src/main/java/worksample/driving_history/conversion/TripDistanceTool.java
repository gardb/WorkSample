package worksample.driving_history.conversion;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TripDistanceTool {

	private BigDecimal tripDistance;

	public BigDecimal calculateTripDistance(String distanceInput) {
		try {
			tripDistance = new BigDecimal(distanceInput);
			tripDistance = tripDistance.setScale(0, RoundingMode.HALF_UP);
			if (tripDistance.compareTo(BigDecimal.ZERO) >= 0) {
				return tripDistance;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

}
