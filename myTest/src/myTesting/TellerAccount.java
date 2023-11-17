package myTesting;

public class TellerAccount {
	
	private String FirstName;
	private String LastName;
	private String Email;
	private String Password;
	
	
	
	
	
	public TellerAccount(String firstName, String lastName, String email, String password) {
		
		this.FirstName = firstName;
		this.LastName = lastName;
		this.Email = email;
		this.Password = password;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	
	
	
	
}
