package AP;

public class Practice 
{
	public static void main(String[] args)
	{
		String s1 = "Hot";
		String s2 = "Hotel";
		String s3 = "Dog";
		String s4 = "Hot";
		
		//compareTo check return value
		int ret = s1.compareTo(s4);
		System.out.println("ret: " + ret);
		
		// if-else
		if(ret < 0)
		{
			System.out.println("s1 < s2");	//true, s1 terminates first
		}
		else if(ret > 0)
		{
			System.out.println("s1 > s2");	//false, "H" comes before "d"
		}
		else if(ret == 0)
		{
			System.out.println("s1 == s2");
		}
		else
		{
			System.out.print("Invalid Input!");
		}
	}
}	
