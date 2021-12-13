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
     *
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
     *
     * Complexity Analysis
     * Time complexity : O(S), where S is the sum of all characters in all strings.
     * In the worst case all nn strings are the same. The algorithm compares the string S1 with the other strings S2…Sn
     * There are SS character comparisons, where SS is the sum of all characters in the input array.
     * Space complexity : O(1). We only used constant extra space.
     *
     * @param strs
     * @return String
     * */
    public String longestCommonPrefixUsingHorizontalScanning(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    /**
     * Approach 2: Vertical scanning
     *
     * Algorithm
     * Imagine a very short string is the common prefix at the end of the array. The above approach will still do SS comparisons.
     * One way to optimize this case is to do vertical scanning.
     * We compare characters from top to bottom on the same column (same character index of the strings) before moving on to the next column.
     *
     * Complexity Analysis
     * Time complexity : O(S), where S is the sum of all characters in all strings.
     * In the worst case there will be nn equal strings with length mm and the algorithm performs S=m*n character comparisons.
     * Even though the worst case is still the same as Approach 1, in the best case there are at most n*minLen comparisons
     * where minLen is the length of the shortest string in the array.
     * Space complexity : O(1). We only used constant extra space.
     *
     * @param strs
     * @return String
     * */
    public String longestCommonPrefixUsingVerticalScanning(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (c != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                } else if (i == strs[i].length()) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    public static void main(String[] args) {
        LongestCommonPrefix solution = new LongestCommonPrefix();
        String[] strs = new String[]{"flower","flow","flight"};

        // C1
        System.out.println(solution.longestCommonPrefixUsingHorizontalScanning(strs));

        // C2
        System.out.println(solution.longestCommonPrefixUsingVerticalScanning(strs));
    }
}
