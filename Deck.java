package threeCardGame;

import java.util.ArrayList;
import java.util.Random;
/*
 * use to create a deck of 30 card and shuffule it
 * everytime it is called can use a reset method for
 * recreating a card
 */
public class Deck {
		private ArrayList<String> cards;
		public Deck(boolean hasDeckToBeMade)
		{
			cards=new ArrayList<String>();
			if(hasDeckToBeMade)
			{
				addCards();
				shuffle();
			}
			
		}
		public String getCard()
		{
			String firstCard="";
			if(!(cards.size()==0))
			{
				firstCard=cards.get(0);
				cards.remove(0);
			}
			return firstCard;
		}
		private void addCards()
		{
			for(int i=1;i<=7;++i)
			{
				cards.add("S"+address(i));
			}
			for(int i=0;i<=7;++i)
			{
				cards.add("C"+address(i));
			}
			for(int i=0;i<=7;++i)
			{
				cards.add("D"+address(i));
			}
			for(int i=1;i<=7;++i)
			{
				cards.add("H"+address(i));
			}
		}
		private String address(int i)
		{
			switch(i)
			{
				case 0:return "seven.png";
				case 1:return "eight.png";
				case 2:return "nine.png";
				case 3:return "ten.png";
				case 4:return "jack.png";
				case 5:return "queen.png";
				case 6:return "king.png";
				case 7:return "ace.png";
			}
			return "";
		}
		
		private void shuffle()
		{
			Random rnd=new Random();
			for(int i=0;i<10000000;++i)
			{
				int t1=rnd.nextInt(29);
				int t2=rnd.nextInt(29);
				String m=cards.get(t1);
				cards.remove(t1);
				String m2=cards.get(t2);
				cards.remove(t2);
				cards.add(t1, m2);
				cards.add(t2, m);
				}
			
		}
		public void display()
		{
			int i=1;
			for(String s: cards)
			{
				System.out.println(i+"  "+s);
				++i;
			}
		}
		public void reset() {
			// TODO Auto-generated method stub
			cards.clear();
			addCards();
			shuffle();
		}
}
