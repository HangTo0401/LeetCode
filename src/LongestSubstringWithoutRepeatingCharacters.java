import javafx.scene.Parent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * MEDIUM
     * 3. Longest Substring Without Repeating Characters
     * Given a string s, find the length of the longest substring without repeating characters.
     *
     * SOLUTION: There are 3 solutions to this problem
     *
     * Create Hashset and variable maxLength to store length of max substring
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

    // Approach 1: Brute Force
    public int lengthOfLongestSubstringBruteForce(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (checkDuplicate(s, i, j)) {
                    result = Math.max(result, j-i+1);
                }
            }
        }
        return result;
    }

    public boolean checkDuplicate(String s, int start, int end) {
        int[] chars = new int[128];

        for (int i = start; i <= end; i++) {
            char c = s.charAt(i);
            chars[c]++;
            if (chars[c] > 1) {
                return false;
            }
        }
        return true;
    }

    // Approach 2: Sliding Window
    public int lengthOfLongestSubstringUsingSlidingWindow(String s) {
        int[] chars = new int[128];

        int left = 0;
        int right = 0;

        int res = 0;
        while (right < s.length()) {
            char r = s.charAt(right);
            chars[r]++;

            while (chars[r] > 1) {
                char l = s.charAt(left);
                chars[l]--;
                left++;
            }

            res = Math.max(res, right - left + 1);

            right++;
        }
        return res;
    }

    // Approach 3: Sliding Window Optimized
    public int lengthOfLongestSubstringUsingHashmap(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)) + 1, i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j);
        }
        return ans;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters longestSubstring = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(longestSubstring.lengthOfLongestSubstring("abcaecbb"));

        // C1
        System.out.println(longestSubstring.lengthOfLongestSubstringBruteForce("abcaecbb"));

        // C2
        System.out.println(longestSubstring.lengthOfLongestSubstringUsingSlidingWindow("abcaecbb"));

        // C3
        System.out.println(longestSubstring.lengthOfLongestSubstringUsingHashmap("abcaecbb"));
    }
}
