                   /*  Marks of PCM  (GFG) */

class Solution
{   public class Marks implements Comparable<Marks>{
        int phy;
        int chem;
        int math;
        Marks(){
            
        }
        Marks(int phy,int chem,int math){
            this.phy = phy;
            this.chem = chem;
            this.math = math;
        }
        public int compareTo(Marks other){
            if(this.phy != other.phy){
                return this.phy - other.phy;
            }else if(this.chem != other.chem){
                return other.chem - this.chem;
            }else{
                return this.math - other.math;
            }
        }
    }
    public void customSort (int phy[], int chem[], int math[], int N)
    {
        // your code here
        Marks[] arr = new Marks[N];
        for(int i=0;i<N;i++){
            arr[i] = new Marks(phy[i],chem[i],math[i]);
        }
        Arrays.sort(arr);
        for(int i=0;i<N;i++){
            phy[i] = arr[i].phy;
            chem[i] = arr[i].chem;
            math[i] = arr[i].math;
        }
    }
}



          /*  Union of Two Sorted Arrays  (GFG) */

class Solution
{
    //Function to return a list containing the union of the two arrays.
    public static ArrayList<Integer> findUnion(int arr1[], int arr2[], int n, int m)
    {
        // add your code here
        ArrayList<Integer> ans = new ArrayList<>();
        int i=0;
        int j=0;
        while(i < n && j < m){
            if(arr1[i] == arr2[j]){
                if(ans.size() > 0 && ans.get(ans.size()-1) != arr1[i]){
                    ans.add(arr1[i]);
                }else if(ans.size() == 0){
                    ans.add(arr1[i]);
                }
                i++;
                j++;
            }else if(arr1[i] < arr2[j]){
                if(ans.size() > 0 && ans.get(ans.size()-1) != arr1[i]){
                    ans.add(arr1[i]);
                }else if(ans.size() == 0){
                    ans.add(arr1[i]);
                }
                i++;
            }else{
                if(ans.size() > 0 && ans.get(ans.size()-1) != arr2[j]){
                    ans.add(arr2[j]);
                }else if(ans.size() == 0){
                    ans.add(arr2[j]);
                }
                j++;
            }
        }
        while(i < n){
            if(ans.size() > 0 && ans.get(ans.size()-1) != arr1[i]){
                ans.add(arr1[i]);
            }
            i++;
        }
        while(j < m){
            if(ans.size() > 0 && ans.get(ans.size()-1) != arr2[j]){
                ans.add(arr2[j]);
            }
            j++;
        }
        return ans;
    }
}

        /*    Search a 2D Matrix  (LC-74) */


class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
       
        int row = binarySearchRowSelect(matrix,target);
       if(row == -1) return false;
       boolean isFound = binarySearch(matrix,row,target);
       
       return isFound;
    }
    public  int binarySearchRowSelect(int[][]matrix,int target){
        int low = 0;
        int hi = matrix.length-1;
        int lastColumn = matrix[0].length-1;
        while(low <= hi){
            int mid = (low + hi) /2;
            
            if(matrix[mid][0] <= target && target <= matrix[mid][lastColumn]){
                return mid;
            }else if(matrix[mid][0] < target){
                low = mid +1;
            }else{
                hi = mid -1;
            }
        }
        return -1;
    }
    
     public  boolean binarySearch(int[][]matrix,int row,int target){
         int low = 0;
         int hi = matrix[0].length-1;
         while(low <= hi){
             int mid = (low + hi) /2;
             if(matrix[row][mid] == target){
                 return true;
             }else if(matrix[row][mid] < target){
                 low = mid +1;
             }else{
                 hi = mid-1;
             }
         }
         return false;
     }
}

      /*  Search a 2D Matrix II (LC-240)    */


class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = 0;
        int col = matrix[0].length-1;
        
        while(row < matrix.length && col >= 0){
            if(matrix[row][col] == target){
                return true;
            }else if(matrix[row][col] < target){
                row++;
            }else{
                col--;
            }
        }
        return false;
    }
}

/*     Find Pivot Index (LC-724)    */

class Solution {
    public int pivotIndex(int[] nums) {
        int sum = 0;
        for(int i=0;i<nums.length;i++){
            sum += nums[i];
        }
        int leftSum =0;
        int rightSum = sum;
        for(int i=0;i<nums.length;i++){
            rightSum -= nums[i];
            if(leftSum == rightSum){
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }
}

      /*     Find K Closest Elements  (LC-658)    */

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        ArrayList<Integer> list = new ArrayList<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i=0;i<arr.length;i++){
            if(pq.size() < k){
                pq.add(new Pair(arr[i],Math.abs(arr[i]-x)));
            }else if(pq.peek().gap > Math.abs(arr[i]-x)){
                pq.remove();
                pq.add(new Pair(arr[i],Math.abs(arr[i]-x)));
            }
        }
        while(pq.size() > 0){
            Pair rem = pq.remove();
            list.add(rem.val);
        }
        Collections.sort(list);
        return list;
    }
    public class Pair implements Comparable<Pair>{
        int val;
        int gap;
        Pair(int val,int gap){
            this.val=val;
            this.gap=gap;
        }
        public int compareTo(Pair other){
            if(this.gap == other.gap){
                return this.val - other.val;
            }else{
                return this.gap- other.gap;
            }
        }
    }
}

         //   #####################       ANOTHER APPROACH        #############


class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int low = 0;
        int hi = arr.length-1;
        int mid =0;
        while(low <= hi){
            mid = (low + hi) /2;
            if(arr[mid] == x){
                break;
            }else if(arr[mid] < x){
                low = mid+1;
            }else{
                hi = mid-1;
            }
        }
        int left = mid-1;
        int right = mid;
        List<Integer> list = new ArrayList<>();
        while(left >= 0 && right <= arr.length-1 && k> 0){
          if(Math.abs(arr[left]-x)  <=  Math.abs(arr[right] - x) ){
                list.add(arr[left]);
                left--;
            }else{
                list.add(arr[right]);
                right++;
            }
            k--;
        }
        while(k > 0 && left >= 0){
            list.add(arr[left]);
            left--;
            k--;
        }
        while(k > 0 && right <= arr.length-1){
            list.add(arr[right]);
            right++;
            k--;
        }
        Collections.sort(list);
        return list;
    }
}


      /*      Find Pair Given Difference  (GFG)   */


class Solution
{
    public boolean findPair(int arr[], int size, int target)
    {
        //code here.
        Arrays.sort(arr);
        int i=0;
        int j =1;
       while(i < arr.length-1 && j < arr.length){
            if(arr[j] - arr[i] == target){
                return true;
            }else if(arr[j] - arr[i] < target){
                j++;
            }else{
                i++;
            }
        }
        return false;
    }
}

      /*    Roof Top  (GFG)   */

class Solution
{
    //Function to find maximum number of consecutive steps 
    //to gain an increase in altitude with each step.
    static int maxStep(int A[], int N)
    {
        // Your code here
        int maxStep =0;
        int count =0;
        for(int i=0;i<N-1;i++){
            if(A[i] < A[i+1]){
                count++;
            }else{
                if(count > maxStep){
                    maxStep = count;
                }
             count =0;
        }
        }
        if(count > maxStep){
            maxStep = count;
        }
        return maxStep;
    }
    
}




