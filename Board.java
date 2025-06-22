import java.util.*;

public class Board {
    private final List<List<Piece>> columns;

    private final int rows;

    /**
     * Constructor to create a board with a specified number of rows and columns.
     * 
     * @param rows    is the number of rows on the board.
     * @param columns is the number of columns on the board.
     */
    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = new ArrayList<>(rows);
        int i;
        for (i = 0; i < columns; i++) {
            this.columns.add(new ArrayList<>());
        }
    }

    /**
     * @return number of rows on the board.
     */
    public int getRows() {
        return rows;
    }

    /**
     * @return number of columns on the board.
     */
    public int getColumns() {
        return this.columns.size();
    }

    /**
     * Gets the piece at the specified coordinates (col, row) on the board.
     * * @param col the column index of the piece.
     * * @param row the row index of the piece.
     * * @return the Piece at the specified coordinates, or null if no piece is
     * present.
     */
    public Piece getPiece(int col, int row) {
        // Check if the coordinates are within bounds
        assert(col >= 0 && col < getColumns());
        assert(row >= 0 && row < getRows());

        List<Piece> column = columns.get(col);

        if (column.size() > row) {
            return column.get(row);
        } else {
            return null;
        }
    }

    /**
     * Moves a piece to the specified column on the board.
     * 
     * @param x      the column index (1-based) where the piece should be placed.
     * @param player the Piece representing the player making the move.
     * @return true if the move was successful and resulted in a win, false
     *         otherwise.
     */
    public boolean move(int x, Piece player) {
        try {
            x -= 1; // Adjust for 0-based index
            List<Piece> column = columns.get(x);
            assert (column.size() >= this.rows);
            column.add(player);
            return checkWin(x, column.size() - 1, player);

        } catch (AssertionError e) {
            System.out.println("Column is full, cannot place piece.");
            return false;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid column index. Please choose a column between 1 and " + getColumns() + ".");
            return false;
        }
    }

    public void printBoard() {
        for (int y = rows - 1; y >= 0; y--) {
            for (int x = 0; x < getColumns(); x++) {
                Piece piece = getPiece(x, y);
                if (piece != null) {
                    System.out.print("| " + piece.getSymbol() + " ");
                } else {
                    System.out.print("|    ");
                }
            }
            System.out.println("|");
        }
        System.out.println("   1    2    3    4    5    6    7");
    }

    private boolean checkLine(int x1, int y1, int xDiff, int yDiff, Piece player) {
        for (int i = 0; i < 4; ++i) {
            int x = x1 + (xDiff * i);
            int y = y1 + (yDiff * i);

            if (x < 0 || x > columns.size() - 1) {
                return false;
            }

            if (y < 0 || y > rows - 1) {
                return false;
            }

            if (player != getPiece(x, y)) {
                return false;
            }
        }

        return true;
    }

    private boolean checkWin(int x, int y, Piece player) {
        // Vertical line
        if (checkLine(x, y, 0, -1, player)) {
            return true;
        }

        for (int offset = 0; offset < 4; ++offset) {
            // Horizontal line
            if (checkLine(x - 3 + offset, y, 1, 0, player)) {
                return true;
            }

            // Leading diagonal
            if (checkLine(x - 3 + offset, y + 3 - offset, 1, -1, player)) {
                return true;
            }

            // Trailing diagonal
            if (checkLine(x - 3 + offset, y - 3 + offset, 1, 1, player)) {
                return true;
            }
        }

        return false;
    }

}
