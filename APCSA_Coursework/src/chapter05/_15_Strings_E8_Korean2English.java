package chapter05;

public class _15_Strings_E8_Korean2English {

	public static void main(String[] args) {
		
		/* 
		 * Korean names (in English phonetics) typically place family names first, 
		 * then given names. English names place given names, then family names last. 
		 * Also, Korean names may have multiple syllables of one name separated; 
		 * for example "Gil Dong" is not a name of a guy called Gil with a middle name Dong, 
		 * but rather one whole name better translated to "Gildong".
		 * 
		 * This is an attempt to turn Korean names into an English version.
		 */
		
		String name1 = "Ko Gil Dong"; // ==> Gildong Ko
		System.out.println( Korean2English(name1) );
		String name2 = "Kang Hoon"; // ==> Hoon Kang
		System.out.println( Korean2English(name2) );
		String name3 = "Dong Bang Bul Pae"; // ==> Bulpae Dongbang
		System.out.println( Korean2English(name3) );
		String name4 = "Park Dong Bang Bul Pae"; // ==> Bulpae Dongbang Park
		System.out.println( Korean2English(name4) );
		// unfortunately, there is no way to distinguish a regular Korean name from 
		// one with double syllable family names, such as Nam Goong Chuck
		
	}

	/**
	 * @param kName: contains a Korean name
	 * @return : the English version of kName
	 */
	public static String Korean2English(String kName) {
		String eName = "";
		
		// count spaces
		int spaces = 0;
		for(int i = 0; i < kName.length(); i++)
		{
			if(kName.substring(i,i+1).equals(" ") ){
				spaces++;
			}
		}
		// System.out.println("Number of Spaces: "+ spaces);
		
		if(spaces == 1) 
		{
			int first = kName.indexOf(" ");
			String w1 = kName.substring(0, first);
			String w2 = kName.substring(first + 1);
			eName = w2 + " " + w1;
		} else if(spaces == 2) 
		{
			int first = kName.indexOf(" ");
			int second = kName.indexOf(" ", first + 1);
			String w1 = kName.substring(0, first);
			String w2 = kName.substring(first + 1, second);
			String w3 = kName.substring(second + 1);
			eName = w2 + w3.toLowerCase() + " " + w1;
		} else if(spaces == 3) 
		{
			int first = kName.indexOf(" ");
			int second = kName.indexOf(" ", first + 1);
			int third = kName.indexOf(" ", second + 1);
			String w1 = kName.substring(0, first);
			String w2 = kName.substring(first + 1, second);
			String w3 = kName.substring(second + 1, third);
			String w4 = kName.substring(third + 1);
			eName = w3 + w4.toLowerCase() + " " + w1 + w2.toLowerCase();
		} else if(spaces == 4)
		{
			int first = kName.indexOf(" ");
			int second = kName.indexOf(" ", first + 1);
			int third = kName.indexOf(" ", second + 1);
			int fourth = kName.indexOf(" ", third + 1);
			String w1 = kName.substring(0, first);
			String w2 = kName.substring(first + 1, second);
			String w3 = kName.substring(second + 1, third);
			String w4 = kName.substring(third + 1, fourth);
			String w5 = kName.substring(fourth + 1);
			eName = w4 + w5.toLowerCase() + " " + w2 + w3.toLowerCase() + " " + w1;
		}
		else {
			eName = "can't process this...";
		}

		
		return eName;
	}
	


}
