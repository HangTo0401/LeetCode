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
     * SOLUTIONS: There are 2 solutions to this problem
     * */

    /**
     * 1. The first solution using Math as followed:
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

    /**
     * 2. The second solution use Pop and Push Digits & Check before Overflow
     * This is answered solution of this problem of Leetcode
     *
     * Algorithm:
     * Reversing an integer can be done similarly to reversing a string.
     * We want to repeatedly "pop" the last digit off of x and "push" it to the back of the rev.
     * In the end, rev will be the reverse of the x.
     *
     * To "pop" and "push" digits without the help of some auxiliary stack/array, we can use math.
     * //pop operation:
     * pop = x % 10;
     * x /= 10;
     *
     * //push operation:
     * temp = rev * 10 + pop;
     * rev = temp;
     *
     * However, this approach is dangerous, because the statement temp=rev*10+pop can cause OVERFLOW.
     * Luckily, it is easy to check beforehand whether or this statement would cause an overflow.
     *
     * To explain, let’s assume that rev is POSITIVE.
     * 1.	If temp= rev*10+pop causes overflow, then it must be that rev≥ Integer.MAX_VALUE/10
     * 2.	If rev > Integer.MAX_VALUE/10, then temp = rev*10+pop is guaranteed to overflow.
     * 3.	If rev == Integer.MAX_VALUE/10, then temp = rev*10+pop will overflow if and only if pop > 7
     *
     * Integer.MAX_VALUE is 2147483647
     * That means: Integer.MAX_VALUE/10 is 214748364
     * And you're about to do: rev = rev * 10 + pop
     *      So if rev > 214748364, then rev * 10 will cause overflow.
     *      Or if rev == 214748364, then rev * 10 is 2147483640, and rev * 10 + pop will cause overflow if pop > 7.
     *
     * To explain, let’s assume that rev is NEGATIVE.
     * 1.	If temp= rev*10+pop causes overflow, then it must be that rev≥ Integer.MIN_VALUE/10
     * 2.	If rev > Integer.MIN_VALUE/10, then temp = rev*10+pop is guaranteed to overflow.
     * 3.	If rev == Integer.MIN_VALUE/10, then temp = rev*10+pop will overflow if and only if pop < -8
     *
     * Integer.MIN_VALUE is -2147483648
     * That means: Integer.MIN_VALUE/10 is -214748364
     * And you're about to do: rev = rev * 10 + pop
     *      So if rev > 214748364, then rev * 10 will cause overflow.
     *      Or if rev == 214748364, then rev * 10 is 2147483640, and rev * 10 + pop will cause overflow if pop < -8.
     *
     * Complexity Analysis
     * •	Time Complexity: O(log(x)). There are roughly log10(x) digits in x.
     * •	Space Complexity: O(1)
     *
     * @param x
     * @return int
     * */
    public int reverseIntUsingPopPush(int x) {
        long reverseInt = 0;

        while (x != 0) {
            //pop operation last digit of x
            int pop = x % 10;
            x /= 10;

            if (reverseInt > Integer.MAX_VALUE/10 || (reverseInt ==     Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (reverseInt < Integer.MIN_VALUE/10 || (reverseInt == Integer.MIN_VALUE / 10 && pop < -8)) return 0;

            // push that digit it to the back of the reverseInt.
            reverseInt = reverseInt*10 + pop;
        }

        return (int) reverseInt;
    }

    public static void main(String[] args) {
        ReverseInteger solution = new ReverseInteger();
        System.out.println(solution.reverseInt(12));

        System.out.println(solution.reverseIntUsingPopPush(12));
    }
}
