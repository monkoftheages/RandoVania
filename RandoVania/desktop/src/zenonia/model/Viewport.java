package zenonia.model;

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.OrthographicCamera;
import zenonia.control.GameController;

public class Viewport extends OrthographicCamera {
    //Camera is pointing at the center of the screen. Basis is the offset to help with wall math
    public static float BASIS_X;
    public static float BASIS_Y;

    public Viewport(LwjglApplicationConfiguration config) {
        super(config.width, config.height);
//        BASIS_X = config.width / 2;
//        BASIS_Y = config.height / 2;
        BASIS_X = (config.width / 2) * GameController.ZOOM_LEVEL;
        BASIS_Y = (config.height / 2) * GameController.ZOOM_LEVEL;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return viewportWidth * zoom;
    }

    public float getHeight() {
        return viewportHeight * zoom;
    }

    public void moveLeft(float distance) {
        if((position.x - distance - BASIS_X) <  0)
            position.x = BASIS_X;
        else
            position.x = position.x - distance;
    }

    public void moveRight(float distance) {
        if((position.x + getWidth() + distance - BASIS_X) >  Map.MAP_MAX_X)
            position.x = Map.MAP_MAX_X - getWidth() + BASIS_X;
        else
            position.x = position.x + distance;
    }

    public void moveDown(float distance) {
        if((position.y - distance - BASIS_Y) <  0)
            position.y = BASIS_Y;
        else
            position.y = position.y - distance;
    }

    public void moveUp(float distance) {
        if((position.y + getHeight() + distance - BASIS_Y) >  Map.MAP_MAX_Y)
            position.y = Map.MAP_MAX_Y - getHeight() + BASIS_Y;
        else
            position.y = position.y + distance;
    }
}
