package Model;

import org.joda.time.LocalDateTime;
import org.joda.time.Period;

public class MonitoredData {
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String activityLabel;
	
	public MonitoredData(LocalDateTime startTime, LocalDateTime endTime, String activityLabel) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.activityLabel = activityLabel;
	}
	
	public LocalDateTime getStartTime() {
		return startTime;
	}
	
	public LocalDateTime getEndTime() {
		return endTime;
	}
	
	public String getActivityLabel() {
		return activityLabel;
	}
	
	public long getDayOfYearFromStartTime() {
		return this.getStartTime().getDayOfYear();
	}
	
	public int getPeriodBetweenStartAndEndTimeInSeconds() {
		Period period = new Period(this.getStartTime(), this.getEndTime());
		return period.getHours()*3600 + period.getMinutes()*60 + period.getSeconds();
	}
}
