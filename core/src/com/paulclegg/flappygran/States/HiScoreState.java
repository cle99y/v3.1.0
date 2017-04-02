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
 * Created by cle99 on 01/04/2017.
 */

public class HiScoreState extends States {

    private BitmapFont hiScoreFontTitle, hiScoreFont;
    private Texture background;
    private GlyphLayout title;
    private GlyphLayout scoreText;
    private Vector3 menuSelect;
    private float[] buttonBoundsX;
    private float[] buttonLength;
    private float[] buttonBoundsY;
    private float[] buttonHeight;
    private String[] labels = {"RESET HIGH SCORES", "BACK"};


    public HiScoreState(GameStateManager gsm) {
        super(gsm);

        //Preferences.reset();

        hiScoreFont = new BitmapFont(Gdx.files.internal("hiscore.fnt"));
        hiScoreFontTitle = new BitmapFont(Gdx.files.internal("hiscore.fnt"));

        hiScoreFontTitle.setColor(Color.WHITE);
        hiScoreFont.setColor(Color.GOLD);

        hiScoreFontTitle.getData().setScale(2.0f);
        hiScoreFont.getData().setScale(1.3f);

        title = new GlyphLayout();
        scoreText = new GlyphLayout();

        background = new Texture("plain_purple.png");

        camera.setToOrtho(false, FlappyGran.WIDTH, FlappyGran.HEIGHT);

        buttonBoundsX = new float[] {0f, 0f};
        buttonLength = new float[] {0f, 0f};
        buttonBoundsY = new float[] {0f, 0f};
        buttonHeight = new float[] {0f, 0f};

        menuSelect = new Vector3();

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
                gsm.set(new ResetScores(gsm));
            }

            if (menuSelect.x > buttonBoundsX[1] &&
                    menuSelect.x < buttonBoundsX[1] + buttonLength[1] &&
                    menuSelect.y > buttonBoundsY[1] &&
                    menuSelect.y < buttonBoundsY[1] + buttonHeight[1]) {
                gsm.set(new MainMenuState(gsm));
            }


        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {

        float screenHeight = camera.viewportHeight * 0.95f;

        sb.setProjectionMatrix(camera.combined);
        sb.begin();

        // --- TITLE ---

        sb.draw(background,
                camera.position.x - camera.viewportWidth / 2, 0,
                camera.viewportWidth, camera.viewportHeight);

        title.setText(hiScoreFontTitle, "YOUR HIGH SCORES");
        hiScoreFontTitle.draw(sb, title,
                (camera.viewportWidth - title.width) / 2,
                screenHeight);
        screenHeight -= 3 * title.height;

        // --- EASY LEVEL HEADER ---

        title.setText(hiScoreFontTitle, "EASY LEVEL");
        hiScoreFontTitle.draw(sb, title,
                (camera.viewportWidth - title.width) / 2,
                screenHeight);
        screenHeight -= 2 * title.height;

        // --- EASY LEVEL DATA ---

        scoreText.setText(hiScoreFont, "Total Games Played: " + getGamesAndScores(Preferences.EASY)[0]);
        hiScoreFont.draw(sb, scoreText,
                (camera.viewportWidth - scoreText.width) / 2f,
                screenHeight);
        screenHeight -= scoreText.height * 1.5f;

        scoreText.setText(hiScoreFont, "Total Points Earned: " + getGamesAndScores(Preferences.EASY)[1]);
        hiScoreFont.draw(sb, scoreText,
                (camera.viewportWidth - scoreText.width) / 2f,
                screenHeight);
        screenHeight -= scoreText.height * 1.5f;

        scoreText.setText(hiScoreFont, "High Score: " + getGamesAndScores(Preferences.EASY)[2]);
        hiScoreFont.draw(sb, scoreText,
                (camera.viewportWidth - scoreText.width) / 2f,
                screenHeight);
        screenHeight -= title.height * 2f;

        // --- TRICKY LEVEL HEADER ---

        title.setText(hiScoreFontTitle, "TRICKY LEVEL");
        hiScoreFontTitle.draw(sb, title,
                (camera.viewportWidth - title.width) / 2,
                screenHeight);
        screenHeight -= 2 * title.height;

        // --- TRICKY LEVEL DATA ---

        scoreText.setText(hiScoreFont, "Total Games Played: " + getGamesAndScores(Preferences.MEDIUM)[0]);
        hiScoreFont.draw(sb, scoreText,
                (camera.viewportWidth - scoreText.width) / 2f,
                screenHeight);
        screenHeight -= scoreText.height * 1.5f;

        scoreText.setText(hiScoreFont, "Total Points Earned: " + getGamesAndScores(Preferences.MEDIUM)[1]);
        hiScoreFont.draw(sb, scoreText,
                (camera.viewportWidth - scoreText.width) / 2f,
                screenHeight);
        screenHeight -= scoreText.height * 1.5f;

        scoreText.setText(hiScoreFont, "High Score: " + getGamesAndScores(Preferences.MEDIUM)[2]);
        hiScoreFont.draw(sb, scoreText,
                (camera.viewportWidth - scoreText.width) / 2f,
                screenHeight);
        screenHeight -= title.height * 2f;

        // --- ANNOYING LEVEL HEADER

        title.setText(hiScoreFontTitle, "ANNOYING LEVEL");
        hiScoreFontTitle.draw(sb, title,
                (camera.viewportWidth - title.width) / 2,
                screenHeight);
        screenHeight -= 2 * title.height;

        // --- ANNOYING LEVEL DATA ---

        scoreText.setText(hiScoreFont, "Total Games Played: " + getGamesAndScores(Preferences.HARD)[0]);
        hiScoreFont.draw(sb, scoreText,
                (camera.viewportWidth - scoreText.width) / 2f,
                screenHeight);
        screenHeight -= scoreText.height * 1.5f;

        scoreText.setText(hiScoreFont, "Total Points Earned: " + getGamesAndScores(Preferences.HARD)[1]);
        hiScoreFont.draw(sb, scoreText,
                (camera.viewportWidth - scoreText.width) / 2f,
                screenHeight);
        screenHeight -= scoreText.height * 1.5f;

        scoreText.setText(hiScoreFont, "High Score: " + getGamesAndScores(Preferences.HARD)[2]);
        hiScoreFont.draw(sb, scoreText,
                (camera.viewportWidth - scoreText.width) / 2f,
                screenHeight);
        screenHeight -= title.height * 3f;

        // --- Reset high scores option ---

        title.setText(hiScoreFontTitle, labels[0]);
        hiScoreFontTitle.draw(sb, title,
                (camera.viewportWidth - title.width) / 2f,
                screenHeight);

        // set bounds for click events
        buttonBoundsX[0] = (camera.viewportWidth - title.width) / 2; // left bound
        buttonLength[0] = title.width;
        buttonBoundsY[0] = screenHeight - title.height;
        buttonHeight[0] = title.height;
        screenHeight -= title.height * 3f;

        // --- Return to main menu ---

        title.setText(hiScoreFontTitle, labels[1]);
        hiScoreFontTitle.draw(sb, title,
                (camera.viewportWidth - title.width) / 2f,
                screenHeight);

        // set bounds for click events
        buttonBoundsX[1] = (camera.viewportWidth - title.width) / 2; // left bound
        buttonLength[1] = title.width;
        buttonBoundsY[1] = screenHeight - title.height;
        buttonHeight[1] = title.height;

        sb.end();

    }

    private int[] getGamesAndScores(int level) {

        int gamesPlayed = Preferences.getGamesPlayed(level);
        int hiSc;

        if (level == Preferences.EASY) {
            hiSc = Preferences.getHighScore(Preferences.EASY);

        } else if (level == Preferences.MEDIUM) {
            hiSc = Preferences.getHighScore(Preferences.MEDIUM);

        } else {
            hiSc = Preferences.getHighScore(Preferences.HARD);
        }

        int total = Preferences.getTotalPoints(level);

        int[] stats = {gamesPlayed, total, hiSc};

        return stats;

    }

    @Override
    public void dispose() {
        background.dispose();
        hiScoreFont.dispose();
        hiScoreFontTitle.dispose();
    }
}
