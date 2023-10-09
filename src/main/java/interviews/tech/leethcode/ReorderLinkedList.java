package interviews.tech.leethcode;

import interviews.tech.leethcode.models.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given the head of a singly linked-list. The list can be represented as:
 *
 * L0 → L1 → … → Ln - 1 → Ln
 * Reorder the list to be on the following form:
 *
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 *
 *
 *
 * Example 1:
 * 1->2->3->4 => 1 -> 4 -> 2 -> 3
 *
 * Input: head = [1,2,3,4]
 * Output: [1,4,2,3]
 * Example 2:
 * 1 -> 2 -> 3 -> 4 -> 5 => 1 -> 5 -> 2 -> 4 -> 3
 *
 * Input: head = [1,2,3,4,5]
 * Output: [1,5,2,4,3]
 *
 */
public class ReorderLinkedList {

    public static void execute() {
        List<ListNode> inputs = new ArrayList<>();

        inputs.add(ListNode.create(new int[]{1,2,3,4}));
        inputs.add(ListNode.create(new int[]{1,2,3,4,5}));
        inputs.add(ListNode.create(new int[]{1}));
        inputs.add(ListNode.create(new int[]{}));

        List<ListNode> expectedOutputs = new ArrayList<>();

        expectedOutputs.add(ListNode.create(new int[]{1,4,2,3}));
        expectedOutputs.add(ListNode.create(new int[]{1,5,2,4,3}));
        expectedOutputs.add(ListNode.create(new int[]{1}));
        expectedOutputs.add(ListNode.create(new int[]{}));

        for (int i=0; i < inputs.size(); i++){
            ListNode input = inputs.get(i);
            reorder(input);
            Boolean passed = ListNode.equals(input, expectedOutputs.get(i));
            System.out.printf("Test case %s - PASSED ? %s%n", i, passed);

        }
    }

    private static void reorder(ListNode head) {
        //Find the mid node by slow pointer and fast pointer approach.
        //Reverse the second half
        //merge the 2 list

        if(head ==null) return;

        ListNode midNode = findMiddleNode(head);
        // print(midNode);

        ListNode head2 = reverseList(midNode.next);

        //Detach 2 lists
        midNode.next = null;


        // print(head2);

        ListNode head3 = mergeTwoList(head, head2);
        //print(head3);


    }

    private static ListNode findMiddleNode(ListNode head) {

        if(head == null) return null;

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    private static ListNode reverseList(ListNode node) {

        if (node == null) return null;

        ListNode prev = null;
        ListNode curr = node;

        while (curr.next != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        //The last link is always broken, so we need to attach after coming out of loop
        curr.next = prev;

        return curr;
    }

    private static ListNode mergeTwoList(ListNode head1, ListNode head2) {
        ListNode newHead = head1;
        ListNode h1 = head1;
        ListNode h2 = head2;
        while (h1 != null && h2 != null) {
            ListNode temp = h1.next;
            h1.next = h2;
            h2 = h2.next;
            h1.next.next = temp;
            h1 = temp;
        }

        return newHead;
    }

    private static void print(ListNode node) {
        while (node != null) {
            System.out.printf("%s->", node.val);
            node = node.next;
        }

        System.out.printf("NULL\n");
    }
}
