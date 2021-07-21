
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



