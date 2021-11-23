import java.util.Stack;

public class SumTwoNumbersInLinkedList {

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

    // Inserts a new Node at the end of the list
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

    /**
     * 1. The second solution is adding two numbers in linked list using Carry
     * We create reverseNode function to reverse 2 linked lists
     * We create 2 stacks to store the value of 2 reverse linked lists
     *
     * @param l1
     * @param l2
     * @return ListNode
     * */
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

    /**
     * 2. The second solution is adding two numbers in linked list without using Carry
     * We create reverseNode function to reverse 2 linked lists
     * We create 2 stacks to store the value of 2 reverse linked lists
     *
     * @param l1
     * @param l2
     * @return ListNode
     * */
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

    /**
     * 3. The third solution is Elementary Math
     * This is answered solution of this problem of Leetcode
     *
     * The pseudocode is as following:
     * •	Initialize current node to dummy head of the returning list.
     * •	Initialize carry to 0.
     * •	Initialize p and q to head of l1 and l2 respectively.
     * •	Loop through lists l1 and l2 until you reach both ends.
     *      o	Set x to node p's value. If p has reached the end of l1, set to 00.
     *      o	Set y to node q's value. If q has reached the end of l2, set to 00.
     *      o	Set sum = x + y + carry
     *      o	Update carry = sum / 10
     *      o	Create a new node with the digit value of (sum mod 10) and set it to current node's next, then set current to current.next.
     *      o	Set p = p.next and q = q.next.
     * •	Check if carry = 1, if so append a new node with digit 1 to the returning list.
     * •	Return dummy head's next node.
     *
     * Complexity Analysis
     *
     * Time complexity : O(\max(m, n))O(max(m,n)). Assume that mm and nn represents the length of l1l1 and l2l2 respectively,
     * the algorithm above iterates at most \max(m, n)max(m,n) times.
     * Space complexity : O(\max(m, n))O(max(m,n)). The length of the new list is at most \max(m,n) + 1max(m,n)+1.
     *
     * Follow up
     * What if the the digits in the linked list are stored in non-reversed order? For example:
     * (3→4→2) + (4→6→5) = 8→0→7
     * */
    public ListNode sumLinkedList(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);

        // Initialize current node to dummy head of the returning list.
        ListNode current = dummyHead;

        // Initialize carry to 0.
        int carry = 0;

        // Initialize p and q to head of l1 and l2 respectively.
        ListNode p = l1;
        ListNode q = l2;

        while (p != null && q != null) {
            int x = (p != null) ? (int) p.val : 0;
            int y = (q != null) ? (int) q.val : 0;
            int sum = x + y + carry;
            carry = sum / 10;

            int digit = sum % 10;
            ListNode newNode = new ListNode(digit);
            current.next = newNode;
            current = current.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }

        if (carry > 0) {
            current.next = new ListNode(carry);
        }
        return dummyHead.next;//get rid of dummyHead 0
    }

    public static void main(String[] args) {
        SumTwoNumbersInLinkedList solution = new SumTwoNumbersInLinkedList();

        System.out.print("======USING CARRY======\n");
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
        result = solution.addTwoNumbersUsingCarry(l1, l2);
        printNodes(result);

        System.out.print("======WITHOUT CARRY======\n");
        ListNode l3 = new ListNode(2);
        l3 = insertNewNodeAtEnd(l3, 4);
        l3 = insertNewNodeAtEnd(l3, 3);
        printNodes(l3);

        System.out.print("============\n");

        ListNode l4 = new ListNode(5);
        l4 = insertNewNodeAtEnd(l4, 6);
        l4 = insertNewNodeAtEnd(l4, 4);
        printNodes(l4);

        System.out.print("============\n");

        ListNode result1 = null;
        result1 = solution.addTwoNumbersWithoutCarry(l3, l4);
        printNodes(result1);

        System.out.print("======SUM WITH CARRY======\n");
        ListNode l5 = new ListNode(2);
        l5 = insertNewNodeAtEnd(l5, 4);
        l5 = insertNewNodeAtEnd(l5, 3);
        printNodes(l5);

        System.out.print("============\n");

        ListNode l6 = new ListNode(5);
        l6 = insertNewNodeAtEnd(l6, 6);
        l6 = insertNewNodeAtEnd(l6, 4);
        printNodes(l6);

        System.out.print("============\n");

        ListNode result2 = null;
        result2 = solution.sumLinkedList(l5, l6);
        printNodes(result2);
    }
}
