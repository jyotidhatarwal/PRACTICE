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




