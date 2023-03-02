package be.kdg.snakejavafx;

import be.kdg.snakejavafx.model.Difficulty;
import be.kdg.snakejavafx.model.Level;
import be.kdg.snakejavafx.model.Size;
import be.kdg.snakejavafx.view.SnakePresenter;
import be.kdg.snakejavafx.view.SnakeView;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SnakeJavaFX extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        final double FRAME_TIME = 0.2;

        Level level = new Level(Size.S, Difficulty.EASY,"mvl");
        SnakeView view = new SnakeView(Size.S, FRAME_TIME);

        SnakePresenter presenter = new SnakePresenter(level, view);

        Scene scene = new Scene(view);
        stage.setScene(scene);
        stage.setTitle("Snake");
        stage.setWidth(800);
        stage.setHeight(500);
        stage.show();

        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            private long lastUpdate = startNanoTime;
            public void handle(long currentNanoTime) {
                //double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                double deltatime = (currentNanoTime-lastUpdate) / 1000000000.0;
                view.getLevelView().setSnakeMoveTime(deltatime);
                view.getLevelView().draw();

                if (deltatime > FRAME_TIME) {
                    lastUpdate = currentNanoTime;
                    view.getLevelView().setSnakeMoveTime(0);
                    level.moveSnake();
                    presenter.updateView();
                }
            }
        }.start();

        scene.getRoot().requestFocus();

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}