package be.kdg.snakejavafx.model;

public enum Size {
    S(10,10),M(15,15);

    public int width;
    public int height;

    private Size(int width,int height) {
        this.width = width;
        this.height = height;
    }
}
