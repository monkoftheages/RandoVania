package randovania.control;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import randovania.model.objects.Wall;
import randovania.utilities.Utilities;

public class LevelEditorController {
    public static final int TOP_HELD = 1;
    public static final int BOTTOM_HELD = 2;
    public static final int LEFT_HELD = 3;
    public static final int RIGHT_HELD = 4;
    public static final int MIDDLE_HELD = -1;
    protected int sideHeld = 0;

    protected GameController gameController;
    protected float startX, startY;

    public LevelEditorController(GameController gameController) {
        this.gameController = gameController;
    }

    /*
     *   DEBUG Methods
     */
    public static float ZOOM_STEP = (float) .02;

    protected Wall heldWall = null;

    public void createWall(float x, float y) {
        if (!GameController.DEBUG_MODE)
            return;
        gameController.gameWorld.createWall(x - 50, y - 50, 100, 100);
    }

    public void deleteWall(float x, float y) {
        if (!GameController.DEBUG_MODE)
            return;
        heldWall = null;
        Wall toDelete = gameController.hasWallCollision(new Rectangle(x, y, 1, 1));
        gameController.gameWorld.deleteWall(toDelete);
    }

    public void unholdWall() {
        if (!GameController.DEBUG_MODE)
            return;
        heldWall = null;
    }

    public void holdWall(float x, float y) {
        if (!GameController.DEBUG_MODE)
            return;
        heldWall = gameController.hasWallCollision(new Rectangle(x, y, 1, 1));
        if (heldWall != null)
            checkForSideHeld(x, y);
    }

    protected void checkForSideHeld(float x, float y) {
        if (Utilities.close(x, heldWall.getBoundingBox().getX())) {
            sideHeld = LEFT_HELD;
        } else if (Utilities.close(x, heldWall.getBoundingBox().getX() + heldWall.getBoundingBox().getWidth())) {
            sideHeld = RIGHT_HELD;
        } else if (Utilities.close(y, heldWall.getBoundingBox().getY())) {
            sideHeld = BOTTOM_HELD;
        } else if (Utilities.close(y, heldWall.getBoundingBox().getY() + heldWall.getBoundingBox().getHeight())) {
            sideHeld = TOP_HELD;
        } else {
            sideHeld = MIDDLE_HELD;
        }
    }

    public void moveWall(float x, float y) {
        if (!GameController.DEBUG_MODE)
            return;
        if (heldWall == null)
            return;
        switch (sideHeld) {
            case LEFT_HELD:
                heldWall.resize(-x, 0);
                heldWall.moveWall(x, 0);
                break;
            case RIGHT_HELD:
                heldWall.resize(x, 0);
                break;
            case TOP_HELD:
                heldWall.resize(0, y);
                break;
            case BOTTOM_HELD:
                heldWall.resize(0, -y);
                heldWall.moveWall(0, y);
                break;
            case MIDDLE_HELD:
                heldWall.moveWall(x, y);
                break;
        }
    }

    public void zoomIn() {
        if (!GameController.DEBUG_MODE)
            return;
        gameController.getCamera().zoom -= ZOOM_STEP;
        System.out.println("Zoom: " + gameController.getCamera().zoom);
    }

    public void zoomOut() {
        if (!GameController.DEBUG_MODE)
            return;
        gameController.getCamera().zoom += ZOOM_STEP;
        System.out.println("Zoom: " + gameController.getCamera().zoom);
    }

    public void printWallInfo() {
        if (!GameController.DEBUG_MODE)
            return;
        gameController.gameWorld.printWallInfo();
    }

    protected void mousePressed(float x, float y, int pointer, int button) {
        if (!GameController.DEBUG_MODE)
            return;
        System.out.println("Mouse pressed: (" + (x) + ", " + (y) + ")");
        if (button == Input.Buttons.RIGHT)
            createWall(x, y);
        else if (button == Input.Buttons.LEFT) {
            startX = x;
            startY = y;
            holdWall(x, y);
        }
    }

    protected void mouseReleased(float x, float y, int pointer, int button) {
        if (!GameController.DEBUG_MODE)
            return;
        float width = Math.abs(startX - x);
        float height = Math.abs(startY - y);
        if (startX > x)
            startX = x;
        if (startY > y)
            startY = y;
//        System.out.println("Mouse released: (" + startX + ", " + startY + ", " + width + ", " + height + ")");
        if (button == Input.Buttons.LEFT)
            unholdWall();
    }

    protected void mouseDragged(float x, float y) {
        if (!GameController.DEBUG_MODE)
            return;
        moveWall(x - startX, y - startY);
        startX += x - startX;
        startY += y - startY;
    }

    public void hideShowWalls() {
        if (!GameController.DEBUG_MODE)
            return;
        GameController.SHOW_WALLS = !GameController.SHOW_WALLS;
    }

    public static boolean DO_FADE = false;
    public void testScript1() {
        if (!GameController.DEBUG_MODE)
            return;
//        DO_FADE = true;
//        gameController.getFadeScreen().increaseFade(.01f);
        gameController.getFadeScreen().fadeOut();
    }

    public void testScript2() {
        if (!GameController.DEBUG_MODE)
            return;
//        gameController.getFadeScreen().decreaseFade(.01f);
//        DO_FADE = true;
        gameController.getFadeScreen().fadeIn();
    }
}
