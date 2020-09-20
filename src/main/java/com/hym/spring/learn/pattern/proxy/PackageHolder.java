package com.hym.spring.learn.pattern.proxy;

/**
 * ${DESCRIPTION}
 *
 * @author huangyiming
 * @since 2020/9/20 21:44
 */
public class PackageHolder {
    /**
     * 代理模式
     * 结构化模式
     * 类似媒婆
     * 男-媒婆-女
     *
     * 因为男没媒婆资源多
     *
     * 且保护目标对象
     * 增强目标对象
     */


    /**
     * jdk 动态代理原理
     * 1 拿到被代理类的饮用，并且获取到他的所有接口（反射获取）
     * 2 jdk proxy类重新生成一个新的类，实现了被代理类所有接口的方法
     * 3 动态生成java代码，并把增强逻辑加入到新生成代码中
     * 4 编译生成新的java代码的class文件
     * 5 加载重新运行新的class，得到的类就是全新类
     */

    /**
     * cglib 动态代理原理
     * 1 拿到被代理类
     * 2 生成一个被代理类的子类，实现其所有方法并增强
     */

    /**
     * 区别
     * 1 生成方式 都是在运行期生成字节码 jdk是采用读取接口的方法直接写class字节码 cglib是使用asm框架生成子类class字节码覆盖父类方法 目的都是生成新类增强代码逻辑
     * 2 对被代理类的要求 jdk要求必须实现至少一个接口  cglib可以代理任何类
     * 3 生成逻辑 cglib生成逻辑更复杂 jdk相对简单
     * 4 运行时效率 cglib生成的fastclass调用效率更高 因为其不用反射调用原类 而jdk需要
     * 5 cglib 有个坑 cglib不能代理final的方法
     */

    /**
     * spring 动态代理
     * 默认两种都用
     * bean实现了接口的类用jdk
     * bean没实现接口的用cglib
     * spring可以强制使用cglib
     * <aop:aspecttj-autoproxy proxy-target-class="true" />
     */

    /**
     * 优点
     * 代理模式能将被代理对象与调用者分离
     * 一定程度上降低了系统的耦合程度已于拓展
     * 代理可以保护目标对象
     * 增强目标对象的指责
     */

    /**
     * 缺点
     * 增加了系统的复杂度
     * 增加了类
     * 会有一定的效率上的衰减
     */
}
