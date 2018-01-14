package threeCardGame;

import java.util.ArrayList;
import java.util.Random;

public class ChoiceWildCard {
	public void choiceWildCard()
	{
		
	}
	public String getWildCard(Player p)
	{
		int h=calculateTheSumOfTheSuiteSent(p.getp().getH());
		int s=calculateTheSumOfTheSuiteSent(p.getp().getS());
		int c=calculateTheSumOfTheSuiteSent(p.getp().getC());
		int d=calculateTheSumOfTheSuiteSent(p.getp().getD());
		String wild=getTheHigestForChochingWildCard(h,s,c,d);
		if(!(wild.equalsIgnoreCase("")))
		{
			return wild;
		}
		else 
		{
			if(h>=6)
				return "H";
			if(s>=6)
				return "S";
			if(d>=6)
				return "D";
			if(c>=6)
				return "C";
			String []aWildGuess={"S","D","C","H"};
			Random rnd=new Random();
			int guessedPosition=rnd.nextInt(100);
			return aWildGuess[guessedPosition%4];
			
		}
	}
	
	public int calculateTheSumOfTheSuiteSent(ArrayList<String> suite)
	{
		int sum=0;
		for(int i=0;i<suite.size();++i)
		{
			sum+=givesTheValueOfACard(suite.get(i).substring(1));
		}
		return sum;
	}
	public String getTheHigestForChochingWildCard(int h,int s,int c,int d)
	{
		if(h>s && h>c&& h>d)
			return "H";
		if(s>h&&s>d&&s>c)
			return "S";
		if(c>s && h<c&& c>d)
			return "C";
		if(d>h&&s<d&&d>c)
			return "D";
		return "";
	}
	public int givesTheValueOfACard(String card)
	{
		switch(card)
		{
			case "ace.png"	:return 8;
			case "king.png"	:return 7;
			case "queen.png":return 6;
			case "jack.png"	:return 5;
			case "ten.png"	:return 4;
			case "nine.png"	:return 3;
			case "eight.png":return 2;
			case "seven.png":return 1;
		}
		return -1;
	}
	public boolean hasPlayerClickOnThecard(int x,int y)
	{
		if(x>=300 &&x<=570
				&& y>=460 &&y<=554)
		{
			return true;
		}
		return false;
	}
	public int whichCardHasPlayerHasChosen(int x,int y)
	{
		int x1=300;
		int x2=49;//+49
		for(int i=0;i<5;++i)
		{
			if(x>=x1 && x<=(x1+x2)
					)
			{
				return i;
			}
			if(i==3)
			{
				x2=69;
			}
				x1=x1+50;
		}
		return -1;
	}
}
