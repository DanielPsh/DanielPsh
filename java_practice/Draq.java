package AP;

public class Draq 
{
	int len;
	int x;
	int y;
	
	public static void main(String[] args) 
	{
		Draq.drawLine(2, 3, 4, 4);
		Draq.drawSpuare(1, 4, 3); //3
		Draq.drawSpuare(6, 8, 5); //4
		Draq.drawSpuare(7, 2, 4); //2
	}
	
	public static void drawLine(int x, int y, int x2, int y2) 
	{
		System.out.println("x1: " + x + ", y1: " + y + ", x2: " + x2 + ", y2: "+ y2);
	}
	
	public static void drawSpuare(int x, int y, int len) 
	{
		while(y-len < 0 || x+len > 10)
		{
			len = len - 1;
		}
		System.out.println("Length: " + len + ", area: " + len*len);
	}
}
