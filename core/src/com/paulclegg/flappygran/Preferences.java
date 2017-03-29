package com.paulclegg.flappygran;

import com.badlogic.gdx.Gdx;

/**
 * Created by cle99 on 26/03/2017.
 */

public class Preferences {
    private static com.badlogic.gdx.Preferences preferences = Gdx.app.getPreferences("flappyGran");
    private static com.badlogic.gdx.Preferences options = Gdx.app.getPreferences("flappyOptions");
    private static final String HIGH_SCORE_HARD = "topscorehard";
    private static final String HIGH_SCORE_EASY = "topscoreeasy";
    private static final String DIFFICULTY = "difficulty";


    public Preferences() {
        if (!preferences.contains(HIGH_SCORE_HARD)) {
            preferences.putInteger(HIGH_SCORE_HARD, 0);
        }

        if (!preferences.contains(HIGH_SCORE_EASY)) {
            preferences.putInteger(HIGH_SCORE_EASY, 0);
        }

        if (!options.contains(DIFFICULTY)) {
            options.putInteger(DIFFICULTY, 1);
        }

    }

    public static void setDifficulty(int d) {
        options.putInteger(DIFFICULTY, d);
        options.flush();
    }

    public static int getDifficulty() {
        return options.getInteger(DIFFICULTY);
    }

    public static void setHighScore(int newHighScore) {
        if (Preferences.getDifficulty() == 0) {
            preferences.putInteger(HIGH_SCORE_EASY, newHighScore);
        } else {
            preferences.putInteger(HIGH_SCORE_HARD, newHighScore);
        }
        preferences.flush();
    }

    public static int getHighScore() {
        return preferences.getInteger(HIGH_SCORE_HARD);
    }

    public static String highScoreToString () {
        if (Preferences.getDifficulty() == 0) {
            return "Your High Score: " + String.valueOf(preferences.getInteger(HIGH_SCORE_EASY));
        } else {
            return "Your High Score: " + String.valueOf(preferences.getInteger(HIGH_SCORE_HARD));
        }
    }
}