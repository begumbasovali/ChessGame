
public class Pawn extends Piece {
    private boolean initialLocation = true;

    public Pawn(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {
        Square targetLocation;
        targetLocation = location.getBoard().getSquareAt(to);
        int rowDistance = targetLocation.getRowDistance(location); // moves only vertically

        boolean validMove = false;
        if (this.location.isAtSameColumn(targetLocation)) {
            // Moving forward
            if (color == ChessBoard.WHITE && rowDistance > 0 && rowDistance <= 2) {
                // Two-square move allowed only from initial location
                if (rowDistance == 2 && initialLocation) {
                    Square[] between = location.getBoard().getSquaresBetween(location, targetLocation);
                    validMove =  targetLocation.isEmpty() && between[0].isEmpty();
                }
                // One-square move allowed always
                else if (rowDistance == 1) {
                    validMove = targetLocation.isEmpty();
                }
            } else if (color == ChessBoard.BLACK && rowDistance < 0 && rowDistance >= -2) {
                // Moving two squares forward from initial location
                if (rowDistance == -2 && initialLocation) {
                    Square[] between = location.getBoard().getSquaresBetween(location, targetLocation);
                    validMove =  targetLocation.isEmpty() && between[0].isEmpty();
                }
                //  One-square move allowed always
                else if (rowDistance == -1) {
                    validMove = targetLocation.isEmpty();
                }
            }
        }

        // Attacking diagonally
        else if (this.location.isNeighborColumn(targetLocation)) {
            if (color == ChessBoard.WHITE && rowDistance == 1) {
                validMove =  !targetLocation.isEmpty() && targetLocation.getPiece().getColor() == ChessBoard.BLACK;
            } else if (color == ChessBoard.BLACK && rowDistance == -1) {
                validMove =  !targetLocation.isEmpty() && targetLocation.getPiece().getColor() == ChessBoard.WHITE;
            }
        }

        return validMove;
    }

    @Override
    public void move(String to) {
        Square targetLocation = location.getBoard().getSquareAt(to);
        // Check if pawn reaches last row
        if (targetLocation.isAtLastRow(color)) {
            // Promote to queen if the pawn reaches the last row
            targetLocation.putNewQueen(color);

        } else {
            // Move the pawn to the target location
            targetLocation.setPiece(this);


        }

         //Clear previous location
        location.clear();

        // Update initialLocation flag before updating current location
        initialLocation = false;


        // Update current location and initialLocation flag
        location = targetLocation;


        // Update next player turn
        location.getBoard().nextPlayer();
    }

    @Override
    public String toString() {
        return color == ChessBoard.WHITE ? "P" : "p";
    }

}
