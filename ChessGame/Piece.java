public abstract class Piece {
    protected int color;
    protected Square location;

    public Piece(int color, Square location) {
        this.color = color;
        this.location = location;
    }

    public abstract boolean canMove(String to);

    public abstract void move(String to);

    public int getColor() {
        return color;
    }

    public abstract String toString();

}



