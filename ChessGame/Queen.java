

public class Queen extends Piece{

    public Queen(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {
        Square targetLocation = location.getBoard().getSquareAt(to);
        int rowDistance = targetLocation.getRowDistance(location);
        int colDistance = targetLocation.getColDistance(location);

        // A queen can move in any direction: vertically, horizontally, or diagonally.
        // Check if the movement is either vertical, horizontal, or diagonal
        if (rowDistance == 0 || colDistance == 0 || Math.abs(rowDistance) == Math.abs(colDistance)) {
            Square[] between = location.getBoard().getSquaresBetween(location, targetLocation);

            // Check if there are any pieces between the current location and the target location
            for (Square sq : between) {
                if (sq != null && !sq.isEmpty()) {
                    return false;
                }
            }
            // Check if the target location is either empty or occupied by an opponent's piece
            return targetLocation.isEmpty() || targetLocation.getPiece().getColor() != color;
        } else {
            return false;
        }
    }

    @Override
    public void move(String to) {
        Square targetLocation = location.getBoard().getSquareAt(to);
        // Move the queen to the target location
        targetLocation.setPiece(this);
        location.clear();
        location = targetLocation;
        location.getBoard().nextPlayer();
    }
    @Override
    public String toString() {
        return color == ChessBoard.WHITE ? "Q" : "q";
    }

}

