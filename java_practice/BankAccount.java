package AP;

public class BankAccount 
{
	private String password;
	private double balance;
	public static final double OVERDRAWN_PENALTY = 20.00;
	
	public BankAccount()
	{
		password = "";
		balance = 0.0;
	}
	public BankAccount(String acctPassword, double acctBalance)
	{
		password = acctPassword;
		balance = acctBalance;
	}
	public double getBalance()
	{
		return balance;
	}
	public void deposit(String acctPassword, double amount)
	{
		if(!acctPassword.equals(password))
		{
			System.out.println("Incorrect");
		}
		else {
			balance += amount;
		}
	}
	public void withdraw(String acctPassword, double amount)
	{
		if(!acctPassword.equals(password))
		{
			System.out.println("Incorrect");
		}
		else {
			balance -= amount;
			if(balance < 0)
			{
				balance -= OVERDRAWN_PENALTY;
			}
		}
	}
}
