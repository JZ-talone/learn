package com.hym.spring.learn.solution;

import lombok.AllArgsConstructor;
import lombok.Data;

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

    public ListNode(int val) {
        this.val = val;
    }
}
