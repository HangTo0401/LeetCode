import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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

    // Approach 1A: Sort by Row
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

    /**
     * Algorithm
     * We can use min(numRows,len(s)) lists to represent the non-empty rows of the Zig-Zag Pattern.
     * Iterate through s from left to right, appending each character to the appropriate row.
     * The appropriate row can be tracked using two variables: the current row and the current direction.
     * The current direction changes only when we moved up to the topmost row or moved down to the bottommost row.
     *
     * @param s
     * @param numRows
     * @return String
     * */
    // Approach 1B: Sort by Row
    public String convertToZigZagSortByRow(String s, int numRows) {
        if (numRows == 1) return s;

        int currentRow = 0;
        boolean goingUp = false;

        List<StringBuilder> list = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            list.add(new StringBuilder());
        }

        for (char c : s.toCharArray()) {
            list.get(currentRow).append(c);
            if (currentRow == 0 || currentRow == numRows -1) {
                goingUp = !goingUp;
            }
            currentRow += goingUp == true ? 1 : -1;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (StringBuilder str : list) {
            stringBuilder.append(str);
        }

        return stringBuilder.toString();
    }

    /**
     * Algorithm
     * Visit all characters in row 0 first, then row 1, then row 2, and so on...
     * For all whole numbers k, interval is (2*numRows−2)
     * First row 0: Characters in row 0 are located at indexes k is k+=(2*numRows−2)+0
     * Last row (numRows-1): Characters in row numRows−1 are located at indexes k is k+=(2*numRows−2)+numRows−1
     * Characters in inner row i are located at indexes k is (2*numRows−2)+i and (k+1) is (2*numRows−2)−i.
     *
     * @param s
     * @param numRows
     * @return String
     * */
    // Approach 2: Visit By Row
    public String convertZigZagVisitByRow(String s, int numRows) {
        if (numRows == 1) return s;

        int interval = 2 * numRows - 2;
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < s.length(); j+=interval) {
                stringBuilder.append(s.charAt(j+i));

                // Other case not first row and last row
                if (i != 0 && i != numRows -1 && j + interval - i < s.length()){
                    stringBuilder.append(s.charAt(j + interval - i));
                }
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        ZigZagConversion zigZagConversion = new ZigZagConversion();
        // C1A
        System.out.println(zigZagConversion.convertZigZag("PAYPALISHIRING", 3));

        // C1B
        System.out.println(zigZagConversion.convertToZigZagSortByRow("PAYPALISHIRING", 3));

        // C2
        System.out.println(zigZagConversion.convertZigZagVisitByRow("PAYPALISHIRING", 3));
    }
}
