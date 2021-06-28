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



/*      Majority Element    (LC-169)        */


class Solution {
    public int majorityElement(int[] nums) {
        int value = validCandidate(nums);
        int count =0;
        for(int i=0;i<nums.length;i++){
            if(value == nums[i]){
                count++;
            }
        }
        // if(count > nums.length/2){
        //     return value;
        // }
        return value;
    }
    private int validCandidate(int[]nums){
        int val = nums[0];
        int count =1;
        for(int i=1;i<nums.length;i++){
            if(val == nums[i]){
                // same value
                count++;
            }else{
                // distinct elements, mapping of them
                count--;
            }
            if(count == 0){
                val = nums[i];
                count =1;
            }
        }
        return val;
    }
}


/*      Majority Element II     (LC-229)        */


class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int val1 =nums[0];
        int count1 =1;
        int val2= nums[0];
        int count2 =0;
        int i=1;
        while(i < nums.length){
            if(nums[i] == val1){
                count1++;
            }else if(nums[i] == val2){
                count2++;
            }else{
                if(count1 == 0){
                    val1 = nums[i];
                    count1 =1;
                }else if(count2 ==0){
                    val2 = nums[i];
                    count2 =1;
                }else{
                    count1--;
                    count2--;
                }
            }
            i++;
        }
        List<Integer> res = new ArrayList<>();
        if(freqGreaterThanNb3(nums,val1) == true){
            res.add(val1);
        }
        if(val1 != val2 && freqGreaterThanNb3(nums,val2) == true){
            res.add(val2);
        }
        return res;
    }
    private boolean freqGreaterThanNb3(int[]nums,int val){
        int count =0;
        for(int i=0;i<nums.length;i++){
            if(val == nums[i]){
                count++;
            }
        }
        if(count > nums.length/3){
            return true;
        }
        return false;
    }
}


/*      Count More than n/k Occurences  OR MAJORITY ELEMENT GENERAL     (GFG)       */


class Solution 
{
    //Function to find all elements in array that appear more than n/k times.
    public int countOccurence(int[] arr, int n, int k) 
    {
        // your code here,return the answer
        int count =0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<n;i++){
            map.put(arr[i],map.getOrDefault(arr[i],0)+1);
        }
        for(int key : map.keySet()){
            if(map.get(key) > n/k){
                count++;
            }
        }
        return count;
    }
}


/*      Product of Array Except Self    (LC-238)        */


class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] rightProduct = new int[nums.length];
        int product = 1;
        for(int i=nums.length-1;i>=0;i--){
            product *= nums[i];
            rightProduct[i] = product;
        }
        int leftProduct =1;
        int[] res = new int[nums.length];
        for(int i=0;i<nums.length-1;i++){
            res[i] = rightProduct[i+1]*leftProduct;
            leftProduct *= nums[i];
        }
        res[nums.length-1] = leftProduct;
        return res;
    }
}

/*      Max Chunks To Make Sorted   (LC-769)        */


class Solution {
    public int maxChunksToSorted(int[] arr) {
        int max =0;
        int count =0;
        for(int i=0;i<arr.length;i++){
            max = Math.max(max,arr[i]);
            if(i == max){
                count++;
            }
        }
        return count;
    }
}


/*      Max Chunks To Make Sorted II    (LC-768)        */


class Solution {
    public int maxChunksToSorted(int[] arr) {
        // step-1 generate right min
        int[] rightMin = new int[arr.length + 1];
        rightMin[arr.length] = Integer.MAX_VALUE;
        for(int i=arr.length-1;i>=0;i--){
            rightMin[i] = Math.min(rightMin[i+1],arr[i]);
        }
        
        // step-2 calculate left max and chunk count
        int leftMax = Integer.MIN_VALUE;
        int count =0;
        for(int i=0;i<arr.length;i++){
            leftMax = Math.max(leftMax,arr[i]);
            if(leftMax <= rightMin[i+1]){
                count++;
            }
        }
        return count;
    }
}


/*      Maximum Product of Three Numbers    (LC-628)        */


class Solution {
    public int maximumProduct(int[] nums) {
        int min1 = Integer.MAX_VALUE;
        int min2 = min1;
        int max1 =Integer.MIN_VALUE;
        int max2 = max1;
        int max3 = max1;
        for(int i=0;i<nums.length;i++){
            int val = nums[i];
            // max check
            
            if(val >= max1){
                max3 = max2;
                max2 = max1;
                max1 = val;
            }else if(val >= max2){
                max3 = max2;
                max2 = val;
            }else if(val >= max3){
                max3 =val;
            }
            
            // min check
            if(val <= min1){
                min2 = min1;
                min1 =val;
            }else if(val <= min2){
                min2 =val;
            }
        }
        int result = Math.max(min1*min2*max1,max1*max2*max3);
        return result;
    }
}



/*      Min Jumps With +i -i Moves  (GFG)       */


 public static int minJumps(int x) {
        // Write your code here
        int jump =1;
        int sum =0;
        while(sum < x){
            sum += jump;
            jump++;
        }
        if((sum-x) % 2 == 0){
            return jump-1;
        }else if((sum + jump -x) % 2 == 0){
            return jump;
        }else{
            return jump +1;
        }
    }


/*      Sort Array By Parity    (LC-905)        */


class Solution {
    public int[] sortArrayByParity(int[] nums) {
        int i=0;
        int j =0;
        while(i < nums.length){
            if(nums[i] % 2 == 0){
                swap(nums,i,j);
                i++;
                j++;
            }else{
                i++;
            }
        }
        return nums;
    }
    private void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}


/*      Transpose Matrix    (LC-867)    */

class Solution {
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] result = new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                result[i][j] = matrix[j][i];
            }
        }
        return result;
    }
}

/*      Rotate Image    (LC-48)     */


class Solution {
    public void rotate(int[][] matrix) {
        // step-1 transpose
        transpose(matrix);
        // step-2 reverse every row
        reverseRow(matrix);
    }
    private void transpose(int[][] arr){
        int n = arr.length;
        for(int i=0;i<n;i++){
            for(int j=0;j<i;j++){
                swap(arr,i,j);
            }
        }
    }
    private void swap(int[][] arr,int i,int j){
        int temp = arr[i][j];
        arr[i][j] = arr[j][i];
        arr[j][i] = temp;
    }
    private void reverseRow(int[][] arr){
        int n = arr.length;
        for(int row =0;row<n;row++){
            int left =0;
            int right = n-1;
            while(left < right){
                int temp = arr[row][left];
                arr[row][left] = arr[row][right];
                arr[row][right] = temp;
                left++;
                right--;
            }
        }
    }
}


/*      Reverse Vowels of a String  (LC-345)        */


class Solution {
    public String reverseVowels(String s) {
        char [] arr = s.toCharArray();
        int left =0;
        int right = arr.length-1;
        while(left < right){
            
            // left vowel
            while(left < right && isVowel(arr,left) == false){
                left++;
            }
            // right vowel
            while(left < right && isVowel(arr,right) == false){
                right--;
            }
            swap(arr,left,right);
            left++;
            right--;
        }
        String ans ="";
        for(char ch : arr){
            ans += ch;
        }
        return ans;
    }
    private boolean isVowel(char[]arr,int idx){
        char ch = arr[idx];
        if(ch == 'a' || ch =='e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'A' || ch =='E'||
          ch == 'I' || ch== 'O' || ch == 'U'){
            return true;
        }
        return false;
    }
    private void swap(char[]arr,int i,int j){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}


/*      Best Meeting Point  (LINTCODE-912)  */


public class Solution {
    /**
     * @param grid: a 2D grid
     * @return: the minimize travel distance
     */
    public int minTotalDistance(int[][] grid) {
        // step -1 get x coordinate -> row wise
        List<Integer> xcoordinate = new ArrayList<>();
        for(int row =0;row<grid.length;row++){
            for(int col=0;col<grid[0].length;col++){
                if(grid[row][col] ==1){
                    xcoordinate.add(row);
                }
            }
        }


        // step -2 get y coordinate -> column wise
        List<Integer> ycoordinate = new ArrayList<>();
        for(int col =0;col<grid[0].length;col++){
            for(int row =0;row<grid.length;row++){
                if(grid[row][col] == 1){
                    ycoordinate.add(col);
                }
            }
        }

        // step-3 calculate mid of x and y i.e best meeting point (median)
        int x = xcoordinate.get(xcoordinate.size()/2);
        int y = ycoordinate.get(ycoordinate.size()/2);


        // step-4 calculate distance
        int distance = 0;
        for(int xval : xcoordinate){
            distance += Math.abs(x - xval);
        }
        for(int yval : ycoordinate){
            distance += Math.abs(y - yval);
        }

        // step-5 return distance
        return distance;

    }
}

