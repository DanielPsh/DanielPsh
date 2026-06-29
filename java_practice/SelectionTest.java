package AP;

public class SelectionTest 
{
	public static void main(String[] args)
	{
		int[] data = {4,5,8,7};
		System.out.println("----- Before -----");
		SelectionSort.printData(data);
		/*
		for(int i = 0; i < data.length; i++)
		{
			System.out.println("Before: " + "[" + i + "] " + data[i]);
		}
		*/
		SelectionSort s = new SelectionSort(data);
		//s.slectionSort(); //greatest -> least
		s.slectionSortAsd(); //least -> greatest
		System.out.println("----- After -----");
		SelectionSort.printData(data);
		/*
		for(int i = 0; i < data.length; i++)
		{
			System.out.println("After: " + "[" + i + "] " + data[i]);
		}
		System.out.println();
		*/
		
		//////////////////////////////////////////////////////////
		SelectionSort s2 = new SelectionSort(data);
		s2.printData2(data);
		
		int ret = Integer.max(10, 7);
		System.out.println(ret);
		
		int ret2 = Integer.min(10, 7);
		System.out.println(ret2);
		
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		System.out.println(Integer.SIZE); //8 bit == 1 byte
		
		int a1 = Math.abs(-70);
		System.out.println(a1);
		
		double a2 = Math.abs(-666.8888);
		System.out.println(a2);
	}
}
