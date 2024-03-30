public class Rook extends Piece {

    public Rook(int color, Square location) {
        super(color, location);
    }

    public boolean canMove(String to) {
        Square targetLocation = location.getBoard().getSquareAt(to);
        int rowDistance = targetLocation.getRowDistance(location);
        int colDistance = targetLocation.getColDistance(location);

        // A rook can move only vertically or horizontally
        if (rowDistance == 0 || colDistance == 0) {
            Square[] between = location.getBoard().getSquaresBetween(location, targetLocation);

            // Check if there are any pieces between the current location and the target location
            if (between != null) {
                for (Square sq : between) {
                    if (sq != null && !sq.isEmpty()) {
                        return false;
                    }
                }
            }

            //Check if the target location is either empty or occupied by an opponent's piece
            return targetLocation.isEmpty() || targetLocation.getPiece().getColor() != color;
        } else {
            return false;
        }
    }

    public void move(String to) {
        Square targetLocation = location.getBoard().getSquareAt(to);

        // Move the rook to the target location
        targetLocation.setPiece(this);
        location.clear();
        location = targetLocation;
        location.getBoard().nextPlayer();
    }

    @Override
    public String toString() {
        return color == ChessBoard.WHITE ? "R" : "r";
    }
}

