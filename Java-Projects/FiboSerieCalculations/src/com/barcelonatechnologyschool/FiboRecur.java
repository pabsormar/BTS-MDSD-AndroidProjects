package com.barcelonatechnologyschool;

public class FiboRecur 
{
	public static void main(String[] args)
	{
	    int index = Integer.parseInt(args[0]);
	    for (int i = 0; i <= index; i++)
	    	System.out.print(fiboCalc(i) + " ");	
	    
	    // If you just want the Nth element, System.out.print(fiboCalc(index));
	}
	
    public static long fiboCalc(int i)
    {
    	if ((i == 0) || (i == 1)) // base cases; to stop the recursion
    		return i;
    	else
    		// recursion step; where the method/function is called until a base case is found
    		return fiboCalc(i - 1) + fiboCalc(i - 2); 
    }
}
