package com.alibaba.csp.switchcenter.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.alibaba.csp.switchcenter.annotation.AppSwitch;
import com.alibaba.csp.switchcenter.bean.Switch.Level;

/**
 * @author jielong.hjl
 */
public class CollectionTypeSwitch {

    @AppSwitch(des = "泛型为 Integer List 类型开关", level = Level.p1)
    public static List<Integer> arraysAsListSwitch = Arrays.asList(1);

    @AppSwitch(des = "泛型为 Integer List 类型开关", level = Level.p1)
    public static List<Integer> integerListSwitch = new ArrayList<Integer>();

    @AppSwitch(des = "泛型为 String List 类型开关", level = Level.p1)
    public static List<String> stringListSwitch = new ArrayList<String>();

    @AppSwitch(des = "泛型为 Boolean List 类型开关", level = Level.p1)
    public static List<Boolean> booleanListSwitch = new ArrayList<Boolean>();

    @AppSwitch(des = "泛型为 Double List 类型开关", level = Level.p1)
    public static List<Double> doubleListSwitch = new ArrayList<Double>();

    @AppSwitch(des = "泛型为 Float List 类型开关", level = Level.p1)
    public static List<Float> floatListSwitch = new ArrayList<Float>();

    @AppSwitch(des = "泛型为 Long List 类型开关", level = Level.p1)
    public static List<Long> longListSwitch = new ArrayList<Long>();

    @AppSwitch(des = "泛型为 Byte List 类型开关", level = Level.p1)
    public static List<Byte> byteListSwitch = new ArrayList<Byte>();

    @AppSwitch(des = "泛型为 Short List 类型开关", level = Level.p1)
    public static List<Short> shortListSwitch = new ArrayList<Short>();

    @AppSwitch(des = "泛型为 Character List 类型开关", level = Level.p1)
    public static List<Character> characterListSwitch = new ArrayList<Character>();

    @AppSwitch(des = "泛型为 AtomicInteger List 类型开关", level = Level.p1)
    public static List<AtomicInteger> atomicIntegerListSwitch = new ArrayList<AtomicInteger>();

    @AppSwitch(des = "泛型为 AtomicLong List 类型开关", level = Level.p1)
    public static List<AtomicLong> atomicLongListSwitch = new ArrayList<AtomicLong>();

    @AppSwitch(des = "泛型为 AtomicBoolean List 类型开关", level = Level.p1)
    public static List<AtomicBoolean> atomicBooleanListSwitch = new ArrayList<AtomicBoolean>();

    @SuppressWarnings("rawtypes")
    @AppSwitch(des = "无泛型的List 类型开关", level = Level.p1)
    public static List noGenericListSwitch = new ArrayList();
	
	/*
	 * java.util.Set switch 
	 */

    @AppSwitch(des = "泛型为 Integer List 类型开关", level = Level.p1)
    public static Set<Integer> integerSetSwitch = new HashSet<Integer>();

    @AppSwitch(des = "泛型为 String List 类型开关", level = Level.p1)
    public static Set<String> stringSetSwitch = new HashSet<String>();

    @AppSwitch(des = "泛型为 Boolean List 类型开关", level = Level.p1)
    public static Set<Boolean> booleanSetSwitch = new HashSet<Boolean>();

    @AppSwitch(des = "泛型为 Double List 类型开关", level = Level.p1)
    public static Set<Double> doubleSetSwitch = new HashSet<Double>();

    @AppSwitch(des = "泛型为 Float List 类型开关", level = Level.p1)
    public static Set<Float> floatSetSwitch = new HashSet<Float>();

    @AppSwitch(des = "泛型为 Long List 类型开关", level = Level.p1)
    public static Set<Long> longSetSwitch = new HashSet<Long>();

    @AppSwitch(des = "泛型为 Byte List 类型开关", level = Level.p1)
    public static Set<Byte> byteSetSwitch = new HashSet<Byte>();

    @AppSwitch(des = "泛型为 Short List 类型开关", level = Level.p1)
    public static Set<Short> shortSetSwitch = new HashSet<Short>();

    @AppSwitch(des = "泛型为 Character List 类型开关", level = Level.p1)
    public static Set<Character> characterSetSwitch = new HashSet<Character>();

    @AppSwitch(des = "泛型为 AtomicInteger List 类型开关", level = Level.p1)
    public static Set<AtomicInteger> atomicIntegerSetSwitch = new HashSet<AtomicInteger>();

    @AppSwitch(des = "泛型为 AtomicLong List 类型开关", level = Level.p1)
    public static Set<AtomicLong> atomicLongSetSwitch = new HashSet<AtomicLong>();

    @AppSwitch(des = "泛型为 AtomicBoolean List 类型开关", level = Level.p1)
    public static Set<AtomicBoolean> atomicBooleanSetSwitch = new HashSet<AtomicBoolean>();

    @SuppressWarnings("rawtypes")
    @AppSwitch(des = "无泛型的List 类型开关", level = Level.p1)
    public static Set noGenericSetSwitch = new HashSet();

}