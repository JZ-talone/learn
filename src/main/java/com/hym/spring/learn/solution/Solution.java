package com.hym.spring.learn.solution;

import java.util.*;

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


    public static void main(String[] args) {
        ListNode a = new ListNode(4, null);
        ListNode b = new ListNode(6, a);
        ListNode c = new ListNode(2, b);
        ListNode d = new ListNode(5, null);
        ListNode e = new ListNode(1, d);
        System.out.println(new Solution().getIntersectionNode(c, e));
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
        if (null == needle || needle.length() == 0) {
            return 0;
        }
        if (haystack.length() < needle.length()) {
            return -1;
        }

        char first = needle.charAt(0);
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystack.charAt(i) == first && haystack.startsWith(needle, i)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * <p>
     * 你可以假设数组中无重复元素。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,3,5,6], 5
     * 输出: 2
     * 示例 2:
     * <p>
     * 输入: [1,3,5,6], 2
     * 输出: 1
     * 示例 3:
     * <p>
     * 输入: [1,3,5,6], 7
     * 输出: 4
     * 示例 4:
     * <p>
     * 输入: [1,3,5,6], 0
     * 输出: 0
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }

        for (int i = 0; i < nums.length; i++) {
            if (target <= nums[i]) {
                return i;
            }
        }

        return nums.length;
    }

    /**
     * 给定一个正整数 n ，输出外观数列的第 n 项。
     * <p>
     * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
     * <p>
     * 你可以将其视作是由递归公式定义的数字字符串序列：
     * <p>
     * countAndSay(1) = "1"
     * countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另一个数字字符串。
     * 前五项如下：
     * <p>
     * 1.     1
     * 2.     11
     * 3.     21
     * 4.     1211
     * 5.     111221
     * 第一项是数字 1
     * 描述前一项，这个数是 1 即 “ 一 个 1 ”，记作 "11"
     * 描述前一项，这个数是 11 即 “ 二 个 1 ” ，记作 "21"
     * 描述前一项，这个数是 21 即 “ 一 个 2 + 一 个 1 ” ，记作 "1211"
     * 描述前一项，这个数是 1211 即 “ 一 个 1 + 一 个 2 + 二 个 1 ” ，记作 "111221"
     * 要 描述 一个数字字符串，首先要将字符串分割为 最小 数量的组，每个组都由连续的最多 相同字符 组成。然后对于每个组，先描述字符的数量，然后描述字符，形成一个描述组。要将描述转换为数字字符串，先将每组中的字符数量用数字替换，再将所有描述组连接起来。
     * <p>
     * 输入：n = 1
     * 输出："1"
     * 解释：这是一个基本样例。
     * 示例 2：
     * <p>
     * 输入：n = 4
     * 输出："1211"
     * 解释：
     * countAndSay(1) = "1"
     * countAndSay(2) = 读 "1" = 一 个 1 = "11"
     * countAndSay(3) = 读 "11" = 二 个 1 = "21"
     * countAndSay(4) = 读 "21" = 一 个 2 + 一 个 1 = "12" + "11" = "1211"
     *
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        return null;
    }

    /**
     * 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
     * <p>
     * 如果不存在最后一个单词，请返回 0 。
     * <p>
     * 说明：一个单词是指仅由字母组成、不包含任何空格字符的 最大子字符串。
     * <p>
     *  
     * <p>
     * 示例:
     * <p>
     * 输入: "Hello World"
     * 输出: 5
     *
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }

        Integer lastIndex = s.length() - 1;
        Integer i;
        for (i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (i.equals(lastIndex)) {
                    lastIndex--;
                } else {
                    return lastIndex - i;
                }
            }
        }

        return lastIndex - i;

    }

    /**
     * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
     * <p>
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * <p>
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：digits = [1,2,3]
     * 输出：[1,2,4]
     * 解释：输入数组表示数字 123。
     * 示例 2：
     * <p>
     * 输入：digits = [4,3,2,1]
     * 输出：[4,3,2,2]
     * 解释：输入数组表示数字 4321。
     * 示例 3：
     * <p>
     * 输入：digits = [0]
     * 输出：[1]
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        boolean flag = false;
        for (int i = digits.length - 1; i >= 0; i--) {
            int x = digits[i] + 1;
            if (x / 10 > 0) {
                digits[i] = x % 10;
                if (i == 0) {
                    flag = true;
                }
            } else {
                digits[i] = x;
                return digits;
            }
        }

        if (flag) {
            int[] x = new int[digits.length + 1];
            x[0] = 1;

            return x;
        }
        return null;
    }

    /**
     * 给你两个二进制字符串，返回它们的和（用二进制表示）。
     * <p>
     * 输入为 非空 字符串且只包含数字 1 和 0。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: a = "11", b = "1"
     * 输出: "100"
     * 示例 2:
     * <p>
     * 输入: a = "1010", b = "1011"
     * 输出: "10101"
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        return null;
    }

    /**
     * 实现 int sqrt(int x) 函数。
     * <p>
     * 计算并返回 x 的平方根，其中 x 是非负整数。
     * <p>
     * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 4
     * 输出: 2
     * 示例 2:
     * <p>
     * 输入: 8
     * 输出: 2
     * 说明: 8 的平方根是 2.82842...,
     *      由于返回类型是整数，小数部分将被舍去。
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    public int mySqrt1(int x) {
        if (x == 0) {
            return 0;
        }

        double C = x, x0 = x;
        while (true) {
            double xi = 0.5 * (x0 + C / x0);
            if (Math.abs(x0 - xi) < 1e-7) {
                break;
            }
            x0 = xi;
        }
        return (int) x0;
    }

    /**
     * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 1->1->2
     * 输出: 1->2
     * 示例 2:
     * <p>
     * 输入: 1->1->2->3->3
     * 输出: 1->2->3
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode before = head;
        ListNode current = head.next;
        while (null != current) {
            if (current.val == before.val) {
                before.next = current.next;
                current = current.next;
            } else {
                before = current;
                current = current.next;
            }
        }
        return head;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if ((null != p && null == q) || (null != q && null == p)) {
            return false;
        }
        if (null == p && null == q) {
            return true;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public boolean isSymmetric(TreeNode root) {
        if (null == root) {
            return true;
        }
        List<Integer> left = cengcebl(root.left, true);
        List<Integer> right = cengcebl(root.right, false);
        return left.equals(right);
    }

    public List<Integer> cengcebl(TreeNode root, boolean leftFirst) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode treeNode = stack.pop();
            if (null != treeNode) {
                list.add(treeNode.val);
                stack.push(leftFirst ? treeNode.right : treeNode.left);
                stack.push(leftFirst ? treeNode.left : treeNode.right);
            } else {
                list.add(null);
            }
        }
        return list;
    }

    public int maxDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     * <p>
     * 例如：
     * 给定二叉树 [3,9,20,null,null,15,7],
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回其自底向上的层次遍历为：
     * <p>
     * [
     * [15,7],
     * [9,20],
     * [3]
     * ]
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (null == root) {
            return lists;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> levelList = new ArrayList<>();
            Integer size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                levelList.add(treeNode.val);
                if (null != treeNode.left) {
                    queue.offer(treeNode.left);
                }
                if (null != treeNode.right) {
                    queue.offer(treeNode.right);
                }
            }
            lists.add(0, levelList);
        }

        return lists;
    }

    /**
     * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
     * <p>
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     * <p>
     * 示例:
     * <p>
     * 给定有序数组: [-10,-3,0,5,9],
     * <p>
     * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
     * <p>
     * 0
     * / \
     * -3   9
     * /   /
     * -10  5
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        Integer index = nums.length / 2;
        TreeNode root = new TreeNode(nums[index]);
        if (index != 0) {
            root.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, index));
        }
        if (index != nums.length - 1) {
            root.right = sortedArrayToBST(Arrays.copyOfRange(nums, index + 1, nums.length));
        }
        return root;
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
    public int[] twoSum2(int[] nums, int target) {

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

    public boolean isBalanced(TreeNode root) {
        if (null == root) {
            return true;
        }
        Integer lengthl = getLength(root.left);
        Integer lengthr = getLength(root.right);
        return lengthl != -1 && lengthr != -1 && Math.abs(lengthl - lengthr) <= 1;
    }

    private Integer getLength(TreeNode node) {
        if (null == node) {
            return 0;
        }
        Integer lengthl = getLength(node.left);
        Integer lengthr = getLength(node.right);
        if (lengthl == -1 || lengthr == -1 || Math.abs(lengthl - lengthr) > 1) {
            return -1;
        }

        return 1 + Math.max(lengthl, lengthr);
    }

    /**
     * bad balanced
     *
     * @param root
     * @return
     */
    public boolean isBalancedbad(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalancedbad(root.left) && isBalancedbad(root.right);
        }
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(height(root.left), height(root.right)) + 1;
        }
    }

    /**
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：2
     * 示例 2：
     * <p>
     * 输入：root = [2,null,3,null,4,null,5,null,6]
     * 输出：5
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int leftd = minDepth(root.left);
        int rightd = minDepth(root.right);
        if (leftd <= 0 && rightd <= 0) {
            return 1;
        }
        return Math.min(leftd <= 0 ? Integer.MAX_VALUE : leftd, rightd <= 0 ? Integer.MAX_VALUE : rightd) + 1;
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        int curSum = 0;

        boolean find = findSum(root, curSum, sum);
        return find;
    }

    private boolean findSum(TreeNode root, int curSum, int sum) {
        if (null == root) {
            return false;
        }

        curSum = curSum + root.val;
        if (root.left == null && root.right == null) {
            return curSum == sum;
        }

        boolean lfind = findSum(root.left, curSum, sum);
        boolean rfind = findSum(root.right, curSum, sum);
        return lfind || rfind;
    }

    /**
     * yanghui sanjiao
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            for (int l = 0; l <= i; l++) {
                if (l == 0 || l == i) {
                    list.add(1);
                } else {
                    list.add(lists.get(i - 1).get(l - 1) + lists.get(i - 1).get(l));
                }
            }
            lists.add(list);
        }
        return lists;
    }

    public List<Integer> getRow(int rowIndex) {
        return generate(rowIndex + 1).get(rowIndex);
    }

    public List<Integer> getRow2(int N) {
        List<Integer> res = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            res.add(1);
            for (int j = i - 1; j > 0; j--) {
                res.set(j, res.get(j) + res.get(j - 1));
            }
        }
        return res;
    }

    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * <p>
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * <p>
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: [7,1,5,3,6,4]
     * 输出: 7
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
     * 示例 2:
     * <p>
     * 输入: [1,2,3,4,5]
     * 输出: 4
     * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
     * 示例 3:
     * <p>
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        Integer maxProfit = 0;
        Integer minindex = null;
        for (int i = 0; i < prices.length; i++) {
            if (minindex == null) {
                if (i + 1 < prices.length && prices[i + 1] < prices[i]) {
                    continue;
                }

                if (i - 1 >= 0 && prices[i - 1] < prices[i]) {
                    continue;
                }

                minindex = i;
            } else {
                if (i + 1 < prices.length && prices[i + 1] > prices[i]) {
                    continue;
                }

                if (i - 1 >= 0 && prices[i - 1] > prices[i]) {
                    continue;
                }

                maxProfit += (prices[i] - prices[minindex]);
                minindex = null;
            }

        }
        return maxProfit;
    }

    public int maxProfit2dp2(int[] prices) {
        int n = prices.length;
        int dp0 = 0, dp1 = -prices[0];
        for (int i = 1; i < n; ++i) {
            int newDp0 = Math.max(dp0, dp1 + prices[i]);
            int newDp1 = Math.max(dp1, dp0 - prices[i]);
            dp0 = newDp0;
            dp1 = newDp1;
        }
        return dp0;
    }

    public int maxProfit2tx(int[] prices) {
        int ans = 0;
        int n = prices.length;
        for (int i = 1; i < n; ++i) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }

    public int maxProfit2dp(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    public int maxProfit1Self(int[] prices) {
        Integer maxProfit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (i + 1 < prices.length && prices[i + 1] < prices[i]) {
                continue;
            }

            if (i - 1 >= 0 && prices[i - 1] < prices[i]) {
                continue;
            }

            for (int j = prices.length - 1; j > i; j--) {
                maxProfit = Math.max(prices[j] - prices[i], maxProfit);
            }
        }
        return maxProfit;
    }

    public int maxProfit(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) {
                minprice = prices[i];
            } else if (prices[i] - minprice > maxprofit) {
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }

    /**
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     * <p>
     * 说明：本题中，我们将空字符串定义为有效的回文串。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "A man, a plan, a canal: Panama"
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: "race a car"
     * 输出: false
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                ++left;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                --right;
            }
            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                ++left;
                --right;
            }
        }
        return true;
    }

    /**
     * [4,1,8,4,5]
     * [5,6,1,8,4,5]
     *
     * @param headA
     * @param headB
     * @return 创建两个指针 pApA 和 pBpB，分别初始化为链表 A 和 B 的头结点。然后让它们向后逐结点遍历。
     * 当 pApA 到达链表的尾部时，将它重定位到链表 B 的头结点 (你没看错，就是链表 B); 类似的，当 pBpB 到达链表的尾部时，将它重定位到链表 A 的头结点。
     * 若在某一时刻 pApA 和 pBpB 相遇，则 pApA/pBpB 为相交结点。
     * 想弄清楚为什么这样可行, 可以考虑以下两个链表: A={1,3,5,7,9,11} 和 B={2,4,9,11}，相交于结点 9。 由于 B.length (=4) < A.length (=6)，pBpB 比 pApA 少经过 2 个结点，会先到达尾部。将 pBpB 重定向到 A 的头结点，pApA 重定向到 B 的头结点后，pBpB 要比 pApA 多走 2 个结点。因此，它们会同时到达交点。
     * 如果两个链表存在相交，它们末尾的结点必然相同。因此当 pApA/pBpB 到达链表结尾时，记录下链表 A/B 对应的元素。若最后元素不相同，则两个链表不相交。
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode curA = headA;
        ListNode curB = headB;
        while (curA != curB) {
            if (curA != null) {
                curA = curA.next;
            } else {
                curA = headB;
            }
            if (curB != null) {
                curB = curB.next;
            } else {
                curB = headA;
            }
        }
        return curA;
    }

    /**
     * 0
     * [2,6,4]
     * [1,5]
     *
     * @param headA
     * @param headB
     * @return
     */

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    public int[] twoSum(int[] numbers, int target) {
        Integer lowIndex = 0;
        Integer highIndex = numbers.length - 1;
        while (lowIndex < highIndex) {
            if (numbers[lowIndex] + numbers[highIndex] == target) {
                return new int[]{lowIndex + 1, highIndex + 1};
            } else if (numbers[lowIndex] + numbers[highIndex] > target) {
                highIndex--;
            } else {
                lowIndex++;
            }
        }
        return new int[]{-1, -1};
    }

    public int[] twoSumEFCZ(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; ++i) {
            int low = i + 1, high = numbers.length - 1;
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                if (numbers[mid] == target - numbers[i]) {
                    return new int[]{i + 1, mid + 1};
                } else if (numbers[mid] > target - numbers[i]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * "abc"
     * "ahbgdc"
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        if (null == s || s.length() == 0) {
            return true;
        }
        char[] chars = s.toCharArray();
        Integer tstart = 0;
        one:
        for (int i = 0; i < s.length(); i++) {

            for (int l = tstart; l < t.length(); l++) {
                if (tstart >= t.length()) {
                    return false;
                }
                if (t.charAt(l) == chars[i]) {
                    tstart = l + 1;
                    continue one;
                }
            }
            return false;
        }
        return true;
    }

    /**
     * "aaaaaa"
     * "bbaaaa"
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequencedoublepoint(String s, String t) {
        int a = s.length();
        int b = t.length();
        int i = 0;
        int l = 0;
        while (i < a && l < b) {
            if (s.charAt(i) == t.charAt(l)) {
                i++;
                l++;
            } else {
                l++;
            }
        }
        return i == s.length();
    }

    public boolean isSubsequencedp(String s, String t) {
        int n = s.length(), m = t.length();

        int[][] f = new int[m + 1][26];
        for (int i = 0; i < 26; i++) {
            f[m][i] = m;
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                if (t.charAt(i) == j + 'a')
                    f[i][j] = i;
                else
                    f[i][j] = f[i + 1][j];
            }
        }
        int add = 0;
        for (int i = 0; i < n; i++) {
            if (f[add][s.charAt(i) - 'a'] == m) {
                return false;
            }
            add = f[add][s.charAt(i) - 'a'] + 1;
        }
        return true;
    }

    /**
     * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
     * <p>
     * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，
     * 我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
     * <p>
     *  
     * 示例 1:
     * <p>
     * 输入: g = [1,2,3], s = [1,1]
     * 输出: 1
     * 解释:
     * 你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
     * 虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
     * 所以你应该输出1。
     *
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        if (g.length == 0 || s.length == 0) {
            return 0;
        }
        Integer gindex = 0;
        Integer sindex = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        while (gindex < g.length && sindex < s.length) {
            if (g[gindex] <= s[sindex]) {
                gindex++;
            }
            sindex++;
        }
        return gindex;
    }

    /**
     * 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。
     * <p>
     * 顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
     * <p>
     * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
     * <p>
     * 注意，一开始你手头没有任何零钱。
     * <p>
     * 如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
     * <p>
     * 示例 1：
     * <p>
     * 输入：[5,5,5,10,20]
     * 输出：true
     * 解释：
     * 前 3 位顾客那里，我们按顺序收取 3 张 5 美元的钞票。
     * 第 4 位顾客那里，我们收取一张 10 美元的钞票，并返还 5 美元。
     * 第 5 位顾客那里，我们找还一张 10 美元的钞票和一张 5 美元的钞票。
     * 由于所有客户都得到了正确的找零，所以我们输出 true。
     *
     * @param bills
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                if (five == 0) {
                    return false;
                } else {
                    five--;
                    ten++;
                }
            } else {
                if (ten > 0 && five > 0) {
                    ten--;
                    five--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
     * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * <p>
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：[1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
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
