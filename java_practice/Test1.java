package AP;

public class Test1 {
	public static void main(String[] args)
	{
		System.out.println(mystery(10));
		System.out.println(3543%10);
		
	}
	public static int mystery(int x)
	{
		if(x <= 1)
		{
			return 1;
		}
		else
		{
			return 2 * mystery(x-2);
		}
	}
}
