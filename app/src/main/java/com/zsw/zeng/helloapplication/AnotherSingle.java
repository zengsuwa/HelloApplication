package com.zsw.zeng.helloapplication;

/**
 * @author zeng
 * @date 2016/9/13
 * @Description:
 * 因为静态的单例对象没有作为类的成员变量直接实例化，
 * 因此在Printer类加载时并没有实例化mPrinter。
 * 第一次调用newInstance() 的时候加载内部类CreatePrinter ，
 * 该内部类定义了一个static 类型的变量mPrinter，此时会首先初始化这个变量，
 * 由JVM 来保证其线程安全性，确保该成员变量只被实例化一次。
 */
public class AnotherSingle {

    public AnotherSingle() {

    }

    public AnotherSingle getSingle() {
        return CreateSingle.single1;
    }

    private static class  CreateSingle{
        private final static AnotherSingle single1 = new AnotherSingle();
    }
}
