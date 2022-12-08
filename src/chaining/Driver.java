package chaining;

import java.util.Random;

public class Driver 
{
	public static void main(String[] args)
	{
		Random rand = new Random();
		Table hashT = new Table();
		String value;
		
		for(int i = 0; i < 10000; i++)
		{
			value = Integer.toString(rand.nextInt(1000000));
			hashT.add(Integer.toString(i), value);
		}
		
		long timeBefore = System.currentTimeMillis();
		for(int i = 0; i < 100; i++)
		{
			value = Integer.toString(rand.nextInt(1000000));
			System.out.println("Node: " + hashT.get((value)));
			
			
		}
		long timeAfter = System.currentTimeMillis() - timeBefore;
		System.out.println("Time to search: " + timeAfter + " milliseconds");
		
		
		
		
		
	
		
		
	}

}
