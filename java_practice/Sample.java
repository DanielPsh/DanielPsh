package AP;

public class Sample {
	int val = 0;
	public static void main(String[] args)
	{
		Sample s = new Sample();
		Object tmp = new Object();
		s.writeMe(tmp);
	}
	public void writeMe(Object obj)
	{
		System.out.println("object");
	}
	public void writeMe(String s)
	{
		System.out.println("string");
	}
}
