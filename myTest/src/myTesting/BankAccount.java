package myTesting;
import java.util.Date;

public class BankAccount {
	private int id; 
	private int pin;
	private int balance; 
	// debit card class 
	private Date created; 
	private boolean isOnline = false; 
	private String FirstName; 
	private String LastName; 
	// need to keep a string of logs and need to add actions after a withdraw or deposit 
	
	public BankAccount(int p, int bal, String FN, String LN) {
		// need to make id unique to each instance 
		this.pin = p; 
		this.balance = bal;
		this.created = new Date();
		this.FirstName = FN; 
		this.LastName = LN; 
		
	}

	public int getId() {
		return id;
	}

	public int getPin() {
		return pin;
	}

	public int getBalance() {
		return balance;
	}

	public Date getCreated() {
		return created;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public String getFirstName() {
		return FirstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
	public void deposit(int amount) {
		this.balance += amount;
		/// need to add action to the log 
	}
	
	public void withdraw(int amount) {
		this.balance -= amount;
		// need to add action to the log
	}
	
	
	
	

}
