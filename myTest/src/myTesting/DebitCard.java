package myTesting;
import java.util.Date;

public class DebitCard {
	private int sixteenDigits; 
	private String FistName; 
	private String LastName; 
	private Date openeded; 
	private String expiration; 
	private int cvv; 
	private int pin; 
	
	
	public DebitCard(String FN, String LN, int p) {
		this.FistName = FN;
		this.LastName = LN; 
		this.openeded = new Date();
		// set exp to 3 years after opened; 
		// set 16 digits;
		//set cvv; 
		this.pin = p; 
		
		
	}


	public int getSixteenDigits() {
		return sixteenDigits;
	}


	public String getFistName() {
		return FistName;
	}


	public String getLastName() {
		return LastName;
	}


	public Date getOpeneded() {
		return openeded;
	}


	public String getExpiration() {
		return expiration;
	}


	public int getCvv() {
		return cvv;
	}


	public int getPin() {
		return pin;
	}


	public void setFistName(String fistName) {
		FistName = fistName;
	}


	public void setLastName(String lastName) {
		LastName = lastName;
	}


	public void setPin(int pin) {
		this.pin = pin;
	}
	
	
	
	
	
	
	
	
	
	
}
