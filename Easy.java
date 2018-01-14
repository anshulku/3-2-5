package threeCardGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Easy extends BasicGameState 
implements ActionListener,MouseListener
{
	
	TableMain t;
	boolean timeForSetupToRun=false;
	Image menu;
	 boolean isAnyGameSaved=false;
	 /*
	  * variable for history
	  */
	 int mw;
	 int mp;
	 float wp;
	 String won;
	 boolean matchHasWon;
	 boolean matchHasWonByPlayer;
	 Image history;
	 Image wonStat;
	 /*
	  * background variable
	  */
	 String background;
	 boolean backgroundHasBeenClicked=false;
	 private Image backgroundImage;
	 Image item3;
	 boolean hasBackgroundSaved;
	 /*
	  * sound variables
	  */
	 Music move;
	 Music shuffle;
	 Music wrong;
	 Music wildMove;
	 /*
	  * these variable are for taking cards from othrs   <new variables>
	  */
	 boolean hasOneGameOver=false;   
	 boolean youHaveNotTakenACard=true;
	 boolean computer1HasCompelete=false;
	 boolean computer2HasCompelete=false;
	 boolean playerHasCompelete=false;
	 boolean hasAnyOneCompeleted=false;
	 boolean nowEveryOneCanTake=false;
	 int save;
	/*
	 * variables for options in menu bar
	 */
	boolean haveYouClickOnMenuBar=false;
	boolean	fileHasBeenClick=false;
	boolean	helpHasBeenClick=false;
	boolean instrutionHasBeenClick=false;
	boolean aboutHasBeenClick=false;
	boolean stasHasBeenClick;
	boolean historyHasBeenClicked;
	Image file;
	Image file2;
	Image help;
	Image help2;
	Image item;
	Image item2;
	Image about;
	Image instrution;
	Image stas;
	Image stas2;
	/*
	 * variable for card drawn to the table
	 */
	boolean doesPlayerHaveToPlay=false;
	boolean doesPlayerHaveToComp1=false;
	boolean doesPlayerHaveToComp2=false;
	boolean gameIsOn=false;
	boolean hasPlayerPutTheCard=false;
	boolean hasComputer1PutTheCard=false;
	boolean hasComputer2PutTheCard=false;
	boolean haveToClickToContinue=false;
	/*
	 * use for showing a wild card
	 */
	Image wild;
	boolean clickToMoveOn;
	String path="/src/threeCardGame/pic/deck/";
	//boolean ;
	/*
	 * following are the variable for the
	 * construting five cards only.
	 */
	boolean drawJustFiveCard=false;
	/*
	 * following are the toss 
	 * variable which help us make a toss 
	 * and then 
	 */
	boolean canYouStillChoose=true;
	String playerMakingFive;
	boolean clickToMove=false;
	boolean haveToChoseWildCard=false;
	private void playUsingTheRules() throws SlickException 
	{
		if(doesPlayerHaveToPlay && hasPlayerPutTheCard)
		{
			if(!(hasComputer1PutTheCard)  )
			{
				String c1=t.c1().getComputerAdvice().getCard(t.c1(), t.getOTC(), "computer1");
				
				t.getOTC().setc1(c1);
				hasComputer1PutTheCard=true;
				t.refressTheImagesOfAllPlayer();
			}
			if(!(hasComputer2PutTheCard))
			{
				String c2=t.c2().getComputerAdvice().getCard(t.c2(), t.getOTC(), "computer2");
				
				t.getOTC().setc2(c2);
				
				t.refressTheImagesOfAllPlayer();
				
				hasComputer2PutTheCard=true;
			}
			
		}
		else if(doesPlayerHaveToComp2)
		{/*
			comp1;
			if(hasPlayerPutTheCard)
			{
				comp2;
			}*/
			if(!(hasComputer2PutTheCard) )
			{
				String c2=t.c2().getComputerAdvice().getCard(t.c2(), t.getOTC(), "computer2");
				
				t.getOTC().setc2(c2);
				t.refressTheImagesOfAllPlayer();
				hasComputer2PutTheCard=true;
				
			}
			if(hasPlayerPutTheCard && !(hasComputer1PutTheCard))
			{
				String c1=t.c1().getComputerAdvice().getCard(t.c1(), t.getOTC(), "computer1");
				
				t.getOTC().setc1(c1);
				t.refressTheImagesOfAllPlayer();
				hasComputer1PutTheCard=true;
			}
		}
		else if(doesPlayerHaveToComp1)
		{/*
			comp1;
			comp2;*/
			if(!(hasComputer1PutTheCard))
			{
				String c1=t.c1().getComputerAdvice().getCard(t.c1(), t.getOTC(), "computer1");
				t.getOTC().setc1(c1);
				t.refressTheImagesOfAllPlayer();
				hasComputer1PutTheCard=true;
			}
			if(!(hasComputer2PutTheCard))
			{
				String c2=t.c2().getComputerAdvice().getCard(t.c2(), t.getOTC(), "computer2");
				t.getOTC().setc2(c2);
				t.refressTheImagesOfAllPlayer();
				hasComputer2PutTheCard=true;
			}
		}
		
	}
	public void drawTheCardsOnTheTable()
	{
			try {
				t.getOTC().draw();
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public boolean isItAWinCondition()
	{
		if(hasPlayerPutTheCard && hasComputer1PutTheCard && hasComputer2PutTheCard)
		{
			return true;
		}
		return false;
	}
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
			menu = new Image("/src/threeCardGame/pic/menuBar.png");
			shuffle=new Music("/src/threeCardGame/sound/Shuffle_Cards.wav");
			shuffle.loop();
			t=new TableMain(!(hasAnyGameBeenSaved()));
			shuffle.stop();
			file=new Image("/src/threeCardGame/pic/File.png");
			file2=new Image("/src/threeCardGame/pic/File2.png");
			help=new Image("/src/threeCardGame/pic/Help.png");
			stas=new Image("/src/threeCardGame/pic/Help.png");
			help2=new Image("/src/threeCardGame/pic/Help2.png");
			stas2=new Image("/src/threeCardGame/pic/Help2.png");
			item=new Image("/src/threeCardGame/pic/item.png");
			item2=new Image("/src/threeCardGame/pic/item2.png");
			item3=new Image("/src/threeCardGame/pic/item3.png");
			about=new Image("/src/threeCardGame/pic/About.png");
			backgroundImage=new Image("/src/threeCardGame/pic/Background.png");
			instrution=new Image("/src/threeCardGame/pic/Instrution.png");
			move=new Music("/src/threeCardGame/sound/Move.wav");
			wrong=new Music("/src/threeCardGame/sound/Wrong_Move.wav");
			wildMove=new Music("/src/threeCardGame/sound/Wild_Card.wav");
			background="";
			history=new Image("/src/threeCardGame/pic/History.png");
			wonStat=new Image("/src/threeCardGame/pic/wonStat.png");
			if(isBackSaved())
			{
				try {
					getBackground(new Scanner(new File("C:/Users/anshul/workspace/uni/War/src/threeCardGame/save/back.txt")));
				} catch (FileNotFoundException e) {
				e.printStackTrace();
				}
				hasBackgroundSaved=true;
			}
			if(background.equalsIgnoreCase(""))
			{
				background="/src/threeCardGame/pic/background/green.png";
			}
		if(hasAnyGameBeenSaved())
		{
			shuffle.loop();
			readSavedGame();
			isAnyGameSaved=false;
			this.setSaved();
			shuffle.stop();
		}
		else
		{
			setSaved();
			readHistory();
			writeHistory();
		}
		
		 /*
		  * for testing purpose
		  * 
		 t.addFive("player");
		 t.addFive("player");
		 t.setWildCardInEveryPlace("S");
		 
		 String test=t.c1().getComputerAdvice().getCard(t.c1(), t.getOTC(), "computer1");
		 
		 System.out.println(test);
		 t.setOnTableCard("",test, "");
		 t.refressTheImagesOfAllPlayer();
		 */

		/* Testing stuff
		 * <
		 * t.addFive("player");
		 * t.addFive("player");
		 * t.displayTestingRoute();
		 * use for testing the table of main
		 * t.test();>
		 */
		
	}
	public void makePlayerCard(int x,int y)
	{
		int x1=0;
		for(int i=0;i<t.player().size();++i)
		{
			t.player().get(i).draw(x+x1, y);
			x1=x1+50;
		}
	}
	public void makeComputer1Card(int x,int y)
	{
		int y1=0;
		for(int i=0;i<t.computer1().size();++i)
		{
			t.computer1().get(i).draw(x, y+y1);
			y1=y1+50;
		}
	}
	public void makeComputer2Card(int x,int y)
	{
		int y1=0;
		for(int i=0;i<t.computer2().size();++i)
		{
			t.computer2().get(i).draw(x, y+y1);
			y1=y1+50;
		}
	}
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		g.drawImage(new Image(background),0,0);
		//g.drawString("Mouse location :"+Mouse.getX()+" ; "+(599-Mouse.getY()), 10, 300);
		
		if(canYouStillChoose || clickToMove)
		{
			t.getToss().draw();
			if(clickToMove)
			{
				g.setColor(Color.black);
				g.drawString("Click on the screen to continue!", 300, 140);
				g.setColor(Color.white);
				t.getToss().drawNameOnTheCard(t.p().getCardneedsToWin(),
						t.c1().getCardneedsToWin(), t.c2().getCardneedsToWin(), g);
			}
		}
		else if(!(clickToMove) && haveToChoseWildCard)
		{
			makePlayerCard(300,460);
			makeComputer1Card(10,144);
			makeComputer2Card(710,144);
		}
		else if(clickToMoveOn)
		{
			g.setColor(Color.black);
			g.drawString("Click on the screen to continue!", 300, 140);
			g.setColor(Color.white);
			makePlayerCard(300,460);
			makeComputer1Card(10,144);
			makeComputer2Card(710,144);
			wild.draw(350,250 );
		}
		else
		{
			drawTheCardsOnTheTable();
			if(hasOneGameOver && nowEveryOneCanTake)
			{
				if(computer1HasCompelete && computer2HasCompelete && playerHasCompelete && nowEveryOneCanTake)
				{
					g.setColor(Color.black);
					g.drawString("click to continue ", 310, 200);
					g.setColor(Color.white);
				}
				else
				{
					g.setColor(Color.black);
					g.drawString("you have to take !! ", 310, 200);
					g.setColor(Color.white);
				}
				t.c1().getTakingCard().setForComputer1(t);
				t.c2().getTakingCard().setForComputer2(t);
				t.drawC1Cards();
				t.drawC2Cards();
				t.drawPCards();
			}
			else
			{
				gameIsOn=true;
			}
			makePlayerCard(t.p().getX1(),t.p().getY1());
			makeComputer1Card(t.c1().getX1(),t.c1().getY1());
			makeComputer2Card(t.c2().getX1(),t.c2().getY1());
			g.drawString("Points - "+t.c1().getcardsWon(), 10, 10);
			g.drawString("Points - "+t.c2().getcardsWon(), 700, 10);
			g.drawString("Points - "+t.p().getcardsWon(), 390, 558);

			g.drawString("Need Points - "+t.c1().getCardsWonTillNow(), 10, 580);
			g.drawString("Need Points - "+t.c2().getCardsWonTillNow(), 656, 580);
			g.drawString("Need Points - "+t.p().getCardsWonTillNow(), 346, 577);
			
			g.drawImage(new Image("/src/threeCardGame/pic/"+t.p().getp().getWildCard()+".png"),380 ,245 );
		}
		if(haveToClickToContinue)
		{
			g.setColor(Color.black);
			g.drawString("Cilck on the screen to continue", 280, 230);
			g.setColor(Color.white);
		}
		if(historyHasBeenClicked)
		{
			history.draw(335,185);
			g.drawString("Match Played : "+mp+"\n" +
					"Match Won : "+mw+"\nWin Percentage : "+wp, 340, 190);
		}
		
		if(matchHasWon)
		{
			if(matchHasWonByPlayer)
			{
				readHistory();
				history.draw(335,185);
				g.drawString("Match Played : "+mp+"\n" +
						"Match Won : "+mw+"\nWin Percentage : "+wp, 340, 190);
			}
			else
			{
				wonStat.draw(335,185);
				g.drawString(won, 340, 190);
			}
			return;
		}
		if(instrutionHasBeenClick)
		{
			instrution.draw(80,172);
			g.drawString("                 Instrution Dreissig Karte Spiel GAME\n\n" +
						"Chose a card which can help you win the game.\n" +
						"In the middle of screen you are shown the wildsuite of the game going.\n" +
						"If you don't have a card of a suite thrown then throw any card," +
						"\nIf you throw a wild card then you win if no one has thrown wild card.\n" +
						"there are point which tells you have much card you have won," +
						"\nand there is need points which tell you how much point you need" +
						"\nto win so that no one take your card in next turn and now if you" +
						"\nmake more than need points then you take other players card" +
						"\n    ---IN ORDER TO WIN YOU NEED TO MAKE FIRST 15 POINTS", 85, 177);
		}
		if(aboutHasBeenClick)
		{
			about.draw(150,172);
			g.drawString("            About Dreissig Karte Spiel GAME\n\n" +
						"Dreissig Karte Spiel \n\n" +
						"Version History: 1.M.J.O; 1.M.J.S.O; 1.M.J.S.1 \n\n" +
						"Version: 1.M.J.S.3\n\n" +
						"Build id: M28031653-JOOO\n\n" +
						"(c) Copyright Dreissig Karte Spiel contributors \nand others 2015.All rights reserved.\n\n" +
						"This product includes software developed by Anshul Kumar",155, 177);
		}
		if(backgroundHasBeenClicked)
		{
			backgroundImage.draw(355,220);
			if(Mouse.getX()>=355 && Mouse.getX()<=465 && (599-Mouse.getY())>=220 && (599-Mouse.getY())<=237)
			{
				item3.draw(355, 220);
			}
			if(Mouse.getX()>=355 && Mouse.getX()<=465 && (599-Mouse.getY())>=238 && (599-Mouse.getY())<=257)
			{
				item3.draw(355, 238);
			}
			if(Mouse.getX()>=355 && Mouse.getX()<=465 && (599-Mouse.getY())>=258 && (599-Mouse.getY())<=277)
			{
				item3.draw(355, 258);	
			}
			if(Mouse.getX()>=355 && Mouse.getX()<=465 && (599-Mouse.getY())>=278 && (599-Mouse.getY())<=297)
			{
				item3.draw(355, 278);
			}
			if(Mouse.getX()>=355 && Mouse.getX()<=465 && (599-Mouse.getY())>=298 && (599-Mouse.getY())<=317)
			{
				item3.draw(355, 298);
			}
			if(Mouse.getX()>=355 && Mouse.getX()<=465 && (599-Mouse.getY())>=318 && (599-Mouse.getY())<=334)
			{
				item3.draw(355, 318);
			}
			g.drawString("Swansea\n" +
					"Green\n" +
					"Space\n" +
					"Stone\n" +
					"Silver\n" +
					"Wooden", 360, 220);
			
		}
		
		if(backgroundHasBeenClicked)
		{
			return;
		}
		if(instrutionHasBeenClick)
		{
			return;
		}
		if(aboutHasBeenClick)
		{
			return;
		}

		if((599-Mouse.getY())<=20  || haveYouClickOnMenuBar)
		{
			
			menu.draw(0,0);
			if(Mouse.getX()>=0 && Mouse.getX()<=40 && (599-Mouse.getY())>=0 && (599-Mouse.getY())<=20)
			{
				file.draw(0,0);
			}
			if(Mouse.getX()>=41 && Mouse.getX()<=84 && (599-Mouse.getY())>=0 && (599-Mouse.getY())<=20)
			{
				help.draw(40,0);
			}
			if(Mouse.getX()>=85 && Mouse.getX()<=130 && (599-Mouse.getY())>=0 && (599-Mouse.getY())<=20)
			{
				stas.draw(84,0);
			}
			if(haveYouClickOnMenuBar)
			{
				if(fileHasBeenClick)
				{
					file.draw(0, 0);
					file2.draw(0, 20);
					if(Mouse.getX()>=0 && Mouse.getX()<=110 && (599-Mouse.getY())>=21 && (599-Mouse.getY())<=37)
					{
						item.draw(0,20);
					}
					if(Mouse.getX()>=0 && Mouse.getX()<=110 && (599-Mouse.getY())>=38 && (599-Mouse.getY())<=56)
					{
						if((((hasPlayerPutTheCard && hasComputer1PutTheCard && hasComputer2PutTheCard)
						||
						!(hasPlayerPutTheCard || hasComputer1PutTheCard || hasComputer2PutTheCard))
						&& gameIsOn))
						{
							item.draw(0,38);
						}
						else
						{
							item2.draw(0,38);
						}
					}
					if(Mouse.getX()>=0 && Mouse.getX()<=110 && (599-Mouse.getY())>=57 && (599-Mouse.getY())<=74)
					{
						item.draw(0, 56);
					}
					if(Mouse.getX()>=0 && Mouse.getX()<=110 && (599-Mouse.getY())>=75 && (599-Mouse.getY())<=92)
					{
						item.draw(0, 74);
					}
					g.drawString("New Game", 0, 20);
					if((((hasPlayerPutTheCard && hasComputer1PutTheCard && hasComputer2PutTheCard)
						||
						!(hasPlayerPutTheCard || hasComputer1PutTheCard || hasComputer2PutTheCard))
						&& gameIsOn))
					{
						g.drawString("Save", 0, 38);
					}
					else
					{
						g.setColor(Color.lightGray);
						g.drawString("Save", 0, 38);
						g.setColor(Color.white);
					}
					g.drawString("Background", 0, 56);
					g.drawString("Exit", 0, 74);
				}
				if(helpHasBeenClick)
				{
					help.draw(40,0);
					help2.draw(40,20);
					if(Mouse.getX()>=40 && Mouse.getX()<=150 && (599-Mouse.getY())>=21 && (599-Mouse.getY())<=37)
					{
						item.draw(40,20);
					}
					if(Mouse.getX()>=40 && Mouse.getX()<=150 && (599-Mouse.getY())>=38 && (599-Mouse.getY())<=54)
					{
						item.draw(40,38);
					}
					g.drawString("Instruction", 40, 20);
					g.drawString("About", 40, 37);
				}
				if(stasHasBeenClick)
				{
					stas.draw(84,0);
					stas2.draw(84,20);
					if(Mouse.getX()>=84 && Mouse.getX()<=194 && (599-Mouse.getY())>=21 && (599-Mouse.getY())<=37)
					{
						item.draw(84,20);
					}
					if(Mouse.getX()>=84 && Mouse.getX()<=194 && (599-Mouse.getY())>=38 && (599-Mouse.getY())<=54)
					{
						item.draw(84,38);
					}
					g.drawString("History",88 , 20);
					g.drawString("Reset",88 , 38);
				}
				if(Mouse.getX()>=0 && Mouse.getX()<=40 && (599-Mouse.getY())>=0 && (599-Mouse.getY())<=20
						&&!(fileHasBeenClick))
				{
					file.draw(0, 0);
					file2.draw(0,20);
					g.drawString("New Game", 0, 20);
					if((((hasPlayerPutTheCard && hasComputer1PutTheCard && hasComputer2PutTheCard)
							||
							!(hasPlayerPutTheCard || hasComputer1PutTheCard || hasComputer2PutTheCard))
							&& gameIsOn))
					{
						g.drawString("Save", 0, 38);
					}
					else
					{
						g.setColor(Color.lightGray);
						g.drawString("Save", 0, 38);
						g.setColor(Color.white);
					}
					g.drawString("Background", 0, 52);
					g.drawString("Exit", 0, 69);
					fileHasBeenClick=true;
					helpHasBeenClick=false;
					stasHasBeenClick=false;
				}
				if(Mouse.getX()>=41 && Mouse.getX()<=84 && (599-Mouse.getY())>=0 && (599-Mouse.getY())<=20
						&& !(helpHasBeenClick))
				{
					help.draw(40,0);
					help2.draw(40,20);
					g.drawString("Instruction", 40, 20);
					g.drawString("About", 0, 37);
					fileHasBeenClick=false;
					helpHasBeenClick=true;
					stasHasBeenClick=false;
				}
				if(Mouse.getX()>=85 && Mouse.getX()<=130 && (599-Mouse.getY())>=0 && (599-Mouse.getY())<=20
						&& !(stasHasBeenClick))
				{
					stas.draw(84,0);
					stas2.draw(84,20);
					fileHasBeenClick=false;
					helpHasBeenClick=false;
					stasHasBeenClick=true;
				}
			}
			g.drawString("Game", 0, 0);
			g.drawString("Help", 44, 0);
			g.drawString("Stas", 88,0 );
		}
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		// TODO Auto-generated method stub
		int x=Mouse.getX();
		int y=599-Mouse.getY();
		if(gameIsOn)
		{
			playUsingTheRules();
		}
		if(hasPlayerPutTheCard && hasComputer1PutTheCard && hasComputer2PutTheCard)
		{
			String whoWon=t.whoHasWon(t.getOTC().getp(), t.getOTC().getc1(), t.getOTC().getc2());
			hasPlayerPutTheCard=false;
			hasComputer1PutTheCard=false;
			hasComputer2PutTheCard=false;
			haveToClickToContinue=true;
			doesPlayerHaveToPlay=false;
			doesPlayerHaveToComp1=false;
			doesPlayerHaveToComp2=false;
		}
		if(nowEveryOneCanTake && t.c1().getTakingCard().getC1()==0)
		{
			computer1HasCompelete=true;
			hasAnyOneCompeleted=true;
		}
		if(nowEveryOneCanTake && t.c2().getTakingCard().getC2()==0)
		{
			computer2HasCompelete=true;
			hasAnyOneCompeleted=true;
		}
		if(nowEveryOneCanTake && t.p().getTakingCard().getP()==0)
		{
			playerHasCompelete=true;
			hasAnyOneCompeleted=true;
		}
	}
	public void reset()
	{
		shuffle.play();
		t.reset();
		t.getToss().reset();
		canYouStillChoose=true;
		clickToMove=false;
		haveToChoseWildCard=false;
		doesPlayerHaveToPlay=false;
		doesPlayerHaveToComp1=false;
		doesPlayerHaveToComp2=false;
		gameIsOn=false;
		hasPlayerPutTheCard=false;
		hasComputer1PutTheCard=false;
		hasComputer2PutTheCard=false;
		haveToClickToContinue=false;  // when some one won
		clickToMoveOn=false;			// after a five card have been shown and the wild card
		clickToMove=false;
		isAnyGameSaved=false;
		haveYouClickOnMenuBar=false;
		fileHasBeenClick=false;
		helpHasBeenClick=false;
		instrutionHasBeenClick=false;
		aboutHasBeenClick=false;

		hasOneGameOver=false;   
		youHaveNotTakenACard=true;
		computer1HasCompelete=false;
		computer2HasCompelete=false;
		playerHasCompelete=false;
		hasAnyOneCompeleted=false;
		nowEveryOneCanTake=false;
	}
	public void setSaved()
	{
		PrintWriter saved=null;
		try {
			saved = new PrintWriter("C:/Users/anshul/workspace/uni/War/src/threeCardGame/save/Saved.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		saved.println("isAnyGameSaved "+isAnyGameSaved);
		saved.println("hasBackgroundSaved "+hasBackgroundSaved);
		saved.close();
	}
	public boolean isBackSaved()
	{
		Scanner in;
		try {
			in = new Scanner(new File("C:/Users/anshul/workspace/uni/War/src/threeCardGame/save/Saved.txt"));
		
		in.next();in.next();
		if(in.hasNext())
		{
			in.next();
			return in.nextBoolean();
		}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	public void saveFile()
	{
		PrintWriter inPlayer=null;
		PrintWriter inComputer1=null;
		PrintWriter inComputer2=null;
		PrintWriter onTable=null;
		PrintWriter external=null;
		try {
			inPlayer =new PrintWriter("C:/Users/anshul/workspace/uni/War/src/threeCardGame/save/Player.txt");
			inComputer1 =new PrintWriter("C:/Users/anshul/workspace/uni/War/src/threeCardGame/save/Computer1.txt");
			inComputer2 =new PrintWriter("C:/Users/anshul/workspace/uni/War/src/threeCardGame/save/Computer2.txt");
			onTable =new PrintWriter("C:/Users/anshul/workspace/uni/War/src/threeCardGame/save/Table.txt");
			external =new PrintWriter("C:/Users/anshul/workspace/uni/War/src/threeCardGame/save/External.txt");
			
			for(int i=0;i<t.p().size();++i)
			{
				inPlayer.println(t.p().get(i));
			}
			for(int i=0;i<t.c1().size();++i)
			{
				inComputer1.println(t.c1().get(i));
			}
			for(int i=0;i<t.c2().size();++i)
			{
				inComputer2.println(t.c2().get(i));
			}
			inPlayer.println("/");
			inComputer1.println("/");
			inComputer2.println("/");
			inPlayer.println("Points "+t.p().getcardsWon());
			inPlayer.println("Needs Points "+t.p().getCardneedsToWin());
			inPlayer.println("current Points "+t.p().getCurrentPoint());
			inPlayer.println("X1 "+t.p().getX1());
			inPlayer.println("Y1 "+t.p().getY1());
			inPlayer.println("X2 "+t.p().getX2());
			inPlayer.println("Y2 "+t.p().getY2());
			
			inComputer1.println("Points "+t.c1().getcardsWon());
			inComputer1.println("Needs Points "+t.c1().getCardneedsToWin());
			inComputer1.println("current Points "+t.c1().getCurrentPoint());
			inComputer1.println("X1 "+t.c1().getX1());
			inComputer1.println("Y1 "+t.c1().getY1());
			
			inComputer2.println("Points "+t.c2().getcardsWon());
			inComputer2.println("Needs Points "+t.c2().getCardneedsToWin());
			inComputer2.println("current Points "+t.c2().getCurrentPoint());
			inComputer2.println("X1 "+t.c2().getX1());
			inComputer2.println("Y1 "+t.c2().getY1());
			
			onTable.println(t.getOTC().getp());
			onTable.println(t.getOTC().getc1());
			onTable.println(t.getOTC().getc2());
			onTable.println("/");
			onTable.println("Wild Card "+t.p().getp().getWildCard());
			
			setSaved();
			
			addExternal(external);
			external.close();
			onTable.close();
			inPlayer.close();
			inComputer1.close();
			inComputer2.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public boolean hasAnyGameBeenSaved()
	{
		try {
			Scanner saved =new Scanner(new File("C:/Users/anshul/workspace/uni/War/src/threeCardGame/save/Saved.txt"));
			if(saved.hasNext())
			{
				saved.next();
			}
			else
			{
				return false;
			}
			return saved.nextBoolean();
		} catch (FileNotFoundException e) {
		}
		return false;
	}
	public void addExternal(PrintWriter external)
	{
		
		/*
		 * variables for options in menu bar
		 */
		external.println("haveYouClickOnMenuBar " + haveYouClickOnMenuBar);
		external.println("fileHasBeenClick "+fileHasBeenClick);
		external.println("helpHasBeenClick "+helpHasBeenClick);
		external.println("instrutionHasBeenClick "+instrutionHasBeenClick);
		external.println("aboutHasBeenClick "+aboutHasBeenClick);
		external.println("doesPlayerHaveToPlay "+doesPlayerHaveToPlay);
		external.println("doesPlayerHaveToComp1 "+doesPlayerHaveToComp1);
		external.println("doesPlayerHaveToComp2 "+doesPlayerHaveToComp2);
		external.println("gameIsOn "+gameIsOn);
		external.println("hasPlayerPutTheCard "+hasPlayerPutTheCard);
		external.println("hasComputer1PutTheCard "+hasComputer1PutTheCard);
		external.println("hasComputer2PutTheCard "+hasComputer2PutTheCard);
		external.println("haveToClickToContinue "+haveToClickToContinue);
		external.println("clickToMoveOn "+clickToMoveOn);
		external.println("drawJustFiveCard "+drawJustFiveCard);
		external.println("canYouStillChoose "+canYouStillChoose);
		external.println("clickToMove "+clickToMove);
		external.println("haveToChoseWildCard "+haveToChoseWildCard);
		external.println("whoWonLast "+t.getWhoWonLastTime());
	}
	public void extratePlayerInformation(Scanner in,Player p,String who)
	{
		ArrayList <String> cards=new ArrayList<String>();
		String g=in.nextLine();
		while(!(g.equalsIgnoreCase("/")))
		{
			cards.add(g);
			g=in.nextLine();
		}
		p.saveGame(cards);
		in.next();
		p.setCardWon(in.nextInt());
		in.next();
		in.next();
		p.setCardsNeedToWin(in.nextInt());
		in.next();
		in.next();
		p.setCurrentPoint(in.nextInt());
		if(in.next().equalsIgnoreCase("X1"))
			p.setX1(in.nextInt());
		if(in.next().equalsIgnoreCase("Y1"))
			p.setY1(in.nextInt());
		if(who.equalsIgnoreCase("player"))
		{
		if(in.next().equalsIgnoreCase("X2"))
			p.setX2(in.nextInt());
		if(in.next().equalsIgnoreCase("Y2"))
			p.setY2(in.nextInt());
		}
		for(int i=0;i<t.p().size();++i)
		{
			System.out.println(t.p().get(i));
		}
		System.out.println("\n\n");
	}
	public void saveTable(Scanner in)
	{
		String tmp=in.next();
		if(!(tmp.equalsIgnoreCase("/")))
		{
			t.getOTC().setP(tmp);
			t.getOTC().setc1(in.next());
			t.getOTC().setc2(in.next());
			in.next();
		}
		in.next();in.next();
		t.setWildCardInEveryPlace(in.next());
		
	}
	public void externalInfo(Scanner in)
	{
		in.next();
		haveYouClickOnMenuBar =in.nextBoolean();
		in.next();fileHasBeenClick =in.nextBoolean();
		in.next();helpHasBeenClick =in.nextBoolean();
		in.next();instrutionHasBeenClick =in.nextBoolean();
		in.next();aboutHasBeenClick =in.nextBoolean();
		in.next();doesPlayerHaveToPlay =in.nextBoolean();
		in.next();doesPlayerHaveToComp1 =in.nextBoolean();
		in.next();doesPlayerHaveToComp2 =in.nextBoolean();
		in.next();gameIsOn =in.nextBoolean();
		in.next();hasPlayerPutTheCard =in.nextBoolean();
		in.next();hasComputer1PutTheCard =in.nextBoolean();
		in.next();hasComputer2PutTheCard =in.nextBoolean();
		in.next();haveToClickToContinue =in.nextBoolean();
		in.next();clickToMoveOn =in.nextBoolean();
		in.next();drawJustFiveCard =in.nextBoolean();
		in.next();canYouStillChoose =in.nextBoolean();
		in.next();clickToMove =in.nextBoolean();
		in.next();haveToChoseWildCard =in.nextBoolean();	
		in.next();t.setWhoWonLastTime(in.next());
	}
	public void readSavedGame()
	{
		try {
			File player=new File("C:/Users/anshul/workspace/uni/War/src/threeCardGame/save/Player.txt");
			extratePlayerInformation(new Scanner(player),t.p(),"player");
			player=new File("C:/Users/anshul/workspace/uni/War/src/threeCardGame/save/Computer1.txt");
			extratePlayerInformation(new Scanner(player),t.c1(),"computer1");
			player=new File("C:/Users/anshul/workspace/uni/War/src/threeCardGame/save/Computer2.txt");
			extratePlayerInformation(new Scanner(player),t.c2(),"computer2");
			player=new File("C:/Users/anshul/workspace/uni/War/src/threeCardGame/save/Table.txt");
			saveTable(new Scanner(player));
			player=new File("C:/Users/anshul/workspace/uni/War/src/threeCardGame/save/External.txt");
			externalInfo(new Scanner(player));
			t.refressTheImagesOfAllPlayer();
			}
			catch (FileNotFoundException e) 
			{
		e.printStackTrace();
			} catch (SlickException e) {
				e.printStackTrace();
			}
	}
	public void setBackground()
	{
		PrintWriter back=null;
		try {
			back=new PrintWriter("C:/Users/anshul/workspace/uni/War/src/threeCardGame/save/back.txt");
			back.println(background);
			hasBackgroundSaved=true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		back.close();
	}
	public void getBackground(Scanner in)
	{
		if(in.hasNext())
			background=in.next();
		else
			background="";
		
	}
	public void readHistory()
	{
		try 
		{
			Scanner in=new Scanner(new File("C:/Users/anshul/workspace/uni/War/src/threeCardGame/save/history.txt"));
			in.next();in.next();
			mp=in.nextInt()+1;
			in.next();in.next();
			mw=in.nextInt();
			wp=((mw*100)/mp);
			System.out.println(mw + ";;  "+mp+"  ;; "+wp);
			in.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			PrintWriter history=null;
			try {
				history=new PrintWriter("C:/Users/anshul/workspace/uni/War/src/threeCardGame/save/history.txt");
				history.println("Match Played "+0);
				history.println("Match Won "+0);
				history.println("Win Percentage"+0);
			} catch (FileNotFoundException t) {
				t.printStackTrace();
			}
			history.close();
		}
	}
	public void writeHistory()
	{
		PrintWriter history=null;
		try {
			history=new PrintWriter("C:/Users/anshul/workspace/uni/War/src/threeCardGame/save/history.txt");
			history.println("Match Played "+mp);
			history.println("Match Won "+mw);
			history.println("Win Percentage "+wp);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		history.close();
	}
	public void mousePressed(int button, int x, int y) {
		if(Mouse.isButtonDown(0))
		{
			if(historyHasBeenClicked)
			{
				historyHasBeenClicked=false;
				return;
			}
			if(matchHasWon)
			{
				matchHasWon=false;
				matchHasWonByPlayer=false;
				reset();
				writeHistory();
				t.p().setCardWon(0);
				t.c1().setCardWon(0);
				t.c2().setCardWon(0);
				return ;
			}
			if(instrutionHasBeenClick)
			{
				instrutionHasBeenClick=false;
				return;
			}
			if(aboutHasBeenClick)
			{
				aboutHasBeenClick=false;
				return;
			}
			if(backgroundHasBeenClicked) 
			{
				if(Mouse.getX()>=355 && Mouse.getX()<=465 && (599-Mouse.getY())>=220 && (599-Mouse.getY())<=237)
				{
					background="/src/threeCardGame/pic/background/swansea.JPG";
					backgroundHasBeenClicked=false;
					hasBackgroundSaved=true;
					setBackground();
					setSaved();
				}
				else if(Mouse.getX()>=355 && Mouse.getX()<=465 && (599-Mouse.getY())>=238 && (599-Mouse.getY())<=257)
				{
					background="/src/threeCardGame/pic/background/green.png";
					backgroundHasBeenClicked=false;
					hasBackgroundSaved=true;
					setBackground();
					setSaved();
				}
				else if(Mouse.getX()>=355 && Mouse.getX()<=465 && (599-Mouse.getY())>=258 && (599-Mouse.getY())<=277)
				{
					background="/src/threeCardGame/pic/background/space.png";
					backgroundHasBeenClicked=false;
					hasBackgroundSaved=true;
					setBackground();
					setSaved();
				}
				else if(Mouse.getX()>=355 && Mouse.getX()<=465 && (599-Mouse.getY())>=278 && (599-Mouse.getY())<=297)
				{
					background="/src/threeCardGame/pic/background/stone.JPG";
					backgroundHasBeenClicked=false;
					hasBackgroundSaved=true;
					setBackground();
					setSaved();
				}
				else if(Mouse.getX()>=355 && Mouse.getX()<=465 && (599-Mouse.getY())>=298 && (599-Mouse.getY())<=317)
				{
					background="/src/threeCardGame/pic/background/silver.JPG";
					backgroundHasBeenClicked=false;
					hasBackgroundSaved=true;
					setBackground();
					setSaved();
				}
				else if(Mouse.getX()>=355 && Mouse.getX()<=465 && (599-Mouse.getY())>=318 && (599-Mouse.getY())<=334)
				{
					background="/src/threeCardGame/pic/background/wooden.JPG";
					backgroundHasBeenClicked=false;
					hasBackgroundSaved=true;
					setBackground();
					setSaved();
				}
				else
				{
					wrong.play();
				}
				return;
			}
			if(Mouse.getX()>=0 && Mouse.getX()<=110 && (599-Mouse.getY())>=21 && (599-Mouse.getY())<=37
					&& fileHasBeenClick)
			{
				isAnyGameSaved=false;
				this.setSaved();
				haveYouClickOnMenuBar=false;
				fileHasBeenClick=false;
				helpHasBeenClick=false;
				t.p().setCardWon(0);
				t.c1().setCardWon(0);
				t.c2().setCardWon(0);
				this.reset();
				return ;
			}
			if(Mouse.getX()>=0 && Mouse.getX()<=110 && (599-Mouse.getY())>=38 && (599-Mouse.getY())<=56
					&& fileHasBeenClick)
			{
				if(((hasPlayerPutTheCard && hasComputer1PutTheCard && hasComputer2PutTheCard)
						||
						!(hasPlayerPutTheCard || hasComputer1PutTheCard || hasComputer2PutTheCard))
						&& gameIsOn)
				{
					haveYouClickOnMenuBar=false;
					fileHasBeenClick=false;
					helpHasBeenClick=false;
					isAnyGameSaved=true;
					saveFile();
				}
				return;
			}
			if(Mouse.getX()>=0 && Mouse.getX()<=110 && (599-Mouse.getY())>=57 && (599-Mouse.getY())<=74
					&& fileHasBeenClick)
			{
				haveYouClickOnMenuBar=false;
				fileHasBeenClick=false;
				helpHasBeenClick=false;
				backgroundHasBeenClicked=true;
				return ;
			}
			if(Mouse.getX()>=0 && Mouse.getX()<=110 && (599-Mouse.getY())>=75 && (599-Mouse.getY())<=92
					&& fileHasBeenClick)
			{
				haveYouClickOnMenuBar=false;
				fileHasBeenClick=false;
				helpHasBeenClick=false;
				System.exit(0);
			}
			if(Mouse.getX()>=84 && Mouse.getX()<=194 && (599-Mouse.getY())>=21 && (599-Mouse.getY())<=37
					&& stasHasBeenClick)
			{
				historyHasBeenClicked=true;
				haveYouClickOnMenuBar=false;
				stasHasBeenClick=false;
				return;
			}
			if(Mouse.getX()>=84 && Mouse.getX()<=194 && (599-Mouse.getY())>=38 && (599-Mouse.getY())<=54
					&& stasHasBeenClick)
			{
				mp=0;
				mw=0;
				wp=0;
				writeHistory();
				return;
			}
			
			if(Mouse.getX()>=40 && Mouse.getX()<=150 && (599-Mouse.getY())>=21 && (599-Mouse.getY())<=37
					&& helpHasBeenClick)
			{
				instrutionHasBeenClick=true;
			}
			if(Mouse.getX()>=40 && Mouse.getX()<=150 && (599-Mouse.getY())>=38 && (599-Mouse.getY())<=54
					&& helpHasBeenClick)
			{
				aboutHasBeenClick=true;
			}
			if(haveYouClickOnMenuBar)
			{
				haveYouClickOnMenuBar=false;
				fileHasBeenClick=false;
				helpHasBeenClick=false;
				stasHasBeenClick=false;
				return;
			}
			if(x>=0 && x<=40 && y>=0 && y<=20)
			{
				haveYouClickOnMenuBar=true;
				fileHasBeenClick=true;
				return;
			}
			if(x>=41 && x<=84 && y>=0 && y<=20)
			{
				haveYouClickOnMenuBar=true;
				helpHasBeenClick=true;
				return;
			}
			if(Mouse.getX()>=85 && Mouse.getX()<=130 && (599-Mouse.getY())>=0 && (599-Mouse.getY())<=20)
			{
				haveYouClickOnMenuBar=true;
				stasHasBeenClick=true;
				return;
			}
		}
		if(computer1HasCompelete && computer2HasCompelete && playerHasCompelete && nowEveryOneCanTake
				&& Mouse.isButtonDown(0))
		{
			t.c1().getTakingCard().copy(t.c1());
			computer1HasCompelete=false;
			t.c2().getTakingCard().copy(t.c2());
			computer2HasCompelete=false;
			t.p().getTakingCard().copy(t.p());
			playerHasCompelete=false;
			hasAnyOneCompeleted=false;
			try {
				t.refressTheImagesOfAllPlayer();
			} catch (SlickException e) {
				e.printStackTrace();
			}
			hasOneGameOver=false;
			nowEveryOneCanTake=false;
		}
		if(hasOneGameOver && Mouse.isButtonDown(0) && (t.p().getTakingCard().isItLegal(x, y)))
		{
			if(youHaveNotTakenACard)
			{
				save=t.p().getTakingCard().getCard(x, y, t);
				if(!(save==-1))
				{
					youHaveNotTakenACard=false;
				}
			}
			else
			{
				if(t.p().getTakingCard().giveCard(t, save, x, y))
				{
					youHaveNotTakenACard=true;
				}
			}
			try {
				t.refressTheImagesOfAllPlayer();
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		if(clickToMoveOn && Mouse.isButtonDown(0)
				&&!(clickToMove) && !(haveToChoseWildCard))
			/*
			 * line run when the wild card is been shown on the screen and 
			 * now the player wants to continue and he clicks on the screen
			 * #this runs before the following if because
			 * # in this click to move should be false which is set to false 
			 * # in the following if but we want this to happen in 
			 * # different clicks 
			 */
		{
			clickToMoveOn=false;
			t.addFive(playerMakingFive);
			nowEveryOneCanTake=true;
		}
		if(clickToMove && Mouse.isButtonDown(0))
			/*
			 * it is run when you have seen what you have to
			 * make and now you wish to continue
			 */
		{
			clickToMove=false;
			return;
		}
		if(haveToClickToContinue && 
				!(hasPlayerPutTheCard) && 
				!(hasComputer1PutTheCard) && 
				!(hasComputer2PutTheCard)  && 
				Mouse.isButtonDown(0))
		{
			haveToClickToContinue=false;
			t.getOTC().setAllToDefault();
			won=t.hasAnyOneWon();
			if(!(won.equalsIgnoreCase("")) )
			{
				this.readHistory();
				if(t.hasPlayerWon())
				{
					++mw;
					matchHasWonByPlayer=true;
				}
				else
				{
					matchHasWonByPlayer=false;
				}
				matchHasWon=true;
				wp=((mw/mp)*100.00f);
				this.writeHistory();
				return ;
			}
			
			
			switch(t.getWhoWonLastTime())
			{
			case	"player"	:doesPlayerHaveToPlay=true;
									break;
			case	"computer1"	:doesPlayerHaveToComp1=true;
									break;
			case	"computer2"	:doesPlayerHaveToComp2=true;
									break;
			}
			
			if((t.p().size()==0)&&  (t.c1().size()==0)
					&&  (t.c2().size()==0))
			{

				t.c1().getTakingCard().setAll(t);
				t.c2().getTakingCard().setAll(t);
				t.p().getTakingCard().setAll(t);
				
				t.reset();
				doesPlayerHaveToPlay=false;
				doesPlayerHaveToComp1=false;
				 doesPlayerHaveToComp2=false;
				 gameIsOn=false;
				 hasPlayerPutTheCard=false;
				 hasComputer1PutTheCard=false;
				 hasComputer2PutTheCard=false;
				 haveToClickToContinue=false;  // when some one won
				 clickToMoveOn=true;			// after a five card have been shown and the wild card
				 canYouStillChoose=false;
			
				 clickToMove=false;
				 haveToChoseWildCard=false;
				 playerMakingFive="true";
				 shift();
				 hasOneGameOver=true;
			}
			return;
		}
		
		if(!(hasPlayerPutTheCard) && gameIsOn && t.p().isLocationInPlayersCard(x, y) 
				 && Mouse.isButtonDown(0) )
		{	
			if( t.p().isTheMoveLegle(x, y ,t.getOTC()))
			{
			t.p().setTheCard(x, y, t.getOTC());
			hasPlayerPutTheCard=true;
			try {
				t.refressTheImagesOfAllPlayer();
			} catch (SlickException e) {
				e.printStackTrace();
			}
			}
			else
			{
				wrong.play();
			}
			
		}
		
		if(canYouStillChoose && t.getToss().isInTheLocationOfCards(x, y))
			/*
			 * goes in just for the toss;
			 */
		{
			if(Mouse.isButtonDown(0))
			{
				int playerMaking=t.getToss().whichCard(x,y);
				t.p().setCardsNeedToWin(playerMaking);
				int[] roc=t.getToss().getRestOfTheName(playerMaking);
				t.c1().setCardsNeedToWin(roc[0]);
				t.c2().setCardsNeedToWin(roc[1]);

				System.out.println("pm :"+playerMaking);
				System.out.println("C1 :"+roc[0]+"\nC2 :"+roc[1]);
				canYouStillChoose=false;
				clickToMove=true;
				settheThingInBeg( playerMaking,roc);
				/*
				 * click to move is true
				 * clicktomoveon is true if computer is making 5 cards
				 * have to chose wild card is false if coomputer is making 5 card but if player making it then true
				 */
				
			}
			
		}
		if(haveToChoseWildCard
				&& t.getwild().hasPlayerClickOnThecard(x, y))
		{
			if( playerMakingFive.equalsIgnoreCase("player"))
			{
				String tempWild=t.p().get(t.getwild().whichCardHasPlayerHasChosen(x, y)).charAt(0)+"";
				t.setWildCardInEveryPlace(tempWild);
				try {
					wild=new Image(path+tempWild+"two.png");
				} catch (SlickException e) {
					e.printStackTrace();
				}
				System.out.println("ply :"+tempWild);
				t.setWildCardInTC();
			}
			haveToChoseWildCard=false;
			clickToMoveOn=true;
		}
	}
	public void shift()
	{
		int temp=t.p().getCardneedsToWin();
		t.p().setCardsNeedToWin(t.c2().getCardneedsToWin());
		t.c2().setCardsNeedToWin(t.c1().getCardneedsToWin());
		t.c1().setCardsNeedToWin(temp);
		int[] roc={t.c1().getCardneedsToWin(),t.c2().getCardneedsToWin()};
		settheThingInBeg(t.p().getCardneedsToWin(),roc);
	}
	public void settheThingInBeg(int playerMaking,int[] roc)
	{
		if(playerMaking==5)
		{
			playerMakingFive="player";
			t.addFive(playerMakingFive);
			haveToChoseWildCard=true;
			doesPlayerHaveToPlay=true;
		}
		else if(roc[0]==5)
		{
			playerMakingFive="computer1";
			t.addFive(playerMakingFive);
			String tempWild=t.getwild().getWildCard(t.c1());
			t.setWildCardInEveryPlace(tempWild);
			haveToChoseWildCard=false;
			clickToMoveOn=true;
			try {
				wild=new Image(path+tempWild+"two.png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("comp 1 :"+tempWild);
			doesPlayerHaveToComp1=true;
			t.setWildCardInTC();
		}
		else
		{
			playerMakingFive="computer2";
			t.addFive(playerMakingFive);
			String tempWild=t.getwild().getWildCard(t.c2());
			t.setWildCardInEveryPlace(tempWild);
			haveToChoseWildCard=false;
			clickToMoveOn=true;
			try {
				wild=new Image(path+tempWild+"two.png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("comp 2 :"+tempWild);
			doesPlayerHaveToComp2=true;
			t.setWildCardInTC();
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
