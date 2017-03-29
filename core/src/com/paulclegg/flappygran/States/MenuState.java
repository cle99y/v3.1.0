package com.paulclegg.flappygran.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.paulclegg.flappygran.FlappyGran;
import com.paulclegg.flappygran.Preferences;


/**
 * Created by cle99 on 26/03/2017.
 */

public class MenuState extends States {

    private Texture background;
    private Texture playBtn;
    private BitmapFont option_selected;
    private BitmapFont option_not_selected;
    private GlyphLayout level0Text = new GlyphLayout();
    private GlyphLayout level1Text = new GlyphLayout();
    private Array<String> levels = new Array<String>();
    private Vector3 difficulty;
    private int level;
    private Rectangle rectangle;
    private ShapeRenderer sr;


    public MenuState(GameStateManager gsm) {
        super(gsm);

        levels.add("NORMAL");
        levels.add("DIFFICULT");
        camera.setToOrtho(false, FlappyGran.WIDTH, FlappyGran.HEIGHT);
        background = new Texture("tutorial.png");
        playBtn = new Texture("play.png");
        option_selected = new BitmapFont(Gdx.files.internal("flappygran.fnt"));
        option_selected.setColor(Color.WHITE);
        option_selected.getData().setScale(1.3f);
        option_not_selected = new BitmapFont(Gdx.files.internal("flappygran.fnt"));
        option_not_selected.setColor(Color.GRAY);
        option_not_selected.getData().setScale(1.3f);
        difficulty = new Vector3();
        rectangle = new Rectangle();
        sr = new ShapeRenderer();

        if (Preferences.getDifficulty() == 1) {

        }
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {

            int xPos = Gdx.input.getX();
            int yPos = Gdx.input.getY();
            difficulty.set(xPos, yPos, 0);
            camera.unproject(difficulty);

            System.out.println("touch x: " + difficulty.x);
            System.out.println("touch y: " + difficulty.y);
            System.out.println(level0Text.height);

            if (difficulty.x > (camera.viewportWidth - level0Text.width) / 2 - level0Text.width &&
                    difficulty.x < (camera.viewportWidth - level0Text.width) / 2 + level0Text.width &&
                    difficulty.y > camera.viewportHeight * 0.2f - level0Text.width &&
                    difficulty.y < camera.viewportHeight * 0.2f) {
                Preferences.setDifficulty(Preferences.getDifficulty() * -1 + 1); // TODO toggle method
                gsm.set(new PlayState(gsm));
            }

            if (difficulty.x > (camera.viewportWidth - level1Text.width) / 2 + level1Text.width &&
                    difficulty.x < (camera.viewportWidth - level1Text.width) /2 + level1Text.width * 2 &&
                    difficulty.y > camera.viewportHeight * 0.2f - level1Text.height &&
                    difficulty.y < camera.viewportHeight * 0.2f) {
                gsm.set(new PlayState(gsm));
            }

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

        level0Text.setText(option_not_selected, levels.get(Preferences.getDifficulty()));
        option_not_selected.draw(sb, level0Text,
                (camera.viewportWidth - level0Text.width) / 2 - level0Text.width,
                camera.viewportHeight * 0.2f);

        level1Text.setText(option_selected, levels.get(Preferences.getDifficulty() * -1 + 1));
        option_selected.draw(sb, level1Text,
                (camera.viewportWidth - level1Text.width) / 2 + level1Text.width,
                camera.viewportHeight * 0.2f);

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
