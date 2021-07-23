
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


//  Best Time to Buy and Sell Stock IV  (LC-188)

// NAIVE APPROACH OF T- O(N^3)

class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if(n == 0){
            return 0;
        }
        int[][] dp = new int[k+1][n];
        // t  --> transaction
        // d --> days
        // pd --> previous days
        for(int t = 1;t<= k ;t++){
            for(int d =1;d<n;d++){
                int max = dp[t][d-1];
                for(int pd =0;pd<d;pd++){
                    int profitTillTransactionMinusOneDay = dp[t-1][pd];
                    int profitOnTransactionDay = prices[d] - prices[pd];
                    
                    if(profitTillTransactionMinusOneDay + profitOnTransactionDay > max){
                        max = profitTillTransactionMinusOneDay + profitOnTransactionDay;
                    }
                }
                dp[t][d] = max;
            }
        }
        return dp[k][n-1];
    }
}



//   OPTIMISED APPROACH OF T-O(N^2)










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


// Paint House II	(LINTCODE - 516)


public class Solution {
    /**
     * @param costs: n x k cost matrix
     * @return: an integer, the minimum cost to paint all houses
     */
    public int minCostII(int[][] costs) {
        // write your code here
        if(costs.length == 0){
            return 0;
        }
        int[][] dp = new int[costs.length][costs[0].length];
        int least = Integer.MAX_VALUE;
        int secondLeast = Integer.MAX_VALUE;
        
        for(int j=0;j<costs[0].length;j++){
            dp[0][j] = costs[0][j];
            if(dp[0][j] < least){
                secondLeast = least;
                least = dp[0][j];
            }else if(dp[0][j] < secondLeast){
                secondLeast = dp[0][j];
            }
        }

        for(int i=1;i<costs.length;i++){
            int newLeast = Integer.MAX_VALUE;
            int newSecondLeast = Integer.MAX_VALUE;
            for(int j=0;j<costs[0].length;j++){
                if(least == dp[i-1][j]){
                    dp[i][j] = costs[i][j] + secondLeast;
                }else{
                    dp[i][j] = costs[i][j] + least;
                }

                if(dp[i][j] < newLeast){
                    newSecondLeast = newLeast;
                    newLeast = dp[i][j];
                }else if(dp[i][j] < newSecondLeast){
                    newSecondLeast = dp[i][j];
                }
            }
            least = newLeast;
            secondLeast = newSecondLeast;
        }
        return least;
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




//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@22

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


// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@




//  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~        LONGEST INCREASING SUBSEQUENCE  (LIS) BASED QUESTIONS


//  Longest Increasing Subsequence  (LC-300)

// T-O(N^2) APPROACH

class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] =1;
        int ans =1;
        for(int i=1;i<n;i++){
            int max =0;
            for(int j=0;j<i;j++){
                if(nums[i] > nums[j]){
                    if(dp[j] > max){
                        max = dp[j];
                    }
                }
            }
            dp[i] = max +1;
            if(dp[i] > ans){
                ans = dp[i];
            }
        }
        return ans;
    }
}



// T-O(NLOGN) APPROACH


class Solution {
    public int lengthOfLIS(int[] nums) {
        int[]dp = new int[nums.length];
        int ans =0;
        for(int val : nums){
            int i =0;
            int j = ans;
            while(i < j){
                int mid = i + (j-i)/2;
                if(dp[mid] < val){
                    i = mid+1;
                }else{
                    j = mid;
                }
            }
            dp[i] = val;
            if(i == ans){
                ans++;
            }
        }
        return ans;
    }
}







//  Maximum sum increasing subsequence  (GFG)       


class Solution
{
	public int maxSumIS(int arr[], int n)  
	{  
	    //code here.
	    int[]dp = new int[n];
	    int ans = 0;
	    for(int i=0;i<n;i++){
	        Integer max = null;
	        for(int j=0;j<i;j++){
	            if(arr[i] > arr[j]){
	                if(max == null){
	                    max = dp[j];
	                }else if(dp[j] > max){
	                    max = dp[j];
	                }
	            }
	        }
	        if(max == null){
	            dp[i] = arr[i];
	        }else{
	            dp[i] = max + arr[i];
	        }
	        if(dp[i] > ans){
	            ans = dp[i];
	        }
	    }
	    return ans;
	}  
}


//  Longest Bitonic subsequence     (GFG)


class Solution
{
    public int LongestBitonicSequence(int[] nums)
    {
        // Code here
        int n = nums.length;
        int[]lis = new int[n];
        for(int i=0;i<n;i++){
            int max =0;
            for(int j=0;j<i;j++){
                if(nums[i] > nums[j]){
                    if(lis[j] > max){
                        max = lis[j];
                    }
                }
            }
            lis[i] = max+1;
        }
        
        int[]lds = new int[n];
        for(int i = n-1;i>=0;i--){
            int max =0;
            for(int j= n-1;j>i;j--){
                if(nums[i] > nums[j]){
                    if(lds[j] > max){
                        max = lds[j];
                    }
                }
            }
            lds[i] = max+1;
        }
        int ans =0;
        for(int i=0;i<n;i++){
            if(lis[i] + lds[i] - 1 > ans){
                ans = lis[i] + lds[i] -1;
            }
        }
        return ans;
    }
}



//      Russian Doll Envelopes  (LC-354)



class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes,(a,b)->{
            return a[0]-b[0];
        });
        int[]dp = new int[envelopes.length];
        int ans =0;
        for(int i=0;i<envelopes.length;i++){
            int max =0;
            for(int j=0;j<i;j++){
                if(envelopes[i][1] > envelopes[j][1] && envelopes[i][0] > envelopes[j][0]){
                    if(dp[j] > max){
                        max = dp[j];
                    }
                }
            }
            dp[i] = max +1;
            if(dp[i] > ans){
                ans = dp[i];
            }
        }
        return ans;
    }
}











// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!



//	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~		CUT BASED QUESTIONS	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~




//	Palindrome Partitioning II	(LC-132)



// NAIVE APPROACH	T-O(N^3)


class Solution {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for(int gap =0;gap<n;gap++){
            for(int i=0,j=gap;j<dp.length;i++,j++){
                if(gap == 0){
                    dp[i][j] = true;
                }else if(gap == 1){
                    if(s.charAt(i) == s.charAt(j)){
                        dp[i][j] = true;
                    }else{
                        dp[i][j] = false;
                    }
                }else{
                    if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1] == true){
                        dp[i][j] = true;
                    }else{
                        dp[i][j] = false;
                    }   
                }
            }
        }
        
        int[][] DP = new int[n][n];
        for(int gap =0;gap<n;gap++){
            for(int i=0,j=gap;j<DP.length;i++,j++){
                if(gap == 0){
                    DP[i][j] = 0;
                }else if(gap == 1){
                    if(s.charAt(i) == s.charAt(j)){
                        DP[i][j] =0;
                    }else{
                        DP[i][j] =1;
                    }
                }else{
                    if(dp[i][j]){ // already palindrome
                        DP[i][j] =0;
                    }else{
                        int min = Integer.MAX_VALUE;
                        for(int k=i;k<j;k++){
                            int left = DP[i][k];
                            int right = DP[k+1][j];
                            if(left + right +1 < min){
                                min = left + right +1;
                            }
                        }
                        DP[i][j] = min;
                    }
                }
            }
        }
        return DP[0][n-1];
    }
}
