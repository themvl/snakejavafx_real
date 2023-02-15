package be.kdg.snakejavafx.model;

public class GameObject {
    public Type getType() {
        return type;
    }

    public enum Type {
        FOOD,WALL;
    }
    private Position position;
    private Type type;

    public GameObject(Type type){
        this.type = type;
    }
}
