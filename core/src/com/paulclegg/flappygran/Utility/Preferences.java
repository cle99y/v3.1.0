package com.paulclegg.flappygran.Utility;

import com.badlogic.gdx.Gdx;

/**
 * Created by cle99 on 26/03/2017.
 */

public class Preferences {

    private static com.badlogic.gdx.Preferences preferences = Gdx.app.getPreferences("flappyGran");
    private static com.badlogic.gdx.Preferences options = Gdx.app.getPreferences("flappyOptions");

    private static final String HIGH_SCORE_HARD = "topscorehard";
    private static final String HIGH_SCORE_MEDIUM = "topscoremedium";
    private static final String HIGH_SCORE_EASY = "topscoreeasy";

    private static final String GAMES_EASY = "gameseasy";
    private static final String GAMES_MEDIUM = "gamesmedium";
    private static final String GAMES_HARD = "gameshard";

    private static final String POINTS_EASY = "pointseasy";
    private static final String POINTS_MEDIUM = "pointsmedium";
    private static final String POINTS_HARD = "pointshard";


    private static final String DIFFICULTY = "difficulty";

    public static final int EASY = 0;
    public static final int MEDIUM = 1;
    public static final int HARD = 2;





    public Preferences() {

        // initialise high scores for each level

        if (!preferences.contains(HIGH_SCORE_HARD)) {
            preferences.putInteger(HIGH_SCORE_HARD, 0);
        }

        if (!preferences.contains(GAMES_EASY)) {
            preferences.putInteger(GAMES_EASY, 0);
        }

        if (!preferences.contains(POINTS_EASY)) {
            preferences.putInteger(POINTS_EASY, 0);
        }

        if (!preferences.contains(HIGH_SCORE_MEDIUM)) {
            preferences.putInteger(HIGH_SCORE_MEDIUM, 0);
        }

        if (!preferences.contains(GAMES_MEDIUM)) {
            preferences.putInteger(GAMES_MEDIUM, 0);
        }

        if (!preferences.contains(POINTS_MEDIUM)) {
            preferences.putInteger(POINTS_MEDIUM, 0);
        }

        if (!preferences.contains(HIGH_SCORE_EASY)) {
            preferences.putInteger(HIGH_SCORE_EASY, 0);
        }

        if (!preferences.contains(GAMES_HARD)) {
            preferences.putInteger(GAMES_HARD, 0);
        }

        if (!preferences.contains(POINTS_HARD)) {
            preferences.putInteger(POINTS_HARD, 0);
        }

        // initialise difficulty level

        if (!options.contains(DIFFICULTY)) {
            options.putInteger(DIFFICULTY, 1);
        }

        preferences.flush();

    }

    public static void reset() {

        preferences.putInteger(HIGH_SCORE_EASY, 0);
        preferences.putInteger(HIGH_SCORE_MEDIUM, 0);
        preferences.putInteger(HIGH_SCORE_HARD, 0);

        preferences.putInteger(GAMES_EASY, 0);
        preferences.putInteger(GAMES_MEDIUM, 0);
        preferences.putInteger(GAMES_HARD, 0);

        preferences.putInteger(POINTS_EASY, 0);
        preferences.putInteger(POINTS_MEDIUM, 0);
        preferences.putInteger(POINTS_HARD, 0);

        preferences.flush();

    }

    public static void setDifficulty(int d) {
        options.putInteger(DIFFICULTY, d);
        options.flush();
    }

    public static void setGamesPlayed() {

        if (getDifficulty() == EASY) {
            preferences.putInteger(GAMES_EASY, getGamesPlayed(EASY) + 1);


        } else if (getDifficulty() == MEDIUM) {
            preferences.putInteger(GAMES_MEDIUM, getGamesPlayed(MEDIUM) + 1);

        } else {
            preferences.putInteger(GAMES_HARD, getGamesPlayed(HARD) + 1);
        }

        preferences.flush();

    }

    public static int getGamesPlayed(int level) {

        if (level == EASY) {
            return preferences.getInteger(GAMES_EASY);
        }

        if (level == MEDIUM) {
            return preferences.getInteger(GAMES_MEDIUM);
        }

        return preferences.getInteger(GAMES_HARD);

    }

    public static void setTotalPoints(int pts) {

        if (getDifficulty() == EASY) {
            preferences.putInteger(POINTS_EASY, getTotalPoints(EASY) + pts);
        }

        if (getDifficulty() == MEDIUM) {
            preferences.putInteger(POINTS_MEDIUM, getTotalPoints(MEDIUM) + pts);
        }

        if (getDifficulty() == HARD) {
            preferences.putInteger(POINTS_HARD, getTotalPoints(HARD) + pts);
        }

        preferences.flush();

    }

    public static int getTotalPoints(int level) {

        if (level == EASY) return preferences.getInteger(POINTS_EASY);

        if (level == MEDIUM) return preferences.getInteger(POINTS_MEDIUM);

        return preferences.getInteger(POINTS_HARD);

    }


    public static int getDifficulty() {
        return options.getInteger(DIFFICULTY);
    }

    public static void setHighScore(int newHighScore) {

        if (getDifficulty() == EASY) {
            preferences.putInteger(HIGH_SCORE_EASY, newHighScore);
            preferences.flush();

        } else if (getDifficulty() == MEDIUM) {
            preferences.putInteger(HIGH_SCORE_MEDIUM, newHighScore);
            preferences.flush();

        } else {
            preferences.putInteger(HIGH_SCORE_HARD, newHighScore);
            preferences.flush();
        }

    }

    public static int getHighScore(int level) {

        if (level == EASY) {
            return preferences.getInteger(HIGH_SCORE_EASY);
        }

        if (level == MEDIUM) {
            return preferences.getInteger(HIGH_SCORE_MEDIUM);
        }

        return preferences.getInteger(HIGH_SCORE_HARD);
    }

    public static String highScoreToString() {

        if (getDifficulty() == EASY) {
            return "Your High Score: " + String.valueOf(preferences.getInteger(HIGH_SCORE_EASY));
        }

        if (getDifficulty() == MEDIUM) {
            return "Your High Score: " + String.valueOf(preferences.getInteger(HIGH_SCORE_MEDIUM));
        }

        return "Your High Score: " + String.valueOf(preferences.getInteger(HIGH_SCORE_HARD));
    }
}