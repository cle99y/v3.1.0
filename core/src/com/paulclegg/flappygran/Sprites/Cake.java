package com.paulclegg.flappygran.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;
import com.paulclegg.flappygran.FlappyGran;

import java.util.Random;

/**
 * Created by cle99 on 26/03/2017.
 */

public class Cake {

    private static int MIN_HEIGHT = Math.round(FlappyGran.HEIGHT * 0.3f);
    private static int MAX_HEIGHT = Math.round(FlappyGran.HEIGHT * 0.8f);
    private static float CAKE_SIZE = FlappyGran.WIDTH * 0.05f;

    private Texture cupCake;
    private Circle boundsCake;
    private Vector3 cakePosition;
    private boolean cakeEaten;
    private Random random;
    private GameScore gamescore;
    private Tube tube;


    public Cake(float x) {

        gamescore = new GameScore();
        cakeEaten = false;
        random = new Random();
        cupCake = new Texture(Gdx.files.internal("cupcake.png"));
        cakePosition = new Vector3(x, MIN_HEIGHT + random.nextInt(MAX_HEIGHT - MIN_HEIGHT), 0);
        //cakePosition.set(x, MIN_HEIGHT + random.nextInt(MAX_HEIGHT - MIN_HEIGHT), 0);
        boundsCake = new Circle(x + CAKE_SIZE, getPosition().y + CAKE_SIZE, CAKE_SIZE / 2);
    }

    public Vector3 getPosition() {
        return cakePosition;
    }

    public Texture getCake() {
        return cupCake;
    }

    public void reposition(float x) {
        cakePosition.set(x, MIN_HEIGHT + random.nextInt(MAX_HEIGHT - MIN_HEIGHT), 0);
        boundsCake.setPosition(x + CAKE_SIZE, cakePosition.y + CAKE_SIZE);
        cakeEaten = false;
    }

    public Circle getBounds() {
        return boundsCake;
    }

    public float getSize() {
        return CAKE_SIZE;
    }

    public void nowEaten() {
        //cakePosition.add(0, -FlappyDemo.HEIGHT, 0);
        cakeEaten = true;
        boundsCake.setPosition(getBounds().x, 0);
    }

    public boolean isEaten() {
        return cakeEaten;

    }

    public void dispose() {
        cupCake.dispose();
    }

}
