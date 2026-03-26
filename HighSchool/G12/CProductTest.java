
public class CProductTest
{
	public static void main(String[] args)
	{
		//Individual objects
		CProduct p1 = new CProduct("Nikon", "Black", 2, 1.99);
		System.out.println(p1.getPrice() + " " + p1.getName() + " " + p1.getColour() + " " + p1.getLens());

		CProduct p2 = new CProduct("Canon", "Brown", 1, 2.99);
		System.out.println(p2.getPrice() + " " + p2.getName() + " " + p2.getColour() + " " + p2.getLens());

		//array of objects
		CProduct[] p3 = new CProduct[3];
		for(int i = 0; i < p3.length; i++)
		{
			p3[i] = new CProduct();
		}

		for(int i = 0; i < p3.length; i++)
		{
			System.out.println(p3[i].getPrice() + " " + p3[i].getName() + " " + p3[i].getColour() + " " + p3[i].getLens());
		}

		//array of companies
		p1.company = new CCompany[3];
		for(int i = 0; i < p1.company.length; i++)
		{
			p1.company[i] = new CCompany();
		}

		for(int i = 0; i < p1.company.length; i++)
		{
			System.out.println(p1.company[i].getName() + " " + p1.company[i].getYear() + " " + p1.company[i].getCompanyID());
		}
	}
}