/*
 * Copyright 1999-2020 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.csp.switchcenter.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.csp.switchcenter.bean.Switch;

/**
 * @author jielong.hjl
 *
 * All mehtod add synchronized made this class to be thead safe. switch
 * op is not frequent, so donn't care about performace here. youji.zj
 */
public class SwitchContainer {

    /**
     * default application name
     */
    public static String DEFAULT_APP_NAME = null;

    /**
     * switches's information map
     */
    private static Map<String, Map<String, Switch>> switches = new HashMap<String, Map<String, Switch>>();

    /**
     * switches's field map
     */
    private static Map<String, Map<String, Field>> switchFields = new HashMap<String, Map<String, Field>>();

    /**
     * switches's default value map
     */
    private static Map<String, Map<String, String>> switchFieldsDefaultValue
        = new HashMap<String, Map<String, String>>();

    /**
     * switches's setter method map
     */
    private static Map<String, Map<String, Method>> switchSetMethods = new HashMap<String, Map<String, Method>>();

    /**
     * switches's getter method map
     */
    private static Map<String, Map<String, Method>> switchGetMethods = new HashMap<String, Map<String, Method>>();

    /**
     * value change listeners
     */
    private static List<Listener> listeners = new ArrayList<Listener>();

    private static Map<String, List<String>> appNameSpacesMap = new HashMap<String, List<String>>();

    private static Map<String, List<String>> appConfigClassMap = new HashMap<String, List<String>>();

    /**
     * Register switch information, switch field, getter/setter method.
     *
     * @param appName
     * @param key
     * @param switchBean
     * @param defaultValue
     * @param field
     * @param getMethod
     * @param setMethod
     */
    public static synchronized void register(String appName, String key, Switch switchBean, String defaultValue,
                                             Field field, Method getMethod, Method setMethod) {
        registerSwitchBean(appName, key, switchBean);
        registerSwitchDefaultValue(appName, key, defaultValue);
        registerSwitchField(appName, key, field);
        registerSwitchGetMethod(appName, key, getMethod);
        registerSwitchSetMethod(appName, key, setMethod);
    }

    /**
     * Register switch bean
     *
     * @param appName
     * @param key
     * @param switchBean
     */
    public static synchronized void registerSwitchBean(String appName, String key, Switch switchBean) {
        Map<String, Switch> switchMap = switches.get(appName);

        if (switchMap == null) {
            switchMap = new HashMap<String, Switch>();
        }

        switchMap.put(key, switchBean);
        switches.put(appName, switchMap);
    }

    /**
     * Get switch bean
     *
     * @param appName
     * @param key
     * @return
     */
    public static synchronized Switch getSwitchBean(String appName, String key) {
        return contains(appName, key) ? (switches.get(appName) == null ? null : switches.get(appName).get(key)) : null;
    }

    /**
     * @param appName
     * @return
     */
    public static synchronized Map<String, Switch> getSwitchBeanMapByAppName(String appName) {
        Map<String, Switch> appSwitches = switches.get(appName);
        return appSwitches != null ? appSwitches : new HashMap<String, Switch>();
    }

    /**
     * @param appName
     * @param key
     * @param switchField
     */
    public static synchronized void registerSwitchField(String appName, String key, Field switchField) {
        Map<String, Field> switchFieldMap = switchFields.get(appName);

        if (switchFieldMap == null) {
            switchFieldMap = new HashMap<String, Field>();
        }

        switchFieldMap.put(key, switchField);
        switchFields.put(appName, switchFieldMap);
    }

    /**
     * @param appName
     * @param key
     * @return
     */
    public static synchronized Field getSwitchField(String appName, String key) {
        return contains(appName, key) ? (switchFields.get(appName) == null ? null : switchFields.get(appName).get(key))
            : null;
    }

    /**
     * @param appName
     * @param key
     * @param switchDefaultValue
     */
    public static synchronized void registerSwitchDefaultValue(String appName, String key, String switchDefaultValue) {
        Map<String, String> switchDefaultValueMap = switchFieldsDefaultValue.get(appName);

        if (switchDefaultValueMap == null) {
            switchDefaultValueMap = new HashMap<String, String>();
        }

        switchDefaultValueMap.put(key, switchDefaultValue);
        switchFieldsDefaultValue.put(appName, switchDefaultValueMap);
    }

    /**
     * @param appName
     * @param key
     * @return
     */
    public static synchronized String getSwitchDefaultValue(String appName, String key) {
        return contains(appName, key) ? (switchFieldsDefaultValue.get(appName) == null ? null
            : switchFieldsDefaultValue.get(appName).get(key)) : null;
    }

    /**
     * @param appName
     * @return
     */
    public static synchronized Map<String, String> getSwitchesDefaultValuesByAppName(String appName) {
        return switchFieldsDefaultValue.get(appName);
    }

    /**
     * @param appName
     * @param key
     * @param getMethod
     */
    public static synchronized void registerSwitchGetMethod(String appName, String key, Method getMethod) {
        Map<String, Method> switchGetMethodMap = switchGetMethods.get(appName);

        if (switchGetMethodMap == null) {
            switchGetMethodMap = new HashMap<String, Method>();
        }

        switchGetMethodMap.put(key, getMethod);
        switchGetMethods.put(appName, switchGetMethodMap);
    }

    /**
     * @param appName
     * @param key
     * @return
     */
    public static synchronized Method getSwitchGetMethod(String appName, String key) {
        return contains(appName, key)
            ? (switchGetMethods.get(appName) == null ? null : switchGetMethods.get(appName).get(key))
            : null;
    }

    /**
     * @param appName
     * @param key
     * @param setMethod
     */
    public static synchronized void registerSwitchSetMethod(String appName, String key, Method setMethod) {
        Map<String, Method> switchSetMethodMap = switchSetMethods.get(appName);

        if (switchSetMethodMap == null) {
            switchSetMethodMap = new HashMap<String, Method>();
        }

        switchSetMethodMap.put(key, setMethod);
        switchSetMethods.put(appName, switchSetMethodMap);
    }

    /**
     * @param appName
     * @param key
     * @return
     */
    public static synchronized Method getSwitchSetMethod(String appName, String key) {
        return contains(appName, key)
            ? (switchSetMethods.get(appName) == null ? null : switchSetMethods.get(appName).get(key))
            : null;
    }

    /**
     * @param appName
     * @param key
     * @return
     */
    public static synchronized boolean contains(String appName, String key) {
        return switches.containsKey(appName) && switches.get(appName).containsKey(key);
    }

    /**
     * @param listener
     */
    public static synchronized void add(Listener listener) {
        listeners.add(listener);
    }

    /**
     * @param listener
     */
    public static synchronized void remove(Listener listener) {
        listeners.remove(listener);
    }

    /**
     * @return
     */
    public static synchronized Iterator<Listener> getListeners() {
        return listeners.iterator();
    }

    /**
     * @return
     */
    public static synchronized Set<String> getAppNames() {
        return switches != null ? switches.keySet() : new HashSet<String>();
    }

    public static synchronized void addAppNameSpace(String appName, String nameSpace) {
        List<String> nameSpaces = appNameSpacesMap.get(appName);

        if (nameSpaces == null) {
            nameSpaces = new ArrayList<String>();
        }

        nameSpaces.add(nameSpace);
        appNameSpacesMap.put(appName, nameSpaces);
    }

    public static synchronized List<String> getAppNameSpaces(String appName) {
        return appNameSpacesMap.containsKey(appName) ? appNameSpacesMap.get(appName) : new ArrayList<String>();
    }

    public static synchronized boolean containsNameSpace(String appName, String nameSpace) {
        List<String> nameSpaceSet = appNameSpacesMap.containsKey(appName) ? appNameSpacesMap.get(appName)
            : new ArrayList<String>();
        return nameSpaceSet.contains(nameSpace);
    }

    public static synchronized void addClz(String appName, Class<?> clz) {
        String clzName = getClzName(clz);
        List<String> clzs = appConfigClassMap.get(appName);

        if (clzs == null) {
            clzs = new ArrayList<String>();
        }

        clzs.add(clzName);
        appConfigClassMap.put(appName, clzs);
    }

    public static synchronized boolean clzExits(String appName, Class<?> clz) {
        return appConfigClassMap.containsKey(appName) ? appConfigClassMap.get(appName).contains(getClzName(clz))
            : false;
    }

    public static List<Field> getFields(String appName) {
        Map<String, Field> switchFieldMap = switchFields.get(appName);

        if (switchFieldMap == null) {
            return new ArrayList<Field>(0);
        }

        return new ArrayList<Field>(switchFieldMap.values());
    }

    private static String getClzName(Class<?> clz) {
        return clz.getName() + clz.hashCode();
    }
}
