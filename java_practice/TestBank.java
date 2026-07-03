package AP;

public class TestBank 
{
	public static void main(String[] args)
	{
		BankAccount b = new BankAccount();
		BankAccount c = new BankAccount("KevinC", 800.00);
		b.deposit("", 400.00);
		c.withdraw("KevinC", 900.00);
		System.out.println("Balance: " + b.getBalance() + "\nBalance: " + c.getBalance());
	}
}
