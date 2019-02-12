package zenonia.view;


import zenonia.control.GameController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BoardGraphics extends JPanel {
    public static int PREF_W = 450;
    public static int PREF_H = 270;
    protected GameFrame parent;
    protected GameController controller;
    public static final Color RED_TRANSPARENT = new Color(1, 0, 0, .5f);

    public BoardGraphics(GameFrame parent) {
        this.parent = parent;
        controller = parent.getController();
    }

    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage sub = controller.getGameMap().getImage().getSubimage(controller.getViewport().getX(), controller.getViewport().getY(), controller.getViewport().getWidth(), controller.getViewport().getHeight());
        Image offscreen = drawOffscreen(sub);
        g.drawImage(offscreen, 0, 0, this);
    }

    protected Image drawOffscreen(BufferedImage subImage) {
        Image offscreen = createImage(subImage.getWidth(), subImage.getHeight());
        Graphics graphics = offscreen.getGraphics();
        graphics.drawImage(subImage, 0, 0, this);
        controller.getPlayer().paintComponent(graphics, controller.getViewport().getX(), controller.getViewport().getY());
        return offscreen;
    }

    public void drawRect(Graphics2D g2, int x, int y, int width, int height) {
        if ((width < 0) || (height < 0)) {
            return;
        }

        if (height == 0 || width == 0) {
            g2.drawLine(x, y, x + width, y + height);
        } else {
            g2.drawLine(x, y, x + width - 4, y);
            g2.drawLine(x, y + 1, x + width - 4, y + 1);
            g2.drawLine(x, y + 2, x + width - 4, y + 2);

            g2.drawLine(x + width, y, x + width, y + height - 4);
            g2.drawLine(x + width - 1, y, x + width - 1, y + height - 4);
            g2.drawLine(x + width - 2, y, x + width - 2, y + height - 4);
            g2.drawLine(x + width - 3, y, x + width - 3, y + height - 4);

            g2.drawLine(x + width, y + height, x, y + height);
            g2.drawLine(x + width, y + height - 1, x, y + height - 1);
            g2.drawLine(x + width, y + height - 2, x, y + height - 2);
            g2.drawLine(x + width, y + height - 3, x, y + height - 3);

            g2.drawLine(x, y + height - 4, x, y + 3);
            g2.drawLine(x + 1, y + height - 4, x + 1, y + 3);
            g2.drawLine(x + 2, y + height - 4, x + 2, y + 3);
        }
    }
}
