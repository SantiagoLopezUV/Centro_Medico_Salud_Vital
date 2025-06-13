package utils;

public class MathUtils {
    public static double roundDPercentageFixed(Number numerator,int divisor, int places) {
        if(divisor == 0) throw new ArithmeticException("Divisor is zero");
        if(numerator.intValue() == 0) return 0;
        int percentage = (int) Math.round((Double.parseDouble(numerator.toString())/ divisor)*Math.pow(10,2+places));
        return (double) percentage/100;
    }

}
