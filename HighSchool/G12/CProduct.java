

public class CProduct extends CThings
{
	private static int productID = 0;
	private double price;
	private static int currentNumberProducts = 0;
	CCompany[] company;

	CProduct()
	{
		super();
		this.price = 0;
		this.currentNumberProducts++;
		this.productID = this.productID + currentNumberProducts;
	}

	CProduct(String name, String colour, String type, int lens, double price)
	{
		super(name, colour, type, lens);
		this.currentNumberProducts++;
		this.productID = this.productID + currentNumberProducts;
	}

	public double getPrice()
	{
		return price;
	}

	public int getProductID()
	{
		return productID;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public void setPrice(int productID)
	{
		this.productID = productID;
	}
}