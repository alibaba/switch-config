package com.alibaba.csp.switchcenter.example;


import com.alibaba.csp.switchcenter.annotation.AppSwitch;
import com.alibaba.csp.switchcenter.bean.Switch.Level;

/**
 * @author jielong.hjl
 */
public class PrimitiveTypeSwitch {

    @AppSwitch(des = "int 类型开关", level = Level.p1)
    public static int primitiveIntSwitch = 1;

    @AppSwitch(des = "doubel 类型开关", level = Level.p1)
    public static double primitiveDoubleSwitch = 1.121;

    @AppSwitch(des = "float 类型开关", level = Level.p2)
    public static float primitiveFloatSwitch = 3.24f;

    @AppSwitch(des = "long 类型开关", level = Level.p3)
    public static long primitiveLongSwitch = 5l;

    @AppSwitch(des = "short 类型开关", level = Level.p4)
    public static short primitiveShortSwitch = 1;

    @AppSwitch(des = "byte 类型开关", level = Level.p2)
    public static byte primitiveByteSwitch = 8;

    @AppSwitch(des = "boolean 类型开关", level = Level.p4)
    public static boolean primitiveBooleanSwitch = false;

    @AppSwitch(des = "char 类型开关", level = Level.p3)
    public static char primitiveCharSwitch = 'c';

    @AppSwitch(des = "int 数组类型开关", level = Level.p2)
    public static int[] primitiveIntArraySwitch = {1, 2, 3};

    @AppSwitch(des = "double 数组类型开关", level = Level.p1)
    public static double[] primitiveDoubleArraySwitch = {2.1, 3.14};

    @AppSwitch(des = "float 数组类型开关", level = Level.p4)
    public static float[] primitiveFloatArraySwitch = {2.89f, 3.42f, 5.67f};

    @AppSwitch(des = "long 数组类型开关", level = Level.p3)
    public static long[] primitiveLongArraySwitch = {1l, 5l, 8l, 9l};

    @AppSwitch(des = "short 数组类型开关", level = Level.p1)
    public static short[] primitiveShortArraySwitch = {3, 8, 1};

    @AppSwitch(des = "byte 数组类型开关", level = Level.p2)
    public static byte[] primitiveByteArraySwitch = {4, 9, 0};

    @AppSwitch(des = "boolean 数组类型开关", level = Level.p3)
    public static boolean[] primitiveBooleanArraySwitch = {false, true, true};

    @AppSwitch(des = "char 数组类型开关", level = Level.p2)
    public static char[] primitiveCharArraySwitch = {'h', 'e', 'l', 'l', 'o'};

}
