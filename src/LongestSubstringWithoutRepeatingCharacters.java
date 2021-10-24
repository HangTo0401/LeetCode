import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * MEDIUM
     * 3. Longest Substring Without Repeating Characters
     * Given a string s, find the length of the longest substring without repeating characters.
     *
     * SOLUTION: Create Hashset and variable maxLength to store length of max substring
     * Create index i and j
     * Then iterate through all characters in input s and check if hashset contain any character at index i
     * If it contains, set maxLength as max(size of hashset, maxLength) and increase index i
     * If not, remove one character in set and increase index j
     *
     * Example 1:
     * Input: s = "abcabcbb"
     * Output: 3
     * Explanation: The answer is "abc", with the length of 3.
     *
     * Example 2:
     * Input: s = "pwwkew"
     * Output: 3
     * Explanation: The answer is "wke", with the length of 3.
     * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.equals("")) return 0;

        HashSet set = new HashSet();
        int maxLengthOfSubstring = 0;
        int j =0;
        for (int i =0; i < s.length(); ) {
            if (!set.contains(s.charAt(i))) {
                // Set does not contain character at index i
                // Add character in set
                set.add(s.charAt(i));

                // Check size of set with maxLengthOfSubstring then increase i
                maxLengthOfSubstring = Math.max(set.size(), maxLengthOfSubstring);
                i++;
            } else {
                // If set already contain character at index i, then remove one character in set
                // We remove character at index j and increase j
                set.remove(s.charAt(j));
                j++;
            }
        }

        return maxLengthOfSubstring;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters longestSubstring = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(longestSubstring.lengthOfLongestSubstring("abcaecbb"));
    }
}
