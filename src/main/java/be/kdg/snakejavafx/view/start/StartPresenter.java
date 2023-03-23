package be.kdg.snakejavafx.view.start;

import be.kdg.snakejavafx.view.about.AboutView;
import be.kdg.snakejavafx.view.help.HelpView;
import be.kdg.snakejavafx.view.highscores.HighscoresPresenter;
import be.kdg.snakejavafx.view.highscores.HighscoresView;
import be.kdg.snakejavafx.view.newgame.NewgamePresenter;
import be.kdg.snakejavafx.view.newgame.NewgameView;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StartPresenter {
    private final StartView view;


    public StartPresenter(StartView view) {
        this.view = view;
        this.addEventHandlers();
    }

    private void addEventHandlers() {
        view.getBtnAbout().setOnAction(actionEvent -> {
            AboutView aboutView = new AboutView();
            Stage aboutStage = new Stage();

            aboutStage.setScene(new Scene(aboutView));

            aboutStage.setMaxWidth(560);
            aboutStage.setMaxHeight(300);

            makeDialog(aboutStage);
        });

        view.getBtnNewGame().setOnAction(actionEvent -> {
            NewgameView newgameView = new NewgameView();
            NewgamePresenter presenter = new NewgamePresenter(newgameView);
            view.getScene().setRoot(newgameView);
        });

        view.getBtnHighscores().setOnAction(actionEvent -> {
            HighscoresView highscoresView = new HighscoresView(false);
            HighscoresPresenter highscoresPresenter = new HighscoresPresenter(highscoresView);
            view.getScene().setRoot(highscoresView);
        });

        view.getBtnHelp().setOnAction(actionEvent -> {
            HelpView helpView = new HelpView();
            Stage helpStage = new Stage();
            helpStage.setScene(new Scene(helpView));
            makeDialog(helpStage);
        });
    }

    private void makeDialog(Stage stage) {
        stage.initOwner(view.getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setX(view.getScene().getWindow().getX() + 100);
        stage.setY(view.getScene().getWindow().getY() + 100);
        stage.setWidth(560);
        stage.setHeight(300);
        stage.setResizable(true);

        stage.showAndWait();
    }
}
