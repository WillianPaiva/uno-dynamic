package uno.app.card;


import java.util.ArrayList;

public class Bag {

  private ArrayList<Card> bag;


  public Bag(ArrayList<Card> bag){
    this.bag = bag;
  }

  public Bag(){
    this.bag = new ArrayList<Card>();
  }
  public void addCard(Card c){
    this.bag.add(c);
  }

  public Bag removeLast(){
    ArrayList<Card> res = new ArrayList<Card>(this.bag);
    res.remove(res.size()-1);
    return new Bag(res);
  }

  /**
   * @return the bag
   */
  public ArrayList<Card> getBag() {
    return bag;
  }

  /**
   * @param bag the bag to set
   */
  public void setBag(ArrayList<Card> bag) {
    this.bag = bag;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((bag == null) ? 0 : bag.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Bag other = (Bag) obj;
    if (bag == null) {
      if (other.bag != null)
        return false;
    } else if (!bag.equals(other.bag))
      return false;
    return true;
  }

}
