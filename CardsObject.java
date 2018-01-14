package threeCardGame;

import java.util.ArrayList;

public class CardsObject 
{
	private ArrayList <String> spade;
	private ArrayList <String> heart;
	private ArrayList <String> diamond;
	private ArrayList <String> clubs;
	private char wildCard;
	public char getWildCard()
	{
		return wildCard;
	}
	public void setWildCard(char wl)
	{
		wildCard=wl;
	}
	public ArrayList<String> getS()
	{
		return spade;
	}
	public ArrayList<String> getH()
	{
		return heart;
	}
	public ArrayList<String> getC()
	{
		return clubs;
	}
	public ArrayList<String> getD()
	{
		return diamond;
	}
	public CardsObject()
	{
		spade=new ArrayList<String>();
		heart=new ArrayList<String>();
		clubs=new ArrayList<String>();
		diamond=new ArrayList<String>();
	}
	public int lengthSpade()
	{
		return spade.size();
	}
	public int lengthHeart()
	{
		return heart.size();
	}
	public int lengthDiamond()
	{
		return diamond.size();
	}
	public int lengthClubs()
	{
		return clubs.size();
	}
	public String getSpade(int i) {
		return spade.get(i);
	}
	public void removeSpade(int i)
	{
		spade.remove(i);
	}
	public void setSpade(String c) {
		spade.add(c);
	}
	
	public String getHeart(int i) {
		return heart.get(i);
	}
	public void removeHeart(int i)
	{
		heart.remove(i);
	}
	public void setHeart(String c) {
		heart.add(c);
	}
	
	public String getClubs(int i) {
		return clubs.get(i);
	}
	public void removeClubs(int i)
	{
		clubs.remove(i);
	}
	public void setClubs(String c) {
		clubs.add(c);
	}
	
	public String getDiamond(int i) {
		return diamond.get(i);
	}
	public void removeDiamond(int i)
	{
		diamond.remove(i);
	}
	public void setDiamond(String c) {
		diamond.add(c);
	}
	public int bestS(Player c,OnTableCard t)
	{
		return commonLogicOfComputer(c, t,spade);
	}
	public int bestC(Player c,OnTableCard t)
	{
		return commonLogicOfComputer(c, t,clubs);
	}
	public int bestH(Player c,OnTableCard t)
	{
		return commonLogicOfComputer(c, t,heart);
		}
	public int bestD(Player c,OnTableCard t)
	{
		return commonLogicOfComputer(c, t,diamond);
	}
	public int commonLogicOfComputer(Player c,OnTableCard t,ArrayList<String> suite)
	{
		if(suite.size()==0)
			return -1;
		if(t.getc1().equalsIgnoreCase("")
				&& t.getc2().equalsIgnoreCase("")
				&& t.getp().equalsIgnoreCase(""))
			/*
			 * no one has thrown a card
			 *  on the current table
			 *   so the computer calling this function
			 *   has to throw the card first
			 */
		{
			int i=getTheBiggestCard(suite);
			return getTheCardInAllSuite(c,suite.get(i));
		}
		else if(!(t.getc1().equalsIgnoreCase("")))
			/*
			 * computer1 has throw the card
			 * that means computer2 has to throw
			 */
		{
			if(!(t.getp().equalsIgnoreCase("")))
				/*
				 * user player was the first to
				 * throw a card
				 */
			{
				/*
				 * get the best card between the two player if
				 * it is the case that the computer can not 
				 * win the current table then play the worst card
				 * else return the lowest card from which you can win
				 */
				String betterCard=betterCardBetweenTwo(t.getp(),t.getc1());
				if(t.getc1().charAt(0)==t.getp().charAt(0)
							|| (t.getp().equalsIgnoreCase(betterCard)))
					/*
					 * if computer1 and player suite is same
					 * OR
					 * player suite is a wild card and the 
					 * computer1 has not thrown wild card
					 */
				{
					int i=aBetterCardThenTheCardSent(betterCard,suite);
					return getTheCardInAllSuite(c,suite.get(i));
				}
				else 
					/*
					 * goes here if computer has played a wild card while
					 * player had perivsorly thrown a simple card
					 */
				{
					int i=worseCardOfTheSuiteSent(suite);
					return getTheCardInAllSuite(c,suite.get(i));
				}
			}
			else
				/*
				 * player has not throw the card
				 * so the current computer2 has to thorw the best card of the suite thrown by 
				 * computer1
				 */
			{
				int i;
				i=aBetterCardThenTheCardSent(t.getc1(),suite);
				return getTheCardInAllSuite(c,suite.get(i));
			}
		}
		else if(!(t.getp().equalsIgnoreCase("")))
			/*
			 * player has throw the card then computer1 has to throw
			 */
		{
			if(!(t.getc2().equalsIgnoreCase("")))
				/*
				 * computer2 has thrown the card then mean computer1
				 * has to throw a card which is better than the card 
				 * of the computer2 and the player...
				 */
			{

				/*
				 * get the best card between the two player if
				 * it is the case that the computer can not 
				 * win the current table then play the worst card
				 * else return the lowest card from which you can win
				 */
				String betterCard=betterCardBetweenTwo(t.getc2(),t.getp());
				if(t.getc2().charAt(0)==t.getp().charAt(0)
						||	(t.getc2().equalsIgnoreCase(betterCard)))
					/*
					 * if computer1 and player suite is same
					 * OR
					 * player suite is a wild card and the 
					 * computer1 has not thrown wild card
					 */
				{
					int i=aBetterCardThenTheCardSent(betterCard,suite);
					return getTheCardInAllSuite(c,suite.get(i));
				}
				else
					/*
					 * goes here if player has played a wild card while
					 * computer had perivsorly thrown a simple card
					 */
				{
					int i=worseCardOfTheSuiteSent(suite);
					return getTheCardInAllSuite(c,suite.get(i));
				}
			
			}
			else
				/*
				 * computer2 has not throw the card
				 */
			{
				int i;
				i=aBetterCardThenTheCardSent(t.getp(),suite);
				i=getTheCardInAllSuite(c,suite.get(i));
				return i;
			}
		}
		return -1;
	
	}
	public int aBetterCardThenTheCardSent(String card,ArrayList<String> suite)
	{
		switch(card.substring(1))
		{
			case	"ace.png"	:return aCardJustBetterThanThePlayer( suite,7);
			case	"king.png"	:return aCardJustBetterThanThePlayer( suite,0);
			case	"queen.png"	:return aCardJustBetterThanThePlayer( suite,1);
			case	"jack.png"	:return aCardJustBetterThanThePlayer( suite,2);
			case	"ten.png"	:return aCardJustBetterThanThePlayer( suite,3);
			case	"nine.png"	:return aCardJustBetterThanThePlayer( suite,4);
			case	"eight.png"	:return aCardJustBetterThanThePlayer( suite,5);
			case	"seven.png"	:return aCardJustBetterThanThePlayer( suite,6);
		}
		return -1;
	}
	public int worseCardOfTheSuiteSent(ArrayList<String> suite)
	{
		return aCardJustBetterThanThePlayer(suite,7);
	}
	public int aCardJustBetterThanThePlayer(ArrayList<String> suite,int i)
	{
		int j=i;
		int t=-1;
		String card[]={"ace.png","king.png","queen.png","jack.png","ten.png","nine.png","eight.png","seven.png"};
		
		
		for(j=i;j>=0;--j)
		{
			t=searchForACard(suite, card[j]);
			if(!(t==-1))
			{
				return t;
			}
		}
		for(j=i;j<card.length;++j)
		{
			t=searchForACard(suite, card[j]);
			if(!(t==-1))
				return t;
		}
		return -1;
	}
	public String betterCardBetweenTwo(String f,String s)
	{
		String[] check={"ace.png","king.png","queen.png","jack.png","ten.png","nine.png","eight.png","seven.png"};
		if(f.charAt(0)==s.charAt(0))
		{
			for(int i=0;i<check.length;++i)
			{
				if(f.substring(1).equalsIgnoreCase(check[i]))
				{
					return f;
				}
				else if(s.substring(1).equalsIgnoreCase(check[i]))
				{
					return s;
				}
			}
		}
		else
		{
			if(s.charAt(0)==wildCard)
			{
				return s;
			}
			else
			{
				return f;
			}
			/*
			 * return the card if one is a wild card if not then return the first card
			 */
		}
		return "";
	}
	public int getTheCardInAllSuite(Player c,String card)
	{
		for(int i=0;i<c.getAp().size();++i)
		{
			String cardGot=c.getAp().get(i);
			if(cardGot.equalsIgnoreCase(card))
			{
				return i;
			}
		}
		return -1;
	}
	public int getTheBiggestCard(ArrayList <String>inSuite)
	{
		int i=searchForACard(inSuite,"ace.png");
		if(!(i==-1))
		{
			return i;
		}
		i=searchForACard(inSuite,"king.png");
		if(!(i==-1))
		{
			return i;
		}
		i=searchForACard(inSuite,"queen.png");
		if(!(i==-1))
		{
			return i;
		}
		i=searchForACard(inSuite,"jack.png");
		if(!(i==-1))
		{
			return i;
		}
		i=searchForACard(inSuite,"ten.png");
		if(!(i==-1))
		{
			return i;
		}
		i=searchForACard(inSuite,"nine.png");
		if(!(i==-1))
		{
			return i;
		}
		i=searchForACard(inSuite,"eight.png");
		if(!(i==-1))
		{
			return i;
		}
		i=searchForACard(inSuite,"seven.png");
		if(!(i==-1))
		{
			return i;
		}
		return -1;
	}
	public int searchForACard(ArrayList <String> inSuite,String card)
	{
		int i=0;
		for(i=0;i<inSuite.size();++i)
		{
			String cardGot=inSuite.get(i).substring(1);
			if(cardGot.equalsIgnoreCase(card))
			{
				return i;
			}
		}
		return -1;
	}
	public void reset()
	{
		spade.clear();
		diamond.clear();
		clubs.clear();
		heart.clear();
		wildCard=' ';
	}
}
