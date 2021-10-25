import java.util.LinkedHashMap;

public class ZigZagConversion {

    /**
     * MEDIUM
     * 6. ZigZag Conversion
     * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
     * (you may want to display this pattern in a fixed font for better legibility)
     *
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     *
     * And then read line by line: "PAHNAPLSIIGYIR"
     * Write the code that will take a string and make this conversion given a number of rows:
     * string convertZigZag(string s, int numRows);
     *
     * SOLUTION: Create LinkedHashMap to store each row in numRows
     * Check position == 1 then goes up => pos++;
     * Check position == numRows then goes down => pos--;
     *
     * If we have position in LinkedHashMap then we append the character to that position
     * If not, create new position with key = position and value = new StringBuilder
     * Then we create new StringBuilder to store all value in map in same line
     *
     * Example 1:
     * Input: s = "PAYPALISHIRING", numRows = 3
     * Output: "PAHNAPLSIIGYIR"
     *
     * Example 2:
     * Input: s = "PAYPALISHIRING", numRows = 4
     * Output: "PINALSIGYAHRPI"
     * Explanation:
     * P     I    N
     * A   L S  I G
     * Y A   H R
     * P     I
     * */

    // Approach 1: Sort by Row
    public String convertZigZag(String s, int numRows) {
        if (numRows == 1) return s;
        int pos = 0;

        // Check if we increment position of character or not
        boolean incrementPos = true;

        // Create linked hashmap which size of map will be equal numRows
        LinkedHashMap<Integer, StringBuilder> map = new LinkedHashMap<>();

        for (char c : s.toCharArray()) {
            // Print each character in string
            if (pos == numRows) incrementPos = false;//go down
            if (pos == 1) incrementPos = true;//go up

            if (incrementPos) {
                // Increase position of character
                pos++;
            } else {
                // Decrease position of character
                pos--;
            }

            // Create new line if map does not contain pos
            if (!map.containsKey(pos)) {
                map.put(pos, new StringBuilder());
            }

            // Append new character if map contain pos
            map.get(pos).append(c);
        }

        // Print Zig zag
        StringBuilder result = new StringBuilder();
        for (int i: map.keySet()) {
            result.append(map.get(i));
        }

        return result.toString();
    }

    public static void main(String[] args) {
        ZigZagConversion zigZagConversion = new ZigZagConversion();
        System.out.println(zigZagConversion.convertZigZag("PAYPALISHIRING", 3));
    }
}
