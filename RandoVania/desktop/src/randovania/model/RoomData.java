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
                        {498.23682f, 21.60001f, 12.399994f, 66.79999f, 13, 64, 1},
                }
        );


        //Creating room 2
        roomDetails[1] = new RoomDetails(
                "room1.jpg",
                createWall(0.80000305, -3.732956, 1745.1208, 26.400002),
                createWall(-1.5258789E-5, 182.8313, 1744.8, 122.80002),
                createWall(2.0224228, 34.341534, 30.0, 11.60001),
                createWall(10.018776, 25.541565, 30.0, 12.399994),
                createWall(23.61876, 18.341583, 24.40001, 12.0),
                createWall(1648.1976, 15.767784, 26.800049, 14.400009),
                createWall(1655.8373, 24.96777, 23.199951, 13.200012),
                createWall(1663.8376, 32.967793, 29.599976, 13.200027),
                createWall(1671.8374, 40.56777, 20.799927, 14.0),
                createWall(1679.8375, 46.967808, 25.200073, 15.200005),
                createWall(1688.0579, 56.567734, 56.0, 14.799973),
                createWall(1696.1332, 134.82228, 48.400146, 47.999985),
                createWall(-7.9999847, 50.128498, 24.000008, 12.000008),
                createWall(2.4000015, 40.6529, 21.600006, 13.200012),
                createWall(-31.19999, 58.215843, 39.6, 12.0)
                );
        roomDetails[1].setTransitionWalls(
                new float[][]{
                        {-10.800003f, 70.13875f, 13.799988f, 109.600006f, 436, 24, 0},
                        {1736.4f, 71.629616f, 13.199951f, 64.0f, 21, 27, 0},
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
