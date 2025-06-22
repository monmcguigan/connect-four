/**
 * Enum representing the pieces in MyConnectFour game.
 * Each piece has an associated character symbol.
 */
public enum Piece {
    PLAYER_1("🚚"),
    PLAYER_2("🌻");

    private final String symbol;

    /**
     * Constructor for a Piece with the specified symbol.
     * 
     * @param symbol the character symbol representing the piece
     */
    Piece(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns the symbol associated with the piece.
     * 
     * @return the character symbol of the piece
     */
    public String getSymbol() {
        return symbol;
    }
}
