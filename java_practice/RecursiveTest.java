package AP;

public class RecursiveTest 
{
	public static void main(String[] args)
	{
		//System.out.println(sum(3));
		//System.out.println(mystery(3,2,6));
		//StringRecur("E");
		//strRecur("12345678");
		doSomething(3);
	}
	public static int sum(int n)
	{
		if (n == 1)
			return 1;
	    else
	    	return n + sum(n - 1);
	}
	public static int mystery(int n, int a, int d)
	{
		if(n == 1)
		{
			return a;
		}else {
			return d + mystery(n-1, a, d);
		}
	}
	public static void StringRecur(String s)
	{
		if(s.length() < 15)
		{
			System.out.println(s);
		}
		StringRecur(s + "*");
	}
	public static void strRecur(String s)
	{
		if(s.length() < 15)
		{
			System.out.println(s);
			strRecur(s + "*");
		}
	}
	public static void doSomething(int n)
	{
		if(n > 0)
		{
			doSomething(n-1);
			System.out.print(n);
			doSomething(n-1);
		}
	}
}

