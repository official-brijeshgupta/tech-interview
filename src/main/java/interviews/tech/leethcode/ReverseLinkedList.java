package interviews.tech.leethcode;

import interviews.tech.leethcode.models.ListNode;


public class ReverseLinkedList {

    public static void execute(){
        ListNode l1 = ListNode.create(new int[]{1,2,3,4,5});
        ListNode.print(l1);

        ListNode r1 = reverseList(l1);
        ListNode.print(r1);

        ListNode l2 = ListNode.create(new int[]{});
        ListNode.print(l2);

        ListNode r2 = reverseList(l1);
        ListNode.print(r2);
    }

    private static ListNode reverseList(ListNode head) {

        if(head == null || head.next==null) return head;

        ListNode prev = null;
        ListNode curr = head;
        ListNode temp = null;

        while(curr!=null){
            temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;

        }

        return prev;


    }
}
