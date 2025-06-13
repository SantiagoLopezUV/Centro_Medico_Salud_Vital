package utils;

public class MathUtils {
    public static double roundDPercentageFixed(Number numerator,int divisor, int places) {
        int percentage = (int) Math.round(((double) numerator/ divisor)*Math.pow(10,2+places));
        return (double) percentage/100;
    }

}
