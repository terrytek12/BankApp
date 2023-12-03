package myTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BankAccountJunitTest {

	
	@Test
	void ConstructorTest() {
		
		BankAccount TestBA = new BankAccount(1234, 100, "John", "Doe");
		
		assertTrue(TestBA.getPin() == 1234 && TestBA.getBalance() == 100 && TestBA.getFirstName().equals("John") && TestBA.getLastName().equals("Doe") && TestBA.getId() >= 1);
		
		
		
	}
	
	
	@Test
	void DepositTestandLogTest() {
		
		BankAccount TestBA = new BankAccount(1234, 100, "John", "Doe");
		
		TestBA.deposit(100);
		
		assertTrue(TestBA.getBalance() == 200);
		
		//assertTrue(TestBA.getLog().equals("LogEntry:deposited $100,"));
		
		
	}
	
	
	@Test
	void WithdrawTestandLogTest() {
		
		BankAccount TestBA = new BankAccount(1234, 100, "John", "Doe");
		
		TestBA.withdraw(100);
		
		assertTrue(TestBA.getBalance() == 0);
		
		//assertTrue(TestBA.getLog().equals("LogEntry:withdrew $100,"));
		
		
	}
	
	@Test 
	void setPinTest(){
		BankAccount TestBA = new BankAccount(1234, 100, "John", "Doe");
		
		TestBA.setPin(4321);
		
		assertTrue(TestBA.getPin() == 4321);
		
		
		
		
		
	}
	
	
}
