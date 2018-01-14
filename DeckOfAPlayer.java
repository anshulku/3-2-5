package threeCardGame;

import java.util.ArrayList;

public class DeckOfAPlayer {
	ArrayList<String> deck;
	public DeckOfAPlayer()
	{
		deck=new ArrayList<String>();
	}
	public void add(String s)
	{
		deck.add(s);
	}
	public String get(int i)
	{
		return deck.get(i);
	}
	public void remove(int i)
	{
		deck.remove(i);
	}
	public void reset()
	{
		deck.clear();
	}
	public int size() {
		// TODO Auto-generated method stub
		return deck.size();
	}
}
