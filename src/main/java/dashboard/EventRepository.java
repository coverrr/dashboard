package dashboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;


@Repository
public class EventRepository {
	public Event<PropertyChangedEventArgs> propertyChanged = new Event<>();
	
	private List<MonitoringEvent> monitoredEvents;
	
	@PostConstruct
	protected void setUp() {
		monitoredEvents = new ArrayList<>();
	}
	
	public List<MonitoringEvent> getAllEvents() {
		return this.monitoredEvents;
	}
	
	// get all Mapo Events, ...
	
	public MonitoringEvent updateEvent(final MonitoringEvent event) {
		Optional<MonitoringEvent> foundEvent = monitoredEvents.stream()
			.filter(e -> e.getSystemName().equals(event.getSystemName()))
			.findFirst();
		
		// update event
		if(foundEvent.isPresent()) {
			monitoredEvents.remove(foundEvent.get());
			monitoredEvents.add(event);
			
			System.out.println("Updating event...");
			propertyChanged.fire(new PropertyChangedEventArgs(this, "list-change", event));
		} else {
			monitoredEvents.add(event);
		}
		
		return event;
	}

	public List<MonitoringEvent> getMonitoredEvents() {
		return monitoredEvents;
	}

	public void setMonitoredEvents(List<MonitoringEvent> monitoredEvents) {
		this.monitoredEvents = monitoredEvents;
	}
	
	 
}
