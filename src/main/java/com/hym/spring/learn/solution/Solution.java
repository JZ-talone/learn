package com.hym.spring.learn.solution;

import java.util.Stack;

/**
 * ${DESCRIPTION}
 *
 * @author huangyiming
 * @since 2020/9/16 11:09
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode three = new ListNode(3, null);
        ListNode two = new ListNode(2, three);
        ListNode one = new ListNode(1, two);
        solution.printReserveOne(one);
        solution.printReserveTwo(one);
    }

    /**
     * @param head: n
     * @return: The new head of reversed linked list.
     */
    public void printReserveOne(ListNode head) {
        if (null == head) {
            return;
        }
        if (null != head.next) {
            printReserveOne(head.next);
        }
        System.out.println(head.val);
    }

    public void printReserveTwo(ListNode head) {
        ListNode cur = head;
        Stack<ListNode> stack = new Stack<>();
        while (null != cur) {
            stack.push(cur);
            cur = cur.next;
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop().val);
        }
    }


}
