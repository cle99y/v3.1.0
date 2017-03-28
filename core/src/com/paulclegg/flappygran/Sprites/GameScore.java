package com.paulclegg.flappygran.Sprites;

import sun.font.GlyphLayout;

/**
 * Created by cle99 on 26/03/2017.
 */

public class GameScore {

    private static int score = 0;
    private GlyphLayout glyph;
    private float boundsScore;

    public GameScore () {

    }

    public int addToScore(int x) {
        int theScore = score += x;
        if (theScore > com.paulclegg.flappygran.Preferences.getHighScore()) {
            com.paulclegg.flappygran.Preferences.setHighScore(theScore);
        }
        return theScore;
    }

    private String repeat(int n, String str){
        return new String(new char[n]).replace("\0", str);
    }

    public int getScore() {
        return score;
    }

    public String scoreToString() {
        String text =String.valueOf(score);
        int len = text.length();
        return "SCORE: " + repeat(4 - len, "0") + text;
    }

    public void reset() {
        score = 0;
    }


}