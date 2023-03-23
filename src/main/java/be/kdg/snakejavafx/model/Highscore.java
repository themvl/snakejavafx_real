package be.kdg.snakejavafx.model;

import javafx.beans.property.StringProperty;

import java.text.spi.DateFormatProvider;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Highscore {
    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public Size getLevelSize() {
        return levelSize;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public String getStartTime() {
        return startTime.format( DateTimeFormatter.ofPattern( "uuuu-MM-dd HH:mm:ss" ) );
    }

    public String getEndTime() {
        return endTime.format( DateTimeFormatter.ofPattern( "uuuu-MM-dd HH:mm:ss" ) );
    }

    public String playerName;
    public int score;
    public Size levelSize;
    public Difficulty difficulty;
    public LocalDateTime startTime;
    public LocalDateTime endTime;

    public Highscore(String playerName, int score, Size levelSize, Difficulty difficulty, LocalDateTime startTime, LocalDateTime endTime) {
        this.playerName = playerName;
        this.score = score;
        this.levelSize = levelSize;
        this.difficulty = difficulty;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Highscore() {
    }

    @Override
    public String toString() {
        return String.join(";",
                playerName,
                levelSize.name(),
                difficulty.name(),
                getStartTime(),
                getEndTime(),
                Integer.toString(score));
    }
}
