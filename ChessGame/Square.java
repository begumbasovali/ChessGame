public class Square {
    private final int row;
    private final int col;
    private Piece piece;
    private ChessBoard board;

    // Constructor to initialize the Square with its row, column, and ChessBoard
    public Square(int row, int col, ChessBoard board) {
        this.row = row;
        this.col = col;
        this.piece = null;
        this.board = board;
    }

    public int getRow() { // Get the row of the Square
        return row;
    }

    public int getCol() { // Get the column of the Square
        return col;
    }

    public Piece getPiece() { // Get the Piece located on the Square
        return piece;
    }

    public void setPiece(Piece piece) { // Set the Piece on the Square
        this.piece = piece;
    }
    public void clear() {  // Clear the Square by removing the Piece from it
        this.piece = null;
    }

    public boolean isEmpty() { // Check if the Square is empty (no Piece on it)
        return piece == null;
    }

    public boolean isAtLastRow(int color) {  // Check if the Square is at the last row based on the color of the Piece
        if(color == ChessBoard.WHITE) {
            return row == 0;
        } else { // Black color
            return row == 7;
            // Implement check for last row based on color
        }
    }
    public boolean isAtSameColumn(Square s) { // Check if the Square is in the same column as another Square
        return this.col == s.getCol();
    }
    public boolean isNeighborColumn(Square s) { // Check if the Square is a neighbor column of another Square
        return Math.abs(col - s.getCol()) == 1;
    }
    public int getRowDistance(Square s) { // Get the distance between the rows of two Squares
        return s.getRow() - row;
    }
    public int getColDistance(Square s) { // Get the distance between the columns of two Squares
        return Math.abs(col - s.getCol());
    }
    public ChessBoard getBoard() { // Get the ChessBoard the Square belongs to
        return board;
    }



    @Override
    public String toString() { // Return a string representation of the Square
        if (piece == null) {
            return " ";
        } else {
            return piece.toString();
        }
    }

    public void putNewQueen(int color) {  // Put a new Queen piece on the Square with the specified color
        // Create a new Queen piece and assign it to the square
        Queen queen = new Queen(color, this);
        setPiece(queen);
    }


}

