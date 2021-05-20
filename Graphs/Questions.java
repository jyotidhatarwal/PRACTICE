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
    

