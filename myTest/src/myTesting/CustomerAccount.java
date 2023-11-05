package myTesting;

public class CustomerAccount {
	private String Email; 
	private String FirstName;
	private String LastName; 
	private String Password; 
	private boolean isOnline = false; 
	// list of bank accounts here 
	// address object here 
	
	public CustomerAccount(String E, String FN, String LN, String Pass) {
		this.Email = E;
		this.FirstName = FN; 
		this.LastName = LN; 
		this.Password = Pass;
		
	} 
	
	public void setPass(String P) {
		this.Password = P; 
	}

	public String getEmail() {
		return Email;
	}

	public String getFirstName() {
		return FirstName;
	}

	public String getLastName() {
		return LastName;
	}

	public String getPassword() {
		return Password;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
	
	
	
	
	
	
	
	
	
}
