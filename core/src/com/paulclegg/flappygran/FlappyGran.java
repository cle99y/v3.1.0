package com.paulclegg.flappygran;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.paulclegg.flappygran.States.GameStateManager;
import com.paulclegg.flappygran.States.MenuState;

public class FlappyGran extends ApplicationAdapter {

	public static int WIDTH = 720;
	public static int HEIGHT = 1280;

	public static final String TITLE = "Flappy Gran";

	private GameStateManager gsm;
	public SpriteBatch batch;
	Texture img;

	public BitmapFont font;

	@Override
	public void create () {

		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("flappygran.fnt"));
		font.setColor(Color.WHITE);
		gsm = new GameStateManager();
		img = new Texture("badlogic.jpg");
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
