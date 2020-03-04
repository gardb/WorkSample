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
	private BigDecimal minutesPerHour;
	private BigDecimal tripMinutes;
	private BigDecimal tripHours;
	
	public BigDecimal calculateTripHours(String startTimeInput, String endTimeInput) {
		fmt = DateTimeFormatter.ofPattern("HH:mm");
		
		startTime = LocalTime.parse(startTimeInput, fmt);
		endTime = LocalTime.parse(endTimeInput, fmt);
		
		duration = Duration.between(startTime, endTime);
		
		minutesPerHour = new BigDecimal(60);
		
		tripMinutes = BigDecimal.valueOf(Math.abs(duration.toMinutes()));
		
		tripHours = tripMinutes.divide(minutesPerHour, 2, RoundingMode.CEILING);
		
		return tripHours;
	}

}
