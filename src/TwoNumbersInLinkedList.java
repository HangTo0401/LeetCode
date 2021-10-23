import java.util.Stack;

public class TwoNumbersInLinkedList {

    /**
     * MEDIUM
     * 2. You are given two non-empty linked lists representing two non-negative integers.
     * The digits are stored in reverse order, and each of their nodes contains a single digit.
     * Add the two numbers and return the sum as a linked list.
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     *
     * SOLUTION: We create reverseNode function to reverse 2 linked lists
     * We create 2 stacks to store the value of 2 reverse linked lists
     *
     * We init CARRY = 0 and new head is NULL
     * Then we iterate through 2 stacks and create variable SUM = CARRY then sum every node of 2 linked lists
     * Note that check the sum if it exceeds 10 then we store CARRY = 1
     * If CARRY != 0 which means 1 then we skip it looping cause we already set SUM = CARRY for next looping
     *
     * Example 1:
     * 2 -> 4 -> 3
     * 5 -> 6 -> 4
     * ===========
     * 7 -> 0 -> 8
     *
     * Input: l1 = [2,4,3], l2 = [5,6,4]
     * Output: [7,0,8]
     * Explanation: 342 + 465 = 807.
     * Then reverse 807, we get 708
     */

     // Definition for singly-linked list.
     public static class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // Inserts a new Node at front of the list
    public static ListNode insertNewNodeAtFront(ListNode head, int new_data) {
         ListNode new_node = new ListNode(new_data);
         new_node.next = head;
         head = new_node;

         return head;
    }

    public static ListNode insertNewNodeAtEnd(ListNode head, int new_data) {
        /* creates a new node */
        ListNode new_node = new ListNode(new_data);

        /* Input validation */
        ListNode cur = head;
        if (cur == null) {
            return cur;
        }

        /* Traverse from head to last node  */
        while(cur.next != null) {
            cur = cur.next;
        }

        /* Insert newNode after Tail node */
        cur.next = new_node;

        return head;
    }

    // Print all nodes in linked list
    public static void printNodes(ListNode head) {
         ListNode cur = head;

        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }

    // Reverse node in order
    public static ListNode reverseNode(ListNode listNode) {
        ListNode cur = listNode;
        ListNode prev = null;
        ListNode next = null;

        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        listNode = prev;
        return listNode;
    }

    // Add value of listNode into stack
    public static Stack<Integer> addListValToStack(ListNode listNode) {
         Stack stack = new Stack();
         ListNode cur = listNode;

         while (cur != null) {
             stack.add(cur.val);
             cur = cur.next;
         }
        return stack;
    }

    // Add two numbers in linked list using Carry
    public ListNode addTwoNumbersUsingCarry(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;

        ListNode head = null;
        int carry = 0;

        ListNode reverseHead1 = reverseNode(l1);
        ListNode reverseHead2 = reverseNode(l2);

        Stack<Integer> stack1 = addListValToStack(reverseHead1);
        Stack<Integer> stack2 = addListValToStack(reverseHead2);

        while (stack1.size() > 0 || stack2.size() > 0 || carry != 0) {
            int sum = carry;
            if (stack1.size() > 0) sum+=stack1.pop();
            if (stack2.size() > 0) sum+=stack2.pop();

            ListNode newNode = new ListNode(sum % 10);
            newNode.next = head;
            head = newNode;

            carry = sum / 10;
        }

        return reverseNode(head);
    }

    // Add two numbers in linked list without using Carry
    public ListNode addTwoNumbersWithoutCarry(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;

        ListNode head = null;

        ListNode reverseHead1 = reverseNode(l1);
        ListNode reverseHead2 = reverseNode(l2);

        Stack<Integer> stack1 = addListValToStack(reverseHead1);
        Stack<Integer> stack2 = addListValToStack(reverseHead2);
        int sum = 0;

        while (stack1.size() > 0 || stack2.size() > 0) {
            if (stack1.size() > 0) sum+=stack1.pop();
            if (stack2.size() > 0) sum+=stack2.pop();

            ListNode newNode = new ListNode(sum % 10);
            newNode.next = head;
            head = newNode;
            sum = sum / 10;
        }

        if (sum > 0) {
            ListNode newNode = new ListNode(sum);
            newNode.next = head;
            head = newNode;
        }

        return reverseNode(head);
    }

    public static void main(String[] args) {
        TwoNumbersInLinkedList twoNumbersInLinkedList = new TwoNumbersInLinkedList();

        ListNode l1 = new ListNode(2);
        l1 = insertNewNodeAtEnd(l1, 4);
        l1 = insertNewNodeAtEnd(l1, 3);
        printNodes(l1);

        System.out.print("============\n");

        ListNode l2 = new ListNode(5);
        l2 = insertNewNodeAtEnd(l2, 6);
        l2 = insertNewNodeAtEnd(l2, 4);
        printNodes(l2);

        System.out.print("============\n");

        ListNode result = null;
        result = twoNumbersInLinkedList.addTwoNumbersUsingCarry(l1, l2);
        printNodes(result);

    }
}
