package dashboard;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@RestController
public class MonitoringEventController {
	
	@Autowired
	private EventRepository eventRepository;
	
	@GetMapping(path = "/event/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<MonitoringEvent> getAllEvents() {
		
		/*
		eventRepository.propertyChanged.addHandler(x -> {
			System.out.println(x.newValue);
			if(x.newValue instanceof MonitoringEvent) {
				MonitoringEvent monEvent = (MonitoringEvent) x.newValue;
				
				System.out.println("Received Monitoring Event: " + monEvent.getLastChecked());
			}
		});
		*/
		/*
		return Flux.interval(Duration.ofSeconds(1))
			.map(i -> Arrays.asList(new MonitoringEvent("TestSystemName", LocalDateTime.now(), true)))
			.flatMapIterable(x -> x);
		*/
		
		return Flux.create(emitter -> {
			eventRepository.propertyChanged.addHandler(x -> {
				System.out.println(x.newValue);
				if(x.newValue instanceof MonitoringEvent) {
					MonitoringEvent monEvent = (MonitoringEvent) x.newValue;
					
					System.out.println("Received Monitoring Event in Emitter: " + monEvent.getLastChecked());
					emitter.next(monEvent);
				}
			});
		}, FluxSink.OverflowStrategy.LATEST);
	}
	
	
}
