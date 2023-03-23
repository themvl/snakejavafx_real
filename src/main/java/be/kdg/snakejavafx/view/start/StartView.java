package be.kdg.snakejavafx.view.start;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class StartView extends VBox {
    private Button btnNewGame;

    private Button btnAbout;
    private Button btnHelp;
    private Button btnHighscores;
    private Label gameName;

    public StartView() {
        this.initialiseNodes();
        this.layoutNodes();
    }
    private void initialiseNodes() {
        gameName = new Label("Snake!!!");
        gameName.setFont(new Font(100));
        btnNewGame = new Button("New Game");

        btnHelp = new Button("Help");
        btnHighscores =new Button("Highscores");
        btnAbout = new Button("About");
    }

    private void layoutNodes(){
        this.setSpacing(10);
        this.setPadding(new Insets(30,30,30,30));
        this.setAlignment(Pos.CENTER);
        btnNewGame.getStyleClass().add("menu-button");
        btnAbout.getStyleClass().add("menu-button");
        btnHighscores.getStyleClass().add("menu-button");
        btnHelp.getStyleClass().add("menu-button");

        getChildren().addAll(gameName,btnNewGame,btnHelp,btnHighscores,btnAbout);
    }

    public Button getBtnNewGame() {
        return btnNewGame;
    }

    public Button getBtnAbout() {
        return btnAbout;
    }

    public Button getBtnHelp() {
        return btnHelp;
    }

    public Button getBtnHighscores() {
        return btnHighscores;
    }
}
