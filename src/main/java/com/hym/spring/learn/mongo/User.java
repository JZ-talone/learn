package com.hym.spring.learn.mongo;

import lombok.Data;

import java.io.Serializable;

/**
 * ${DESCRIPTION}
 *
 * @author huangyiming
 * @since 2020/9/13 12:37
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -3258839839160856613L;
    private Long id;
    private String userName;
    private String passWord;
}