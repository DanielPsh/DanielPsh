package AP;

public class ArrayTest {
	public static void main(String[] args)
	{
		int[][] mat = {
				{2, 6, 8, 7},
				{1, 5, 4, 0},
				{9, 3, 2, 8}
		};
		System.out.println("row-by-row");
		for(int i = 0; i < mat.length; i++)
		{
			int sum = 0;
			for(int j = 0; j < mat[i].length; j++)
			{
				System.out.print(mat[i][j] + " ");
				sum += mat[i][j];
			}
			System.out.println("Sum: " + sum);
			System.out.println();
		}
		System.out.println("column-by-column");
		for(int i = 0; i < mat[0].length; i++)
		{
			int sum = 0;
			for(int j = 0; j < mat.length; j++)
			{
				System.out.print(mat[j][i] + " ");
				sum += mat[j][i];
			}
			System.out.println("Sum: " + sum);
			System.out.println();
		}
		
		int[] Csum = new int[mat[0].length];
		
		for(int i = 0; i < mat.length; i++)
		{
			int sum = 0;
			for(int j = 0; j < mat[i].length; j++)
			{
				System.out.print(mat[i][j] + "  ");
			}
			System.out.println();
		}
		for (int j = 0; j < mat[0].length; j++) 
		{
		    int sum = 0;
		    for (int i = 0; i < mat.length; i++) 
		    {
		        sum += mat[i][j];
		    }
		    Csum[j] = sum;
		}
		for (int i = 0; i < Csum.length; i++) {
		    System.out.print(Csum[i] + " ");
		}
	}
}

