package zenonia.control;

import zenonia.model.Map;
import zenonia.model.Player;
import zenonia.model.Viewport;
import zenonia.model.Wall;
import zenonia.view.GameFrame;
import zenonia.view.BoardGraphics;

import java.awt.*;

public class GameController {
//    protected int mapXLocation;
//    protected int mapYLocation;
    protected GameFrame parent;
    protected Thread moveThread;
    protected boolean moveLeft = false;
    protected boolean moveRight = false;
    protected boolean moveUp = false;
    protected boolean moveDown = false;

    protected static final int FRAMES_PER_SECOND = 30;
    protected static int MOVE_DRAW_TIME;
    protected static final int WALK_SPEED = 20;

    protected Map gameMap;
    protected Viewport viewport;
    protected Player player;

    public GameController(GameFrame parent) {
        MOVE_DRAW_TIME = 1000/FRAMES_PER_SECOND;
        gameMap = new Map();
        viewport = new Viewport();
        player = new Player();
        this.parent = parent;
        moveThread = new Thread() {
            public void run() {
                while (true) {
                    while (moveLeft || moveRight || moveUp || moveDown) {
                        move();
                        try {
                            Thread.currentThread().join(MOVE_DRAW_TIME);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        moveThread.start();
    }

    public Map getGameMap() {
        return gameMap;
    }

    public Player getPlayer() {
        return player;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void startMovingUp() {
        moveUp = true;
    }

    public void startMovingDown() {
        moveDown = true;
    }

    public void startMovingLeft() {
        moveLeft = true;
    }

    public void startMovingRight() {
        moveRight = true;
    }

    public void stopMovingUp() {
        moveUp = false;
    }

    public void stopMovingDown() {
        moveDown = false;
    }

    public void stopMovingLeft() {
        moveLeft = false;
    }

    public void stopMovingRight() {
        moveRight = false;
    }

    public void move() {
        moveLeft();
        moveRight();
        moveUp();
        moveDown();
    }

    public void moveUp() {
        if (!moveUp)
            return;
        viewport.moveUp(WALK_SPEED);
        movePlayer(0, -WALK_SPEED);
        repaint();
    }

    public void moveDown() {
        if (!moveDown)
            return;
        viewport.moveDown(WALK_SPEED);
        movePlayer(0, WALK_SPEED);
        repaint();
    }

    public void moveRight() {
        if (!moveRight)
            return;
        viewport.moveRight(WALK_SPEED);
        movePlayer(WALK_SPEED, 0);
        repaint();
    }

    public void moveLeft() {
        if (!moveLeft)
            return;
        viewport.moveLeft(WALK_SPEED);
        movePlayer(-WALK_SPEED, 0);
        repaint();
    }

    protected void movePlayer(int distX, int distY) {
        player.getBoundingBox().translate(distX, distY);
        Wall collidedWall = hasWallCollision(player.getBoundingBox());
        if(collidedWall != null) {
            System.out.println("collision");
            player.getBoundingBox().translate(-distX, -distY);
        } else {
            System.out.println("no collision");
            player.movePlayer(distX, distY);
        }
    }

    protected Wall hasWallCollision(Rectangle playerBox) {
        for(Wall wall : gameMap.getWalls())
            if(wall.getBoundingBox().intersects(playerBox)) {
//                System.out.println("Player Box: " + playerBox.x + " " + playerBox.y + " " + playerBox.width + " " + playerBox.height);
//                System.out.println("Wall   Box: " + wall.getBoundingBox().x + " " + wall.getBoundingBox().y + " " + wall.getBoundingBox().width + " " + wall.getBoundingBox().height);
                return wall;
            }
        return null;
    }

    public void repaint() {
        getGraphics().repaint();
    }

    protected BoardGraphics getGraphics() {
        return parent.getBoardGraphics();
    }
}
