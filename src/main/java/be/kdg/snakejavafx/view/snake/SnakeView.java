package be.kdg.snakejavafx.view.snake;

import be.kdg.snakejavafx.model.Highscore;
import be.kdg.snakejavafx.model.Size;
import be.kdg.snakejavafx.view.lost.LostPresenter;
import be.kdg.snakejavafx.view.lost.LostView;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.Group;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;
import javafx.scene.image.Image;

public class SnakeView extends BorderPane {
    // controls/nodes
    private Label spelerNaam;
    private Label score;

    private HBox topInformation;

    private Polyline levelBox;
    private Group levelDrawing;
    private StackPane levelPane;

    private LevelView levelview;

    private Image apple;
    private ImageView scoreApple;

    private LostView lostView;

    public SnakeView(Size size, double FRAME_TIME) {
        this.initialiseNodes(size, FRAME_TIME);
        this.layoutNodes();
    }

    private void initialiseNodes(Size size, double FRAME_TIME){
        // init nodes

        spelerNaam = new Label();
        spelerNaam.setText("Name: mvl");

        score = new Label();
        score.setText("Score: 100");

        HBox.setHgrow(spelerNaam, Priority.ALWAYS);
        HBox.setHgrow(score, Priority.ALWAYS);

        spelerNaam.setMaxWidth(Double.MAX_VALUE);
        score.setMaxWidth(Double.MAX_VALUE);

        topInformation = new HBox();
        levelDrawing = new Group();

        levelBox = new Polyline();
        //levelBox.setStrokeWidth(2);
        Scale scale = new Scale(1,1);
        levelBox.getTransforms().add(scale);

        levelBox.getPoints().addAll(0d,0d,
                100d,0d,
                1d,100d,
                0d,100d,
                0d,0d);


        levelPane = new StackPane();

        levelview = new LevelView(size,FRAME_TIME);
        topInformation.setPrefHeight(20);
        topInformation.setPadding(new Insets(10,10,10,10));

        Font font =new Font(24);
        spelerNaam.setFont(font);
        score.setFont(font);
        spelerNaam.setStyle("-fx-font-weight: bold");
        score.setStyle("-fx-font-weight: bold");

        apple =new Image("file:src/main/resources/images/apple.png");
        scoreApple = new ImageView();
        scoreApple.setImage(apple);
    }

    private void layoutNodes() {
        // layout nodes
        topInformation.getChildren().addAll(spelerNaam,scoreApple,score);


        this.setTop(topInformation);
        levelDrawing.getChildren().add(levelBox);
        levelPane.getChildren().add(levelDrawing);
        this.setCenter(levelview);

    }

    public LevelView getLevelView() {
        return levelview;
    }

    public void setScore(int player_score){
        score.setText("Score: "+player_score);
    }

    // package private getters

    public void setPlayerName(String name) {
        spelerNaam.setText("Name: " + name);
    }

    public void lost(Highscore highscore, SnakePresenter game) {
        lostView = new LostView();
        LostPresenter lostPresenter = new LostPresenter(lostView, highscore, game);
        this.setRight(lostView);
    }

}
