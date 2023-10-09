package interviews.tech.leethcode.models;


//Definition for singly-linked list.
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static boolean equals(ListNode head1, ListNode head2){


        while(head1!=null && head2!=null){
            if(head1.val!=head2.val) return false;

            head1 = head1.next;
            head2 = head2.next;
        }

        return head1 == null && head2==null;
    }

    public static ListNode create(int[] data) {
        ListNode head = null;
        ListNode prev = null;
        for (int i = 0; i < data.length; i++) {
            ListNode node = new ListNode(data[i], null);

            if (prev != null) {
                prev.next = node;
                prev = node;
            }

            if (head == null) {
                head = node;
                prev = node;
            }
        }
        return head;
    }

    public static void print(ListNode head){
        while (head!=null){
            System.out.print(head.val + " --> " );
            head = head.next;
        }
        System.out.println("NULL" );
    }
}
