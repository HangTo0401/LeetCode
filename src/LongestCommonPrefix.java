public class LongestCommonPrefix {

    /**
     * EASY
     * 14. Longest Common Prefix
     * Write a function to find the longest common prefix string amongst an array of strings.
     * If there is no common prefix, return an empty string "".
     *
     * Example 1:
     * Input: strs = ["flower","flow","flight"]
     * Output: "fl"
     *
     * Example 2:
     * Input: strs = ["dog","racecar","car"]
     * Output: ""
     * Explanation: There is no common prefix among the input strings.
     *
     * Constraints:
     * 1 <= strs.length <= 200
     * 0 <= strs[i].length <= 200
     * strs[i] consists of only lower-case English letters.
     * Accepted
     * 1,313,856
     * Submissions
     * 3,429,365
     * */

    /**
     * Approach 1: Horizontal scanning
     *
     * Algorithm
     * To employ this idea, the algorithm iterates through the strings S1…Sn
     * , finding at each iteration ii the longest common prefix of strings LCP(S1…Si)
     * When LCP(S1…Si) is an empty string, the algorithm ends.
     * Otherwise, after nn iterations, the algorithm returns LCP(S1…Sn).
     * @param strs
     * @return String
     * */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";

        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        LongestCommonPrefix solution = new LongestCommonPrefix();
        String[] strs = new String[]{"flower","flow","flight"};

        // C1
        System.out.println(solution.longestCommonPrefix(strs));
    }
}
