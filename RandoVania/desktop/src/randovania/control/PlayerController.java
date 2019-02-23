package randovania.control;

import com.badlogic.gdx.math.Rectangle;
import randovania.model.Player;
import randovania.model.Viewport;
import randovania.model.Wall;
import randovania.utilities.Utilities;

public class PlayerController {
    protected GameController gameController;
    protected Player player;

    public boolean moveLeft = false;
    public boolean moveRight = false;
    public boolean moveUp = false;
    public boolean moveDown = false;
    public boolean jumpUp = false;

    protected final int MOVING_LEFT = 0;
    protected final int MOVING_RIGHT = 1;
    protected final int MOVING_UP = 2;
    protected final int MOVING_DOWN = 3;

    protected boolean contactingGround = true;

    protected static final int WALK_SPEED = 200;
    //    protected static final int WALK_SPEED = 1000;
    protected static final int JUMP_SPEED = 400;
    //    protected static final int JUMP_SPEED = 1000;
    public static final int JUMP_TIME = 200;
    public static final float MAX_JUMP_HEIGHT = (float) .22;
    public static final int PLAYER_CENTEREDNESS = 1;
//    public static final int PLAYER_CENTEREDNESS = 1;

    protected float totalJumpHeight = 0;

    public PlayerController(GameController c) {
        gameController = c;
        player = new Player();
    }

    public void startMovingUp() {
        if (gameController.isGravityOn())
            return;
        if (moveDown)
            return;
        moveUp = true;
    }

    public void startMovingDown() {
        if (gameController.isGravityOn())
            return;
        if (moveUp)
            return;
        moveDown = true;
    }

    public void startMovingLeft() {
        moveRight = false;
        player.setFacingLeft(true);
        player.setMoving(true);
        moveLeft = true;
    }

    public void startMovingRight() {
        moveLeft = false;
        player.setFacingLeft(false);
        player.setMoving(true);
        moveRight = true;
    }

    public void stopMovingUp() {
        if (gameController.isGravityOn())
            return;
        moveUp = false;
    }

    public void stopMovingDown() {
        if (gameController.isGravityOn())
            return;
        moveDown = false;
    }

    public void stopMovingLeft() {
        if (!moveLeft)
            return;
        moveLeft = false;
        player.setMoving(false);
    }

    public void stopMovingRight() {
        if (!moveRight)
            return;
        moveRight = false;
        player.setMoving(false);
    }

    public void jump() {
        if (jumpUp || !gameController.isGravityOn() || !contactingGround)
            return;
        totalJumpHeight = 0;
        jumpUp = true;
        contactingGround = false;
        moveDown = false;
        moveUp = false;
    }

    public void move(float delta) {
        moveLeft(delta);
        moveRight(delta);
        moveDown(delta);
        moveUp(delta);
        jumpUp(delta);
    }

    public void jumpUp(float delta) {
        if (!jumpUp)
            return;
        if (totalJumpHeight + delta >= MAX_JUMP_HEIGHT) {
            //Reached max height for jump,
            //need to verify and change to difference to guarantee a specific height
            delta = MAX_JUMP_HEIGHT - totalJumpHeight;
            jumpUp = false;
            moveDown = true;
        } else {
            totalJumpHeight += delta;
        }
        if (movePlayer(0, JUMP_SPEED * delta, MOVING_UP)) {
            float diff = getCamera().getY() - player.getBoundingBox().getY();
            if (diff < PLAYER_CENTEREDNESS)
                getCamera().moveUp(JUMP_SPEED * delta);
        }
    }

    public void moveUp(float delta) {
        if (!moveUp)
            return;
        if (movePlayer(0, JUMP_SPEED * delta, MOVING_UP)) {
            float diff = getCamera().getY() - player.getBoundingBox().getY();
            if (diff < PLAYER_CENTEREDNESS)
                getCamera().moveUp(JUMP_SPEED * delta);
        }
    }

    public void moveDown(float delta) {
        if (!moveDown)
            return;
        if (movePlayer(0, -JUMP_SPEED * delta, MOVING_DOWN)) {
            float diff = player.getBoundingBox().getY() - getCamera().getY();
            if (diff < PLAYER_CENTEREDNESS) {
                getCamera().moveDown(JUMP_SPEED * delta);
            }
        }
    }

    public void moveRight(float delta) {
        if (!moveRight)
            return;
        if (movePlayer(WALK_SPEED * delta, 0, MOVING_RIGHT)) {
            //Dont move the camera unless character is centered
            float diff = getCamera().getX() - player.getBoundingBox().getX();
            if (diff < PLAYER_CENTEREDNESS)
                getCamera().moveRight(WALK_SPEED * delta);
        }
    }

    public void moveLeft(float delta) {
        if (!moveLeft)
            return;
        if (movePlayer(-WALK_SPEED * delta, 0, MOVING_LEFT)) {
            float diff = player.getBoundingBox().getX() - getCamera().getX();
            if (diff < PLAYER_CENTEREDNESS)
                getCamera().moveLeft(WALK_SPEED * delta);
        }
    }

    protected boolean movePlayer(float distX, float distY, int direction) {
        player.getBoundingBox().setX(player.getBoundingBox().getX() + distX);
        player.getBoundingBox().setY(player.getBoundingBox().getY() + distY);
        Wall collidedWall = gameController.hasWallCollision(player.getBoundingBox());
        if (collidedWall != null) {
            return movePlayerAfterCollision(distX, distY, direction, collidedWall);
        } else {
            //disallow jumps while falling
            if (direction == MOVING_DOWN)
                contactingGround = false;
            player.movePlayer(distX, distY);
            return true;
        }
    }

    //Function checks if the player is touching the wall that was collided with
    //if it is and not overlapping, return no movement
    //if it is not, move the player far enough to touch the wall
    //Using an almost equal function since float math is not always exact
    protected boolean movePlayerAfterCollision(float distX, float distY, int direction, Wall collidedWall) {
        float moveX = 0;
        float moveY = 0;
        player.getBoundingBox().setY(player.getBoundingBox().getY() - distY);
        player.getBoundingBox().setX(player.getBoundingBox().getX() - distX);
        float playerEdge = 0;
        float wallEdge = 0;
        switch (direction) {
            case MOVING_DOWN:
                contactingGround = true;
                playerEdge = player.getBoundingBox().getY();
                wallEdge = collidedWall.getBoundingBox().getY() + collidedWall.getBoundingBox().getHeight();
                moveY = wallEdge - playerEdge;
                break;
            case MOVING_UP:
                //Moving up, cancel jump when colliding with a ceiling
                if (jumpUp) {
                    jumpUp = false;
                    moveDown = true;
                }
                playerEdge = player.getBoundingBox().getY() + player.getBoundingBox().getHeight();
                wallEdge = collidedWall.getBoundingBox().getY();
                moveY = wallEdge - playerEdge;
                break;
            case MOVING_LEFT:
                playerEdge = player.getBoundingBox().getX();
                wallEdge = collidedWall.getBoundingBox().getX() + collidedWall.getBoundingBox().getWidth();
                moveX = wallEdge - playerEdge;
                break;
            case MOVING_RIGHT:
                playerEdge = player.getBoundingBox().getX() + player.getBoundingBox().getWidth();
                wallEdge = collidedWall.getBoundingBox().getX();
                moveX = wallEdge - playerEdge;
                break;

        }
        if (!player.getBoundingBox().overlaps(collidedWall.getBoundingBox()) && Utilities.almostEqual(playerEdge, wallEdge))
            return false;
        //If there is still a collision, stop loop
        player.getBoundingBox().setY(player.getBoundingBox().getY() + moveY);
        player.getBoundingBox().setX(player.getBoundingBox().getX() + moveX);
        boolean stillHasCollision = player.getBoundingBox().overlaps(collidedWall.getBoundingBox());
        player.getBoundingBox().setY(player.getBoundingBox().getY() - moveY);
        player.getBoundingBox().setX(player.getBoundingBox().getX() - moveX);
        if (stillHasCollision) {
            System.out.println("Still has collision");
            return false;
        }
        return movePlayer(moveX, moveY, direction);
    }

    protected Viewport getCamera() {
        return gameController.getCamera();
    }

    public Player getPlayer() {
        return player;
    }

    public void createTextures() {
        player.createTextures();
    }

    public void disposeTextures() {
        player.disposeTextures();
    }
}