package myTesting;

public class MyTEST {
	public static void main(String[] args) {
		
		
		TestClass willTest = new TestClass("Jeff");
		
		
		
		BankAccount BaccountA = new BankAccount(3, 4, "s", "s");
		
		BankAccount BaccountB = BaccountA;
		
		BaccountB.setPin(123);
		
		System.out.println(BaccountA.getPin());
		
		
		
		
		
		System.out.println(willTest.getMyString());
		System.out.println("Hi");
	}
}