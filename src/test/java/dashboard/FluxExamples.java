package dashboard;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import reactor.core.publisher.Flux;

public class FluxExamples {
	
	@Test
	public void helloWorld() {
		Flux.just("Hello world!", "Ah, nice!")
			.subscribe(System.out::println, System.out::println, null);
	}
	
	@Test
	public void countingLetters() throws Exception {
		List<String> words = Arrays.asList(
				"the",
				"quick",
				"brown",
				"fox",
				"jumps",
				"over",
				"the",
				"lazy",
				"dog"
				);
		

		Flux.fromIterable(words)
			.flatMap(word -> Flux.fromArray(word.split("")))							// split into single letters
			.distinct()
			.sort()
			.zipWith(Flux.range(1, 100), (word, line) -> line + ") " + word)			// add line numbers
			.subscribe(System.out::println);
	}
	
	@Test
	public void testName() throws Exception {
		Flux<String> fastClock = Flux.interval(Duration.ofSeconds(1))
				.map(tick -> "fast: " + tick);
		
		Flux<String> slowClock = Flux.interval(Duration.ofSeconds(2))
				.map(tick -> "slow: " + tick);
		
		/*
		Flux clock = Flux.merge(
				fastClock.filter(tick -> isFastTime()), 
				slowClock.filter(tick -> isSlowTime())
		);
		*/
		
		// clock.subscribe(System.out::println);
		
		Flux<LocalTime> feed = Flux.interval(Duration.ofSeconds(1))
			.map(tick -> LocalTime.now());
			
		slowClock
			.withLatestFrom(feed, (tick, time) -> tick + " " + time)
			.subscribe(System.out::println);
		
	}
	
	@Test
	public void countdown() throws Exception {
		
	}
	
}
