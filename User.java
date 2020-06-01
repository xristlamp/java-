package perikor;

import java.util.ArrayList;
import java.util.List;

public abstract class User {
	private String UserName = "";
	private String UserEmail = "";
	
	
	
	public void setUserEmail(String UserEmail) {
		this.UserEmail=UserEmail;
		
	}
    public String getUserEmail() {
    	return UserEmail;
    }
    public String getUserName() {
		return UserName;
	}
    public User(String UserName, String UserEmail) {
    	this.UserName=UserName;
    	this.UserEmail=UserEmail;
    	
    	
    }
    public  abstract boolean showOwner();
	

}
