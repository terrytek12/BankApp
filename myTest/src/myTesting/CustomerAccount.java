package myTesting;

public class CustomerAccount {
	private String Email; 
	private String FirstName;
	private String LastName; 
	private String Password; 
	private boolean isOnline = false; 
	private BankAccount BAccountList[];
	//public	Address address; 
	private int numBankAccounts; 
	
	
	
	
	
	public CustomerAccount(String E, String FN, String LN, String Pass) {
		this.Email = E;
		this.FirstName = FN; 
		this.LastName = LN; 
		this.Password = Pass;
		this.numBankAccounts = 0;
		//this.address = new Address();
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
	
	/*
	
	public String getStreetAddress() {
		return this.address.getStreetAddress();
	}
	
	public int getStreetNumber() {		
		return this.address.getStreetNumber();
	}
	
	public String getStreetName() {
		return this.address.getStreetName();
	}
	
	public String getApt() {		
		return this.address.getApt();
	}
	
	public String getCity() {		
		return this.address.getCity();
	}
	
	public String getState() {		
		return this.address.getState();
	}
	
	public int getZip() {		
		return this.address.getZip();
	}
	
	*/
	
	
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
	
	/*

	public void setStreetNumber(int nmbr) {
		this.address.setStreetNumber(nmbr);
	}
	
	public void setStreetName(String name) {
		this.address.setStreetName(name);
	}
	
	public void setApt(String apt) {
		this.address.setApt(apt);
	}
	
	public void setStreetAddress() {
		this.address.setStreetAddress();
	}
	
	public void setCity(String city) {
		this.address.setCity(city);
	}
	
	public void setState(String state) {
		this.address.setState(state);
	}
	
	public void setZip(int zip) {
		this.address.setZip(zip);
	}
	*/
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
	
	
	
	public void removeBankAccount(int id) {
		
		
		for (int n = 0; n < numBankAccounts; n++) {
			if(BAccountList[n].getId() == id) {
				for(int j = n; j < numBankAccounts; j++) {
					BAccountList[j] = BAccountList[j+1];
				}
				
				
				BAccountList[numBankAccounts - 1] = null;
				numBankAccounts--;
			}
		}
		
		
		
		
	}
	
	public BankAccount getBankAccount(int id) {
		for (int n = 0; n < numBankAccounts; n++) {
	        // Check if BAccountList[n] is not null and if the id matches
	        if (BAccountList[n] != null && BAccountList[n].getId() == id) {
	            return BAccountList[n]; // Return immediately upon finding the match
	        }
	    }
	    return null; 
	
	}
	
	
	public BankAccount getBAbyIndex(int i) {
		return BAccountList[i];
	}
	
	public boolean getOnline() {
		return this.isOnline;
	}
		
	public String toString() {
		
		StringBuilder bankAccountsStr = new StringBuilder();
	    for (int i = 0; i < numBankAccounts; i++) {
	        if (i > 0) bankAccountsStr.append(""); // Add a space between bank accounts
	        bankAccountsStr.append(BAccountList[i].toString());
	    }
	    return Email + "," + FirstName + "," + LastName + "," + Password + "," + (bankAccountsStr.length() > 0 ?  bankAccountsStr.toString() : "") + "\n";
		
		
	}
	
	
}
