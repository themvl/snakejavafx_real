package be.kdg.snakejavafx.view.about;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class AboutView extends HBox {
    private Label about;
    private ImageView apple;
    public AboutView(){
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {
        this.setBackground(new Background(new BackgroundFill(
                Color.BLACK,
                CornerRadii.EMPTY,
                Insets.EMPTY)));
        about = new Label(getAbout());
        about.setTextFill(Color.WHITE);
        about.setFont(new Font(18));
        apple = new ImageView(new Image(
                "file:src/main/resources/images/logo.png"));
        apple.setSmooth(false);
        apple.setFitWidth(200);
        apple.setPreserveRatio(true);
        about.setWrapText(true);
    }

    private void layoutNodes(){
        getChildren().addAll(apple,about);
        setFillHeight(true);
        this.setPadding(new Insets(30,30,30,30));
    }

    private String getAbout() {
        Path myFile = Paths.get("src/main/resources/text/About.txt");
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(myFile)) {

            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            return "no about file found!";
        }

        return contentBuilder.toString();
    }
}
