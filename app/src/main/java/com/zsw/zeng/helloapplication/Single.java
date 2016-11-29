package com.zsw.zeng.helloapplication;

/**
 * @author zeng
 * @date 2016/9/13
 * @Description:
 * 当一个线程通过第一个if判断语句时，无论怎样，在同一时刻，
 * 只有一个线程可以进入第二个if判断语句，而且会new 出一个实例，
 * 保证synchronized方法从始至终只会执行一次，这样就解决了饿汉式和懒汉式的尴尬，
 * 因为它既不会像懒汉式那样多次进入synchronized方法占用系统资源，
 * 也不会像饿汉式那样在不使用的情况下占用系统资源。
 *
 */
public class Single {
    private volatile static Single single;

    public static Single getSingle() {
        if(single ==null ){
            synchronized (Single.class){
                if (single ==null){
                    single = new Single();
                }
            }
        }
        return single;
    }
}
