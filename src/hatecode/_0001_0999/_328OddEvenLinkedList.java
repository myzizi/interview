package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : OddEvenLinkedList
 * Date : Aug, 2018
 * Description : TODO
 */
public class _328OddEvenLinkedList {
    /**
     * 328. Odd Even Linked List
Given a singly linked list, group all odd nodes together followed by the even nodes. 
Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example 1:

Input: 1->2->3->4->5->NULL
Output: 1->3->5->2->4->NULL
Example 2:

Input: 2->1->3->5->6->4->7->NULL
Output: 2->3->6->7->1->5->4->NULL
Note:

The relative order inside both the even and odd groups should remain as it was in the input.
The first node is considered odd, the second node even and so on ...
     *
     Given 1->2->3->4->5->NULL,
     return 1->3->5->2->4->NULL.

     1 -> 3    2 -> 4

     3 -> 4 -> 5 -> 6


     time : O(n);
     space : O(1);
     * @param head
     * @return
     */
    //1->2->3->4->5->NULL 
    //odd -> 1, even -> 2
    // next step: odd->1 -> 3, 1 will point to 3 instead of 2
    //            even->2->4, 2 will point to 4 instead of 3 since we use next.next
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            // even point to next next, so 
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        // we add odd.next point to evenHead. so they can be in single list now
        odd.next = evenHead;
        return head;
    }
}