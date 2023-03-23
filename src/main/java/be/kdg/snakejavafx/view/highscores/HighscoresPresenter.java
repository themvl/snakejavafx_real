package be.kdg.snakejavafx.view.highscores;

import be.kdg.snakejavafx.model.Difficulty;
import be.kdg.snakejavafx.model.Highscore;
import be.kdg.snakejavafx.model.Size;
import be.kdg.snakejavafx.view.start.StartPresenter;
import be.kdg.snakejavafx.view.start.StartView;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;

public class HighscoresPresenter {
    HighscoresView view;
    ArrayList<Highscore> highscores;
    public HighscoresPresenter(HighscoresView view) {
        this.view =view;
        highscores = new ArrayList<>();
        loadHighscores();
        updateView();

        addEventHandlers();
    }

    private void addEventHandlers() {
        view.getBackToMenu().setOnAction(actionEvent -> {
            StartView startView = new StartView();
            StartPresenter presenter = new StartPresenter(startView);

            view.getScene().setRoot(startView);
        });
    }
    
    private void loadHighscores() {
        Path myFile = Paths.get("src/main/resources/text/Highscores.txt");
        try(Stream<String> stream = Files.lines(myFile)) {
            stream.forEach(line -> {
                Highscore highscore = new Highscore();
                String[] lines = line.split(";");
                if(lines.length <6) {
                    return;
                }
                highscore.playerName = lines[0];
                highscore.levelSize = Size.valueOf(lines[1]);
                highscore.difficulty = Difficulty.valueOf(lines[2]);
                highscore.startTime = LocalDateTime.parse(lines[3],
                        DateTimeFormatter.ofPattern( "uuuu-MM-dd HH:mm:ss" ));
                highscore.endTime = LocalDateTime.parse(lines[4],
                        DateTimeFormatter.ofPattern( "uuuu-MM-dd HH:mm:ss" ));
                highscore.score = Integer.parseInt(lines[5]);
                highscores.add(highscore);
            });
        }catch (IOException e){
            System.out.println("Highscores didnt load");
        }
    }

    public void addHighscore(Highscore highscore) {
        highscores.add(highscore);
        //clean up too many highscores only keep 10
        if(highscores.size() > 10) {
            highscores.sort(Comparator.comparing(Highscore::getScore));
            for (int i = 10; i <= highscores.size(); i++) {
                    highscores.remove(0);
            }
        }
        updateView();
    }

    public void saveHighscores() {
        try {
            FileWriter myWriter = new FileWriter("src/main/resources/text/Highscores.txt");

            highscores.stream().forEach(s -> {
                try {
                    myWriter.write(s.toString()+"\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void updateView() {
        view.getHighscores().getItems().clear();
        highscores.stream().forEach(highscore -> view.getHighscores().getItems().add(highscore));
        view.getHighscores().sort();
    }
}
