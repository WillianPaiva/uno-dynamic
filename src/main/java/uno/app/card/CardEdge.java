package uno.app.card;

public class CardEdge {
  private Card a;
  private Card b;

  public CardEdge(Card a , Card b){
    this.a = a;
    this.b = b;
	}

	/**
	 * @return the a
	 */
	public Card getA() {
		return a;
	}

	/**
	 * @param a the a to set
	 */
	public void setA(Card a) {
		this.a = a;
	}

	/**
	 * @return the b
	 */
	public Card getB() {
		return b;
	}

	/**
	 * @param b the b to set
	 */
	public void setB(Card b) {
		this.b = b;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((b == null) ? 0 : b.hashCode());
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
		CardEdge other = (CardEdge) obj;
		if (a == null) {
			if (other.a != null)
				return false;
		} else if (!a.equals(other.a))
			return false;
		if (b == null) {
			if (other.b != null)
				return false;
		} else if (!b.equals(other.b))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "["+ a.toString() + "," + b.toString() + "]";
	}


}
