package zenonia.model;


import java.awt.*;

/**
 * Created by b3_authors on 2/11/2019.
 */
public class Wall {
    protected Rectangle rect;

    public Wall() {

    }

    public Wall(int x, int y, int width, int height) {
        rect = new Rectangle(x, y, width, height);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
    }

    public Rectangle getBoundingBox() {
        return rect;
    }
}
