package uno.app.graph;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;


import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

import uno.app.RandonColor;
import uno.app.card.Bag;
import uno.app.card.Card;
import uno.app.card.CardEdge;
import uno.app.colors.Colors;


public class CardsGraph{
  private Graph graph; 
  private Hashtable<Integer,Bag> bags = new Hashtable<Integer,Bag>();
  private String basePath = new File("style.css").getAbsolutePath();
  private ArrayList<Card> cards;
  private ArrayList<Card> discards = new ArrayList<Card>();
  private Viewer v;

  public CardsGraph(ArrayList<Card> c)
  {
	  System.setProperty("gs.ui.renderer",
              "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
    this.graph = new SingleGraph("Main Graph");
    graph.setStrict(false);
    graph.setAutoCreate( true );
    this.cards = c;
    graph.addAttribute("ui.stylesheet", "url('"+basePath+"')");
  }

  public void display()
  {
    v = this.graph.display();
  }



  public void genDFT() throws InterruptedException{

    cards.forEach(x -> {Node n = graph.addNode(x.toString());
        n.addAttribute("card", x);

        	String t = n.getAttribute("ui.label");
              if(t == null){
        n.addAttribute("ui.label", x.toString());
              }

      });



    int index = 0;
    while(!cards.isEmpty()){
      if(bags.isEmpty()){
        makeFirstBag(index);
        index++;
      }else{
        if(!makeBag(index)){
          makeFirstBag(index);
          index++;
        }else{
          index++;
        }
      }
    }

    for(int i = 0 ; i < bags.size(); i++){
      ArrayList<Card> temp = bags.get(i).getBag();
      for(int x = 0; x < (temp.size()-1) ; x++){
        CardEdge c = new CardEdge(temp.get(temp.size()-(x+1)),temp.get(temp.size()-(x+2))); 
        Edge e = graph.addEdge(c.toString(),c.getA().toString(),c.getB().toString());
        if(e != null){
            	  e.addAttribute("ui.label", c.toString());
            	  e.addAttribute("edge", c);
            	  e.addAttribute("ui.style", "fill-color: "+RandonColor.colors[i%400]+";");
        }
      }

      breakline();
      System.out.println("bag color : "+cssClass[i%5]);
      System.out.println(temp);
      breakline();

      }

    for(int i = 0 ; i < bags.size(); i++){
      ArrayList<Card> temp = bags.get(i).getBag();
      for(int x = 0; x < (temp.size()-1) ; x++){
        for(int y = (x+1); y < (temp.size()) ; y++){
          if(temp.get(x).connect(temp.get(y))){
            CardEdge c = new CardEdge(temp.get(x),temp.get(y)); 
            Edge e = graph.addEdge(c.toString(), c.getA().toString(), c.getB().toString());
            if(e != null){
              String t = e.getAttribute("ui.label");
              if(t == null){
            	  e.addAttribute("ui.label", c.toString());
            	  e.addAttribute("edge", c);
            	  e.addAttribute("ui.class", "notin");
              }
            }
          }
          }
        }
    }

    }

  public static void breakline()
  {
    System.out.println(Colors.GREEN+"\n=====================================================================");
  }

  public void makeFirstBag(int index){
    Bag b = new Bag();
    Card actual = cards.get(0);
    cards.remove(0);
    int key = 0;
    b.addCard(actual);
    ArrayList<Card> sequence = getSequence(actual);
    if(sequence.size()>0){
      for(int i = 0 ; i < sequence.size(); i++){
        b.addCard(sequence.get(i));
      }
    }
    bags.put(index, b);
  }

  public ArrayList<Card> getSequence(Card c){
    ArrayList<Card> temp = new ArrayList<Card>();
    int indice = 0;
    Card actual = c;
    Card next = getNext(actual);
    while(!(next==null)){
      temp.add(next);
      actual = next;
      discards.add(actual);
      cards.remove(next);
      next = getNext(actual);
      indice++;
    }
    return temp;
  }

  public Card getNext(Card c){
    for(Card d: this.cards){
      if(c.connect(d)){
        return d;
      }
    }
    return null;
  }


  public boolean makeBag(int index){
    Bag b = bags.get(bags.size()-1).removeLast();
    ArrayList<Card> temp = b.getBag();
    while(!temp.isEmpty()){
      Card actual = temp.get(temp.size()-1);
      temp.remove(temp.size()-1);
      ArrayList<Card> sequence = getSequence(actual);
      if(sequence.size()>0){
        temp.add(actual);
        for(int i = 0 ; i < sequence.size(); i++){
          temp.add(sequence.get(i));
        }
        b.setBag(temp);
        bags.put(index, b);
        return true;
      }
    }
    return false;
  }

}


