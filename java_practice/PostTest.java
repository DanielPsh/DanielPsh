package AP;

public class PostTest 
{
	public static void main(String[] args)
	{
		PostOffice p[];
		p = new PostOffice[10];
		Mail m = p[9].getBox(56).getMail(3);
	}
}
