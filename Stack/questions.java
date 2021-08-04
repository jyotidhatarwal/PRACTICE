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



/*  Remove Outermost Parentheses    (LC-1021)       */


class Solution {
    public String removeOuterParentheses(String s) {
        Stack<Character> st = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            // if opening brace then first check if innermost then push to stack
            if(ch == '('){
                if(st.size() > 0){
                    sb.append(ch);
                }
                st.push(ch);
            }else{
                // if closing brace then pop from stack and then check if innermost
                st.pop();
                if(st.size() > 0){
                    sb.append(ch);
                }
            }
        }
        return sb.toString();
    }
}



/*   Minimum Add to Make Parentheses Valid  (LC-921)        */


class Solution {
    public int minAddToMakeValid(String s) {
        Stack<Character> st = new Stack<>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch == '('){
                st.push(ch);
            }else{
                if(st.size() > 0 && st.peek() == '('){
                    st.pop();
                }else{
                    st.push(ch);
                }
            }
        }
        return st.size();
    }
}



/*  Minimum Remove to Make Valid Parentheses    (LC-1249)       */


class Solution {
    public String minRemoveToMakeValid(String s) {
        char[] arr = s.toCharArray();
        Stack<Integer> st = new Stack<>();
        for(int i=0;i<arr.length;i++){
            if(arr[i] == '('){
                st.push(i);
            }else if(arr[i] == ')'){
                if(st.size() == 0){
                    arr[i] = '.';
                }else{
                    st.pop();
                }
            }
        }
        while(st.size() > 0){
            arr[st.pop()] = '.';
        }
        StringBuilder sb = new StringBuilder();
        for(char ch : arr){
            if(ch != '.'){
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}




  
