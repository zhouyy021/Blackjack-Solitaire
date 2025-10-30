public class Card {
    private final String rank;
    private final String suit;
    private final int value;

    // create a card with the given rank and suit
    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;

        switch (rank) {
            case "A":
                this.value = 1;
                break;
            case "J":
            case "Q":
            case "K":
                this.value = 10;
                break;
            default:
                this.value = Integer.parseInt(rank);
                break;
        }
    }

    public int getValue() {
        return this.value;
    }

    public boolean isAce() {
        return this.rank.equals("A");
    }

    public String toString() {
        return this.rank + " " + this.suit;
    }
}
