package dashboard;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EventRepositoryTest {

	EventRepository repo = new EventRepository();
	
	@Test
	public void whenUpdatingEvent_ListUpdatesCorrectly() throws Exception {
		List<MonitoringEvent> events = new ArrayList<>(
				Arrays.asList(
					new MonitoringEvent("Mapo Dev", LocalDateTime.of(2018, 12, 12, 11, 10), true),
					new MonitoringEvent("Papo Dev", LocalDateTime.of(2018, 12, 12, 11, 10), false)
				)
		);
		this.repo.setMonitoredEvents(events);
		MonitoringEvent monitoringEvent = new MonitoringEvent("Mapo Dev", LocalDateTime.now(), true);
		
		MonitoringEvent updatedEvent = this.repo.updateEvent(monitoringEvent);
		
		Assert.assertEquals(this.repo.getAllEvents().size(), 2);
		Assert.assertEquals(updatedEvent, monitoringEvent);
	}
	
	@Test
	public void whenUpdatingEvent_ItemGetsInsertedCorrectly() throws Exception {
		List<MonitoringEvent> events = new ArrayList<>(
				Arrays.asList(
					new MonitoringEvent("Mapo Dev", LocalDateTime.of(2018, 12, 12, 11, 10), true),
					new MonitoringEvent("Papo Dev", LocalDateTime.of(2018, 12, 12, 11, 10), false)
				)
		);
		this.repo.setMonitoredEvents(events);

		MonitoringEvent monitoringEvent = new MonitoringEvent("Fast-Report Mapo", LocalDateTime.now(), true);
		
		this.repo.updateEvent(monitoringEvent);
		
		Assert.assertEquals(this.repo.getAllEvents().size(), 3);
	}
	
}
