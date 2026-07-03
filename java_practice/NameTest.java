package AP;


public class NameTest
{
	public static void main(String[] args)
	{
		Name n1 = new Name("Scott","Dentes");
    	Name n2 = new Name("Nick","Elser");
    	System.out.println(n1);
    	System.out.println(n2);
    	System.out.print(n1.compareTo(n2));
	}
}