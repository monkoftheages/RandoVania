package randovania.model.objects;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import randovania.control.GameController;
import randovania.model.objects.GameObject;
import randovania.view.Animation;
import randovania.view.graphics.Assets;

public class Player extends GameObject {
    public static float START_X = 180;
    public static float START_Y = 63;

    protected float x, y, width, height;
    protected Rectangle playerRectangle;
    protected Texture playerTexture;
    protected Animation playerAnimation;

    protected boolean facingLeft = true;
    protected boolean moving = false;

    public Player() {
        x = START_X + GameController.START_X;
        y = START_Y + GameController.START_Y;
    }

    protected float totaltime = 0;

    public void paintComponent(SpriteBatch g, float shiftX, float shiftY, float deltaTime) {
        if(!moving) {
            paintComponent(Assets.bobFall, g, shiftX, shiftY, deltaTime);
        } else {
            paintComponent(Assets.bobRun, g, shiftX, shiftY, deltaTime);
        }
    }

    public void paintComponent(Animation ani, SpriteBatch g, float shiftX, float shiftY, float deltaTime) {
        totaltime+=deltaTime;
        TextureRegion drawFrame = ani.getKeyFrame(totaltime, 0);
        //Draw image swapped based on which way the player is facing
        g.draw(drawFrame,
                facingLeft ? (shiftX + x + drawFrame.getRegionWidth()) : (shiftX + x),
                shiftY + y,
                facingLeft ? -(float)drawFrame.getRegionWidth() : (float)drawFrame.getRegionWidth(),
                (float)drawFrame.getRegionHeight());
    }

    public void movePlayer(float distX, float distY) {
        x = x + distX;
        y = y + distY;

    }

    public Rectangle getBoundingBox() {
        return playerRectangle;
    }

    public void setFacingLeft(boolean fl) {
        facingLeft = fl;
    }

    public void setMoving(boolean m) {
        if(m != moving) {
            totaltime = 0;
        }
        moving = m;
    }

    public void createTextures() {
        playerAnimation = Assets.bobRun;
        playerTexture = new Texture("character.png");
        width = playerTexture.getWidth();
        height = playerTexture.getHeight();
        //shifted bounding box to make player contact ground/wall (due to whitespace on the graphics)
        playerRectangle = new Rectangle(x+1 + 4, y+1, width-6 - 8, height-8);
    }

    public void disposeTextures() {
        playerTexture.dispose();
    }
}
