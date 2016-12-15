package dynamic;

import java.util.ArrayList;
import java.util.Hashtable;

import uno.app.card.Bag;
import uno.app.card.Card;

////////////////////////////////////////////////////////////////////////////////
// Suppose we have a sequence of subsets of cards S 1 ; S 2 ; : : : ; S p as  //
// com- puted by the DFS. Now we want to compute all the maximum sequences by //
// considering each subset independently. Valid sequences of cards may re-    //
// main in one subset, or may visit more than one. For example, the sequence  //
// 6-5-7-2-4-3-1 starts in the green set, then goes through the red set       //
// before returning to the blue set (see Figure 2, left). This can be         //
// decompose into a sequence ending in 5 in the green set, then joining 5 to  //
// 2 in the red set, and nishing from 2 in the blue set. For the computation  //
// within one set, we don't need to know the details of what happens in the   //
// other set, but just how they interact with the current set, i.e. with the  //
// intersections. Here is the process we follow. In a subset S i , we compute //
// a maximal sequence, taking into account possibilities of joining to cards  //
// by sequences in neighbouring sets. Precisely, we need to de ne the         //
// signature of a sequence, that is how the sequence behave on the            //
// intersection. Then, for each subset, we consider a maximal sequence of     //
// cards together with possible extra moves, and associate the length of the  //
// sequence to its signature for further computation. This can be done        //
// iteratively, using the value of the signatures of the set S i              //
////////////////////////////////////////////////////////////////////////////////



public class Dynamic {

  private Hashtable<Integer, Bag> bags = new Hashtable<Integer, Bag>();
  private Hashtable<Integer, ArrayList<Signature>> sigs = new Hashtable<Integer, ArrayList<Signature>>();
  private ArrayList<Card> cards;
  private ArrayList<Card> discards = new ArrayList<Card>();

  public Dynamic(ArrayList<Card> cards) {
    super();
    this.cards = cards;
  }


  //////////////////////////////////////////////////////////////////////////////
  // Here, we rst use a separation of the deck of cards into some maximal     //
  // sequences obtained within a depth rst search algorithm DFS. Explore the  //
  // whole card set with a DFS. All the paths from the root to a leaf in are  //
  // maximal paths. They form a sequence of subsets of cards S 1 , S 2 , etc. //
  // They are represented with colors in Fig. 1 (S 1 , S 2 , S 3). The key    //
  // observation of this exploral is that a valid sequence of cards that uses //
  // two cards within di erent subsets S i and S i +1 must pass by cards      //
  // which are at the intersection S i \ S i +1 of these subsets. For         //
  // example, in Fig. 1, it is not possible to join the node with label 4 to  //
  // the node with label 6 without going through 1 or 2. We will use this to  //
  // make an ecient algorithm when the intersections of the subsets are     //
  // small.                                                                   //
  //////////////////////////////////////////////////////////////////////////////

  public void genDFT() throws InterruptedException{
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
  }

  private void makeFirstBag(int index){
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



  private ArrayList<Card> getSequence(Card c){
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

  private Card getNext(Card c){
    for(Card d: this.cards){
      if(c.connect(d)){
        return d;
      }
    }
    return null;
  }


  private boolean makeBag(int index){
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

  public String toString(){
    return "bags found = "+ this.bags.size();
	}

	/**
	 * @return the bags
	 */
	public Hashtable<Integer, Bag> getBags() {
		return bags;
	}

	/**
	 * @param bags the bags to set
	 */
	public void setBags(Hashtable<Integer, Bag> bags) {
		this.bags = bags;
	}

  public void makeSigs(){
    Hashtable<Integer, Bag> temp = new Hashtable<Integer, Bag>(bags);
    for(int i = 0; i< temp.size();i++){
      System.out.println("--->"+i);
      Bag presentBag = temp.get(i);
      Bag pastBag = new Bag();
      Bag futureBag = new Bag();
      ArrayList<Signature> pastSigs = new ArrayList<Signature>();
      ArrayList<Signature> presentSigs = new ArrayList<Signature>();
      if(i!=0){
        pastBag = temp.get(i-1);
      }
      if(i < temp.size()-1){
        futureBag = temp.get(i+1);
      }
      ArrayList<Card> pastPresentIntersection = pastBag.intersection(presentBag);
      ArrayList<Card> futurePresentIntersection = futureBag.intersection(presentBag);
      ArrayList<ArrayList<Card>> interpolations =  new ArrayList<ArrayList<Card>>();
      interpolations =  interpolate(presentBag.getBag(),futurePresentIntersection);


      for(ArrayList<Card> l:interpolations){
        Signature prensent = new Signature();
        boolean lastIntersec = false;
        ArrayList<Card> sigSequence = new ArrayList<Card>();
        ArrayList<Card> sigForbiden = new ArrayList<Card>();
        char sigType = 'n';
        int size = 0;
        int interJump = 0;
        Card previusCard = null;
        for(Card card:l){
          if(previusCard == null){
            if(futurePresentIntersection.contains(card)){
              // sigSequence.add(card);
              sigType = 'b';
              lastIntersec = true;
            }else{
              sigType = 'a';
            }
          }else{
            if(futurePresentIntersection.contains(card)){
              if(lastIntersec){
                interJump++;
              }

              if(!lastIntersec){
                size++;
                lastIntersec = true;
                if(sigType == 'n'){
                  sigType = 'a';
                }else if(sigType == 'b'){
                  sigType = 'u';
                }
                sigSequence.add(card);
              }
            }else{
              if(lastIntersec){
                if(!sigSequence.contains(previusCard)){
                  sigSequence.add(previusCard);
                }
                lastIntersec = false;
                if(sigType == 'n'){
                  sigType = 'b';
                }else if(sigType == 'a'){
                  if(interJump > 0){
                    sigType = 'v';
                  }else{
                    sigType = 'X';
                  }

                }
              }
              size++;
            }
          }
          previusCard = card;
        }

        Signature result = new Signature(sigSequence,sigType,sigForbiden,size);
        // System.out.println(pastSigs);
        // for(Signature ps:pastSigs){
        //   if(haveall(ps.getSequence(),l) ){
        //     ArrayList<Card> sigSeq = new ArrayList<Card>();
        //     sigSeq.addAll(ps.getSequence());
        //     sigSeq.addAll(sigSequence);
        //     char sigT = 'n';
        //     if((ps.getType() == 'a' || ps.getType() == 'b') && (sigType == 'a' || sigType == 'b')){
        //         sigT = 'v';
        //      }
        //     int siz = size + ps.getSize();
        //     Signature res = new Signature(sigSeq,sigT,sigForbiden,siz);
        //     presentSigs.add(res);
        //     System.out.println(res);
        //     }
        // }
        if(!sigSequence.isEmpty()){
          System.out.println(result);
          System.out.println(l);
        }
        // if(pastPresentIntersection.contains(l.get(0))){
        //   ArrayList<Card> term = new ArrayList<Card>();
        //   Signature s = search (pastSigs,term,'a');
        //   if(s != null){
        //   }
        // }
      }
    }
  }


  private boolean haveall(ArrayList<Card> term,ArrayList<Card> seq ){
    for(Card c:term){
      if(!seq.contains(c)){
        return false;
      }
    }
    return true;
  }

  private boolean havenone(ArrayList<Card> term,ArrayList<Card> seq ){
    for(Card c:term){
      if(seq.contains(c)){
        return false;
      }
    }
    return true;
  }

  private ArrayList<ArrayList<Card>> interpolate(ArrayList<Card> bag ,ArrayList<Card> intersection ){
    ArrayList<ArrayList<Card>> temp3 = new ArrayList<ArrayList<Card>>();
    for(Card c:bag){
      ArrayList<Card> temp = new ArrayList<Card>(bag);
      ArrayList<Card> temp2 = new ArrayList<Card>();
      temp.remove(c);
      temp2.add(c);
      getInterpolations(temp,temp2,temp3, intersection);
    }
    return temp3;
  }

  private ArrayList<Card> getInterpolations(ArrayList<Card> c,
                                            ArrayList<Card> p,
                                            ArrayList<ArrayList<Card>> res,
                                            ArrayList<Card> intersection 
                                            ){
    Card cd = p.get(p.size()-1);
    for(Card x:c){
      if(cd.connect(x) || (intersection.contains(cd) && intersection.contains(x))){
        ArrayList<Card> tc = new ArrayList<Card>(c);
        ArrayList<Card> tp = new ArrayList<Card>(p);
        tc.remove(x);
        tp.add(x);
        res.add(getInterpolations(tc,tp,res,intersection));
        }
      }
    return p;
    }
  }
