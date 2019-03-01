package randovania.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import randovania.control.GameApplication;
import randovania.control.GameController;
import randovania.model.Viewport;
import randovania.view.graphics.Assets;

public class ScreenController extends Game {
    public SpriteBatch batch;
    protected GameApplication parent;
    protected Viewport camera;
    public ShapeRenderer shapeRenderer;


    public ScreenController(GameApplication parent) {
        this.parent = parent;
    }

    @Override
    public void create() {
        Assets.load();
        batch = new SpriteBatch();
        getGameController().createTextures();
        camera = new Viewport(parent.getConfig());
        camera.zoom = GameController.ZOOM_LEVEL;
        camera.position.set(camera.getWidth()/2, camera.getHeight()/2, 0);
        camera.update();
        shapeRenderer = new ShapeRenderer();
    }

    @Override
   	public void render() {
   		super.render();
   	}

    @Override
   	public void dispose () {
   		batch.dispose();
        shapeRenderer.dispose();
        getGameController().disposeTextures();
   	}

   	protected GameController getGameController() {
        return parent.getGameController();
    }

    public Viewport getCamera() {
        return camera;
    }
}
