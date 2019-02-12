package zenonia.model;

import zenonia.view.BoardGraphics;

import java.awt.*;

/**
 * Created by b3_authors on 2/11/2019.
 */
public class Viewport {
    protected Rectangle viewportRectangle;

    public Viewport() {
        viewportRectangle = new Rectangle(0, 680, BoardGraphics.PREF_W, BoardGraphics.PREF_H);
    }

    public int getX() {
        return (int)viewportRectangle.getX();
    }

    public int getY() {
        return (int)viewportRectangle.getY();
    }

    public int getWidth() {
        return (int)viewportRectangle.getWidth();
    }

    public int getHeight() {
        return (int)viewportRectangle.getHeight();
    }

    public void moveLeft(int distance) {
        if((viewportRectangle.x - distance) <  0)
            viewportRectangle.x = 0;
        else
            viewportRectangle.x = viewportRectangle.x - distance;
    }

    public void moveRight(int distance) {
        if((viewportRectangle.x + viewportRectangle.width + distance) >  Map.MAP_MAX_X)
            viewportRectangle.x = Map.MAP_MAX_X - viewportRectangle.width;
        else
            viewportRectangle.x = viewportRectangle.x + distance;
    }

    public void moveUp(int distance) {
        if((viewportRectangle.y - distance) <  0)
            viewportRectangle.y = 0;
        else
            viewportRectangle.y = viewportRectangle.y - distance;
    }

    public void moveDown(int distance) {
        if((viewportRectangle.y + viewportRectangle.height + distance) >  Map.MAP_MAX_Y)
            viewportRectangle.y = Map.MAP_MAX_Y - viewportRectangle.height;
        else
            viewportRectangle.y = viewportRectangle.y + distance;
    }
}
