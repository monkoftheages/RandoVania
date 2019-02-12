package zenonia.control;


import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import zenonia.view.GameFrame;

public class MSKeyListener implements KeyListener, MouseListener {
    public static int RIGHT = 39;
    public static int LEFT = 37;
    public static int UP = 38;
    public static int DOWN = 40;
    public static int RIGHT_ALT = 68;
    public static int LEFT_ALT = 65;
    public static int UP_ALT = 87;
    public static int DOWN_ALT = 83;
    public static int JUMP = 38;

    protected GameController controller;
    protected GameFrame parent;
    protected boolean testCheck = false;

    public MSKeyListener(GameController controller, GameFrame parent) {
        this.controller = controller;
        this.parent = parent;
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
//        System.out.println("key " + e.getKeyCode());
        int keyCode = e.getKeyCode();
        if (keyCode == RIGHT || keyCode == RIGHT_ALT)
            keyPressedRight();
        else if (keyCode == LEFT || keyCode == LEFT_ALT)
            keyPressedLeft();
        else if (keyCode == UP || keyCode == UP_ALT)
            keyPressedUp();
        else if (keyCode == DOWN || keyCode == DOWN_ALT)
            keyPressedDown();
//        else if (keyCode == CLEAR)
//            keyPressedClear();
//        else if (keyCode == FLAG || keyCode == FLAG_ALT)
//            keyPressedFlag();
//        else if (keyCode == FAST_CLEAR)
//            keyPressedFastClear();
//        else if (keyCode == RESTART)
//            keyPressedRestartGame();
//        else if (keyCode == SOLVE)
//            keyPressedSolve();
    }

    protected void keyPressedRight() {
        controller.startMovingRight();
    }

    protected void keyPressedLeft() {
        controller.startMovingLeft();
    }

    protected void keyPressedUp() {
        controller.startMovingUp();
    }

    protected void keyPressedDown() {
        controller.startMovingDown();
    }

    protected void keyPressedClear() {
    }

    protected void keyPressedRestartGame() {
    }

    protected void keyPressedFlag() {
    }

    protected void keyPressedFastClear() {
    }

    protected void keyPressedSolve() {
    }

    protected void keyPressedQuery() {
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == RIGHT || keyCode == RIGHT_ALT)
            keyReleasedRight();
        else if (keyCode == LEFT || keyCode == LEFT_ALT)
            keyReleasedLeft();
        else if (keyCode == UP || keyCode == UP_ALT)
            keyReleasedUp();
        else if (keyCode == DOWN || keyCode == DOWN_ALT)
            keyReleasedDown();
    }

    protected void keyReleasedUp() {
        controller.stopMovingUp();
    }

    protected void keyReleasedDown() {
        controller.stopMovingDown();
    }

    protected void keyReleasedLeft() {
        controller.stopMovingLeft();
    }

    protected void keyReleasedRight() {
        controller.stopMovingRight();
    }

    //Mouse listener section

    public void mouseClicked(MouseEvent e) {

    }

    protected int startX, startY;
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse pressed: (" + (e.getX() + controller.getViewport().getX()) + ", " + (e.getY() + controller.getViewport().getY()) + ")");
        startX = (e.getX() + controller.getViewport().getX());
        startY = (e.getY() + controller.getViewport().getY());
    }

    public void mouseReleased(MouseEvent e) {
        int newX = (e.getX() + controller.getViewport().getX());
        int newY = (e.getY() + controller.getViewport().getY());
        int width = Math.abs(startX - newX);
        int height = Math.abs(startY - newY);
        if(startX > newX)
            startX = newX;
        if(startY > newY)
            startY = newY;
        System.out.println("Mouse released: (" + startX + ", " + startY + ", " + width + ", " + height + ")");
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
