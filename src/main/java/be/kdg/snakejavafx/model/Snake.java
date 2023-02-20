package be.kdg.snakejavafx.model;
import java.util.ArrayList;

public class Snake {

    public enum Orientation {
        UP(new Position(0,-1)),
        DOWN(new Position(0,1)),
        LEFT(new Position(-1,0)),
        RIGHT(new Position(1,0));
        private final Position relativePos;
        Orientation(Position pos){
            relativePos=pos;
        }

        public Position getRelativePos() {
            return relativePos;
        }
    }

    private Orientation orientation;
    private final ArrayList<Position> snakeParts;
    public ArrayList<Position> getSnakePartPositions(){
        return snakeParts;
    }

    public Snake(int size, Position headPosition) {
        snakeParts = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            snakeParts.add(headPosition.plus(new Position(0,i)));
        }
        orientation = Orientation.UP;
    }

    public boolean overlaps(Position pos) {
        for (Position part :
                snakeParts) {
            if (part.equals(pos)) {
                return true;
            }
        }
        return false;
    }

    public void move() {
        snakeParts.remove(snakeParts.size()-1);
        snakeParts.add(0, snakeParts.get(0).plus(orientation.getRelativePos()));
    }

    public Position getNextPosition(){
        return snakeParts.get(0).plus(orientation.getRelativePos());
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public void lengten(){
        snakeParts.add(0, snakeParts.get(0).plus(orientation.getRelativePos()));
    }
}
