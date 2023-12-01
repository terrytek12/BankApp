package myTesting;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DebitCard {
	private String sixteenDigits; 
	private String FistName; 
	private String LastName; 
	private Calendar opened; 
	private Calendar exp;
	private String expiration; 
	private String cvv; 
	private int pin; 
	
	
	
	public DebitCard(String FN, String LN, int p) {
		this.FistName = FN;
		this.LastName = LN; 
		this.opened = Calendar.getInstance();
		
		
		// set exp to 3 years after opened; 
		this.exp = opened;
		int expYr = 3 + opened.get(Calendar.YEAR);
		this.exp.set(Calendar.YEAR, expYr);
		this.expiration = exp.toString();
		
		this.sixteenDigits = generateCardNumber();
		this.cvv = generateCvvNumber();
		this.pin = p; 
		
		
	}
	
	
	
	private String generateCardNumber() {
		Random random = new Random();
		StringBuilder cardNum = new StringBuilder();
		
		for (int n = 0; n < 16; n++) {
			int digit = random.nextInt(10);
			cardNum.append(digit);
		}
		
		return cardNum.toString();
	}
	
	private String generateCvvNumber() {
		Random random = new Random();
		StringBuilder CVVnum = new StringBuilder();
		
		for (int n = 0; n < 3; n++) {
			int digit = random.nextInt(10);
			CVVnum.append(digit);
		}
		
		return CVVnum.toString();
	}
	
	
	
	


	public String getSixteenDigits() {
		return sixteenDigits;
	}


	public String getFistName() {
		return FistName;
	}


	public String getLastName() {
		return LastName;
	}


	public String getOpened() {
		return opened.toString();
	}


	public String getExpiration() {
		//return expiration;
		return exp.toString();
	}


	public String getCvv() {
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
