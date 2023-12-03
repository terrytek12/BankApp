package myTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CustomerAccountJunitTest {

	@Test
	void testCustomerAccountConstructor() {
		CustomerAccount testCA = new CustomerAccount("jdoe@gmail.com","John","Doe","0123");
		
		testCA.setStreetNumber(101);testCA.setStreetName("2nd St");testCA.setCity("Hayward");testCA.setState("California");
		testCA.setZip(94544);testCA.setStreetAddress();
		
		assertEquals("jdoe@gmail.com",testCA.getEmail());
		assertEquals("John Doe",testCA.getFirstName()+" "+testCA.getLastName());
		assertEquals("101 2nd St", testCA.getStreetAddress());
	}


	
	@Test
	void testCustomerAccountAddBankAccount() {
		CustomerAccount testCA = new CustomerAccount("jdoe@gmail.com","John","Doe","1234");
		
		testCA.setStreetNumber(101);testCA.setStreetName("2nd St");testCA.setCity("Hayward");testCA.setState("California");
		testCA.setZip(94544);testCA.setStreetAddress();
		BankAccount testBA = new BankAccount(1234, 0.0, "John", "Doe");
		testCA.AddBankAccount(testBA);
		
		int testid = testBA.getId();
		testCA.getBankAccount(testid);
		System.out.println(testid);
		System.out.println(testCA.getBankAccount(testid));
		System.out.println(testBA.getPin());
	
	}
	
}
