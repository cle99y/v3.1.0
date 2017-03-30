package com.paulclegg.flappygran.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.paulclegg.flappygran.FlappyGran;
import com.paulclegg.flappygran.Preferences;
import com.paulclegg.flappygran.Sprites.GameScore;

/**
 * Created by cle99 on 26/03/2017.
 */

public class GameOver extends States {

    private static final Color FOR_GAME_OVER = new Color(0xf7f6c9ff);

    private Texture background;
    private Texture gameOver;
    private Vector3 newGame;
    private Texture playAgain;
    private BitmapFont scoreFont, replayFont;
    private GlyphLayout textLayout;
    private int lastScore;


    public GameOver(GameStateManager gsm, int last) {
        super(gsm);
        lastScore = last;
        scoreFont = new BitmapFont(Gdx.files.internal("flappygran.fnt"));
        scoreFont.getData().setScale(1.5f);
        scoreFont.setColor(Color.MAROON);
        replayFont = new BitmapFont(Gdx.files.internal("flappygran.fnt"));
        replayFont.getData().setScale(1.2f);
        replayFont.setColor(FOR_GAME_OVER);

        textLayout = new GlyphLayout();
        camera.setToOrtho(false, FlappyGran.WIDTH, FlappyGran.HEIGHT);
        gameOver = new Texture("gameover.png");
        background = new Texture("bg.png");
        playAgain = new Texture("playagain.png");
        newGame = new Vector3();
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {

            // get screen co-ordinates of touch or mouse click

            int xPos = Gdx.input.getX();
            int yPos = Gdx.input.getY();
            newGame.set(xPos, yPos, 0);
            camera.unproject(newGame);

            // check that the screen is touched/clicked within the bounds of the 'play again' icon

            if (newGame.x > (camera.viewportWidth - playAgain.getWidth()) / 2 &&
                    newGame.x < (camera.viewportWidth + playAgain.getWidth()) / 2 &&
                    newGame.y > camera.viewportHeight * 0.22f &&
                    newGame.y < camera.viewportHeight * 0.22f + playAgain.getHeight()) {
                GameScore.reset();
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
            sb.draw(gameOver, (camera.viewportWidth - gameOver.getWidth()) / 2,
                    (camera.viewportHeight - gameOver.getHeight()) / 2,
                    gameOver.getWidth(), gameOver.getHeight());
            textLayout.setText(scoreFont, "You scored: " + String.valueOf(lastScore));
            scoreFont.draw(sb, textLayout,
                    (camera.viewportWidth - textLayout.width) / 2,
                    camera.viewportHeight * 0.15f);
            textLayout.setText(scoreFont, Preferences.highScoreToString());
            scoreFont.draw(sb, textLayout,
                    (camera.viewportWidth - textLayout.width) / 2,
                    camera.viewportHeight * 0.1f);
            sb.draw(playAgain,
                    (camera.viewportWidth - playAgain.getWidth()) / 2,
                    (camera.viewportHeight * 0.22f),
                    playAgain.getWidth(),
                    playAgain.getHeight());

        sb.end();


    }

    @Override
    public void dispose() {
        background.dispose();
        playAgain.dispose();
        gameOver.dispose();
    }
}
