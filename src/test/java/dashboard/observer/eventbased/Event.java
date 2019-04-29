package dashboard.observer.eventbased;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 
 * @author nils
 * 
 * You subsribe by calling addHandler() and you can unsubsribe by calling close(), or use try-with to autoclose.
 *
 * @param <TArgs> Actual data that is being fired as the event gets fired
 */
public class Event<TArgs> {
	private int count = 0;
	
	/**
	 * Set of subscribers
	 */
	private Map<Integer, Consumer<TArgs>> handlers = new HashMap<>();
	
	/**
	 * Adding subscribers: The subsriber will get a "memento" back so that he can unsubscribe
	 */
	public Subscription addHandler(Consumer<TArgs> handler) {
		int i = count;
		handlers.put(count++, handler);
		
		return new Subscription(this, i);
	}
	
	public void fire(TArgs args) {
		for(Consumer<TArgs> handler : handlers.values()) {
			handler.accept(args);
		}
	}
	
	public class Subscription implements AutoCloseable{
		/**
		 * reference to the event that it is part of
		 */
		private Event<TArgs> event;
		
		/**
		 * This id is an index (the key) into the map of Event-Class.
		 */
		private int id;
		
		public Subscription(Event<TArgs> event, int id) {
			this.event = event;
			this.id = id;
		}

		@Override
		public void close() {
			event.handlers.remove(id);
		}

	}
	 
}
