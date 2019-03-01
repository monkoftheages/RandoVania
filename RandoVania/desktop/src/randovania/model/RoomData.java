package randovania.model;


import com.badlogic.gdx.math.Rectangle;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

//Temporary class to store information about rooms, will find a solution in the future for a better storage (outside of game then dynamically loaded)
public class RoomData {
    public static RoomDetails[] roomDetails;
    public static int NUM_OF_ROOMS = 2;

    public RoomData() {
        roomDetails = new RoomDetails[NUM_OF_ROOMS];
        //Creating room 1
        roomDetails[0] = new RoomDetails(
                "room0.jpg",
                createWall(464.04077, 89.24735, 98.400024, 396.4),
                createWall(-4.800026, -48.800003, 573.5998, 70.8),
                createWall(320.28928, 455.2336, 144.4, 30.800018),
                createWall(95.54167, 454.89194, 192.39993, 30.799866),
                createWall(63.994385, 152.53975, 31.999992, 341.6),
                createWall(175.81772, 485.27783, 16.000053, 112.39996),
                createWall(383.82013, 486.2635, 16.399994, 111.599976),
                createWall(192.09447, 582.607, 192.0, 15.599976)
        );
        roomDetails[0].setTransitionWalls(
                new float[][]{
                        {498.23682f, 21.60001f, 12.399994f, 66.79999f, 16, 83, 1},
                }
        );


        //Creating room 2
        roomDetails[1] = new RoomDetails(
                "room1.jpg",
                createWall(0.0, 15.467041, 1745.1208, 26.400002),
                createWall(-1.5258789E-5, 200.4313, 1744.8, 105.20001),
                createWall(3.2224197, 53.541527, 30.0, 11.200012),
                createWall(11.618774, 44.34156, 30.0, 12.399994),
                createWall(24.818764, 36.741585, 24.40001, 12.0),
                createWall(1648.9977, 35.767784, 26.800049, 14.400009),
                createWall(1657.0374, 44.567776, 23.199951, 13.200012),
                createWall(1665.0376, 50.967796, 29.599976, 14.400024),
                createWall(1673.0375, 59.367775, 20.799927, 14.0),
                createWall(1681.0375, 66.56781, 25.200073, 14.800003),
                createWall(1689.2578, 75.76774, 56.0, 14.799973),
                createWall(1697.3331, 154.02228, 48.400146, 47.999985),
                createWall(-6.799988, 69.32851, 24.000008, 11.600006),
                createWall(3.600006, 59.452896, 21.600006, 13.200012),
                createWall(-21.59999, 76.61584, 30.800003, 12.0)
                );
        roomDetails[1].setTransitionWalls(
                new float[][]{
                        {0.80000305f, 90.53874f, 9.399986f, 109.600006f, 436, 24, 0},
                        {1731.6f, 88.82962f, 13.199951f, 64.0f, 21, 27, 0},
                }
        );
    }

    protected Rectangle createWall(float x, float y, float w, float h) {
        return new Rectangle(x, y, w, h);
    }

    protected Rectangle createWall(double x, double y, double w, double h) {
        return createWall((float) x, (float) y, (float) w, (float) h);
    }

    protected Rectangle createTransition(float x, float y, float w, float h) {
        return new Rectangle(x, y, w, h);
    }

    public class RoomDetails {
        public ArrayList<Rectangle> walls;
        public float[][] transitionWalls;
        public String fileName;

        public RoomDetails() {
            walls = new ArrayList<Rectangle>();
        }

        public RoomDetails(String fileLoc, Rectangle... rects) {
            fileName = fileLoc;
            walls = new ArrayList<Rectangle>();
            for (Rectangle r : rects)
                walls.add(r);
        }

        public void setTransitionWalls(float[][] tw) {
            transitionWalls = tw;
        }
    }
}
