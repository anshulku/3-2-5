package threeCardGame;

import java.util.ArrayList;

public class LogicOfComputer {
	private String wildCard;
	private int h;
	private int s;
	private int c;
	private int d;
	public LogicOfComputer()
	{
		wildCard="";
	}
	public void setCardNumber(Player c)
	{
		h=c.getp().lengthHeart();
		s=c.getp().lengthSpade();
		this.c=c.getp().lengthClubs();
		d=c.getp().lengthDiamond();
	}
	
	public String getWildCard()
	{
		return wildCard;
	}
	public void setWildCard(String p)
	{
		wildCard=p;
	}
	public String lowestCardYouGot()
	{
		if(h>0 && h<=2 && !(wildCard.equalsIgnoreCase("H")))
		{
			h--;
			return "heart";
		}
		else if(s>0 && s<=2 && !(wildCard.equalsIgnoreCase("S")))
		{
			s--;
			return "spade";
		}
		else if(c>0 && c<=2 && !(wildCard.equalsIgnoreCase("C")))
		{
			c--;
			return "club";
		}
		else if(d>0 && d<=2 && !(wildCard.equalsIgnoreCase("D")))
		{
			d--;
			return "diamond";
		}
		else
		{
			return "non";
		}
	}
	public String getCard(Player c,OnTableCard t,String ui)
	{
		int i=getCardComputerGenerated(c,t,ui);
		String card=c.getAp().get(i);
		c.remove(i);
		return card;
	}
	/*
	 * ui indicate the identity of a computer
	 */
	private int getCardComputerGenerated(Player c,OnTableCard t,String ui)
	{
		//for a computer
		int i;
		setCardNumber(c);
		if(ui.equalsIgnoreCase("computer1"))
		{
			/*
			 * computer1 has to throw the card
			 */
			if(t.getp().equalsIgnoreCase("") && t.getc2().equalsIgnoreCase(""))
			{
				
				switch(this.lowestCardYouGot())
				{
					case "heart"	:	i=c.getp().bestH(c,t);
										return i;
					case "spade"	:	i=c.getp().bestS(c,t);
										return i;
					case "diamond"	:	i=c.getp().bestD(c,t);
										return i;
					case "club"		:	i=c.getp().bestC(c,t);
										return i;
					case "non"		:	i=bestCardInFirstCard(c);

										return i;
				}
			}

			/*
			 * computer2 and player have already put the card and now computer1 has to play
			 */
			else if (!(t.getc2().equalsIgnoreCase("")))
			{
				String card=t.getc2();
				i=-1;
				switch(card.charAt(0))
				{
					case 'H'	:i= c.getp().bestH(c, t);
					break;
					case 'C'	:i= c.getp().bestC(c, t);
					break;
					case 'S'	:i= c.getp().bestS(c, t);
					break;
					case 'D'	:i= c.getp().bestD(c, t);
					break;
				}
				if(i==-1 && !(t.getp().charAt(0)==wildCard.charAt(0)))
					/*
					 * saying that if i do not have a card of the suite thrown
					 * by coumputer2 then i throw a card of wild card if 
					 * player has  not thrown a wild card
					 */
				{
					switch(wildCard.charAt(0))
					{
					case 'H'	:i = easyestWildCard(c.getp().getH(),c);
					if(!(i==-1))i=c.getp().getTheCardInAllSuite(c, c.getp().getHeart(i));
					break;
					case 'C'	:i = easyestWildCard(c.getp().getC(),c);
					if(!(i==-1))i=c.getp().getTheCardInAllSuite(c, c.getp().getClubs(i));
					break;
					case 'S'	:i = easyestWildCard(c.getp().getS(),c);
					if(!(i==-1))i=c.getp().getTheCardInAllSuite(c, c.getp().getSpade(i));
					break;
					case 'D'	:i = easyestWildCard(c.getp().getD(),c);
					if(!(i==-1))i=c.getp().getTheCardInAllSuite(c, c.getp().getDiamond(i));
					break;
					}
					
				}
				if(i==-1 && t.getp().charAt(0)==wildCard.charAt(0))
					/*
					 * saying that if i do not have a card of the suite thrown
					 * by coumputer2 then i throw a card of wild card if 
					 * player has  not thrown a wild card
					 */
				{
					switch(wildCard.charAt(0))
					{
						case 'H'	:i = aCardJustBetterThanSent(t.getp(),c.getp().getH());if(!(i==-1)){i=c.getp().getTheCardInAllSuite(c, c.getp().getH().get(i));}
						break;
						case 'C'	:i = aCardJustBetterThanSent(t.getp(),c.getp().getC());if(!(i==-1))i=c.getp().getTheCardInAllSuite(c, c.getp().getC().get(i));
						break;
						case 'S'	:i = aCardJustBetterThanSent(t.getp(),c.getp().getS());if(!(i==-1))i=c.getp().getTheCardInAllSuite(c, c.getp().getS().get(i));
						break;
						case 'D'	:i = aCardJustBetterThanSent(t.getp(),c.getp().getD());if(!(i==-1))i=c.getp().getTheCardInAllSuite(c, c.getp().getD().get(i));
						break;
					}
					
				}
				if(i==-1)
					/*
					 * saying that if i do not have a card of the suite thrown
					 * by coumputer2 then i throw a card of card if 
					 * player has thrown a wild card or if computer1 does not have a
					 * wild card
					 */
				{
					i=anyCardWhichIsWorst(c);
				}
				return i;
			}
			/*
			 * player have already put the card and now computer1 has to play
			 */
			else if (!(t.getp().equalsIgnoreCase("")))
			{
				String card=t.getp();
				i=-1;
				switch(card.charAt(0))
				{
				//not ready yet....
					case 'H':i= c.getp().bestH(c, t);
					break;
					case 'C'	:i= c.getp().bestC(c, t);
					break;
					case 'S'	:i= c.getp().bestS(c, t);
					break;
					case 'D'	:i= c.getp().bestD(c, t);
					break;
				}
				
				if(i==-1)
				{
					switch(wildCard.charAt(0))
					{
					case 'H'	:i = easyestWildCard(c.getp().getH(),c);
					if(!(i==-1))i=c.getp().getTheCardInAllSuite(c, c.getp().getHeart(i));
					break;
					case 'C'	:i = easyestWildCard(c.getp().getC(),c);
					if(!(i==-1))i=c.getp().getTheCardInAllSuite(c, c.getp().getClubs(i));
					break;
					case 'S'	:i = easyestWildCard(c.getp().getS(),c);
					if(!(i==-1))i=c.getp().getTheCardInAllSuite(c, c.getp().getSpade(i));
					break;
					case 'D'	:i = easyestWildCard(c.getp().getD(),c);
					if(!(i==-1))i=c.getp().getTheCardInAllSuite(c, c.getp().getDiamond(i));
					break;
					}
					if(i==-1)
					{
						i=anyCardWhichIsWorst(c);
					}
				}
				return i;
			
			}
		}
		else
		{
			//make exactly as above 
			if(t.getc1().equalsIgnoreCase("") && 
					t.getp().equalsIgnoreCase(""))
			{
				switch(this.lowestCardYouGot())
				{
					case "heart"	:	i=c.getp().bestH(c,t);
										return i;
					case "spade"	:	i=c.getp().bestS(c,t);
										return i;
					case "diamond"	:	i=c.getp().bestD(c,t);
										return i;
					case "club"		:	i=c.getp().bestC(c,t);
										return i;
					case "non"		:	i=bestCardInFirstCard(c);
										return i;
				}
			}
			else if(!(t.getp().equalsIgnoreCase("")))
			{
				String card=t.getp();
				i=-1;
				switch(card.charAt(0))
				{
					case 'H'	:i= c.getp().bestH(c, t);
					break;
					case 'C'	:i= c.getp().bestC(c, t);
					break;
					case 'S'	:i= c.getp().bestS(c, t);
					break;
					case 'D'	:i= c.getp().bestD(c, t);
					break;
				}
				if(i==-1 && !(t.getc1().charAt(0)==wildCard.charAt(0)))
					/*
					 * saying that if i do not have a card of the suite thrown
					 * by coumputer2 then i throw a card of wild card if 
					 * player has  not thrown a wild card
					 */
				{
					switch(wildCard.charAt(0))
					{
					case 'H'	:i = easyestWildCard(c.getp().getH(),c);
					if(!(i==-1))i=c.getp().getTheCardInAllSuite(c, c.getp().getHeart(i));
					break;
					case 'C'	:i = easyestWildCard(c.getp().getC(),c);
					if(!(i==-1))i=c.getp().getTheCardInAllSuite(c, c.getp().getClubs(i));
					break;
					case 'S'	:i = easyestWildCard(c.getp().getS(),c);
					if(!(i==-1))i=c.getp().getTheCardInAllSuite(c, c.getp().getSpade(i));
					break;
					case 'D'	:i = easyestWildCard(c.getp().getD(),c);
					if(!(i==-1))i=c.getp().getTheCardInAllSuite(c, c.getp().getDiamond(i));
					break;
					}
					
				}
				if(i==-1 && t.getc1().charAt(0)==wildCard.charAt(0))
					/*
					 * saying that if i do not have a card of the suite thrown
					 * by coumputer2 then i throw a card of wild card if 
					 * player has  not thrown a wild card
					 */
				{
					switch(wildCard.charAt(0))
					{
						case 'H'	:i = aCardJustBetterThanSent(t.getc1(),c.getp().getH());if(!(i==-1))i=c.getp().getTheCardInAllSuite(c, c.getp().getH().get(i));
						break;
						case 'C'	:i = aCardJustBetterThanSent(t.getc1(),c.getp().getC());if(!(i==-1))i=c.getp().getTheCardInAllSuite(c, c.getp().getC().get(i));
						break;
						case 'S'	:i = aCardJustBetterThanSent(t.getc1(),c.getp().getS());if(!(i==-1))i=c.getp().getTheCardInAllSuite(c, c.getp().getS().get(i));
						break;
						case 'D'	:i = aCardJustBetterThanSent(t.getc1(),c.getp().getD());if(!(i==-1))i=c.getp().getTheCardInAllSuite(c, c.getp().getD().get(i));
						break;
					}
					
				}
				if(i==-1)
					/*
					 * saying that if i do not have a card of the suite thrown
					 * by coumputer2 then i throw a card of card if 
					 * player has thrown a wild card or if computer1 does not have a
					 * wild card
					 */
				{
					i=anyCardWhichIsWorst(c);
				}
				return i;
			
			}
			else if(!(t.getc1().equalsIgnoreCase("")))
			{
				String card=t.getc1();
				i=-1;
				switch(card.charAt(0))
				{
				//not ready yet....
					case 'H'	:i= c.getp().bestH(c, t);
					break;
					case 'C'	:i= c.getp().bestC(c, t);
					break;
					case 'S'	:i= c.getp().bestS(c, t);
					break;
					case 'D'	:i= c.getp().bestD(c, t);
					break;
				}
				if(i==-1)
				{
					switch(wildCard.charAt(0))
					{
						case 'H'	:i = easyestWildCard(c.getp().getH(),c);
						if(!(i==-1))i=c.getp().getTheCardInAllSuite(c, c.getp().getHeart(i));
						break;
						case 'C'	:i = easyestWildCard(c.getp().getC(),c);
						if(!(i==-1))i=c.getp().getTheCardInAllSuite(c, c.getp().getClubs(i));
						break;
						case 'S'	:i = easyestWildCard(c.getp().getS(),c);
						if(!(i==-1))i=c.getp().getTheCardInAllSuite(c, c.getp().getSpade(i));
						break;
						case 'D'	:i = easyestWildCard(c.getp().getD(),c);
						if(!(i==-1))i=c.getp().getTheCardInAllSuite(c, c.getp().getDiamond(i));
						break;
					}
					if(i==-1)
					{
						i=anyCardWhichIsWorst(c);
					}
				}
				return i;
			}
		}
		return -1;
	}
	private int aCardJustBetterThanSent(String card,ArrayList<String> suite)
	{
		switch(card.substring(1))
		{
		case	"ace.png"	:return justBetterCardOtherwiseNot( suite,7);
		case	"king.png"	:return justBetterCardOtherwiseNot( suite,0);
		case	"queen.png"	:return justBetterCardOtherwiseNot( suite,1);
		case	"jack.png"	:return justBetterCardOtherwiseNot( suite,2);
		case	"ten.png"	:return justBetterCardOtherwiseNot( suite,3);
		case	"nine.png"	:return justBetterCardOtherwiseNot( suite,4);
		case	"eight.png"	:return justBetterCardOtherwiseNot( suite,5);
		case	"seven.png"	:return justBetterCardOtherwiseNot( suite,6);
		}
		return -1;
	}
	public int justBetterCardOtherwiseNot(ArrayList<String> suite,int i)
	{
		if(i==7)
			return -1;
		String card[]={"ace.png","king.png","queen.png","jack.png","ten.png","nine.png","eight.png","seven.png"};
		for(int j=i;j>=0;--j)
		{
			int t=searchForTheCard(suite,card[j]);
			if(!(t==-1))
				return t;
		}
		return -1;
	}
	private int searchForTheCard(ArrayList<String> suite,String card)
	{
		for(int i=0;i<suite.size();++i)
		{
			if(suite.get(i).substring(1).equalsIgnoreCase(card))
				return i;
		}
		return -1;
	}
	public int anyCardWhichIsWorst(Player c)
	{
		int i=-1;
		i=searchCard(c,"seven.png",false);
		if(!(i==-1))
			return i;
		i=searchCard(c,"eight.png",false);
		if(!(i==-1))
			return i;
		i=searchCard(c,"nine.png",false);
		if(!(i==-1))
			return i;
		i=searchCard(c,"ten.png",false);
		if(!(i==-1))
			return i;
		i=searchCard(c,"jack.png",false);
		if(!(i==-1))
			return i;
		i=searchCard(c,"queen.png",false);
		if(!(i==-1))
			return i;
		i=searchCard(c,"king.png",false);
		if(!(i==-1))
			return i;
		i=searchCard(c,"ace.png",false);
		if(!(i==-1))
			return i;
		i=searchCard(c,"seven.png",true);
		if(!(i==-1))
			return i;
		i=searchCard(c,"eight.png",true);
		if(!(i==-1))
			return i;
		i=searchCard(c,"nine.png",true);
		if(!(i==-1))
			return i;
		i=searchCard(c,"ten.png",true);
		if(!(i==-1))
			return i;
		i=searchCard(c,"jack.png",true);
		if(!(i==-1))
			return i;
		i=searchCard(c,"queen.png",true);
		if(!(i==-1))
			return i;
		i=searchCard(c,"king.png",true);
		if(!(i==-1))
			return i;
		i=searchCard(c,"ace.png",true);
		if(!(i==-1))
			return i;
		return -1;
	}
	public int searchCard(Player c,String card,boolean toUseWildCardOrnot)
	{
		for(int i=0;i<c.size();++i)
		{
			String gotCard=c.get(i).substring(1);
			if(gotCard.equalsIgnoreCase(card)
					&&!(c.get(i).charAt(0)==wildCard.charAt(0) 
					     && !(toUseWildCardOrnot))
					)
			{
				return i;
			}
		}
		return -1;
	}
	public int easyestWildCard(ArrayList<String> wildCard,Player c)
	{
		int i=c.getp().searchForACard(wildCard,"seven.png");
		if(!(i==-1))
		{
			return i;
		}
		i=c.getp().searchForACard(wildCard,"eight.png");
		if(!(i==-1))
		{
			return i;
		}
		i=c.getp().searchForACard(wildCard,"nine.png");
		if(!(i==-1))
		{
			return i;
		}
		i=c.getp().searchForACard(wildCard,"ten.png");
		if(!(i==-1))
		{
			return i;
		}
		i=c.getp().searchForACard(wildCard,"jack.png");
		if(!(i==-1))
		{
			return i;
		}
		i=c.getp().searchForACard(wildCard,"queen.png");
		if(!(i==-1))
		{
			return i;
		}
		i=c.getp().searchForACard(wildCard,"king.png");
		if(!(i==-1))
		{
			return i;
		}
		i=c.getp().searchForACard(wildCard,"ace.png");
		if(!(i==-1))
		{
			return i;
		}
		return -1;
	}
	//just guessing i wont be neededing it 
	private int bestCardInFirstCard(Player c)
	{
		/*
		 *if the computer is throwing the card then
		 *the computer should throw the  best card it can
		 *if not then the wild card... 
		 */
		for(int i=0;i<c.size();++i)
		{
			String s=c.get(i).charAt(0)+"";
			if(!(s.equalsIgnoreCase(wildCard)))
			{
				switch(c.get(i).substring(1))
				{
					case	"ace.png"	:	return i;
					case	"king.png"	:	return i;
					case	"queen.png"	:	return i;
					case	"jack.png"	:	return i;
				}
			}
		}
		
		/*
		 * throw wild card
		 */
		for(int i=0;i<c.size();++i)
		{
			String s=c.get(i).charAt(0)+"";
			if(s.equalsIgnoreCase(wildCard))
			{
				switch(c.get(i).substring(1))
				{
					case	"ace.png"	:	return i;
					case	"king.png"	:	return i;
					case	"queen.png"	:	return i;
					case	"jack.png"	:	return i;
				}
			}
		}
		for(int i=0;i<c.size();++i)
		{
			String s=c.get(i).charAt(0)+"";
			if(!(s.equalsIgnoreCase(wildCard)))
			{
				switch(c.get(i).substring(1))
				{
					case	"ten.png"	:	return i;
					case	"nine.png"	:	return i;
					case	"eight.png"	:	return i;
					case	"seven.png"	:	return i;
				}
			}
		}
		
		for(int i=0;i<c.size();++i)
		{
			String s=c.get(i).charAt(0)+"";
			if(s.equalsIgnoreCase(wildCard))
			{
				switch(c.get(i).substring(1))
				{
					case	"ten.png"	:	return i;
					case	"nine.png"	:	return i;
					case	"eight.png"	:	return i;
					case	"seven.png"	:	return i;
				}
			}
		}
		return -1;
	}
}
