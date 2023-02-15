package be.kdg.snakejavafx.model;

import javafx.geometry.Pos;

import java.util.Scanner;

public class Game {
    //todo player and highscore logic
    public static void main(String[] args) {
        Level level = new Level(Size.S, Difficulty.EASY);
        Scanner scanner = new Scanner(System.in);

        String input="";
        do{
            System.out.println(level.toString());
            System.out.println("Type, u,d,l,r to give directions.");
            input = scanner.nextLine();
            if (input.length() > 0){
                input = input.substring(0,1);
            }
            System.out.println("input = " + input);

            switch (input){
                case "u":
                    level.changeSnakeDirection(Snake.Orientation.UP);
                    break;
                case "d":
                    level.changeSnakeDirection(Snake.Orientation.DOWN);
                    break;
                case "l":
                    level.changeSnakeDirection(Snake.Orientation.LEFT);
                    break;
                case "r":
                    level.changeSnakeDirection(Snake.Orientation.RIGHT);
                    break;
            }

            if (!level.moveSnake()){
                System.out.println("You hit a wall! you lost!");
                break;
            }
        }while(!input.equals("q"));
    }
}
