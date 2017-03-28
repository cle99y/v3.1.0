package com.paulclegg.flappygran.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by cle99 on 26/03/2017.
 */

public class MenuState extends States {

    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        background = new Texture("tutorial.png");
        playBtn = new Texture("play.png");

    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
