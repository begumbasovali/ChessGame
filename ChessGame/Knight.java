
public class Knight extends Piece {
    public Knight(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {
        Square targetLocation = location.getBoard().getSquareAt(to);
        int rowDistance = Math.abs(targetLocation.getRowDistance(location));
        int colDistance = Math.abs(targetLocation.getColDistance(location));

         /** A knight can move in an L-shape: 2 squares in one direction and 1 square in a perpendicular direction.
         * There are two possible L-shape movements for a knight either two squares vertically and one square horizontally,
         * or two squares horizontally and one square vertically, and it cannot move onto a square occupied
         * by a piece of the same color.
         * Check if the movement matches either of the two L-shape movements
         */

        if ((rowDistance == 2 && colDistance == 1) || (rowDistance == 1 && colDistance == 2)) {
            // Check if the target location is either empty or occupied by an opponent's piece
            return targetLocation.isEmpty() || targetLocation.getPiece().getColor() != color;
        } else {
            return false;
        }

    }
    @Override
    public void move(String to) {
        Square targetLocation = location.getBoard().getSquareAt(to);        // Move the knight to the target location
        targetLocation.setPiece(this);
        location.clear();
        location = targetLocation;
        location.getBoard().nextPlayer();
    }
    @Override
    public String toString() {
        return color == ChessBoard.WHITE ? "N" : "n";
    }
}
