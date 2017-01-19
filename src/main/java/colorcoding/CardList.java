package colorcoding;

import java.util.ArrayList;

import uno.app.card.Card;

public class CardList {

  private ArrayList<Card> list;
  private Card last;
  private Card first;

	public CardList(ArrayList<Card> list , Card last) {
    this.list = list;
    this.last = last;
	}

	/**
	 * @return the list
	 */
	public ArrayList<Card> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(ArrayList<Card> list) {
		this.list = list;
	}

	/**
	 * @return the last
	 */
	public Card getLast() {
		return last;
	}

	/**
	 * @param last the last to set
	 */
	public void setLast(Card last) {
		this.last = last;
	}

}
