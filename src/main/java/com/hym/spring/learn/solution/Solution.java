package com.hym.spring.learn.solution;

import java.util.HashMap;
import java.util.Map;
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


    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     * <p>
     *  
     * <p>
     * 示例:
     * <p>
     * 给定 nums = [2, 7, 11, 15], target = 9
     * <p>
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     */
    public int[] twoSum(int[] nums, int target) {

        int[] answer = new int[2];

        one:
        for (int i = 0; i < nums.length - 1; i++) {
            for (int l = i + 1; l < nums.length; l++) {
                if (nums[i] + nums[l] == target) {
                    answer[0] = i;
                    answer[1] = l;
                    break one;
                }
            }
        }
        return answer;
    }

    public int[] twoSum1(int[] nums, int target) {
        int[] answer = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer index = map.get(target - nums[i]);
            if (null != index) {
                answer[0] = i;
                answer[1] = index;
            } else {
                map.put(nums[i], i);
            }
        }

        return answer;

    }

    /**
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 123
     * 输出: 321
     *  示例 2:
     * <p>
     * 输入: -123
     * 输出: -321
     * 示例 3:
     * <p>
     * 输入: 120
     * 输出: 21
     * 注意:
     * <p>
     * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
     *
     * @param x
     * @return
     */
    public int reverse(int x) {

        return 0;
    }

}
