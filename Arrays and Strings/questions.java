/*   Long Pressed Name(LC-925)    */

class Solution {
    public boolean isLongPressedName(String name, String typed) {
        if(name.length() > typed.length()){
            return false;
        }
        int i=0;
        int j=0;
        while(i < name.length() && j < typed.length()){
            if(name.charAt(i) == typed.charAt(j)){
                i++;
                j++;
            }else if(i > 0 && name.charAt(i-1) == typed.charAt(j)){
                j++;
            }else{
                return false;
            }
        }
        while(j < typed.length()){
            if(name.charAt(i-1) != typed.charAt(j)){
                return false;
            }
            j++;
        }
        if(i < name.length()){
            return false;
        }
        return true;
    }
}


/*    Container With Most Water (LC-11)   */


class Solution {
    public int maxArea(int[] height) {
        int i=0;
        int j = height.length-1;
        int water =0;
        while(i < j){
            int width = j-i;
            int heights = Math.min(height[i],height[j]);
            
            water = Math.max(water,heights*width);
            
            if(height[i] < height[j]){
                i++;
            }else{
                j--;
            }
        }
        return water;
    }
}


/*  Squares of a Sorted Array (LC-977)  */

class Solution {
    public int[] sortedSquares(int[] nums) {
        int[]res = new int[nums.length];
        int i=0;
        int j = nums.length-1;
        int idx = res.length-1;
        while(i <= j){
            int val1 = nums[i]*nums[i];
            int val2 = nums[j]*nums[j];
            if(val1 > val2){
                res[idx] = val1;
                i++;
            }else{
                res[idx] = val2;
                j--;
            }
            idx--;
        }
        return res;
    }
}


/*    RANGE ADDITION    */



 public static int[] getModifiedArray(int length, int[][] queries) {
        // write your code here
        int[] res = new int[length];
        // provide impact to res
        for(int q=0;q<queries.length;q++){
            int st = queries[q][0];
            int end = queries[q][1];
            int inc = queries[q][2];
            
            res[st] += inc;
            if(end +1 < length){
                res[end+1] -= inc;
            }
        }
        
        int sum=0;
        // prefix sum
        for(int i=0;i<length;i++){
            sum += res[i];
            res[i] = sum;
        }
        return res;
    }


