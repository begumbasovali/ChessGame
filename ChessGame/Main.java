import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        ChessBoard board = new ChessBoard();
        board.printBoard();

        Piece capturedPiece = null;

        // Game loop until the game is ended
        while (!board.isGameEnded()) {
            System.out.println("It is " + (board.isWhitePlaying() ? "White" : "Black") + "'s turn");
            Piece piece;

            do {
                System.out.print("Enter the location of the piece:");
                String from = reader.next();
                piece = board.getPieceAt(from);
                if (piece == null) {
                    System.out.println("There is no piece at this location, please try again.");
                } else if (piece.getColor() != (board.isWhitePlaying() ? ChessBoard.WHITE : ChessBoard.BLACK)) {
                    System.out.println("It is not " + (board.isWhitePlaying() ? "Black" : "White") + "'s turn, please try again.");
                    piece = null;
                }
            } while (piece == null);

            String to;
            do {
                System.out.print("Enter the new location of the piece:");
                to = reader.next();
                if (!piece.canMove(to)) {
                    System.out.println("Invalid move. Please try again.");
                }
            } while (!piece.canMove(to));
            // Capture the piece at the target location, if any
            Piece targetPiece = board.getPieceAt(to);
            if (targetPiece != null) {
                capturedPiece = targetPiece;
                System.out.println("Captured piece: " + capturedPiece);
            }
            // Move the piece to the new location
            piece.move(to);
            board.printBoard();


        }
        System.out.println("Game Over!");
        System.out.println((board.isWhitePlaying() ? "Black" : "White" )+ " wins!");
        if (capturedPiece != null) {
            System.out.println("Captured piece: " + capturedPiece);
        }

        reader.close(); // Close the scanner outside the loop
    }
}








