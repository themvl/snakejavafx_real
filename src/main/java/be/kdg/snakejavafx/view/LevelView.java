package be.kdg.snakejavafx.view;
import be.kdg.snakejavafx.model.GameObject;
import be.kdg.snakejavafx.model.Position;
import be.kdg.snakejavafx.model.Size;
import be.kdg.snakejavafx.model.Snake;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.HashMap;

public class LevelView  extends StackPane{
    private Size size;

    private ArrayList<Position> snakeParts;
    private static final double OFFSET = 20;
    private Snake.Orientation direction;
    private final Canvas canvas = new Canvas();

    private HashMap<Position,GameObject> gameObjects;
    private double snakeMovetime = 0;
    private final double FRAME_TIME;

    public LevelView(Size size, double FRAME_TIME) {
        this.size = size;
        getChildren().add(canvas);
        snakeParts = new ArrayList<>();
        direction = Snake.Orientation.UP;
        gameObjects = new HashMap<>();
        this.FRAME_TIME = FRAME_TIME;
    }

    public void setSnakeMoveTime(double time) {
        this.snakeMovetime = time;
    }
    public void setSize(Size size) {
        this.size = size;
    }

    public void setSnakeParts(ArrayList<Position> snakeParts){
        this.snakeParts = snakeParts;
    }

    public void setDirection(Snake.Orientation direction) {
        this.direction = direction;
    }

    public void setGameObjects(HashMap<Position,GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public void draw() {
        double square_size = getSquareSize();
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        //set lines
        g.setStroke(Color.BLACK);
        g.setLineWidth(1);

        //draw grid
        for (int x = 0; x <= size.width; x++) {
            g.strokeLine(OFFSET+x*square_size, OFFSET, OFFSET+x*square_size, OFFSET+size.height*square_size);
        }

        for (int y = 0; y <= size.height; y++) {
            g.strokeLine( OFFSET,OFFSET+y*square_size, OFFSET+size.height*square_size, OFFSET+y*square_size);
        }

        //draw objects
        gameObjects.forEach((pos, obj) -> {
            switch (obj.getType()) {
                case WALL -> g.setFill(Color.BLACK);
                case FOOD -> g.setFill(Color.GREEN);
            }
            g.fillRect(pos.x*square_size+OFFSET, pos.y*square_size+OFFSET,
                    square_size,square_size);
        });

        //draw player
        g.setFill(Color.RED);
        for (int i = 1; i < snakeParts.size()-1; i++) {
            Position pos = snakeParts.get(i);
            g.fillRect(pos.x*square_size+OFFSET,
                    pos.y*square_size+OFFSET,
                    square_size,square_size);
        }

        //draw snake mouth
        g.setFill(Color.PURPLE);
        drawAnimatedInDirection(direction, Color.RED, snakeParts.get(0), false);

        Position tail = new Position(snakeParts.get(snakeParts.size()-1).x-snakeParts.get(snakeParts.size()-2).x,
                snakeParts.get(snakeParts.size()-1).y- snakeParts.get(snakeParts.size()-2).y);

        Snake.Orientation direction;
        if (tail.x > 0) {
            direction = Snake.Orientation.RIGHT;
        }else if (tail.x < 0) {
            direction = Snake.Orientation.LEFT;
        } else if (tail.y < 0) {
            direction = Snake.Orientation.UP;
        } else {
            direction = Snake.Orientation.DOWN;
        }

        drawAnimatedInDirection(direction, Color.RED, snakeParts.get(snakeParts.size()-1), true);


//        Position pos = snakeParts.get(0);
//
//        g.setFill(Color.BLACK);
//        g.fillOval(pos.x*square_size+OFFSET+square_size/2-square_size*0.1+offset.x*square_size*0.5,
//                pos.y*square_size+OFFSET+square_size/2-square_size*0.1+offset.y*square_size*0.5,
//                square_size*0.2, square_size*0.2);


    }

    private double getSquareSize() {
        return (getSmallestDimension()-2*OFFSET)/size.width;
    }

    private double getSmallestDimension(){
        return Math.min(canvas.getWidth(), canvas.getHeight());
    }

    private void drawAnimatedInDirection(Snake.Orientation direction, Color color, Position pos, boolean reverse){
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setFill(color);
        double square_size = getSquareSize();
        double factor = (snakeMovetime/FRAME_TIME)*square_size;

        if (reverse) {
            factor = square_size-factor;
        }

        switch (direction){
            case LEFT -> g.fillRect(pos.x*square_size+OFFSET-factor+square_size,
                    pos.y*square_size+OFFSET,
                    factor,
                    square_size);
            case RIGHT -> g.fillRect(pos.x*square_size+OFFSET,
                    pos.y*square_size+OFFSET,
                    factor,
                    square_size);
            case UP -> g.fillRect(pos.x*square_size+OFFSET,
                    pos.y*square_size+OFFSET-factor+square_size,
                    square_size,
                    factor);
            case DOWN -> g.fillRect(pos.x*square_size+OFFSET,
                    pos.y*square_size+OFFSET,
                    square_size,
                    factor);
        }
    }

    @Override
    protected void layoutChildren() {
        final int top = (int)snappedTopInset();
        final int right = (int)snappedRightInset();
        final int bottom = (int)snappedBottomInset();
        final int left = (int)snappedLeftInset();
        final int w = (int)getWidth() - left - right;
        final int h = (int)getHeight() - top - bottom;
        double smallest = Math.min(w, h);

        canvas.setLayoutX(left);
        canvas.setLayoutY(top);
        setAlignment(Pos.CENTER);

        if (w<h) {
            canvas.setTranslateX(0);
            canvas.setTranslateY((h-smallest)/2);
        }else {
            canvas.setTranslateX((w-smallest)/2);
            canvas.setTranslateY(0);
        }

        if (smallest != canvas.getWidth() || smallest != canvas.getHeight()) {
            canvas.setWidth(smallest);
            canvas.setHeight(smallest);
            draw();
        }
    }
}
