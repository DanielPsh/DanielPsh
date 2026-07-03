package AP;

public class CheckDigit 
{
	public static int getCheck(int num)
	{
		int factor = 7;
		int total = 0;
		while(num > 0)
		{
			total += (num % 10) * factor;
			num /= 10;
			factor--;
		}
		int remainder = total % 10;
		int checkDigit = 10 - remainder;
		
		return checkDigit;
	}
	public static boolean isValid(int numWithCheckDigit)
	{
		int num = numWithCheckDigit / 10;
		int eCheckDigit = getCheck(num);
		int aCheckDigit = numWithCheckDigit % 10;
		
		return eCheckDigit == aCheckDigit;
	}
	public static int getNumberofDigits(int num)
	{
		int count = 0;
		while(num > 0)
		{
			count++;
			num /= 10;
		}
		return count;
	}
	public static int getDigit(int num, int n)
	{
		int divisor = (int) Math.pow(10, n-1);
		int digit = (num/divisor) % 10;
		return digit;
	}
}
