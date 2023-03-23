package be.kdg.snakejavafx.view.lost;

import be.kdg.snakejavafx.model.Highscore;
import be.kdg.snakejavafx.view.highscores.HighscoresPresenter;
import be.kdg.snakejavafx.view.snake.SnakePresenter;
import be.kdg.snakejavafx.view.start.StartPresenter;
import be.kdg.snakejavafx.view.start.StartView;

public class LostPresenter {
    private final LostView view;
    private final HighscoresPresenter highscores;
    private final SnakePresenter game;
    public LostPresenter(LostView view, Highscore highscore, SnakePresenter game) {
        this.view = view;
        highscores = new HighscoresPresenter(view.getHighscores());
        highscores.addHighscore(highscore);
        view.getHighscores().setHighlighted(highscore);
        highscores.saveHighscores();
        this.game = game;

        addEventHandlers();
    }

    private void addEventHandlers() {
        view.getBackToMenu().setOnAction(actionEvent -> {
            StartView startView = new StartView();
            StartPresenter presenter = new StartPresenter(startView);

            view.getScene().setRoot(startView);
        });

        view.getPlayAgain().setOnAction(actionEvent ->  {
            game.reset();
        });
    }
}
