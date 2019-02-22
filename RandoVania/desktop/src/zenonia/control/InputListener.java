package zenonia.control;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;

import java.awt.event.MouseEvent;

public class InputListener implements InputProcessor {
    public static int RIGHT = 22;
    public static int LEFT = 21;
    public static int UP = 19;
    public static int DOWN = 20;
    public static int RIGHT_ALT = 32;
    public static int LEFT_ALT = 29;
    public static int UP_ALT = 51;
    public static int DOWN_ALT = 47;
    public static int JUMP = 62;
    public static int GRAVITY = 35;
    public static int ZOOM_IN = 70;
    public static int ZOOM_OUT = 69;

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

    protected float startX, startY;
    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        Vector3 v = new Vector3(x, y, 0);
        controller.getCamera().unproject(v);
        System.out.println("Mouse pressed: (" + (v.x ) + ", " + (v.y ) + ")");
        startX = v.x;
        startY = v.y;
        if(button == Input.Buttons.RIGHT)
            createWall(v.x, v.y);
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        Vector3 v = new Vector3(x, y, 0);
        controller.getCamera().unproject(v);
        float width = Math.abs(startX - v.x);
        float height = Math.abs(startY - v.y);
        if(startX > v.x)
            startX = v.x;
        if(startY > v.y)
            startY = v.y;
        System.out.println("Mouse released: (" + startX + ", " + startY + ", " + width + ", " + height + ")");
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
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

    protected void keyPressedGravity() {
        controller.toggleGravity();
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

    protected void createWall(float x, float y) {
        controller.createWall(x, y);
    }

    protected void keyPressedZoomIn() {
        controller.zoomIn();
    }

    protected void keyPressedZoomOut() {
        controller.zoomOut();
    }
}
