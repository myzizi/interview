package hatecode._1000_1999;
public class _1176DietPlanPerformance {
/*
1176. Diet Plan Performance
A dieter consumes calories[i] calories on the i-th day. 

Given an integer k, for every consecutive sequence of k days (calories[i], calories[i+1], ..., calories[i+k-1] for all 0 <= i <= n-k), they look at T, the total calories consumed during that sequence of k days (calories[i] + calories[i+1] + ... + calories[i+k-1]):

If T < lower, they performed poorly on their diet and lose 1 point; 
If T > upper, they performed well on their diet and gain 1 point;
Otherwise, they performed normally and there is no change in points.
Initially, the dieter has zero points. Return the total number of points the dieter has after dieting for calories.length days.

Note that the total points can be negative.

 

Example 1:

Input: calories = [1,2,3,4,5], k = 1, lower = 3, upper = 3
Output: 0
*/
    
    //thinking process: O(n)/O(1)
    //the problem is to say: given an array A, a for length k of its subarray,
    //if its sum > upper, then +1, if less than lower than -1,
    //return points after all. 
    
    //so sliding window, but we need to care when K is >= its array length,so we
    //can use i-k to minimize this impact.
    public int dietPlanPerformance(int[] A, int k, int lower, int upper) {
        if (A == null || A.length < 1 || k < 1 || lower >= upper)
            return 0;

        int res = 0;
        
        //two pointer or sliding window templates
        for (int i = 0, win = 0; i < A.length; ++i) {
            win += A[i];
            //we remove first element, if window is bigger
            if (i >= k - 1) {
                if (i > k - 1) win -= A[i - k];

                if (win < lower)       --res;
                else if (win > upper)  ++res;
            }
        }
        return res;

    }
}