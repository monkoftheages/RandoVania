package randovania.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;

public class InputListener implements InputProcessor {
    public static int RIGHT = 22;
    public static int LEFT = 21;
    public static int UP = 19;
    public static int DOWN = 20;
    public static int RIGHT_ALT = 32;
    public static int LEFT_ALT = 29;
    public static int UP_ALT = 51;
    public static int DOWN_ALT = 47;
    //Spacebar
    public static int JUMP = 62;
    //G
    public static int GRAVITY = 35;
    //+
    public static int ZOOM_IN = 70;
    //-
    public static int ZOOM_OUT = 69;
    //P
    public static int PRINT_WALLS = 44;
    //N
    public static int NEW_WALL = 42;
    //M
    public static int DELETE_WALL = 41;
    //V
    public static int HIDE_SHOW_WALLS = 50;


    protected GameController controller;

    public InputListener(GameController c) {
        controller = c;
    }

    @Override
    public boolean keyDown(int keyCode) {
//        System.out.println("Key: " + keyCode);

        if (keyCode == RIGHT || keyCode == RIGHT_ALT)
            keyPressedRight();
        else if (keyCode == LEFT || keyCode == LEFT_ALT)
            keyPressedLeft();
        else if (keyCode == UP || keyCode == UP_ALT)
            keyPressedUp();
        else if (keyCode == DOWN || keyCode == DOWN_ALT)
            keyPressedDown();
        else if (keyCode == JUMP)
            keyPressedJump();
        else if (keyCode == GRAVITY)
            keyPressedGravity();
        else if (keyCode == ZOOM_IN)
            keyPressedZoomIn();
        else if (keyCode == ZOOM_OUT)
            keyPressedZoomOut();
        else if (keyCode == PRINT_WALLS)
            keyPressedPrintWalls();
        else if (keyCode == NEW_WALL)
            createNewWall();
        else if (keyCode == DELETE_WALL)
            deleteWall();
        else if (keyCode == HIDE_SHOW_WALLS)
            hideShowWalls();
        return false;
    }

    @Override
    public boolean keyUp(int keyCode) {
        if (keyCode == RIGHT || keyCode == RIGHT_ALT)
            keyReleasedRight();
        else if (keyCode == LEFT || keyCode == LEFT_ALT)
            keyReleasedLeft();
        else if (keyCode == UP || keyCode == UP_ALT)
            keyReleasedUp();
        else if (keyCode == DOWN || keyCode == DOWN_ALT)
            keyReleasedDown();
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        Vector3 v = new Vector3(x, y, 0);
        controller.getCamera().unproject(v);
        mousePressed(v.x, v.y, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        Vector3 v = new Vector3(x, y, 0);
        controller.getCamera().unproject(v);
        mouseReleased(v.x, v.y, pointer, button);
        return false;
    }

    @Override
    public boolean touchDragged(int x, int y, int i2) {
        Vector3 v = new Vector3(x, y, 0);
        controller.getCamera().unproject(v);
        mouseDragged(v.x, v.y);
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }

    protected void keyPressedGravity() {
        controller.toggleGravity();
    }

    /*
     * Player control functions
     */

    protected void keyPressedRight() {
        controller.getPlayerController().startMovingRight();
    }

    protected void keyPressedLeft() {
        controller.getPlayerController().startMovingLeft();
    }

    protected void keyPressedUp() {
        controller.getPlayerController().startMovingUp();
    }

    protected void keyPressedDown() {
        controller.getPlayerController().startMovingDown();
    }

    protected void keyPressedJump() {
        controller.getPlayerController().jump();
    }

    protected void keyReleasedUp() {
        controller.getPlayerController().stopMovingUp();
    }

    protected void keyReleasedDown() {
        controller.getPlayerController().stopMovingDown();
    }

    protected void keyReleasedLeft() {
        controller.getPlayerController().stopMovingLeft();
    }

    protected void keyReleasedRight() {
        controller.getPlayerController().stopMovingRight();
    }

    /*
     * Level Editor Functions
     */

    protected void keyPressedZoomIn() {
        controller.getLevelEditorController().zoomIn();
    }

    protected void keyPressedZoomOut() {
        controller.getLevelEditorController().zoomOut();
    }

    protected void keyPressedPrintWalls() {
        controller.getLevelEditorController().printWallInfo();
    }

    protected void mousePressed(float x, float y, int pointer, int button) {
        controller.getLevelEditorController().mousePressed(x, y, pointer, button);
    }

    protected void mouseReleased(float x, float y, int pointer, int button) {
        controller.getLevelEditorController().mouseReleased(x, y, pointer, button);
    }

    protected void mouseDragged(float x, float y) {
        controller.getLevelEditorController().mouseDragged(x, y);
    }

    protected void createNewWall() {
        Vector3 v = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        controller.getCamera().unproject(v);
        controller.getLevelEditorController().createWall(v.x, v.y);
    }

    protected void deleteWall() {
        Vector3 v = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        controller.getCamera().unproject(v);
        controller.getLevelEditorController().deleteWall(v.x, v.y);
    }

    protected void hideShowWalls() {
        controller.getLevelEditorController().hideShowWalls();
    }
}
