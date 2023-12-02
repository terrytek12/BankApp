package JUnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import myTesting.BankAccount;

public class BankAccountConstructorTest {

	@Test
	public void testBankAccountConstructor() {
		
		BankAccount test = new BankAccount(0000, 0.0, "John", "Doe");
		String result = test.getFirstName();
		
		System.out.println(test.getId());
		
		assertEquals("John", result);
	}

}
