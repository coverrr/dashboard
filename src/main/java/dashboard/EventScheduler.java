package dashboard;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EventScheduler {

	@Autowired
	private EventRepository eventRepository;
	
	
	
	@Scheduled(fixedRate = 5000)
	public void pollMapo() {
		System.out.println("Stub: Making HTTP-Request to http://localhost:9200 ...");
		System.out.println("Stub: Parsing Request and creating MonitoringEvent-Object ...");
		
		MonitoringEvent event = new MonitoringEvent("Mapo Test", LocalDateTime.now(), true);
		
		this.eventRepository.updateEvent(event);
		
	}
	
	
}
