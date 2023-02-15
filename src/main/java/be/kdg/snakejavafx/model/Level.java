package be.kdg.snakejavafx.model;

import javafx.geometry.Pos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class Level {
    private Size size;
    private int score;
    private Difficulty difficulty;
    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private GameObject[][] gameObjects;

    private  Random random;

    private Snake snake;
    public Level(Size size, Difficulty difficulty) {
        this.size = size;
        this.difficulty = difficulty;

        startTime = LocalDateTime.now();

        random = new Random();

        gameObjects = new GameObject[size.width][size.height];
        snake = new Snake(3, new Position(size.width/2, size.height/2));

        spawnObject(GameObject.Type.FOOD);
        spawnObject(GameObject.Type.WALL);

    }

    @Override
    public String toString() {
        String string = "";
        string += "# #".repeat(size.width)+"\n";
        for (int y = 0; y < size.width; y++) {
            string+="#";
            for (int x = 0; x < size.height; x++) {
                string+= " ";

                if (snake.overlaps(new Position(x,y))){
                    string+= "x";
                } else {
                    if (gameObjects[x][y] == null) {
                        string+=" ";
                    }else if (gameObjects[x][y].getType() == GameObject.Type.WALL){
                        string+="#";
                    } else if (gameObjects[x][y].getType() == GameObject.Type.FOOD) {
                        string +="O";
                    }
                }
                string+=" ";
            }
            string+="#\n";
        }
        string += "# #".repeat(size.width)+"\n";
        return string;
    }

    public static void main(String[] args) {
        Level level = new Level(Size.M, Difficulty.EASY);
        System.out.println(level.toString());
    }

    public void spawnObject(GameObject.Type type){
        // dont spawn object on top of snake so retry until you get a non overlapping position
        Position pos;

        do{
            pos = new Position(
                    random.nextInt(size.height),
                    random.nextInt(size.width));
        }while (snake.overlaps(pos));

        gameObjects[pos.x][pos.y] = new GameObject(type);
    }

    // return false if hits wall or dies and true if it succeeds.
    public boolean moveSnake() {
        Position pos = snake.getNextPosition();
        if (pos.x >= size.width || pos.y >= size.height || pos.x < 0 || pos.y <0) {
            return false;
        }

        if(gameObjects[pos.x][pos.y] != null){
            switch (gameObjects[pos.x][pos.y].getType()){
                case WALL: return false;
                case FOOD:
                    //todo increase score and make snake longer.
            }
        }

        snake.move();
        return true;
    }

    public void changeSnakeDirection(Snake.Orientation orientation){
        snake.setOrientation(orientation);
    }
}
