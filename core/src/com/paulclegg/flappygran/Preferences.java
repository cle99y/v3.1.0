package com.paulclegg.flappygran;

import com.badlogic.gdx.Gdx;

/**
 * Created by cle99 on 26/03/2017.
 */

public class Preferences {
    private static com.badlogic.gdx.Preferences preferences = Gdx.app.getPreferences("flappyGran");


    public Preferences() {
        if (!preferences.contains("topScore")) {
            preferences.putInteger("topScore", 0);
        }
    }

    public static void setHighScore(int newHighScore) {
        preferences.putInteger("topScore", newHighScore);
        preferences.flush();
    }

    public static int getHighScore() {
        return preferences.getInteger("topScore");
    }

    public static String highScoreToString () {
        return "Your High Score: " + String.valueOf(preferences.getInteger("topScore"));
    }
}