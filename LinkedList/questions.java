//  ADDITION OF TWO LINKEDLIST

class Solution{
    //Function to add two numbers represented by linked list.
    static Node addTwoLists(Node first, Node second){
        // code here
        // return head of sum list
        Node c1 = reverse(first);
        Node c2 = reverse(second);
        Node dummy = new Node(-1);
        Node prev = dummy;
        int carry = 0;
        while(c1 != null || c2 != null || carry != 0){
            int sum = (c1 != null ? c1.data : 0) + (c2 != null ? c2.data : 0) + carry;
            int lastDigit = sum % 10;
            carry = sum / 10;
            prev.next = new Node(lastDigit);
            prev = prev.next;
            if(c1 != null) c1 = c1.next;
            if(c2 != null) c2 = c2.next;
        }
        return reverse(dummy.next);
        
        
    }
    static Node reverse(Node head){
        if(head == null || head.next == null) return head;
        Node prev = null;
        Node curr = head;
        while(curr != null){
            Node forward = curr.next;
            curr.next = prev;
            prev = curr;
            curr = forward;
        }
        return prev;
    }
}



