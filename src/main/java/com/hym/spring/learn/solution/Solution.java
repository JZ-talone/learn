package com.hym.spring.learn.solution;

import java.util.Arrays;
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

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isValid("([)]"));
    }

    /**
     * @param x
     * @return
     */
    public int reverse(int x) {
        Long answer = 0L;
        while (x != 0) {
            answer = answer * 10 + x % 10;
            x = x / 10;
        }
        if (answer > Integer.MAX_VALUE || answer < Integer.MIN_VALUE) {
            return 0;
        } else {
            return Integer.parseInt(String.valueOf(answer));
        }

    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        return reverse(x) == x;

    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * <p>
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "()"
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: "()[]{}"
     * 输出: true
     * 示例 3:
     * <p>
     * 输入: "(]"
     * 输出: false
     * <p>
     * 示例 4:
     * <p>
     * 输入: "([)]"
     * 输出: false
     * 示例 5:
     * <p>
     * 输入: "{[]}"
     * 输出: true
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Integer length = s.length();
        if (length % 2 == 1) return false;
        Map<Character, Character> maps = new HashMap<>();
        maps.put(')', '(');
        maps.put(']', '[');
        maps.put('}', '{');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            char cur = s.charAt(i);
            if (maps.containsKey(cur)) {
                if (stack.isEmpty() || !stack.peek().equals(maps.get(cur))) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(cur);
            }
        }
        return stack.isEmpty();
    }

    /**
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     * <p>
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * <p>
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 给定 nums = [3,2,2,3], val = 3,
     * <p>
     * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
     * <p>
     * 你不需要考虑数组中超出新长度后面的元素。
     * 示例 2:
     * <p>
     * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
     * <p>
     * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
     * <p>
     * 注意这五个元素可为任意顺序。
     * <p>
     * 你不需要考虑数组中超出新长度后面的元素。
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        Integer length = 0;
        if (nums.length == 0) {
            return length;
        }
        Integer curIndex = 0;
        Integer bacIndex = nums.length - 1;

        do {
            if (nums[curIndex] == val) {
                Integer tmp = nums[bacIndex];
                nums[bacIndex] = nums[curIndex];
                nums[curIndex] = tmp;
                bacIndex--;
            } else {
                curIndex++;
            }
        } while (curIndex <= bacIndex);
        return curIndex;
    }

    /**
     * 实现 strStr() 函数。
     * <p>
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
     * <p>
     * 示例 1:
     * <p>
     * 输入: haystack = "hello", needle = "ll"
     * 输出: 2
     * 示例 2:
     * <p>
     * 输入: haystack = "aaaaa", needle = "bba"
     * 输出: -1
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        return 0;
    }

    public String longestCommonPrefix(String[] strs) {
        String answer = "";
        if (strs.length == 0) {
            return answer;
        }
        char[] anschar = strs[0].toCharArray();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            if (chars.length == 0) {
                return "";
            }
            if (chars.length < anschar.length) {
                anschar = String.valueOf(anschar).substring(0, chars.length).toCharArray();
            }
            two:
            for (int i = 0; i < chars.length; i++) {
                if (i >= anschar.length) {
                    break two;
                }
                if (chars[i] != anschar[i]) {
                    if (i == 0) {
                        return "";
                    } else {
                        anschar = Arrays.copyOf(anschar, i);
                        break two;
                    }
                }
            }
        }
        return String.valueOf(anschar);
    }
}
