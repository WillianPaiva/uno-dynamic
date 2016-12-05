package uno.app;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import uno.app.card.Card;
import uno.app.colors.Colors;
import uno.app.graph.CardsGraph;

public class App 
{
  private static ArrayList<Card> cards = new ArrayList<Card>();
  public static void main( String[] args ) throws IOException, InterruptedException
  {
    System.out.println(Colors.RED+"Reading File");
    readFile(args[0]);
    breakline();
    cards.forEach(System.out::print);
    breakline();
    CardsGraph n = new CardsGraph(cards);
    n.display();
    n.genDFT();
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
