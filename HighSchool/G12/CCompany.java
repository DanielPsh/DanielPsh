

public class CCompany extends CBusiness
{
	private int companyID;
	private static int currentNumberCompanies = 0;
	CProduct[] product;

	public CCompany()
	{
		super();
		this.currentNumberCompanies++;
		this.companyID = this.companyID + currentNumberCompanies;
	}

	CCompany(String name, int year)
	{
		super(name, year);
		this.currentNumberCompanies++;
		this.companyID = this.companyID + currentNumberCompanies;
	}


	public int getCompanyID()
	{
		return companyID;
	}

	public void setCompanyID(int companyID)
	{
		this.companyID = companyID;
	}
}