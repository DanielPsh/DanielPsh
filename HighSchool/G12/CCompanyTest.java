

public class CCompanyTest
{
    public static void main(String[] args)
    {
		//individual companies
		CCompany c1 = new CCompany("Google", 2020);
		System.out.println(c1.getName() + " " + c1.getYear() + " " + c1.getCompanyID());

		CCompany c2 = new CCompany("Apple", 1999);
		System.out.println(c2.getName() + " " + c2.getYear() + " " + c2.getCompanyID());

		//array of companies/objects
		CCompany[] c3 = new CCompany[3];
		for(int i = 0; i < c3.length; i++)
		{
			c3[i] = new CCompany();
		}

		for(int i = 0; i < c3.length; i++)
		{
			System.out.println(c3[i].getName() + " " + c3[i].getCompanyID());
		}

		//array of products
		c1.product = new CProduct[3];
		for(int i = 0; i < c1.product.length; i++)
		{
			c1.product[i] = new CProduct();
		}

		for(int i = 0; i < c1.product.length; i++)
		{
			System.out.println(c1.product[i].getName() + " " + c1.product[i].getColour() + " " + c1.product[i].getLens() + " " + c1.product[i].getPrice());
		}
    }
}