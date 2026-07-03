package AP;

public class Address 
{
	private String name;
	private String street;
	private String city;
	private String state;
	private String zip;
	
	Address()
	{
		this.name = null;
		this.street = null;
		this.city = null;
		this.state = null;
		this.zip = null;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getStreet()
	{
		return street;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public String getState()
	{
		return state;
	}
	
	public String getZip()
	{
		return zip;
	}
}
