package be.kdg.snakejavafx.model;

import javafx.geometry.Pos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Level {
    private Size size;
    private int score;
    private Difficulty difficulty;
    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private HashMap<Position,GameObject> gameObjects;

    private Random random;

    private Snake snake;

    public Level(Size size, Difficulty difficulty) {
        this.size = size;
        this.difficulty = difficulty;

        startTime = LocalDateTime.now();

        random = new Random();

        gameObjects = new HashMap<>();
        snake = new Snake(3, new Position(size.width / 2, size.height / 2));

        spawnObject(GameObject.Type.FOOD);
        spawnObject(GameObject.Type.WALL);

    }

    @Override
    public String toString() {
        String string = "";
        string += "# #".repeat(size.width) + "\n";
        for (int y = 0; y < size.width; y++) {
            string += "#";
            for (int x = 0; x < size.height; x++) {
                string += " ";

                if (snake.overlaps(new Position(x, y))) {
                    string += "x";
                } else {
                    if (gameObjects.keySet().contains(new Position(x,y))) {
                        switch (gameObjects.get(new Position(x,y)).getType()) {
                            case WALL:
                                string += "#";
                                break;
                            case FOOD:
                                string += "0";
                                break;
                        }
                    }else {
                        string += " ";
                    }
                }
                string += " ";
            }
            string += "#\n";
        }
        string += "# #".repeat(size.width) + "\n";
        return string;
    }

    public static void main(String[] args) {
        Level level = new Level(Size.M, Difficulty.EASY);
        System.out.println(level.toString());
    }

    public void spawnObject(GameObject.Type type) {
        // dont spawn object on top of snake so retry until you get a non overlapping position
        Position pos;

        do {
            pos = new Position(
                    random.nextInt(size.height),
                    random.nextInt(size.width));
        } while (positionTaken(pos));

        gameObjects.put(pos, new GameObject(type));
    }

    // return false if hits wall or dies and true if it succeeds.
    public boolean moveSnake() {
        Position pos = snake.getNextPosition();
        if (pos.x >= size.width || pos.y >= size.height || pos.x < 0 || pos.y < 0) {
            return false;
        }

        if (gameObjects.keySet().contains(pos)) {
            switch (gameObjects.get(pos).getType()) {
                case WALL:
                    return false;
                case FOOD:
                    //todo increase score and make snake longer.
            }
        }

        snake.move();
        return true;
    }

    public void changeSnakeDirection(Snake.Orientation orientation) {
        snake.setOrientation(orientation);
    }

    public boolean positionTaken(Position pos){
        if(snake.overlaps(pos)){
            return true;
        }

        if(gameObjects.keySet().contains(pos)) {
            return true;
        }

        return false;
    }
}
