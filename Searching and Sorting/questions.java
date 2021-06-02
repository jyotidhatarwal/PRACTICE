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




