package zenonia.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import zenonia.control.GameApplication;
import zenonia.control.GameController;
import zenonia.model.Viewport;
import zenonia.view.graphics.Assets;

public class ScreenController extends Game {
    public SpriteBatch batch;
    protected GameApplication parent;
    protected Viewport camera;

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
    }

    @Override
   	public void render() {
   		super.render();
   	}

    @Override
   	public void dispose () {
   		batch.dispose();
        getGameController().disposeTextures();
   	}

   	protected GameController getGameController() {
        return parent.getGameController();
    }

    public Viewport getCamera() {
        return camera;
    }
}
