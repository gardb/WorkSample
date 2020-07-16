package worksample.driving_history.conversion;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TripTimeTool {

	private DateTimeFormatter fmt;
	private LocalTime startTime;
	private LocalTime endTime;
	private Duration duration;
	private final BigDecimal minutesPerHour = new BigDecimal(60);
	private BigDecimal tripMinutes;
	private BigDecimal tripHours;

	public BigDecimal calculateTripHours(String startTimeInput, String endTimeInput) {

		startTime = tryParseTime(startTimeInput);
		endTime = tryParseTime(endTimeInput);

		duration = tryDurationBetween(startTime, endTime);

		tripMinutes = tryValueOfMinutes(duration);

		tripHours = tryGetTripHours(tripMinutes, minutesPerHour);

		return tripHours;
	}

	private LocalTime tryParseTime(String timeInput) {
		try {
			fmt = DateTimeFormatter.ofPattern("HH:mm");
			return LocalTime.parse(timeInput, fmt);
		} catch (Exception e) {
			return null;
		}
	}

	private Duration tryDurationBetween(LocalTime startTime, LocalTime endTime) {
		try {
			if (endTime.isBefore(startTime)) {
				return null;
			} else {
				return Duration.between(startTime, endTime);
			}
		} catch (Exception e) {
			return null;
		}
	}

	private BigDecimal tryValueOfMinutes(Duration duration) {
		try {
			return BigDecimal.valueOf(Math.abs(duration.toMinutes()));
		} catch (Exception e) {
			return null;
		}
	}

	private BigDecimal tryGetTripHours(BigDecimal tripMinutes, BigDecimal minutesPerHour) {
		try {
			return tripMinutes.divide(minutesPerHour, 2, RoundingMode.HALF_UP);
		} catch (Exception e) {
			return null;
		}
	}

}
