/*
BFS OF A GRAPH --> GFG
*/

class Solution
{
    //Function to return Breadth First Traversal of given graph.
    public ArrayList<Integer> bfsOfGraph(int V,ArrayList<ArrayList<Integer>> adj)
    {
        // Code 
        ArrayList<Integer> ans = new ArrayList<>();
        LinkedList<Integer> que = new LinkedList<>();
        boolean[] visited = new boolean[V];
        que.add(0);
        visited[0] = true;
        while(que.size() > 0){
            int rem = que.removeFirst();
            ans.add(rem);
            for(int vertex : adj.get(rem)){
                if(visited[vertex] == false){
                    que.add(vertex);
                    visited[vertex] = true;
                }
            }
        }
        return ans;
    
    }
}

/*
IS GRAPH BIPARTITE --> LEETCODE 785
*/

class Solution {
    // 0 -- > not colored
    // 1 --> blue
    // -1 --> red
    
    /* USING BFS */
    // T-O(V+E)
    // S- O(V)
    public boolean isBipartite(int[][] graph) {
        int[] color = new int[graph.length];
        for(int i=0;i<graph.length;i++){
            if(color[i] == 0){
                Queue<Integer> que = new LinkedList<>();
                que.add(i);
                color[i] = 1;
                while(que.size() > 0){
                    Integer node = que.poll();
                    for(int n:graph[node]){
                        if(color[n] == color[node]) return false;
                        else if(color[n] == 0){
                            que.add(n);
                            color[n] = -color[node];
                        }
                    }
                }
            }
        }
        return true;
    }
}


/*
IS GRAPH BIPARTITE --> GFG
*/

class Solution
{
    public boolean isBipartite(int V, ArrayList<ArrayList<Integer>>adj)
    {
        // Code here
   // 0 --> not colored
   // 1 --> blue
   // -1 --> red
        int[] colorArray = new int[V];
        for(int i=0;i<V;i++){
            if(colorArray[i] == 0){
             Queue<Integer> que = new LinkedList<>();
        que.add(i);
       colorArray[i] = 1;
        while(que.size() > 0){
            Integer rem = que.poll();
            for(int v: adj.get(rem)){
                if(colorArray[v] == colorArray[rem]) return false;
                else if(colorArray[v] == 0){
                    que.add(v);
                    colorArray[v] = -colorArray[rem];
                }
              
                
            }
        }  
            }
        }
        return true;
       
    }
      
}



/*       DFS OF A GRAPH (GFG)       */

class Solution
{
    //Function to return a list containing the DFS traversal of the graph.
    public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj)
    {
        // Code here
       
        ArrayList<Integer> ans = new ArrayList<>();
        boolean[] visited = new boolean[V];
        for(int i=0;i<V;i++){
            if(visited[i] == false){
                dfs(adj,visited,i,ans);
            }
        }
        return ans;
    }
    private void dfs(ArrayList<ArrayList<Integer>> graph,boolean[]visited,int curr,ArrayList<Integer> ans){
    
        visited[curr] = true;
        ans.add(curr);
        for(int nbrs:graph.get(curr)){
            if(visited[nbrs] == false){
                dfs(graph,visited,nbrs,ans);
            }
        }
       
    }
    
 /* MOTHER VERTEX (GFG) */
    class Solution
{
    //Function to find a Mother Vertex in the Graph.
    int count;
    public int findMotherVertex(int V, ArrayList<ArrayList<Integer>>adj)
    {
        // Code here
        Stack<Integer> st = new Stack<>();
        boolean[] visited = new boolean[V];
		for(int i=0;i<V;i++){
		    if(visited[i] == false){
		        dfs(adj,visited,i,st);
		    }
		}
		int ans = st.pop();
		visited = new boolean[V];
		count=0;
		dfs(adj,visited,ans);
	    if(count != V){
	        return -1;
	    }
	    return ans;
		
    }
    	private void dfs(ArrayList<ArrayList<Integer>> graph,boolean[] visited,int curr,Stack<Integer> st){
       	    visited[curr] = true;
       	    for(int nbrs:graph.get(curr)){
       	        if(visited[nbrs] == false){
       	            dfs(graph,visited,nbrs,st);
       	        }
       	    }
       	    st.push(curr);
       	}
       	
    private void dfs(ArrayList<ArrayList<Integer>> graph,boolean[]visited,int curr){
       	    visited[curr] = true;
       	    count++;
       	    for(int nbrs:graph.get(curr)){
                if(visited[nbrs] == false){
                    dfs(graph,visited,nbrs);
                }       	        
       	    }
       	}
}
    
	/*STRONGLY CONNECTED COMPONENTS ( KOSARAJU ALGORITHM)  (GFG)	*/
	
class Solution
{
    //Function to find number of strongly connected components in the graph.
    public int kosaraju(int V, ArrayList<ArrayList<Integer>> adj)
    {
        //code here
        // step-1
        Stack<Integer> st = new Stack<>();
        boolean[] visited = new boolean[V];
        for(int i=0;i<V;i++){
            if(visited[i] == false){
                dfs(adj,visited,i,st);
            }
        }
        // step-2
        ArrayList<ArrayList<Integer>> newGraph = transpose(adj,V);
        
        //step-3
        visited = new boolean[V];
        int count =0;
        while(st.size() >0){
            int curr = st.pop();
                if(visited[curr] == false){
                    dfs(newGraph,visited,curr);
                    count++;
                }
        }
        return count;
    }
    private void dfs(ArrayList<ArrayList<Integer>> graph,boolean[] visited,int curr,Stack<Integer> st){
        visited[curr] = true;
        for(int nbrs: graph.get(curr)){
            if(visited[nbrs] == false){
                dfs(graph,visited,nbrs,st);
            }
        }
        st.push(curr);
    }
    private void dfs(ArrayList<ArrayList<Integer>> graph,boolean[]visited,int curr){
        visited[curr] = true;
        for(int nbrs:graph.get(curr)){
            if(visited[nbrs] == false){
                dfs(graph,visited,nbrs);
            }
        }
    }
    
    private ArrayList<ArrayList<Integer>> transpose(ArrayList<ArrayList<Integer>> graph,int N){
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for(int i=0;i<N;i++){
            result.add(new ArrayList<>());
        }
        
        for(int i=0;i<N;i++){
            for(int nbrs:graph.get(i)){
                result.get(nbrs).add(i);
            }
        }
        return result;
    }
}

	/* CHEF AND REVERSING  ( CODECHEF)	*/
	
import java.io.*;
import java.util.*;

public class Main {
    	public static class Pair{
	    int vtx;
	    int wt;
	    Pair(int vtx,int wt){
	        this.vtx = vtx;
	        this.wt = wt;
	    }
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String[] st = br.readLine().split(" ");
	    int vtces = Integer.parseInt(st[0]);
	    int edges = Integer.parseInt(st[1]);
	    ArrayList<ArrayList<Pair>> graph = new ArrayList<>();
	    for(int i=0;i<vtces;i++){
	        graph.add(new ArrayList<>());
	    }
	    for(int i=0;i<edges;i++){
	        st = br.readLine().split(" ");
	        int u = Integer.parseInt(st[0]) -1;
	        int v= Integer.parseInt(st[1])-1;
	        graph.get(u).add(new Pair(v,0));
	        graph.get(v).add(new Pair(u,1));
	    }
	    LinkedList<Pair> que = new LinkedList<>();
	    boolean[] vis = new boolean[vtces];
	    que.addLast(new Pair(0,0));
	    
	    while(que.size() > 0){
	        Pair rem  = que.removeFirst();
	        if(rem.vtx == vtces-1){
	            System.out.println(rem.wt);
	            return;
	        }
	        vis[rem.vtx] = true;
	        for(Pair nbrs:graph.get(rem.vtx)){
	            if(vis[nbrs.vtx] == true){
	           continue;     
	            }
	            if(nbrs.wt == 0){
	              que.addFirst(new Pair(nbrs.vtx,rem.wt+0));  
	            }else{
	               que.addLast(new Pair(nbrs.vtx,rem.wt+1)); 
	            }
	        }
	    }
	    System.out.println("-1");
		
	}
	
}
	
	/*MINIMUM SPANNING TREE USING PRIMS ( SPOJ) */
import java.io.*;
import java.util.*;

public class Main {
   static class Edge {
      int src;
      int nbr;
      int wt;

      Edge(int src, int nbr, int wt) {
         this.src = src;
         this.nbr = nbr;
         this.wt = wt;
      }
   }
    public static class Pair implements Comparable<Pair>{
       int v;
       int av;
       int wt;
       Pair(int v,int av,int wt){
           this.v = v;
           this.av = av;
           this.wt = wt;
       }
       public int compareTo(Pair other){
           return this.wt - other.wt;
       }
   }

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       String s=br.readLine();
       String[] sp=s.split(" ");
      int vtces = Integer.parseInt(sp[0]);
      ArrayList<Edge>[] graph = new ArrayList[vtces];
      for (int i = 0; i < vtces; i++) {
         graph[i] = new ArrayList<>();
      }

      int edges = Integer.parseInt(sp[1]);
      for (int i = 0; i < edges; i++) {
         String[] parts = br.readLine().split(" ");
         int v1 = Integer.parseInt(parts[0])-1;
         int v2 = Integer.parseInt(parts[1])-1;
         int wt = Integer.parseInt(parts[2]);
         graph[v1].add(new Edge(v1, v2, wt));
         graph[v2].add(new Edge(v2, v1, wt));
      }

      // write your code here
      PriorityQueue<Pair> pq = new PriorityQueue<>();
      long[] ans = new long[vtces];
      boolean[] visited = new boolean[vtces];
      pq.add(new Pair(0,-1,0));
      while(pq.size() >0){
          Pair rem = pq.remove();
          if(visited[rem.v] == true) continue;
          visited[rem.v] = true;
          ans[rem.v] = rem.wt;
      
         for(Edge e:graph[rem.v]){
              if(visited[e.nbr] == false){
                  pq.add(new Pair(e.nbr,rem.v,e.wt));
              }
          }
      }
      long sum =0;
      for(int i=0;i<ans.length;i++){
          sum += ans[i];
          
      }
      System.out.println(sum);
   }
  

}
	
	/*	BELLMAN FORD ALGORITHM	*/
	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String[] st = br.readLine().split(" ");
	    int n = Integer.parseInt(st[0]);
	    int m = Integer.parseInt(st[1]);
	    int[][] arr = new int[m][3];
	    for(int i=0;i<m;i++){
	        st = br.readLine().split(" ");
	        arr[i][0] = Integer.parseInt(st[0])-1;
	        arr[i][1] = Integer.parseInt(st[1])-1;
	        arr[i][2] = Integer.parseInt(st[2]);
	    }
	    int[] path = new int[n];
	    Arrays.fill(path,Integer.MAX_VALUE);
	    path[0] = 0;
	    
	    
	    
	    
	    for(int i =0 ;i<n-1;i++){
	        for(int j= 0;j<m;j++){
	            int u = arr[j][0];
	            int v = arr[j][1];
	            int wt = arr[j][2];
	            
	            if(path[u] == Integer.MAX_VALUE){
	                continue;
	            }
	            if(path[u] + wt < path[v]){
	                path[v] = path[u] + wt;
	            }
	        }
	    }
	    
	    for(int i=1;i<n;i++){
	        if(path[i] != Integer.MAX_VALUE){
	            System.out.print(path[i] +" ");
	        }else{
	            System.out.print("1000000000");
	        }
	    }
	    System.out.println();
        
			}
}
	
	
	/*	Negative weight cycle  (GFG)	*/

class Solution
{
    public int isNegativeWeightCycle(int n, int[][] edges)
    {
        //code here
        int[] path = new int[n];
        Arrays.fill(path,Integer.MAX_VALUE);
        path[0] = 0;
        
        for(int i=0;i<n-1;i++){
            for(int j=0;j<edges.length;j++){
                int u = edges[j][0];
                int v = edges[j][1];
                int wt = edges[j][2];
                if(path[u] == Integer.MAX_VALUE) continue;
                if(path[u] + wt < path[v]){
                    path[v] = path[u] + wt;
                }
            }
        }
        for(int j=0;j<edges.length;j++){
            int u = edges[j][0];
            int v = edges[j][1];
            int wt = edges[j][2];
            if(path[u] == Integer.MAX_VALUE) continue;
            if(path[u] + wt < path[v]){
                return 1;
            }
        }
        return 0;
    }
}
	
	/*	TOPOLOGICAL SORTING (GFG) USING DFS	*/
	
class Solution
{
    //Function to return list containing vertices in Topological order. 
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) 
    {
        // add your code here
        Stack<Integer> st = new Stack<>();
        boolean[] visited = new boolean[V];
        Arrays.fill(visited,false);
        for(int i=0;i<V;i++){
            if(visited[i] == false){
                dfs(adj,i,visited,st);
            }
        }
        int[]ans = new int[V];
        int i= 0;
        while(st.size() > 0){
            ans[i] = st.peek();
            i++;
            st.pop();
        }
        return ans;
    }
static void dfs(ArrayList<ArrayList<Integer>> graph,int curr,boolean[]visited,Stack<Integer> st){
        visited[curr] = true;
        for(int nbr : graph.get(curr)){
            if(visited[nbr] == false){
                dfs(graph,nbr,visited,st);
            }
        }
        st.push(curr);
    }
}
	

	/* KAHN'S ALGORITHM  ( GFG) 	(ITERATIVE TOPOLOGICAL SORT)	*/
	
class Solution
{
    //Function to return list containing vertices in Topological order. 
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) 
    {
        // add your code here
        int[]indegree = new int[V];
        int[] ans = new int[V];
        
        for(int i=0;i<V;i++){
            for(int nbrs: adj.get(i)){
                indegree[nbrs]++;
            }
        }
        LinkedList<Integer> que = new LinkedList<>();
             for(int i=0;i<V;i++){
                if(indegree[i] == 0){
                    que.addLast(i);
                }
             }
        int count=0;
        while(que.size() > 0){
            int rem = que.removeFirst();
            ans[count] = rem;
            count++;
            for(int nbrs:adj.get(rem)){
                indegree[nbrs]--;
                if(indegree[nbrs] == 0){
                    que.addLast(nbrs);
                }
            }
        }
        if(count == V){
            return ans;
        }else{
            return new int[]{-1};
        }
    }
}

	/* Course Schedule II (LC- 210)	*/
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // step-1 --> make a graph from prerequiste
         ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i=0;i<numCourses;i++){
            graph.add(new ArrayList<>());
        }
        for(int i=0;i<prerequisites.length;i++){
            int u = prerequisites[i][0];
            int v = prerequisites[i][1];
            graph.get(v).add(u);    // because b is necessary for a 
        }
        // step-2 --> kahn's Algorithm
         int[] indegree = new int[numCourses];
			    int[] ans = new int[numCourses];
			    for(int i=0;i<numCourses;i++){
			        for(int nbrs:graph.get(i)){
			            indegree[nbrs]++;
			        }
			    }
			    LinkedList<Integer> que = new LinkedList<>();
			    for(int i=0;i<numCourses;i++){
			        if(indegree[i] == 0){
			            que.addLast(i);
			        }
			        
			    }
			    int count =0;
			    while(que.size() > 0){
			        int rem = que.removeFirst();
			        ans[count] = rem;
			        count++;
			        for(int nbr : graph.get(rem)){
			            indegree[nbr]--;
			            if(indegree[nbr] == 0){
			                que.addLast(nbr);
			            }
			        }
			    }
			    if(count == numCourses){
			        return ans;
			    }else{
			        return new int[]{};
			    }
    }
}
	
	/*	Rotting Oranges (LC-994)	*/

class Solution {
    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        if(n== 0 && m == 0) return 0;
        LinkedList<int[]> que = new LinkedList<>();
        int fresh_orange =0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j] == 1){
                    fresh_orange++;
                }else if(grid[i][j] == 2){  // adding position of rotten orange in que
                    que.add(new int[]{i,j});
                }
            }
        }
        if(fresh_orange ==0) return 0;
        int minute=0;
        while(que.size() > 0){
            int size = que.size();
            while(size-- > 0){
                int[] rem = que.removeFirst();
                int x = rem[0];
                int y = rem[1];
                if(x-1>=0 && grid[x-1][y] == 1){
                    grid[x-1][y] = 2;
                    que.add(new int[]{x-1,y});
                    fresh_orange--;
                }
                if(y-1>=0 && grid[x][y-1] == 1){
                    grid[x][y-1] = 2;
                    que.add(new int[]{x,y-1});
                    fresh_orange--;
                }
                if(x+1<grid.length && grid[x+1][y] == 1){
                    grid[x+1][y] = 2;
                    que.add(new int[]{x+1,y});
                    fresh_orange--;
                }
                if(y+1<grid[0].length && grid[x][y+1] == 1){
                    grid[x][y+1] = 2;
                    que.add(new int[]{x,y+1});
                    fresh_orange--;
                }
            }
            minute++;
        }
        return fresh_orange == 0 ? minute-1 : -1;
    }
}
	
	/*	Coloring A Border	(LC-1034)	*/

class Solution {
    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        if(grid[r0][c0] == color){
            return grid;
        }
        
        dfs(grid,r0,c0,grid[r0][c0],visited,color,n,m);
        return grid;
    }
    private void dfs(int[][]grid,int row,int col,int val,boolean[][]visited,int color,int n,int m){
        if(row > n-1 || row <0 ||  col > m-1 || col <0 || grid[row][col] != val || visited[row][col]){
            return;
        }
        visited[row][col] = true;
        boolean border = false;
        if(row==0 || col== 0 ||row == n-1 || col == m-1 || grid[row+1][col] != val || grid[row-1][col] != val || grid[row][col +1 ] != val || grid[row][col-1] != val ){
            border = true;
        }
        
        dfs(grid,row+1,col,val,visited,color,n,m);  // bottom
        dfs(grid,row-1,col,val,visited,color,n,m);  // top
        dfs(grid,row,col+1,val,visited,color,n,m);  // right
        dfs(grid,row,col-1,val,visited,color,n,m);  // left
        
        if(border){
            grid[row][col] = color;
        }
    }
}
	
	/*	Detect cycle in an undirected graph	(GFG)	USING BFS	*/
	
class Pair{
    int curr;
    int par;
    Pair(int curr,int par){
        this.curr = curr;
        this.par = par;
    }
}


class Solution
{   // by using BFS
    //Function to detect cycle in an undirected graph.
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj)
    {
        // Code here
        boolean[] visited = new boolean[V];
        Arrays.fill(visited,false);
        for(int i=0;i<V;i++){
            if(visited[i] == false){
               if(checkCycle(adj,visited,i)){
                return true;
            }
            }
        }
        return false;
    }
    
    private boolean checkCycle(ArrayList<ArrayList<Integer>> graph,boolean[]visited,int S){
        
        LinkedList<Pair> que = new LinkedList<>();
        que.add(new Pair(S,-1));
        visited[S] = true;
        while(que.size() > 0){
            int current = que.peek().curr;
            int parent = que.peek().par;
            que.remove();
            
            for(int nbrs : graph.get(current)){
                if(visited[nbrs] == false){
                    que.add(new Pair(nbrs,current));
                    visited[nbrs] = true;
                }else if(parent != nbrs) return true;
            }
        }
        return false;
    }
}
	

	/*	Detect cycle in an undirected graph	(GFG)	USING DFS	*/

class Solution
{   // using DFS
    //Function to detect cycle in an undirected graph.
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj)
    {
        // Code here
        boolean[] visited = new boolean[V];
        Arrays.fill(visited,false);
        for(int i=0;i<V;i++){
            if(visited[i] == false){
                if(checkCycle(i,-1,visited,adj)){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean checkCycle(int curr, int parent,boolean[]visited,ArrayList<ArrayList<Integer>> adj){
        visited[curr] = true;
        for(int nbrs : adj.get(curr)){
            if(visited[nbrs] == false){
                if(checkCycle(nbrs,curr,visited,adj) == true){
                    return true;
                }
            }else if(parent != nbrs) return true;
        }
        return false;
    }
}
	

	/*	Detect cycle in a directed graph  (GFG)	USING DFS	*/

class Solution 
{   // using dfs
    //Function to detect cycle in a directed graph.
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj)
    {
        // code here
        boolean[] visited = new boolean[V];
        boolean[] dfsVisited = new boolean[V];
        for(int i=0;i<V;i++){
            if(visited[i] == false){
                if(checkCycle(i,visited,dfsVisited,adj) == true){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean checkCycle(int curr,boolean[]visited,boolean[]dfsVisited,ArrayList<ArrayList<Integer>> adj){
        visited[curr] = true;
        dfsVisited[curr] = true;
        
        for(int nbrs: adj.get(curr)){
            if(visited[nbrs] == false){
                if(checkCycle(nbrs,visited,dfsVisited,adj) == true){
                    return true;
                }
            }else if(dfsVisited[nbrs] == true){
                return true;
            }
        }
        dfsVisited[curr] = false;
        return false;
    }
}
	

		/*	Number of Islands (LC-200)	*/

class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int ans =0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == '1' && visited[i][j] == false){
              dfs(grid,visited,i,j,m,n);
                ans++;
             }
                }
            }
         return ans;
        }
       
    
    private void dfs(char[][]grid,boolean[][] visited,int i,int j,int m,int n){
        
        if(i < 0 || i >= m || j <0 || j>= n || grid[i][j] == '0' || visited[i][j] == true){
            return ;
        }
        visited[i][j] = true;
        
        dfs(grid,visited,i+1,j,m,n);
        dfs(grid,visited,i-1,j,m,n);
        dfs(grid,visited,i,j+1,m,n);
        dfs(grid,visited,i,j-1,m,n);
        
        
    }
}
	
	/*number of islands  (GFG)	*/
	
class Solution
{
    //Function to find the number of islands.
    public int numIslands(char[][] grid)
    {
        // Code here
        int m = grid.length;
        int n = grid[0].length;
        int ans =0;
        boolean[][] visited = new boolean[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == '1' && visited[i][j] == false){
                    dfs(grid,visited,i,j,m,n);
                    ans++;
                }
            }
        }
        return ans;
    }
    private void dfs(char[][]grid,boolean[][]visited,int i,int j,int m, int n){
        if(i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == '0' || visited[i][j] == true){
            return;
        }
        visited[i][j] = true;
        dfs(grid,visited,i+1,j,m,n);
        dfs(grid,visited,i-1,j,m,n);
        dfs(grid,visited,i,j+1,m,n);
        dfs(grid,visited,i,j-1,m,n);
        dfs(grid,visited,i-1,j-1,m,n);
         dfs(grid,visited,i-1,j+1,m,n);
          dfs(grid,visited,i+1,j-1,m,n);
           dfs(grid,visited,i+1,j+1,m,n);
    }
}
	
	
	/*	Number of Enclaves	(LC-1020)	*/

class Solution {
    public int numEnclaves(int[][] grid) {
        // using dfs
        int ans =0;
        int m = grid.length;
        int n = grid[0].length;
        
        // travel on boundary and change 1 to 0
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i == 0 || j== 0 || i== m-1 || j == n-1){
                    change1to0(grid,i,j,m,n);
                }
            }
        }
        
        // calculate the 1's which are not connected to boundary
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == 1){
                    ans++;
                }
            }
        }
        return ans;
    }
    private void change1to0(int[][]grid,int i,int j,int m,int n){
        if(i <0 || j <0 || i>= m || j >=n || grid[i][j] == 0){
            return;
        }
        grid[i][j] = 0;
        change1to0(grid,i+1,j,m,n);
        change1to0(grid,i-1,j,m,n);
        change1to0(grid,i,j+1,m,n);
        change1to0(grid,i,j-1,m,n);
    }
}
	
	/*	Max Area of Island (LC - 695)	*/

class Solution {
    // same as number of islands but this time we have to calculate the size of island too. and return the island with maximum size
    // t-O(m*n)
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if(m == 0 || n ==0) return 0;
        int[] count = new int[1];
        int max =0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == 1){
                    count[0] = 0;
                    dfs(grid,i,j,m,n,count);
                    max = Math.max(count[0],max);
                }
            }
        }
        return max;
    }
    private void dfs(int[][] grid,int x,int y,int m,int n,int[] count){
        if(x < 0 || y < 0 || x >= m || y >= n || grid[x][y] == 0){
            return;
        }
        grid[x][y] = 0; 
        count[0]++;
        dfs(grid,x+1,y,m,n,count);
        dfs(grid,x-1,y,m,n,count);
        dfs(grid,x,y+1,m,n,count);
        dfs(grid,x,y-1,m,n,count);
    }
}
	
	/*	Island Perimeter 	(LC- 463)	*/
	
class Solution {
    public int islandPerimeter(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int perimeter =0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == 1){
                    // top
                    if(i == 0 || grid[i-1][j] == 0) perimeter++;
                    // left
                    if(j == 0 || grid[i][j-1] == 0) perimeter++;
                    // bottom
                    if(i == m-1 || grid[i+1][j] == 0) perimeter++;
                    // right
                    if(j == n-1 || grid[i][j+1] == 0) perimeter++;
                }
            }
        }
        return perimeter;
    }
}

		/*	Surrounded Regions	(LC-130)	*/
	
class Solution {
    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        
        // apply dfs and change O to $ on the boundary --> step -1
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0 || j == 0 || i == m-1 || j == n-1){
                    dfs(board,i,j);
                }
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j] == '$'){
                    board[i][j] = 'O';
                }else{
                    board[i][j] = 'X';
                }
            }
        }
        
    }
    private void dfs(char[][] board,int i,int j){
      if(i<0 || j<0 || i>board.length-1 || j>board[0].length-1 || board[i][j]!='O'){
            return;
        }
        board[i][j] = '$';
        dfs(board,i+1,j);
        dfs(board,i-1,j);
        dfs(board,i,j+1);
        dfs(board,i,j-1);
    }
}
	
	/*	Number of Distinct Islands (LINTCODE - 860)	*/
	
public class Solution {
    /**
     * @param grid: a list of lists of integers
     * @return: return an integer, denote the number of distinct islands
     */
       HashSet<String> set = new HashSet<>();
    int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
    char[] move = {'r','d','l','u'};
    String shape = "";
    int n=0,m=0;
    public int numberofDistinctIslands(int[][] grid) {
        // write your code here
         if(grid.length ==0 || grid[0].length == 0) return 0;
       n = grid.length;
        m = grid[0].length;
        for(int i=0;i<n;i++){
            for(int j =0;j<m;j++){
                if(grid[i][j]==1){
                    dfs(i,j,grid);
                    set.add(shape);
                    shape = "";
                }
            }
        }
        
        return set.size();
    }
  
    
    public void dfs(int i,int j,int[][] grid){
        
        grid[i][j] = 0;
        for(int d = 0;d<4;d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];
            
            if(r>=0 && c>=0 && r< n && c<m && grid[r][c] == 1){
                shape += move[d];
                dfs(r,c,grid);
                shape += "b";
            }
        }
         
    }
}
	
	/*	Bus Routes	(LC - 815)	*/
	
class Solution {
    public int numBusesToDestination(int[][] routes, int S, int T) {
       HashSet<Integer> visited = new HashSet<>();
       Queue<Integer> q = new LinkedList<>();
       HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
       int ans = 0; 
        
       if (S==T) return 0; 
        
       for(int i = 0; i < routes.length; i++){
            for(int j = 0; j < routes[i].length; j++){
                ArrayList<Integer> buses = map.getOrDefault(routes[i][j], new ArrayList<>());
                buses.add(i);
                map.put(routes[i][j], buses);                
            }       
        }
                
       q.add(S); 
       while (q.size() > 0) {
           int size = q.size();
           ans++;
           while(size-- > 0) {
               int cur = q.remove();
               ArrayList<Integer> buses = map.get(cur);
               for (int bus: buses) {
                    if (visited.contains(bus)) continue;
                    visited.add(bus);
                    for (int j = 0; j < routes[bus].length; j++) {
                        if (routes[bus][j] == T) return ans;
                        q.add(routes[bus][j]);  
                   }
               }
           }
        }
        return -1;
    }
}

	
	/*	Shortest Bridge	(LC - 934)	*/
	
class Solution {
    
    int[][] dirs = {{1,0},{0,1},{-1,0},{0,-1}};
    
    public int shortestBridge(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        LinkedList<int[]> que = new LinkedList<>();
        boolean found = false;  // found 1
        // step-1 marking visited for all 1's
        for(int i=0;i<m;i++){
            if(found == true){
                break;
            }
            for(int j=0;j<n;j++){
                if(grid[i][j] == 1){
                    dfs(grid,visited,i,j,que,dirs);
                    found = true;
                    break;
                }
            }
        }
        // step-2 calculating flips --> using BFS
        int step =0;
        while(que.size() > 0){
            int size = que.size();
            while(size-- > 0){
                int[] curr = que.remove();
               
                for(int[] dir : dirs){
                     int x = curr[0] + dir[0];
                     int y = curr[1] + dir[1];
                    if(x >=0 && y>=0 && x < grid.length && y < grid[0].length && visited[x][y] == false){
                     if(grid[x][y] == 1){
                         return step;
                     }
                        que.add(new int[]{x,y});
                        visited[x][y] = true;
                    }
                   
                }
            }
            step++;
        }
        return -1;
        
    }
    private void dfs(int[][] grid,boolean[][] visited,int i, int j, LinkedList<int[]> que,int[][] dirs){
        if(i < 0 || j <0 || i >= grid.length || j >= grid[0].length || visited[i][j] == true || grid[i][j] == 0){
            return;
        }
        visited[i][j] = true;
        que.add(new int[]{i,j});
        
        for(int[] dir : dirs){
            dfs(grid,visited,i+dir[0],j+dir[1],que,dirs);
        }
    }
}
	
	/*	Shortest Path in Binary Matrix (LC-1091)	*/
	
class Solution {
    int[][] dirs = {{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1}};
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        if(n == 0 || m == 0) return -1;
        
        if(grid[0][0] == 1 || grid[n-1][m-1] == 1) return -1;
        
        LinkedList<int[]> que = new LinkedList<>();
        int ans =0;
        que.add(new int[]{0,0});
        
        while(que.size() > 0){
            int size = que.size();
            while(size-- > 0){
                int[] rem = que.remove();
                int i = rem[0];
                int j = rem[1];
                if(i == n-1 && j == m-1) return ans+1;
                for(int[] dir : dirs){
                    int x = i + dir[0];
                    int y = j + dir[1];
                    if(x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] == 0){
                        que.add(new int[]{x,y});
                        grid[x][y] = 1;
                    }
                }
            }
          
            ans++;
        }
        return -1;
    }
}
	
	/*	 01 Matrix	(LC-542)	*/
	
class Solution {
    int[][] dirs = {{1,0},{0,1},{-1,0},{0,-1}};
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        LinkedList<int[]> que = new LinkedList<>();
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(mat[i][j] == 0){
                    que.add(new int[]{i,j});
                }else {
                    mat[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
        while(que.size() > 0){
            int size = que.size();
           
                int[] curr = que.remove();
                
                for(int[] dir : dirs){
                    int x = curr[0] + dir[0];
                    int y = curr[1] + dir[1];
                    if(x < 0 || x >= mat.length || y < 0|| y >= mat[0].length || mat[x][y] <= mat[curr[0]][curr[1]] +1) continue;
                    que.add(new int[]{x,y});
                    mat[x][y] = mat[curr[0]][curr[1]] +1;
                }
            
        }
        return mat;
    }
}
	
	/*	Euler circuit and Path(UNDIRECTED GRAPH)	(GFG)	*/
	
class Solution
{
    public int isEularCircuitExist(int V, ArrayList<ArrayList<Integer>> adj)
    {
        // Code here
        int[] degree = new int[V];
        for(int i=0;i<V;i++){
            if(adj.get(i).size() % 2 ==1){
                degree[i]++;
            }
        }
        int odd = 0;
        for(int i : degree){
            if(i % 2 != 0){
                odd++;
            }
        }
        if(odd == 0){
            return 2;   // circuit
        }else if(odd == 2){
              return 1;   // path
        }
        
        return 0;   // neither of one
    }
}
	
	/*	Eulerian Path in an Undirected Graph 	(GFG)	*/
	
class Solution{
    static int eulerPath(int N, int graph[][]){
        // code here
        int[] degree=new int[N];
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)
			{
				if(graph[i][j]==1)
				{
					degree[i]++;
				}
			}
		}
		int odd=0;
		for(int i:degree)
		{
			if(i%2!=0)
			{
				odd++;
			}
		}
		if(odd==0||odd == 2)
		{
			return 1;
		}
		else
		{
		return 0;
		}
    }
}
	
	/*	Euler Circuit in an Undirected Graph	(GFG)	*/
	
class Solution
{
    public boolean isEularCircuitExist(int V, ArrayList<ArrayList<Integer>> adj)
    {
        // Code here
        int[] degree = new int[V];
        for(int i=0;i<V;i++){
                if(adj.get(i).size()% 2 == 1){
                    degree[i]++;
                }
        }
        int odd =0;
        for(int i : degree){
            if(i % 2 != 0){
                 odd++;
            }
            if(odd != 0){
                return false;
            }
        }
        return true;
        
        
    }
}
	
	/*	Walls and Gates	(LINTCODE-663)	*/
	
public class Solution {
    /**
     * @param rooms: m x n 2D grid
     * @return: nothing
     */
    public void wallsAndGates(int[][] rooms) {
        // write your code here
        if(rooms.length == 0 || rooms[0].length == 0) return;
    int n = rooms.length;
    int m = rooms[0].length;
    boolean[][] visited = new boolean[n][m];
  
     LinkedList<int[]> que = new LinkedList<>();
    for(int i = 0;i<n;i++){
        for(int j = 0; j<m;j++){
            if(rooms[i][j] == 0){
               que.add(new int[]{i,j});
               visited[i][j] = true;
            }
        }
    }
    
    int level = 0;
    int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};

    while(que.size()!=0){
        int size = que.size();
        while(size-->0){
             int[] idx = que.removeFirst();
            int r = idx[0];
            int c = idx[1];
            
            for(int d = 0;d< 4;d++){
                
                int x = r + dir[d][0];
                int y = c + dir[d][1];
                
                if(x>=0 && y>=0 && x< n && y<m && rooms[x][y] == 2147483647 && visited[x][y] == false ){
                    rooms[x][y] = level + 1; 
                   que.add(new int[]{x,y});
                   visited[x][y] = true;
                }
            }
        }
        level++;
    }   
    }
}
	

		/*	Similar String Groups	(L.C- 839)	*/
	
class Solution {
    int[] parent;
    private int find(int x){
        if(parent[x] == x){
            return x;
        }
        int temp = find(parent[x]);
         parent[x] = temp;
        return temp;
    }
    private boolean isSimilar(String s1,String s2){
        int count =0;
        for(int i=0;i<s1.length();i++){
            if(s1.charAt(i) != s2.charAt(i)){
                count++;
                if(count > 2){
                    return false;
                }
            }
        }
        return true;
    }
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        
        parent = new int[n];
        // initialyy everyone is parent of itself
        for(int i=0;i<n;i++){
            parent[i] = i;
        }
        int count =n;
        for(int i=0;i<n;i++){
            int lox = find(i);
            for(int j= i+1;j<n;j++){
                if(isSimilar(strs[i],strs[j])){
                    int loy = find(j);
                    if(lox != loy){
                        parent[loy] = lox;
                        count--;
                }
            }
        }
        }
        return count;
    }
}
	
	/*	Redundant Connection	(L.C-684)	*/
	
class Solution {
    // using union find
    int[] parent;
   private int find(int i){
       if (parent[i] == i)
          return i;
       int temp = find(parent[i]);
       parent[i] = temp;
       return temp;
    }
    public int[] findRedundantConnection(int[][] edges) {
         int n = edges.length;
         parent = new int[n+1];
        // initially everyone is parent of itself only
         for (int i = 0; i <= n; i++){
             parent[i] = i;
         }

            for (int[] ans : edges){
            int lox = find(ans[0]);
            int loy = find(ans[1]);

                if (lox != loy){
                    parent[loy] = lox;
                 }else{
                    return ans;
             }
            }

        return new int[0];
    }
}
	
	
	
	
	
	
