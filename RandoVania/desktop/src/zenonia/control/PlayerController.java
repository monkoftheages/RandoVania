package zenonia.control;

import zenonia.model.Player;
import zenonia.model.Viewport;
import zenonia.model.Wall;
import zenonia.utilities.Utilities;

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
    public static final int PLAYER_CENTEREDNESS = 30;
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
    //if it is, return no movement
    //if it is not, move the player far enough to touch the wall
    protected boolean movePlayerAfterCollision(float distX, float distY, int direction, Wall collidedWall) {
        float moveX = 0;
        float moveY = 0;
        if (direction == MOVING_DOWN) {
            //moving down, if player y is 1 pixel higher than collided wall's top, then return false, otherwise move down the difference
            contactingGround = true;
            player.getBoundingBox().setY(player.getBoundingBox().getY() - distY);
            //Almost equal since float math is not always exact
            if (Utilities.almostEqual(player.getBoundingBox().getY(), collidedWall.getBoundingBox().getY() + collidedWall.getBoundingBox().getHeight())) {
                return false;
            }
            moveY = (collidedWall.getBoundingBox().getY() + collidedWall.getBoundingBox().getHeight()) - player.getBoundingBox().getY();
        } else if (direction == MOVING_UP) {
            //Moving up, cancel jump when colliding with a ceiling
            if(jumpUp) {
                jumpUp = false;
                moveDown = true;
            }
            player.getBoundingBox().setY(player.getBoundingBox().getY() - distY);
            if (Utilities.almostEqual(player.getBoundingBox().getY() + player.getBoundingBox().getHeight(), collidedWall.getBoundingBox().getY()))
                return false;
            moveY = collidedWall.getBoundingBox().getY() - (player.getBoundingBox().getY() + player.getBoundingBox().getHeight());
        } else if (direction == MOVING_RIGHT) {
            player.getBoundingBox().setX(player.getBoundingBox().getX() - distX);
            if (Utilities.almostEqual(player.getBoundingBox().getX() + player.getBoundingBox().getWidth(), collidedWall.getBoundingBox().getX()))
                return false;
            moveX = collidedWall.getBoundingBox().getX() - (player.getBoundingBox().getX() + player.getBoundingBox().getWidth());
        } else if (direction == MOVING_LEFT) {
            player.getBoundingBox().setX(player.getBoundingBox().getX() - distX);
            if (Utilities.almostEqual(player.getBoundingBox().getX(), collidedWall.getBoundingBox().getX() + collidedWall.getBoundingBox().getWidth()))
                return false;
            moveX = (collidedWall.getBoundingBox().getX() + collidedWall.getBoundingBox().getWidth()) - player.getBoundingBox().getX();
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
