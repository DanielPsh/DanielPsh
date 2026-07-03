package AP;

import java.util.*;
public class ArrTest 
{
	public static void main(String[] args)
	{
		int[] arr = new int[10];
		Random rand = new Random();
		
		//generate random number from 1-10
		for (int i = 0; i < arr.length; i++) 
		{
			arr[i] = rand.nextInt(10) + 1;
	        System.out.println(arr[i]);
		}
		
		//search for random number
		int search = rand.nextInt(10) + 1;
	    System.out.println("Searching for " + search);
	    
	    //print binary
		if (binarySearch(arr, search) == -1) 
		{
			System.out.println("Number not found");
		}
		else 
	    {
			System.out.println("Number found at index: " + binarySearch(arr, search));
	    }
        
        //print linear
        if (linearSearch(arr, search) == -1) {
            System.out.println("Number not found");
        } 
        else 
        {
            System.out.println("Number found at index: " + linearSearch(arr, search));
        }
	}
	
	//binary search
	public static int binarySearch(int[] arr, int target) 
	{
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        
        while (low <= high) 
        {
            mid = (low + high) / 2;
            if (arr[mid] == target) 
            {
                return mid;
            } 
            else if (arr[mid] < target) 
            {
                low = mid + 1;
            } 
            else 
            {
                high = mid - 1;
            }
        }
        return -1;
    }
	
	//linear search 
    public static int linearSearch(int[] arr, int target) 
    {
        for (int i = 0; i < arr.length; i++) 
        {
            if (arr[i] == target) 
            {
                return i;
            }
        }
        return -1;
    }
}
