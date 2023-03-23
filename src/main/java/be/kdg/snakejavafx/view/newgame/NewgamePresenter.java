package be.kdg.snakejavafx.view.newgame;

import be.kdg.snakejavafx.model.Difficulty;
import be.kdg.snakejavafx.model.Level;
import be.kdg.snakejavafx.model.Size;
import be.kdg.snakejavafx.view.snake.SnakePresenter;
import be.kdg.snakejavafx.view.snake.SnakeView;

public class NewgamePresenter {
    NewgameView view;

    public NewgamePresenter(NewgameView view) {
        this.view = view;
        addEvenHandlers();
    }

    private void addEvenHandlers( ) {
        view.getPlay().setOnAction(ActionEvent -> {
            String name = view.getPlayerName();

            if(name.equals("")) {
                view.nameError();
                return;
            }

            Difficulty diff = view.getDifficulty();
            Size size = view.getSize();
            Level level = new Level(size,diff,name);
            SnakeView snakeView = new SnakeView(size);
            SnakePresenter presenter = new SnakePresenter(level , snakeView);

            view.getScene().setRoot(snakeView);
            snakeView.getScene().getRoot().requestFocus();
        });
    }
}
