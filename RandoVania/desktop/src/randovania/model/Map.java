package randovania.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import randovania.control.GameController;

import java.util.ArrayList;

public class Map {
    public static int MAP_MAX_X = 1000;
    public static int MAP_MAX_Y = 1000;
    protected Texture mapTexture;

    protected ArrayList<Wall> walls;

    public Map() {
    }

    protected void drawWalls(SpriteBatch batch) {
        if(!GameController.DEBUG_MODE)
            return;
        for(Wall wall : walls)
            wall.paintComponent(batch);
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public void paintComponent(SpriteBatch batch, int x, int y) {
        batch.draw(mapTexture, 0, 0, 0, 0, mapTexture.getWidth(), mapTexture.getHeight());

        drawWalls(batch);
    }

    public Texture getTexture() {
        return mapTexture;
    }

    public void createTextures() {
        mapTexture = new Texture("map_sotn_cutdown.jpg");
        MAP_MAX_Y = mapTexture.getHeight();
        MAP_MAX_X = mapTexture.getWidth();
        walls = new ArrayList<Wall>();
        walls.add(new Wall(467.76404, -16.000004, 100.0, 100.0));
        walls.add(new Wall(470.6817, 151.59999, 100.0, 100.0));
//        walls.add(new Wall(190, 11, 614, 39));
//        walls.add(new Wall(765, 99, 140, 42));
//        walls.add(new Wall(871, 24, 34, 117));

        //Walls ouside of map area
        walls.add(new Wall(-29, 0, 30, MAP_MAX_Y));
        walls.add(new Wall(MAP_MAX_X-1, 0, 30, MAP_MAX_Y));
        walls.add(new Wall(0, -29, MAP_MAX_X, 30));
        walls.add(new Wall(0, MAP_MAX_Y-1, MAP_MAX_X, 30));
    }

    public void printWallInfo() {
        for(Wall w : walls)
            w.printInfo();
    }

    public void createWall(float x, float y, float width, float height) {
        walls.add(new Wall(x, y, width, height));
    }

    public void disposeTextures() {
        for(Wall wall : walls)
            wall.dispose();
        mapTexture.dispose();
    }
}
