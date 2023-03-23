package be.kdg.snakejavafx.view.newgame;

import be.kdg.snakejavafx.model.Difficulty;
import be.kdg.snakejavafx.model.Size;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class NewgameView extends VBox {
    private TextField playerName;
    private ToggleGroup difficulty;
    private HBox difficultyBox;
    private Label fillInName;

    private ToggleGroup size;
    private HBox sizeBox;
    private Button play;
    private Label naamLabel;


    private Label difficultyLabel;
    private Label sizeLabel;

    public NewgameView() {
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {
        naamLabel = new Label("Name");
        naamLabel.setFont(new Font(24));
        naamLabel.setStyle("-fx-font-weight: bold");
        playerName = new TextField();
        fillInName = new Label();
        fillInName.setFont(new Font(24));
        fillInName.setStyle("-fx-text-fill: red;" +
                "-fx-font-weight: bold");

        difficultyLabel = new Label("Difficulty");
        difficultyLabel.setStyle("-fx-font-size: 24;" +
                "-fx-font-weight: bold;");
        sizeLabel = new Label("Map size");
        sizeLabel.setStyle("-fx-font-size: 24;" +
                "-fx-font-weight: bold;");

        difficulty = new ToggleGroup();
        RadioButton easy = new RadioButton("Easy");
        easy.setToggleGroup(difficulty);
        easy.setSelected(true);
        easy.getStyleClass().add("my-radio");
        easy.setUserData(Difficulty.EASY);

        RadioButton normal = new RadioButton("Normal");
        normal.setToggleGroup(difficulty);
        normal.getStyleClass().add("my-radio");
        normal.setUserData(Difficulty.MEDIUM);

        RadioButton hard = new RadioButton("Hard");
        hard.setToggleGroup(difficulty);
        hard.getStyleClass().add("my-radio");
        hard.setUserData(Difficulty.HARD);

        HBox.setHgrow(easy, Priority.ALWAYS);
        easy.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(normal, Priority.ALWAYS);
        normal.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(hard, Priority.ALWAYS);
        hard.setMaxWidth(Double.MAX_VALUE);


        size = new ToggleGroup();
        RadioButton small = new RadioButton("S");
        small.setToggleGroup(size);
        small.setSelected(true);
        small.getStyleClass().add("my-radio");
        small.setUserData(Size.S);

        RadioButton medium = new RadioButton("M");
        medium.setToggleGroup(size);
        medium.getStyleClass().add("my-radio");
        medium.setUserData(Size.M);

        RadioButton large = new RadioButton("L");
        large.setToggleGroup(size);
        large.getStyleClass().add("my-radio");
        large.setUserData(Size.L);

        HBox.setHgrow(small, Priority.ALWAYS);
        small.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(medium, Priority.ALWAYS);
        medium.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(large, Priority.ALWAYS);
        large.setMaxWidth(Double.MAX_VALUE);

        difficultyBox = new HBox(easy,normal,hard);
        sizeBox = new HBox(small, medium, large);


        play = new Button("Play!!");
        play.getStyleClass().add("menu-button");
    }

    private void layoutNodes() {
        this.getChildren().addAll(naamLabel,playerName,fillInName,difficultyLabel,difficultyBox,sizeLabel, sizeBox, play);
        this.setSpacing(10);
        difficultyBox.setSpacing(10);
        difficultyBox.setAlignment(Pos.CENTER);
        difficultyBox.setMaxWidth(600);
        sizeBox.setSpacing(10);
        sizeBox.setAlignment(Pos.CENTER);
        sizeBox.setMaxWidth(600);
        this.setPadding(new Insets(30,30,30,30));
        this.setAlignment(Pos.CENTER);
    }

    public String getPlayerName() {
        return playerName.getText();
    }

    public Difficulty getDifficulty() {
        return (Difficulty) (difficulty.getSelectedToggle().getUserData());
    }

    public Size getSize() {
        return (Size) (size.getSelectedToggle().getUserData());
    }

    public Button getPlay() {
        return play;
    }
    public void nameError() {
        playerName.setStyle(
                "-fx-border-color: red;" +
                        "-fx-background-color: #ffa9a9;");
        fillInName.setText("Please fill in a name.");
    }
}
