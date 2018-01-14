package threeCardGame;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

public class Player {
	private CardsObject p;
	private DeckOfAPlayer ap;
	private LogicOfComputer computerAdvice;
	private int cardneedsToWin;
	private int cardsWon;
	int x,x2;
	int y,y2;
	private int currentPoint;
	private TakingCardsFromOthers takingCard;
	public boolean isLocationInPlayersCard(int x1,int y1)
	{
		if(x1>=x &&x1<=x2 && y1>=y && y1<=y2)
		{
			return true;
		}
		return false;
	}
	public void setTheCard(int x,int y,OnTableCard otc)
	{
		int tx=this.x,ty=this.y;
		int k=0;
		for(int i=0;i<ap.size();++i)
		{
			if(x>=tx && x<=tx+50)
			{
				k=i;
				break;
			}
			if(i==ap.size()-1 && x>=tx && x<=tx+70)
			{
				k=i;
				break;
			}
			tx=tx+50;
		}
		System.out.println("value of k"+k);
		otc.setP(ap.get(k));
		System.out.println("otc of k"+otc.getp());
		this.x=this.x+25;
		x2=x2-25;
		remove(k);
	}
	public boolean isTheMoveLegle(int x,int y , OnTableCard otc)
	{
		int tx=this.x,ty=this.y;
		int k=0;
		for(int i=0;i<ap.size();++i)
		{
			if(x>=tx && x<=tx+50)
			{
				k=i;
				break;
			}
			if(i==ap.size()-1 && x>=tx && x<=tx+70)
			{
				k=i;
				break;
			}
			tx=tx+50;
		}
		System.out.println("vlaue of k in move is legeal"+k);
		if(!(otc.getc1().equalsIgnoreCase("")))
		{
			if(otc.getc1().charAt(0)==ap.get(k).charAt(0))
			{
				return true;
			}
			else
			{
				int i=0;
				for(i=0;i<ap.size();++i)
				{
					if(ap.get(i).charAt(0)==otc.getc1().charAt(0))
						break;
				}
				if(i==ap.size())
				{
					return true;
				}
				return false;
			}
			
		}
		else if(!(otc.getc2().equalsIgnoreCase("")))
		{
			if(otc.getc2().charAt(0)==ap.get(k).charAt(0))
			{
				return true;
			}
			else
			{
				int i=0;
				for(i=0;i<ap.size();++i)
				{
					if(ap.get(i).charAt(0)==otc.getc2().charAt(0))
						break;
				}
				if(i==ap.size())
				{
					return true;
				}
				return false;
			}
		}
		else
		{
			return true;
		}
	}
	public Player(int fx,int fy,int tx,int ty)
	{
		p=new CardsObject();
		ap=new DeckOfAPlayer();
		computerAdvice=new LogicOfComputer();
		x=fx;
		y=fy;
		cardsWon=0;
		currentPoint=0;
		takingCard=new TakingCardsFromOthers(tx,ty);
	}
	public TakingCardsFromOthers getTakingCard()
	{
		return takingCard;
	}
	public void incCardsWon()
	{
		++cardsWon;
		currentPoint++;
	}
	public int getcardsWon()
	{
		return cardsWon;
	}
	public int getCurrentPoint()
	{
		return currentPoint;
	}
	public void setX1(int s)
	{
		x=s;
	}
	public void setX2(int s)
	{
		x2=s;
	}
	public void setY1(int s)
	{
		y=s;
	}
	public void setY2(int s)
	{
		y2=s;
	}
	public int getX1()
	{
		return x;
	}
	public int getX2()
	{
		return x2;
	}
	public int getY1()
	{
		return y;
	}
	public int getY2()
	{
		return y2;
	}
	public void setCardsNeedToWin(int i)
	{
		cardneedsToWin=i;
	}
	public int getCardneedsToWin()
	{
		return cardneedsToWin;
	}
	public int getCardsWonTillNow()
	{
		return currentPoint-cardneedsToWin;
	}
	public void setWildCardInEveryPlace(String c)
	{
		computerAdvice.setWildCard(c);
		p.setWildCard(c.charAt(0));
	}
	public void setComputerAdvice(LogicOfComputer ca)
	{
		computerAdvice=ca;
	}
	public LogicOfComputer getComputerAdvice()
	{
		return computerAdvice;
	}
	public DeckOfAPlayer getAp()
	{
		return ap;
	}
	public CardsObject getp()
	{
		return p;
	}
	public int size()
	{
		return ap.size();
	}
	public void add(String s)
	{
		char whichSuite=s.charAt(0);
		switch(whichSuite)
		{
			case 'H' :p.setHeart(s);
						break;
			case 'S' :p.setSpade(s);
						break;
			case 'C' :p.setClubs(s);
						break;
			case 'D' :p.setDiamond(s);
						break;
		
		}
		ap.add(s);
	}
	public String remove(int i) {
		String card= ap.get(i);
		ap.remove(i);
		char whichSuite=card.charAt(0);
		switch(whichSuite)
		{
			case 'H' :removeH(card);
						break;
			case 'S' :removeS(card);
						break;
			case 'C' :removeC(card);
						break;
			case 'D' :removeD(card);
						break;
		}
		return card;
	}
	private void removeS(String card)
	{
		for(int i=0;i<p.lengthSpade();++i)
		{
			if(p.getSpade(i).equalsIgnoreCase(card))
			{
				p.removeSpade(i);
				break;
			}
		}
	}
	private void removeC(String card)
	{
		for(int i=0;i<p.lengthClubs();++i)
		{
			if(p.getClubs(i).equalsIgnoreCase(card))
			{
				p.removeClubs(i);
				break;
			}
		}
	}
	private void removeH(String card)
	{
		for(int i=0;i<p.lengthHeart();++i)
		{
			if(p.getHeart(i).equalsIgnoreCase(card))
			{
				p.removeHeart(i);
				break;
			}
		}
	}
	private void removeD(String card)
	{
		for(int i=0;i<p.lengthDiamond();++i)
		{
			if(p.getDiamond(i).equalsIgnoreCase(card))
			{
				p.removeDiamond(i);
				break;
			}
		}
	}
	public String get(int i)
	{
		return ap.get(i);
	}
	public void saveGame(ArrayList<String> save)
	{
		p.reset();
		ap.reset();
		for(int i=0;i<save.size();++i)
		{
			this.add(save.get(i));
		}
	}
	public void setCurrentPoint(int point)
	{
		currentPoint=point;
	}
	public void reset(int x1,int y1)
	{
		p.reset();
		ap.reset();
		x=x1;
		y=y1;
		currentPoint=0;
	}
	public void setCardWon(int i) {
		cardsWon=i;
		
	}
}
