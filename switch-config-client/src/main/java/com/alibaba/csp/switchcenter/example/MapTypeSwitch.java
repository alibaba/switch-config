package com.alibaba.csp.switchcenter.example;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.alibaba.csp.switchcenter.annotation.AppSwitch;
import com.alibaba.csp.switchcenter.bean.Switch.Level;

/**
 * @author jielong.hjl
 */
public class MapTypeSwitch {

    //<K, V>  <-->  <Integer, ?>

    @AppSwitch(des = "泛型是<Integer, Integer> Map 开关", level = Level.p4)
    public static Map<Integer, Integer> INT_INT_MAP = new HashMap<Integer, Integer>();

    @AppSwitch(des = "泛型是<Integer, Boolean> Map 开关", level = Level.p4)
    public static Map<Integer, Boolean> INT_BOOL_MAP = new HashMap<Integer, Boolean>();

    @AppSwitch(des = "泛型是<Integer, String> Map 开关", level = Level.p4)
    public static Map<Integer, String> INT_STRING_MAP = new HashMap<Integer, String>();

    @AppSwitch(des = "泛型是<Integer, > Map 开关", level = Level.p4)
    public static Map<Integer, Long> INT_LONG_MAP = new HashMap<Integer, Long>();

    @AppSwitch(des = "泛型是<Integer, Float> Map 开关", level = Level.p4)
    public static Map<Integer, Float> INT_FLOAT_MAP = new HashMap<Integer, Float>();

    @AppSwitch(des = "泛型是<Integer, Double> Map 开关", level = Level.p4)
    public static Map<Integer, Double> INT_DOUBLE_MAP = new HashMap<Integer, Double>();

    @AppSwitch(des = "泛型是<Integer,Character> Map 开关", level = Level.p4)
    public static Map<Integer, Character> INT_CHAR_MAP = new HashMap<Integer, Character>();

    @AppSwitch(des = "泛型是<Integer, Short> Map 开关", level = Level.p4)
    public static Map<Integer, Short> INT_SHORT_MAP = new HashMap<Integer, Short>();

    @AppSwitch(des = "泛型是<Integer, Byte> Map 开关", level = Level.p4)
    public static Map<Integer, Byte> INT_BYTE_MAP = new HashMap<Integer, Byte>();

    @AppSwitch(des = "泛型是<Integer, AtomicLong> Map 开关", level = Level.p4)
    public static Map<Integer, AtomicLong> INT_ATOMICLONG_MAP = new HashMap<Integer, AtomicLong>();

    @AppSwitch(des = "泛型是<Integer, AtomicBoolean> Map 开关", level = Level.p4)
    public static Map<Integer, AtomicBoolean> INT_ATOMICBOOL_MAP = new HashMap<Integer, AtomicBoolean>();

    @AppSwitch(des = "泛型是<Integer, AtomicInteger> Map 开关", level = Level.p4)
    public static Map<Integer, AtomicInteger> INT_ATOMICINT_MAP = new HashMap<Integer, AtomicInteger>();


//	//<K, V>  <-->  <Boolean, ?>
//	@AppSwitch(des="泛型是<Boolean, Integer> Map 开关", level=Level.p4)
//	public static Map<Boolean, Integer> 			BOOL_INT_MAP 				=		new HashMap<Boolean, Integer>();  
//	
//	@AppSwitch(des="泛型是<Boolean, Boolean> Map 开关", level=Level.p4)
//	public static Map<Boolean, Boolean> 			BOOL_BOOL_MAP 				=		new HashMap<Boolean, Boolean>();  
//	
//	@AppSwitch(des="泛型是<Boolean, String> Map 开关", level=Level.p4)
//	public static Map<Boolean, String> 				BOOL_STRING_MAP				= 		new HashMap<Boolean, String>();
//	
//	@AppSwitch(des="泛型是<Boolean, Long> Map 开关", level=Level.p4)
//	public static Map<Boolean, Long> 				BOOL_LONG_MAP				= 		new HashMap<Boolean, Long>();
//	
//	@AppSwitch(des="泛型是<Boolean, Float> Map 开关", level=Level.p4)
//	public static Map<Boolean, Float> 				BOOL_FLOAT_MAP				= 		new HashMap<Boolean, Float>();
//	
//	@AppSwitch(des="泛型是<Boolean, Double> Map 开关", level=Level.p4)
//	public static Map<Boolean, Double> 				BOOL_DOUBLE_MAP				= 		new HashMap<Boolean, Double>();
//
//	@AppSwitch(des="泛型是<Boolean, Character> Map 开关", level=Level.p4)
//	public static Map<Boolean, Character> 			BOOL_CHAR_MAP				= 		new HashMap<Boolean, Character>();
//	
//	@AppSwitch(des="泛型是<Boolean, Short> Map 开关", level=Level.p4)
//	public static Map<Boolean, Short> 				BOOL_SHORT_MAP				= 		new HashMap<Boolean, Short>();
//	
//	@AppSwitch(des="泛型是<Boolean, Byte> Map 开关", level=Level.p4)
//	public static Map<Boolean, Byte> 				BOOL_BYTE_MAP				= 		new HashMap<Boolean, Byte>();
//	
//	@AppSwitch(des="泛型是<Boolean, AtomicLong> Map 开关", level=Level.p4)
//	public static Map<Boolean, AtomicLong> 			BOOL_ATOMICLONG_MAP			= 		new HashMap<Boolean, AtomicLong>();
//	
//	@AppSwitch(des="泛型是<Boolean, AtomicBoolean> Map 开关", level=Level.p4)
//	public static Map<Boolean, AtomicBoolean> 		BOOL_ATOMICBOOL_MAP			= 		new HashMap<Boolean, AtomicBoolean>();
//	
//	@AppSwitch(des="泛型是<Boolean, AtomicInteger> Map 开关", level=Level.p4)
//	public static Map<Boolean, AtomicInteger> 		BOOL_ATOMICINT_MAP			= 		new HashMap<Boolean, AtomicInteger>();

    //<K, V>  <-->  <String, ?>
    @AppSwitch(des = "泛型是<String, Integer> Map 开关", level = Level.p4)
    public static Map<String, Integer> STRING_INT_MAP = new HashMap<String, Integer>();

    @AppSwitch(des = "泛型是<String, Boolean> Map 开关", level = Level.p4)
    public static Map<String, Boolean> STRING_BOOL_MAP = new HashMap<String, Boolean>();

    @AppSwitch(des = "泛型是<String, String> Map 开关", level = Level.p4)
    public static Map<String, String> STRING_STRING_MAP = new HashMap<String, String>();

    @AppSwitch(des = "泛型是<String, Long> Map 开关", level = Level.p4)
    public static Map<String, Long> STRING_LONG_MAP = new HashMap<String, Long>();

    @AppSwitch(des = "泛型是<String, Float> Map 开关", level = Level.p4)
    public static Map<String, Float> STRING_FLOAT_MAP = new HashMap<String, Float>();

    @AppSwitch(des = "泛型是<String, Double> Map 开关", level = Level.p4)
    public static Map<String, Double> STRING_DOUBLE_MAP = new HashMap<String, Double>();

    @AppSwitch(des = "泛型是<String, Character> Map 开关", level = Level.p4)
    public static Map<String, Character> STRING_CHAR_MAP = new HashMap<String, Character>();

    @AppSwitch(des = "泛型是<String, Short> Map 开关", level = Level.p4)
    public static Map<String, Short> STRING_SHORT_MAP = new HashMap<String, Short>();

    @AppSwitch(des = "泛型是<String, Byte> Map 开关", level = Level.p4)
    public static Map<String, Byte> STRING_BYTE_MAP = new HashMap<String, Byte>();

    @AppSwitch(des = "泛型是<String, AtomicLong> Map 开关", level = Level.p4)
    public static Map<String, AtomicLong> STRING_ATOMICLONG_MAP = new HashMap<String, AtomicLong>();

    @AppSwitch(des = "泛型是<String, AtomicBoolean> Map 开关", level = Level.p4)
    public static Map<String, AtomicBoolean> STRING_ATOMICBOOL_MAP = new HashMap<String, AtomicBoolean>();

    @AppSwitch(des = "泛型是<String, AtomicInteger> Map 开关", level = Level.p4)
    public static Map<String, AtomicInteger> STRING_ATOMICINT_MAP = new HashMap<String, AtomicInteger>();

    //<K, V>  <-->  <Long, ?>
    @AppSwitch(des = "泛型是<Long, Integer> Map 开关", level = Level.p4)
    public static Map<Long, Integer> LONG_INT_MAP = new HashMap<Long, Integer>();

    @AppSwitch(des = "泛型是<Long, Boolean> Map 开关", level = Level.p4)
    public static Map<Long, Boolean> LONG_BOOL_MAP = new HashMap<Long, Boolean>();

    @AppSwitch(des = "泛型是<Long, String> Map 开关", level = Level.p4)
    public static Map<Long, String> LONG_STRING_MAP = new HashMap<Long, String>();

    @AppSwitch(des = "泛型是<Long, Long> Map 开关", level = Level.p4)
    public static Map<Long, Long> LONG_LONG_MAP = new HashMap<Long, Long>();

    @AppSwitch(des = "泛型是<Long, Float> Map 开关", level = Level.p4)
    public static Map<Long, Float> LONG_FLOAT_MAP = new HashMap<Long, Float>();

    @AppSwitch(des = "泛型是<Long, Double> Map 开关", level = Level.p4)
    public static Map<Long, Double> LONG_DOUBLE_MAP = new HashMap<Long, Double>();

    @AppSwitch(des = "泛型是<Long, Character> Map 开关", level = Level.p4)
    public static Map<Long, Character> LONG_CHAR_MAP = new HashMap<Long, Character>();

    @AppSwitch(des = "泛型是<Long, Short> Map 开关", level = Level.p4)
    public static Map<Long, Short> LONG_SHORT_MAP = new HashMap<Long, Short>();

    @AppSwitch(des = "泛型是<Long, Byte> Map 开关", level = Level.p4)
    public static Map<Long, Byte> LONG_BYTE_MAP = new HashMap<Long, Byte>();

    @AppSwitch(des = "泛型是<Long, AtomicLong> Map 开关", level = Level.p4)
    public static Map<Long, AtomicLong> LONG_ATOMICLONG_MAP = new HashMap<Long, AtomicLong>();

    @AppSwitch(des = "泛型是<Long, AtomicBoolean> Map 开关", level = Level.p4)
    public static Map<Long, AtomicBoolean> LONG_ATOMICBOOL_MAP = new HashMap<Long, AtomicBoolean>();

    @AppSwitch(des = "泛型是<Long, AtomicInteger> Map 开关", level = Level.p4)
    public static Map<Long, AtomicInteger> LONG_ATOMICINT_MAP = new HashMap<Long, AtomicInteger>();

    //<K, V>  <-->  <Double, ?>
    @AppSwitch(des = "泛型是<Double, Integer> Map 开关", level = Level.p4)
    public static Map<Double, Integer> DOUBLE_INT_MAP = new HashMap<Double, Integer>();

    @AppSwitch(des = "泛型是<Double, Boolean> Map 开关", level = Level.p4)
    public static Map<Double, Boolean> DOUBLE_BOOL_MAP = new HashMap<Double, Boolean>();

    @AppSwitch(des = "泛型是<Double, String> Map 开关", level = Level.p4)
    public static Map<Double, String> DOUBLE_STRING_MAP = new HashMap<Double, String>();

    @AppSwitch(des = "泛型是<Double, Long> Map 开关", level = Level.p4)
    public static Map<Double, Long> DOUBLE_LONG_MAP = new HashMap<Double, Long>();

    @AppSwitch(des = "泛型是<Double, Float> Map 开关", level = Level.p4)
    public static Map<Double, Float> DOUBLE_FLOAT_MAP = new HashMap<Double, Float>();

    @AppSwitch(des = "泛型是<Double, Double> Map 开关", level = Level.p4)
    public static Map<Double, Double> DOUBLE_DOUBLE_MAP = new HashMap<Double, Double>();

    @AppSwitch(des = "泛型是<Double, Character> Map 开关", level = Level.p4)
    public static Map<Double, Character> DOUBLE_CHAR_MAP = new HashMap<Double, Character>();

    @AppSwitch(des = "泛型是<Double, Short> Map 开关", level = Level.p4)
    public static Map<Double, Short> DOUBLE_SHORT_MAP = new HashMap<Double, Short>();

    @AppSwitch(des = "泛型是<Double, Byte> Map 开关", level = Level.p4)
    public static Map<Double, Byte> DOUBLE_BYTE_MAP = new HashMap<Double, Byte>();

    @AppSwitch(des = "泛型是<Double, AtomicLong> Map 开关", level = Level.p4)
    public static Map<Double, AtomicLong> DOUBLE_ATOMICLONG_MAP = new HashMap<Double, AtomicLong>();

    @AppSwitch(des = "泛型是<Double, AtomicBoolean> Map 开关", level = Level.p4)
    public static Map<Double, AtomicBoolean> DOUBLE_ATOMICBOOL_MAP = new HashMap<Double, AtomicBoolean>();

    @AppSwitch(des = "泛型是<Double, AtomicInteger> Map 开关", level = Level.p4)
    public static Map<Double, AtomicInteger> DOUBLE_ATOMICINT_MAP = new HashMap<Double, AtomicInteger>();

    //<K, V>  <-->  <Float, ?>
    @AppSwitch(des = "泛型是<Float, Integer> Map 开关", level = Level.p4)
    public static Map<Float, Integer> FLOAT_INT_MAP = new HashMap<Float, Integer>();

    @AppSwitch(des = "泛型是<Float, Boolean> Map 开关", level = Level.p4)
    public static Map<Float, Boolean> FLOAT_BOOL_MAP = new HashMap<Float, Boolean>();

    @AppSwitch(des = "泛型是<Float, String> Map 开关", level = Level.p4)
    public static Map<Float, String> FLOAT_STRING_MAP = new HashMap<Float, String>();

    @AppSwitch(des = "泛型是<Float, Long> Map 开关", level = Level.p4)
    public static Map<Float, Long> FLOAT_LONG_MAP = new HashMap<Float, Long>();

    @AppSwitch(des = "泛型是<Float, Float> Map 开关", level = Level.p4)
    public static Map<Float, Float> FLOAT_FLOAT_MAP = new HashMap<Float, Float>();

    @AppSwitch(des = "泛型是<Float, Double> Map 开关", level = Level.p4)
    public static Map<Float, Double> FLOAT_DOUBLE_MAP = new HashMap<Float, Double>();

    @AppSwitch(des = "泛型是<Float, Character> Map 开关", level = Level.p4)
    public static Map<Float, Character> FLOAT_CHAR_MAP = new HashMap<Float, Character>();

    @AppSwitch(des = "泛型是<Float, Short> Map 开关", level = Level.p4)
    public static Map<Float, Short> FLOAT_SHORT_MAP = new HashMap<Float, Short>();

    @AppSwitch(des = "泛型是<Float, Byte> Map 开关", level = Level.p4)
    public static Map<Float, Byte> FLOAT_BYTE_MAP = new HashMap<Float, Byte>();

    @AppSwitch(des = "泛型是<Float, AtomicLong> Map 开关", level = Level.p4)
    public static Map<Float, AtomicLong> FLOAT_ATOMICLONG_MAP = new HashMap<Float, AtomicLong>();

    @AppSwitch(des = "泛型是<Float, AtomicBoolean> Map 开关", level = Level.p4)
    public static Map<Float, AtomicBoolean> FLOAT_ATOMICBOOL_MAP = new HashMap<Float, AtomicBoolean>();

    @AppSwitch(des = "泛型是<Float, AtomicInteger> Map 开关", level = Level.p4)
    public static Map<Float, AtomicInteger> FLOAT_ATOMICINT_MAP = new HashMap<Float, AtomicInteger>();

    //<K, V>  <-->  <Character, ?>
    @AppSwitch(des = "泛型是<Character, Integer> Map 开关", level = Level.p4)
    public static Map<Character, Integer> CHAR_INT_MAP = new HashMap<Character, Integer>();

    @AppSwitch(des = "泛型是<Character, Boolean> Map 开关", level = Level.p4)
    public static Map<Character, Boolean> CHAR_BOOL_MAP = new HashMap<Character, Boolean>();

    @AppSwitch(des = "泛型是<Character, String> Map 开关", level = Level.p4)
    public static Map<Character, String> CHAR_STRING_MAP = new HashMap<Character, String>();

    @AppSwitch(des = "泛型是<Character, Long> Map 开关", level = Level.p4)
    public static Map<Character, Long> CHAR_LONG_MAP = new HashMap<Character, Long>();

    @AppSwitch(des = "泛型是<Character, Float> Map 开关", level = Level.p4)
    public static Map<Character, Float> CHAR_FLOAT_MAP = new HashMap<Character, Float>();

    @AppSwitch(des = "泛型是<Character, Double> Map 开关", level = Level.p4)
    public static Map<Character, Double> CHAR_DOUBLE_MAP = new HashMap<Character, Double>();

    @AppSwitch(des = "泛型是<Character, Character> Map 开关", level = Level.p4)
    public static Map<Character, Character> CHAR_CHAR_MAP = new HashMap<Character, Character>();

    @AppSwitch(des = "泛型是<Character, Short> Map 开关", level = Level.p4)
    public static Map<Character, Short> CHAR_SHORT_MAP = new HashMap<Character, Short>();

    @AppSwitch(des = "泛型是<Character, Byte> Map 开关", level = Level.p4)
    public static Map<Character, Byte> CHAR_BYTE_MAP = new HashMap<Character, Byte>();

    @AppSwitch(des = "泛型是<Character, AtomicLong> Map 开关", level = Level.p4)
    public static Map<Character, AtomicLong> CHAR_ATOMICLONG_MAP = new HashMap<Character, AtomicLong>();

    @AppSwitch(des = "泛型是<Character, AtomicBoolean> Map 开关", level = Level.p4)
    public static Map<Character, AtomicBoolean> CHAR_ATOMICBOOL_MAP = new HashMap<Character, AtomicBoolean>();

    @AppSwitch(des = "泛型是<Character, AtomicInteger> Map 开关", level = Level.p4)
    public static Map<Character, AtomicInteger> CHAR_ATOMICINT_MAP = new HashMap<Character, AtomicInteger>();

    //<K, V>  <-->  <Byte, ?>
    @AppSwitch(des = "泛型是<Byte, Integer> Map 开关", level = Level.p4)
    public static Map<Byte, Integer> BYTE_INT_MAP = new HashMap<Byte, Integer>();

    @AppSwitch(des = "泛型是<Byte, Boolean> Map 开关", level = Level.p4)
    public static Map<Byte, Boolean> BYTE_BOOL_MAP = new HashMap<Byte, Boolean>();

    @AppSwitch(des = "泛型是<Byte, String> Map 开关", level = Level.p4)
    public static Map<Byte, String> BYTE_STRING_MAP = new HashMap<Byte, String>();

    @AppSwitch(des = "泛型是<Byte, Long> Map 开关", level = Level.p4)
    public static Map<Byte, Long> BYTE_LONG_MAP = new HashMap<Byte, Long>();

    @AppSwitch(des = "泛型是<Byte, Float> Map 开关", level = Level.p4)
    public static Map<Byte, Float> BYTE_FLOAT_MAP = new HashMap<Byte, Float>();

    @AppSwitch(des = "泛型是<Byte, Double> Map 开关", level = Level.p4)
    public static Map<Byte, Double> BYTE_DOUBLE_MAP = new HashMap<Byte, Double>();

    @AppSwitch(des = "泛型是<Byte, Character> Map 开关", level = Level.p4)
    public static Map<Byte, Character> BYTE_CHAR_MAP = new HashMap<Byte, Character>();

    @AppSwitch(des = "泛型是<Byte, Short> Map 开关", level = Level.p4)
    public static Map<Byte, Short> BYTE_SHORT_MAP = new HashMap<Byte, Short>();

    @AppSwitch(des = "泛型是<Byte, Byte> Map 开关", level = Level.p4)
    public static Map<Byte, Byte> BYTE_BYTE_MAP = new HashMap<Byte, Byte>();

    @AppSwitch(des = "泛型是<Byte, AtomicLong> Map 开关", level = Level.p4)
    public static Map<Byte, AtomicLong> BYTE_ATOMICLONG_MAP = new HashMap<Byte, AtomicLong>();

    @AppSwitch(des = "泛型是<Byte, AtomicBoolean> Map 开关", level = Level.p4)
    public static Map<Byte, AtomicBoolean> BYTE_ATOMICBOOL_MAP = new HashMap<Byte, AtomicBoolean>();

    @AppSwitch(des = "泛型是<Byte, AtomicInteger> Map 开关", level = Level.p4)
    public static Map<Byte, AtomicInteger> BYTE_ATOMICINT_MAP = new HashMap<Byte, AtomicInteger>();

    //<K, V>  <-->  <Short, ?>
    @AppSwitch(des = "泛型是<Short, Integer> Map 开关", level = Level.p4)
    public static Map<Short, Integer> SHORT_INT_MAP = new HashMap<Short, Integer>();

    @AppSwitch(des = "泛型是<Short, Boolean> Map 开关", level = Level.p4)
    public static Map<Short, Boolean> SHORT_BOOL_MAP = new HashMap<Short, Boolean>();

    @AppSwitch(des = "泛型是<Short, String> Map 开关", level = Level.p4)
    public static Map<Short, String> SHORT_STRING_MAP = new HashMap<Short, String>();

    @AppSwitch(des = "泛型是<Short, Long> Map 开关", level = Level.p4)
    public static Map<Short, Long> SHORT_LONG_MAP = new HashMap<Short, Long>();

    @AppSwitch(des = "泛型是<Short, Float> Map 开关", level = Level.p4)
    public static Map<Short, Float> SHORT_FLOAT_MAP = new HashMap<Short, Float>();

    @AppSwitch(des = "泛型是<Short, Double> Map 开关", level = Level.p4)
    public static Map<Short, Double> SHORT_DOUBLE_MAP = new HashMap<Short, Double>();

    @AppSwitch(des = "泛型是<Short, Character> Map 开关", level = Level.p4)
    public static Map<Short, Character> SHORT_CHAR_MAP = new HashMap<Short, Character>();

    @AppSwitch(des = "泛型是<Short, Short> Map 开关", level = Level.p4)
    public static Map<Short, Short> SHORT_SHORT_MAP = new HashMap<Short, Short>();

    @AppSwitch(des = "泛型是<Short, Byte> Map 开关", level = Level.p4)
    public static Map<Short, Byte> SHORT_BYTE_MAP = new HashMap<Short, Byte>();

    @AppSwitch(des = "泛型是<Short, AtomicLong> Map 开关", level = Level.p4)
    public static Map<Short, AtomicLong> SHORT_ATOMICLONG_MAP = new HashMap<Short, AtomicLong>();

    @AppSwitch(des = "泛型是<Short, AtomicBoolean> Map 开关", level = Level.p4)
    public static Map<Short, AtomicBoolean> SHORT_ATOMICBOOL_MAP = new HashMap<Short, AtomicBoolean>();

    @AppSwitch(des = "泛型是<Short, AtomicInteger> Map 开关", level = Level.p4)
    public static Map<Short, AtomicInteger> SHORT_ATOMICINT_MAP = new HashMap<Short, AtomicInteger>();

    //<K, V>  <-->  <AtomicInteger, ?>
    @AppSwitch(des = "泛型是<AtomicInteger, Integer> Map 开关", level = Level.p4)
    public static Map<AtomicInteger, Integer> ATOMICINT_INT_MAP = new HashMap<AtomicInteger, Integer>();

    @AppSwitch(des = "泛型是<AtomicInteger, Boolean> Map 开关", level = Level.p4)
    public static Map<AtomicInteger, Boolean> ATOMICINT_BOOL_MAP = new HashMap<AtomicInteger, Boolean>();

    @AppSwitch(des = "泛型是<AtomicInteger, String> Map 开关", level = Level.p4)
    public static Map<AtomicInteger, String> ATOMICINT_STRING_MAP = new HashMap<AtomicInteger, String>();

    @AppSwitch(des = "泛型是<AtomicInteger, Long> Map 开关", level = Level.p4)
    public static Map<AtomicInteger, Long> ATOMICINT_LONG_MAP = new HashMap<AtomicInteger, Long>();

    @AppSwitch(des = "泛型是<AtomicInteger, Float> Map 开关", level = Level.p4)
    public static Map<AtomicInteger, Float> ATOMICINT_FLOAT_MAP = new HashMap<AtomicInteger, Float>();

    @AppSwitch(des = "泛型是<AtomicInteger, Double> Map 开关", level = Level.p4)
    public static Map<AtomicInteger, Double> ATOMICINT_DOUBLE_MAP = new HashMap<AtomicInteger, Double>();

    @AppSwitch(des = "泛型是<AtomicInteger, Character> Map 开关", level = Level.p4)
    public static Map<AtomicInteger, Character> ATOMICINT_CHAR_MAP = new HashMap<AtomicInteger, Character>();

    @AppSwitch(des = "泛型是<AtomicInteger, Short> Map 开关", level = Level.p4)
    public static Map<AtomicInteger, Short> ATOMICINT_SHORT_MAP = new HashMap<AtomicInteger, Short>();

    @AppSwitch(des = "泛型是<AtomicInteger, Byte> Map 开关", level = Level.p4)
    public static Map<AtomicInteger, Byte> ATOMICINT_BYTE_MAP = new HashMap<AtomicInteger, Byte>();

    @AppSwitch(des = "泛型是<AtomicInteger, AtomicLong> Map 开关", level = Level.p4)
    public static Map<AtomicInteger, AtomicLong> ATOMICINT_ATOMICLONG_MAP = new HashMap<AtomicInteger, AtomicLong>();

    @AppSwitch(des = "泛型是<AtomicInteger, AtomicBoolean> Map 开关", level = Level.p4)
    public static Map<AtomicInteger, AtomicBoolean> ATOMICINT_ATOMICBOOL_MAP = new HashMap<AtomicInteger, AtomicBoolean>();

    @AppSwitch(des = "泛型是<AtomicInteger, AtomicInteger> Map 开关", level = Level.p4)
    public static Map<AtomicInteger, AtomicInteger> ATOMICINT_ATOMICINT_MAP = new HashMap<AtomicInteger, AtomicInteger>();

    //<K, V>  <-->  <AtomicBoolean, ?>
    @AppSwitch(des = "泛型是<AtomicBoolean, Integer> Map 开关", level = Level.p4)
    public static Map<AtomicBoolean, Integer> ATOMICBOOL_INT_MAP = new HashMap<AtomicBoolean, Integer>();

    @AppSwitch(des = "泛型是<AtomicBoolean, Boolean> Map 开关", level = Level.p4)
    public static Map<AtomicBoolean, Boolean> ATOMICBOOL_BOOL_MAP = new HashMap<AtomicBoolean, Boolean>();

    @AppSwitch(des = "泛型是<AtomicBoolean, String> Map 开关", level = Level.p4)
    public static Map<AtomicBoolean, String> ATOMICBOOL_STRING_MAP = new HashMap<AtomicBoolean, String>();

    @AppSwitch(des = "泛型是<AtomicBoolean, Long> Map 开关", level = Level.p4)
    public static Map<AtomicBoolean, Long> ATOMICBOOL_LONG_MAP = new HashMap<AtomicBoolean, Long>();

    @AppSwitch(des = "泛型是<AtomicBoolean, Float> Map 开关", level = Level.p4)
    public static Map<AtomicBoolean, Float> ATOMICBOOL_FLOAT_MAP = new HashMap<AtomicBoolean, Float>();

    @AppSwitch(des = "泛型是<AtomicBoolean, Double> Map 开关", level = Level.p4)
    public static Map<AtomicBoolean, Double> ATOMICBOOL_DOUBLE_MAP = new HashMap<AtomicBoolean, Double>();

    @AppSwitch(des = "泛型是<AtomicBoolean, Double> Map 开关", level = Level.p4)
    public static Map<AtomicBoolean, Character> ATOMICBOOL_CHAR_MAP = new HashMap<AtomicBoolean, Character>();

    @AppSwitch(des = "泛型是<AtomicBoolean, Short> Map 开关", level = Level.p4)
    public static Map<AtomicBoolean, Short> ATOMICBOOL_SHORT_MAP = new HashMap<AtomicBoolean, Short>();

    @AppSwitch(des = "泛型是<AtomicBoolean, Byte> Map 开关", level = Level.p4)
    public static Map<AtomicBoolean, Byte> ATOMICBOOL_BYTE_MAP = new HashMap<AtomicBoolean, Byte>();

    @AppSwitch(des = "泛型是<AtomicBoolean, AtomicLong> Map 开关", level = Level.p4)
    public static Map<AtomicBoolean, AtomicLong> ATOMICBOOL_ATOMICLONG_MAP = new HashMap<AtomicBoolean, AtomicLong>();

    @AppSwitch(des = "泛型是<AtomicBoolean, AtomicBoolean> Map 开关", level = Level.p4)
    public static Map<AtomicBoolean, AtomicBoolean> ATOMICBOOL_ATOMICBOOL_MAP = new HashMap<AtomicBoolean, AtomicBoolean>();

    @AppSwitch(des = "泛型是<AtomicBoolean, AtomicInteger> Map 开关", level = Level.p4)
    public static Map<AtomicBoolean, AtomicInteger> ATOMICBOOL_ATOMICINT_MAP = new HashMap<AtomicBoolean, AtomicInteger>();
}
