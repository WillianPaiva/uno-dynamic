package dynamic;

import java.util.ArrayList;

import uno.app.card.Card;

////////////////////////////////////////////////////////////////////////////////
// We now discuss precisely how the signature should be de ned. There is not  //
// a single way of de ning the signatures, you can check Pr. Gavoille's       //
// webpage for another proposal (in French). We describe here one way of      //
// computing the signature. We consider the set of cards in the intersection  //
// S i \ S i +1 that are used by a sequence. A card may be used for jumping   //
// from the card of one subset to the other either from S i to S i +1 (from   //
// left to right) or in the other direction. Note that if it jumped from left //
// to right, the next jump must be from right to left, and vice versa. In the //
// signature, we store all the cards that are used to jump from one set to    //
// the other in a sequence, and we remember if the rst jump is from left to   //
// right or from right to left with a signature type. This implies in the     //
// signature                                                                  //
////////////////////////////////////////////////////////////////////////////////

public class Signature {
  public enum Time{
    PAST,PRESENT,FUTURE
  }
  /////////////////////////////////////////////////////////////////////////
  // an ordered sequence of cards (e.g. (1 ; 2 ; 5)), that describes the //
  // order in which the cards are jumped through in the intersection     //
  /////////////////////////////////////////////////////////////////////////
  private ArrayList<Card> sequence;

  /////////////////////////////////////////////////////////////////////////////
  // a type (A or B) specifying whether the rst card in the order is visited //
  // from left to right (type A) or from right to left (type B).             //
  /////////////////////////////////////////////////////////////////////////////
  private char type;

  //////////////////////////////////////////////////////////////////////////////
  // Another case that must be considered is when some card of the intersec-  //
  // tion is used in the sequence but not as a jump from one subset to the    //
  // other. In that case, the same card should not be used in a later part of //
  // the sequence. So in the signature, we need to store the set of cards     //
  // that are forbidden for later use. a set of forbiden card, that have been //
  // used alread                                                              //
  //////////////////////////////////////////////////////////////////////////////
  private ArrayList<Card> forbiden;

  private int size;

  private Time time;

  public Signature(ArrayList<Card> sequence, char type, ArrayList<Card> forbiden, int size){
    this.sequence = sequence;
    this.type = type;
    this.forbiden = forbiden;
    this.size = size;
  }

  public Signature(){
    this.sequence =new ArrayList<Card>();
    this.type = 'n';
    this.forbiden = new ArrayList<Card>();
    this.size = 0;
  }

  public ArrayList<Card> getSequence() {
    return sequence;
  }

  public void setSequence(ArrayList<Card> sequence) {
    this.sequence = sequence;
  }

  public char getType() {
    return type;
  }

  public void setType(char type) {
    this.type = type;
  }

  public ArrayList<Card> getForbiden() {
    return forbiden;
  }

  public void setForbiden(ArrayList<Card> forbiden) {
    this.forbiden = forbiden;
  }

	/**
	* @return the size
	*/
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	public Time getTime() {
    return time;
  }

  public void setTime(Time time) {
    this.time = time;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((forbiden == null) ? 0 : forbiden.hashCode());
    result = prime * result + ((sequence == null) ? 0 : sequence.hashCode());
    result = prime * result + ((time == null) ? 0 : time.hashCode());
    result = prime * result + type;
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
    Signature other = (Signature) obj;
    if (forbiden == null) {
      if (other.forbiden != null)
        return false;
    } else if (!forbiden.equals(other.forbiden))
      return false;
    if (sequence == null) {
      if (other.sequence != null)
        return false;
    } else if (!sequence.equals(other.sequence))
      return false;
    if (time != other.time)
      return false;
    if (type != other.type)
      return false;
    return true;
  }
  
  public String toString(){
    return "{("+sequence+"),"+type+",("+forbiden+")}:"+size;
  }

}
