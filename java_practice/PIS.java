package AP;

import java.util.Scanner;

public class PIS
{
	public static void main(String[] args)
	{
		System.out.println("=======================================");
		System.out.println("\nWelcome to Product Information System\n");
		System.out.println("=======================================");

		Company[] companies = null;
		Product[] products = null;
		int choice;

		do
		{
			Scanner inputChoice = new Scanner(System.in);
			System.out.print("\n======== MENU ========");
			System.out.print("\n[1] Company\n[2] Product\n[3] Exit\nEnter your choice: ");
			choice = inputChoice.nextInt();

			if(choice == 1)
			{
				int choiceCompany;

				do
				{
					Scanner inputChoiceCompany = new Scanner(System.in);
					System.out.print("\n======== SUB MENU ========");
					System.out.print("\n[1] Add New Company\n[2] Add Products to Company\n[3] Display Companies\n[4] Exit\nEnter your choice: ");
					choiceCompany = inputChoiceCompany.nextInt();

					if(choiceCompany == 1)
					{
						System.out.println("\nADD NEW COMPANY\n");

						if(companies == null)
						{
							System.out.println("No companies yet!");

							Scanner inputCompaniesCreate = new Scanner(System.in);
							System.out.print("Continue creating companies? [y/n] ");
							char companiesCreate = inputCompaniesCreate.nextLine().charAt(0);

							if(companiesCreate == 'y')
								companies =	createCompanies(companies);
						}
						else
						{
							System.out.println("Current Companies");
							displayCompanies(companies);

							Scanner inputCompaniesCreate = new Scanner(System.in);
							System.out.print("\nContinue creating companies? [y/n] ");
							char companiesCreate = inputCompaniesCreate.nextLine().charAt(0);

							if(companiesCreate == 'y')
								addCompanies(companies);
						}

					}
					else if(choiceCompany == 2)
					{
						System.out.println("\nAdd Products to Company\n");

						if(companies == null)
						{
							System.out.println("Create companies first!");

							Scanner inputCompaniesCreate = new Scanner(System.in);
							System.out.print("\nContinue creating companies? [y/n] ");
							char companiesCreate = inputCompaniesCreate.nextLine().charAt(0);

							if(companiesCreate == 'y')
								companies =	createCompanies(companies);
						}

						else
						{
							System.out.println("Current Companies");
							displayCompanies(companies);

							Scanner inputCompaniesCreateProduct = new Scanner(System.in);
							System.out.print("\nContinue adding products? [y/n] ");
							char companiesCreateProduct = inputCompaniesCreateProduct.nextLine().charAt(0);

							if(companiesCreateProduct == 'y')
							{
								addCompanyProducts(companies);
								displayCompanies(companies);
							}

						}
					}
					else if(choiceCompany == 3)
					{
						System.out.println("\nDisplay Companies");
						displayCompanies(companies);
					}
					else if(choiceCompany == 4)
						choiceCompany = 4;
					else
						System.out.println("\nNot a valid input!");
				}while(choiceCompany != 4);
			}
			else if(choice == 2)
			{
				Scanner inputProductArraySize = new Scanner(System.in);
				System.out.print("Enter a number of Products: ");
				int productArraySize = inputProductArraySize.nextInt();

				Product[] productArray = new Product[productArraySize];

				for(int i = 0; i < productArray.length; i++)
				{
					productArray[i] = new Product();

					Scanner inputInfo = new Scanner(System.in);
					System.out.print("Enter the name of product " + (i + 1) + " : ");
					String nameX = inputInfo.nextLine();
					System.out.print("Enter the colour of product " + (i + 1) + " : ");
					String colourX = inputInfo.nextLine();
					System.out.print("Enter the shape of product " + (i + 1) + " : ");
					String shapeX = inputInfo.nextLine();
					System.out.print("Enter the price of product " + (i + 1) + " : ");
					double priceX = inputInfo.nextDouble();

					productArray[i].setName(nameX);
					productArray[i].setColor(colourX);
					productArray[i].setShape(shapeX);
					productArray[i].setPrice(priceX);
				}
				for(int i = 0; i < productArray.length; i++ )
				{
					System.out.println("\nName of product " + (i + 1) + " : " + productArray[i].getName());
					System.out.println("Color: " + productArray[i].getColor());
					System.out.println("Shape: " + productArray[i].getShape());
					System.out.println("Price: " + productArray[i].getPrice());
					System.out.println("Product ID: " + productArray[i].getProductID());
				}
			}
			else if (choice == 3)
				choice = 3;
			else
				System.out.println("Not a valid input!");
		}while(choice != 3);

		System.out.println("Goodbye!");
	}
	public static void displayCompanies(Company[] companies)
	{
		if(companies == null)
			System.out.println("no companies yet");
		else if(companies[0].getProductList() == null)
		{
			// -1 added
			for(int i = 0; i < (companies.length)-1; i++)
				System.out.println((i+1) + " " + companies[i].getName() + " " + companies[i].getYear());

			System.out.println("no products yet");
		}
		else
		{
			// -1 added
			for(int i = 0; i < (companies.length)-1; i++)
			{
				System.out.println((i+1) + " " + companies[i].getName()+ " " +companies[i].getYear());

				// -1 added
				for(int j = 0; j < (companies[i].getProductList()).length-1; j++)
				{
					System.out.println((companies[i].getProductList())[j].getName() + " " + (companies[i].getProductList())[j].getColor() + " " + (companies[i].getProductList())[j].getShape() + " " + (companies[i].getProductList())[j].getPrice());
				}
			}
		}
	}

	public static Company[] createCompanies(Company[] companies)
	{
		Scanner inputNumberCompanies = new Scanner(System.in);
		System.out.print("How many companies? ");
		int numberCompanies = inputNumberCompanies.nextInt();

		companies = new Company[numberCompanies];
		for(int i = 0;i<companies.length;i++)
		{
			companies[i] = new Company();
			System.out.println("\nADD NEW COMPANY\n");
			Scanner inputInfo = new Scanner(System.in);
			System.out.print("Enter the name of company " + (i + 1) + " : ");
			String nameX = inputInfo.nextLine();
			System.out.print("Enter the year of establishment of company " + (i + 1) + " : ");
			int yearX = inputInfo.nextInt();
			companies[i].setName(nameX);
			companies[i].setYear(yearX);
		}
		return companies;
	}

	public static Company[] addCompanies(Company[] companies)
	{
		Scanner inputCompanyName = new Scanner(System.in);
		System.out.print("\nEnter name of company: ");
		String companyName = inputCompanyName.nextLine();

		for(int i = 0; i < companies.length; i++)
		{
			if(companies[i].getName() == "no name")
			{	companies[i].setName(companyName);
				break;
			}
			else
				System.out.print("Can not add anymore");
		}

		return companies;
	}

	public static Company[] addCompanyProducts(Company[] companies)
	{
		System.out.println("\nCurrent Companies");
		displayCompanies(companies);

		Scanner inputX = new Scanner(System.in);
		Scanner inputChoiceCompany = new Scanner(System.in);
		System.out.print("\nChoose a company: ");
		int choiceCompany = inputChoiceCompany.nextInt();

		Scanner inputNumberCompaniesProducts = new Scanner(System.in);
		System.out.print("How many products? ");
		int numberCompaniesProducts = inputNumberCompaniesProducts.nextInt();
		int x = choiceCompany - 1;
		companies[x].setProductList(new Product[numberCompaniesProducts]);


		for(int i = 0; i < companies[x].getProductList().length; i++)
		{
			System.out.print("Enter the name of product " + (i + 1) + " : ");
			String nameX = inputX.nextLine();
			System.out.print("Enter the colour of product " + (i + 1) + " : ");
			String colourX = inputX.nextLine();
			System.out.print("Enter the shape of product " + (i + 1) + " : ");
			String shapeX = inputX.nextLine();
			System.out.print("Enter the price of product " + (i + 1) + " : ");
			double priceX = inputX.nextDouble();
			companies[x].getProductList()[i] = new Product();
			companies[x].getProductList()[i].setName(nameX);

			companies[x].getProductList()[i].setColor(colourX);
			companies[x].getProductList()[i].setShape(shapeX);
			companies[x].getProductList()[i].setPrice(priceX);
		}
		return companies;
	}
}