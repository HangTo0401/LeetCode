public class MedianOfTwoSortedArrays {
    /**
     * HARD
     * 4. Median of Two Sorted Arrays
     * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
     * The overall run time complexity should be O(log (m+n)).
     *
     * SOLUTION: Create merge arrays
     * Iterate nums1 and nums2 then add all values of nums1 and nums2 into merge arrays
     * Set medianDigit equal to length of merge arrays
     * Check if medianDigit is even then median = (mergeArrays[medianDigit] + mergeArrays[medianDigit-1]) /2
     * If it is odd then median = mergeArrays[medianDigit/2]
     * Return median
     *
     * Example 1:
     * Input: nums1 = [1,3], nums2 = [2]
     * Output: 2.00000
     * Explanation: merged array = [1,2,3] and median is 2.
     *
     * Example 2:
     * Input: nums1 = [1,2], nums2 = [3,4]
     * Output: 2.50000
     * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
     * */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length == 0 && nums2.length == 1) return nums2[0];
        if(nums1.length == 1 && nums2.length == 0) return nums1[0];

        int[] mergedArrays = new int[nums1.length + nums2.length];
        int i = 0;
        int j = 0;
        int k = 0;

        while(i < nums1.length && j < nums2.length){
            if (nums1[i] < nums2[j]) {
                mergedArrays[k++] = nums1[i++];
            } else {
                mergedArrays[k++] = nums2[j++];
            }
        }

        if(j < nums2.length){
            while (j < nums2.length) {
                mergedArrays[k++] = nums2[j++];
            }
        }

        if(i < nums1.length){
            while(i < nums1.length){
                mergedArrays[k++] = nums1[i++];
            }
        }

        // Find median of mergedArrays
        int medianDigit = mergedArrays.length;
        if (medianDigit % 2 != 0) {
            // Odd
            Double median = Double.valueOf(mergedArrays[medianDigit/2]);
            return median;
        } else {
            // Even
            medianDigit = medianDigit/2;
            Double median = Double.valueOf(mergedArrays[medianDigit]+mergedArrays[medianDigit-1])/2;
            return median;
        }
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays medianOfTwoSorted = new MedianOfTwoSortedArrays();

        // Create nums1 and nums2 arrays
        int[] nums1 = {1,3};
        int[] nums2 = {2};
        
        System.out.println(medianOfTwoSorted.findMedianSortedArrays(nums1, nums2));
    }
}
