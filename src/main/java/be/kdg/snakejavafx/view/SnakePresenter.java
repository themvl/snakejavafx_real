package be.kdg.snakejavafx.view;

import be.kdg.snakejavafx.model.Level;

public class SnakePresenter {
    private Level model;
    private SnakeView view;

    public SnakePresenter(Level model, SnakeView view) {
        this.model = model;
        this.view = view;

        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        // koppelt event handlers aan view controls

    }

    private void updateView(){
        //vult de view met data uit model
    }
}
