package backtrack;

import java.util.ArrayList;

import uno.app.card.Card;

public class Backtrack {
  private ArrayList<Card> cards;
	private ArrayList<Card> longest;
  private int max = 0;

	/**
	 *
	 */
	public Backtrack(ArrayList<Card> cards) {
    this.cards = cards;
	}


  public void searchLongest(){
    for(Card c:cards){
      ArrayList<Card> temp = new ArrayList<Card>(cards);
      ArrayList<Card> temp2 = new ArrayList<Card>();
      temp.remove(c);
      temp2.add(c);
      getMax(temp,temp2);
    }
  }

  private ArrayList<Card> getMax(ArrayList<Card> c, ArrayList<Card> p){
    ArrayList<Card> result = new ArrayList<Card>();
    Card cd = p.get(p.size()-1);
    for(Card x:c){
      if(cd.connect(x)){
        ArrayList<Card> tc = new ArrayList<Card>(c);
        ArrayList<Card> tp = new ArrayList<Card>(p);
        tc.remove(x);
        tp.add(x);
        result = getMax(tc,tp);
        if(result.size() > this.max){
          this.max = result.size();
          this.longest = new ArrayList<Card>(result);
        }
      }
    }
    return p;
  }

  public String toString(){
    return "size "+(this.max-1)+"\nsequence "+this.longest;
  }
}
