package be.kdg.snakejavafx.model;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String playerName;
        do {
            System.out.println("please enter your name.");
            playerName = scanner.nextLine();
            playerName = playerName.strip();
        }while (playerName.isEmpty());

        Level level = new Level(Size.S, Difficulty.EASY, playerName);

        String input;
        do{
            System.out.println(level);

            System.out.println("Type, u,d,l,r to give directions.");
            input = scanner.nextLine();
            if (input.length() > 0){
                input = input.substring(0,1);
            }
            System.out.println("input = " + input);
            switch (input) {
                case "u" -> level.changeSnakeDirection(Snake.Orientation.UP);
                case "d" -> level.changeSnakeDirection(Snake.Orientation.DOWN);
                case "l" -> level.changeSnakeDirection(Snake.Orientation.LEFT);
                case "r" -> level.changeSnakeDirection(Snake.Orientation.RIGHT);
            }

            if (!level.moveSnake()){
                System.out.println("You hit a wall! you lost!");
                break;
            }
        }while(!input.equals("q"));
        
        Highscore highscore = level.getHighscore();


        System.out.printf("%s's score is: %d", highscore.playerName, highscore.score);
    }
}
