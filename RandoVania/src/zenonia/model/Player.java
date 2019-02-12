package zenonia.model;

import zenonia.view.graphics.Sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by b3_authors on 2/11/2019.
 */
public class Player {
    protected int x, y, width, height;
    protected BufferedImage playerSprite;
    protected Rectangle playerRectangle;

    public Player() {
        try {
            playerSprite = ImageIO.read(new File("Z:\\workspace\\3_MyProjects\\Zenonia\\images\\character.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        x = 200;
        y = 880;
        width = playerSprite.getWidth();
        height = playerSprite.getHeight();
        playerRectangle = new Rectangle(x, y, width, height);
    }

    public void paintComponent(Graphics g, int shiftX, int shiftY) {
        g.drawImage(playerSprite, x - shiftX, y - shiftY, null);
    }


    public void movePlayer(int distX, int distY) {
        x = x + distX;
        y = y + distY;

    }

    public void moveUp(int distance) {
        y = y - distance;
    }

    public void moveDown(int distance) {
        y = y + distance;
    }

    public Rectangle getBoundingBox() {
        return playerRectangle;
    }
}
