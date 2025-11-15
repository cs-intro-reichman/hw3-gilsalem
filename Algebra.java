// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.
public class Algebra {

    public static void main(String args[]) {
        System.out.println(plus(2,3));  
        System.out.println(minus(7,2));  
        System.out.println(minus(2,7)); 
        System.out.println(times(3,4));  
        System.out.println(plus(2,times(4,2))); 
        System.out.println(pow(5,3));      
        System.out.println(pow(3,5));      
        System.out.println(div(12,3));   
        System.out.println(div(5,5));    
        System.out.println(div(25,7));   
        System.out.println(mod(25,7));   
        System.out.println(mod(120,6));     
        System.out.println(sqrt(36));
        System.out.println(sqrt(263169));
        System.out.println(sqrt(76123));
        System.out.println(plus(-3,5));       
        System.out.println(times(-3,4));      
        System.out.println(div(-12,3));       
        System.out.println(mod(-12,5));       
    }

    // Returns x1 + x2
    public static int plus(int x1, int x2) {
        if (x2 > 0) {
            for (int i = 0; i < x2; i++) x1++;
        } else {
            for (int i = x2; i < 0; i++) x1--;
        }
        return x1;
    }

    // Returns x1 - x2
    public static int minus(int x1, int x2) {
        int result = x1;
        if (x2 >= 0) {
            for (int i = 0; i < x2; i++) result--;
        } else {
            for (int i = x2; i < 0; i++) result++;
        }
        return result;
    }

    // Returns x1 * x2
    public static int times(int x1, int x2) {
        if (x1 == 0 || x2 == 0) return 0;

        int result = 0;
        int count = x2;
        int sign = 1;

        if (x2 < 0) {
            count = minus(0, x2);
            sign = minus(0, sign); 
        }
        if (x1 < 0) {
            x1 = minus(0, x1);
            sign = minus(0, sign); 
        }

        for (int i = 0; i < count; i++) {
            result = plus(result, x1);
        }

        if (sign < 0) result = minus(0, result);

        return result;
    }

    // Returns x^n
    public static int pow(int x, int n) {
        if (n == 0) return 1;
        int result = x;
        for (int i = 1; i < n; i++) {
            result = times(result, x);
        }
        return result;
    }

    // Integer division x1 / x2
    public static int div(int x1, int x2) {
        if (x2 == 0) return 0;

        int dividend = x1;
        int divisor = x2;
        int result = 0;
        int sign = 1;

        if (x1 < 0) {
            dividend = minus(0, x1);
            sign = minus(0, sign);
        }
        if (x2 < 0) {
            divisor = minus(0, x2);
            sign = minus(0, sign);
        }

        while (dividend >= divisor) {
            dividend = minus(dividend, divisor);
            result = plus(result, 1);
        }

        if (sign < 0) result = minus(0, result);
        return result;
    }

    // x1 % x2
    public static int mod(int x1, int x2) {
        if (x2 == 0) return 0;

        int dividend = x1;
        int divisor = x2;

        if (x1 < 0) dividend = minus(0, x1);
        if (x2 < 0) divisor = minus(0, x2);

        while (dividend >= divisor) {
            dividend = minus(dividend, divisor);
        }

        if (x1 < 0) dividend = minus(0, dividend);
        return dividend;
    }

    // Integer sqrt
    public static int sqrt(int x) {
        int i = 0;
        while (times(i, i) <= x) {
            if (times(i, i) == x) return i;
            i++;
        }
        return i - 1;
    }
}
