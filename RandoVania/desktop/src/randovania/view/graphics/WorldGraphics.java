package randovania.view.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import randovania.control.GameController;
import randovania.control.GameApplication;
import randovania.model.Viewport;
import randovania.view.ScreenController;

public class WorldGraphics extends ScreenAdapter {
	protected GameApplication parent;
	protected boolean allowDrawing = false;
	protected Color fadeColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);

	public WorldGraphics(GameApplication parent) {
		this.parent = parent;
	}

	@Override
	public void render (float delta) {
		//Cap on delta to 1/30, help reduce jumps when on low fps
		delta = Math.min(delta, (float)1/(float)30);
		update(delta);
		draw(delta);
	}

	protected void update(float delta) {
		getGameController().update(delta);
	}

	protected void draw(float delta) {
		if(!allowDrawing)
			return;
		getCamera().update();
		getBatch().setProjectionMatrix(getCamera().combined);
		getBatch().enableBlending();
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		getBatch().begin();
		getGameController().getGameWorld().paintComponent(getBatch(), 0, 0);
		getGameController().getPlayer().paintComponent(getBatch(), 0, 0, delta);
		getGameController().getFadeScreen().paintComponent(getBatch(), delta, getCamera().getX(), getCamera().getY());
		getBatch().end();
	}


	public void allowDrawing(boolean allow) {
		allowDrawing = allow;
	}

	protected ScreenController getScreenController() {
		return parent.getScreenController();
	}

	protected GameController getGameController() {
		return parent.getGameController();
	}

	protected Viewport getCamera() {
		return getScreenController().getCamera();
	}

	public SpriteBatch getBatch() {
		return getScreenController().batch;
	}
}
