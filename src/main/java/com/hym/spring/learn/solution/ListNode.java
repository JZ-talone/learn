package com.hym.spring.learn.solution;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Comparator.nullsFirst;

/**
 * ${DESCRIPTION}
 *
 * @author huangyiming
 * @since 2020/9/16 11:19
 */
@Data
@AllArgsConstructor
public class ListNode {
    int val;
    ListNode next;
    int testval;

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1, null, 1);
        ListNode listNode2 = new ListNode(2, null, 4);
        ListNode listNode3 = new ListNode(2, null, 3);
        ListNode listNode4 = new ListNode(3, null, 1);
        List<ListNode> list = Lists.newArrayList(listNode1, listNode2, listNode3, listNode4);


        System.out.println(JSON.toJSONString(list.stream().sorted(comparing(ListNode::getVal, nullsFirst(Integer::compareTo))
                .thenComparing(ListNode::getTestval).reversed()).collect(Collectors.toList())));

    }
}
