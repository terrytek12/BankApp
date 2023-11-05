package myTesting;

public class CustomerAccount {
	private String Email; 
	private String FirstName;
	private String LastName; 
	private String Password; 
	private boolean isOnline = false; 
	private BankAccount BAccountList[];
	// address object here 
	private int numBankAccounts; 
	
	
	
	
	
	public CustomerAccount(String E, String FN, String LN, String Pass) {
		this.Email = E;
		this.FirstName = FN; 
		this.LastName = LN; 
		this.Password = Pass;
		this.numBankAccounts = 0;
		BAccountList = new BankAccount[7];
		
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
	
	
	public void AddBankAccount(BankAccount Account) {
		
		
		if (numBankAccounts >= BAccountList.length) {
			BankAccount[] newArray = new BankAccount[BAccountList.length * 2];
			
			for (int i = 0; i < BAccountList.length; i++) {
		        newArray[i] = BAccountList[i];
		    }
			
			BAccountList = newArray;
			
		}
		
		
		BAccountList[numBankAccounts] = Account;
		
		numBankAccounts += 1;
		
		
	}
	
	
	
	
	
	
}
