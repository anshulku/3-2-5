package threeCardGame;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TakingCardsFromOthers {
	private int p=-1;
	private int c1=-1;
	private int c2=-1;
	private ArrayList<String> temp;
	private int x;
	private int y;
	private char ws;
	String path="/src/threeCardGame/pic/deck/";
	
	public int getP() {
		return p;
	}
	public void setP(int p) {
		this.p = p;
	}
	public int getC1() {
		return c1;
	}
	public void setC1(int c1) {
		this.c1 = c1;
	}
	public int getC2() {
		return c2;
	}
	public void setC2(int c2) {
		this.c2 = c2;
	}
	public ArrayList<String> getTemp() {
		return temp;
	}
	public void setTemp(ArrayList<String> temp) {
		this.temp = temp;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public char getWs() {
		return ws;
	}
	public TakingCardsFromOthers(int x1,int y1)
	{
		x=x1;
		y=y1;
		temp=new ArrayList<String>();
	}
	public void setWild(char ch)
	{
		ws=ch;
	}
	public void setAll(TableMain t)
	{
		p=t.p().getCardsWonTillNow();
		c1=t.c1().getCardsWonTillNow();
		c2=t.c2().getCardsWonTillNow();
	}
	public void setTheWorseCard(Player from,Player to,String card)
	{
		int i=0;
		if(wasTheCardGood(card))
		{
			i=getThePosition(card);
			to.getTakingCard().temp.add(temp.get(i));
			temp.remove(i);
		}
		else
		{
			for(int k=0;k<from.size();++k)
			{
				if(wasTheCardGood(from.get(k)))
				{
					to.getTakingCard().temp.add(from.get(k));
					from.remove(k);
					return ;
				}
			}
			String []cards={"ace.png","king.png","jack.png","ten.png","nine.png","eight.png","seven.png"};
			for(int k=cards.length-1;k>=0;--k)
			{
				i=theCardIsGood(from,cards[k],false);
				if(!(i==-1))
				{
					to.getTakingCard().temp.add(from.get(i));
					from.remove(i);
					return;
				}
			}
			for(int k=cards.length-1;k>=0;--k)
			{
				i=theCardIsGood(from,cards[k],true);
				if(!(i==-1))
				{
					to.getTakingCard().temp.add(from.get(i));
					from.remove(i);
					return;
				}
			}
		}
	}
	private int theCardIsGood(Player from,String card,boolean useWild) 
	{
		for(int i=0;i<from.size();++i)
		{
			if(from.get(i).substring(1).equalsIgnoreCase(card) )
			{
				if(useWild && from.get(i).charAt(0)==ws)
				{
					return i;
				}
				else
				{
					return i;
				}
			}
		}
		return -1;
	}
	public int getThePosition(String card)
	{
		for(int i=0;i<temp.size();++i)
		{
			if(temp.get(i).equalsIgnoreCase(card))
			{
				return i;
			}
		}
		return -1;
	}
	public boolean wasTheCardGood(String card)
	{
		if(!(card.charAt(0)==ws))
		{
			switch(card.substring(1))
			{
				case "seven.png":return true;
				case "eight.png":return true;
				case "nine.png":return true;
			}
		}
		return false;
	}
	public void setForComputer1(TableMain t)
	{
		Random rnd=new Random();
		if(c1<=0)
		{
			return;
		}
		try {
		if(p<0)
		{
			int pos=rnd.nextInt(t.p().size()-1);
			String tmp=t.p().get(pos);
			t.p().remove(pos);
			t.c1().getTakingCard().temp.add(tmp);
			--c1;
			setTheWorseCard(t.c1(),t.p(),tmp);
			++p;
			t.p().getTakingCard().p++;
			t.p().getTakingCard().c1--;
			t.c2().getTakingCard().p++;
			t.c2().getTakingCard().c1--;
			
			t.refressTheImagesOfAllPlayer();
		}
		if(c2<0)
		{
			int pos=rnd.nextInt(t.c2().size()-1);
			String tmp=t.c2().get(pos);
			t.c2().remove(pos);
			t.c1().getTakingCard().temp.add(tmp);
			--c1;
			setTheWorseCard(t.c1(),t.c2(),tmp);
			++c2;
			t.c2().getTakingCard().c2++;
			t.c2().getTakingCard().c1--;
			t.p().getTakingCard().c2++;
			t.p().getTakingCard().c1--;
			
			t.refressTheImagesOfAllPlayer();
		}
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	public void setForComputer2(TableMain t)
	{
		Random rnd=new Random();
		if(c2<=0)
		{
			return;
		}

		try {
		if(p<0)
		{
			int pos=rnd.nextInt(t.p().size()-1);
			String tmp=t.p().get(pos);
			t.p().remove(pos);
			t.c2().getTakingCard().temp.add(tmp);
			--c2;
			setTheWorseCard(t.c2(),t.p(),tmp);
			++p;
			t.refressTheImagesOfAllPlayer();

			t.p().getTakingCard().p++;
			t.p().getTakingCard().c2--;
			t.c1().getTakingCard().p++;
			t.c1().getTakingCard().c2--;
		}
		if(c1<0)
		{
			int pos=rnd.nextInt(t.c1().size()-1);
			String tmp=t.c1().get(pos);
			t.c1().remove(pos);
			t.c2().getTakingCard().temp.add(tmp);
			--c2;
			setTheWorseCard(t.c2(),t.c1(),tmp);
			++c1;
			t.refressTheImagesOfAllPlayer();

			t.p().getTakingCard().c1++;
			t.p().getTakingCard().c2--;
			t.c1().getTakingCard().c1++;
			t.c1().getTakingCard().c2--;
		}

		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	public boolean isItLegal(int x1,int y1)
	{
		if(p==0)
		{
			return false;
		}
		if(c1<0)
		{
			if(x1>=10 && x1<=80  && y1>=30  && y1<=575)
			{
				return true;
			}
		}
		if(c2<0)
		{
			if(x1>=710 && x1<=780  && y1>=30  && y1<=575)
			{
				return true;
			}
		}
		if(x1>=150 && y1>=460 && x1<=670 && y1<=555)
		{
			return true;
		}
		if(x1>=250 && y1>=350  && y1<=445)
		{
			int x2=250;
			for(int i=0;i<temp.size();++i)
			{
				x2=x2+50;
				if(i==temp.size()-1)
				{
					x2=x2+20;
				}
			}
			System.out.println("tk : x2 :"+x2);
			if(x1<=x2)
			{
				return true;
			}
		}
		return false;
	}
	public int getCard(int x1,int y1,TableMain t)
	{

		if(p==0)
		{
			return -1;
		}
		if(c1<0)
		{
			if(x1>=10 && x1<=80  && y1>=30  && y1<=575)
			{
				int y2=30;
				int i=-1;
				for(i=0;i<t.c1().size();++i)
				{
					if(y1>=y2 && y1<=y2+49)
					{
						break;
					}
					if(y1>=y2 && y1<=y2+95 && i==t.c1().size()-1)
					{
						break;
					}
					y2+=50;
				}
				if(!(i==t.c1().size()))
				{
					String tmp=t.c1().get(i);
					t.c1().remove(i);
					t.p().getTakingCard().temp.add(tmp);
					return 1;
				}
			}
		}
		if(c2<0)
		{
			if(x1>=710 && x1<=780  && y1>=30  && y1<=575)
			{
				int y2=30;
				int i=-1;
				for(i=0;i<t.c2().size();++i)
				{
					if(y1>=y2 && y1<=y2+49)
					{
						break;
					}
					if(y1>=y2 && y1<=y2+95 && i==t.c1().size()-1)
					{
						break;
					}
					y2+=50;
				}
				if(!(i==t.c2().size()))
				{
					String tmp=t.c2().get(i);
					t.c2().remove(i);
					t.p().getTakingCard().temp.add(tmp);
					return 2;
				}
			}
		}
		return -1;
	
	}
	public boolean giveCard(TableMain t,int w,int x1,int y1)
	{
		if(x1>=t.p().getX1() && y1>=t.p().getY1() && x1<=t.p().getX2() && y1<=t.p().getY2())
		{
			int x2=150;
			int i=0;
			for(i=0;i<t.p().size();++i)
			{
				if(x1>=x2 && x1<=x2+49)
				{
					break;
				}
				if(x1>=x2 && x1<=x2+70 && i==t.c1().size()-1)
				{
					break;
				}
				x2+=50;
			}
			if(!(i==t.p().size()))
			{
				String tmp=t.p().get(i);
				t.p().remove(i);
				switch(w)
				{
					case 1	:t.c1().getTakingCard().temp.add(tmp);
								--p;
								++c1;
								t.c1().getTakingCard().c1++;
								t.c1().getTakingCard().p--;
								t.c2().getTakingCard().c1++;
								t.c2().getTakingCard().p--;
							return true;
					case 2	:t.c2().getTakingCard().temp.add(tmp);
								--p;
								++c2;
								t.c2().getTakingCard().c2++;
								t.c2().getTakingCard().p--;
								t.c1().getTakingCard().c2++;
								t.c1().getTakingCard().p--;
					
							return true;
				}
			}
		}
		else if(x1>=250 && y1>=350  && y1<=445) 
		{
			
			int x2=250;
			int i=0;
			for(i=0;i<t.p().getTakingCard().getTemp().size();++i)
			{
				if(x1>=x2 && x1<=x2+49)
				{
					break;
				}
				if(x1>=x2 && x1<=x2+70 && i==t.p().getTakingCard().getTemp().size()-1)
				{
					break;
				}
				x2+=50;
			}
			if(!(i==t.p().getTakingCard().getTemp().size()))
			{
				String tmp=t.p().getTakingCard().getTemp().get(i);
				t.p().getTakingCard().getTemp().remove(i);
				switch(w)
				{
					case 1	:t.c1().getTakingCard().temp.add(tmp);
							--p;
							++c1;
							t.c2().getTakingCard().c1++;
							t.c2().getTakingCard().p--;
							t.c1().getTakingCard().c1++;
							t.c1().getTakingCard().p--;
							return true;
					case 2	:t.c2().getTakingCard().temp.add(tmp);
							--p;
							++c2;
							t.c2().getTakingCard().c2++;
							t.c2().getTakingCard().p--;
							t.c1().getTakingCard().c2++;
							t.c1().getTakingCard().p--;
							return true;
				}
			}
		
		}
		return false;
	}
	public void copy(Player p)
	{
		for(int i=0;i<temp.size();++i)
		{
			p.add(temp.get(i));
		}
		reset();	
	}
	public void reset()
	{
		temp.clear();
	}
}
