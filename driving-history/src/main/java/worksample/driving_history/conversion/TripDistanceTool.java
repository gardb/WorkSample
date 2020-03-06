package worksample.driving_history.conversion;

import java.math.BigDecimal;

public class TripDistanceTool {

	private BigDecimal tripDistance;

	public BigDecimal calculateTripDistance(String distanceInput) {
		try {
			tripDistance = new BigDecimal(distanceInput);
		} catch (Exception e) {
			return null;
		}
		return tripDistance;
	}

}
