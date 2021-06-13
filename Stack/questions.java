/*    Next Greater Element I    (LC-496)    */


class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int [] ans = new int[nums1.length];
        int[] nge = nextGreaterElementRight(nums2);
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums2.length;i++){
            map.put(nums2[i],nge[i]);
        }
        for(int i=0;i<nums1.length;i++){
            ans[i] = map.get(nums1[i]);
        }
        return ans;
    }
    
     private int[] nextGreaterElementRight(int[] nums2) {
        Stack<Integer> st = new Stack<>();
         int[] ans = new int[nums2.length];
         for(int i=nums2.length-1;i>=0;i--){
             while(st.size() > 0 && st.peek() < nums2[i]) st.pop();
             ans[i] = st.size() > 0 ? st.peek() : -1;
             st.push(nums2[i]);
         }
         return ans;
    }
}

/*    Next Greater Element II (LC-503)    */


class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int[] ans = new int[nums.length];
        Stack<Integer> st = new Stack<>();
        for(int i = nums.length-2;i>=0;i--){
            // pop push
            while(st.size() > 0 && st.peek() <= nums[i]){
                st.pop();
            }
            st.push(nums[i]);
        }
        for(int i=nums.length-1;i>=0;i--){
            // pop ans push
            while(st.size() > 0 && st.peek() <= nums[i]){
                st.pop();
            }
            ans[i] = st.size() > 0 ? st.peek() : -1;
            st.push(nums[i]);
        }
        return ans;
    }
}


/*    Validate Stack Sequences  (LC-946)    */


class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> st = new Stack<>();
        int j=0;
        for(int ele : pushed){
            st.push(ele);
            while(st.size() > 0 && st.peek() == popped[j]){
                st.pop();
                j++;
            }
        }
        boolean result = false;
        if(j == popped.length){
            result = true;
        }
        return result;
    }
}


/*    Design a Stack With Increment Operation (LC-1381)   */


class CustomStack {
        int value[];
        int increment[];
        int index;
    public CustomStack(int maxSize) {
            value = new int[maxSize];
            increment = new int[maxSize];
            index=-1;
    }
    
    public void push(int x) {
        if(index+1 == value.length){
                return;
            }
            index++;
            value[index] = x;
            increment[index] = 0;
    }
    
    public int pop() {
          if(index == -1){
                return -1;
            }
        int x = value[index];
        int inc = increment[index];
        index--;
        if(index >=0){
            increment[index] += inc;
        }
        return x+inc;
    }
    
    public void increment(int k, int val) {
         int ind = Math.min(index,k-1);
        if(ind >= 0){
            increment[ind] += val;
        }
    }
}

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack obj = new CustomStack(maxSize);
 * obj.push(x);
 * int param_2 = obj.pop();
 * obj.increment(k,val);
 */




  
