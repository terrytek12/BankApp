package myTesting;

public class BankAppAPI {

	/*
	 * In order for a Customers to interact with the Banking
	 * App they must create a Customer Account. The Constructor takes
	 * four inputs (String email, String firstName, String lastName,
	 * String password) when creating a new Customer Account. 
	 * Customer Account instances will also hold an Address object
	 * and a Bank Account array object that acts as a list of Bank
	 * Accounts that is linked to a the current Customer Account. 
	 * Methods created are getters and setters, adding an existing
	 * Bank Account(BankAccount account), getters and setters for 
	 * Customer Account's Address, deposit, withdraw and log methods.
	 * 
	 * // This endpoint expects four objects.	 */
	CustomerAccount customerAccount = new CustomerAccount("jdoe@gmail.com",
													"John","Doe","1234");
	// *** Methods to include, will work on this *** // 
	// getStreetAddress setStreetAddress // Combines Street number and 
	// 										Street name into one String.
	// isOnline // When flag is set, prevents account from being accessed
	//  			by another client/server.
	// addBankAccount(BankAccount Account) // Add an existing BankAccount to 
	// 											CustomerAccount BankAccount[]
	//											array (list of Bank Accounts)
	// getBAbyIndex // Takes int, returns Bank Account for that index under
	//					Customer Account
	// 
	/* 
	 *
	 */
}
