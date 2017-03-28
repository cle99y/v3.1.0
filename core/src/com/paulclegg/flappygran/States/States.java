package com.paulclegg.flappygran.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by cle99 on 26/03/2017.
 */

public abstract class States {

    protected OrthographicCamera camera;
    protected OrthographicCamera gui;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    protected States(GameStateManager gsm) {
        this.gsm = gsm;
        this.mouse = new Vector3();
        this.camera = new OrthographicCamera();
        this.gui = new OrthographicCamera();
    }

    public abstract void handleInput();

    public abstract void update(float dt);

    public abstract void render(SpriteBatch sb);

    public abstract void dispose();




}
