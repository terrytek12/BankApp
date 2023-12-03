package myTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.Before;

class CustomerAccountJunitTest {
/*
	CustomerAccount testCA;
	BankAccount testBA;
	@Before public void initialize() {
	this.testCA = new CustomerAccount("jdoe@gmail.com","John","Doe","1234");
	this.testCA.setStreetNumber(101);testCA.setStreetName("2nd St");testCA.setCity("Hayward");testCA.setState("California");
	this.testCA.setZip(94544);testCA.setStreetAddress();
	this.testBA = new BankAccount(1234, 0.0, "John", "Doe");
	}
	*/
	@Test
	public void testCustomerAccountConstructor() {
		CustomerAccount testCA = new CustomerAccount("jdoe@gmail.com","John","Doe","0123");
		
		testCA.setStreetNumber(101);testCA.setStreetName("2nd St");testCA.setCity("Hayward");testCA.setState("California");
		testCA.setZip(94544);testCA.setStreetAddress();
		
		
		assertEquals("jdoe@gmail.com",testCA.getEmail());
		assertEquals("John Doe",testCA.getFirstName()+" "+testCA.getLastName());
		assertEquals("101 2nd St", testCA.getStreetAddress());
	}

	@Test
	public void testCustomerAccountsetPass() {
		CustomerAccount testCA = new CustomerAccount("jdoe@gmail.com","John","Doe","0123");
		testCA.setPass("4321");
		assertEquals("4321",testCA.getPassword());
	}
	
	@Test
	public void testCustomerAccountBankAccount() {
		CustomerAccount testCA = new CustomerAccount("jdoe@gmail.com","John","Doe","1234");
		
		testCA.setStreetNumber(101);testCA.setStreetName("2nd St");testCA.setCity("Hayward");testCA.setState("California");
		testCA.setZip(94544);testCA.setStreetAddress();
		BankAccount testBA = new BankAccount(1234, 0.0, "John", "Doe");
		testCA.AddBankAccount(testBA);
		
		int testid = testBA.getId();
		
		assertEquals(testCA.getBAbyIndex(0),testCA.getBankAccount(testid));
	
	}
	
	@Test
	public void testCustomerAccounttoString() {
		CustomerAccount testCA = new CustomerAccount("jdoe@gmail.com","John","Doe","1234");
		
		String testString = testCA.toString();
		
		assertEquals("jdoe@gmail.com,John,Doe,1234,\n",testString);
	}
	
}
