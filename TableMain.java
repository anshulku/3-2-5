package threeCardGame;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TableMain {
	
	
	private Player c1;
	private Player c2;
	private Player p;
	private Deck deck;
	private ArrayList<Image> player=new ArrayList<Image>();
	private ArrayList<Image> computer2=new ArrayList<Image>();
	private ArrayList<Image> computer1=new ArrayList<Image>();
	private String path;
	private OnTableCard otc;
	private Toss start;
	private ChoiceWildCard wild;
	private String whoWonLastTime;
	private String backCard;
	/* <for testing stuff
	private Image ti;
	private String tp;
	private String tc;
	private ArrayList<String> testingRoute;
	/ for testing stuff>
	 */
	public TableMain(boolean hasDeckToBeMade)
	{
		c1=new Player(10,30,95,30);
		c2=new Player(710,30,620,30);
		p=new Player(150,460,250,350);
		p.setX2(670);
		p.setY2(555);
		deck=new Deck(hasDeckToBeMade);
		path="/src/threeCardGame/pic/deck/";
		backCard="/src/threeCardGame/pic/backOfACard.png";
		otc=new OnTableCard();
		start=new Toss();   
		wild=new ChoiceWildCard();
		/* <for testing stuff
		tp=path;
		tc="Cfive.png";
		testingRoute=new ArrayList<String>();
		deck=new Deck();
		for testing stuff>
		*/
	}
	/*
	// <\for testing stuff
	public void test() throws SlickException
	{
		player.add(new Image(tp+tc));
	}
	public void displayTestingRoute()
	{
		for(String cardImagePath : testingRoute)
		{
			System.out.println(cardImagePath);
		}
	}
	// /for testing stuff>
	*/
	public ChoiceWildCard getwild()
	{
		return wild;
	}
	public Toss getToss()
	{
		return start;
	}
	public OnTableCard getOTC()
	{
		return otc;
	}
	public void setOnTableCard(String p,String c1,String c2) throws SlickException
	{
		otc.setc1(c1);
		otc.setc2(c2);
		otc.setP(p);
	}
	
	public Deck deck()
	{
		return deck;
	}
	public Player p()
	{
		return p;
	}
	public Player c1()
	{
		return c1;
	}
	public Player c2()
	{
		return c2;
	}
	public ArrayList<Image> player()
	{
		return player;
	}
	public ArrayList<Image> computer1()
	{
		return computer1;
	}
	public ArrayList<Image> computer2()
	{
		return computer2;
	}
	
	public void setWildCardInEveryPlace(String c)
	{
		p.setWildCardInEveryPlace(c);
		c1.setWildCardInEveryPlace(c);
		c2.setWildCardInEveryPlace(c);
	}
	public void refressTheImagesOfAllPlayer() throws SlickException
	{
		player.clear();
		computer1.clear();
		computer2.clear();
		for(int i=0;i<p.size();++i)
		{
			String p2=p.get(i);
			player.add(new Image(path+p2));
		}
		for(int i=0;i<c1.size();++i)
		{
			String p2=c1.get(i);
			computer1.add(new Image(backCard));
		}
		for(int i=0;i<c2.size();++i)
		{
			String p2=c2.get(i);
			computer2.add(new Image(backCard));
		}
	}
	public void addFive(String playerMakingFive)
	{
		whoWonLastTime=playerMakingFive;
		try {
		for(int i=0;i<5;++i)
		{
			String t;
			if(playerMakingFive.equalsIgnoreCase("player"))
			{
				t=deck.getCard();
				p.add(t);
				player.add(new Image(path+t));
				
				
				//<for testing stuff
				//testingRoute.add(path+t);
				//for testing stuff>
				t=deck.getCard();
				c1.add(t);
				computer1.add(new Image(backCard));
				
				
				//<for testing stuff
				//testingRoute.add(path+t);
				//for testing stuff>
				t=deck.getCard();
				c2.add(t);
				computer2.add(new Image(backCard));
				
				
				//<for testing stuff
				//testingRoute.add(path+t);
				//for testing stuff>
			}
			else if(playerMakingFive.equalsIgnoreCase("computer1"))
			{
				t=deck.getCard();
				c1.add(t);
				computer1.add(new Image(backCard));
				
				
				//<for testing stuff
				//testingRoute.add(path+t);
				//for testing stuff>
				t=deck.getCard();
				p.add(t);
				player.add(new Image(path+t));
				
				
				//<for testing stuff
				//testingRoute.add(path+t);
				//for testing stuff>
				t=deck.getCard();
				c2.add(t);
				computer2.add(new Image(backCard));
				//<for testing stuff
				//testingRoute.add(path+t);
				//for testing stuff>
			}
			else
			{
				t=deck.getCard();
				c2.add(t);
				computer2.add(new Image(backCard));
				//<for testing stuff
				//testingRoute.add(path+t);
				//for testing stuff>
				t=deck.getCard();
				c1.add(t);
				computer1.add(new Image(backCard));
				//<for testing stuff
				//testingRoute.add(path+t);
				//for testing stuff>
				t=deck.getCard();
				p.add(t);
				player.add(new Image(path+t));
				//<for testing stuff
				//testingRoute.add(path+t);
				//for testing stuff>
			}
		}
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addRestOfTheCard(String playerMakingFive)
	{
		try {
			for(int i=0;i<5;++i)
			{
				String t;
				if(playerMakingFive.equalsIgnoreCase("player"))
				{
					t=deck.getCard();
					p.add(t);
					player.add(new Image(path+t));
					t=deck.getCard();
					c1.add(t);
					player.add(new Image(path+t));
					t=deck.getCard();
					c2.add(t);
					player.add(new Image(path+t));
				}
				else if(playerMakingFive.equalsIgnoreCase("computer1"))
				{
					t=deck.getCard();
					c1.add(t);
					player.add(new Image(path+t));
					t=deck.getCard();
					p.add(t);
					player.add(new Image(path+t));
					t=deck.getCard();
					c2.add(t);
					player.add(new Image(path+t));
				}
				else
				{
					t=deck.getCard();
					c2.add(t);
					player.add(new Image(path+t));
					t=deck.getCard();
					c1.add(t);
					player.add(new Image(path+t));
					t=deck.getCard();
					p.add(t);
					player.add(new Image(path+t));
				}
			}
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public void setWhoWonLastTime(String t)
	{
		whoWonLastTime=t;
	}
	public String getWhoWonLastTime()
	{
		return whoWonLastTime;
	}
	public String betterCardBetweenTwo(String card1,String card2)
	{
		String[] check={"ace.png","king.png","queen.png","jack.png","ten.png","nine.png","eight.png","seven.png"};
			for(int i=0;i<check.length;++i)
			{
				if(card1.substring(1).equalsIgnoreCase(check[i]))
				{
					return card1;
				}
				else if(card2.substring(1).equalsIgnoreCase(check[i]))
				{
					return card2;
				}
			}
			return "";
	}
	public String tellWhoWonFST(String card,String p,String comp1, String comp2)
	{
		if(card.equalsIgnoreCase(p))
			return "first";
		if(card.equalsIgnoreCase(comp1))
			return "secound";
		if(card.equalsIgnoreCase(comp2))
			return "third";	
		return "";
	}
	public String tellWhoHasWon(String p,String comp1, String comp2)
	{
		char wild=c1.getp().getWildCard();
		System.out.println(wild);
		if(p.charAt(0)==wild || (p.charAt(0)==comp1.charAt(0) && p.charAt(0)==comp2.charAt(0)))
		{
			System.out.println("310");
			if(p.charAt(0)==comp1.charAt(0) && p.charAt(0)==comp2.charAt(0))
			{
				System.out.println("313");
				String betterCard=betterCardBetweenTwo(p,comp1);
				betterCard=betterCardBetweenTwo(betterCard,comp2);
				System.out.println("316 :;  "+betterCard);
				return tellWhoWonFST(betterCard,p,comp1,comp2);
			}
			else if(p.charAt(0)==comp1.charAt(0))
			{
				System.out.println("321");
				String betterCard=betterCardBetweenTwo(p,comp1);
				return tellWhoWonFST(betterCard,p,comp1,comp2);
			}
			else if(p.charAt(0)==comp2.charAt(0))
			{
				System.out.println("327");
				String betterCard=betterCardBetweenTwo(p,comp2);
				return tellWhoWonFST(betterCard,p,comp1,comp2);
			}
			else
			{
				System.out.println("333");
				return "first";
			}
		}
		else
		{
			if(comp1.charAt(0)==wild && comp2.charAt(0)==wild)
			{
				System.out.println("341");
				String betterCard=betterCardBetweenTwo(comp1,comp2);
				return tellWhoWonFST(betterCard,p,comp1,comp2);
			}
			else if(comp1.charAt(0)==wild)
			{
				System.out.println("347");
				return "secound";
			}
			else if(comp2.charAt(0)==wild)
			{
				System.out.println("352");
				return "third";
			}
			else if(p.charAt(0)==comp1.charAt(0))
			{
				System.out.println("357");
				String betterCard=betterCardBetweenTwo(p,comp1);
				return tellWhoWonFST(betterCard,p,comp1,comp2);
			}
			else if(p.charAt(0)==comp2.charAt(0))
			{
				System.out.println("363");
				String betterCard=betterCardBetweenTwo(p,comp2);
				return tellWhoWonFST(betterCard,p,comp1,comp2);
			}
			else
			{
				System.out.println("369");
				return "first";
			}
		}
	}
	public String whoHasWon(String p,String comp1, String comp2)
	{
		if(whoWonLastTime.equalsIgnoreCase("player"))
		{
			switch(tellWhoHasWon(p,comp1,comp2))
			{
				case	"first":whoWonLastTime="player";
								this.p.incCardsWon();
								return whoWonLastTime;
				case	"secound":whoWonLastTime="computer1";
									c1.incCardsWon();
									return whoWonLastTime;
				case	"third":whoWonLastTime="computer2";
								c2.incCardsWon();
								return whoWonLastTime;
			}
		}
		else if(whoWonLastTime.equalsIgnoreCase("computer1"))
		{
			switch(tellWhoHasWon(comp1,comp2,p))
			{
				case	"first":whoWonLastTime="computer1";c1.incCardsWon();return whoWonLastTime;
				case	"secound":whoWonLastTime="computer2";c2.incCardsWon();return whoWonLastTime;
				case	"third":whoWonLastTime="player";this.p.incCardsWon();return whoWonLastTime;
			}
		}
		else if(whoWonLastTime.equalsIgnoreCase("computer2"))
		{
			switch(tellWhoHasWon(comp2,p,comp1))
			{
				case	"first":whoWonLastTime="computer2";c2.incCardsWon();return whoWonLastTime;
				case	"secound":whoWonLastTime="player";this.p.incCardsWon();return whoWonLastTime;
				case	"third":whoWonLastTime="computer1";c1.incCardsWon();return whoWonLastTime;
			}
		}
		return "";
	}

	public void drawC1Cards()
	{
		ArrayList<Image> i=new ArrayList<Image>();
		int tx=c1.getTakingCard().getX(),ty=c1.getTakingCard().getY();
		for(int k=0;k<c1.getTakingCard().getTemp().size();++k)
		{
			try {
				i.add(new Image(path+c1.getTakingCard().getTemp().get(k)));
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		for(int k=0;k<i.size();++k)
		{
			i.get(k).draw(tx, ty);
			ty+=50;
		}
	}
	public void drawC2Cards()
	{
		ArrayList<Image> i=new ArrayList<Image>();
		int tx=c2.getTakingCard().getX(),ty=c2.getTakingCard().getY();
		for(int k=0;k<c2.getTakingCard().getTemp().size();++k)
		{
			
			try {
				i.add(new Image(path+c2.getTakingCard().getTemp().get(k)));
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		for(int k=0;k<i.size();++k)
		{
			i.get(k).draw(tx, ty);
			ty+=50;
		}
	}
	public boolean hasPlayerWon()
	{
		if(p.getcardsWon()==15)
		{
			return true;
		}
		return false;
	}
	public String hasAnyOneWon()
	{
		String won="";
		if(p.getcardsWon()==15)
		{
			if(c1.getcardsWon()>c2.getcardsWon())
			{
				won="[1] Player\n[2] Computer 1\n[3] Computer 2";
			}
			else if(c1.getcardsWon()<c2.getcardsWon())
			{
				won="[1] Player\n[2] Computer 2\n[3] Computer 1";
			}
			else
			{
				won="[1] Player\n[2] Computer 1 ; Computer 2";
			}
		}
		else if(c1.getcardsWon()==15)
		{
			if(p.getcardsWon()>c2.getcardsWon())
			{
				won="[1] Computer 1\n[2] Player\n[3] Computer 2";
			}
			else if(p.getcardsWon()<c2.getcardsWon())
			{
				won="[1] Computer 1\n[2] Computer 2\n[3] Player";
			}
			else
			{
				won="[1] Computer 1\n[2] Player ; Computer 2";
			}
		}
		else if(c2.getcardsWon()==15)
		{
			if(c1.getcardsWon()>p.getcardsWon())
			{
				won="[1] Computer 2\n[2] Computer 1\n[3] Player";
			}
			else if(c1.getcardsWon()<p.getcardsWon())
			{
				won="[1] Computer 2\n[2] Player\n[3] Computer 1";
			}
			else
			{
				won="[1] Computer 2\n[2] Player ; Computer 1";
			}
		}
		if(p.getcardsWon()==c1.getcardsWon() && p.getcardsWon()==15)
		{
			won="[1] Computer 1 ; Player\n[2] Player";
		}
		else if(c2.getcardsWon()==c1.getcardsWon() && 15==c1.getcardsWon())
		{
			won="[1] Computer 1 ; Player\n[2] Player";
		}
		else if(p.getcardsWon()==c2.getcardsWon() && p.getcardsWon()==15)
		{
			won="[1] Computer 1 ; Player\n[2] Player";
		}
		else if(p.getcardsWon()==c1.getcardsWon() && p.getcardsWon()==c2.getcardsWon()
				&& p.getcardsWon()==15)
		{
			won="[1] Computer 1 ; Player \n Computer 2";
		}
		return won;
	}
	public void drawPCards()
	{
		ArrayList<Image> i=new ArrayList<Image>();
		int tx=p.getTakingCard().getX(),ty=p.getTakingCard().getY();
		for(int k=0;k<p.getTakingCard().getTemp().size();++k)
		{
			try {
				i.add(new Image(path+p.getTakingCard().getTemp().get(k)));
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		for(int k=0;k<i.size();++k)
		{
			i.get(k).draw(tx, ty);
			tx+=50;
		}
	}
	public void setWildCardInTC()
	{
		p.getTakingCard().setWild(p.getp().getWildCard());
		c1.getTakingCard().setWild(p.getp().getWildCard());
		c2.getTakingCard().setWild(p.getp().getWildCard());
	}
	public void reset()
	{
		c1.reset(10,30);
		c2.reset(710,30);
		p.reset(150,460);
		p.setX2(670);
		p.setY2(555);
		deck.reset();
		player.clear();
		computer2.clear();
		computer1.clear();
		otc.setAllToDefault();;   
	}
}
