package com.alibaba.csp.switchcenter.example;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

import com.alibaba.csp.switchcenter.annotation.AppSwitch;
import com.alibaba.csp.switchcenter.bean.Switch.Level;

/**
 * @author jielong.hjl
 */
public class CommonTypeSwitch {

    @AppSwitch(des = "String 类型开关", level = Level.p2)
    public static String stringSwitch = "string";
    
    @AppSwitch(des = "String 类型初始化值为空开关", level = Level.p2)
    public static String stringNullSwitch;

    @AppSwitch(des = "Integer 类型开关", level = Level.p1)
    public static Integer integerSwitch = 2;

    @AppSwitch(des = "Double 类型开关", level = Level.p4)
    public static Double doubleSwitch = 3.21;

    @AppSwitch(des = "Float 类型开关", level = Level.p3)
    public static Float floatSwitch = 4.87F;

    @AppSwitch(des = "Long 类型开关", level = Level.p2)
    public static Long longSwitch = 8L;

    @AppSwitch(des = "Boolean 类型开关", level = Level.p4)
    public static Boolean booleanSwitch = true;

    @AppSwitch(des = "Short 类型开关", level = Level.p2)
    public static Short shortSwitch = 12;

    @AppSwitch(des = "Byte 类型开关", level = Level.p2)
    public static Byte byteSwitch = 5;

    @AppSwitch(des = "Character 类型开关", level = Level.p4)
    public static Character characterSwitch = 'c';

    @AppSwitch(des = "AtomicInteger 类型开关", level = Level.p1)
    public static AtomicInteger atomicIntegerSwitch = new AtomicInteger(21);

    @AppSwitch(des = "AtomicBoolean 类型开关", level = Level.p1)
    public static AtomicBoolean atomicBooleanSwitch = new AtomicBoolean(true);

    @AppSwitch(des = "AtomicLong 类型开关", level = Level.p1)
    public static AtomicLong atomicLongSwitch = new AtomicLong(4L);

    @AppSwitch(des = "String 数组类型开关", level = Level.p4)
    public static String[] stringArraySwitch = {"hello", "world"};

    @AppSwitch(des = "Integer 数组类型开关", level = Level.p4)
    public static Integer[] integerArraySwitch = {3, 6, 9};

    @AppSwitch(des = "Double 数组类型开关", level = Level.p4)
    public static Double[] doubleArraySwitch = {4.11, 2.13};

    @AppSwitch(des = "Float 数组类型开关", level = Level.p3)
    public static Float[] floatArraySwitch = {3.2f};

    @AppSwitch(des = "Long 数组类型开关", level = Level.p3)
    public static Long[] longArraySwitch = {2L};

    @AppSwitch(des = "Boolean 数组类型开关", level = Level.p3)
    public static Boolean[] booleanArraySwitch = {true, false, false};

    @AppSwitch(des = "Short 数组类型开关", level = Level.p3)
    public static Short[] shortArraySwitch = {2, 1};

    @AppSwitch(des = "Byte 数组类型开关", level = Level.p2)
    public static Byte[] byteArraySwitch = {5, 8, -1};

    @AppSwitch(des = "Character 数组类型开关", level = Level.p2)
    public static Character[] characterArraySwitch = {'s', 'w', 'i', 't', 'c', 'h'};

    static int[] atomicIntegerArrayItem = {3, 4, 5, 6};
    static long[] atomicIntegerLongItem = {6l, 5l};

    @AppSwitch(des = "AtomicIntegerArray 类型开关", level = Level.p2)
    public static AtomicIntegerArray atomicIntegerArraySwitch = new AtomicIntegerArray(atomicIntegerArrayItem);

    @AppSwitch(des = "AtomicLongArray 类型开关", level = Level.p2)
    public static AtomicLongArray atomicLongArraySwitch = new AtomicLongArray(atomicIntegerLongItem);

}
