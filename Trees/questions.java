/*
Node defined as

class Node{
    int data;
    Node left,right;
    Node(int d){
        data=d;
        left=right=null;
    }
}

*/


//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~` BINARY TREE TRAVERSALS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

// INORDER TRAVERSAL (USING STACK)	ITERATIVE


class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> st = new Stack<>();
        List<Integer> ans = new ArrayList<>();
        addAllLeftMostNode(root,st);
        while(st.size() > 0){
            TreeNode rem = st.pop();
            ans.add(rem.val);
            addAllLeftMostNode(rem.right,st);
        }
        return ans;
        
    }
    private void addAllLeftMostNode(TreeNode root,Stack<TreeNode> st){
        while(root != null){
            st.push(root);
            root = root.left;
        }
    }
}




// INORDER TRAVERSAL (MORRIS)

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
      List<Integer> ans = new ArrayList<>();
        while(root != null){
            if(root.left == null){
                ans.add(root.val);
                root = root.right;
            }else{
                TreeNode rootp1 = root.left;
                while(rootp1.right != null && rootp1.right != root){
                    rootp1 = rootp1.right;
                }
                if(rootp1.right == null){
                    rootp1.right = root;
                    root = root.left;
                }else{
                    ans.add(root.val);
                    rootp1.right = null;
                    root = root.right;
                }
            }
        }
        return ans;
    }
}





// PREORDER TRAVERSAL	(MORRIS)


public class Solution {
    public ArrayList<Integer> preorderTraversal(TreeNode A) {
        ArrayList<Integer>ans = new ArrayList<>();
        while(A != null){
            if(A.left == null){
                ans.add(A.val);
                A = A.right;
            }else{
                TreeNode rootp1 = A.left;
                while(rootp1.right != null && rootp1.right != A){
                    rootp1 = rootp1.right;
                }
                if(rootp1.right == null){
                    ans.add(A.val);
                    rootp1.right = A;
                    A = A.left;
                }else{
                    rootp1.right = null;
                    A = A.right;
                }
            }
        }
        return ans;
  }
	
	
	
	
// LEVEL ORDER TRAVERSAL	(LC-102)
	
	
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<TreeNode> que = new LinkedList<>();
        que.add(root);
        while(que.size() > 0){
            int size = que.size();
            List<Integer> ans = new ArrayList<>();
            while(size-- > 0){
                TreeNode rem = que.removeFirst();
                ans.add(rem.val);
                if(rem.left != null){
                    que.add(rem.left);
                }
                if(rem.right != null){
                    que.add(rem.right);
                }
            }
            result.add(ans);
        }
        return result;
    }
}




	
	
// 	Validate Binary Search Tree	(LC-98)
	
	
class Solution {
    public boolean isValidBST(TreeNode root) {
        boolean result = true;
        List<Integer> ans = inorder(root);
        int size = ans.size();
        for(int i=1;i<size;i++){
            if(ans.get(i) <= ans.get(i-1)){
                result = false;
                break;
            }
        }
        return result;
    }
    private List<Integer> inorder(TreeNode root){
        List<Integer> ans = new ArrayList<>();
        while(root != null){
            if(root.left == null){
                ans.add(root.val);
                root = root.right;
            }else{
                TreeNode rootp1 = root.left;
                while(rootp1.right != null && rootp1.right != root){
                    rootp1 = rootp1.right;
                }
                if(rootp1.right == null){
                    rootp1.right = root;
                    root = root.left;
                }else{
                    ans.add(root.val);
                    rootp1.right = null;
                    root = root.right;
                }
            }
        }
        return ans;
    }
}

	
	
//	RECOVER BST	(LC-99)
	
	
class Solution {
    private TreeNode first;
    private TreeNode second;
    private TreeNode pre;
    public void recoverTree(TreeNode root) {
        if(root==null) return;
        first = null;
        second = null;
        pre = null;
        inorder(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
    
    private void inorder(TreeNode root){
        if(root==null) return;
        inorder(root.left);
        
        if(first==null && (pre==null ||pre.val>=root.val)){
            first = pre;
        }
        if(first!=null && pre.val>=root.val){
            second = root;
        }
        pre = root;
        inorder(root.right);
    }
}

	
	
	//	!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~		VIEW BASED QUESTIONS 	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	
	

	/*		Left View of Binary Tree	(GFG)		*/

class Tree
{
    //Function to return list containing elements of left view of binary tree.
    ArrayList<Integer> leftView(Node root)
    {
      // Your code here
      if(root == null) return new ArrayList<>();
      ArrayList<Integer> ans = new ArrayList<>();
      LinkedList<Node> que = new LinkedList<>();
      que.add(root);
      while(que.size() > 0){
          int size = que.size();
          ans.add(que.getFirst().data);
          while(size-- > 0){
              Node rem = que.removeFirst();
              if(rem.left != null){
                  que.add(rem.left);
              }
              if(rem.right != null){
                  que.add(rem.right);
              }
          }
      }
      return ans;
    }
}



/*		Binary Tree Right Side View	(LC-199)		*/

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        if(root == null) return new ArrayList<>();
     List<Integer> ans = new ArrayList<>();
        LinkedList<TreeNode>que = new LinkedList<>();
        que.add(root);
        while(que.size() > 0){
            int size = que.size();
            ans.add(que.getFirst().val);
            while(size-- > 0){
                TreeNode rem = que.removeFirst();
                if(rem.right != null){
                    que.add(rem.right);
                }
                if(rem.left != null){
                    que.add(rem.left);
                }
            }
        }
        return ans;
    }
}


			/*	Top View of Binary Tree (GFG)		*/


class Solution
{   public static class Pair{
        Node node = null;
        int hl =0;
        Pair(Node node,int hl){
            this.node = node;
            this.hl = hl;
        }
        
    }
    //Function to return a list of nodes visible from the top view 
    //from left to right in Binary Tree.
    static void width(Node root,int hl,int[]minMax){
        if(root == null) return;
        minMax[0] = Math.min(minMax[0],hl);
        minMax[1] = Math.max(minMax[1],hl);
        width(root.left,hl-1,minMax);
        width(root.right,hl+1,minMax);
    }
    static ArrayList<Integer> topView(Node root)
    {
        // add your code
        int[] minMax = new int[2];
        width(root,0,minMax);
        int length = minMax[1] - minMax[0] +1;
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0;i<length;i++){
            ans.add(null);
        }
        LinkedList<Pair> que = new LinkedList<>();
        que.add(new Pair(root,Math.abs(minMax[0])));
        while(que.size() > 0){
            int size = que.size();
            while(size--  > 0){
                Pair rem = que.removeFirst();
                Node node = rem.node;
                int hl = rem.hl;
                if(ans.get(hl) == null){
                    ans.set(hl,node.data);
                }
                if(node.left != null){
                    que.add(new Pair(node.left,hl-1));
                }
                if(node.right != null){
                    que.add(new Pair(node.right,hl+1));
                }
            }
        }
        return ans;
        
    }
}


/*		Bottom View of Binary Tree	(GFG)	*/

class Tree
{   public static class Pair{
    Node node = null;
    int hl =0;
    Pair(Node node,int hl){
        this.node = node;
        this.hl = hl;
    }
}
    public void width(Node root,int hl,int[]minMax){
        if(root == null) return;
        minMax[0]  = Math.min(minMax[0],hl);
        minMax[1] = Math.max(minMax[1],hl);
        width(root.left,hl-1,minMax);
        width(root.right,hl+1,minMax);
    }
    //Function to return a list containing the bottom view of the given tree.
    public ArrayList <Integer> bottomView(Node root)
    {
        // Code here
        ArrayList<Integer> ans = new ArrayList<>();
        int[] minMax = new int[2];
        width(root,0,minMax);
        int length = minMax[1] - minMax[0] +1;
        for(int i=0;i<length;i++){
            ans.add(null);
        }
        LinkedList<Pair> que = new LinkedList<>();
        que.add(new Pair(root,Math.abs(minMax[0])));
        while(que.size() > 0){
            int size = que.size();
            while(size-- > 0){
                Pair rem = que.removeFirst();
                Node node = rem.node;
                int hl = rem.hl;
                ans.set(hl,node.data);
                if(node.left != null){
                    que.add(new Pair(node.left,hl-1));
                }
                if(node.right != null){
                    que.add(new Pair(node.right,hl+1));
                }
            }
        }
        return ans;
    }
}

/*		Reverse Level Order Traversal(GFG)		*/


class Tree
{
    public ArrayList<Integer> reverseLevelOrder(Node node) 
    {
        // code here
        ArrayList<Integer> ans = new ArrayList<>();
        LinkedList<Node> que = new LinkedList<>();
        que.add(node);
        while(que.size() > 0){
            int size = que.size();
            while(size-- > 0){
                Node rem = que.removeFirst();
                ans.add(rem.data);
                if(rem.right != null){
                    que.add(rem.right);
                }
                if(rem.left != null){
                    que.add(rem.left);
                }
            }
        }
        Collections.reverse(ans);
        return ans;
    }
}      



/*		Vertical Order Traversal of a Binary Tree	(LC-987)	*/


class Solution {
    public class vPair{
        TreeNode node = null;
        int hl =0;
        vPair(TreeNode node,int hl){
            this.node = node;
            this.hl =hl;
        }
    }
    public void width(TreeNode node,int hl,int[] ans){
        if(node == null) return;
        ans[0] = Math.min(ans[0],hl);
        ans[1] = Math.max(ans[1],hl);
        
        width(node.left,hl-1,ans);
        width(node.right,hl+1,ans);
    }
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        int[]minMax = new int[2];
        width(root,0,minMax);
        int len = minMax[1] - minMax[0] +1;
         List<List<Integer>> ans = new ArrayList<>();
        for(int i=0;i<len;i++){
            ans.add(new ArrayList<>());
        }
       
        PriorityQueue<vPair> parentQue = new PriorityQueue<>((a,b) -> {
            return a.node.val - b.node.val;
        });
        PriorityQueue<vPair> childQue = new PriorityQueue<>((a,b) -> {
            return a.node.val - b.node.val;
        });
        
        parentQue.add(new vPair(root,Math.abs(minMax[0])));
        while(parentQue.size() > 0){
            int size = parentQue.size();
            while(size-- > 0){
                vPair rem = parentQue.remove();
                TreeNode node = rem.node;
                int hl = rem.hl;
                ans.get(hl).add(node.val);
                if(node.left != null){
                    childQue.add(new vPair(node.left,hl-1));
                }
                if(node.right != null){
                    childQue.add(new vPair(node.right,hl+1));
                }
            }
            PriorityQueue<vPair> temp = parentQue;
            parentQue = childQue;
            childQue = temp;
        }
        
        return ans;
    }
}


/*	Diagonal Order Of A Binarytree	(GFG)	*/


 public static ArrayList<ArrayList<Integer>> diagonalOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addFirst(root);
        while(que.size() > 0){
            int size = que.size();
             ArrayList<Integer> ans = new ArrayList<>();
            while(size-- > 0){
                TreeNode rem = que.removeFirst();
                while(rem != null){
                    ans.add(rem.val);
                    if(rem.left != null){
                        que.addLast(rem.left);
                    }
                    rem = rem.right;
                }
            }
            result.add(ans);
        }
        return result;
    }




/*		Boundary Traversal of binary tree(GFG)		*/


class Solution
{   ArrayList<Integer> ans;
	ArrayList <Integer> printBoundary(Node node)
	{
	    ans= new ArrayList<>();
	    if(node == null) return new ArrayList<>();
	    ans.add(node.data);
	    if(node.left != null){
	        leftB(node.left);
	    }
	    leaves(node);
	    if(node.right != null){
	        rightB(node.right);
	    }
	    return ans;
	}
	void leftB(Node node){
	    if(node.left == null && node.right == null) return;
	    ans.add(node.data);
	    if(node.left != null){
	        leftB(node.left);
	    }else if(node.right != null){
	        leftB(node.right);
	    }
	    
	}
	void leaves(Node node){
	    if(node == null) return;
	    if(node.left == null && node.right == null) {
	        ans.add(node.data);
	    }
	    leaves(node.left);
	    leaves(node.right);
	}
	void rightB(Node node){
	    if(node.left == null && node.right == null) return;
	    if(node.right != null){
	        rightB(node.right);
	    }else if(node.left != null){
	        rightB(node.left);
	    }
	    ans.add(node.data);
	}
}




	
	
//	Vertical sum	(GFG)
	
	//	ITERATIVE SOLUTION

class Solution{
    public class pair{
        Node node = null;
        int vl =0;
        pair(Node node,int vl){
            this.node = node;
            this.vl = vl;
        }
    }
    private void width(Node root,int vl,int[] minMax){
        if(root == null) return;
        
        minMax[0] = Math.min(minMax[0],vl);
        minMax[1] = Math.max(minMax[1],vl);
        
        width(root.left,vl-1,minMax);
        width(root.right,vl+1,minMax);
    }
    public ArrayList <Integer> verticalSum(Node root) {
        // add your code here
        int[]minMax = new int[2];
        width(root,0,minMax);
        int len = minMax[1] - minMax[0] +1;
        LinkedList<pair> que = new LinkedList<>();
        que.addLast(new pair(root,Math.abs(minMax[0])));
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0;i<len;i++){
            ans.add(0);
        }
        while(que.size() > 0){
            int size = que.size();
            while(size-- > 0){
                pair rp = que.removeFirst();
                Node node = rp.node;
                int vl = rp.vl;
                ans.set(vl,ans.get(vl) + node.data);
                if(node.left != null){
                    que.addLast(new pair(node.left,vl-1));
                }
                if(node.right != null){
                    que.addLast(new pair(node.right,vl+1));
                }
            }
        }
        return ans;
    }
}

	
	
//	VERTICAL SUM	(GFG)
	
	// RECURSIVE SOLUTION
	
	
class Solution{
    private void width(Node root,int vl,int[]minMax){
        if(root == null) return;
        minMax[0] = Math.min(minMax[0],vl);
        minMax[1] = Math.max(minMax[1],vl);
        
        width(root.left,vl-1,minMax);
        width(root.right,vl+1,minMax);
    }
    private void dfs(Node root,int vl,ArrayList<Integer> ans){
        if(root == null) return;
        
        ans.set(vl,ans.get(vl)+root.data);
        dfs(root.left,vl-1,ans);
        dfs(root.right,vl+1,ans);
    }
    public ArrayList <Integer> verticalSum(Node root) {
        // add your code here
        int[]minMax = new int[2];
        width(root,0,minMax);
        int len = minMax[1] - minMax[0] +1;
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0;i<len;i++){
            ans.add(0);
        }
        
        dfs(root,Math.abs(minMax[0]),ans);
        return ans;
    }
}

	
	
//	DIAGONAL SUM TREE	(GFG)
	
	//	RECURSIVE
	

class Tree {
    private static void dfs(Node root,int diagonalNumber,ArrayList<Integer> ans){
        if(root == null) return;
        if(diagonalNumber == ans.size()){
            ans.add(0);
        }
        ans.set(diagonalNumber,ans.get(diagonalNumber) + root.data);
        
        dfs(root.left,diagonalNumber+1,ans);
        dfs(root.right,diagonalNumber,ans);
    }
    public static ArrayList <Integer> diagonalSum(Node root) 
    {
        // code here.
        ArrayList<Integer> ans = new ArrayList<>();
        dfs(root,0,ans);
        return ans;
    }
}
	
	
// DIAGONAL SUM TREE	(GFG)
	
	// ITERATIVE
	
	
class Tree {
    public static ArrayList <Integer> diagonalSum(Node root) 
    {
        // code here.
        LinkedList<Node> que = new LinkedList<>();
        ArrayList<Integer> ans  = new ArrayList<>();
        que.add(root);
        while(que.size() > 0){
            int size = que.size();
            int sum =0;
            while(size-- > 0){
                Node rem = que.removeFirst();
                while(rem != null){
                    sum += rem.data;
                    if(rem.left != null){
                        que.addLast(rem.left);
                    }
                    rem = rem.right;
                }
            }
            ans.add(sum);
        }
        return ans;
    }
}
	
	

/*		Binary Tree Coloring Game	(LC-1145)	*/


class Solution {
    int left,right,val;
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        val = x;
        help(root);
       if(Math.max(Math.max(left,right),n-left-right-1) > n/2){
            return true;
        }
        return false;
    }
    private int help(TreeNode root){
        if(root == null) return 0;
        int l = help(root.left);
        int r = help(root.right);
        if(root.val == val){
            left = l;
            right =r;
        }
        return l+ r+1;
    }
}


// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	DLL TO TREE AND TREE TO DLL	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	
	
	
	//	 Convert Binary Search Tree to Sorted Doubly Linked List
	
	//T-O(N)	S-O(N)	USING STACK
	
public class Solution {
    /**
     * @param root: root of a tree
     * @return: head node of a doubly linked list
     */
     private void addAllLeftMostNode(TreeNode curr,Stack<TreeNode> st){
         while(curr != null){
             st.push(curr);
             curr = curr.left;
         }
     }
    public TreeNode treeToDoublyList(TreeNode root) {
        // Write your code here.
    Stack<TreeNode> st = new Stack<>();
    addAllLeftMostNode(root,st);
    TreeNode dummy = new TreeNode(-1);
    TreeNode prev = dummy;
    while(st.size() > 0){
        TreeNode curr = st.pop();
        prev.right = curr;
        curr.left = prev;
        prev = curr;
        addAllLeftMostNode(curr.right,st);
    }
    TreeNode head = dummy.right;
    dummy.right = head.left = null;
    head.left = prev;
    prev.right = head;
    return head;

    }
}


	//	 Convert Binary Search Tree to Sorted Doubly Linked List	
	
	// T-O(N)	S-O(1)
	
	
	public class Solution {
    /**
     * @param root: root of a tree
     * @return: head node of a doubly linked list
     */
public TreeNode treeToDoublyList(TreeNode root) {
     TreeNode dummy = new TreeNode(-1);
        TreeNode prev = dummy;
        TreeNode curr = root;
        while(curr != null){
            if(curr.left == null){
                prev.right = curr;
                curr.left = prev;
                prev = curr;
                curr = curr.right;
            }else{
                TreeNode rootp1 = curr.left;
                while(rootp1.right != null && rootp1.right != curr){
                    rootp1 = rootp1.right;
                }
                if(rootp1.right == null){
                    rootp1.right = curr;
                    curr = curr.left;
                }
                if(rootp1.right == curr){
                    rootp1.right = null;
                    prev.right = curr;
                    curr.left = prev;
                    prev = curr;
                    curr = curr.right;
                }
            }
        }
        TreeNode head = dummy.right;
        dummy.right = head.left = null;
        head.left = prev;
        prev.right = head;
        return head;
}
}
	

*/

/*CONVERT A BINARY TREE TO A CIRCULAR DOUBLY LL */
class Solution
{ 
    //Function to convert binary tree into circular doubly linked list.
    Node bTreeToClist(Node root)
    {
        //your code here
        if(root == null) return null;
        Node left = bTreeToClist(root.left);
        Node right = bTreeToClist(root.right);
        root.left = root.right = root;
        return concatenate(concatenate(left,root),right);  // because answer required in inorder
    }
    Node concatenate(Node l1,Node l2){
      if(l1 == null) return l2;
      if(l2 == null) return l1;
        
    Node t1 = l1.left;
    Node t2 = l2.left;
    
    t1.right = l2;
    l2.left = t1;
    t2.right = l1;
    l1.left = t2;
    return l1;  
    }
}

/* In-place conversion of Sorted DLL to Balanced BST */
	//T-O(N)
class solution{
	private static Node getMidNode(Node root){
        if(root == null || root.right == null){
            return root;
        }
        Node slow =root;
        Node fast = root;
        while(fast.right != null && fast.right.right != null){
            slow = slow.right;
            fast = fast.right.right;
        }
        return slow;
    }
  // left : prev, right : next
  public static Node SortedDLLToBST(Node head) {
    if(head == null || head.right == null){
        return head;
    }
    Node mid = getMidNode(head);
    Node prev = mid.left;
    Node forward = mid.right;
    forward.left = mid.right = mid.left = null;
    if(prev != null){
        prev.right = null;
    }
    Node leftHead = prev != null ? head : null;
    Node rightHead = forward;
    Node root = mid;
    
    root.left = SortedDLLToBST(leftHead);
    root.right = SortedDLLToBST(rightHead);
    return root;
  }
}	

/*  APPROACH T- O(NLOGN)*/

public static ListNode convert(ListNode head){
    if(head==null || head.next==null) return head;
    ListNode slow=head, fast=head;
    while(fast!=null&&fast.next!=null){
        slow=slow.next;
        fast=fast.next.next;
    }
    if(slow.pre!=null) slow.pre.next=null;
    if(slow.next!=null) slow.next.pre=null;
    slow.pre=convert(head);
    slow.next=convert(slow.next);
    return slow;
}

/*  OPTIMIZED APPROACH T-O(N) */

public static ListNode convert(ListNode head){
    if(head==null || head.next==null) return head;
    int n=0;
    ListNode ptr=head;
    while(ptr!=null){
        n++;
        ptr=ptr.next;
    }
    return build(head, n);
}

public ListNode build(ListNode head, int n){
    if(n<=0) return null;
    ListNode left=build(head, n/2);
    ListNode root=head;
    root.pre=left;
    head=head.next;
    root.next=build(head, n-n/2-1);
    return root;
}

	
	
	
	
//	~~!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	
	
/*   Delete Node in a BST (LC - 450)   */

class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
        if(key > root.val){
            root.right = deleteNode(root.right,key);
        }else if(key < root.val){
            root.left = deleteNode(root.left,key);
        }else{
            root = delete(root);
        }
        return root;
    }
    private TreeNode delete(TreeNode node){
        if(node.left == null && node.right == null) return null;
        else if(node.right == null) return node.left;
        else if(node.left == null) return node.right;
        else{
            TreeNode rootp1 = node.right;
            while(rootp1.left != null){
                rootp1 = rootp1.left;
            }
            rootp1.left = node.left;
            return node.right;
        }
    }
}


/*    Clone a Binary Tree (GFG)  */

/*    RECURSIVE APPROACH  */

class Solution{
    public static Tree cloneTree(Tree tree){
       // add code here.
      if(tree == null) return null;
       Tree node = new Tree(tree.data);
       node.left = cloneTree(tree.left);
       node.right = cloneTree(tree.right);
       node.random = tree.random;
       return node
      
    }     
  }

/*    ANOTHER APPROACH  */

class Solution{
    public static Tree cloneTree(Tree tree){
       // add code here.
       map(tree);
       set(tree);
       Tree newHead = tree.left;
       extract(tree);
       
       return newHead;
     }
     
     private static void map(Tree tree){
          if(tree == null) return ;
        Tree node = new Tree(tree.data);
        Tree rootp1 = tree.left;
        tree.left = node;
        node.left = rootp1;
        map(tree.left.left);
        map(tree.right);
     }
     private static void set(Tree tree){
         if(tree == null) return ;
         if(tree.random != null){
             tree.left.random = tree.random.left;
         }
         set(tree.left.left);
         set(tree.right);
     }
     private static Tree extract(Tree tree){
         if(tree==null) return null;
        Tree rootp1 = tree.left;
        rootp1.left=null;
        tree.left  = tree.left.left;
        rootp1.left = extract(tree.left);
        rootp1.right = extract(tree.right);
        return rootp1;
     }  
}

	
	//	!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
	
	
// 	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	CONSTRUCTION BASED QUESTIONS	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	
	
	

/*      Construct tree from Inorder and LevelOrder  (GFG)        */

class GfG
{
    Node buildTree(int inord[], int level[])
    {
        //your code here
          //your code here
       HashMap<Integer,Integer> map=new HashMap<>();
	       for(int i=0;i<inord.length;i++)
	       {
	    	   map.put(inord[i], i);
	       }
	       return buildTreelevel(inord,0,inord.length-1,level,map);
    }
    Node buildTreelevel(int[] inorder,int is,int ie,int[] level,HashMap<Integer,Integer> map)
	 {
		 if(is>ie||level.length==0)
		 {
			 return null;
		 }
		 Node root=new Node(level[0]);
		 int index=map.get(level[0]);
		 int count=index-is;
		 int[] llevel=new int[count];
		 int[] rlevel=new int[level.length-count-1];
		 int l=0;
		 int r=0;
		 for(int i=1;i<level.length;i++)
		 {
			 if(map.get(level[i])<index)
			 {
				 llevel[l]=level[i];
				 l++;
			 }
			 else
			 {
				 rlevel[r]=level[i];
				 r++;
			 }
		 }
		 root.left= buildTreelevel(inorder, is, index-1, llevel, map);
		 root.right= buildTreelevel(inorder, index+1, ie, rlevel, map);
		 return root;
	 }
}



/*     Construct BST from Postorder (GFG)     */

class GFG
{   int idx =0;
    public static Node constructTree(int post[],int n)
    {
        //Add your code here.
        return help(post,0,n-1);
    }
   private static Node help(int post[],int start,int end){
     
     if(start > end) return null;
     Node node = new Node(post[end]);
     int i;
     for(i= end;i>=start;i--){
         if(post[i] < post[end]) break;
     }
     node.right= help(post,i+1,end-1);
     node.left =help(post,start,i);
     return node;
       
   }
}


/*	Serialize and Deserialize a Binary Tree (GFG)	*/

class Tree 
{
    //Function to serialize a tree and return a list containing nodes of tree.
	public void serialize(Node root, ArrayList<Integer> A) 
	{
	    //code here
	    if(root == null){
	        A.add(-1);
	        return;
	    }
	    A.add(root.data);
	    serialize(root.left,A);
	    serialize(root.right,A);
	}
	
	//Function to deserialize a list and construct the tree.
	int index =0;
    public Node deSerialize(ArrayList<Integer> A)
    {
        //code here
        if(index == A.size()) return null;
        int val = A.get(index++);
        if(val == -1) return null;
        Node node = new Node(val);
        node.left = deSerialize(A);
        node.right = deSerialize(A);
        return node;
    }
}


/*	Serialize and Deserialize Binary Tree	(LC-297)		*/



public class Codec {
    private void helpSerialize(TreeNode root,StringBuilder sb){
        if(root == null){
            sb.append("null,");
            return;
        }
        sb.append(root.val+",");
        helpSerialize(root.left,sb);
        helpSerialize(root.right,sb);
    }
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        helpSerialize(root,sb);
        return sb.toString();
    }
    int idx=0;
    private TreeNode Helpdeserialize(String[] arr) {
        if(idx >= arr.length || arr[idx].equals("null")){
            idx++;
            return null;
        }
        
        TreeNode node = new TreeNode(Integer.parseInt(arr[idx++]));
        node.left =  Helpdeserialize(arr);
        node.right =  Helpdeserialize(arr);
        return node;
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split(",");
        return  Helpdeserialize(arr);
    }
}





	/*		 Binary Tree Cameras	(LC-968)		*/


    // -1 i am covered by a camera .
    // 0 i am a camera.
    // 1 means i need a camera.
class Solution{
    int camera=0;
    public int minCameraCover(TreeNode root) {
        if(minCameraCover_(root) == 1) camera++;
        return camera;
    }
    
    public int minCameraCover_(TreeNode root){
        if(root==null) return  -1;
        
        int lres = minCameraCover_(root.left);
        int rres = minCameraCover_(root.right);

        if(lres == 1 || rres == 1){
            camera++;
            return 0;
        }

        if(lres == 0 || rres == 0){
            return -1;
        }

        return 1;
    }
    
}



/*		Count Complete Tree Nodes(LC-222)	*/


class Solution {
    public int countNodes(TreeNode root) {
        if(root == null){
            return 0;
        }
        int leftHeight = leftCount(root);
        int rightHeight = rightCount(root);
        if(leftHeight == rightHeight){
            return (1 << leftHeight) -1;
        }
        return countNodes(root.left) + countNodes(root.right) +1;
    }
    private int leftCount(TreeNode root){
        int count=1;
        while(root.left != null){
            count++;
            root = root.left;
        }
        return count;
    }
    private int rightCount(TreeNode root){
        int count =1;
        while(root.right != null){
            count++;
            root = root.right;
        }
        return count;
    }
}

/*		 Closest Binary Search Tree Value	(LINTCODE)		*/


public class Solution {
    /**
     * @param root: the given BST
     * @param target: the given target
     * @return: the value in the BST that is closest to the target
     */
    public int closestValue(TreeNode root, double target) {
        // write your code here
        int ans = root.val;
        while(root != null){
            if(Math.abs(root.val - target) < Math.abs(ans - target)){
                ans = root.val;
            }
            if(target < root.val){
                root = root.left;
            }else{
                root = root.right;
            }
        }
        return ans;
    }
}


/*		Sum Root to Leaf Numbers	(LC-129)	*/


class Solution {
    public int sumNumbers(TreeNode root) {
       return traversal(root,0);
    }
    private int traversal(TreeNode root,int val){
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){
            return val*10 + root.val;
        }
        int left = traversal(root.left,val*10+root.val);
        int right = traversal(root.right,val*10+root.val);
        return left + right;
    }
}







