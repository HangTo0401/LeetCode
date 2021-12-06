public class RegularExpressionMatching {
    /**
     * HARD
     * 10. Regular Expression Matching
     * Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
     *
     * '.' Matches any single character
     * '*' Matches zero or more of the preceding element.
     * The matching should cover the entire input string (not partial).
     *
     * Example 1:
     * Input: s = "aa", p = "a"
     * Output: false
     * Explanation: "a" does not match the entire string "aa".
     *
     * Example 2:
     * Input: s = "aa", p = "a*"
     * Output: true
     * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
     *
     * Example 3:
     * Input: s = "ab", p = ".*"
     * Output: true
     * Explanation: ".*" means "zero or more (*) of any character (.)".
     *
     * Example 4:
     * Input: s = "aab", p = "c*a*b"
     * Output: true
     * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
     *
     * Example 5:
     * Input: s = "mississippi", p = "mis*is*p*."
     * Output: false
     *
     * Constraints:
     * 1 <= s.length <= 20
     * 1 <= p.length <= 30
     *
     * s contains only lowercase English letters.
     * p contains only lowercase English letters, '.', and '*'.
     * It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
     *
     * 1. Using matches method in Java
     * @param s
     * @param pattern
     * @return boolean
     * */
    public boolean isMatch(String s, String pattern) {
        return s.matches(pattern);
    }

    /**
     * 2. Using Recursion
     * If a star * is present in the pattern, it will be in the second position pattern[1].
     * Then, we may ignore this part of the pattern, or delete a matching character in the text.
     * If we have a match on the remaining strings after any of these operations, then the initial inputs matched.
     *
     * @param s
     * @param pattern
     * @return boolean
     * */
    public boolean isMatchUsingRecursion(String s, String pattern) {
        if (pattern.isEmpty()) return s.isEmpty();
        boolean first_match = (!s.isEmpty() && (pattern.charAt(0) == s.charAt(0) || pattern.charAt(0) == '.'));

        // Check case pattern is a*
        // Then, we may ignore this part of the pattern, or delete a matching character in the text.
        if(pattern.length() >= 2 && pattern.charAt(1) == '*') {
            return isMatch(s, pattern.substring(2)) || (first_match && isMatch(s.substring(1), pattern));
        }
        // Check match on remaining string except first character
        return first_match && isMatch(s.substring(1), pattern.substring(1));
    }

    /**
     * 3. Using Dynamic Programming
     * We proceed with the same recursion as in Approach 1,
     * except because calls will only ever be made to match(text[i:], pattern[j:]),
     * we use dp(i, j) to handle those calls instead, saving us expensive string-building operations
     * and allowing us to cache the intermediate results.
     * https://www.youtube.com/watch?v=l3hda49XcDE
     *
     * @param s
     * @param p
     * @return boolean
     * */
    public boolean isMatchUsingDynamicProgramming(String s, String p) {
        // return s.matches(p);
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];

        // When we have empty string and empty pattern,
        // it's also counted for Regular Expression match
        dp[0][0] = true;

        //Deals with patterns like a* or a*b* or a*b*c*
        for (int i = 1; i < dp[0].length; i++) {
            if (p.charAt(i-1) == '*') {
                dp[0][i] = dp[0][i-2];
            }
        }

        // i for string s and j for pattern
        for (int i = 1; i < dp.length;i++){
            for (int j = 1; j < dp[0].length;j++){
                // Check first match
                if (p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.') {
                    dp[i][j] = dp[i-1][j-1];
                } else if (p.charAt(j-1) == '*') {
                    dp[i][j] = dp[i][j-2];
                    if (s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.') {
                        dp[i][j] = dp[i][j] | dp[i-1][j];
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }

        return dp[s.length()][p.length()];
    }

    public static void main(String[] args) {
        RegularExpressionMatching solution = new RegularExpressionMatching();

        // C1
        System.out.println(solution.isMatch("aa", "ab*a*c*a"));

        // C2
        System.out.println(solution.isMatchUsingRecursion("aa", "ab*a*c*a"));

        // C3
        System.out.println(solution.isMatchUsingDynamicProgramming("aaa", "ab*a*c*a"));
    }
}
