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
  private String basePath = new File("style.css").getAbsolutePath();
  private Viewer v;

  public CardsGraph(ArrayList<Card> c)
  {
	  System.setProperty("org.graphstream.ui.renderer",
              "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
    this.graph = new SingleGraph("Main Graph");
    graph.setStrict(false);
    graph.setAutoCreate( true );
    graph.addAttribute("ui.stylesheet", "url('"+basePath+"')");
  }

  public void display()
  {
    v = this.graph.display();
  }


  // public int createNodes(){
  //   cards.forEach(x -> {Node n = graph.addNode(x.toString());
  //       n.addAttribute("card", x);
  //       String t = n.getAttribute("ui.label");
  //       if(t == null){
  //         n.addAttribute("ui.label", x.toString());
  //       }
  //     });
  //   return graph.getNodeCount();
  // }

  // public void plotBags(){
  //   for(int i = 0 ; i < bags.size(); i++){
  //     ArrayList<Card> temp = bags.get(i).getBag();
  //     for(int x = 0; x < (temp.size()-1) ; x++){
  //       CardEdge c = new CardEdge(temp.get(temp.size()-(x+1)),temp.get(temp.size()-(x+2))); 
  //       Edge e = graph.addEdge(c.toString(),c.getA().toString(),c.getB().toString());
  //       if(e != null){
  //         e.addAttribute("ui.label", c.toString());
  //         e.addAttribute("edge", c);
  //         e.addAttribute("ui.style", "fill-color: "+RandonColor.colors[i%400]+";");
  //       }
  //     }
  //     breakline();
  //     System.out.println(temp);
  //     breakline();
  //   }
  // }

  // public void plotExtraEdges(){
  //   for(int i = 0 ; i < bags.size(); i++){
  //     ArrayList<Card> temp = bags.get(i).getBag();
  //     for(int x = 0; x < (temp.size()-1) ; x++){
  //       for(int y = (x+1); y < (temp.size()) ; y++){
  //         if(temp.get(x).connect(temp.get(y))){
  //           CardEdge c = new CardEdge(temp.get(x),temp.get(y)); 
  //           Edge e = graph.addEdge(c.toString(), c.getA().toString(), c.getB().toString());
  //           if(e != null){
  //             String t = e.getAttribute("ui.label");
  //             if(t == null){
  //               e.addAttribute("ui.label", c.toString());
  //               e.addAttribute("edge", c);
  //               e.addAttribute("ui.class", "notin");
  //             }
  //           }
  //         }
  //       }
  //     }
  //   }

  // }


}


