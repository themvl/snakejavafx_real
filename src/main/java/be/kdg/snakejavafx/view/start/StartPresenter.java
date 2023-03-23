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
            aboutStage.initOwner(view.getScene().getWindow());
            aboutStage.initModality(Modality.APPLICATION_MODAL);
            aboutStage.setScene(new Scene(aboutView));
            aboutStage.setX(view.getScene().getWindow().getX() + 100);
            aboutStage.setY(view.getScene().getWindow().getY() + 100);
            aboutStage.setWidth(560);
            aboutStage.setHeight(300);
            aboutStage.setResizable(true);
            aboutStage.setMaxWidth(560);
            aboutStage.setMaxHeight(300);

            aboutStage.showAndWait();
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
            Stage aboutStage = new Stage();
            aboutStage.initOwner(view.getScene().getWindow());
            aboutStage.initModality(Modality.APPLICATION_MODAL);
            aboutStage.setScene(new Scene(helpView));
            aboutStage.setX(view.getScene().getWindow().getX() + 100);
            aboutStage.setY(view.getScene().getWindow().getY() + 100);
            aboutStage.setWidth(560);
            aboutStage.setHeight(300);
            aboutStage.setResizable(true);

            aboutStage.showAndWait();
        });
    }
}
