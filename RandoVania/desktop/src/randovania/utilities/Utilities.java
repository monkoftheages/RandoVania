package randovania.utilities;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.io.File;
import java.util.Iterator;

public class Utilities {
    public static final float EQUALITY_THRESHOLD = (float).0001;
    public static final float EQUALITY_THRESHOLD_SMALL = (float).00001;

    public static final float EQUALITY_THRESHOLD_CLOSE = (float)5;

    public static boolean almostEqual(float a, float b) {
        return Math.abs(a - b) < EQUALITY_THRESHOLD;
    }

    public static float getDifference(float a, float b) {
        return Math.abs(a - b);
    }

    public static boolean close(float a, float b) {
        return Math.abs(a - b) < EQUALITY_THRESHOLD_CLOSE;
    }

    public static float round(float a, int places) {
        float hundo = (float)Math.pow(10, places);
        return (float)Math.round(a * hundo) / hundo;
    }

    public static Dimension getImageDimension(File imgFile){
        int pos = imgFile.getName().lastIndexOf(".");
        String suffix = imgFile.getName().substring(pos + 1);
        Iterator<ImageReader> iter = ImageIO.getImageReadersBySuffix(suffix);
        while(iter.hasNext()) {
            ImageReader reader = iter.next();
            try {
                ImageInputStream stream = new FileImageInputStream(imgFile);
                reader.setInput(stream);
                int width = reader.getWidth(reader.getMinIndex());
                int height = reader.getHeight(reader.getMinIndex());
                return new Dimension(width, height);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                reader.dispose();
            }
        }
        return null;
    }
}
