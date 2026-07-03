package AP;

public class SelectionSort 
{
	private int[] a;
	
	public SelectionSort(int[] arr)
	{
		a = arr;
	}
	
	private void swap(int i, int j)
	{
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public void slectionSort()
	{
		int maxPos, max;
		for(int i = 0; i < a.length -1; i++)
		{
			max = a [i];
			maxPos = i;
			for(int j = i + 1; j < a.length; j++)
				if(max < a[j])
				{
					max = a[j];
					maxPos = j;
					System.out.println("(i: " + i + ", j: " + j + ") " + "max: " + max);
					System.out.println("(i: " + i + ", j: " + j + ") " + "maxPos: " + maxPos);
				}
			swap(i, maxPos);
			//print array
		}
	}
	
	public void slectionSortAsd()
	{
		int minPos, min;
		for(int i = 0; i < a.length -1; i++)
		{
			min = a [i];
			minPos = i;
			for(int j = i + 1; j < a.length; j++)
				if(min > a[j])
				{
					min = a[j];
					minPos = j;
					System.out.println("(i: " + i + ", j: " + j + ") " + "max: " + min);
					System.out.println("(i: " + i + ", j: " + j + ") " + "maxPos: " + minPos);
				}
			swap(i, minPos);
			//print array
		}
	}
	
	public static void printData(int[] data)
	{
		System.out.println("Data 1");
		for(int i = 0; i < data.length; i++)
		{
			System.out.println("[" + i + "] " + data[i]);
		}
	}
	public void printData2(int[] data)
	{
		System.out.println("Data 2");
		for(int i = 0; i < data.length; i++)
		{
			System.out.println("[" + i + "] " + data[i]);
		}
	}
}
