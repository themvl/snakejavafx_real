package be.kdg.snakejavafx.model;

public enum Difficulty {
    EASY(1), MEDIUM(2),HARD(3);
    private final int level;

    private Difficulty(int level) {
        this.level = level;
    }

    int getLevel() {
        return level;
    }
}
