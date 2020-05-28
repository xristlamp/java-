package perikor;


public class Owner extends User {
	private boolean isAdmin=true;
	public Owner(String UserName, String UserEmail) {
		super (UserName,UserEmail);
		
	}
	public boolean showOwner() {
		return true;
	}
	

}
