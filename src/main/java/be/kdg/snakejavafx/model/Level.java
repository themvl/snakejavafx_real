package be.kdg.snakejavafx.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Random;

public class Level {
    private final HashMap<Position,GameObject> gameObjects;

    private final Random random;
    private final Size size;

    private Snake snake;

    private final Highscore highscore;

    public Snake getSnake() {
        return snake;
    }

    public Level(Size size, Difficulty difficulty, String playerName) {

        highscore = new Highscore();
        highscore.levelSize = size;
        this.size = size;
        highscore.difficulty = difficulty;
        highscore.startTime = LocalDateTime.now();

        highscore.playerName = playerName;

        random = new Random();

        gameObjects = new HashMap<>();
        spawnNewGameObjects();

    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("# #".repeat(size.width)).append("\n");
        for (int y = 0; y < size.width; y++) {
            string.append("#");
            for (int x = 0; x < size.height; x++) {
                string.append(" ");

                if (snake.overlaps(new Position(x, y))) {
                    string.append("x");
                } else {
                    if (gameObjects.containsKey(new Position(x,y))) {
                        switch (gameObjects.get(new Position(x, y)).getType()) {
                            case WALL -> string.append("#");
                            case FOOD -> string.append("0");
                        }
                    }else {
                        string.append(" ");
                    }
                }
                string.append(" ");
            }
            string.append("#\n");
        }
        string.append("# #".repeat(size.width)).append("\n");
        string.append(getHighscore().playerName).append("'s score: ").append(highscore.score).append("\n");
        return string.toString();
    }

    public static void main(String[] args) {
        Level level = new Level(Size.M, Difficulty.EASY, "default");
        System.out.println(level);
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

        if (gameObjects.containsKey(pos)) {
            switch (gameObjects.get(pos).getType()) {
                case WALL -> {
                    return false;
                }
                case FOOD -> {
                    highscore.score += 100;
                    snake.lengten();
                    spawnObject(GameObject.Type.FOOD);
                    gameObjects.remove(pos);
                    return true;
                }
            }
        }

        if(snake.overlaps(pos)) {
            return false;
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

        return gameObjects.containsKey(pos);
    }

    public Highscore getHighscore(){
        highscore.endTime = LocalDateTime.now();
        return highscore;
    }

    public HashMap<Position, GameObject> getGameObjects(){
        return  gameObjects;
    }

    private void spawnNewGameObjects() {
        snake = new Snake(4, new Position(size.width / 2, size.height / 2));

        spawnObject(GameObject.Type.FOOD);
        spawnObject(GameObject.Type.WALL);
        switch (highscore.difficulty) {
            case EASY ->  spawnObject(GameObject.Type.WALL);
            case MEDIUM -> {
                spawnObject(GameObject.Type.WALL);
                spawnObject(GameObject.Type.WALL);
            }
            case HARD -> {
                spawnObject(GameObject.Type.WALL);
                spawnObject(GameObject.Type.WALL);
                spawnObject(GameObject.Type.WALL);
            }
        }
    }
    public void reset() {
        highscore.score = 0;
        highscore.startTime = LocalDateTime.now();
        snake = new Snake(4, new Position(size.width / 2, size.height / 2));

        gameObjects.clear();
        spawnNewGameObjects();
    }

}
