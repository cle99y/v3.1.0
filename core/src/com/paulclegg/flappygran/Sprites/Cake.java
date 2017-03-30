package com.paulclegg.flappygran.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;
import com.paulclegg.flappygran.FlappyGran;
import com.paulclegg.flappygran.Preferences;
import com.paulclegg.flappygran.Utility.Levels;

import java.util.Random;

/**
 * Created by cle99 on 26/03/2017.
 */

public class Cake {

    private static int MIN_HEIGHT = Math.round(FlappyGran.HEIGHT * 0.3f);
    private static int MAX_HEIGHT = Math.round(FlappyGran.HEIGHT * 0.8f);
    private static int MIDDLE_HEIGHT = Math.round((FlappyGran.HEIGHT - MIN_HEIGHT) * 0.5f);
    private static float CAKE_SIZE = FlappyGran.WIDTH * 0.05f;
    private static String[] effects;

    private Texture cupCake, ghostCake, slideCake, gravityCake;
    private Circle boundsCake;
    private Vector3 cakePosition;
    private boolean cakeEaten;
    private Random random;
    private String effect;


    public Cake(float x) {

        effects = (Preferences.getDifficulty() == Preferences.HARD) ? Levels.hard : Levels.easy;

        for (String e : effects)  // for testing & debugging

        cakeEaten = false;
        random = new Random();
        cupCake = new Texture(Gdx.files.internal("cupcake.png"));
        ghostCake = new Texture(Gdx.files.internal("ghostcake.png"));
        gravityCake = new Texture(Gdx.files.internal("gravitycake.png"));
        slideCake = new Texture(Gdx.files.internal("slidecake.png"));

        cakePosition = new Vector3(x, MIN_HEIGHT + random.nextInt(MAX_HEIGHT - MIN_HEIGHT), 0);
        //cakePosition.set(x, MIN_HEIGHT + random.nextInt(MAX_HEIGHT - MIN_HEIGHT), 0);
        boundsCake = new Circle(x + CAKE_SIZE, getPosition().y + CAKE_SIZE, CAKE_SIZE / 2);
        effect = setEffect();

    }

    public Vector3 getPosition() {
        return cakePosition;
    }

    public Texture getCake() {

        if (getEffect() == "GHOST") {
            return ghostCake;
        } else if (getEffect() == "GRAVITY") {
            return gravityCake;
        } else if (getEffect() == "SLIDE") {
            return slideCake;
        }
        return cupCake;
    }

    public void reposition(float x) {

        // set an effect that the cake will enable when eaten
        effect = setEffect();

        if (getEffect().equals("GRAVITY")) {
            cakePosition.y = MIDDLE_HEIGHT;
        } else {
            cakePosition.y = random.nextInt(MAX_HEIGHT - MIN_HEIGHT);
        }

        // reposition cake after it moves out of view to the end of the tube/cake state
        cakePosition.set(x, MIN_HEIGHT + cakePosition.y, 0);
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

        cakeEaten = true;
        if (getEffect() == "GHOST") {
            MakeEffect.ghost();
        } else if (getEffect() == "GRAVITY") {
            MakeEffect.gravity();
        } else if (getEffect() == "SLIDE") {
            MakeEffect.slide();
        } else {
            MakeEffect.normal();
        }

        // move collider out of the way
        boundsCake.setPosition(getBounds().x, 0);
    }

    public boolean isEaten() {
        return cakeEaten;

    }

    public String setEffect () {
        int selector = random.nextInt(effects.length);

        return effects[selector];
    }

    public String getEffect() {
        return effect;
    }

    public void dispose() {
        cupCake.dispose();
    }

}
