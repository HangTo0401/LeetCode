import java.util.*;
import java.util.stream.IntStream;

public class ThreeSum {
    /**
     * MEDIUM
     * 15. Three Sum
     * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
     * Notice that the solution set must not contain duplicate triplets.
     *
     * Example 1:
     * Input: nums = [-1,0,1,2,-1,-4]
     * Output: [[-1,-1,2],[-1,0,1]]
     *
     * Example 2:
     * Input: nums = []
     * Output: []
     *
     * Example 3:
     * Input: nums = [0]
     * Output: []
     *
     * Constraints:
     * 0 <= nums.length <= 3000
     * -105 <= nums[i] <= 105
     *
     * Hint 1: So, we essentially need to find three numbers x, y, and z such that they add up to the given value.
     * If we fix one of the numbers say x, we are left with the two-sum problem at hand!
     *
     * Hint 2:
     * For the two-sum problem, if we fix one of the numbers, say x
     * , we have to scan the entire array to find the next number y
     * which is value - x
     * where value is the input parameter. Can we change our array somehow so that this search becomes faster?
     *
     * Hint 3: The second train of thought for two-sum is, without changing the array, can we use additional space somehow?
     * Like maybe a hash map to speed up the search?
     * */

    /**
     * Approach 1: Using Hashset and Two pointers
     * @param nums
     * */
    public List<List<Integer>> threeSum(int[] nums) {
        if(nums.length==0 || nums==null) return new ArrayList<>();

        Arrays.sort(nums);
        HashSet set = new HashSet();

        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    set.add(Arrays.asList(nums[i], nums[left], nums[right]));
                }
                if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        List<List<Integer>> list = new ArrayList<List<Integer>>(set);
        return list;
    }

    /**
     * Approach 2: Using TwoSum method
     * Time complexity is still O(N^2)
     * @param nums
     * */
    public static List<List<Integer>> threeSumUsingTwoSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums.length < 3) return res;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;
            ArrayList<List<Integer>> twoSumList = twoSum(nums, i, 0 - nums[i]);
            for (List<Integer> ls : twoSumList) {
                ls.add(nums[i]);
                res.add(ls);
            }
        }

        return res;
    }

    public static ArrayList<List<Integer>> twoSum(int[] nums, int index, int target) {
        HashMap<Integer, Integer> map = new HashMap();
        int count = 0;
        HashSet<ArrayList<Integer>> set = new HashSet();

        for (int i = index + 1; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                ArrayList<Integer> cur = new ArrayList<>();
                cur.add(target - nums[i]);
                cur.add(nums[i]);
                Collections.sort(cur);
                set.add(cur);
                break;
            }
            map.put(nums[i], count++);
        }

        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>(set);
        return result;
    }

    public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();
        int[] nums1 = new int[]{-1,0,1,2,-1,-4};

        // C1
        solution.threeSum(nums1);

        // C2
        solution.threeSumUsingTwoSum(nums1);
    }
}
