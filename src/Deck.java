import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private final ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();

        String[] suits = {"C", "D", "H", "S"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for (String suit : suits) {
            for (String rank : ranks) {
                this.cards.add(new Card(rank, suit));
            }
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    public Card draw() {
        if (this.cards.isEmpty()) {
            return null;
        }
        return this.cards.remove(0);
    }
}
