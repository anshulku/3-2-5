package threeCardGame;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Toss {
	private Image card1;
	private Image card2;
	private Image card3;
	private float[] positionOfCards={300.0f,250.0f,
										380.0f,250.0f,
										460.0f,250.0f};
	protected String path="/src/threeCardGame/pic/";;
	public Toss()
	{
		try {
			card1=new Image(path+"backOfACard.png");
			card2=new Image(path+"backOfACard.png");
			card3=new Image(path+"backOfACard.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		giveNameToTheCard();
	}
	public void reset()
	{
		try {
			card1=new Image(path+"backOfACard.png");
			card2=new Image(path+"backOfACard.png");
			card3=new Image(path+"backOfACard.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		giveNameToTheCard();
	}
	private void drawCardName(String j,Graphics g,int x,int y)
	{
		g.setColor(Color.black);
		g.drawString(j, x, y);  //300,230 ;380,360 ;  460,230
		g.setColor(Color.white);
	}
	public String whichCard(int x)
	{
		switch(x)
		{
			case 2:return "two";
			case 3: return "three";
			case 5:return "five";
		}
		return "";
	}
	public void drawTheCardHolderSentToYou(String x,Graphics g,String holder)
	{
		if(x.equalsIgnoreCase(card1.getName()))
		{
			drawCardName(holder,g,300,230);
		}
		else if(x.equalsIgnoreCase(card2.getName()))
		{
			drawCardName(holder,g,380,360);
		}
		else
		{
			drawCardName(holder,g,460,230);
		}
	}
	public void drawNameOnTheCard(int p,int c1,int c2,Graphics g)
	{
		String ply=whichCard(p);
		String comp1=whichCard(c1);
		String comp2=whichCard(c2);
		this.drawTheCardHolderSentToYou(ply, g, "player");
		this.drawTheCardHolderSentToYou(comp1, g, "computer 1");
		this.drawTheCardHolderSentToYou(comp2, g, "computer 2");
		/*
		try {
			card1=new Image(path+"deck/S"+ply1+".png");
			card2=new Image(path+"deck/S"+comp11+".png");
			card3=new Image(path+"deck/S"+comp12+".png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	private void giveNameToTheCard()
	{
		Random rnd=new Random();
		int k=rnd.nextInt(60);
		String[] names={"two","three","five"};
		switch(k%3)
		{
			case	0:	k=rnd.nextInt(60);
						card1.setName(names[k%3]);
						for(int i=0;i<names.length;++i)
						{
							if(!(card1.getName().equalsIgnoreCase(names[i])))
							{
								card2.setName(names[i]);
								break;
							}
						}
						for(int i=0;i<names.length;++i)
						{
							if(!(card1.getName().equalsIgnoreCase(names[i]))
									&& !(card2.getName().equalsIgnoreCase(names[i])))
							{
								card3.setName(names[i]);
								break;
							}
						}
						break;
			case	1:	k=rnd.nextInt(60);
						card2.setName(names[k%3]);
						for(int i=0;i<names.length;++i)
						{
							if(!(card2.getName().equalsIgnoreCase(names[i])))
							{
								card1.setName(names[i]);
								break;
							}
						}
						for(int i=0;i<names.length;++i)
						{
							if(!(card1.getName().equalsIgnoreCase(names[i]))
									&& !(card2.getName().equalsIgnoreCase(names[i])))
							{
								card3.setName(names[i]);
								break;
							}
						}
						break;
			case	2:	k=rnd.nextInt(60);
						card3.setName(names[k%3]);
						for(int i=0;i<names.length;++i)
						{
							if(!(card3.getName().equalsIgnoreCase(names[i])))
							{
								card1.setName(names[i]);
								break;
							}
						}
						for(int i=0;i<names.length;++i)
						{
							if(!(card1.getName().equalsIgnoreCase(names[i]))
									&& !(card3.getName().equalsIgnoreCase(names[i])))
							{
								card2.setName(names[i]);
								break;
							}
						}
						break;
		}
	}
	public float[] getPosition()
	{
		return positionOfCards;
	}
	public Image getCard2()
	{
		return card2;
	}
	public Image getCard3()
	{
		return card3;
	}
	public Image getCard1()
	{
		return card1;
	}
	public void draw() {
		card1.draw(positionOfCards[0], positionOfCards[1]);
		card2.draw(positionOfCards[2], positionOfCards[3]);
		card3.draw(positionOfCards[4], positionOfCards[5]);
	}
	public boolean isInTheLocationOfCards(int x,int y)
	{
		int k;
		try {
			k = isItCard1(x,y);
		
		if(k==1)
			return true;
		k=isItCard2(x,y);
		if(k==2)
			return true;
		k=isItCard3(x,y);
		if(k==3)
			return true;
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	private int numberOfCard(Image k)
	{
		switch(k.getName())
		{
		case "two":return 2;
		case "three":return 3;
		case "five":return 5;
		}
		return -1;
	}
	public int isItCard1(int x,int y) throws SlickException
	{
		if(x>=300 && x<=371 && y>=250 && y<=346)
		{
			
			return 1;
		}
		return -1;
	}
	public int isItCard2(int x,int y) throws SlickException
	{
		if(x>=380 && x<=451 && y>=250 && y<=346)
		{
			return 2;
		}
		return -1;
	}
	public int isItCard3(int x,int y) throws SlickException
	{
		if(x>=460 && x<=531 && y>=250 && y<=346)
		{
			return 3;
		}
		return -1;
	}
	public int whichCard(int x, int y) {

		int k;
		try {
				k = isItCard1(x,y);
				if(k==1)
				{
					k=numberOfCard(card1);
					String temp=card1.getName();
					card1=new Image(path+"deck/S"+card1.getName()+".png");
					card1.setName(temp);
					return k;
				}
				k=isItCard2(x,y);
				if(k==2)
				{
					k=numberOfCard(card2);
					String temp=card2.getName();
					card2=new Image(path+"deck/S"+card2.getName()+".png");
					card2.setName(temp);
					return k;
				}
				k=isItCard3(x,y);
				if(k==3)
				{
					k=numberOfCard(card3);
					String temp=card3.getName();
					card3=new Image(path+"deck/S"+card3.getName()+".png");
					card3.setName(temp);
					return k;
				}
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	public int[] getRestOfTheName(int player)
	{
		int[] name={2,3,5};
		int[] make=new int[2];
		int k=0;
		for(int i=0;i<name.length;++i)
		{
			if(!(player==name[i]))
			{
				make[k]=name[i];
				++k;
			}
		}
		Random rnd =new Random();
		int p2=rnd.nextInt(90);int tmp=0;
		switch(p2%2)
		{
			case 0 :	p2=rnd.nextInt(90);
						tmp=make[p2%2];
						make[0]=make[(p2+1)%2];
						make[1]=tmp;
						System.out.println("2"+make[0]+";"+make[1]);
						break;
			case 1 :	p2=rnd.nextInt(90)%2;
						tmp=make[p2%2];
						make[1]=make[(p2+1)%2];
						make[0]=tmp;
						System.out.println("1"+make[0]+";"+make[1]);
						break;
		}
		try {
			String temp=card1.getName();
			card1=new Image(path+"deck/S"+temp+".png");
			card1.setName(temp);
			temp=card2.getName();
			card2=new Image(path+"deck/S"+temp+".png");
			card2.setName(temp);
			temp=card3.getName();
			card3=new Image(path+"deck/S"+temp+".png");
			card3.setName(temp);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return make;
	}
}
