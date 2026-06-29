package AP;

public class IntPair
{
    private int firstValue;
    private int secondValue;
    public IntPair(int first, int second)
    {
        firstValue = first;
        secondValue = second;
    }
    public int getFirst()
    { return firstValue; }
    public int getSecond()
    { return secondValue; }
    public void setFirst(int a)
    { firstValue = a; }
    public void setSecond(int b)
    { secondValue = b;}
}