import java.util.Scanner;

public class BlackjackSolitaire {

    private  final Deck deck;
    private  final Card[] grid;
    private int discards_remain;
    private int cards_placed;
    private final Scanner scanner;

    private final int[][] hands = new int[][] {
            // 4 rows and 5 columns
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 10},
            {11, 12, 13},
            {14, 15, 16},

            {1, 6},
            {2, 7},
            {3, 8, 11, 14},
            {4, 9, 12, 15},
            {5, 10, 13, 16}
    };

    public BlackjackSolitaire() {
        deck = new Deck();
        scanner = new Scanner(System.in);
        grid = new Card[17];
        discards_remain = 4;
        cards_placed = 0;
    }

//
    public int play() {
        while (cards_placed < 16) {
            Card drawn = deck.draw();
            displayTheGrid();
            System.out.println("Card drawn: " + drawn);
            System.out.println("Discards remaining: " + discards_remain);

            int spot = getPlayerInput();
            if (spot >= 1 && spot <= 16) {
                grid[spot] = drawn;
                cards_placed++;
            } else {
                discards_remain--;
            }
        }

        int total = getTotalScore();
        System.out.println("Game over! You scored " + total + " points.");
        displayTheGrid();
        return total;
    }

    private int getPlayerInput() {
        while (true) {
            System.out.println("Please enter a number between 1 and 16 to place the card or between 17 and 20 to discard.");

            if (!scanner.hasNextInt()) {
                String wrongInput = scanner.next();
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
            int spot = scanner.nextInt();

            if (spot < 1 || spot > 20) {
                System.out.println("Please enter a number between 1 and 20.");
                continue;
            }

            if (spot >= 1 && spot <= 16) {
                if (grid[spot] != null) {
                    System.out.println("Card already placed at this spot, please try another spot.");
                    continue;
                }
            }

            if (spot >= 17 && spot <= 20) {
                if (discards_remain == 0) {
                    System.out.println("No discards remaining, please try to place this card in the grid.");
                    continue;
                }
            }
            return spot;
        }
    }
        private void displayTheGrid () {
            System.out.println();
            System.out.printf("%-4s%-4s%-4s%-4s%-4s%n",
                    getString(1), getString(2), getString(3), getString(4), getString(5));
            System.out.printf("%-4s%-4s%-4s%-4s%-4s%n",
                    getString(6), getString(7), getString(8), getString(9), getString(10));
            System.out.printf("%-4s%-4s", "", "");
            System.out.printf("%-4s%-4s%-4s%n",
                    getString(11), getString(12), getString(13));
            System.out.printf("%-4s%-4s", "", "");
            System.out.printf("%-4s%-4s%-4s%n",
                    getString(14), getString(15), getString(16));
            System.out.println();
        }

        private String getString (int index){
            if (grid[index] == null) {
                return Integer.toString(index);
            } else {
                return grid[index].toString();
            }
        }

        private int getTotalScore () {
            int total = 0;
            for (int[] hand : hands) {
                total += scoreHand(hand);
            }
            return total;
        }

        private int scoreHand ( int[] indices) {
            int sum = 0;
            int aces = 0;
            int numCards = 0;

            for (int index : indices) {
                Card card = grid[index];

                if (card != null) {
                    numCards++;
                    sum += card.getValue();
                    if (card.isAce()) {
                        aces++;
                    }
                }
            }
            while (sum <=11 && aces > 0) {
                sum += 10;
                aces--;
            }

            if (sum > 21) {
                return 0;
            } else if (sum == 21) {
                if (numCards == 2) {
                    return 10;
                } else {
                    return 7;
                }
            } else if (sum == 20) {
                return 5;
            } else if (sum == 19) {
                return 4;
            } else if (sum == 18) {
                return 3;
            } else if (sum == 17) {
                return 2;
            } else {
                return 1;
            }
        }
    }