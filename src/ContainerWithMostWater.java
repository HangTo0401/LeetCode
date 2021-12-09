public class ContainerWithMostWater {
    /**
     * MEDIUM
     * 11. Container With Most Water
     * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
     * n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0).
     * Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.
     * Notice that you may not slant the container.
     *
     * Example 1:
     * Input: height = [1,8,6,2,5,4,8,3,7]
     * Output: 49
     * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
     * In this case, the max area of water (blue section) the container can contain is 49.
     *
     * Example 2:
     * Input: height = [1,1]
     * Output: 1
     *
     * Example 3:
     * Input: height = [4,3,2,1,4]
     * Output: 16
     *
     * Example 4:
     * Input: height = [1,2,1]
     * Output: 2
     *
     * Constraints:
     * n == height.length
     * 2 <= n <= 105
     * 0 <= height[i] <= 104
     * */

    /**
     * Approach 1: BruteForce using nested loop
     * @param height
     * @return int
     * */
    public int maxAreaBruteForce(int[] height) {
        int maxArea = 0;

        for (int i = 0; i < height.length; i++) {
            for (int j = i+1; j < height.length; j++) {
                maxArea = Math.max(maxArea, Math.min(height[i], height[j]) * (j-i));
            }
        }
        return maxArea;
    }

    /**
     * Approach 2: Two Pointer Approach
     *
     * Algorithm
     * The intuition behind this approach is that the area formed between the lines will always be limited by the height of the shorter line.
     * Further, the farther the lines, the more will be the area obtained.
     *
     * We take two pointers, one at the beginning and one at the end of the array constituting the length of the lines.
     * Futher, we maintain a variable maxarea to store the maximum area obtained till now.
     *
     * At every step, we find out the area formed between them,
     * update maxarea and move the pointer pointing to the shorter line towards the other end by one step.
     * The algorithm can be better understood by looking at the example below:
     * @param height
     * @return int
     * */
    public int maxAreaUsingTwoPointers(int[] height) {
        int maxArea = 0;
        int begin = 0;
        int end = height.length - 1;

        while(begin < end) {
            maxArea = Math.max(maxArea, Math.min(height[begin], height[end]) * (end - begin));
            if (height[begin] < height[end]) {
                begin++;
            } else {
                end--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        ContainerWithMostWater solution = new ContainerWithMostWater();

        int[] height = new int[]{1,8,6,2,5,4,8,3,7};

        // C1
        System.out.println(solution.maxAreaBruteForce(height));

        // C2
        System.out.println(solution.maxAreaUsingTwoPointers(height));
    }
}
