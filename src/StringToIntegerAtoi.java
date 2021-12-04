public class StringToIntegerAtoi {
    /**
     * MEDIUM
     * 8. String to Integer (atoi)
     * The algorithm for myAtoi(string s) is as follows:
     *
     * a. Read in and ignore any leading whitespace.
     * b. Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it is either. This determines if the final result is negative or positive respectively. Assume the result is positive if neither is present.
     * c. Read in next the characters until the next non-digit character or the end of the input is reached. The rest of the string is ignored.
     * d. Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
     * e. If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then clamp the integer so that it remains in the range. Specifically, integers less than -231 should be clamped to -231, and integers greater than 231 - 1 should be clamped to 231 - 1.
     * f. Return the integer as the final result.
     * */
    public int myAtoi(String s) {
        if(s.equals("")) return 0;
        s = s.trim();

        int start = 0;
        int res = 0;
        boolean isPositive = true;

        if (s.startsWith("+") || s.startsWith("-")) {
            isPositive = s.startsWith("-") ? false : true;
            start = 1;
        }

        for (int i = start; i < s.length(); i++) {
            // Convert these digits into an integer
            int diff = s.charAt(i) - '0';

            if (diff >= 0 && diff < 10) {
                // Check integer in range [-231, 231 - 1]
                if ((Integer.MAX_VALUE - diff) / 10 < res) {
                    if (isPositive) {
                        return Integer.MAX_VALUE;
                    }
                    return Integer.MIN_VALUE;
                }
                res = res*10 + diff;
            } else {
                break;
            }
        }

       return isPositive ? res : -1*res;
    }

    public static void main(String[] args) {
        StringToIntegerAtoi solution = new StringToIntegerAtoi();
        System.out.println(solution.myAtoi("42"));
    }
}
