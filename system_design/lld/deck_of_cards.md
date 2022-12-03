<b>Deck of Cards</b>: Design the data structures for a generic deck of cards. Explain how you would subclass the data structures to implement blackjack.

<b>Solution</b>: First, we need to recognize that a "generic" deck of cards can mean many things. Generic could mean a standard deck of cards that can playa poker-like game, or it could even stretch to Uno or Baseball cards. It is important to ask your interviewer what she means by generic.

Let's assume that your interviewer clarifies that the deck is a standard 52-card set, like you might see used in a blackjack or poker game. If so, the design might look like this:

```
public enum Suit {
  Club(0), Diamond(1), Heart(2), Spade(3);
  private int value;
  private Suit(int v) { value = v; }
  public int getValue() { return value; }
  public static Suit getSuitFromValue(int value) {
    switch(value) {
      case 0:
        return Club;
      case 1:
        return Diamond;
      case 2:
        return Heart;
      case 3:
        return Spade;
    }
  }
}

public abstract class Card {
  private boolean available = true;
  
  /*A number or face thats on card - a number 2 through 10, or 11 for Jack, 12 for Queen, 
  13 for King, or 1 for Ace*/
  protected int faceValue;
  protected Suit suit;
  
  public Card(int c, Suit s) {
    faceValue = c;
    suit = s;
  }
  
  public abstract int value();
  public Suit suit() { return suit; }
  
  /*Checks if the card is available to be given out to someone*/
  public boolean isAvailable() { return available; }
  public void markUnavailable() { available = false; }
  public void markAvailable() { available = true; }
}

public class Deck <T extends Card> {
  private ArrayList<T> cards; // all cards, dealt or not
  private int dealtIndex = 0; // marks first undealt card
  
  public void setDeckOfCards(ArrayList<T> deckOfCards) { cards = deckOfCards; }
  
  public void shuffle() { //TODO(); }
  public int remainingCards() { return cards.size() - dealtIndex; }
  public T[] dealHand(int number) { //TODO(); }
  public T dealCard() { //TODO(); }
}

public class Hand <T extends Card> {
  protected ArrayList<T> cards = new ArrayList<T>();
  
  public int score() {
    int score = 0;
    for (T card: cards) {
      score += card.value();
    }
    return score;
  }
  
  public void addCard(T card) { cards.add(card); }
}
```

In the above code, we have implemented Deck with generics but restricted the type of T to Card. We have also implemented Card as an abstract class, since methods like value () don't make much sense without a specific game attached to them. (You could make a compelling argument that they should be implemented anyway, by defaulting to standard poker rules.)

Now, let's say we're building a blackjack game, so we need to know the value of the cards. Face cards are 10 and an ace is 11 (most of the time, but that's the job of the Hand class, not the following class).

```
public class BlackJackCard extends Card {
  public BlackJackCard(int c, Suit s) { super(c, s); }
  
  public int value() {
    if (isAce()) return 1;
    else if (faceValue >= 11 && faceValue <= 13) return 10;
    else return faceValue;
  }
  
  public int minValue() {
    if (isAce()) return 1;
    else return value();
  }
  
  public int maxValue() {
    if (isAce()) return 11;
    else return value();
  }
  
  public boolean isAce() {
    return faceValue == 1;
  }
  
  public boolean isFaceCard() {
    return faceValue >= 11 && faceValue <= 13;
  }
}

public class BlackJackHand extends Hand<BlackJackCard> {
  /*There are multiple possible scores for a blackjack hand, since aces have multiple values. 
  Return the highest possible score that's under 21, or the lowest score that over.*/
  public int score() {
    ArrayList<Integer> scores = possibleScores();
    int maxUnder = Integer.MIN_VALUE;
    int minOver = Integer.MAX_VALUE;
    for (int score: scores) {
      if (score > 21 && score < minOVer) {
        minOver = score;
      } else if (score <= 21 && score > maxUnder) {
        maxUnder = score;
      }
    }
    return maxUnder == Integer.MIN_VALUE ? minOver : maxUnder;
  }
  
  /* return a list of all possible scores this hand could have (evaluating each ace as 1 and 11)*/
  private ArrayList<Integer> possibleScores() { //TODO(); }
  
  public boolean busted() { score() > 21; }
  public boolean is21() { score() == 21; }
  public boolean isBlackJack() { //TODO(); }
}
```

This is just one way of handling aces. We could, alternatively, create a class of type Ace that extends BlackJackCard.
