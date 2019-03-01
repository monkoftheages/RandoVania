package randovania.model.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import randovania.control.GameController;
import randovania.model.RoomData;
import randovania.utilities.Utilities;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class Room extends GameObject {
    protected ArrayList<Wall> walls;
    protected Texture roomTexture;
    protected RoomData.RoomDetails roomDetails;
    protected int width, height;

    public Room(RoomData.RoomDetails roomDetails) {
        this.roomDetails = roomDetails;
        Dimension x = Utilities.getImageDimension(new File(roomDetails.fileName));
        width = x.width;
        height = x.height;
        walls = new ArrayList<Wall>();
        for(Rectangle r : roomDetails.walls)
            createWall(r);
        for(float[] twData: roomDetails.transitionWalls)
            createTransitionWall(twData);
    }

    protected void createOutsideWalls() {
        //Walls ouside of map area
        createWall(-29, 0, 30, height);
        createWall(width -1, 0, 30, height);
        createWall(0, -29, width, 30);
        createWall(0, height -1, width, 30);
    }

    public void createTextures() {
        roomTexture = new Texture(roomDetails.fileName);
        width = roomTexture.getWidth();
        height = roomTexture.getHeight();
        createOutsideWalls();
        for(Wall w : walls)
            w.createTextures();
    }

    public void paintComponent(SpriteBatch batch, int x, int y) {
        batch.draw(roomTexture, 0, 0, 0, 0, roomTexture.getWidth(), roomTexture.getHeight());
        drawWalls(batch);
    }

    protected void drawWalls(SpriteBatch batch) {
        if(!GameController.DEBUG_MODE)
            return;
        if(!GameController.SHOW_WALLS)
            return;
        for(Wall wall : walls)
            wall.paintComponent(batch);
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public void printWallInfo() {
        for(Wall w : walls)
            w.printInfo();
    }

    public void createWall(Rectangle r) {
        Wall w = new Wall(r.x, r.y, r.width, r.height);
        walls.add(w);
    }

    public void createTransitionWall(float[] twData) {
        TransitionWall w = new TransitionWall(twData);
        walls.add(w);
    }

    public void createWall(float x, float y, float width, float height) {
        Wall w = new Wall(x, y, width, height);
        walls.add(w);
    }

    public void createWall(double x, double y, double width, double height) {
        createWall((float)x, (float)y, (float)width, (float)height);
    }

    public void createWallAndTexture(float x, float y, float width, float height) {
        Wall w = new Wall(x, y, width, height);
        w.createTextures();
        walls.add(w);
    }

    public void deleteWallAndTexture(Wall w) {
        boolean removed = walls.remove(w);
        if(removed) {
            w.disposeTextures();
        }
    }

    public void disposeTextures() {
        for(Wall wall : walls)
            wall.disposeTextures();
        roomTexture.dispose();
    }
}
