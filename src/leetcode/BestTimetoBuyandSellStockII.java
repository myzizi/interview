package leetcode;

/**
 * Created by duqiang on 28/07/2017.
 */
public class BestTimetoBuyandSellStockII {
    /**
     * 122. Best Time to Buy and Sell Stock II
     * Say you have an array for which the ith element is the price of a given stock on day i.

     Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However,
     you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

     case : [5, 1, 2, 3, 4]

     time : O(n);
     space : O(1);
     * @param prices
     * @return
     */
    public static int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }
    
    // using DP dp store the max value for {1,2,3...n -1}
    public int maxProfit(int[] prices) {
        //edge case
        if (prices == null || prices.length == 0 || prices.length == 1) {
            return 0;
        }
        
        //dp[n] to describe  the n integers max--profit, dp[0] = 0, dp[1] = Math.max(nums[1] - nums[0], 0); dp[n] 
        
        int len = prices.length - 1;
        int[] dp = new int[len + 1];
        dp[0] = 0;
        for(int i = 1; i <= len; i++) {
            dp[i] = dp[i-1] + Math.max(prices[i] - prices[i-1], 0);
        }
        
        return dp[len];
    }
}
