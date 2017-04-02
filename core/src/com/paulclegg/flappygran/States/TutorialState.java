package com.paulclegg.flappygran.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.paulclegg.flappygran.FlappyGran;
import com.paulclegg.flappygran.Utility.Preferences;


/**
 * Created by cle99 on 26/03/2017.
 */

public class TutorialState extends States {

    private Texture background;
    private Texture playBtn;
    private Rectangle rectangle;
    private ShapeRenderer sr;


    public TutorialState(GameStateManager gsm) {
        super(gsm);


        // Preferences.setDifficulty(Preferences.MEDIUM);
        camera.setToOrtho(false, FlappyGran.WIDTH, FlappyGran.HEIGHT);
        background = new Texture("tutorial.png");
        playBtn = new Texture("play.png");
        rectangle = new Rectangle();
        sr = new ShapeRenderer();
        System.out.println("initial difficulty: " + Preferences.getDifficulty());

    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {

            gsm.set(new MainMenuState(gsm));

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

        sb.draw(background, camera.position.x - camera.viewportWidth / 2, 0, camera.viewportWidth, camera.viewportHeight);

        // allow selection of a difficulty level



        sb.end();

//        sr.setProjectionMatrix(camera.combined);
//        sr.begin(ShapeRenderer.ShapeType.Line);
//        sr.setColor(Color.BLACK);
//        sr.rect((camera.viewportWidth - level0Text.width) / 2 - level0Text.width,
//                camera.viewportHeight * 0.2f - level0Text.height,
//                level0Text.width, level0Text.height);
//        sr.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();


    }
}
