package com.barcelonatechnologyschool;

public class FiboNonRec
{	
	public static void main(String[] args) 
	{
		long fiboSum = 0;
		long lastElement = 1, previousLastelement = 1;
		int numElements = Integer.parseInt(args[0]);
		
		for (int idx = 1; idx <= numElements; idx++)
		{
			if ((idx == 1) || (idx == 2))
			{
				fiboSum++;
				System.out.print(1 + " ");
			}
			else
			{
				fiboSum = lastElement + previousLastelement;
				previousLastelement = lastElement;
				lastElement = fiboSum;
				System.out.print(fiboSum + " ");
			}			
		}		
	}
}
