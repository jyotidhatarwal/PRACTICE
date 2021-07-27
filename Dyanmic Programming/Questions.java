
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






//	OPTIMISED APPROACH T-O(N^2)


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
        
       int[]DP = new int[n];
        DP[0] = 0;
        for(int j=1;j<n;j++){
            if(dp[0][j]){
                DP[j] = 0;
            }else{
                 int min = Integer.MAX_VALUE;
              for(int i=j;i>=1;i--){
                  if(dp[i][j]){
                      if(DP[i-1] < min){
                          min = DP[i-1];
                      }
                  }
              }
            DP[j] = min +1;
        }
            }
             
        return DP[n-1];
    }
}



//	MATRIX CHAIN MULTIPLICATION	(GFG)


// NAIVE APPROACH T-O(N^3)


class Solution{
    static int matrixMultiplication(int N, int arr[])
    {
        // code here
        int[][]dp = new int[N-1][N-1];
        for(int gap=0;gap<dp.length;gap++){
            for(int i=0,j=gap;j<dp.length;i++,j++){
                if(gap == 0){
                    dp[i][j] =0;
                }else if(gap == 1){
                    dp[i][j] = arr[i]*arr[j]*arr[j+1];
                }else{
                    int min = Integer.MAX_VALUE;
                    for(int k=i;k<j;k++){
                        int leftCost = dp[i][k];
                        int rightCost = dp[k+1][j];
                        int multiplicationCost = arr[i]*arr[k+1]*arr[j+1];
                        int totalCost = leftCost + rightCost + multiplicationCost;
                        if(totalCost < min){
                            min = totalCost;
                        }
                    }
                    dp[i][j] = min;
                }
            }
        }
        return dp[0][dp.length-1];
    }
}




//	Boolean Parenthesization	(LINTCODE-725)


// NAIVE APPROACH T-O(N^3)



public class Solution {
    /**
     * @param symb: the array of symbols
     * @param oper: the array of operators
     * @return: the number of ways
     */
    public int countParenth(char[] symb, char[] oper) {
        // write your code here
        int n = symb.length;
        int[][]dpt = new int[n][n];	// DP TRUE
        int[][]dpf = new int[n][n];	// DP FALSE
        for(int gap =0 ;gap<n;gap++){
            for(int i=0,j=gap;j<n;i++,j++){
                if(gap == 0){
                    if(symb[i] == 'T'){
                        dpt[i][j] = 1;
                        dpf[i][j] = 0;
                    }else{
                        dpt[i][j] =0;
                        dpf[i][j] =1;
                    }
                }else{
                    for(int k=i;k<j;k++){
                        int ltc = dpt[i][k];    // left true count
                        int rtc = dpt[k+1][j];  // right true count
                        int lfc = dpf[i][k];    // left false count
                        int rfc = dpf[k+1][j];  // right false count

                        if(oper[k] == '&'){
                            dpt[i][j] += ltc*rtc;
                            dpf[i][j] += ltc*rfc + lfc*rtc + lfc*rfc;
                        }else if(oper[k] == '|'){
                            dpt[i][j] += ltc*rtc + ltc*rfc + lfc*rtc;
                            dpf[i][j] += lfc*rfc;
                        }else{
                            // operator == ^
                            dpt[i][j] += ltc*rfc + lfc*rtc;
                            dpf[i][j] += lfc*rfc + ltc*rtc;
                        }
                    }
                }
            }
        }
        return dpt[0][n-1];
    }
}


//	Optimal binary search tree 	(GFG)


// NAIVE APPROACH - T-O(N^3)


class Solution
{
    static int optimalSearchTree(int keys[], int freq[], int n)
    {
        // code here
        int[][]dp = new int[n][n];
        for(int gap =0;gap<n;gap++){
            for(int i=0,j=gap;j<n;i++,j++){
                if(gap == 0){
                    dp[i][j] = freq[i];
                }else if(gap == 1){
                    int freq1 = freq[i];
                    int freq2 = freq[j];
                    dp[i][j] = Math.min(freq1 + 2*freq2,2*freq1 + freq2);
                }else{
                    int min = Integer.MAX_VALUE;
                    int freqSum =0;
                    for(int x=i;x<=j;x++){
                        freqSum += freq[x];
                    }
                    for(int k=i;k<=j;k++){
                        int left = k == i ? 0 : dp[i][k-1];
                        int right = k == j ? 0 : dp[k+1][j];
                        min = Math.min(min,left+right+freqSum);
                    }
                    dp[i][j] = min;
                }
            }
        }
        return dp[0][n-1];
    }
}



//	Burst Balloons	(LC-312)


// NAIVE APPROACH GIVES TLE

// NAIVE APPROACH

class Solution {
    public int maxCoins(int[] nums) {
        int[]arr = new int[nums.length+2];
        arr[0] =1;
        arr[arr.length-1] = 1;
        for(int i=0;i<nums.length;i++){
            arr[i+1] = nums[i];
        }
        return burst(arr,0,arr.length-1); // arr, start point , end point (both points are exclusive)
    }
    private int burst(int[]arr,int left,int right){
        if(left+1 == right){
            return 0;
        }
        int ans =0;
        for(int i=left+1;i<right;i++){
            int tempLeft = burst(arr,left,i);
            int tempRight = burst(arr,i,right);
            int currAns = tempLeft + tempRight + arr[left]*arr[i]*arr[right];
            ans = Math.max(ans,currAns);
        }
        return ans;
    }
}


// RECURSIVE APPROACH T-O(N^3)

//  DP APPROACH

class Solution {
    public int maxCoins(int[] nums) {
        int[]arr = new int[nums.length+2];
        arr[0] =1;
        arr[arr.length-1] = 1;
        for(int i=0;i<nums.length;i++){
            arr[i+1] = nums[i];
        }
        return burst(arr,0,arr.length-1,new int[arr.length][arr.length]); 
        // arr, start point , end point (both points are exclusive)
    }
    private int burst(int[]arr,int left,int right,int[][]dp){
        if(left+1 == right){
            return 0;
        }
        if(dp[left][right] != 0){
            return dp[left][right];
        }
        int ans =0;
        for(int i=left+1;i<right;i++){
            int tempLeft = burst(arr,left,i,dp);
            int tempRight = burst(arr,i,right,dp);
            int currAns = tempLeft + tempRight + arr[left]*arr[i]*arr[right];
            ans = Math.max(ans,currAns);
            dp[left][right] = ans;
        }
        return ans;
    }
}












//	!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!



// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~		STRING BASED QUESTIONS 	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~




//	Longest Common Subsequence	(LC-1143)

// T-O(N^2)	S-O(N^2)

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][]dp = new int[n+1][m+1];
        for(int i=dp.length-2;i>=0;i--){
            for(int j=dp[0].length-2;j>=0;j--){
                char c1 = text1.charAt(i);
                char c2 = text2.charAt(j);
                if(c1 == c2){
                    dp[i][j] = 1 + dp[i+1][j+1];
                }else{
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j+1]);
                }
            }
        }
        return dp[0][0];
    }
}



//	 Longest Palindromic Subsequence	(LC-516)

// T-O(N^2)	S-O(N^2)

class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][]dp = new int[n][n];
        for(int gap=0;gap<n;gap++){
            for(int i=0,j=gap;j<n;j++,i++){
                if(gap == 0){
                    dp[i][j] =1;
                }else if(gap == 1){
                    if(s.charAt(i) == s.charAt(j)){
                        dp[i][j] = 2;
                    }else{
                        dp[i][j] =1;
                    }
                }else{
                    if(s.charAt(i) == s.charAt(j)){
                        dp[i][j] = 2 + dp[i+1][j-1];
                    }else{
                        dp[i][j] = Math.max(dp[i][j-1],dp[i+1][j]);
                    }
                }
            }
        }
        return dp[0][n-1];
    }
}



//	Longest Palindromic Substring	(LC-5)


class Solution {
    public String longestPalindrome(String s) {
     int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String ans = "";
        for(int gap=0;gap<n;gap++){
            for(int i=0,j=gap;j<n;i++,j++){
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
                if(dp[i][j] == true){
                    ans = s.substring(i,j+1);
                }
            }
        }
        return ans;
    }
}



//	Palindromic Substrings	(LC-647)


class Solution {
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][]dp = new boolean[n][n];
        int count =0;
        for(int gap =0;gap<n;gap++){
            for(int i=0,j=gap;j<n;i++,j++){
                if(gap == 0){
                    dp[i][j] = true;
                    count++;
                }else if(gap == 1){
                    if(s.charAt(i) == s.charAt(j)){
                        dp[i][j] = true;
                        count++;
                    }else{
                        dp[i][j] = false;
                    }
                }else{
                    if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1] == true){
                        dp[i][j] = true;
                        count++;
                    }else{
                        dp[i][j] = false;
                    }
                }
            }
        }
        return count;
    }
}


//	Count Different Palindromic Subsequences	(LC-730)


class Solution {
    public int countPalindromicSubsequences(String s) {
        int n = s.length();
        int[][]dp = new int[n][n];
        int[]prev = new int[n];
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i=0;i<n;i++){
            char ch = s.charAt(i);
            if(map.containsKey(ch) == false){
                prev[i] = -1;
            }else{
                prev[i] = map.get(ch);
            }
            map.put(ch,i);
        }
        int[]next = new int[n];
        for(int i=n-1;i>=0;i--){
            char ch = s.charAt(i);
            if(map.containsKey(ch) == false){
                next[i] = -1;
            }else{
                next[i] = map.get(ch);
            }
            map.put(ch,i);
        }
        for(int gap=0;gap<n;gap++){
            for(int i=0,j=gap;j<n;i++,j++){
                if(gap == 0){
                    dp[i][j] = 1;
                }else if(gap == 1){
                    dp[i][j] = 2;
                }else{
                    char start = s.charAt(i);
                    char end = s.charAt(j);
                    if(start != end){
                        int a = dp[i][j-1]  + dp[i+1][j];
                        int b = dp[i+1][j-1];
                        dp[i][j]  = (int)((a-b)% 1000000007 + 1000000007) % 1000000007;
                    }else{
                         int Next = next[i];
                        int Prev = prev[j];
                        if(Next > Prev){
                            dp[i][j] = 2*dp[i+1][j-1];
                            dp[i][j] %= 1000000007;
                            dp[i][j] += 2;
                            
                        }else if(Next == Prev){
                            dp[i][j] = 2*dp[i+1][j-1];
                            dp[i][j] %= 1000000007;
                            dp[i][j] += 1;
                        }else if(Next < Prev){
                            long a = 2*dp[i+1][j-1];
                            long b = dp[Next+1][Prev-1];
                            dp[i][j] =  (int)(((a-b)% 1000000007 + 1000000007)  % 1000000007);
                        }
                    }
                   
                }
            }
        }
        return dp[0][n-1] % 1000000007;
    }
}



//	Count subsequences of type a^i, b^j, c^k	(GFG)


class Solution
{
    public int fun(String s)
    {
        // Write your code here
        int n = s.length();
        int a =0;
        int ab =0;
        int abc =0;
        for(int i=0;i<n;i++){
            char ch = s.charAt(i);
            if(ch == 'a'){
                a = ((2*(a % 1000000007) % 1000000007) + 1) % 1000000007 ;
            }else if(ch == 'b'){
                ab  = ((2*(ab % 1000000007) % 1000000007)+ (a % 1000000007)) % 1000000007;
            }else if(ch == 'c'){
                abc = ((2*(abc % 1000000007)% 1000000007) + (ab % 1000000007) % 1000000007);
            }
        }
        return abc % 1000000007;
    }
}


//	Longest Common Substring	(GFG)


class Solution{
    int longestCommonSubstr(String S1, String S2, int n, int m){
        // code here
        int[][]dp = new int[n+1][m+1];
        int max =0;
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                char c1 = S1.charAt(i-1);
                char c2 = S2.charAt(j-1);
                if(c1 != c2){
                    dp[i][j] =0;
                }else{
                    dp[i][j] = dp[i-1][j-1] +1;
                }
                if(dp[i][j] > max){
                    max = dp[i][j];
                }
            }
        }
        return max;
    }
}



//	Longest Repeating Subsequence	(GFG)


class Solution
{
    public int LongestRepeatingSubsequence(String str)
    {
        // code here
        int n = str.length();
        int[][]dp = new int[n+1][n+1];
        for(int i=dp.length-1;i>=0;i--){
            for(int j=dp[0].length-1;j>=0;j--){
                if(i == dp.length-1 && j == dp[0].length-1){
                    dp[i][j] =0;
                }else if(i == dp.length-1){
                    dp[i][j] =0;
                }else if(j == dp[0].length-1){
                    dp[i][j] =0;
                }else{
                    if(str.charAt(i) == str.charAt(j) && i != j){
                        dp[i][j] = dp[i+1][j+1] +1;
                    }else{
                        dp[i][j] = Math.max(dp[i+1][j],dp[i][j+1]);
                    }
                }
            }
        }
        return dp[0][0];
    }
}


//	Longest Palindrome in a String 	(GFG)


class Solution{
    static String longestPalin(String S){
        // code here
        int n = S.length();
        String ans ="";
        boolean[][]dp = new boolean[n][n];
        
        for(int gap=0;gap<n;gap++){
            for(int i=0,j=gap;j<n;i++,j++){
                if(gap == 0){
                    dp[i][j] = true;
                }else if(gap == 1){
                    if(S.charAt(i) == S.charAt(j)){
                        dp[i][j] = true;
                    }else{
                        dp[i][j] = false;
                    }
                }else{
                    if(S.charAt(i) == S.charAt(j) && dp[i+1][j-1] == true){
                        dp[i][j] = true;
                    }else{
                        dp[i][j] = false;
                    }
                }
                if(dp[i][j] == true){
                    String t=S.substring(i,j+1);
                    if(t.length()>ans.length()){
                        ans=t;
                    }
                }
            }
        }
        return ans;
    }
}



//	Count Palindromic Subsequences	(GFG)


class Solution
{
    long countPS(String str)
    {
        // Your code here
        int n  = str.length();
        long mod=1000000007;
        long[][]dp = new long[n][n];
        for(int gap =0;gap<n;gap++){
            for(int i=0,j=gap;j<n;i++,j++){
                if(gap == 0){
                    dp[i][j] = 1;
                }else if(gap == 1){
                    if(str.charAt(i) == str.charAt(j)){
                        dp[i][j] = 3;
                    }else{
                        dp[i][j] = 2;
                    }
                }else{
                    if(str.charAt(i) == str.charAt(j)){
                        dp[i][j] = dp[i][j-1] + dp[i+1][j];
                        dp[i][j] %= 1000000007;
                        dp[i][j] += 1;
                    }else{
                           long  a = (dp[i][j-1] + dp[i+1][j])%mod;
                            long  b = dp[i+1][j-1];
                            dp[i][j] = ((a - b) % mod + mod) % mod;
                    }
                }
               
            }
        }
        return dp[0][n-1] % 1000000007;
    }
}






//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


//	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~		MISCILLANIOUS	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`


//	Wildcard Matching	(LC-44)


class Solution {
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        boolean[][]dp = new boolean[m+1][n+1];
        for(int i=dp.length-1;i>=0;i--){
            for(int j = dp[0].length-1;j>=0;j--){
                if(i== dp.length-1 && j == dp[0].length-1){
                    // last cell
                    dp[i][j] = true;
                }else if(i == dp.length-1){
                    // last row
                    dp[i][j] = false;
                }else if(j == dp[0].length-1){
                    // last column
                    if(p.charAt(i) == '*'){
                        dp[i][j] = dp[i+1][j];
                    }else{
                        dp[i][j] = false;
                    }
                }else{
                    // rest of the array
                    if(p.charAt(i) == '?'){
                        dp[i][j] = dp[i+1][j+1];
                    }else if(p.charAt(i) == '*'){
                        dp[i][j] = dp[i][j+1] || dp[i+1][j];
                    }else if(p.charAt(i) == s.charAt(j)){
                        dp[i][j] = dp[i+1][j+1];
                    }else{
                        dp[i][j] = false;
                    }
                }
            }
        }
        return dp[0][0];
    }
}


//	Regular Expression Matching	(LC-10)


class Solution {
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        boolean[][]dp = new boolean[m+1][n+1];
        for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[0].length;j++){
                if(i == 0 && j == 0){
                    // first ceell
                    dp[i][j] = true;
                }else if(i == 0){
                    // first row
                    dp[i][j] = false;
                }else if(j == 0){
                    // first column
                    char patternCharacter = p.charAt(i-1);  
                    // as in dp ith character in pattern is mapped to i-1 position
                    if(patternCharacter == '*'){
                        dp[i][j] = dp[i-2][j];
                    }else{
                        dp[i][j] = false;
                    }
                }else{
                    // rest of the array
                    char patternCharacter = p.charAt(i-1);
                    char stringCharacter = s.charAt(j-1);
                    if(patternCharacter == '*'){
                        dp[i][j] = dp[i-2][j];
                        char patternSecondLastchar = p.charAt(i-2);
                        if(patternSecondLastchar == '.' || patternSecondLastchar == stringCharacter){
                            dp[i][j] = dp[i][j] || dp[i][j-1];
                        }
                    }else if(patternCharacter == '.'){
                        dp[i][j] = dp[i-1][j-1];
                    }else if(patternCharacter == stringCharacter){
                        dp[i][j] = dp[i-1][j-1];
                    }else{
                        dp[i][j] = false;
                    }
                    
                }
            }
        }
        return dp[m][n];
    }
}







