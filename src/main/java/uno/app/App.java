package uno.app;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import backtrack.Backtrack;

import colorcoding.ColorCoding;

import dynamic.Dynamic;

import uno.app.card.Card;
import uno.app.colors.Colors;

public class App 
{
  private static ArrayList<Card> cards = new ArrayList<Card>();
  public static void main( String[] args ) throws IOException, InterruptedException
  {

    long startTime = System.currentTimeMillis();
    long endTime = System.currentTimeMillis();

    breakline();
    System.out.println(Colors.RED+"Reading File");
    readFile(args[0]);
    breakline();
    cards.forEach(System.out::print);
    breakline();


    breakline();
    Backtrack b = new Backtrack(cards);
    startTime = System.currentTimeMillis();
    b.searchLongest();
    System.out.println(Colors.RED+b.toString());
    endTime = System.currentTimeMillis();
    System.out.println(Colors.BLUE+"Total backtrack execution time: " + (endTime-startTime) + "ms"); 




    breakline();
    Dynamic d = new Dynamic(new ArrayList<Card>(cards));
    startTime = System.currentTimeMillis();
    d.genDFT();
    System.out.println(Colors.RED+d.toString());
    d.makeSigs();
    endTime = System.currentTimeMillis();
    d.getBags().forEach((x,y) -> {breakline(); System.out.println(y); breakline();});
    System.out.println(Colors.BLUE+"Total dynamic execution time: " + (endTime-startTime) + "ms"); 

    ColorCoding test = new ColorCoding(cards,cards.size());

  }

  public static void breakline()
  {
    System.out.println(Colors.GREEN+"\n=====================================================================");
  }

  public static void readFile(String arg) throws IOException
  {
    try (Stream<String> stream = Files.lines(Paths.get(arg),Charset.defaultCharset())) {
      stream.forEach(x -> cards.add( new Card(x) )  );
    }
  }
}
