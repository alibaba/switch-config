package com.alibaba.csp.switchcenter.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.alibaba.csp.switchcenter.annotation.AppSwitch;
import com.alibaba.csp.switchcenter.bean.Switch.Level;

/**
 * @author jielong.hjl
 */
public class CustomTypeSwitch {

    @AppSwitch(des = "自定义Person 类型开关", level = Level.p1)
    public static Person personTypeSwitch = new Person("switch", 22, false, 1000.0f);

    @AppSwitch(des = "自定义Person数组 类型开关", level = Level.p1)
    public static Person[] personArraySwitch = {};

    @AppSwitch(des = "自定义Person List 类型开关", level = Level.p1)
    public static List<Person> personListSwitch = new ArrayList<Person>();

    @AppSwitch(des = "自定义Person Set 类型开关", level = Level.p1)
    public static Set<Person> personSetSwitch = new HashSet<Person>();

    @AppSwitch(des = "<Person, Person> Map 类型开关", level = Level.p1)
    public static Map<Person, Person> PERSON_PERSON_MAP = new HashMap<Person, Person>();

	/*
	 * Map<?, Person> 
	 */

    @AppSwitch(des = "<Integer, Person> Map 类型开关", level = Level.p1)
    public static Map<Integer, Person> INT_PERSON_MAP = new HashMap<Integer, Person>();

//	@AppSwitch(des="<Boolean, Person> Map 类型开关", level=Level.p1)
//	public static Map<Boolean, Person> 		 BOOL_PERSON_MAP		= 	new HashMap<Boolean, Person>();

    @AppSwitch(des = "<String, Person> Map 类型开关", level = Level.p1)
    public static Map<String, Person> STRING_PERSON_MAP = new HashMap<String, Person>();

    @AppSwitch(des = "<Long, Person> Map 类型开关", level = Level.p1)
    public static Map<Long, Person> LONG_PERSON_MAP = new HashMap<Long, Person>();

    @AppSwitch(des = "<Double, Person> Map 类型开关", level = Level.p1)
    public static Map<Double, Person> DOUBLE_PERSON_MAP = new HashMap<Double, Person>();

    @AppSwitch(des = "<Float, Person> Map 类型开关", level = Level.p1)
    public static Map<Float, Person> FLOAT_PERSON_MAP = new HashMap<Float, Person>();

    @AppSwitch(des = "<Character, Person> Map 类型开关", level = Level.p1)
    public static Map<Character, Person> CHAR_PERSON_MAP = new HashMap<Character, Person>();

    @AppSwitch(des = "<Short, Person> Map 类型开关", level = Level.p1)
    public static Map<Short, Person> SHORT_PERSON_MAP = new HashMap<Short, Person>();

    @AppSwitch(des = "<Byte, Person> Map 类型开关", level = Level.p1)
    public static Map<Byte, Person> BYTE_PERSON_MAP = new HashMap<Byte, Person>();

    @AppSwitch(des = "<AtomicInteger, Person> Map 类型开关", level = Level.p1)
    public static Map<AtomicInteger, Person> ATOMICINT_PERSON_MAP = new HashMap<AtomicInteger, Person>();

    @AppSwitch(des = "<AtomicBoolean, Person> Map 类型开关", level = Level.p1)
    public static Map<AtomicBoolean, Person> ATOMICBOOL_PERSON_MAP = new HashMap<AtomicBoolean, Person>();

    @AppSwitch(des = "<AtomicLong, Person> Map 类型开关", level = Level.p1)
    public static Map<AtomicLong, Person> ATOMICLONG_PERSON_MAP = new HashMap<AtomicLong, Person>();
	
	/*
	 * Map<Person, Integer> 
	 */

    @AppSwitch(des = "<Person, Integer> Map 类型开关", level = Level.p1)
    public static Map<Person, Integer> PERSON_INT_MAP = new HashMap<Person, Integer>();

    @AppSwitch(des = "<Person, Boolean> Map 类型开关", level = Level.p1)
    public static Map<Person, Boolean> PERSON_BOOL_MAP = new HashMap<Person, Boolean>();

    @AppSwitch(des = "<Person, String> Map 类型开关", level = Level.p1)
    public static Map<Person, String> PERSON_STRING_MAP = new HashMap<Person, String>();

    @AppSwitch(des = "<Person, Double> Map 类型开关", level = Level.p1)
    public static Map<Person, Double> PERSON_DOUBLE_MAP = new HashMap<Person, Double>();

    @AppSwitch(des = "<Person, Long> Map 类型开关", level = Level.p1)
    public static Map<Person, Long> PERSON_LONG_MAP = new HashMap<Person, Long>();

    @AppSwitch(des = "<Person, Float> Map 类型开关", level = Level.p1)
    public static Map<Person, Float> PERSON_FLOAT_MAP = new HashMap<Person, Float>();

    @AppSwitch(des = "<Person, Character> Map 类型开关", level = Level.p1)
    public static Map<Person, Character> PERSON_CHAR_MAP = new HashMap<Person, Character>();

    @AppSwitch(des = "<Person, Short> Map 类型开关", level = Level.p1)
    public static Map<Person, Short> PERSON_SHORT_MAP = new HashMap<Person, Short>();

    @AppSwitch(des = "<Person, Byte> Map 类型开关", level = Level.p1)
    public static Map<Person, Byte> PERSON_BYTE_MAP = new HashMap<Person, Byte>();

    @AppSwitch(des = "<Person, AtomicInteger> Map 类型开关", level = Level.p1)
    public static Map<Person, AtomicInteger> PERSON_ATOMICINT_MAP = new HashMap<Person, AtomicInteger>();

    @AppSwitch(des = "<Person, AtomicBoolean> Map 类型开关", level = Level.p1)
    public static Map<Person, AtomicBoolean> PERSON_ATOMICBOOL_MAP = new HashMap<Person, AtomicBoolean>();

    @AppSwitch(des = "<Person, AtomicLong> Map 类型开关", level = Level.p1)
    public static Map<Person, AtomicLong> PERSON_ATOMICLONG_MAP = new HashMap<Person, AtomicLong>();

}
