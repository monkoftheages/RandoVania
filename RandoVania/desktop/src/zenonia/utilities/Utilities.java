package zenonia.utilities;

/**
 * Created by b3_authors on 2/21/2019.
 */
public class Utilities {
    public static final float EQUALITY_THRESHOLD = (float).00001;
    public static boolean almostEqual(float x, float y) {
        return Math.abs(x - y) < EQUALITY_THRESHOLD;
    }

    public static float getDifference(float x, float y) {
        return Math.abs(x - y);
    }
}
