package be.kdg.snakejavafx;

import be.kdg.snakejavafx.view.start.StartPresenter;
import be.kdg.snakejavafx.view.start.StartView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SnakeJavaFX extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        StartView view = new StartView();
        StartPresenter presenter = new StartPresenter(view);

        Scene scene = new Scene(view);
        stage.setScene(scene);
        stage.setTitle("Snake");

        scene.getStylesheets().add("file:src/main/resources/Stylesheets/default.css");
        scene.getRoot().requestFocus();

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}