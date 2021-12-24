import java.util.Arrays;

public class ThreeSumClosest {
    /**
     * MEDIUM
     * 16. Three Sum Closest
     * Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
     * Return the sum of the three integers.
     * You may assume that each input would have exactly one solution.
     *
     * Example 1:
     * Input: nums = [-1,2,1,-4], target = 1
     * Output: 2
     * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
     *
     * Example 2:
     * Input: nums = [0,0,0], target = 1
     * Output: 0
     *
     * Constraints:
     * 3 <= nums.length <= 1000
     * -1000 <= nums[i] <= 1000
     * -104 <= target <= 104
     * */
    public static int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) return 0;
        Arrays.sort(nums);

        int j = 0;
        int k = 0;
        int sum = 0;
        int closestSum = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < nums.length-2; i++) {

            //Using two pointers
            j = i + 1;
            k = nums.length -1;
            while (j < k) {
                sum = nums[i] + nums[j] + nums[k];
                if (Math.abs(sum - target) < Math.abs(closestSum - target)) {
                    closestSum = sum;
                }
                if (sum < target) {
                    j++;
                } else {
                    k--;
                }
            }
        }

        return closestSum;
    }

    public static void main(String[] args) {
        ThreeSumClosest solution = new ThreeSumClosest();

        int[] nums = new int[]{-1, 2, 1, -4};
        System.out.println(solution.threeSumClosest(nums, 1));
    }
}
