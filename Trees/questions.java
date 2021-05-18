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
