package com.hym.spring.learn.solution;

import java.util.*;

/**
 * ${DESCRIPTION}
 *
 * @author huangyiming
 * @since 2020/9/16 11:09
 */
public class Solution {

    int maxD = 0;
    private TreeNode ans = null;

    /**
     * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
     * <p>
     * 输入：[1,12,-5,-6,50,3], k = 4
     * 输出：12.75
     * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
     *
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverage(int[] nums, int k) {
        if (nums.length < k) {
            return 0D;
        }
        Double maxAverage = null;
        Integer currentStartIndex = 0;
        while (currentStartIndex <= nums.length - k) {
            if (null == maxAverage) {
                maxAverage = getAverage(nums, k, currentStartIndex);
                currentStartIndex++;
                continue;
            }
            if (nums[currentStartIndex + k - 1] <= nums[currentStartIndex - 1]) {
                currentStartIndex++;
                continue;
            } else {
                Double curAverage = getAverage(nums, k, currentStartIndex);
                if (curAverage > maxAverage) {
                    maxAverage = curAverage;
                }
                currentStartIndex++;
                continue;
            }
        }
        return maxAverage;
    }

    private Double getAverage(int[] nums, int k, Integer currentStartIndex) {
        Double x = 0D;
        for (int i = 0; i < k; i++) {
            x += nums[currentStartIndex + i];
        }
        return x / k;
    }

    public double findMaxAverage2(int[] nums, int k) {
        int sum = 0;
        int n = nums.length;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int maxSum = sum;
        for (int i = k; i < n; i++) {
            sum = sum - nums[i - k] + nums[i];
            maxSum = Math.max(maxSum, sum);
        }
        return 1.0 * maxSum / k;
    }

    /**
     * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
     * <p>
     * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
     * <p>
     * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
     * <p>
     * 返回矩阵中 省份 的数量
     * <p>
     * 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
     * 输出：2
     * <p>
     * 输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
     * 输出：3
     * <p>
     * [[1,0,0,1],[0,1,1,0],[0,1,1,1],[1,0,1,1]]
     *
     * @param isConnected
     * @return
     */
    public int findCircleNum(int[][] isConnected) {
        Set<Set> list = new HashSet<>();
        for (int[] ints : isConnected) {
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < ints.length; i++) {
                if (ints[i] == 1) {
                    set.add(i);
                }
            }
            list.add(set);
        }
        return list.size() == 0 ? isConnected.length : list.size();
    }

    /**
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     * <p>
     * 注意：答案中不可以包含重复的三元组。
     * <p>
     *  
     * <p>
     * 示例：
     * <p>
     * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     * <p>
     * 满足要求的三元组集合为：
     * [
     * [-1, 0, 1],
     * [-1, -1, 2]
     * ]
     * <p>
     * 解法 排序+双指针 时间复杂度o（n2） 空间复杂度o（n）
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    /**
     * 在一个由小写字母构成的字符串 s 中，包含由一些连续的相同字符所构成的分组。
     * <p>
     * 例如，在字符串 s = "abbxxxxzyy" 中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。
     * <p>
     * 分组可以用区间 [start, end] 表示，其中 start 和 end 分别表示该分组的起始和终止位置的下标。上例中的 "xxxx" 分组用区间表示为 [3,6] 。
     * <p>
     * 我们称所有包含大于或等于三个连续字符的分组为 较大分组 。
     * <p>
     * 找到每一个 较大分组 的区间，按起始位置下标递增顺序排序后，返回结果。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "abbxxxxzzy"
     * 输出：[[3,6]]
     * 解释："xxxx" 是一个起始于 3 且终止于 6 的较大分组。
     *
     * @param s
     * @return
     */
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> ans = new ArrayList<>();
        Integer lowIndex = 0;
        Integer fastIndex = 0;
        while (fastIndex < s.length()) {
            if (s.charAt(fastIndex) == s.charAt(lowIndex)) {
                fastIndex++;
            } else {
                if (fastIndex - lowIndex >= 3) {
                    List<Integer> inner = new ArrayList<>();
                    inner.add(lowIndex);
                    inner.add(fastIndex - 1);
                    ans.add(inner);
                }
                lowIndex = fastIndex;
                fastIndex++;
            }
        }
        if (fastIndex - lowIndex >= 3) {
            List<Integer> inner = new ArrayList<>();
            inner.add(lowIndex);
            inner.add(fastIndex - 1);
            ans.add(inner);
        }
        return ans;
    }

    /**
     * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
     * 输出: 3
     * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root, p, q);
        return this.ans;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            ans = root;
        }
        return lson || rson || (root.val == p.val || root.val == q.val);
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
     * 回文链表
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (null == head || head.next == null) {
            return true;
        }

        // 找到前半部分链表
        ListNode halfNode = getHalfNode(head);

        // 翻转后半部分链表
        ListNode revertListNode = revertNode(halfNode.next);

        // head与revertListNode对比
        ListNode cur = head;
        ListNode cur2 = revertListNode;
        while (cur != null && cur2 != null) {
            if (cur.val != cur2.val) {
                return false;
            }
            cur = cur.next;
            cur2 = cur2.next;
        }
        revertNode(revertListNode);

        return true;
    }

    private ListNode revertNode(ListNode next) {
        ListNode pre = null;
        ListNode cur = next;
        while (cur != null) {
            ListNode curNext = cur.next;
            cur.next = pre;
            pre = cur;
            cur = curNext;
        }
        return pre;
    }

    /**
     * 偷窃第 k 间房屋，那么就不能偷窃第 k−1 间房屋，偷窃总金额为前 k−2 间房屋的最高总金额与第 kk 间房屋的金额之和。
     * 不偷窃第 k 间房屋，偷窃总金额为前 k−1 间房屋的最高总金额。
     * <p>
     * 在两个选项中选择偷窃总金额较大的选项，该选项对应的偷窃总金额即为前 k 间房屋能偷窃到的最高总金额。
     * dp[i]=max(dp[i−2]+nums[i],dp[i−1])
     */

    private ListNode getHalfNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 小偷问题  2 1 1 2      4
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int dpk2 = nums[0];
        int dpk1 = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int dp = Math.max(dpk2 + nums[i], dpk1);
            dpk2 = dpk1;
            dpk1 = dp;
        }
        return Math.max(dpk2, dpk1);
    }

    /**
     * 删除倒数x个节点  [1,2,3,4,5]
     * 2
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode rever = revertNode(head);
        if (n == 1) {
            rever = rever.next;
        } else {
            ListNode pre = null;
            ListNode cur = rever;
            Integer i = 1;
            while (i <= n) {
                if (i < n) {
                    pre = cur;
                    cur = cur.next;
                } else {
                    pre.next = cur.next;
                }
                i++;
            }
        }
        return revertNode(rever);
    }

    public ListNode gene(int[] nums) {
        ListNode head = null;
        ListNode pre = null;
        for (int num : nums) {
            ListNode cur = new ListNode(num, null);
            if (null != pre) {
                pre.next = cur;
            } else {
                head = cur;
            }
            pre = cur;
        }
        return head;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    /**
     * 翻转二叉树
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode tmp = invertTree(root.right);
        root.right = invertTree(root.left);
        root.left = tmp;
        return root;
    }

    /**
     * 诺0
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        Integer indexA = 0;
        Integer indexB = null;
        while (indexA < nums.length) {
            if (nums[indexA] == 0) {
                if (null == indexB) {
                    indexB = indexA;
                }

                indexA++;
            } else {
                if (indexB != null) {
                    Integer tmp = nums[indexA];
                    nums[indexA] = nums[indexB];
                    nums[indexB] = tmp;

                    indexB++;
                    indexA++;
                } else {
                    indexA++;
                }
            }

        }
    }

    /**
     * 消失的数字
     * 输入:
     * [4,3,2,7,8,2,3,1]
     * <p>
     * 输出:
     * [5,6]
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int newIndex = Math.abs(nums[i]) - 1;
            if (nums[newIndex] > 0) {
                nums[newIndex] *= -1;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                ans.add(i + 1);
            }
        }
        return ans;
    }

    /**
     * 二叉树直径
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {

        deep(root);
        return maxD;
    }

    private int deep(TreeNode left) {
        if (null == left) {
            return 0;
        }
        int ld = deep(left.left);
        int rd = deep(left.right);
        maxD = Math.max(ld + rd, maxD);
        return Math.max(ld, rd) + 1;
    }

    /**
     * 合并二叉树
     *
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }

        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        t1.val += t2.val;
        return t1;
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

    /**
     * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     * <p>
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
     * <p>
     * 说明：不允许修改给定的链表。
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur1 = dummy;
        ListNode cur2 = dummy;
        if (null == cur2.next || null == cur2.next || null == cur2.next.next) {
            return null;
        }
        do {
            cur1 = cur1.next;
            cur2 = cur2.next.next;
            if (null == cur2.next || null == cur2.next || null == cur2.next.next) {
                return null;
            }
        } while (cur1 != cur2);

        cur1 = dummy;
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        return cur1;
    }

    public int numTrees(int n) {
        if (n <= 0) {
            return 0;
        }
        List<Integer> anss = new ArrayList<>();
        anss.add(1);
        anss.add(1);
        anss.add(2);
        if (n <= 2) {
            return anss.get(n);
        }
        for (int i = 3; i <= n; i++) {
            int roundTime = i / 2 + (i % 2 == 0 ? 0 : 1);
            int ans = 0;
            for (int x = 0; x < roundTime; x++) {
                int bs = (i / 2D) - x < 1 ? 1 : 2;
                int index1 = x - 0;
                int index2 = i - x - 1;
                ans += bs * anss.get(index1) * anss.get(index2);
            }
            anss.add(ans);
        }
        return anss.get(n);
    }

    /**
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (null == root) {
            return true;
        }
        return isValidBSTLeft(root.left, Long.parseLong(String.valueOf(root.val)), Long.MIN_VALUE) && isValidBSTRight(root.right, Long.parseLong(String.valueOf(root.val)), Long.MAX_VALUE);
    }

    private boolean isValidBSTRight(TreeNode root, Long minval, Long maxval) {
        if (null == root) {
            return true;
        }

        if (root.val >= maxval || root.val <= minval) {
            return false;
        }

        return isValidBSTLeft(root.left, Long.parseLong(String.valueOf(root.val)), minval) && isValidBSTRight(root.right, Long.parseLong(String.valueOf(root.val)), maxval);
    }

    private boolean isValidBSTLeft(TreeNode root, Long maxval, Long minval) {
        if (null == root) {
            return true;
        }

        if (root.val >= maxval || root.val <= minval) {
            return false;
        }

        return isValidBSTLeft(root.left, Long.parseLong(String.valueOf(root.val)), minval) && isValidBSTRight(root.right, Long.parseLong(String.valueOf(root.val)), maxval);

    }

    /**
     * 1 2 5 3 4 null 6
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        if (null == root) {
            return;
        }
        TreeNode cur = null;
        Stack<TreeNode> stacks = new Stack<>();
        stacks.push(root);
        while (!stacks.isEmpty()) {
            TreeNode treeNode = stacks.pop();
            if (null != treeNode.right) {
                stacks.push(treeNode.right);
            }
            if (null != treeNode.left) {
                stacks.push(treeNode.left);
            }

            treeNode.left = null;
            treeNode.right = null;
            if (cur == null) {
                cur = treeNode;
            } else {
                cur.right = treeNode;
                cur = treeNode;
            }
        }

    }

    /**
     * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
     *
     * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE"是"ABCDE"的一个子序列，而"AEC"不是）
     *
     * 题目数据保证答案符合 32 位带符号整数范围。
     *
     *
     * s = "babgbag", t = "bag"
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        if(t.length()>s.length()){
            return 0;
        }
        return findCount(s,t,0);
    }

    /**
     * s = "babgbag", t = "bag"
     * @param s
     * @param t
     * @param tindex
     * @return
     */
    private int findCount(String s, String t, int tindex) {
        Integer totalCount = 0;
        Integer fromIndex = null;
        while(fromIndex==null||fromIndex>=0){
            if(fromIndex==null){
                fromIndex = s.indexOf(t.charAt(tindex));
            }else{
                fromIndex = s.indexOf(t.charAt(tindex),fromIndex+1);
            }

            if(fromIndex>=0){
                if(tindex==t.length()-1){
                    totalCount+=1;
                }else if(fromIndex+1<s.length()){
                    totalCount+=findCount(s.substring(fromIndex+1),t,tindex+1);
                }
            }

        }
        return totalCount;
    }

    public int numDistinct2(String s, String t) {
        int m = s.length(), n = t.length();
        if (m < n) {
            return 0;
        }
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][n] = 1;
        }
        for (int i = m - 1; i >= 0; i--) {
            char sChar = s.charAt(i);
            for (int j = n - 1; j >= 0; j--) {
                char tChar = t.charAt(j);
                if (sChar == tChar) {
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    }

    public int numDistinct3(String s, String t) {
        int sl = s.length();
        int tl = t.length();
        int[][] dp = new int[sl+1][tl+1];
        for(int i=0;i<sl+1;i++){
            dp[i][0] = 1;
        }
        for(int i=1;i<tl+1;i++){
            dp[0][i] = 0;
        }
        for(int i=1;i<sl+1;i++){
            for(int l=1;l<tl+1;l++){
                if(s.charAt(i-1)==t.charAt(l-1)){
                    dp[i][l] = dp[i-1][l-1]+dp[i-1][l];
                }else{
                    dp[i][l] = dp[i-1][l];
                }
            }
        }
        return dp[sl][tl];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        TreeNode treeNode6 = new TreeNode(6);
//        TreeNode treeNode5 = new TreeNode(5, null, treeNode6);
//        TreeNode treeNode4 = new TreeNode(4, null, null);
//        TreeNode treeNode3 = new TreeNode(3, null, null);
//        TreeNode treeNode2 = new TreeNode(2, treeNode3, treeNode4);
//        TreeNode treeNode1 = new TreeNode(1, treeNode2, treeNode5);
//        int[] nums = new int[]{1, 2, 3, 4, 5};
//        solution.removeNthFromEnd(solution.gene(nums), 2);
//        System.out.println(solution.numTrees(5));
//        solution.flatten(treeNode1);
//        //new Solution().moveZeroes(nums);
//        int[] nums1 = new int[]{-1, 0, 1, 2, -1, -4};
//        solution.threeSum(nums1);
//
//        int[][] xx = new int[][]{new int[]{1, 1, 0}, new int[]{1, 1, 0}, new int[]{0, 0, 1}};
//        solution.findCircleNum(xx);
//        System.out.println(solution.numDistinct("adbdadeecadeadeccaeaabdabdbcdabddddabcaaadbabaaedeeddeaeebcdeabcaaaeeaeeabcddcebddebeebedaecccbdcbcedbdaeaedcdebeecdaaedaacadbdccabddaddacdddc"
//                ,"bcddceeeebecbc"));
        System.out.println(solution.massage(new int[]{0,0,0,1}));
    }

    /**
     * 输入： [2,1,4,5,3,1,1,3]
     * 输出： 12
     * 非相邻最大和
     * @param nums
     * @return
     */
    public int massage(int[] nums) {
        int[][] dp = new int[nums.length+2][2];
        for(int i=0;i<nums.length+2;i++){
            for(int l=0;l<2;l++){
                if(i==0||i==1){
                    dp[i][l] = 0;
                }else{
                    if(l==0){
                        dp[i][l] = nums[i-2]+dp[i-1][l+1];
                    }else{
                        dp[i][l] = Math.max(dp[i-1][l],dp[i-1][l-1]);
                    }
                }
            }
        }
        return Math.max(dp[nums.length+1][0],dp[nums.length+1][1]);
    }

    public boolean divisorGame(int N) {
        boolean[] dp = new boolean[N];
        dp[0] = false;
        one:for(int i=1;i<N;i++){
            int real = i+1;
            for(int l=1;l<real;l++){
                if(real%l==0){
                    if(!dp[real-l-1]){
                        dp[i] = true;
                        continue one;
                    }
                }
            }
            dp[i] = false;
        }
        return dp[N-1];
    }

    /**
     * 示例 1：
     *
     * 输入：cost = [10, 15, 20]
     * 输出：15
     * 解释：最低花费是从 cost[1] 开始，然后走两步即可到阶梯顶，一共花费 15 。
     *  示例 2：
     *
     * 输入：cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
     * 输出：6
     * 解释：最低花费方式是从 cost[0] 开始，逐个经过那些 1 ，跳过 cost[3] ，一共花费 6 。
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int[][] dp = new int[cost.length][2];
        for(int i=0;i<cost.length;i++){
            for(int l=0;l<2;l++){
                if(l==0){
                    if(i>1){
                        dp[i][l] = cost[i] + Math.min(dp[i-1][0],dp[i-2][0]);
                    }else{
                        dp[i][l] = cost[i];
                    }
                }
                if(l==1){
                    if(i>0){
                        dp[i][l] = dp[i-1][0];
                    }else{
                        dp[i][l] = 0;
                    }
                }
            }
        }
        return Math.min(dp[cost.length-1][0],dp[cost.length-1][1]);
    }
}
