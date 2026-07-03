package AP;

public class ArrayTest6 
{
	public static void main(String[] args)
	{
		int[][] array = {
				{10, 20, 30, 40},
				{50, 60, 70, 80},
				{90, 100, 110, 120}
		};
		
		System.out.println("length array: " + array.length);
		System.out.println("length row: " + array[0].length);
		for(int r = 0; r < array.length; r++)
		{
			for(int c = 0; c < array[r].length; c++)
			{
				System.out.println(r + "row " + c + "column: " + array[r][c]);
			}
		}
	}
}
