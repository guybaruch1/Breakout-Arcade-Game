package numbers;

/**
 * A utility class to compare double values with a certain threshold.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class DoubleEquals {

    /**
     * The threshold for comparing double values.
     */

    private static final double THRESHOLD = 0.00001;

    /**
     * Returns true if the absolute difference between the given double values is less than the threshold.
     *
     * @param a the first double value to compare
     * @param b the second double value to compare
     * @return true if the absolute difference between the given double values is less than the threshold, false
     * otherwise
     */
    public static boolean equals(double a, double b) {
        return Math.abs(a - b) < THRESHOLD;
    }
}