package perikor;

public class NoOptionException extends Exception{
	
	public NoOptionException() {
		
		super("No such option available");
		
	}
	
	public NoOptionException (String m) {
		super(m);
	}
	

}
