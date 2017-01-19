package colorcoding;

import java.util.ArrayList;
import java.util.HashMap;

import uno.app.card.Card;

public class ColorCoding {
  private ArrayList<Card> cards;
  private int K;
  private HashMap<Integer,ArrayList<Card>> labelsSet = new HashMap<Integer,ArrayList<Card>>();
  private ArrayList<Card> result = new ArrayList<Card>();
  private HashMap<Integer,ArrayList<Integer>> subsets = new HashMap<Integer,ArrayList<Integer>>();

  public ColorCoding(ArrayList<Card> cards, int K){
    this.cards = cards;
    this.K = K;
    assignLabels();
    makeSetOfCardsByLabel();
    calcSubsets();
  }

  //////////////////////////////////////////////////////////////////////////
  // assigne a randon label to each card on the deck step one of the algo //
  //////////////////////////////////////////////////////////////////////////

  private void assignLabels(){
    cards.forEach(x-> x.setLabel((int)(Math.random() * K + 1)));
  }


  //////////////////////////////////////////
  // make the set of cards with label "l" //
  //////////////////////////////////////////

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

  /////////////////////////////////////////////////////////////////////////////
  // for each size s from 2 to k, for each subset S of labels of size s in   //
  // {1, . . . , k}, for each ` ∈ S, compute the cards of CS,` following the //
  // rule above                                                              //
  /////////////////////////////////////////////////////////////////////////////

  private void calcSubsets(){
    for(int x:labelsSet.keySet()){

      if(labelsSet.get(x).size() == 1){
        ///////////////////////////////////////////////////////////////////////
        // if S is of order 1, i.e. S = {`}, then every card with label ` is //
        // the last card of a sequence of cards having all color in S. So    //
        // C{`},` is all cards with label ` for each ` ∈ {1, . . . , k}.     //
        ///////////////////////////////////////////////////////////////////////

      }else if(labelsSet.get(x).size() > 1){
        ////////////////////////////////////////////////////////////////////////
        // otherwise, a card C with label L is the end of such a sequence for //
        // S∪{L} if and only if there is a label L'  ∈ S such that C is       //
        // reachable from a card with label L'  that is the last card in a    //
        // sequence using all colors from S. Thus CS∪{ell},L contains all    //
        // cards that are of label L and of same number or color than cards   //
        // in CS,`0 sor some label L' ∈ S.                                    //
        ////////////////////////////////////////////////////////////////////////

      }
    }
  }



}
