package com.paulclegg.flappygran.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.paulclegg.flappygran.FlappyGran;
import com.paulclegg.flappygran.Utility.Preferences;

/**
 * Created by cle99 on 02/04/2017.
 */

public class ResetScores extends States {

    private BitmapFont title;
    private GlyphLayout text;
    private Vector3 menuSelect;
    private Texture background;
    private float[] buttonBoundsX;
    private float[] buttonLength;
    private float[] buttonBoundsY;
    private float[] buttonHeight;

    public ResetScores(GameStateManager gsm) {
        super(gsm);

        title = new BitmapFont(Gdx.files.internal("hiscore.fnt"));
        title.setColor(Color.WHITE);
        title.getData().setScale(2.0f);
        text = new GlyphLayout();

        background = new Texture("plain_purple.png");

        menuSelect = new Vector3();

        camera.setToOrtho(false, FlappyGran.WIDTH, FlappyGran.HEIGHT);

        buttonBoundsX = new float[] {0f, 0f};
        buttonLength = new float[] {0f, 0f};
        buttonBoundsY = new float[] {0f, 0f};
        buttonHeight = new float[] {0f, 0f};

    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {

            // get the screen co-ordinates of the touch / click
            int xPos = Gdx.input.getX();
            int yPos = Gdx.input.getY();
            menuSelect.set(xPos, yPos, 0);
            camera.unproject(menuSelect);

            if (menuSelect.x > buttonBoundsX[0] &&
                    menuSelect.x < buttonBoundsX[0] + buttonLength[0] &&
                    menuSelect.y > buttonBoundsY[0] &&
                    menuSelect.y < buttonBoundsY[0] + buttonHeight[0]) {
                Preferences.reset();
                gsm.set(new HiScoreState(gsm));

            }

            if (menuSelect.x > buttonBoundsX[1] &&
                    menuSelect.x < buttonBoundsX[1] + buttonLength[1] &&
                    menuSelect.y > buttonBoundsY[1] &&
                    menuSelect.y < buttonBoundsY[1] + buttonHeight[1]) {
                gsm.set(new HiScoreState(gsm));
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

        sb.draw(background,
                camera.position.x - camera.viewportWidth / 2, 0,
                camera.viewportWidth, camera.viewportHeight);

        text.setText(title, "DELETE ALL HIGH SCORE DATA?");
        title.draw(sb, text,
                (camera.viewportWidth - text.width) / 2,
                camera.viewportHeight * 0.7f);

        text.setText(title, "Yes");
        float fixWidth = text.width;
        title.draw(sb, text,
                (camera.viewportWidth - text.width) / 2 - fixWidth,
                camera.viewportHeight * 0.5f);
        buttonBoundsX[0] = (camera.viewportWidth - text.width) / 2 - fixWidth; // left bound
        buttonLength[0] = fixWidth;
        buttonBoundsY[0] = camera.viewportHeight * 0.5f - text.height;
        buttonHeight[0] = text.height;

        text.setText(title, "No");
        title.draw(sb, text,
                (camera.viewportWidth - text.width) / 2 + fixWidth,
                camera.viewportHeight * 0.5f);
        buttonBoundsX[1] = (camera.viewportWidth - text.width) / 2 + fixWidth; // left bound
        buttonLength[1] = fixWidth;
        buttonBoundsY[1] = camera.viewportHeight * 0.5f - text.height;
        buttonHeight[1] = text.height;


        sb.end();

    }

    @Override
    public void dispose() {
        title.dispose();
        background.dispose();
    }
}
