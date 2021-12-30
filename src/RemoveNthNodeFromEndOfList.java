import java.util.List;

public class RemoveNthNodeFromEndOfList {
    /**
     * MEDIUM
     * 19. Remove Nth Node From End of List
     * Given the head of a linked list, remove the nth node from the end of the list and return its head.
     * Example 1:
     * Input: head = [1,2,3,4,5], n = 2
     * Output: [1,2,3,5]
     *
     * Example 2:
     * Input: head = [1], n = 1
     * Output: []
     *
     * Example 3:
     * Input: head = [1,2], n = 1
     * Output: [1]
     *
     * Constraints:
     * The number of nodes in the list is sz.
     * 1 <= sz <= 30
     * 0 <= Node.val <= 100
     * 1 <= n <= sz
     *
     * Follow up: Could you do this in one pass?
     * */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * Approach 1: Using two loops
     * */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return head;

        ListNode secondPointer = head;
        int count = 1;

        while (secondPointer != null && count < n) {
            count++;
            secondPointer = secondPointer.next;
        }

        if(secondPointer == null) {
            return head;
        }

        ListNode firstPointer = head;
        ListNode prev = null;
        while (secondPointer.next != null) {
            prev = firstPointer;
            secondPointer = secondPointer.next;
            firstPointer = firstPointer.next;
        }

        // Remove node at Nth node
        prev.next = firstPointer.next;
        return head;
    }

    public static void main(String[] args) {
        RemoveNthNodeFromEndOfList solution = new RemoveNthNodeFromEndOfList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        solution.removeNthFromEnd(head, 2);
    }
}
