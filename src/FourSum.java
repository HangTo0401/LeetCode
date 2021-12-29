import java.util.*;

public class FourSum {
    /**
     * MEDIUM
     * 18. Four Sum
     * Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
     * 0 <= a, b, c, d < n
     * a, b, c, and d are distinct.
     * nums[a] + nums[b] + nums[c] + nums[d] == target
     * You may return the answer in any order.
     *
     * Example 1:
     * Input: nums = [1,0,-1,0,-2,2], target = 0
     * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
     *
     * Example 2:
     * Input: nums = [2,2,2,2,2], target = 8
     * Output: [[2,2,2,2]]
     *
     * Constraints:
     * 1 <= nums.length <= 200
     * -109 <= nums[i] <= 109
     * -109 <= target <= 109
     * */


    /**
     * Algorithm
     * We can implement k - 2 loops using a recursion. We will pass the starting point and k as the parameters.
     * When k == 2, we will call twoSum, terminating the recursion.
     *
     * For the main function:
     *    Sort the input array nums.
     *    Call kSum with start = 0, k = 4, and target, and return the result.
     *
     * For kSum function:
     * At the start of the kSum function, we will check three conditions:
     * a. Have we run out of numbers to choose from?
     * b. Is the smallest number remaining greater than target / k? If so, then any k numbers we choose will be too large.
     * c. Is the largest number remaining smaller than target / k? If so, then any k numbers we choose will be too small.
     * If any of these conditions is true, there is no need to continue as no combination of the remaining elements can sum to target.
     *
     * If k equals 2, call twoSum and return the result.
     * Iterate i through the array from start:
     * a. If the current value is the same as the one before, skip it.
     * b. Recursively call kSum with start = i + 1, k = k - 1, and target - nums[i].
     * c. For each returned subset of values:
     *      Include the current value nums[i] into subset.
     *      Add subset to the result res.
     * Return the result res.
     *
     * For twoSum function:
     * Set the low pointer lo to start, and high pointer hi to the last index.
     * While low pointer is smaller than high:
     *  a. If the sum of nums[lo] and nums[hi] is less than target, increment lo.
     *      Also increment lo if the value is the same as for lo - 1.
     *  b. If the sum is greater than target, decrement hi.
     *      Also decrement hi if the value is the same as for hi + 1.
     *  c. Otherwise, we found a pair:
     *      Add it to the result res.
     *      Decrement hi and increment lo.
     * Return the result res.
     * */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }

    public List<List<Integer>> kSum(int[] nums, int target, int start, int k) {
        List<List<Integer>> res = new ArrayList<>();

        // If we have run out of numbers to add, return res.
        if (start == nums.length) {
            return res;
        }

        // There are k remaining values to add to the sum. The
        // average of these values is at least target / k.
        int average_value = target / k;

        // We cannot obtain a sum of target if the smallest value
        // in nums is greater than target / k or if the largest
        // value in nums is smaller than target / k.
        if (nums[start] > average_value || average_value > nums[nums.length - 1]) {
            return res;
        }

        if (k == 2) {
            return twoSum(nums, start, target);
        }

        for (int i = start; i < nums.length; ++i) {
            if (i == start || nums[i - 1] != nums[i]) {
                for (List<Integer> subset : kSum(nums, target - nums[i], i + 1, k - 1)) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i])));
                    res.get(res.size() - 1).addAll(subset);
                }
            }
        }
        ;
        return res;
    }

    public List<List<Integer>> twoSum(int[] nums, int start, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        for (int i = start; i < nums.length; ++i) {
            if (res.isEmpty() || res.get(res.size() - 1).get(1) != nums[i]) {
                if (set.contains(target - nums[i])) {
                    res.add(Arrays.asList(target - nums[i], nums[i]));
                }
            }

            set.add(nums[i]);
        }

        return res;
    }

    public static void main(String[] args) {
        FourSum solution = new FourSum();
        int[] nums = new int[]{1,0,-1,0,-2,2};

        List<List<Integer>> result = solution.fourSum(nums, 0);
        result.forEach(System.out::println);
    }
}
