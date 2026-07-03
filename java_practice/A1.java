package AP;

public class A1 {
	public static void main(String[] args)
	{
		/*
		int a = 0;
		for(int x = 0; x < 10; x++)
		{
			for(int z = 0; z <= 5; z++)
			{
				for(int i = 1; i <= 16; i = i *2) 
				{
					a++;
				}
			}
		}
		System.out.println(a);
		
		int a = 10;
		double b = 3.7;
		int c = 4;
		int x = (int)(a+b);
		double y = (double) a/c;
		double z = (double) (a/c);
		double w = x + y + z;
		System.out.println(w);
		
		int i = 1;
		int k = 1;
		while (i < 5)
		{
			k *= i;
			k++;
		}
		System.out.println(k);
		*/
		
		

		
		int[][] arr = {
				{1,2,3,4},
				{5,6,7,8},
				{9,10,11,12}
		};
		int sum = 0;
		for(int[] x: arr)
		{
			for(int y = 0; y < x.length - 1; y++)
			{
				sum += x[y];
			}
		}
		System.out.print(sum);
	}
}
