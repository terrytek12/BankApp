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
		CustomerAccount testCA = new CustomerAccount("jdoe@gmail.com","John","Doe","1234");
		
		testCA.setStreetNumber(101);testCA.setStreetName("2nd St");testCA.setCity("Hayward");testCA.setState("California");
		testCA.setZip(94544);testCA.setStreetAddress();
		
		
		assertEquals("jdoe@gmail.com",testCA.getEmail());
		assertEquals("John Doe",testCA.getFirstName()+" "+testCA.getLastName());
		assertEquals("101 2nd St", testCA.getStreetAddress());
	}

	@Test
	public void testCustomerAccountsetPass() {
		CustomerAccount testCA = new CustomerAccount("jdoe@gmail.com","John","Doe","1234");
		testCA.setPass("4321");
		assertEquals("4321",testCA.getPassword());
	}
	
	@Test
	public void testCustomerAccountgetEmail() {
		CustomerAccount testCA = new CustomerAccount("jdoe@gmail.com","John","Doe","1234");
		assertEquals("jdoe@gmail.com",testCA.getEmail());
	}
	
	@Test
	public void testCustomerAccountgetName() {
		CustomerAccount testCA = new CustomerAccount("jdoe@gmail.com","John","Doe","1234");
		assertEquals("John Doe",testCA.getFirstName()+" "+testCA.getLastName());
	}
	
	@Test
	public void testCustomerAccountgetPassword() {
		CustomerAccount testCA = new CustomerAccount("jdoe@gmail.com","John","Doe","1234");
		assertEquals("1234",testCA.getPassword());
	}
	
	@Test
	public void testCustomerAccountgetAddress() {
		CustomerAccount testCA = new CustomerAccount("jdoe@gmail.com","John","Doe","1234");
		testCA.setStreetNumber(101);testCA.setStreetName("2nd St");testCA.setCity("Hayward");testCA.setState("California");
		testCA.setZip(94544);testCA.setStreetAddress();
		
		assertEquals("101 2nd St Hayward California 94544",testCA.getStreetAddress()+" "+
														testCA.getCity()+" "+testCA.getState()+
														" "+testCA.getZip());
		
	}
	
	@Test
	public void testCustomerAccountisOnline() {
		CustomerAccount testCA = new CustomerAccount("jdoe@gmail.com","John","Doe","1234");
		assertEquals(false,testCA.isOnline());
	}
	
	@Test
	public void testCustomerAccountsetAddress() {
		CustomerAccount testCA = new CustomerAccount("jdoe@gmail.com","John","Doe","1234");
		testCA.setStreetNumber(212);
		testCA.setStreetName("First St");
		testCA.setApt("Apt 3");
		testCA.setStreetAddress();
		testCA.setCity("Las Vegas");
		testCA.setState("Nevada");
		testCA.setZip(83523);
		assertEquals("212 First St Apt 3 Las Vegas Nevada 83523",testCA.getStreetAddress()+" "+
				testCA.getCity()+" "+testCA.getState()+
				" "+testCA.getZip());

	}
	
	@Test
	public void testCustomerAccountAddGetBankAccount() {
		CustomerAccount testCA = new CustomerAccount("jdoe@gmail.com","John","Doe","1234");
		
		testCA.setStreetNumber(101);testCA.setStreetName("2nd St");testCA.setCity("Hayward");testCA.setState("California");
		testCA.setZip(94544);testCA.setStreetAddress();
		BankAccount testBA = new BankAccount(1234, 0.0, "John", "Doe");
		testCA.AddBankAccount(testBA);
		
		int testid = testBA.getId();
		
		assertEquals(testCA.getBAbyIndex(0),testCA.getBankAccount(testid));
	
	}
	
	@Test
	public void testCustomerAccountremoveAccount() {
		CustomerAccount testCA = new CustomerAccount("jdoe@gmail.com","John","Doe","1234");
		BankAccount testBA1 = new BankAccount(1234, 0.0, "John", "Doe");
		testCA.AddBankAccount(testBA1);
		BankAccount testBA2 = new BankAccount(1234, 50.00, "Jane", "Doe");
		testCA.AddBankAccount(testBA2);
		testCA.removeBankAccount(testBA1.getId());
		assertEquals(1,testCA.numBankAccounts());
		
	}
	
	@Test
	public void testCustomerAccounttoString() {
		CustomerAccount testCA = new CustomerAccount("jdoe@gmail.com","John","Doe","1234");
		
		String testString = testCA.toString();
		
		assertEquals("jdoe@gmail.com,John,Doe,1234,\n",testString);
	}
	
}
