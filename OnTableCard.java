package threeCardGame;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class OnTableCard {
	String p;
	String c1;
	String c2;
	String path="/src/threeCardGame/pic/deck/";
	public OnTableCard()
	{
		p="";
		c1="";
		c2="";
	}
	public void setP(String s) {
		p=s;
	}
	public void setc1(String s) {
		c1=s;
	
	}
	public void setc2(String s) {
		c2=s;
	}
	public void draw() throws SlickException
	{
		Image pi;
		Image c1i;
		Image c2i;
		if(!(p.equalsIgnoreCase("")))
		{
			pi=new Image(path+p);
			pi.draw(360,320);
		}
		if(!(c1.equalsIgnoreCase("")))
		{
			c1i=new Image(path+c1);
			c1i.draw(140,100);
		}
		if(!(c2.equalsIgnoreCase("")))
		{
			c2i=new Image(path+c2);
			c2i.draw(580,100);
		}
	}
	public void setAllToDefault(){
		p="";
		c1="";
		c2="";
	}
	public String getp(){
		return p;
	}
	public String getc1(){
		return c1;
	}
	public String getc2(){
		return c2;
	}
	public boolean hasEveryOnePlayed()
	{
		if((p.equalsIgnoreCase("")) && (c1.equalsIgnoreCase("")) && (c2.equalsIgnoreCase("")))
		{
			return true;
		}
		return false;
	}
}
