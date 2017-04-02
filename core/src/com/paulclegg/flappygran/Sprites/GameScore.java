package com.paulclegg.flappygran.Sprites;

import com.paulclegg.flappygran.Utility.Preferences;

/**
 * Created by cle99 on 26/03/2017.
 */

public class GameScore {

    public static int score;


    private GameScore () {

    }

    public static void addToScore(int x) {
        score += x;
        if (score > Preferences.getHighScore(Preferences.getDifficulty())) {
            Preferences.setHighScore(score);
        }

    }

    private static String repeat(int n, String str){
        return new String(new char[n]).replace("\0", str);
    }

    public static int getScore() {
        return score;
    }

    public static String scoreToString() {
        String text =String.valueOf(score);
        int len = text.length();
        return "SCORE: " + repeat(4 - len, "0") + text;
    }

    public static void reset() {
        score = 0;
    }


}