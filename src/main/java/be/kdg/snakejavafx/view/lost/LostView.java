package be.kdg.snakejavafx.view.lost;

import be.kdg.snakejavafx.view.highscores.HighscoresView;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LostView extends VBox {
    private final Label title;
    private final Button backToMenu;
    private final Button playAgain;

    private final HighscoresView highscores;
    public LostView() {
        // init nodes
        title = new Label("Lost!");
        title.setFont(new Font(24));
        title.setTextFill(Color.RED);
        title.setStyle("-fx-font-weight: bold");

        backToMenu = new Button("Main menu");
        backToMenu.getStyleClass().add("menu-button");

        playAgain = new Button("Play again");
        playAgain.getStyleClass().add("menu-button");

        highscores = new HighscoresView(true);
        this.getChildren().addAll(title, highscores,backToMenu, playAgain);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        this.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                + "-fx-border-radius: 0;" + "-fx-border-color: black;");

    }

    public HighscoresView getHighscores(){
        return highscores;
    }

    public Button getBackToMenu() {
        return backToMenu;
    }
    public Button getPlayAgain() {
        return playAgain;
    }
}
