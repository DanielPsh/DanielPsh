
import java.util.Scanner;

public class CMS
{
	public static void main(String[] args)
	{
		System.out.println("====================================================");
		System.out.println("\nWelcome to Camera Management System v1.2 Testing\n");
        System.out.println("\n BY Daniel Park \n");
		System.out.println("====================================================");

		CCompany[] companies = null;
		CProduct[] products = null;
		int choice;

		do
		{
			Scanner inputChoice = new Scanner(System.in);
			System.out.print("\n======== MAIN MENU ========");
			System.out.print("\n[1] Companies\n[2] Cameras\n[3] Exit Program\nEnter your choice: ");
			choice = inputChoice.nextInt();

			if(choice == 1)
			{
				int choiceCompany;

				do
				{
					Scanner inputChoiceCompany = new Scanner(System.in);
					System.out.print("\n======== SUB MENU ========");
					System.out.print("\n[1] Add New Companies\n[2] Add Cameras to Companies\n[3] Display Companies\n[4] Exit\nEnter your choice: ");
					choiceCompany = inputChoiceCompany.nextInt();

					if(choiceCompany == 1)
					{
						System.out.println("\n<<ADD NEW COMPANY>>\n");

						if(companies == null)
						{
							System.out.println("No Companies yet!");

							Scanner inputCompaniesCreate = new Scanner(System.in);
							System.out.print("Continue creating Companies? [y/n] ");
							char companiesCreate = inputCompaniesCreate.nextLine().charAt(0);

							if(companiesCreate == 'y')
								companies =	createCompanies(companies);
						}
						else
						{
							System.out.println("<<Current Companies>>");
							displayCompanies(companies);

							Scanner inputCompaniesCreate = new Scanner(System.in);
							System.out.print("\nContinue creating Companies? [y/n] ");
							char companiesCreate = inputCompaniesCreate.nextLine().charAt(0);

							if(companiesCreate == 'y')
								addCompanies(companies);
						}

					}
					else if(choiceCompany == 2)
					{
						System.out.println("\nAdd cameras to Companies\n");
						System.out.println("Add cameras in the Companies in the order of the COMPANY ID");

						if(companies == null)
						{
							System.out.println("Create Companies first!");

							Scanner inputCompaniesCreate = new Scanner(System.in);
							System.out.print("\nContinue creating Companies? [y/n] ");
							char companiesCreate = inputCompaniesCreate.nextLine().charAt(0);

							if(companiesCreate == 'y')
								companies =	createCompanies(companies);
						}

						else
						{
							System.out.println("<<Current Companies>>");
							displayCompanies(companies);

							Scanner inputCompaniesCreateProduct = new Scanner(System.in);
							System.out.print("\nContinue adding cameras? [y/n] ");
							char companiesCreateProduct = inputCompaniesCreateProduct.nextLine().charAt(0);

							if(companiesCreateProduct == 'y')
							{
								addCompanyProducts(companies);
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
						System.out.println("\n<<INVALID input!>>");
				}while(choiceCompany != 4);
			}
			else if(choice == 2)
			{
				int choiceProduct;
				do
			{
				Scanner inputChoiceProduct = new Scanner(System.in);
				System.out.print("\n======== SUB MENU ========");
				System.out.print("\n[1] Add New Camera\n[2] Add Companies to the Camera\n[3] Display Camera\n[4] Exit\nEnter your choice: ");
				choiceProduct = inputChoiceProduct.nextInt();

				if(choiceProduct == 1)
				{
					System.out.println("\n<<ADD NEW CAMERA>>\n");

					if(products == null)
					{
						System.out.println("No cameras yet!");

						Scanner inputProductCreate = new Scanner(System.in);
						System.out.print("Continue creating cameras? [y/n] ");
						char productsCreate = inputProductCreate.nextLine().charAt(0);

						if(productsCreate == 'y')
							products =	createProducts(products);
					}
					else
					{
						System.out.println("<<Current cameras>>");
						displayProducts(products);

						Scanner inputCompaniesCreate = new Scanner(System.in);
						System.out.print("\nContinue adding cameras? [y/n] ");
						char companiesCreate = inputCompaniesCreate.nextLine().charAt(0);

						if(companiesCreate == 'y')
							addProducts(products);
					}

				}
				else if(choiceProduct == 2)
				{
					System.out.println("\nAdd Companies to the cameras\n");
					System.out.println("Add Companies into the cameras in the order of the CAMERA ID");

					if(products == null)
					{
						System.out.println("Create cameras first!");

						Scanner inputCompaniesCreate = new Scanner(System.in);
						System.out.print("\nContinue creating cameras? [y/n] ");
						char companiesCreate = inputCompaniesCreate.nextLine().charAt(0);

						if(companiesCreate == 'y')
							products =	createProducts(products);
					}

					else
					{
						System.out.println("<<Current Cameras>>");
						displayProducts(products);

						Scanner inputCompaniesCreateProduct = new Scanner(System.in);
						System.out.print("\nContinue adding cameras? [y/n] ");
						char companiesCreateProduct = inputCompaniesCreateProduct.nextLine().charAt(0);

						if(companiesCreateProduct == 'y')
						{
							addProductCompanies(products);
						}

					}
				}
				else if(choiceProduct == 3)
				{
					System.out.println("\nDisplay Companies");
					displayProducts(products);
				}
				else if(choiceProduct == 4)
					choiceProduct = 4;
				else
					System.out.println("\n<<INVALID input!>>");
				}while(choiceProduct != 4);
			}
			else if (choice == 3)
				choice = 3;
			else
				System.out.println("<<INVALID input!>>");
		}while(choice != 3);

		System.out.println("Good Bye!\n Exiting Program....");
	}
	public static void displayCompanies(CCompany[] companies)
	{
		for(int i = 0; i < companies.length; i++)
		{
			if(companies == null)
				System.out.println("no Companies yet");
			else if(companies[i].product == null)
			{
				System.out.println("ID: " + (i + 1) + "\nName of the COMPANY: " + companies[i].getName() + " \nYear of establishment of COMPANY:" + companies[i].getYear());
				System.out.println("no cameras yet");
			}
			else
			{
				System.out.println("ID: "+ (i + 1) + " Name of the COMPANY: " + companies[i].getName());
				System.out.println("ID: "+ (i + 1) + " Year of establishment of COMPANY: " + companies[i].getYear());
				if(companies[i].product != null)
				{
					for(int j = 0; j < companies[i].product.length; j++)
					{
						System.out.println("==========================");
						System.out.println("Name: " + companies[i].product[j].getName());
                        System.out.println("Lens: " + companies[i].product[j].getLens());
                        System.out.println("Colour: " + companies[i].product[j].getColour());
                        System.out.println("Type: " + companies[i].product[j].getType());
                        System.out.println("Price: " + companies[i].product[j].getPrice());
						System.out.println("==========================");
					}
				}
			}
		}
	}
	public static void displayProducts(CProduct[] products)
	{
		for(int i = 0; i < products.length; i++)
		{
			if(products == null)
				System.out.println("no cameras yet");
			else if(products[i].company == null)
			{
				System.out.println((i + 1) + " " + products[i].getName() + " "  + products[i].getLens() + " " + products[i].getColour() + " " + products[i].getType() + " " + products[i].getPrice());
				System.out.println("no Companies yet");
			}
			else
			{
				System.out.println((i + 1) + " " + "NAME: " + products[i].getName());
				System.out.println((i + 1) + " " + "LENS: " + products[i].getLens());
				System.out.println((i + 1) + " " + "COLOUR: " + products[i].getColour());
				System.out.println((i + 1) + " " + "TYPE:" + products[i].getType());
				System.out.println((i + 1) + " " + "PRICE: " + products[i].getPrice());
				if(products[i].company != null)
				{
					for(int j = 0; j < products[i].company.length; j++)
					{
						System.out.println("Name of the COMPANY: " + products[i].company[j].getName());
						System.out.println("Year of establishment of COMPANY: " + products[i].company[j].getYear());
					}
				}
			}
		}
	}

	public static CCompany[] createCompanies(CCompany[] companies)
	{
		Scanner inputNumberCompanies = new Scanner(System.in);
		System.out.print("How many Companies? ");
		int numberCompanies = inputNumberCompanies.nextInt();

		companies = new CCompany[numberCompanies];
		for(int i = 0;i<companies.length;i++)
		{
			companies[i] = new CCompany();
			System.out.println("\nADD NEW Companies\n");
			Scanner inputInfo = new Scanner(System.in);
			System.out.print("Enter the name of COMPANY " + (i + 1) + ": ");
			String nameX = inputInfo.nextLine();
			System.out.print("Enter the year of establishment of COMPANY " + (i+1) + ": ");
			int yearX = inputInfo.nextInt();
			companies[i].setName(nameX);
			companies[i].setYear(yearX);
		}
		return companies;
	}
	public static CProduct[] createProducts(CProduct[] products)
	{
		Scanner inputNumberProducts = new Scanner(System.in);
		System.out.print("How many Product? ");
		int numberProducts = inputNumberProducts.nextInt();

		products = new CProduct[numberProducts];
		for(int i = 0; i < products.length; i++)
		{
			System.out.print("Enter the name of the Camera " + (i + 1) + ": ");
			String nameX = inputNumberProducts.next();
			System.out.print("Enter the lens of the Camera " + (i + 1) + ": ");
			int lensX = inputNumberProducts.nextInt();
			System.out.print("Enter the Colour of the Camera " + (i + 1) + ": ");
			String colourX = inputNumberProducts.next();
            System.out.print("Enter the Type of the Camera " + (i + 1) + ": ");
			String typeX = inputNumberProducts.next();
			System.out.print("Enter the price of the Camera " + (i + 1) + ": ");
			double priceX = inputNumberProducts.nextDouble();
			products[i] = new CProduct();
			products[i].setName(nameX);
			products[i].setLens(lensX);
			products[i].setColour(colourX);
            products[i].setType(typeX);
			products[i].setPrice(priceX);
		}
		return products;
	}

	public static CCompany[] addCompanies(CCompany[] companies)
	{
		Scanner inputCompanyName = new Scanner(System.in);
		System.out.print("\nEnter name of COMPANY: ");
		String companyName = inputCompanyName.nextLine();
		for(int i = 0; i < companies.length; i++)
		{
			if(companies[i].getName() == "no name")
			{	companies[i].setName(companyName);
				break;
			}
			else
				System.out.print("Can not add anymore.");
		}

		return companies;
	}
	public static CProduct[] addProducts(CProduct[] products)
	{
		Scanner inputProductName = new Scanner(System.in);
		System.out.print("\nEnter name of the Camera: ");
		String productName = inputProductName.next();
		System.out.print("Enter the lens of the Camera: ");
		int productLens = inputProductName.nextInt();
		System.out.print("Enter the colour of the Camera: ");
		String productColour = inputProductName.next();
        System.out.print("Enter the type of the Camera: ");
		String productType = inputProductName.next();
		System.out.print("Enter the price of the Camera: ");
		double productPrice = inputProductName.nextDouble();

		for(int i = 0; i < products.length; i++)
		{
			if(products[i].getName() == "no name")
			{
				products[i].setName(productName);
				products[i].setLens(productLens);
				products[i].setColour(productColour);
                products[i].setType(productType);
				products[i].setPrice(productPrice);
				break;
			}
			else
				System.out.print("Can not add anymore.");
		}

		return products;
	}

	public static CCompany[] addCompanyProducts(CCompany[] companies)
	{
		System.out.println("\n<<Current Companies>>");
		displayCompanies(companies);

		Scanner inputX = new Scanner(System.in);
		Scanner inputChoiceCompany = new Scanner(System.in);
		System.out.print("\nChoose a COMPANY: ");
		int choiceCompany = inputChoiceCompany.nextInt();

		Scanner inputNumberCompaniesProducts = new Scanner(System.in);
		System.out.print("How many Camerass? ");
		int numberCompaniesProducts = inputNumberCompaniesProducts.nextInt();
		int x = choiceCompany - 1;
		companies[x].product = new CProduct[numberCompaniesProducts];


		for(int i = 0; i < companies[x].product.length; i++)
		{
			System.out.print("Enter the name of the Camera " + (i + 1) + ": ");
			String nameX = inputX.next();
			System.out.print("Enter the lens of the Camera " + (i + 1) + ": ");
			int lensX = inputX.nextInt();
			System.out.print("Enter the colour of the Camera " + (i + 1) + ": ");
			String colourX = inputX.next();
            System.out.print("Enter the type of the Camera " + (i + 1) + ": ");
			String typeX = inputX.next();
			System.out.print("Enter the price of the Camera " + (i+  1) + ": ");
			double priceX = inputX.nextDouble();
			companies[x].product[i] = new CProduct();
			companies[x].product[i].setName(nameX);
			companies[x].product[i].setLens(lensX);
			companies[x].product[i].setColour(colourX);
            companies[x].product[i].setType(typeX);
			companies[x].product[i].setPrice(priceX);
		}
		return companies;
	}
	public static CProduct[] addProductCompanies(CProduct[] products)
	{
		System.out.println("\nCurrent Cameras");
		displayProducts(products);

		Scanner inputX = new Scanner(System.in);
		Scanner inputChoiceCompany = new Scanner(System.in);
		System.out.print("\nChoose a Camera: ");
		int choiceCompany = inputChoiceCompany.nextInt();

		Scanner inputNumberCompaniesProducts = new Scanner(System.in);
		System.out.print("How many Companies? ");
		int numberCompaniesProducts = inputNumberCompaniesProducts.nextInt();
		int x = choiceCompany - 1;
		products[x].company = new CCompany[numberCompaniesProducts];


		for(int i = 0; i < products[x].company.length; i++)
		{
			products[x].company[i] = new CCompany();
			System.out.println("\nADD NEW Companies\n");
			Scanner inputInfo = new Scanner(System.in);
			System.out.print("Enter the name of COMPANY " + (i+1) + ": ");
			String nameX = inputInfo.nextLine();
			System.out.print("Enter the year of establishment of COMPANY " + (i+1) + ": ");
			int yearX = inputInfo.nextInt();
			products[x].company[i].setName(nameX);
			products[x].company[i].setYear(yearX);
		}
		return products;
	}

	
}