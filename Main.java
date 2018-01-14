package threeCardGame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import spider.GameOver;
import spider.SpiderXscapeMenu;
import spider.State1;
import spider.State2;
import spider.State3;
import spider.War;

/*
 * use slick 2d to add easy class to play
 * 30 card game
 */
public class Main extends StateBasedGame
{

	public Main(String name) throws SlickException {
		super(name);
	}
	public static void main(String args[]) throws SlickException
	{
		AppGameContainer app=new AppGameContainer(new Main("dreissig Karte Spiel"));
		app.setDisplayMode(800, 600, false);
		app.setTargetFrameRate(500);
		app.setShowFPS(false);
		app.setIcon("/src/threeCardGame/pic/icon.png");
		app.setMusicOn(true);
		
		app.start();
	}
	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		this.addState(new Easy());
	}

}
