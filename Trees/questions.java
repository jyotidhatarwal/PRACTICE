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





