package randovania.model.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import randovania.model.RoomData;

import java.util.ArrayList;

public class World extends GameObject {
    public static int MAP_MAX_X = 2000;
    public static int MAP_MAX_Y = 1000;

//    protected ArrayList<Wall> walls;
    protected RoomData roomData;
    protected Room currentRoom;
    protected Room nextRoom;


    public World() {
        roomData = new RoomData();
        currentRoom = new Room(roomData.roomDetails[0]);
        nextRoom = new Room(roomData.roomDetails[1]);
        setCurrentRoom(currentRoom);
    }

    public void setCurrentRoom(Room room) {
        currentRoom = room;
        MAP_MAX_X = currentRoom.width;
        MAP_MAX_Y = currentRoom.height;
    }

    public ArrayList<Wall> getWalls() {
        return currentRoom.getWalls();
    }

    public void paintComponent(SpriteBatch batch, int x, int y) {
        currentRoom.paintComponent(batch, x, y);
    }

    public void createWall(float x, float y, float w, float h) {
        currentRoom.createWallAndTexture(x, y, w, h);
    }

    public void deleteWall(Wall w) {
        currentRoom.deleteWallAndTexture(w);
    }

    public void printWallInfo() {
        currentRoom.printWallInfo();
    }

    public void createTextures() {
        currentRoom.createTextures();
        nextRoom.createTextures();
    }

    public void disposeTextures() {
        currentRoom.disposeTextures();
        nextRoom.disposeTextures();
    }
}