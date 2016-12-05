package uno.app.card;

import uno.app.colors.Colors;

public class Card {
  private int nb;
  private int color;
  public Card(String card)
  {
    String[] split = card.split(" ");
    this.nb = Integer.parseInt(split[0]);
    this.color = Integer.parseInt(split[1]);
  }

  public int getNb() {
	return nb;
}

public void setNb(int nb) {
	this.nb = nb;
}

public int getColor() {
	return color;
}

public void setColor(int color) {
	this.color = color;
}

@Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + color;
    result = prime * result + nb;
    return result;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Card other = (Card) obj;
    if (color != other.color)
      return false;
    if (nb != other.nb)
      return false;
    return true;
  }

  public String toString()
  {
    return "["+this.nb+","+this.color+"]";
  }

  public boolean connect(Card c)
  {
    return this.nb == c.getNb() || this.color == c.getColor();
  }

}
