package randovania.utilities;

public class Utilities {
    public static final float EQUALITY_THRESHOLD = (float).0001;
    public static final float EQUALITY_THRESHOLD_SMALL = (float).00001;

    public static final float EQUALITY_THRESHOLD_CLOSE = (float)10;

    public static boolean almostEqual(float a, float b) {
        return Math.abs(a - b) < EQUALITY_THRESHOLD;
    }

    public static float getDifference(float a, float b) {
        return Math.abs(a - b);
    }

    public static boolean close(float a, float b) {
        return Math.abs(a - b) < EQUALITY_THRESHOLD_CLOSE;
    }
}
