package be.kdg.snakejavafx.view.help;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class HelpView extends HBox {

    public HelpView() {
        this.setBackground(new Background(new BackgroundFill(
                Color.BLACK,
                CornerRadii.EMPTY,
                Insets.EMPTY)));
        Label help = new Label(getHelp());
        help.setTextFill(Color.WHITE);
        help.setFont(new Font(18));
        help.setWrapText(true);
        getChildren().addAll(help);
        setFillHeight(true);
        this.setPadding(new Insets(30,30,30,30));
    }

    private String getHelp(){
        Path myFile = Paths.get("src/main/resources/text/Help.txt");
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(myFile)) {

            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            return "no help file found!";
        }

        return contentBuilder.toString();
    }
}
