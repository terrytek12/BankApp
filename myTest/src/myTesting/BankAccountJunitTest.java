package myTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BankAccountJunitTest {

	@Test
	void test() {
		fail("Not yet implemented");
	}

	
	@Test
	void ConstructorTest() {
		
		BankAccount TestBA = new BankAccount(1234, 100, "John", "Doe", 001);
		
		assertTrue(TestBA.getPin() == 1234 && TestBA.getBalance() == 100 && TestBA.getFirstName().equals("John") && TestBA.getLastName().equals("Doe") && TestBA.getId() == 001);
		
		
		
	}
	
	
	@Test
	void DepositTestandLogTest() {
		
		BankAccount TestBA = new BankAccount(1234, 100, "John", "Doe", 001);
		
		TestBA.deposit(100);
		
		assertTrue(TestBA.getBalance() == 200);
		
		//assertTrue(TestBA.getLog().equals("LogEntry:deposited $100,"));
		
		
	}
	
	
	@Test
	void WithdrawTestandLogTest() {
		
		BankAccount TestBA = new BankAccount(1234, 100, "John", "Doe", 001);
		
		TestBA.withdraw(100);
		
		assertTrue(TestBA.getBalance() == 0);
		
		//assertTrue(TestBA.getLog().equals("LogEntry:withdrew $100,"));
		
		
	}
	
	@Test 
	void setPinTest(){
		BankAccount TestBA = new BankAccount(1234, 100, "John", "Doe", 001);
		
		TestBA.setPin(4321);
		
		assertTrue(TestBA.getPin() == 4321);
		
		
		
		
		
	}
	
	
}
