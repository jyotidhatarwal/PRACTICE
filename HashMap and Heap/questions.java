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
