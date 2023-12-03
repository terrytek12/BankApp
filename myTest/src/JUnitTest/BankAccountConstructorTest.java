package JUnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import myTesting.BankAccount;

public class BankAccountConstructorTest {

	//@SuppressWarnings("deprecation")
	@Test
	public void testBankAccountConstructor() {
		
		BankAccount test = new BankAccount(0000, 0.0, "John", "Doe");
		String result = test.getFirstName();
		
		System.out.println(test.getId());
		
		assertEquals("John", result);
		assertEquals("Doe", test.getLastName());
		assertEquals(0000, test.getPin());
		assertEquals(0.0, test.getBalance(), 0);
	}

}
