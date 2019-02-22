package com.mygdx.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TestingScreenAdapter extends ScreenAdapter {
    protected TestingGame game;
    protected boolean initialized = false;
    protected SpriteBatch batch;
    protected Texture character, wallTexture, map;
    protected OrthographicCamera camera;


    public TestingScreenAdapter(TestingGame game) {
        this.game = game;
    }

    public void render(float delta) {
        create();
        //Cap on delta to 1/30, force 30 fps
        delta = Math.min(delta, (float) 1 / (float) 30);
        update(delta);
        draw();
    }

    protected boolean f = false;
    protected static int MOVE_SPEED = 200;

    protected void update(float delta) {
        if (Gdx.input.isKeyPressed(22)) {
            camera.translate(-MOVE_SPEED * delta, 0);
            camera.update();
        } else if (Gdx.input.isKeyPressed(21)) {
            camera.translate(MOVE_SPEED * delta, 0);
            camera.update();
        } else if (Gdx.input.isKeyPressed(19)) {
            camera.translate(0, MOVE_SPEED * delta);
            camera.update();
        } else if (Gdx.input.isKeyPressed(20)) {
            camera.translate(0, -MOVE_SPEED * delta);
            camera.update();
        }
//            camera.translate(-100, 0);
//            camera.update();
    }

    protected void draw() {
        batch.begin();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
//        batch.draw(wallTexture, 0, 0, 0, 0, wallTexture.getWidth(), wallTexture.getHeight());
        batch.draw(map, 0, 0, 0, 0, map.getWidth(), map.getHeight());
        batch.draw(character, 0, 0, 0, 0, character.getWidth(), character.getHeight());
        batch.end();
    }

    public void dispose() {
        batch.dispose();
        character.dispose();
        wallTexture.dispose();
        map.dispose();
    }

    protected void create() {
        if (initialized)
            return;
        camera = new OrthographicCamera(640, 480);
        camera.position.set(640 / 2, 480 / 2, 0);
        initialized = true;
        batch = new SpriteBatch();
        character = new Texture("character.png");
        wallTexture = createTexture();
        map = new Texture("map.png");
    }

    protected Texture createTexture() {
        int w = 100;
        int h = 100;
        Pixmap p = new Pixmap(w, h, Pixmap.Format.RGBA8888);
        p.setColor(Color.BLUE);
        p.fillRectangle(0, 0, w, h);
        Texture t = new Texture(p);
        p.dispose();
        return t;
    }
}
