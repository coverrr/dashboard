package dashboard;

public class PropertyChangedEventArgs {
	
	public Object source;			// besser: private + strongly typed <T>
	public String propertyName;
	public Object newValue;
	
	public PropertyChangedEventArgs(Object source, String propertyName, Object newValue) {
		this.source = source;
		this.propertyName = propertyName;
		this.newValue = newValue;
	}
	
	
	// old value, new value

}
