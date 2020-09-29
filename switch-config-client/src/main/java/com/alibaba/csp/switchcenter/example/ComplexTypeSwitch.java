package com.alibaba.csp.switchcenter.example;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

import com.alibaba.csp.switchcenter.annotation.AppSwitch;
import com.alibaba.csp.switchcenter.bean.Switch.Level;

/**
 * @author jielong.hjl
 */
public class ComplexTypeSwitch {

	/*
	 * java.util.Date
	 */

    @SuppressWarnings("deprecation")
    @AppSwitch(des = "Date类型开关", level = Level.p1)
    public static Date dateTypeSwitch = new Date(114, 6, 3);

    @SuppressWarnings("deprecation")
    @AppSwitch(des = "Date数组类型开关", level = Level.p1)
    public static Date[] dateTypeArraySwitch = {new Date(114, 6, 3), new Date(115, 0, 30)};

    @AppSwitch(des = "泛型为Date的List类型开关", level = Level.p1)
    public static List<Date> DATE_ARRAYLIST = new ArrayList<Date>();

    @AppSwitch(des = "泛型为<String, Date>的Map类型开关", level = Level.p1)
    public static Map<String, Date> STRING_DATE_HASHMAP = new HashMap<String, Date>();

    /*
     * Big type
     */
    @AppSwitch(des = "BigInteger类型开关", level = Level.p1)
    public static BigInteger bigIntegerTypeSwitch = BigInteger.valueOf(38888);

    @AppSwitch(des = "BigDecimal类型开关", level = Level.p1)
    public static BigDecimal bigDecimalTypeSwitch = BigDecimal.valueOf(3.00000000001);

    /*
     * Enum type
     */
    @AppSwitch(des = "枚举类型开关", level = Level.p1)
    public static EnumType enumTypeSwitch = EnumType.ITEM1;
	
	/*
	 * java.util.List<?>
	 */

    @AppSwitch(des = "泛型为Integer的LinkedList", level = Level.p1)
    public static List<Integer> INT_LINKEDLIST = new LinkedList<Integer>();

    @AppSwitch(des = "泛型为Integer的Vector", level = Level.p1)
    public static Vector<Integer> INT_VECTOR = new Vector<Integer>();

    @AppSwitch(des = "泛型为Integer的Stack", level = Level.p1)
    public static Stack<Integer> INT_STACK = new Stack<Integer>();

    @AppSwitch(des = "泛型为List<Integer>的ArrayList", level = Level.p1)
    public static List<List<Integer>> LIST_INT_ARRAYLIST = new ArrayList<List<Integer>>();

    @AppSwitch(des = "泛型为List<Integer>的LinkedList", level = Level.p1)
    public static List<List<Integer>> LIST_INT_LINKEDLIST = new LinkedList<List<Integer>>();

    @AppSwitch(des = "泛型为LinkedList<Integer>的LinkedList", level = Level.p1)
    public static List<LinkedList<Integer>> LINKEDLIST_INT_ARRAYLIST = new ArrayList<LinkedList<Integer>>();

    @AppSwitch(des = "泛型为List<Person>的ArrayList", level = Level.p1)
    public static List<List<Person>> LIST_PERSON_ARRAYLIST = new ArrayList<List<Person>>();

    /**
     * java.util.Map<?, ?>
     */

    @AppSwitch(des = "泛型为<Integer, Map<String,Integer>>的HashMap", level = Level.p1)
    public static Map<Integer, Map<String, Integer>> INT_MAP_HASHMAP = new HashMap<Integer, Map<String, Integer>>();

    @AppSwitch(des = "泛型为<Integer, Person>的HashMap", level = Level.p1)
    public static Map<Integer, Person> INT_PERSON_HASHMAP = new HashMap<Integer, Person>();

    @AppSwitch(des = "泛型为<Map<String, Integer>, Map<String, Integer>>的HashMap", level = Level.p1)
    public static Map<Integer, Map<String, Map<String, Integer>>> MAP_MAP_HASHMAP = new HashMap<Integer, Map<String, Map<String, Integer>>>();

}	
