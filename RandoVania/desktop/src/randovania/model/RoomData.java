package randovania.model;


import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

//Temporary class to store information about rooms, will find a solution in the future for a better storage (outside of game then dynamically loaded)
public class RoomData {
    public static RoomDetails[] roomDetails;
    public static int NUM_OF_ROOMS = 2;

    public RoomData() {
        roomDetails = new RoomDetails[NUM_OF_ROOMS];

        //Creating room 1
        roomDetails[0] = new RoomDetails(
                564, 720,
                "room1.jpg",
                createWall(466.4408, 138.04735, 98.400024, 396.4),
                createWall(-3.600029, 0.39999008, 573.5998, 70.8),
                createWall(321.88928, 504.0336, 144.4, 30.800018),
                createWall(97.94165, 504.09198, 192.39993, 30.799866),
                createWall(65.994385, 202.13976, 31.999992, 340.4),
                createWall(165.41772, 535.27783, 28.400047, 111.599976),
                createWall(385.42014, 534.66345, 26.399994, 112.79999),
                createWall(193.29446, 631.807, 192.0, 35.599976)
        );


        //Creating room 2
        roomDetails[1] = new RoomDetails(
                1746, 414,
                "room2.jpg"
        );
    }

    protected Rectangle createWall(float x, float y, float w, float h) {
        return new Rectangle(x, y, w, h);
    }

    protected Rectangle createWall(double x, double y, double w, double h) {
        return createWall((float)x, (float)y, (float)w, (float)h);
    }

    public class RoomDetails {
        public ArrayList<Rectangle> walls;
        public int width, height;
        public String fileName;

        public RoomDetails() {
            walls = new ArrayList<Rectangle>();
        }

        public RoomDetails(int w, int h, String fileLoc, Rectangle... rects) {
            width = w;
            height = h;
            fileName = fileLoc;
            walls = new ArrayList<Rectangle>();
            for(Rectangle r : rects)
                walls.add(r);
        }
    }
}
