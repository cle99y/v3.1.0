package com.paulclegg.flappygran.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;
import com.paulclegg.flappygran.FlappyGran;

/**
 * Created by cle99 on 26/03/2017.
 */

public class Gran {
    private static final int GRAVITY = -50;
    private static final int MOVEMENT = 150;
    public static int PLAYER_SIZE;
    public static boolean gravityInversion = false;
    private Vector3 position;
    private Vector3 velocity;
    private Texture gran, ghostGran;
    public static boolean isGhost = false;
    //public Circle boundsGran;
    public Circle boundsGranTop, boundsGranBottom;


    public Gran(int x, int y) {
        PLAYER_SIZE = Math.round(FlappyGran.HEIGHT / 10);
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        ghostGran = new Texture("ghostgran.png");
        gran = new Texture("gran.png");
        boundsGranTop = new Circle(x, y, (PLAYER_SIZE / 2) * 27/35);
        boundsGranBottom = new Circle(x, y, (PLAYER_SIZE / 2) * 27/35);
    }

    public void update(float dt) {
        if (position.y > 150)
            velocity .add(0, setGravity() * GRAVITY, 0);
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);
        if (position.y < 150)
            position.y = 150;
        if (position.y > FlappyGran.HEIGHT - PLAYER_SIZE) {
            position.y = FlappyGran.HEIGHT - PLAYER_SIZE;
        }
        boundsGranTop.setPosition(position.x + PLAYER_SIZE / 2, position.y + PLAYER_SIZE - boundsGranTop.radius);
        boundsGranBottom.setPosition(position.x + PLAYER_SIZE / 2, position.y + boundsGranBottom.radius);
        velocity.scl(1/dt);

    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getGran() {
        return (isGhost) ? ghostGran : gran;
    }

    public void jump() {
        velocity.y = setGravity() * 760;
    }

    public Circle getBounds(String area) {
        if (area == "top")
            return boundsGranTop;

        return boundsGranBottom;
    }

    public float setGravity() {
        // set a GRAVITY multiplier depending on status of the
        // gravityInversion flag
        return (gravityInversion) ? -1.0f : 1.0f;
    }

    public void dispose() {
        gran.dispose();
    }
}
