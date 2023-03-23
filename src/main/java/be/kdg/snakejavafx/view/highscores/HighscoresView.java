package be.kdg.snakejavafx.view.highscores;

import be.kdg.snakejavafx.model.Highscore;
import javafx.css.PseudoClass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class HighscoresView extends VBox {
    private Label title;
    private TableView<Highscore> highscores;
    private Button backToMenu;

    boolean minimalMode;

    public HighscoresView(boolean minimalMode) {
        this.minimalMode = minimalMode;
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes(){
        title = new Label("Highscores");
        title.setFont(new Font(20));

        highscores = new TableView<>();
        highscores.getStyleClass().add("highscores");

        TableColumn<Highscore,String> nameColumn =
                new TableColumn<>("Player");
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("playerName")
        );

        TableColumn<Highscore,String> scoreColumn =
                new TableColumn<>("Score");

        scoreColumn.setCellValueFactory(
                new PropertyValueFactory<>("score")
        );

        TableColumn<Highscore,String> sizeColumn =
                new TableColumn<>("Size");
        sizeColumn.setCellValueFactory(
                new PropertyValueFactory<>("levelSize")
        );

        TableColumn<Highscore, String> difficultyColumn =
                new TableColumn<>("Difficulty");
        difficultyColumn.setCellValueFactory(
                new PropertyValueFactory<>("difficulty")
        );

        TableColumn<Highscore, String> startColumn =
                new TableColumn<>("Start time");
        startColumn.setCellValueFactory(
                new PropertyValueFactory<>("startTime")
        );

        TableColumn<Highscore, String> endColumn =
                new TableColumn<>("End time");
        endColumn.setCellValueFactory(
                new PropertyValueFactory<>("endTime")
        );

        if(!minimalMode) {
            highscores.getColumns().addAll(
                    nameColumn,
                    sizeColumn,
                    difficultyColumn,
                    startColumn,
                    endColumn,
                    scoreColumn
            );
        }else {
            highscores.getColumns().addAll(
                    nameColumn,
                    scoreColumn
            );
        }
        scoreColumn.setSortType(TableColumn.SortType.DESCENDING);
        highscores.getSortOrder().add(scoreColumn);
        highscores.sort();

        backToMenu = new Button("Main menu");
        backToMenu.getStyleClass().add("menu-button");
    }

    private void layoutNodes() {
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(title,highscores);
        if(!minimalMode) {
            this.getChildren().add(backToMenu);
        }
        this.setSpacing(10);
        this.setPadding(new Insets(10,10,10,10));
    }

    public TableView getHighscores() {
        return highscores;
    }

    public Button getBackToMenu() {
        return backToMenu;
    }

    public void setHighlighted(Highscore highscore) {
        PseudoClass higlighted = PseudoClass.getPseudoClass("highlighted");
        highscores.setRowFactory(tableView -> {
            TableRow<Highscore> row = new TableRow<>();
            row.itemProperty().addListener((obs, oldHs, newHs) ->
                    row.pseudoClassStateChanged(higlighted,
                            newHs == highscore));
            return row;
        });
    }
}
