import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyConnectFour {

    private BufferedReader input;

    Board board;

    /**
     * Constructor to initialize the MyConnectFour game.
     * It sets up the game board and starts the game.
     */
    public MyConnectFour() {
        board = new Board(6, 7);
        input = new BufferedReader(new InputStreamReader(System.in));
        playGame();
    }

    // Prints starting intructions for the game.
    private void printInstructions() {
        System.out.println("Welcome to Connect 4");
        System.out.println("There are 2 players red and yellow");
        System.out.println("Player 1 is Red, Player 2 is Yellow");
        System.out.println("To play the game type in the number of the column you want to drop you counter in");
        System.out.println("A player wins by connecting 4 counters in a row - vertically, horizontally or diagonally");
        System.out.println("");
    }

    // Prints a congraulations message when a player has won.
    private void printCongratulations(Piece player) {
        System.out.println("You have won " + player.getSymbol() + "!!!");
    }

    /**
     * Reads user input from the console and converts it to an integer.
     * If the input is invalid, it prompts the user to enter a valid number.
     * 
     * @return the integer input from the user
     */
    private int getUserInput() {
        try {
            String strInput = input.readLine();
            int intInput = Integer.parseInt(strInput);
            return intInput;
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return getUserInput();
        } catch (IOException e) {
            System.out.println("An error occurred while reading input. Please try again.");
            return getUserInput();
        }
    }

    /**
     * Main method to play the MyConnectFour game.
     */
    private void playGame() {
        printInstructions();
        board.printBoard();
        boolean hasWon = false;
        while (!hasWon) {
            // Player 1
            hasWon = board.move(getUserInput(), Piece.PLAYER_1);
            board.printBoard();
            if (hasWon) {
                printCongratulations(Piece.PLAYER_1);
            } else {
                // Player 2
                hasWon = board.move(getUserInput(), Piece.PLAYER_2);
                board.printBoard();
                if (hasWon) {
                    printCongratulations(Piece.PLAYER_2);
                }
            }

        }
    }
}