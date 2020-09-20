package com.hym.spring.learn.pattern.single;

/**
 * ${DESCRIPTION}
 * <p>
 * 枚举式单例
 *
 * @author huangyiming
 * @since 2020/9/20 20:41
 */
public enum EnumSingle {

    INSTANCE;

    private Object data;

    public static EnumSingle getInstance() {
        return INSTANCE;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
