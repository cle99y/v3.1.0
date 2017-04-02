package com.paulclegg.flappygran.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.paulclegg.flappygran.FlappyGran;
import com.paulclegg.flappygran.Sprites.Button;

/**
 * Created by cle99 on 01/04/2017.
 */

public class MainMenuState extends States {

    private Texture background;
    private Button button;
    private BitmapFont menuFont, titleFont;
    private String[] labels = {"Options", "High Scores", "Instructions", "Quit", "Play"};
    public static final Color PURPLE = new Color(0x6145acff);
    private Vector3 menuSelect;
    private float[]  buttonBounds;


    public MainMenuState(GameStateManager gsm) {
        super(gsm);

        background = new Texture("plain_purple.png");
        button = new Button();

        menuFont = new BitmapFont(Gdx.files.internal("flappygran.fnt"));
        menuFont.setColor(PURPLE);
        menuFont.getData().setScale(1.4f);

        titleFont = new BitmapFont(Gdx.files.internal("flappygran.fnt"));
        titleFont.setColor(Color.WHITE);
        titleFont.getData().setScale(1.5f);

        camera.setToOrtho(false, FlappyGran.WIDTH, FlappyGran.HEIGHT);

        menuSelect = new Vector3();
        buttonBounds = new float[labels.length];

    }

    @Override
    public void handleInput() {

        if (Gdx.input.justTouched()) {

            // get the screen co-ordinates of the touch / click
            int xPos = Gdx.input.getX();
            int yPos = Gdx.input.getY();
            menuSelect.set(xPos, yPos, 0);
            camera.unproject(menuSelect);

            boolean xBounds = menuSelect.x > (camera.viewportWidth - button.getButtonWidth()) / 2 &&
                    menuSelect.x < (camera.viewportWidth + button.getButtonWidth()) / 2;

            if (xBounds) {

                if (menuSelect.y > buttonBounds[0] && menuSelect.y < buttonBounds[0] + button.getButtonHeight()) {
                    gsm.set(new OptionsState(gsm));
                }

                if (menuSelect.y > buttonBounds[1] && menuSelect.y < buttonBounds[1] + button.getButtonHeight()) {
                    gsm.set(new HiScoreState(gsm));
                }

                if (menuSelect.y > buttonBounds[2] && menuSelect.y < buttonBounds[2] + button.getButtonHeight()) {
                    gsm.set(new TutorialState(gsm));
                }

                if (menuSelect.y > buttonBounds[3] && menuSelect.y < buttonBounds[3] + button.getButtonHeight()) {
                    Gdx.app.exit();
                }

                if (menuSelect.y > buttonBounds[4] && menuSelect.y < buttonBounds[4] + button.getButtonHeight()) {
                    gsm.set(new PlayState(gsm));
                }
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

        drawButtons(sb);

        sb.end();

    }

    public void drawButtons(SpriteBatch sb) {

        int buttonSpace = 50;
        int numberOfButtons = labels.length;
        float centreY = camera.viewportHeight / 2;
        float menuHeight = numberOfButtons * button.getButtonHeight() + buttonSpace * (numberOfButtons);
        float drawHeight = centreY + menuHeight / 2 - button.getButtonHeight();
        GlyphLayout label = new GlyphLayout();

        label.setText(titleFont, "MAIN MENU");
        titleFont.draw(sb, label,
                (camera.viewportWidth - label.width) / 2f,
                camera.viewportHeight * 0.9f);

        for (int b = 0; b < numberOfButtons; b++) {

            // draw buttons
            sb.draw(button.getButton(),
                    (camera.viewportWidth - button.getButtonWidth()) / 2f,
                    drawHeight - (buttonSpace + button.getButtonHeight()) * b,
                    button.getButtonWidth(),
                    button.getButtonHeight());

            // set button bounds for bottom and top edges
            buttonBounds[b] = drawHeight - (buttonSpace + button.getButtonHeight()) * b;

            label.setText(menuFont, labels[b]);
            menuFont.draw(sb,
                    label,
                    (camera.viewportWidth - label.width) / 2f,
                    drawHeight - (buttonSpace + button.getButtonHeight()) * b + (label.height + button.getButtonHeight()) / 2);
        }
    }

    @Override
    public void dispose() {

        background.dispose();
        button.getButton().dispose();
        menuFont.dispose();

    }
}
