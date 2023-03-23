package be.kdg.snakejavafx.model;

public enum Size {
    S(10,10),M(15,15),L(20,20);

    public final int width;
    public final int height;

    Size(int width,int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
