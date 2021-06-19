      /*  Kth Largest Element in an Array   (LC-215)    */

class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();  // min priority Queue
        for(int i=0;i<nums.length;i++){
            if(pq.size() < k){
                pq.add(nums[i]);
            }else if(pq.peek() < nums[i]){
                pq.remove();
                pq.add(nums[i]);
            }
        }
        return pq.peek();
    }
}

/*    Kth Largest Element in a Stream   (LC-703)    */


class KthLargest {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    int k =0;
    public KthLargest(int k, int[] nums) {
        this.k = k;
        for(int i=0;i<nums.length;i++){
            if(pq.size() < k){
                pq.add(nums[i]);
            }else if(pq.peek() < nums[i]){
                pq.remove();
                pq.add(nums[i]);
            }
        }
    }
    
    public int add(int val) {
        pq.add(val);
      if(pq.size() > this.k){
          pq.remove();
      }
        return pq.peek();
    }
}


/*     Intersection of Two Arrays   (LC-349)    */


class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
      HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums1.length;i++){
            map.put(nums1[i], map.getOrDefault(nums1[i],0)+1);
        }
        List<Integer> ans = new ArrayList<>();
        for(int i=0;i<nums2.length;i++){
            if(map.containsKey(nums2[i])){
            ans.add(nums2[i]);
            map.remove(nums2[i]);
            }
        }
        int[] res = new int[ans.size()];
        int i=0;
        for(int ele : ans){
            res[i++] = ele;
        }
        return res;
    }
}


/*  Intersection of Two Arrays II   (LC-350)    */


class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums1.length;i++){
            map.put(nums1[i],map.getOrDefault(nums1[i],0)+1);
        }
        List<Integer> ans = new ArrayList<>();
        for(int i=0;i<nums2.length;i++){
            if(map.containsKey(nums2[i])){
                ans.add(nums2[i]);
                map.put(nums2[i],map.get(nums2[i]) -1);
                if(map.get(nums2[i]) == 0){
                    map.remove(nums2[i]);
                }
            }
        }
        int[] res = new int[ans.size()];
        int i=0;
        for(int ele : ans){
            res[i++] = ele;
        }
        return res;
    }
}


/*    Longest Consecutive Sequence    (LC-128)    */


class Solution {
    public int longestConsecutive(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }
        int len =0;
        for(int ele : nums){
            if(map.containsKey(ele) == false) continue;
            map.remove(ele);
            int prev = ele-1;
            int next = ele+1;
            
            while(map.containsKey(prev)){
                map.remove(prev);
                prev--;
            }
            while(map.containsKey(next)){
                map.remove(next);
                next++;
            }
            len = Math.max(len,next-prev-1);
        }
        return len;
    }
}


/*    Top K Frequent Elements (LC-347)    */


class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }
        // min priority Queue
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) ->{
           return a[1]-b[1]; 
        }) ;
        
        for(int ele : map.keySet()){
            pq.add(new int[]{ele,map.get(ele)});
            if(pq.size() > k) pq.remove();
        }
        int[] ans = new int[k];
        int i =0;
        while(pq.size() > 0){
            int[] rem = pq.remove();
          //  int data = rem[0];
            ans[i++] = rem[0];
        }
        return ans;
    }
}

/*     Kth Smallest Element in a Sorted Matrix    (LC-378)    */


class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                 if(pq.size() < k){
                     pq.add(matrix[i][j]);
                 }else if(pq.peek() > matrix[i][j]){
                     pq.remove();
                     pq.add(matrix[i][j]);
                 }
            }
        }
        return pq.peek();
    }
}

/*    K Closest Points to Origin    (LC-973)    */

class Solution {
    public int[][] kClosest(int[][] points, int k) {
        // 0--> x coordinate
        // 1 --> y  coordinate
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
           int d1 = a[0]*a[0] + a[1]*a[1];  // x1 ^ 2 + y1 ^ 2
            int d2 = b[0]*b[0] + b[1]*b[1];    // x2 ^ 2 + y2 ^ 2;
            return d2 - d1;
        });
        
        for(int[] point : points){
            pq.add(new int[]{point[0],point[1]});
            if(pq.size() > k){
                pq.remove();
            }
        }
        int[][] ans = new int[k][];
        int i=0;
        while(pq.size() > 0){
            ans[i++] = pq.remove();
        }
        return ans;
    }
}



/*          Sub-Array sum divisible by K        (GFG)*/


class Solution
{
    long subCount(long arr[] ,int n,int k)
    {
        long[] rem = new long[k];
        Arrays.fill(rem,0);
        int sum = 0;
        for(int i=0;i<n;i++){
            sum += arr[i];
            rem[((sum % k) + k) % k]++;
        }
        int ans =0;
        for(int i=0;i<k;i++){
            if(rem[i] > 1){
                ans += (rem[i] * (rem[i] -1 )) /2;
            }
        }
        ans += rem[0];
        return ans;
        
    }
}


/*          Minimum Number of Refueling Stops         (LC-871)    */


class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        if(target == startFuel){
            return 0;
        }
        // max priority Queue
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int ans =0;
        int currentMaxCoordinate = startFuel;
        int idx = 0;
        while(true){
            while( idx < stations.length && stations[idx][0] <= currentMaxCoordinate){
                pq.add(stations[idx][1]);
                idx++;
            }
            if(currentMaxCoordinate >= target) return ans;
            if(pq.size() == 0) return -1;
            currentMaxCoordinate += pq.remove();
            ans++;
        }
     
    }
}


/*		SAME DIFFERENCES		(CODEFORCES)		*/


import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args) throws Exception{
        Scanner scn = new Scanner(System.in);
        int t = scn.nextInt();
       
        while(t-- > 0){
            int n = scn.nextInt();
            int[] arr = new int[n];
            for(int i=0;i<n;i++){
                arr[i] = scn.nextInt();
            }
            HashMap<Integer,Integer> map = new HashMap<>();
             long count =0;
            for(int i=0;i<n;i++){
              if(map.containsKey(arr[i]-i)){
                    count += map.get(arr[i]-i);
            }
             map.put(arr[i]-i,map.getOrDefault(arr[i]-i,0)+1);
            }
             System.out.println(count);
        }
       
    }
}


/*        Potions (Hard Version)    (CODEFORCES )                                            */



import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args) throws Exception{
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        long [] arr  = new long [n];
        for(int i=0;i<n;i++){
            arr[i] = scn.nextLong();
        }
        PriorityQueue<Long> pq = new PriorityQueue<>();
        long positive =0;
        long negative =0;
        long count =0;
        for(int i=0;i<n;i++){
            if(arr[i] >= 0){
                positive += arr[i];
                count++;
            }else{
                if(positive >= (negative +Math.abs(arr[i]))){
                    negative += Math.abs(arr[i]);
                     pq.add(arr[i]);
                }else{
                    if(pq.size() == 0){
                        continue;
                    }
                    long temp = Math.abs(pq.peek());
                    if(Math.abs(arr[i]) < temp){
                        pq.remove();
                        pq.add(arr[i]);
                        negative = negative - temp + Math.abs(arr[i]);
                    }
                }
               
            }
        }
        System.out.println(count + pq.size());
    }
}



/*                X of a Kind in a Deck of Cards      (LC-914)          */


class Solution {
    public boolean hasGroupsSizeX(int[] deck) {
        Map<Integer, Integer> count = new HashMap<>();
        int res = 0;
        for (int i : deck) count.put(i, count.getOrDefault(i, 0) + 1);
        for (int i : count.values()) res = gcd(i, res);
        if(res >=2) return true;
        return false;
    }

    public int gcd(int a, int b) {
        return b > 0 ? gcd(b, a % b) : a;
    
    }
}



/*          Check Arithmetic Progression  (GFG)       */


class Solution
{
    boolean checkIsAP(int arr[] ,int n)
    {
        // code here
        Arrays.sort(arr);
        int d1;
        int d2;
        for(int i=0;i<n-2;i++){
            d1 = arr[i+1] - arr[i];
            d2 = arr[i+2] - arr[i+1];
            if(d1 != d2) return false;
        }
return true;
    
    }
}


/*           Rabbits in Forest      (LC-781)          */


class Solution {
    public int numRabbits(int[] answers) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int val : answers){
            map.put(val,map.getOrDefault(val,0)+1);
        }
        int numberOfRabbitsInForest =0;
        for(int key : map.keySet()){
            int groupSize = key +1;
            int rabbitsReported = map.get(key);
            int numberOfGroups = (int) Math.ceil((rabbitsReported*1.0)/groupSize*1.0);
            numberOfRabbitsInForest += numberOfGroups * groupSize;
        }
        return numberOfRabbitsInForest;
    }
}



/*                Array of Doubled Pairs  (LC-954)          */


class Solution {
    public boolean canReorderDoubled(int[] arr) {
        if(arr.length == 0) return true;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int val : arr){
            map.put(val,map.getOrDefault(val,0)+1);
        }
        Integer[] A = new Integer[arr.length];
        for(int i=0;i<arr.length;i++){
            A[i] = arr[i];
        }
        Arrays.sort(A,(a,b) ->{
           return Math.abs(a) - Math.abs(b); 
        });
        for(int val : A){
            if(map.get(val) == 0){
                continue;
            }
            if(map.getOrDefault(2*val,0) <= 0){
                return false;
            }
            map.put(val,map.get(val)-1);
            map.put(2*val,map.get(2*val)-1);
        }
        return true;
    }
}


/*          Longest Consecutive Sequence        (LC-128)          */


class Solution {
    public int longestConsecutive(int[] nums) {
        int max =0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int n : nums){
            if(map.containsKey(n) == false){
                int startPoint = n;
                int endPoint = n;
            
            if(map.containsKey(n-1) == true){
                startPoint = n - map.get(n-1);
            }
            if (map.containsKey(n+1) == true){
                endPoint = n + map.get(n+1);
            }
            int newLength = endPoint - startPoint +1;
            map.put(startPoint,newLength);
            map.put(endPoint,newLength);
            if(startPoint != n && endPoint != n){
                map.put(n,1);
            }
            max = Math.max(max,newLength);
        }
        }
        return max;
    }
}


/*          Bulb Switcher     (LC-319)          */


class Solution {
    public int bulbSwitch(int n) {
        int bulb =0;
        if(n == 0){
            return 0;
        }
        int i=1;
        while(i*i <= n){
            bulb++;
            i++;
        }
        return bulb;
    }
}


/*                Isomorphic Strings            (LC-205)          */


class Solution {
    public boolean isIsomorphic(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        // this is used to map character of s to t
        HashMap<Character,Character> map1 = new HashMap<>();
        
        // this is used to mark character of t i.e are they used or not
        HashMap<Character,Boolean> map2 = new HashMap<>();
        
        for(int i=0;i<s.length();i++){
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if(map1.containsKey(c1) == true){
                if(map1.get(c1) != c2){
                    return false;
                }
            }else{
                if(map2.containsKey(c2) == true){
                    return false;
                }else{
                    map1.put(c1,c2);
                    map2.put(c2,true);
                }
            }
        }
        return true;
    }
}


/*          Trapping Rain Water     (LC-42)           */


// T-O(N)         S-O(N)


class Solution {
    public int trap(int[] height) {
        int n = height.length;
        if(n == 0){
            return 0;
        }
        int leftMax[] = new int[n];
        int rightMax[] = new int[n];
        leftMax[0] = height[0];
        for(int i=1;i<n;i++){
            leftMax[i] = Math.max(leftMax[i-1],height[i]);
        }
        rightMax[n-1] = height[n-1];
        for(int i= n-2;i>=0;i--){
            rightMax[i] = Math.max(height[i],rightMax[i+1]);
        }
        int ans =0;
        for(int i=1;i<n-1;i++){
            ans += Math.min(leftMax[i],rightMax[i]) - height[i];
        }
        return ans;
    }
}


//    T-O(N)      S-O(1)


class Solution {
    public int trap(int[] height) {
        int n = height.length;
        if(n == 0){
            return 0;
        }
        int leftMax = 0;
        int rightMax = 0;
        
        int i=0;
        int j = n-1;
        int ans =0;
        while(i < j){
            leftMax = Math.max(leftMax,height[i]);
            rightMax = Math.max(rightMax,height[j]);
            
            if(leftMax <= rightMax){
                ans += leftMax - height[i];
                i++;
            }else{
                ans += rightMax - height[j];
                j--;
            }
        }
        return ans;
    }
    
}



/*          Trapping Rain Water II  (LC-407)          */


class Solution {
    
    int[][] dirs = {{1,0},{0,1},{-1,0},{0,-1}};
    
    class Pair implements Comparable<Pair>{
        int row;
        int col;
        int height;
        Pair(int row,int col,int height){
            this.row = row;
            this.col = col;
            this.height = height;
        }
        public int compareTo(Pair other){
            return this.height - other.height;
        }
    }
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;
        if(m == 0 || n == 0){
            return 0;
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[m][n];
        
        for(int i=0;i<m;i++){
            visited[i][0] = true;
            visited[i][n-1] = true;
            pq.add(new Pair(i,0,heightMap[i][0]));
            pq.add(new Pair(i,n-1,heightMap[i][n-1]));
        }
        for(int i=0;i<n;i++){
            visited[0][i] = true;
            visited[m-1][i] = true;
            pq.add(new Pair(0,i,heightMap[0][i]));
            pq.add(new Pair(m-1,i,heightMap[m-1][i]));
        }
        
        int ans =0;
        
        while(pq.size() > 0){
            Pair rem = pq.remove();
            int row = rem.row;
            int col = rem.col;
            int height = rem.height;
            
            for(int[] dir : dirs){
                int r = row + dir[0];
                int c = col + dir[1];
                
                if(r > 0 && r < m-1 && c > 0 && c < n-1 && visited[r][c] == false){
                    visited[r][c] = true;
                    ans += Math.max(0,height-heightMap[r][c]);
                    pq.add(new Pair(r,c,Math.max(height,heightMap[r][c])));
                }
            }
        }
            
            
        return ans;
    }
}


/*          Pairs which are Divisible by K            (GFG)       */


class Solution{
    
    public static int count4Divisibiles(int arr[], int n ) 
    { 
        // Complete the function
        int ans =0;
        int k=4;
        int[] remainder = new int[n];
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<n;i++){
            remainder[i] = arr[i] % k;
           
            if(remainder[i] < 0){
                remainder[i] += k;
            }
            if(remainder[i] == 0){
                if(map.containsKey(0)){
                    ans += map.get(0);
                }
            }else{
                if(map.containsKey(k-remainder[i])){
                    ans += map.get(k-remainder[i]);
                }
            }
            map.put(remainder[i],map.getOrDefault(remainder[i],0)+1);
        }
        return ans;
    }
    
}



/*          Length of the largest subarray with contiguous elements     (DISTINCT ELEMENTS ONLY)            (GFG)       */



private  static int LengthOfLargestSubarrayWithContiguousElements(int[] arr){
	    // Only distinct elements are allowed
	    int max;
	    int min;
	    int overallMax = 1;
	    int n = arr.length;
	    for(int i=0;i<n-1;i++){
	       max = arr[i];
	       min = arr[i];
	        for(int j=i+1;j<n;j++){
	            
	            if(arr[j] > max){
	                max = arr[j];
	            }
	            if(arr[j] < min){
	                min = arr[j];
	            }
	            if((j-i+1) == (max-min +1)){
	                overallMax = Math.max((j-i+1),overallMax);
	            }
	            
	        }
	    }
	    return  overallMax;
	}


/*          Length of the largest subarray with contiguous elements     (DUPLICATES ELEMENTS ARE ALLOWED)   (GFG)       */


private static int LengthOfLargestSubarrayWithContiguousElements(int[] arr){
	    // duplicates elements are also allowed
	    int n = arr.length;
	    int max;
	    int min;
	    HashSet<Integer> visited = new HashSet<>();
	    int overallMax = 1;
	    for(int i=0;i<n-1;i++){
	        max = arr[i];
	        min = arr[i];
	        visited.add(arr[i]);
	        for(int j= i+1;j<n;j++){
	            if(arr[j] < min){
	                min = arr[j];
	            }
	            if(arr[j] > max){
	                max = arr[j];
	            }
	            if(visited.contains(arr[j]) == true){
	                break;
	            }else{
	                visited.add(arr[j]);
	                if((j-i+1) == (max-min +1)){
	                    overallMax = Math.max(overallMax,j-i+1);
	                }
	            }
	        }
	    }
	    return overallMax;
	}


/*		Check if frequencies can be equal 	(GFG)		*/


class Solution {
    boolean sameFreq(String s) {
        // code here
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
         int max=Collections.max(map.values());
         int min= Collections.min(map.values());
         int maxc=0;
         int minc=0;
        for(int val:map.values()){
            
           if(val==max){
               maxc++;
           }
           if(val==min){
               minc++;
           }
        }
        
       if(max==min){
           return true;
       }else if(maxc==map.size()-1 && minc==1 && min==1){
           return true;
       }else if(maxc==1 && minc==map.size()-1 && max-min==1){
           return true;
       }else return false;
        
    }
}


/*		Tricky Sorting Cost	######	OR	LONGEST COMMON SUBSEQUENCE		*/



class Solution{
    static int sortingCost(int N, int arr[]){
        // code here
        int ans = longestCommonSubsequence(N,arr);
        int res = N -ans;
        return res;
        
    }
    static int longestCommonSubsequence(int N,int arr[]){
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int n: arr){
            if(map.containsKey(n) == false){
                if(map.containsKey(n-1) == true){
                    map.put(n,map.get(n-1)+1);
                }else{
                    map.put(n,1);
                }
            }
        }
        int max  = Collections.max(map.values());
        return max;
    }
}



/*		Brick Wall	(LC-554)		*/



class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        int noOfRows = wall.size();
       int maxEmptySpace =0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(List<Integer> row : wall){
            int prefixSum =0;
            for(int j=0;j<row.size()-1;j++){
                prefixSum += row.get(j);
                map.put(prefixSum,map.getOrDefault(prefixSum,0)+1);
               maxEmptySpace = Math.max(maxEmptySpace,map.get(prefixSum));
            }
        }
        int minBricksToCross = noOfRows - maxEmptySpace;
        return  minBricksToCross;
    }
}






