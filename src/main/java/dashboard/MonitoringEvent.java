package dashboard;

import java.time.LocalDateTime;

public class MonitoringEvent {
	private String systemName;
	private LocalDateTime lastChecked;
	private boolean healthy;

	
	public MonitoringEvent(String systemName, LocalDateTime lastChecked, boolean healthy) {
		this.systemName = systemName;
		this.lastChecked = lastChecked;
		this.healthy = healthy;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public LocalDateTime getLastChecked() {
		return lastChecked;
	}

	public void setLastChecked(LocalDateTime lastChecked) {
		this.lastChecked = lastChecked;
	}

	public boolean isHealthy() {
		return healthy;
	}

	public void setHealthy(boolean healthy) {
		this.healthy = healthy;
	}
	
	
}
