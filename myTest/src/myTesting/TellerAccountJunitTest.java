package myTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TellerAccountJUnitTest {

	@Test
	public void testTellerAccount() {
		TellerAccount tellerTest = new TellerAccount ("Jack", "Davis", "jackdavis12@gmail.com", "hellothere"); 
		
	}

	
	//getter Junit tests
	@Test
	public void testGetFirstName() {
		TellerAccount tellerTest = new TellerAccount ("Jack", "Davis", "jackdavis12@gmail.com", "hellothere");
		assertEquals("Jack", tellerTest.getFirstName());
	}
	
	@Test
	public void testGetLastName() {
		TellerAccount tellerTest = new TellerAccount ("Jack", "Davis", "jackdavis12@gmail.com", "hellothere");
		assertEquals("Davis", tellerTest.getLastName());
	}
	
	@Test
	public void testGetEmail() {
		TellerAccount tellerTest = new TellerAccount ("Jack", "Davis", "jackdavis12@gmail.com", "hellothere");
		assertEquals("jackdavis12@gmail.com", tellerTest.getEmail());
	}
	
	@Test
	public void testGetPassword() {
		TellerAccount tellerTest = new TellerAccount ("Jack", "Davis", "jackdavis12@gmail.com", "hellothere");
		assertEquals ("hellothere", tellerTest.getPassword());
		
	}
	
	
	//setter Junit tests
	@Test
	public void testSetFirstName() {
		TellerAccount tellerTest = new TellerAccount ("Jack", "Davis", "jackdavis12@gmail.com", "hellothere");
		tellerTest.setFirstName("Tom");
		assertEquals ("Tom", tellerTest.getFirstName());
	}

	@Test
	public void testSetLastName() {
		TellerAccount tellerTest = new TellerAccount ("Jack", "Davis", "jackdavis12@gmail.com", "hellothere");
		tellerTest.setLastName("Cruise");
		assertEquals ("Cruise", tellerTest.getLastName());
		
	}

	@Test
	public void testSetEmail() {
		TellerAccount tellerTest = new TellerAccount ("Jack", "Davis", "jackdavis12@gmail.com", "hellothere");
		tellerTest.setEmail("tomcruise19@aol.com");
		assertEquals("tomcruise19@aol.com", tellerTest.getEmail());
	}

	@Test
	public void testSetPassword() {
		TellerAccount tellerTest = new TellerAccount ("Jack", "Davis", "jackdavis12@gmail.com", "hellothere");
		tellerTest.setPassword("luckyducky12");
		assertEquals("luckyducky12", tellerTest.getPassword());
	}

}
