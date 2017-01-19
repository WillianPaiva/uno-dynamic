package colorcoding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;

import uno.app.card.Card;

public class ColorCoding {
  private ArrayList<Card> cards;
  //the lenght k to be searched 
  private int K;

  private HashMap<Integer,ArrayList<Card>> labelsSet = new HashMap<Integer,ArrayList<Card>>();
  private ArrayList<Card> result = new ArrayList<Card>();

  private HashMap<Integer,ArrayList<ArrayList<Card>>> subsets = new HashMap<Integer,ArrayList<ArrayList<Card>>>();

  public ColorCoding(ArrayList<Card> cards, int K){
    this.cards = cards;
    this.K = K;
    assignLabels();
    makeSetOfCardsByLabel();
    calcSubsets();
    subsets.forEach((x,y) -> y.forEach(System.out::println));
  }

  //////////////////////////////////////////////////////////////////////////
  // assigne a randon label to each card on the deck step one of the algo //
  //////////////////////////////////////////////////////////////////////////
  private void assignLabels(){
    cards.forEach(x-> x.setLabel((int)(Math.random() * K + 1)));
  }


  ////////////////////////////////////////////////////////////////////////////
  // make the set of cards with label "l" creates a sub set of cards tha    //
  // share the same label. for each label L define C{l}l as the set of cards//
  // with label L                                                           //
  ////////////////////////////////////////////////////////////////////////////

  private void makeSetOfCardsByLabel(){
    for(int i = 1 ; i <= K; i++ ){
      ArrayList<Card> cds = new ArrayList<Card>();
      for(Card c:cards){
        if(c.getLabel() == i){
          cds.add(c);
        }
      }
      labelsSet.put(i,cds);
    }
  }

  ///////////////////////////////////////////////////////////////////////////
  // for each size s from 2 to k, for each subset S of labels of size s in //
  // {1, . . . , k}, for each l` ∈ S, compute the cards of CS,l` following //
  // the rule above                                                        //
  ///////////////////////////////////////////////////////////////////////////
  private void calcSubsets(){
    IntStream.rangeClosed(0, K).boxed().forEach(x-> subsets.put(x,new ArrayList<ArrayList<Card>>()));
    labelsSet.forEach((key,list) -> subsets.get(list.size()).add(list));
    }

  private void calc(){
    //for each size from 2 to K
    for(int s = 2 ; s <=K; s++){
      //for each subset S of labels of size s
      //for each label L in S calculate
      subsets.get(s).forEach(label -> {

        });
    }
  }
 
  }



