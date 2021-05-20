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

