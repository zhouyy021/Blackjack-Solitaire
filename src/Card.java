public class Card {
    private int rank;
    private char suit;

    public Card(int rank, char suit) {
        if (rank < 1 || rank > 9) {
            throw new IllegalArgumentException();
        }
        if ("HDCS".indexOf(suit) < 0) {
            throw new IllegalArgumentException();
        }
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }
    public char getSuit() {
        return suit;
    }
    public boolean isAce(){
        return rank == 1;
    }
    public boolean isFace(){
        return rank == 11 || rank == 12 || rank == 13;
    }
}
