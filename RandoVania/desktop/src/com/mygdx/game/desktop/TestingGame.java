package com.mygdx.game.desktop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TestingGame extends Game {
	SpriteBatch batch;
	Texture img;

	public TestingGame() {

	}

	@Override
	public void create () {
	}

	@Override
	public void render () {
		super.render();
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		batch.draw(character, 0, 0, 0, 0, 400, 400);
//		batch.end();
	}
	
	@Override
	public void dispose () {
	}
}
