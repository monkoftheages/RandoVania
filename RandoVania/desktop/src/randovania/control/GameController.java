package randovania.control;

import com.badlogic.gdx.math.Rectangle;
import randovania.model.objects.TransitionWall;
import randovania.model.objects.World;
import randovania.model.objects.Player;
import randovania.model.Viewport;
import randovania.model.objects.Wall;
import randovania.view.graphics.FadeScreen;
import randovania.view.graphics.WorldGraphics;


public class GameController {
    public static boolean DEBUG_MODE = true;
    public static boolean SHOW_WALLS = false;
    public static float ZOOM_LEVEL = (float) .4;
    public static final float START_X = 0;
    public static final float START_Y = 0;

    protected boolean transitioning = false;
    protected boolean fading = false;
    protected boolean gravityOn = true;

    protected GameApplication parent;
    protected TransitionWall transitioningWall;
    protected World gameWorld;
    protected PlayerController playerController;
    protected LevelEditorController levelEditorController;
    protected FadeScreen fadeScreen;

    public GameController(GameApplication parent) {
        gameWorld = new World();
        playerController = new PlayerController(this);
        levelEditorController = new LevelEditorController(this);
        if (gravityOn)
            playerController.moveDown = true;
        this.parent = parent;
        fadeScreen = new FadeScreen(this);
    }

    public void update(float delta) {
        if(transitioning)
            return;
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

    public void handleTransition(TransitionWall wall) {
        if(fading)
            return;
        fading = true;
        getPlayerController().handleTransition();
        transitioning = true;
        transitioningWall = wall;
        getFadeScreen().fadeOut();
    }

    public void finishedFadingIn() {
        fading = false;
    }

    public void finishedFadingOut() {
        gameWorld.setCurrentRoom(transitioningWall.connectingRoom);
        getPlayer().setLocationAndRect(transitioningWall.playerStartX, transitioningWall.playerStartY);
        getCamera().setLocation(transitioningWall.playerStartX, transitioningWall.playerStartY);
        getFadeScreen().fadeIn();
        transitioning = false;
    }

    public boolean isTransitioning() {
        return transitioning;
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
        fadeScreen.createTextures();
    }

    public void disposeTextures() {
        gameWorld.disposeTextures();
        playerController.disposeTextures();
        fadeScreen.disposeTextures();
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public LevelEditorController getLevelEditorController() {
        return levelEditorController;
    }

    public FadeScreen getFadeScreen() {
        return fadeScreen;
    }

}
