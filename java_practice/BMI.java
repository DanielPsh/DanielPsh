package AP;

import java.util.Scanner;

public class BMI 
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);

		System.out.print("Enter weight in kg: ");
		double weight = input.nextDouble();

		System.out.print("Enter height in cm: ");
		double height = input.nextDouble();

		//final double KILOGRAMS_PER_POUND = 0.45359237;
		//final double METERS_PER_INCH = 0.0254;

		//double weightInKilograms = weight * KILOGRAMS_PER_POUND;
		//double heightInMeters = height * METERS_PER_INCH;
		double bmi = (weight / Math.pow(height, 2) * 10000);

		System.out.println("BMI is " + bmi);
		if (bmi<24)
		System.out.println("Underweight");
		else if (bmi>27 && bmi < 30)
		System.out.println("Normal");
		else if (bmi>30)
		System.out.println("Overweight");
		else System.out.println("Obese");
	}
}