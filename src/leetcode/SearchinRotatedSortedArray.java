package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SearchinRotatedSortedArray
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : TODO
 */
public class SearchinRotatedSortedArray {

    /**
     * 33. Search in Rotated Sorted Array
     * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

     (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

     You are given a target value to search. If found in the array return its index, otherwise return -1.

     You may assume no duplicate exists in the array.

     4 5 6 7 0 1 2

     4 5 6 0 1 2 3

     time : O(logn);
     space : O(1);
     * @param nums
     * @param target
     * @return
     */
    //interview friendly
    // thinking process:
    // so the array is like way up and way down, eg, 5,6,7,8,1,2,3
    // mid has two cases: 1 is in 5,6,7,8 another is in 1,2,3 range, so we need to have 
    // if else to handle the situation, suppose mid is in 5,6,7,8, target also have two cases
    //one is in 5,6,7,8 another one is in 1,2,4 if it is in 5,6,7,8, we need to move end = mid
    //else move left = mid since we want to narrow down the range
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] == target) return mid;
          //which means we are in ascend sequence maybe in first or second part
            if (nums[left] < nums[mid]) {
                // 5,6,7,8,1,2,3 left = 5 mid = 8, target is 2 so we want to 
                //make sure target is between left and mid because we want to 
                if (nums[left] <= target && target <= nums[mid]) {
                    right = mid;
                 //not here we only have > or < no equals
                } else {
                    left = mid;
                }
            //which means we are in descend sequence maybe partially 
            // but we examine from mid to end and move start to mid
            //nums[start] >= nums[mid]
            } else {
                if (nums[mid] <= target && target <= nums[right]) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
        }
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        return -1;
    }
    
    // another solutions
    public int search2(int[] nums, int target) {
        int n = nums.length;
        int lo = 0, hi = nums.length - 1;
        // find the index of the smallest value using binary search.
        // Loop will terminate since mid < hi, and lo or hi will shrink by at least 1.
        // Proof by contradiction that mid < hi: if mid==hi, then lo==hi and loop would
        // have been terminated.
        while (lo < hi) {
            int mid = lo + (hi -lo) / 2;
            if (nums[mid] > nums[hi]) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        // lo==hi is the index of the smallest value and also the number of places
        // rotated.
        int rot = lo;
        lo = 0;
        hi = n - 1;
        // The usual binary search and accounting for rotation.
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int realmid = (mid + rot) % n;
            if (nums[realmid] == target)
                return realmid;
            if (nums[realmid] < target)
                lo = mid + 1;
            else
                hi = mid - 1;
        }
        return -1;
    }
}
