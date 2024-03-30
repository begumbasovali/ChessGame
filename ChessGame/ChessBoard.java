public class ChessBoard {
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    private Square[][] board;   // the 64 squares of the board
    private boolean whitePlaying; // indicates if it's currently white player's turn



    public ChessBoard() {  // constructor
        board = new Square[8][8];
        whitePlaying = true;
        initialize();
    }

    // initializes the chess board with pieces in their starting positions
    private void initialize() { // method
        // initialize the board with empty squares
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = new Square(row, col, this);
            }
        }
        // set up black pieces
        board[0][0].setPiece(new Rook(BLACK, board[0][0]));
        board[0][1].setPiece(new Knight(BLACK, board[0][1]));
        board[0][2].setPiece(new Bishop(BLACK, board[0][2]));
        board[0][3].setPiece(new Queen(BLACK, board[0][3]));
        board[0][4].setPiece(new King(BLACK, board[0][4]));
        board[0][5].setPiece(new Bishop(BLACK, board[0][5]));
        board[0][6].setPiece(new Knight(BLACK, board[0][6]));
        board[0][7].setPiece(new Rook(BLACK, board[0][7]));
        for (int col = 0; col < 8; col++) {
            board[1][col].setPiece(new Pawn(BLACK, board[1][col]));
        }
        // set up white pieces
        board[7][0].setPiece(new Rook(WHITE, board[7][0]));
        board[7][1].setPiece(new Knight(WHITE, board[7][1]));
        board[7][2].setPiece(new Bishop(WHITE, board[7][2]));
        board[7][3].setPiece(new Queen(WHITE, board[7][3]));
        board[7][4].setPiece(new King(WHITE, board[7][4]));
        board[7][5].setPiece(new Bishop(WHITE, board[7][5]));
        board[7][6].setPiece(new Knight(WHITE, board[7][6]));
        board[7][7].setPiece(new Rook(WHITE, board[7][7]));
        for (int col = 0; col < 8; col++) {
            board[6][col].setPiece(new Pawn(WHITE, board[6][col]));
        }
    }

    public boolean isWhitePlaying() {
        return whitePlaying;
    }

    public void nextPlayer() { // Switch the current player to the next player
        whitePlaying = !whitePlaying;
    }

    public boolean isGameEnded() {
        //End game control : you can end the game when no pieces exist from one color
        int numWhitePieces = 0;
        int numBlackPieces = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col].getPiece(); // Get the piece at the current square

                if (piece != null) { // If a piece exists at the square
                    if (piece.getColor() == WHITE) { // If the piece is white
                        numWhitePieces++; // Increment the counter for white pieces
                    } else { // If the piece is black
                        numBlackPieces++; // Increment the counter for black pieces
                    }
                }
            }
        }
        // Return true if either no white pieces or no black pieces remain
        return numWhitePieces == 0 || numBlackPieces == 0;
    }

    public Square getSquareAt(String location) {
        int col = location.charAt(0) - 'a'; // Convert the letter to column index
        int row = 8 - (location.charAt(1) - '0'); // Convert the number to row index
        return board[row][col]; // Return the square at the specified location
    }

    public Piece getPieceAt(String location) {
        // Retrieve the square at the specified location
        Square square = getSquareAt(location);
        // Return the piece on the square
        return square.getPiece();
    }


    public Square[] getSquaresBetween(Square location, Square targetLocation) {
        // Calculate the difference in row and column indices between the target and starting squares
        int rowDiff = targetLocation.getRow() - location.getRow();
        int colDiff = targetLocation.getCol() - location.getCol();

        // Calculate the direction of the movement
        int rowDir = Integer.compare(rowDiff, 0);
        int colDir = Integer.compare(colDiff, 0);

        // Calculate the number of steps needed to reach the target square
        int steps = Math.max(Math.abs(rowDiff), Math.abs(colDiff)) ;

        // Create an array to store the squares between the starting and target squares
        Square[] between = new Square[steps-1];

        for (int i = 0; i < steps-1; i++) {
            // Calculate the row and column indices of the squares between location and targetLocation
            int row = location.getRow() + rowDir * (i+1);
            int col = location.getCol() + colDir * (i+1) ;
            // Assign the corresponding square from the board to the between array
            between[i] = board[row][col];
        }
        return between;
    }

    public void printBoard() { // current state of board
        System.out.println("    A   B   C   D   E   F   G   H"); // Print the column labels
        System.out.println("   -------------------------------");
        for (int row = 0; row < 8; ++row) {
            System.out.print(8 - row + " ");
            for (int col = 0; col < 8; ++col) {
                System.out.print("| " + board[row][col].toString() + " "); // Print the piece at each square

            }
            System.out.print("| " + (8 - row)); // Print the row number on the right side
            System.out.println();
            System.out.println("   -------------------------------");
        }
        System.out.println("    A   B   C   D   E   F   G   H");
    }


}












