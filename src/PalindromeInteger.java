public class PalindromeInteger {
    /**
     * EASY
     * 9. Given an integer x, return true if x is palindrome integer.
     * An integer is a palindrome when it reads the same backward as forward. For example, 121 is palindrome while 123 is not.
     *
     * Example 1:
     *
     * Input: x = 121
     * Output: true
     * Example 2:
     *
     * Input: x = -121
     * Output: false
     * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
     * Example 3:
     *
     * Input: x = 10
     * Output: false
     * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
     * Example 4:
     *
     * Input: x = -101
     * Output: false
     *
     * Constraints:
     * -231 <= x <= 231 - 1
     *
     * Follow up: Could you solve it without converting the integer to a string?
     * */
    public boolean isPalindrome(int x) {
        if(x < 0) return false;
        int original = x;
        int rev = 0;
        while(x != 0) {
            int digit = x % 10;
            if (rev > Integer.MAX_VALUE || (rev == Integer.MAX_VALUE/10 && digit > 7)) return false;
            if (rev < Integer.MIN_VALUE || (rev == Integer.MIN_VALUE/10 && digit < -8)) return false;
            rev = rev*10 + digit;
            x = x /10;
        }
        return original == rev;
    }

    public static void main(String[] args) {
        PalindromeInteger solution = new PalindromeInteger();
        System.out.println(solution.isPalindrome(122));
    }
}
