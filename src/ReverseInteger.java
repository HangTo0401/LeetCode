public class ReverseInteger {
    /**
     * EASY
     * 7. Reverse Integer
     *
     * Given a signed 32-bit integer x, return x with its digits reversed.
     * If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
     * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
     *
     * Example 1:
     * Input: x = 123
     * Output: 321
     *
     * Example 2:
     * Input: x = -123
     * Output: -321
     *
     * Example 3:
     * Input: x = 120
     * Output: 21
     *
     * Example 4:
     * Input: x = 0
     * Output: 0
     *
     * Constraints: -231 <= x <= 231 – 1
     *
     * SOLUTIONS: There are
     * */

    /**
     * 1. The first solution as followed:
     * a. Check x is positive or negative. If x is negative then convert from negative to positive
     * b. If x != 0 then:
     *    Take digit = x % 10 (last number)
     *    Multiply reversedNum = reversedNum*10 + digit
     *    Check overflow on reversedNum
     *    Divide x = x /10
     * c. Return reversedNum
     *
     * @param x
     * @return int
     */
    public int reverseInt(int x) {
        if (x == 0) return x;

        // Reversed number
        long reversedNum = 0;

        // Check x is positive or negative
        boolean unsigned = x > 0 ? true : false; //positive then unsigned = true
        if (!unsigned) {
            x *= -1;//convert negative to positive to reverse
        }

        while(x != 0) {
            int digit = x % 10;
            reversedNum = reversedNum*10 + digit;
            if (reversedNum >= Integer.MAX_VALUE || reversedNum <= Integer.MIN_VALUE) return 0;
            x = x / 10;
        }

        return (int) reversedNum;
    }

    public static void main(String[] args) {
        ReverseInteger solution = new ReverseInteger();
        System.out.println(solution.reverseInt(12));
    }
}
