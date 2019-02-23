package randovania.control;

import com.badlogic.gdx.math.Rectangle;
import randovania.model.Map;
import randovania.model.Player;
import randovania.model.Viewport;
import randovania.model.Wall;
import randovania.view.graphics.WorldGraphics;


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
        if (gravityOn)
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
    public static float ZOOM_STEP = (float) .02;
    public static float ZOOM_LEVEL = (float) .4;

    protected Wall heldWall = null;

    public void createWall(float x, float y) {
        if (!DEBUG_MODE)
            return;
        gameMap.createWall(x - 50, y - 50, 100, 100);
    }

    public void unholdWall()  {
        heldWall = null;
    }

    public void holdWall(float x, float y) {
        if (!DEBUG_MODE)
            return;
        heldWall = hasWallCollision(new Rectangle(x, y, 1, 1));
    }

    public void moveWall(float x, float y) {
        if (!DEBUG_MODE)
            return;
        if(heldWall == null)
            return;
        heldWall.moveWall(x, y);
    }

    public void zoomIn() {
        if (!DEBUG_MODE)
            return;
        getCamera().zoom -= ZOOM_STEP;
        System.out.println("Zoom: " + getCamera().zoom);
    }

    public void zoomOut() {
        if (!DEBUG_MODE)
            return;
        getCamera().zoom += ZOOM_STEP;
        System.out.println("Zoom: " + getCamera().zoom);
    }

    public void printWallInfo() {
        gameMap.printWallInfo();
    }

}
