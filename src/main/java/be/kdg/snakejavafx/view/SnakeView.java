package be.kdg.snakejavafx.view;

import be.kdg.snakejavafx.model.Size;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Polyline;
import javafx.scene.transform.Scale;

public class SnakeView extends BorderPane {
    // controls/nodes
    private Label spelerNaam;
    private Label score;

    private HBox topInformation;

    private Polyline levelBox;
    private Group levelDrawing;
    private StackPane levelPane;

    private LevelView levelview;

    public SnakeView(Size size, double FRAME_TIME) {
        this.initialiseNodes(size, FRAME_TIME);
        this.layoutNodes();
    }

    private void initialiseNodes(Size size, double FRAME_TIME){
        // init nodes
        spelerNaam = new Label();
        spelerNaam.setText("Naam: mvl");

        score = new Label();
        score.setText("Score: 100");

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
    }

    private void layoutNodes() {
        // layout nodes
        topInformation.getChildren().addAll(spelerNaam,score);

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
}
