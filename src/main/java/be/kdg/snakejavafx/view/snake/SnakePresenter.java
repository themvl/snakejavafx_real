package be.kdg.snakejavafx.view.snake;

import be.kdg.snakejavafx.model.Level;
import be.kdg.snakejavafx.model.Snake;
import javafx.animation.AnimationTimer;

public class SnakePresenter {
    private final Level model;
    private final SnakeView view;
    private final AnimationTimer mainLoop;
    private boolean isPaused = false;

    public SnakePresenter(Level model, SnakeView view) {
        this.model = model;
        this.view = view;

        this.addEventHandlers();
        this.updateView();

        final long startNanoTime = System.nanoTime();

        mainLoop = new AnimationTimer() {
            private long lastUpdate = startNanoTime;
            double deltatime=0;
            public void handle(long currentNanoTime) {
                if(isPaused) {
                    lastUpdate = currentNanoTime-(long)(deltatime*1000000000.0);
                    System.out.println("lastUpdate = " + lastUpdate);
                    return;
                }
                deltatime = (currentNanoTime-lastUpdate) / 1000000000.0;
                view.getLevelView().setSnakeMoveTime(deltatime);
                view.getLevelView().draw();

                if (deltatime > getFrameTime()) {
                    lastUpdate = currentNanoTime;
                    view.getLevelView().setSnakeMoveTime(0);
                    boolean notLost = model.moveSnake();

                    if (!notLost) {
                        this.stop();
                        view.getLevelView().setSnakeMoveTime(deltatime);
                        view.lost(model.getHighscore(), SnakePresenter.this);
                        updateView();
                    }else  {
                        updateView();
                    }
                }
            }
        };
        mainLoop.start();

        view.setPlayerName(model.getHighscore().playerName);
    }

    private void addEventHandlers() {
        // koppelt event handlers aan view controls
        view.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case LEFT,A -> model.changeSnakeDirection(Snake.Orientation.LEFT);
                case RIGHT,D -> model.changeSnakeDirection(Snake.Orientation.RIGHT);
                case UP,W -> model.changeSnakeDirection(Snake.Orientation.UP);
                case DOWN,S -> model.changeSnakeDirection(Snake.Orientation.DOWN);
                case ESCAPE -> isPaused = !isPaused;
            }
        });
    }

    private void updateView(){
        //vult de view met data uit model
        view.getLevelView().setSnakeParts(model.getSnake().getSnakePartPositions());
        view.setScore(model.getHighscore().score);
        view.getLevelView().setDirection(model.getSnake().getOrientation());
        view.getLevelView().setGameObjects(model.getGameObjects());
        view.getLevelView().setFrameTime(getFrameTime());

        view.getLevelView().draw();
    }

    private double getFrameTime() {
        double ft = 0;
        switch (model.getHighscore().difficulty) {
            case EASY -> ft = 0.5;
            case MEDIUM -> ft = 0.4;
            case HARD -> ft = 0.3;
        }

        ft-=model.getHighscore().score*0.000065;
        return ft;
    }

    public void reset() {
        model.reset();
        view.setRight(null);
        mainLoop.start();
        view.getScene().getRoot().requestFocus();
    }
}
