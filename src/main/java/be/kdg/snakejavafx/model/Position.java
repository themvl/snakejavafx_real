package be.kdg.snakejavafx.model;

public class Position{
    public int x;
    public int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Position plus(Position pos){
        return new Position(x+ pos.x,y+ pos.y);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position)) {
            return false;
        }

        if (x != ((Position)obj).x) {
            return false;
        }
        if (y != ((Position)obj).y) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "("+x+","+y+")";
    }
}
