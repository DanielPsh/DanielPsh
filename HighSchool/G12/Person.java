

public class Person
{
	int age;
	String name;

	Person()
	{
		this.age = 0;
		this.name = "no name";
	}
	Person(int newAge, String newName)
	{
		this.age = newAge;
		this.name = newName;
	}

	public void setAge(int age)
	{
		this.age = age;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getAge()
	{
		return this.age;
	}
	public String getName()
	{
		return this.name;
	}
}
