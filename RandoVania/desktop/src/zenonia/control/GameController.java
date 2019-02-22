package zenonia.control;

import com.badlogic.gdx.math.Rectangle;
import zenonia.model.Map;
import zenonia.model.Player;
import zenonia.model.Viewport;
import zenonia.model.Wall;
import zenonia.view.graphics.WorldGraphics;


public class GameController {
    public static boolean DEBUG_MODE = true;

    public static final float START_X = 0;
    public static final float START_Y = 0;

    protected GameApplication parent;
    protected boolean gravityOn = true;
    protected Map gameMap;
    protected PlayerController playerController;

    public GameController(GameApplication parent) {
        gameMap = new Map();
        playerController = new PlayerController(this);
        if(gravityOn)
            playerController.moveDown = true;
        this.parent = parent;
    }

    public void update(float delta) {
        playerController.move(delta);
    }

    public Map getGameMap() {
        return gameMap;
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
        for (Wall wall : gameMap.getWalls())
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
        gameMap.createTextures();
        playerController.createTextures();
    }

    public void disposeTextures() {
        gameMap.disposeTextures();
        playerController.disposeTextures();
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    /*
     *   DEBUG Methods
     */
    public static float ZOOM_STEP = (float).02;
    public static float ZOOM_LEVEL = (float).4;

    public void createWall(float x, float y) {
        if(!DEBUG_MODE)
            return;
        gameMap.createWall(x - 50, y - 50, 100, 100);
    }

    public void zoomIn() {
        if(!DEBUG_MODE)
            return;
        getCamera().zoom -= ZOOM_STEP;
        System.out.println("Zoom: " + getCamera().zoom);
    }

    public void zoomOut() {
        if(!DEBUG_MODE)
            return;
        getCamera().zoom += ZOOM_STEP;
        System.out.println("Zoom: " + getCamera().zoom);
    }

}
