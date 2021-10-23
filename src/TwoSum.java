import java.util.HashMap;

public class TwoSum {

    /**
     * EASY
     * 1. Given an array of integers nums and an integer target
     * Return index of the two numbers such that they add up to target number.
     *
     * SOLUTION: Create hashmap to store value with format {key: value, value: index}
     * Traverse through nums list and check if hashmap already have the number which sum with nums[i] equal to target
     * If it already had, then get the index of the two numbers
     * If not, then keep put value into map with format {key: value, value: index}
     *
     * Example 1:
     * Input: nums = [2,7,11,15], target = 9
     * Output: [0,1]
     * Output: Because nums[0] + nums[1] == 9, we return [0, 1].
     * */
    public static int[] getTwoSum(int[] nums, int target) {
        int[] output = new int[2];
        HashMap map = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            // First number is the number that map contains which sum of first number and nums[i] equal to target
            // Second number is nums[i]
            // Check whether map contains value that sum with nums[i] equal to target
            if (map.get(target - nums[i]) != null) {
                output[0] = (int) map.get(target - nums[i]);
                output[1] = i;
                break;
            }

            // Map store values with format (value, index)
            map.put(nums[i], i);
        }

        return output;
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();

        int[] nums = {3,2,4,6,0};
        int[] result = twoSum.getTwoSum(nums, 10);

        for (int x : result) {
            System.out.println(x);
        }
    }
}
