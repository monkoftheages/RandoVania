package zenonia.model;

import zenonia.view.BoardGraphics;
import zenonia.view.SpriteController;
import zenonia.view.graphics.Sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by b3_authors on 2/11/2019.
 */
public class Map {
    public static int MAP_MAX_X = 1000;
    public static int MAP_MAX_Y = 1000;
    protected BufferedImage mapBackground;
    protected int lowerYBound;
    protected int leftXBound;

    protected ArrayList<Wall> walls;

    public Map() {
        try {
            mapBackground = ImageIO.read(new File("Z:\\workspace\\3_MyProjects\\Zenonia\\images\\map.png"));
            MAP_MAX_Y = mapBackground.getHeight();
            MAP_MAX_X = mapBackground.getWidth();
        } catch (IOException e) {
            e.printStackTrace();
        }
        walls = new ArrayList<Wall>();
        walls.add(new Wall(131, 689, 30, 260));
        walls.add(new Wall(131, 912, 429, 37));
        walls.add(new Wall(-1, 0, 1, MAP_MAX_Y));
        walls.add(new Wall(MAP_MAX_X, 0, 1, MAP_MAX_Y));
        walls.add(new Wall(0, -1, MAP_MAX_X, 1));
        walls.add(new Wall(0, MAP_MAX_Y, MAP_MAX_X, 1));
//        walls.add(new Wall(900, 900, 30, 260));
        drawWalls();
    }

    protected void drawWalls() {
        for(Wall wall : walls)
            wall.paintComponent(mapBackground.getGraphics());

    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public void paintComponent(Graphics g) {
//        g.drawImage(mapSprite.getImage(), mapSprite.getX(), mapSprite.getY(), null);
//        for(Wall wall : walls)
//            wall.paintComponent(g);
    }

    public BufferedImage getImage() {
        return mapBackground;
    }
}
