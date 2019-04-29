package dashboard.observer;

public interface Observer<T> {
	void handle(PropertyChangedEventArgs<T> args);
}
