public class LongestPalindromicSubstring {

    /**
     * MEDIUM
     * 5. Given a string s, return the longest palindromic substring in s.
     *
     * SOLUTION: There are 3 solutions to this problem
     *
     * C3. Create resultStart with the begin index of the longest substring
     * resultLength is length of the longest substring
     * Iterate through each character and find the middle point of string s
     * Then we expand from middle point on both sides to find the longest palindrome substring
     *
     * We have 2 possible of middle point :
     * For even length: middle = middle to middle +1
     * For odd length: middle = middle
     *
     * How to check Palindrome?
     * Using 2 pointers, start in middle element of array the traverse outward and compare value at 2 pointers is equal or not
     * If value in 2 pointers is same then keep tracking to find index of start and end of substring
     * Then compare if resultLength < end - begin -1 then update resultStart and resultLength
     * Return substring from index resultStart to (resultStart + resultLength)
     *
     * Example 1:
     * Input: s = "babad"
     * Output: "bab"
     * Note: "aba" is also a valid answer.
     *
     * Example 2:
     * Input: s = "ac"
     * Output: "a"
     * */
    // Approach 1: Brute Force
    public String longestPalindromeSubstringUsingBruteForce(String s) {
        if (s.length() <= 1) return s;

        String longestPalindromeSubstring = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String substr = s.substring(i, j+1);
                if (isPalindrome(substr) && substr.length() > longestPalindromeSubstring.length()) {
                    // Update longestPalindromeSubstring
                    longestPalindromeSubstring = substr;
                }
            }
        }

        return longestPalindromeSubstring;
    }

    public boolean isPalindrome(String s) {
        StringBuilder stringBuilder = new StringBuilder(s);
        String reversed = stringBuilder.reverse().toString();
        return reversed.equals(s);
    }

    // Approach 2: Dynamic Programming
    public String longestPalindromeUsingDynamicProgramming(String s) {
        if(s == null || s.length() <= 1 ) return s;

        int len = s.length();

        boolean[][] dp = new boolean[len][len];

        int startMax = 0, maxLen = 1;
        for(int end = 0; end < s.length(); end++) {
            for(int start = 0; start <= end; start++) {
                if(s.charAt(start) == s.charAt(end)) {
                    if (end - start > 2) {
                        dp[start][end] = dp[start + 1][end - 1];
                    } else {
                        dp[start][end] =  true;
                    }
                }

                if(dp[start][end] == true && end - start + 1 > maxLen) {
                    maxLen = end - start + 1;
                    startMax = start;
                }
            }
        }

        return s.substring(startMax, startMax + maxLen);
    }

    private int  resultStart = 0;
    private int  resultLength = 0;
    // Approach 3: Expand Around Center
    // This function is used to expand substring on both sides
    public void expandRange(String s, int begin, int end) {
        while (begin >=0 && end < s.length() && s.charAt(begin) == s.charAt(end)) {
            // Expand range on both sides
            begin--;
            end++;
        }

        // Check if any substring has longer than resultLength then update
        if (resultLength < end - begin - 1) {
            /**
             * The correct start is "begin + 1" and correct end is "end - 1".
             * The inclusive length of the substring thus is (end - 1) - (begin + 1) + 1 (due to inclusive range)
             * which is equal to "end - begin - 1"
             */
            resultStart = begin + 1;
            resultLength = end - begin - 1;
        }
    }

    public String longestPalindromeSubstringUsingExpandAroundCenter(String s) {
        if (s.length() < 2) return s;

        for (int start = 0; start < s.length(); start++) {
            expandRange(s, start, start); //If s.length is odd then we have one middle point
            expandRange(s, start, start +1); //If s.length is even then we have two middle point
        }

        return s.substring(resultStart, resultStart+resultLength);
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();

        // C1
        System.out.println(longestPalindromicSubstring.longestPalindromeSubstringUsingBruteForce("babad"));

        // C2
        System.out.println(longestPalindromicSubstring.longestPalindromeUsingDynamicProgramming("babad"));

        // C3
        System.out.println(longestPalindromicSubstring.longestPalindromeSubstringUsingExpandAroundCenter("babad"));
    }
}
