package chapter05;

public class _11_Strings_E6_Separate {

	public static void main(String[] args) {
		
		String str = "aabbccddee";
		
		// 1. finish the method separate()
		// 2. use separate() to print str two characters per line
/*
aa
bb
cc
dd
ee
 */
		separate(str);
		
	}
	
	/**
	 * prints s two characters per line
	 * @param s
	 */
	public static void separate(String s) 
	{
		for(int i = 0; i < s.length(); i+=2)
		{
			System.out.println(s.substring(i, i + 2));
		}
	}

}
