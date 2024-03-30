public class Bishop extends Piece {

    public Bishop(int color, Square location) {
        super(color, location);
    }

    public boolean canMove(String to) {

        Square targetLocation = location.getBoard().getSquareAt(to);
        int rowDistance = Math.abs(targetLocation.getRowDistance(location));
        int colDistance = Math.abs(targetLocation.getColDistance(location));
        // Print the row and column distances for debugging
        //System.out.println("Row Distance: " + rowDistance);
        //System.out.println("Col Distance: " + colDistance);

        // a bishop can move only diagonally, and it cannot move onto a square occupied by a piece of the same color.
        if (rowDistance == colDistance ) {
            Square[] between = location.getBoard().getSquaresBetween(location, targetLocation);
            // Check for obstructions along the diagonal path
            if (between != null) {
                for (Square sq : between) {
                    if (sq != null && !sq.isEmpty()) {
                        return false;
                    }
                }
            }

            return targetLocation.isEmpty() || targetLocation.getPiece().getColor() != color;
        } else {
            return false;
        }
    }


    public void move(String to) {
        Square targetLocation = location.getBoard().getSquareAt(to);
        targetLocation.setPiece(this);
        location.clear();
        location = targetLocation;
        location.getBoard().nextPlayer();
    }
    public String toString() {
        return color == ChessBoard.WHITE ? "B" : "b";
    }
}
