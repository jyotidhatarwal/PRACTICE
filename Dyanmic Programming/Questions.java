
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

//~~~~~~~~~~~~~~~~    BUY AND SELL STOCKS   (6 VARIATIONS)      



//  Best Time to Buy and Sell Stock   (LC-121)


class Solution {
    public int maxProfit(int[] prices) {
        int leastSoFar = Integer.MAX_VALUE;
        int overallProfit =0;
        int profitIfSoldToday = 0;
        for(int i=0;i<prices.length;i++){
            if(prices[i] < leastSoFar){
                leastSoFar = prices[i];
            }
            profitIfSoldToday = prices[i] - leastSoFar;
            if(profitIfSoldToday > overallProfit){
                overallProfit = profitIfSoldToday;
            }
        }
        return overallProfit;
    }
}



//  Best Time to Buy and Sell Stock II    (LC-122)    


class Solution {
    public int maxProfit(int[] prices) {
        int buyDate =0;
        int sellDate =0;
        int profit =0;
        for(int i=1;i<prices.length;i++){
            if(prices[i] >= prices[i-1]){
                sellDate++;
            }else{
                profit += prices[sellDate] - prices[buyDate];
                buyDate = sellDate = i;
            }
        }
        profit += prices[sellDate] - prices[buyDate];
        return profit;
    }
}



//  Best Time to Buy and Sell Stock with Transaction Fee    (LC-714)    



class Solution {
    public int maxProfit(int[] prices, int fee) {
        int oldBoughtStateProfit = -prices[0];
        int oldSoldStateProfit = 0;
        for(int i=1;i<prices.length;i++){
            int newBoughtStateProfit =0;
            int newSoldStateProfit =0;
            // if bought today will it be profitable
            if(oldSoldStateProfit - prices[i] > oldBoughtStateProfit){
                newBoughtStateProfit = oldSoldStateProfit - prices[i];
            }else{
                newBoughtStateProfit = oldBoughtStateProfit;
            }
            // if sold today will it be profitable
            if(oldBoughtStateProfit + prices[i] - fee > oldSoldStateProfit){
                newSoldStateProfit = oldBoughtStateProfit + prices[i] - fee;
            } else{
                newSoldStateProfit = oldSoldStateProfit;
            }
            oldBoughtStateProfit = newBoughtStateProfit;
            oldSoldStateProfit = newSoldStateProfit;
        }
        return oldSoldStateProfit;
    
    }
}


//    Best Time to Buy and Sell Stock with Cooldown   (LC-309)  


class Solution {
    public int maxProfit(int[] prices) {
        int oldBoughtStateProfit = -prices[0];
        int oldSoldStateProfit =0;
        int oldCooledStateProfit =0;
        for(int i=1;i<prices.length;i++){
            int newBoughtStateProfit =0;
            int newSoldStateProfit =0;
            int newCooledStateProfit =0;
            
            // if bought today will the profit is maximized
            
            // buying is taking place after a cooldown
            if(oldCooledStateProfit - prices[i] > oldBoughtStateProfit){
                newBoughtStateProfit = oldCooledStateProfit - prices[i];
            }else{
                newBoughtStateProfit = oldBoughtStateProfit;
            }
            
            
            // if sold today will the profit is maximized
            
            // selling can take place ater a buy
            
            if(oldBoughtStateProfit + prices[i] > oldSoldStateProfit){
                newSoldStateProfit = oldBoughtStateProfit + prices[i];
            }else{
                newSoldStateProfit = oldSoldStateProfit;
            }
            
            // if cooled today will the profit is maximized
            
            // cool down can take place after a  sell
            
            if(oldSoldStateProfit > oldCooledStateProfit){
                newCooledStateProfit = oldSoldStateProfit;
            }else{
                newCooledStateProfit = oldCooledStateProfit;
            }
            
         oldBoughtStateProfit =  newBoughtStateProfit;
         oldSoldStateProfit =    newSoldStateProfit;
         oldCooledStateProfit =  newCooledStateProfit;
        }
        return oldSoldStateProfit;
    }
}



//     Best Time to Buy and Sell Stock III    (lc-123)


class Solution {
    public int maxProfit(int[] prices) {
        
        int profitIfSoldToday = 0;
        int leastSoFar = prices[0];
        int[] maxProfitIfSoldUptoToday = new int[prices.length];
        
        // calculating maximum profit if sold upto today
        for(int i=1;i<prices.length;i++){
            if(prices[i] < leastSoFar){
                leastSoFar = prices[i];
            }
            profitIfSoldToday = prices[i] - leastSoFar;
            if( profitIfSoldToday  > maxProfitIfSoldUptoToday[i-1]){
                maxProfitIfSoldUptoToday[i] = profitIfSoldToday;
            }else{
                maxProfitIfSoldUptoToday[i] = maxProfitIfSoldUptoToday[i-1];
            }
        }
        
        // calculating maximum profit if bought uptill today i.e today or after today
        int profitIfBoughtToday = 0;
        int maxSoFar = prices[prices.length-1];
        
        int[]maxProfitIfBoughtTodayOrAfterToday = new int[prices.length];
        for(int i=prices.length-2;i>=0;i--){
            if(prices[i] > maxSoFar){
                maxSoFar = prices[i];
            }
            profitIfBoughtToday = maxSoFar - prices[i];
            if(profitIfBoughtToday > maxProfitIfBoughtTodayOrAfterToday[i+1]){
                maxProfitIfBoughtTodayOrAfterToday[i] =  profitIfBoughtToday;
            }else{
                 maxProfitIfBoughtTodayOrAfterToday[i] =  maxProfitIfBoughtTodayOrAfterToday[i+1];
            }
        }
        
         // the maximum sum of profit if sold upto today and profit if bought today or after today is              // the desired answer
        int overallProfit =0;
        for(int i=0;i<prices.length;i++){
            if(maxProfitIfSoldUptoToday[i] + maxProfitIfBoughtTodayOrAfterToday[i] > overallProfit){
                overallProfit = maxProfitIfSoldUptoToday[i] + maxProfitIfBoughtTodayOrAfterToday[i];
            }
        }
        return overallProfit;
    }
}











// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


// ~~~~~~~~~~~~~~~~~~~~~~~~         INCLUDE EXCLUDE BASED QUESTION



// Paint Fence  (LINTCODE-514)


public class Solution {
    /**
     * @param n: non-negative integer, n posts
     * @param k: non-negative integer, k colors
     * @return: an integer, the total number of ways
     */
    public int numWays(int n, int k) {
        // write your code here
        if(n == 1){
            return k;
        }
        int same = k;
        int different = k * (k-1);
        int total = same + different;
        for(int i=3;i<=n;i++){
            same = different;
            different = total*(k-1);
            total = same + different;
        }
        return total;
    }
}



//  Paint House     (LINTCODE - 515)    


public class Solution {
    /**
     * @param costs: n x 3 cost matrix
     * @return: An integer, the minimum cost to paint all houses
     */
    public int minCost(int[][] costs) {
        // write your code here
        int n = costs.length;
        if(n == 0){
            return 0;
        }
        long[][] dp = new long[costs.length][costs[0].length];
        dp[0][0] = costs[0][0]; // Minimum cost of painting first house with red color 
        dp[0][1] = costs[0][1]; // blue
        dp[0][2] = costs[0][2]; // green

        for(int i=1;i<costs.length;i++){
            dp[i][0] = costs[i][0] + Math.min(dp[i-1][1],dp[i-1][2]);
            dp[i][1] = costs[i][1] + Math.min(dp[i-1][0],dp[i-1][2]);
            dp[i][2] = costs[i][2] + Math.min(dp[i-1][0],dp[i-1][1]);
        }
        long minCost = Math.min(dp[n-1][0],Math.min(dp[n-1][1],dp[n-1][2]));
        return (int)minCost;
    }
}








//      Consecutive 1's not allowed     (GFG)


class Solution {
    long countStrings(int n) {
        // code here
        long ending0 = 1;
        long ending1 = 1;
        
        for(int i=2;i<=n;i++){
            long new0 = (ending1 + ending0) % 1000000007;
            long new1 = ending0 % 1000000007;
            ending0 = new0;
            ending1 = new1;
        }
        return (ending0 + ending1) % 1000000007;
    }
}



//  Minimum Path Sum        (LC-64)


class Solution {
    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        for(int i=n-1;i>=0;i--){
            for(int j= m-1;j>=0;j--){
                if(i == n-1 && j == m-1){
                    dp[i][j] = grid[i][j];
                }else if(i == n-1){
                    dp[i][j] = dp[i][j+1] + grid[i][j];
                }else if(j == m-1){
                    dp[i][j] = dp[i+1][j] + grid[i][j];
                }else{
                    dp[i][j] = Math.min(dp[i][j+1],dp[i+1][j]) + grid[i][j];
                }
            }
        }
        return dp[0][0];
    }
}




