package com.zsw.zeng.helloapplication.fixutils;

import android.content.Context;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.HashSet;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

import static android.content.Context.MODE_PRIVATE;

/**
 * @author ZengSuWa
 * @Description：
 * @Company：众鑫贷
 * @Created time：2017/3/16 10:31
 */

public class FixDexUtils {
    private static HashSet<File> loadDex = new HashSet<File>();
    private final static String DEX_SUFFIX = ".dex";

    static {
        loadDex.clear();
    }

    public static void loadFixedDex(Context context) {
        //dex 文件目录
        File fileDir = context.getDir("odex", MODE_PRIVATE);
        File[] listFiles = fileDir.listFiles();
        for (File file : listFiles) {
            if (file.getName().endsWith(DEX_SUFFIX)) {
                //说明是补丁 所有补丁文件
                loadDex.add(file);
            }
        }
        doDexInject(context, fileDir, loadDex);
    }

    /**
     * @param context
     * @param fileDir
     * @param loadDex
     */
    private static void doDexInject(Context context, File fileDir, HashSet<File> loadDex) {
        //类加载器的加载
        //dex解压的临时目录
        String optimizedDir = fileDir.getAbsolutePath() + File.separator + "opt_dex";
        File fopt = new File(optimizedDir);
        //如果这个文件目录不存在就创建这个文件目录
        if (!fopt.exists()) {
            fopt.mkdirs();
        }
        //把所有的补丁都加载出来
        for (File dex : loadDex) {
            //每加载一个补丁，就创建一个类加载器去加载那个补丁
            //1参为：补丁的文件路径  2参为：压缩路径（dex会解压到这在进行加载） 3参：是否有本地库，4参：类加载器的父类
            DexClassLoader classLoader = new DexClassLoader(dex.getAbsolutePath(), optimizedDir, null, context.getClassLoader());
            inject(classLoader, context);
        }


    }

    /**
     * 注入
     *
     * @param classLoader 加载了补丁的类加载器
     * @param context     系统的类加载器
     */
    private static void inject(DexClassLoader classLoader, Context context) {
        PathClassLoader pathLoader = (PathClassLoader) context.getClassLoader();
        try {
            //先获得classLoader 才能获得PathList 最后才能获得Elements数组
            //自己的数组在前面
            Object dexElements = combineArray(getDexElements(getPathList(classLoader)), getDexElements(getPathList(pathLoader)));
            //得到系统PathList，然后再把新的数组放进去
            Object pathList = getPathList(pathLoader);
            setField(pathList, pathList.getClass(), "dexElements", dexElements);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取类的字段
     *
     * @param obj
     * @param cl
     * @param field
     * @return
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    private static Object getFiled(Object obj, Class<?> cl, String field) throws IllegalAccessException, NoSuchFieldException {
        Field localField = cl.getDeclaredField(field);
        localField.setAccessible(true);
        return localField.get(obj);
    }

    /**
     * @param obj
     * @param cl
     * @param field
     * @param value
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    private static void setField(Object obj, Class<?> cl, String field, Object value) throws IllegalAccessException, NoSuchFieldException {
        Field localField = cl.getDeclaredField(field);
        localField.setAccessible(true);
        localField.set(obj, value);
    }

    private static Object getPathList(Object baseDexClassLoader) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        return getFiled(baseDexClassLoader, Class.forName("dalvik.system.BaseDexClassLoader"), "pathList");
    }

    private static Object getDexElements(Object paramObject) throws NoSuchFieldException, IllegalAccessException {
        return getFiled(paramObject, paramObject.getClass(), "dexElements");
    }

    /**
     * 两个数组进行拼接
     *
     * @param arrayLhs
     * @param arrayRhs
     * @return
     */
    private static Object combineArray(Object arrayLhs, Object arrayRhs) {
        //java.lang.Class.getComponentType() 方法返回类的组件类型的数组。如果这个类并不代表一个数组类，此方法返回null。
        //就是返回你数组里面装的数据的数据类型的类名称
        Class<?> localClass = arrayLhs.getClass().getComponentType();
        int i = Array.getLength(arrayLhs);//获得左边数组的长度
        int j = i + Array.getLength(arrayRhs);//获得新数组的长度（左边数组长度+右边数组的长度）
        Object result = Array.newInstance(localClass, j);//创建一个新数组
        for (int k = 0; k < j; ++k) {//拼接新数组
            if (k < i) {//如果k小于i,那么把arrayLhs的k位置的元素放入到新数组k位置上
                Array.set(result, k, Array.get(arrayLhs, k));
            } else {//如果k大于i,那么把arrayRhs的k位置的元素放入到新数组k位置上
                Array.set(result, k, Array.get(arrayRhs, k - i));

            }
        }
        return result;
    }
}
