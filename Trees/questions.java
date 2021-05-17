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

