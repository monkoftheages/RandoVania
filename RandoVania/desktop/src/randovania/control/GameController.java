package randovania.control;

import com.badlogic.gdx.math.Rectangle;
import randovania.model.objects.World;
import randovania.model.objects.Player;
import randovania.model.Viewport;
import randovania.model.objects.Wall;
import randovania.view.graphics.WorldGraphics;


public class GameController {
    public static boolean DEBUG_MODE = true;
    public static boolean SHOW_WALLS = false;
    public static float ZOOM_LEVEL = (float) .4;

    public static final float START_X = 0;
    public static final float START_Y = 0;

    protected GameApplication parent;
    protected boolean gravityOn = true;
    protected World gameWorld;
    protected PlayerController playerController;
    protected LevelEditorController levelEditorController;

    public GameController(GameApplication parent) {
        gameWorld = new World();
        playerController = new PlayerController(this);
        levelEditorController = new LevelEditorController(this);
        if (gravityOn)
            playerController.moveDown = true;
        this.parent = parent;
    }

    public void update(float delta) {
        playerController.move(delta);
    }

    public World getGameWorld() {
        return gameWorld;
    }

    public Player getPlayer() {
        return playerController.getPlayer();
    }

    public void toggleGravity() {
        gravityOn = !gravityOn;
        if (gravityOn) {
            playerController.moveUp = false;
            playerController.moveDown = true;
        } else {
            playerController.moveDown = false;
        }
    }

    protected Wall hasWallCollision(Rectangle playerBox) {
        for (Wall wall : gameWorld.getWalls())
            if (wall.getBoundingBox().overlaps(playerBox)) {
                return wall;
            }
        return null;
    }

    protected WorldGraphics getGraphics() {
        return parent.getBoardGraphics();
    }

    protected Viewport getCamera() {
        return parent.getScreenController().getCamera();
    }

    public boolean isGravityOn() {
        return gravityOn;
    }

    public void createTextures() {
        gameWorld.createTextures();
        playerController.createTextures();
    }

    public void disposeTextures() {
        gameWorld.disposeTextures();
        playerController.disposeTextures();
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public LevelEditorController getLevelEditorController() {
        return levelEditorController;
    }

}
