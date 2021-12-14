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
                if (c != strs[j].charAt(i) || i == strs[i].length()) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    /**
     * Approach 3: Using Divide and Conquer
     * To apply the observation above, we use divide and conquer technique,
     * where we split the LCP(Si…Sj) problem into two subproblems LCP(Si…Smid) and LCP(Smid+1…Sj), where mid is (i + j)/2
     * We use their solutions lcpLeft and lcpRight to construct the solution of the main problem LCP(Si…Sj).
     * To accomplish this we compare one by one the characters of lcpLeft and lcpRight till there is no character match.
     * The found common prefix of lcpLeft and lcpRight is the solution of the LCP(Si…Sj).
     *
     * Complexity Analysis
     * In the worst case we have nn equal strings with length m
     *
     * Time complexity : O(S), where SS is the number of all characters in the array, S = m*n.
     * Time complexity is 2*T(n/2)+O(m). Therefore time complexity is O(S).
     * In the best case this algorithm performs O(minLen*n) comparisons, where minLen is the shortest string of the array
     *
     * Space complexity : O(m*logn)
     * There is a memory overhead since we store recursive calls in the execution stack.
     * There are logn recursive calls, each store need mm space to store the result, so space complexity is O(m*logn)
     * @param strs
     * @return String
     * */
    public String longestCommonPrefixUsingDivideAndConquer(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        return longestCommonPrefix(strs, 0, strs.length - 1);
    }

    private String longestCommonPrefix(String[] strs, int left, int right) {
        if (left == right) return strs[left];
        else {
            int mid = (left + right)/2;
            String lcpLeft = longestCommonPrefix(strs, left, mid);
            String lcpRight = longestCommonPrefix(strs, mid+1, right);
            return commonPrefix(lcpLeft, lcpRight);
        }
    }

    public String commonPrefix(String lcpLeft, String lcpRight) {
        int minLen = Math.min(lcpLeft.length(), lcpRight.length());
        for (int i = 0; i < minLen; i++) {
            if (lcpLeft.charAt(i) != lcpRight.charAt(i)) {
                return lcpLeft.substring(0, i);
            }
        }

        return lcpLeft.substring(0, minLen);
    }

    /**
     * Approach 4: Binary Search
     * The idea is to apply binary search method to find the string with maximum value L, which is common prefix of all of the strings.
     * The algorithm searches space is the interval (0 \ldots minLen)(0…minLen), where minLen is minimum string length and the maximum possible common prefix.
     * Each time search space is divided in two equal parts, one of them is discarded, because it is sure that it doesn't contain the solution.
     *
     * There are two possible cases:
     * S[1...mid] is not a common string. This means that for each j > i S[1..j] is not a common string and we discard the second half of the search space.
     * S[1...mid] is common string. This means that for for each i < j S[1..i] is a common string and we discard the first half of the search space
     * , because we try to find longer common prefix.
     * @param strs
     * @return String
     * */
    public String longestCommonPrefixUsingBinarySearch(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        int minLen = Integer.MAX_VALUE;

        for (String str : strs) {
            minLen = Math.min(minLen, str.length());
        }

        int low = 1;
        int high = minLen;

        while (low <= high) {
            int middle = (low+high)/2;
            if (isCommonPrefix(strs, middle)) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }

        return strs[0].substring(0, (low+high)/2);
    }

    private boolean isCommonPrefix(String[] strs, int len){
        String str1 = strs[0].substring(0,len);
        for (int i = 1; i < strs.length; i++) {
            if (!strs[i].startsWith(str1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Approach 5: Using StringBuilder
     * The idea is to find the match between first and second string
     * @param strs
     * @return String
     * */
    public String longestCommonPrefixUsingStringBuilder(String[] strs) {
        if(strs == null || strs.length == 0) return "";
        if(strs.length == 1) return strs[0];

        // Check match between first and second string
        String result = returnMatch(strs[0], strs[1]);
        if(result == "") return "";

        // Check the rest of string with match
        for(int i = 2; i < strs.length; i++){
            String currentResult = returnMatch(strs[i], result);
            if(currentResult =="") return "";
            else result = currentResult;
        }
        return result;
    }

    public String returnMatch(String one, String two){
        StringBuilder sb =  new StringBuilder();
        int p1 = 0, p2 = 0;
        while(p1 < one.length() && p2 < two.length()){
            if(one.charAt(p1) == two.charAt(p2)){
                sb.append(one.charAt(p1));
                p1++;
                p2++;
            }
            else break;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LongestCommonPrefix solution = new LongestCommonPrefix();
        String[] strs = new String[]{"flower","flow","flight"};

        // C1
        System.out.println(solution.longestCommonPrefixUsingHorizontalScanning(strs));

        // C2
        System.out.println(solution.longestCommonPrefixUsingVerticalScanning(strs));

        // C3
        System.out.println(solution.longestCommonPrefixUsingDivideAndConquer(strs));

        // C4
        System.out.println(solution.longestCommonPrefixUsingBinarySearch(strs));

        // C5
        System.out.println(solution.longestCommonPrefixUsingStringBuilder(strs));
    }
}
