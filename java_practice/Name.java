package AP;

public class Name
{
	// instance field
    private String firstName;
    private String lastName;
    
    public Name(String first, String last)  //constructor
    {
        firstName = first;
        lastName = last;
    }
    
    // Overriding 
    public String toString()
    { 
    	return firstName + " " + lastName; 
    }
    
    public boolean equals(Object obj)
    {
        Name n = (Name) obj;
        return n.firstName.equals(firstName) && n.lastName.equals(lastName);
    }
    
    public int compareTo(Name n)
    {
    	return 0;
    	/*
    	if (lastName.equals(n.lastName))
    	       return firstName.compareTo(n.firstName);
    	   else
    	       return 0;
    	       */
    	
    	 /*
    	int lastComp = lastName.compareTo(n.lastName);
    	   if (lastComp != 0)
    	       return lastComp;
    	   else
    	       return firstName.compareTo(n.firstName);
    	       */
    	       
    	/*
    	 
    	if (!(lastName.equals(n.lastName)))
    	       return firstName.compareTo(n.firstName);
    	   else
    	       return lastName.compareTo(n.lastName);
    	       */
    }
} 
