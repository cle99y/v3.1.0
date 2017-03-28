package com.paulclegg.flappygran.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by cle99 on 26/03/2017.
 */

public class Tube {

    private static int FLUCTUATION = Math.round(com.paulclegg.flappygran.FlappyGran.HEIGHT * 0.3f);
    private static int LOW_OPENING = Math.round(com.paulclegg.flappygran.FlappyGran.HEIGHT * 0.3f);
    public static int TUBE_WIDTH = Math.round(com.paulclegg.flappygran.FlappyGran.WIDTH * 0.13f);
    public static int tubesPassed = 0;

    private Texture topTube, botttomTube;
    private Vector2 posTopTube, posBottomTube;
    private Rectangle boundsTop, boundsBottom;
    private Random random;
    private int TUBE_GAP = Math.round(com.paulclegg.flappygran.FlappyGran.HEIGHT * 0.21f);
    private static int INITIAL_GAP = 100;
    private int gap;
    private GameScore gamescore;


    public Tube (float x) {
        System.out.println("gap: " + TUBE_GAP);
        gamescore = new GameScore();
        topTube = new Texture("toptube.png");
        botttomTube = new Texture("bottomtube.png");
        random = new Random();

        posTopTube = new Vector2(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOW_OPENING);
        posBottomTube = new Vector2(x, posTopTube.y - TUBE_GAP - botttomTube.getHeight());

        boundsTop = new Rectangle(posTopTube.x, posTopTube.y,
                TUBE_WIDTH, topTube.getHeight());
        boundsBottom = new Rectangle(posBottomTube.x, posBottomTube.y,
                TUBE_WIDTH, botttomTube.getHeight());

    }

    public void setGap(boolean boo) {



        if (boo) {

            posTopTube = new Vector2(posTopTube.x, posTopTube.y + TUBE_GAP - INITIAL_GAP);
            posBottomTube = new Vector2(posBottomTube.x, posTopTube.y - TUBE_GAP - botttomTube.getHeight());

            boundsTop = new Rectangle(posTopTube.x, posTopTube.y,
                    TUBE_WIDTH, topTube.getHeight());
            boundsBottom = new Rectangle(posBottomTube.x, posBottomTube.y,
                    TUBE_WIDTH, botttomTube.getHeight());
        }
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBotttomTube() {
        return botttomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBottomTube() {
        return posBottomTube;
    }

    public int getTubesPassed () {
        return tubesPassed;
    }

    public int incrementTubesPassed () {
        tubesPassed ++;
        return tubesPassed;
    }

    public void resetTubes () {
        tubesPassed = 0;
    }

    public void reposition(float x) {
        //reposition tubes
        posTopTube.set(x, random.nextInt(FLUCTUATION) + INITIAL_GAP + LOW_OPENING);
        posBottomTube.set(x, posTopTube.y - INITIAL_GAP - botttomTube.getHeight());

        //reposition rectangles
        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBottom.setPosition(posBottomTube.x, posBottomTube.y);
    }

    public boolean collides(Circle player) {
        return Intersector.overlaps(player, boundsTop) || Intersector.overlaps(player, boundsBottom);
    }

    public void dispose() {
        topTube.dispose();
        botttomTube.dispose();
    }

    public Rectangle getTopBounds() {
        return boundsTop;
    }
    public Rectangle getBottomBounds() {
        return boundsBottom;
    }
}
