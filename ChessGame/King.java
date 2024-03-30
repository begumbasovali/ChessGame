public class King extends Piece {
    public King(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {
        Square targetLocation = location.getBoard().getSquareAt(to);
        int rowDistance = targetLocation.getRow() - location.getRow();
        int colDistance = targetLocation.getCol() - location.getCol();
        // A king can move one square in any direction: vertically, horizontally, or diagonally.
        // Check if the movement is within the range of one square
        if (Math.abs(rowDistance) <= 1 && Math.abs(colDistance) <= 1) {
            // Check if the target location is either empty or occupied by an opponent's piece
            return targetLocation.isEmpty() || targetLocation.getPiece().getColor() != color;
        } else {
            return false;
        }
    }
    @Override
    public void move(String to) {
        Square targetLocation = location.getBoard().getSquareAt(to);
        // Move the king to the target location
        targetLocation.setPiece(this);
        location.clear();
        location = targetLocation;
        location.getBoard().nextPlayer();
    }

    @Override
    public String toString() {
        return color == ChessBoard.WHITE ? "K" : "k";
    }

}
