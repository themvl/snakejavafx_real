package be.kdg.snakejavafx.view;

import be.kdg.snakejavafx.model.Level;
import be.kdg.snakejavafx.model.Snake;

public class SnakePresenter {
    private final Level model;
    private final SnakeView view;

    public SnakePresenter(Level model, SnakeView view) {
        this.model = model;
        this.view = view;

        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        // koppelt event handlers aan view controls
        view.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case LEFT -> model.changeSnakeDirection(Snake.Orientation.LEFT);
                case RIGHT -> model.changeSnakeDirection(Snake.Orientation.RIGHT);
                case UP -> model.changeSnakeDirection(Snake.Orientation.UP);
                case DOWN -> model.changeSnakeDirection(Snake.Orientation.DOWN);
            }
        });
    }

    public void updateView(){
        //vult de view met data uit model
        view.getLevelView().setSnakeParts(model.getSnake().getSnakePartPositions());
        view.setScore(model.getHighscore().score);
        view.getLevelView().setDirection(model.getSnake().getOrientation());
        view.getLevelView().setGameObjects(model.getGameObjects());

        view.getLevelView().draw();
    }
}
