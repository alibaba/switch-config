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
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.alibaba.csp.switchcenter.annotation.AppSwitch;
import com.alibaba.csp.switchcenter.annotation.NameSpace;
import com.alibaba.csp.switchcenter.bean.Result;
import com.alibaba.csp.switchcenter.bean.Switch;
import com.alibaba.csp.switchcenter.command.CommandCenter;
import com.alibaba.csp.switchcenter.exception.SwitchCenterError;
import com.alibaba.csp.switchcenter.exception.SwitchCenterException;
import com.alibaba.csp.switchcenter.log.SwitchRecordLog;
import com.alibaba.csp.switchcenter.message.ErrorType;
import com.alibaba.csp.switchcenter.message.Message;
import com.alibaba.csp.switchcenter.util.StringUtil;
import com.alibaba.csp.switchcenter.util.SwitchUtil;
import com.alibaba.fastjson.JSON;

import static com.alibaba.csp.switchcenter.message.ErrorType.ATOMIC_TYPE_SET_ERROR;
import static com.alibaba.csp.switchcenter.message.ErrorType.EXECUTE_CALLBACK_FAIL;
import static com.alibaba.csp.switchcenter.message.ErrorType.EXECUTE_SWITCH_CALLBACK_FAIL;
import static com.alibaba.csp.switchcenter.message.ErrorType.JSON_DESERIALIZATION_ERROR;
import static com.alibaba.csp.switchcenter.message.ErrorType.NOT_SUPPORT_SWITCH_FILED_MODIFIERS;
import static com.alibaba.csp.switchcenter.message.ErrorType.PRIMITIVE_TYPE_NOT_FOUND;
import static com.alibaba.csp.switchcenter.message.ErrorType.REGISTER_SWITCH_CLASS_UNKNOWN_ERROR;
import static com.alibaba.csp.switchcenter.message.ErrorType.REGISTER_SWITCH_FIELD_UNKNOWN_ERROR;
import static com.alibaba.csp.switchcenter.message.ErrorType.SET_SWITCH_BY_REFLECT_FAIL;
import static com.alibaba.csp.switchcenter.message.ErrorType.SET_VALUE_UNKNOWN_ERROR;
import static com.alibaba.csp.switchcenter.message.ErrorType.SWITCH_CLASS_NOT_FOUND;
import static com.alibaba.csp.switchcenter.message.ErrorType.SWITCH_DO_NOT_EXITS;
import static com.alibaba.csp.switchcenter.message.ErrorType.SWITCH_VALUE_TYPE_NOT_MATCH;

/**
 * @author jielong.hjl
 * @author Eric Zhao
 */
public class SwitchManager {

    private static volatile boolean isFirstInit = true;

    private static final boolean ENABLE_SWITCH = Boolean.valueOf(System.getProperty("csp.switch.enable", "true"));

    private static final boolean IGNORE_TYPE_CHECK = Boolean.valueOf(System.getProperty("switch.type.ignore", "false"));

    /**
     * Register application's constant classes and start client. Throws
     * RuntimeException if client has been started.
     *
     * @param appName Application's name, case sensitive.
     */
    public static synchronized void init(String appName, Class<?> switchClasses) {
        if (!ENABLE_SWITCH) {
            return;
        }

        init(appName, new Class<?>[] {switchClasses});
    }

    /**
     * Register application's constant classes and start client. Throws
     * RuntimeException if client has been started.
     *
     * Application's
     *
     * @param appName name, case sensitive.
     */
    public static synchronized void init(String appName, Class<?>... switchClasses) {
        if (!ENABLE_SWITCH) {
            return;
        }

        CommandCenter.init();

        if (StringUtil.nullOrBlank(appName)) {
            throw new SwitchCenterException("Start client exception: Param appName can't be null or blank.");
        }

        if (isFirstInit) {
            SwitchContainer.DEFAULT_APP_NAME = appName;
            isFirstInit = false;
        }

        List<Class<?>> switchClassesL = new ArrayList<Class<?>>(Arrays.asList(switchClasses));

        List<Class<?>> noDupClasses = new ArrayList<Class<?>>(switchClasses.length);
        for (Class<?> clz : switchClassesL) {
            if (!SwitchContainer.clzExits(appName, clz)) {
                noDupClasses.add(clz);
            } else {
                SwitchRecordLog.warn("App {0}, Class : {1} has been registered already.", appName, clz);
            }
        }

        if (noDupClasses.size() < 1) {
            SwitchRecordLog.warn(SWITCH_CLASS_NOT_FOUND, "App {0}, no registrable switch class found.", appName);
            return;
        }

        Class<?>[] noDupClassesArray = noDupClasses.toArray(new Class<?>[noDupClasses.size()]);
        registerSwitch(appName, noDupClassesArray);

        SwitchRecordLog.info("App {0}, {1}, init done", appName, JSON.toJSONString(noDupClassesArray));
    }

    /**
     * Register switch value change listener.
     *
     * @param listener Value change listener.
     * @see Listener
     */
    public static void addListener(Listener listener) {
        SwitchContainer.add(listener);
    }

    /**
     * Remove registered value change listener.
     *
     * @param listener Registered value change listener.
     */
    public static void removeListener(Listener listener) {
        SwitchContainer.remove(listener);
    }

    /**
     * If specified switch exits.
     *
     * @param appName Application's name, case sensitive.
     * @param key     Field name and class name's combination.
     * @see SwitchUtil#calculateKey(String, String)
     */
    public static boolean contains(String appName, String key) {
        return SwitchContainer.contains(appName, key);
    }

    /**
     * Set specified switch's value with value string. Throws RuntimeException if
     * specifed switch do not exits or occur exception during change switch's value.
     *
     * @param appName Application's name, case sensitive.
     * @param key     Field name and class name's combination.
     * @param value
     * @return Returns <code>true</code> if change value success and execute
     * callback success, <code>false</code> if change value fail or execute
     * callback fail.
     */
    @SuppressWarnings("rawtypes")
    public static Result setValue(String appName, String key, String value) {
        Field field = SwitchContainer.getSwitchField(appName, key);
        Switch switchBean = SwitchContainer.getSwitchBean(appName, key);

        if (field == null || switchBean == null) {
            String message = String.format("appName:%s, switch key:%s not found", appName, key);
            SwitchRecordLog.warn(SWITCH_DO_NOT_EXITS, message);
            return Result.newFail(SWITCH_DO_NOT_EXITS);
        }

        Class<?> type = SwitchContainer.getSwitchBean(appName, key).getType();
        String oldValue = getStrValue(appName, key);
        try {
            if (setFieldValue(appName, key, type, value)) {
                if (executeCallback(appName, key, value)) {
                    switchBean.setLastModifiedTime(System.currentTimeMillis());
                    notifyListener(appName, key, value);
                    return Result.newSuccess(null);
                }

                // execute callback failed, rollback to old value.
                setFieldValue(appName, key, type, oldValue);
                return Result.newFail(EXECUTE_SWITCH_CALLBACK_FAIL);
            }

            // set value failed, rollback to old value
            setFieldValue(appName, key, type, oldValue);
            return Result.newFail(SET_VALUE_UNKNOWN_ERROR);
        } catch (RuntimeException e) {
            // set value failed, rollback to old value
            setFieldValue(appName, key, type, oldValue);

            if (e instanceof SwitchCenterException) {
                SwitchCenterException switchCenterException = (SwitchCenterException) e;
                return Result.newFail(switchCenterException.getErrorType());
            }

            SwitchRecordLog.error(SET_VALUE_UNKNOWN_ERROR,
                "set value exception, appName: " + appName + " key: " + key + " value:" + value, e);
            return Result.newFail(SET_VALUE_UNKNOWN_ERROR);

        }

    }

    /**
     * Get specified switch's value.
     *
     * @param appName Application's name, case sensitive.
     * @param key     Field name and class name's combination.
     * @return Return Specifed switch's value, <code>null</code> if switch do not
     * exits or filed not accessable.
     * @since 2.0.5
     */
    private static Object getValue(String appName, String key) {
        Method getMethod = SwitchContainer.getSwitchGetMethod(appName, key);

        if (getMethod != null) {// use field's getter method to get switch's value.

            try {
                return getMethod.invoke(null);
            } catch (Exception e) {
                // Do nothing
                SwitchRecordLog.warn("AppName : " + appName + " key : " + key + " not reachable.", e);
            }

        }

        Field field = SwitchContainer.getSwitchField(appName, key);

        if (field != null) {

            try {
                field.setAccessible(true);
                return field.get(null);
            } catch (Exception e) {
                SwitchRecordLog.info("AppName : " + appName + " key : " + key + " not reachable.", e);
            }

        }

        return null;
    }

    /**
     * Get specified switch's string value.
     * <p/>
     * <p>
     * Even If switch field type is Byte array, result won't be base64 string.
     * </p>
     *
     * @since 2.0.5
     */
    public static String getStrValue(String appName, String key) {
        Object valueObj = getValue(appName, key);
        Class<?> type = SwitchContainer.getSwitchBean(appName, key).getType();

        return SwitchUtil.handle(valueObj, type);
    }

    /**
     * Get application's real-time status switches collection.
     *
     * @param appName Application's name, case sensitive.
     * @since 2.0.5
     */
    public static Collection<Switch> getSwitchList(String appName) {
        Map<String, Switch> switchesMap = SwitchContainer.getSwitchBeanMapByAppName(appName);

        Iterator<String> iterator = switchesMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Switch bean = switchesMap.get(key);
            bean.setValue(getStrValue(appName, key));
        }

        return switchesMap.values();
    }

    /**
     * Get specified switch object.
     *
     * @param appName Application's name, case sensitive.
     * @param key     Field name and class name's combination.
     * @see SwitchUtil#calculateKey(String, String)
     * @since 2.0.5
     */
    public static Switch getSwitch(String appName, String key) {
        Switch pointSwitch = SwitchContainer.getSwitchBean(appName, key);

        if (pointSwitch == null) {
            return null;
        }

        pointSwitch.setValue(getStrValue(appName, key));
        return pointSwitch;
    }

    /**
     * Get application's switches real tine MD5 string.
     * <p/>
     * <p>
     * If switches value is same, MD5 value should be same too.
     * </p>
     *
     * @param appName Application's name, case sensitive.
     * @since 2.0.8
     */
    public static String monitor(String appName) {
        Collection<Switch> switches = getSwitchList(appName);
        return StringUtil.md5(SwitchUtil.toJSONString(switches));
    }

    private static synchronized void registerSwitch(String appName, Class<?>... switchClasses) {
        for (Class<?> switchClass : switchClasses) {
            try {
                registerField(appName, switchClass);
            } catch (SwitchCenterException e) {
                throw e;
            } catch (SwitchCenterError e) {
                throw e;
            } catch (Throwable e) {
                SwitchRecordLog.error(REGISTER_SWITCH_CLASS_UNKNOWN_ERROR,
                    "Register switch class %s" + switchClass + " occur unknown exception", e);
            }
        }
    }

    private static synchronized void registerField(String appName, Class<?> switchClass) {
        if (StringUtil.isEmpty(appName) || switchClass == null) {
            SwitchRecordLog.warn("appName or switchClass is null, unable to init switch");
            return;
        }

        Field[] fields = switchClass.getDeclaredFields();

        NameSpace nameSpace = switchClass.getAnnotation(NameSpace.class);
        String nameSpaceString = nameSpace == null ? switchClass.getName() : nameSpace.nameSpace();
        SwitchContainer.addAppNameSpace(appName, nameSpaceString);

        if (fields == null) {
            return;
        }

        for (Field field : fields) {
            field.setAccessible(true);
            AppSwitch annotation = field.getAnnotation(AppSwitch.class);
            if (annotation == null) {
                continue;
            }

            try {
                if (containsUnsupportModifier(field)) {
                    throw new SwitchCenterException(NOT_SUPPORT_SWITCH_FILED_MODIFIERS,
                        "field:" + field + "contains not support modifiers");
                }

                Object value = field.get(null);
                String name = field.getName();
                String defaultValue = SwitchUtil.toJSONString(value);
                String strValue = SwitchUtil.handle(value, field.getType());

                Switch switchBean = new Switch(name, strValue, nameSpaceString, annotation.valueDes(), annotation.des(),
                    defaultValue, SwitchUtil.getValueConfig(annotation.values()), annotation.level(),
                    field.getType());

                if (nameSpace != null) {
                    String[] nameSpaceTags = nameSpace.tags();
                    switchBean.addTags(nameSpaceTags);
                }

                // set field tags
                String[] fieldTags = annotation.tags();
                switchBean.addTags(fieldTags);

                java.lang.reflect.Type genricType = getFieldGeneric(field);
                switchBean.setGeneric(genricType);

                Class<? extends SwitchCallback> callback = annotation.callback();

                if (callback != SwitchCallback.class) {
                    switchBean.setCallback(callback);
                }

                String key = SwitchUtil.calculateKey(name, nameSpaceString);

                switchBean.setLastModifiedTime(System.currentTimeMillis());
                SwitchContainer.register(appName, key, switchBean, defaultValue, field,
                    SwitchUtil.getGetterMethod(field, switchClass), SwitchUtil.getSetterMethod(field, switchClass));
                executeCallback(appName, key, defaultValue);

                SwitchRecordLog.info("App {0}, {1}, field registered", appName, key);
            } catch (Exception e) {
                if (e instanceof SwitchCenterException) {
                    SwitchCenterException switchCenterException = (SwitchCenterException) e;
                    SwitchRecordLog.error(switchCenterException.getErrorType(),
                        "Register field " + field.getName() + " exception, ", switchCenterException);
                }
                SwitchRecordLog.error(REGISTER_SWITCH_FIELD_UNKNOWN_ERROR,
                    "Register field " + field.getName() + " occur exception, ", e);
            }
        }

        SwitchContainer.addClz(appName, switchClass);
    }

    private static boolean containsUnsupportModifier(Field field) {
        // return false;
        if (field == null) {
            return false;
        }
        int modifier = field.getModifiers();

        // non-static || final field
        if ((modifier & Modifier.STATIC) != Modifier.STATIC || (modifier & Modifier.FINAL) == Modifier.FINAL) {
            return true;
        }

        return false;
    }

    private static java.lang.reflect.Type getFieldGeneric(Field field) {
        try {
            Object value = field.get(null);
            Class<?> type = field.getType();

            if (!type.isPrimitive()) {
                Class<?> clz = value.getClass();

                // handle AnonynousClass
                for (; clz.isAnonymousClass() || clz.isMemberClass(); ) {
                    clz = clz.getSuperclass();
                }

                java.lang.reflect.Type rawType = (java.lang.reflect.Type) clz;
                java.lang.reflect.Type genericType = field.getGenericType();

                try {
                    ParameterizedType parameterizedType = (ParameterizedType) genericType;
                    return  new TypeReference(rawType, parameterizedType.getActualTypeArguments());
                } catch (ClassCastException e) {
                    return genericType;
                }
            }
        } catch (Exception e) {
            return field.getType();
        }
        return null;
    }

    private static boolean executeCallback(String appName, String key, String value) {
        boolean success = true;
        Class<? extends SwitchCallback> callbackClass = SwitchContainer.getSwitchBean(appName, key).getCallback();

        if (callbackClass != null) {
            try {
                SwitchCallback callback = (SwitchCallback) callbackClass.newInstance();
                if (callback != null) {
                    String[] keys = SwitchUtil.splitKey(key);
                    value = SwitchUtil.escapeStringJsonQuotes(value);
                    callback.handleSwitch(keys[0], keys[1], value);
                }
            } catch (Exception e) {
                success = false;
                SwitchRecordLog.error(EXECUTE_CALLBACK_FAIL,
                    "Execute switch callback " + callbackClass + " failed", e);
            }
        }

        return success;
    }

    private static boolean setFieldValue(String appName, String key, Class<?> type, String value) {
        java.lang.reflect.Type generic = SwitchContainer.getSwitchBean(appName, key).getGeneric();

        // check if string value can parse to field value rightly.
        if (!valueTypeMatchFieldType(value, type, generic)) {
            String message = String.format("appName:%s, key:%s, value:%s value type matched false", appName, key,
                value);
            SwitchRecordLog.warn(SWITCH_VALUE_TYPE_NOT_MATCH, message);
            throw new SwitchCenterException(SWITCH_VALUE_TYPE_NOT_MATCH, message);
        }

        Object newValue = parseValue(value, type, generic);
        String oldValue = getStrValue(appName, key);

        if (type.isAssignableFrom(AtomicBoolean.class) || type.isAssignableFrom(AtomicInteger.class)
            || type.isAssignableFrom(AtomicLong.class)) {
            return setAtomicTypeFieldValue(appName, key, type, newValue);
        }

        Method setMethod = SwitchContainer.getSwitchSetMethod(appName, key);
        if (setMethod != null) {
            try {
                setMethod.invoke(null, newValue);
                SwitchRecordLog
                    .info(Message.buildMessage(appName, key, value, oldValue, ErrorType.SET_SWITCH_VALUE_SUCCESS));
                return true;
            } catch (Exception e) {
                // Do nothing
                SwitchRecordLog.warn(ErrorType.SET_SWITCH_BY_SET_METHOD_FAIL,
                    "appName:{0}, key:{1} set switch by set method fail", appName, key);
            }
        }

        Field field = SwitchContainer.getSwitchField(appName, key);
        try {
            field.setAccessible(true);
            field.set(null, newValue);
            SwitchRecordLog
                .info(Message.buildMessage(appName, key, value, oldValue, ErrorType.SET_SWITCH_VALUE_SUCCESS));
            return true;
        } catch (Exception e) {
            String message = "set switch value by reflect fail, appName:" + appName + ", key:" + key + ", value:"
                + value + ", oldValue:" + oldValue;
            SwitchRecordLog.error(SET_SWITCH_BY_REFLECT_FAIL, message, e);
            throw new SwitchCenterException(SET_SWITCH_BY_REFLECT_FAIL, message);
        }
    }

    private static boolean setAtomicTypeFieldValue(String appName, String key, Class<?> type, Object value) {
        Field field = SwitchContainer.getSwitchField(appName, key);
        try {
            Object o = field.get(null);
            if (type.isAssignableFrom(AtomicBoolean.class)) {
                ((AtomicBoolean) o).set(((AtomicBoolean) value).get());
            } else if (type.isAssignableFrom(AtomicLong.class)) {
                ((AtomicLong) o).set(((AtomicLong) value).get());
            } else if (type.isAssignableFrom(AtomicInteger.class)) {
                ((AtomicInteger) o).set(((AtomicInteger) value).get());
            }
            return true;
        } catch (Throwable e) {
            SwitchRecordLog.error(ATOMIC_TYPE_SET_ERROR, "handler atomic type error!", e);
            throw new SwitchCenterException(ATOMIC_TYPE_SET_ERROR, "set atomic type error, value:" + value);
        }
    }

    private static boolean valueTypeMatchFieldType(String value, Class<?> type, java.lang.reflect.Type generic) {
        if (IGNORE_TYPE_CHECK) {
            return true;
        }

        Object parsedValue = parseValue(value, type, generic);
        if (parsedValue == null) {
            return false;
        }

        // double or float string, escape 100.0 == 100
        if (typeIsDoubleOrFloat(type)) {
            return true;
        }

        Class<?> parsedType = parsedValue.getClass();
        String parsedValueStr = SwitchUtil.handle(parsedValue, type);
        boolean valueEquals = SwitchUtil.jsonEquals(value, parsedValueStr);

        if (type.isPrimitive()) {
            return valueEquals && SwitchUtil.primitiveTypeEquals(type, parsedType);
        }

        return valueEquals && type.isAssignableFrom(parsedType);
    }

    private static boolean typeIsDoubleOrFloat(Class<?> type) {
        if (type.isPrimitive()) {
            return float.class.equals(type) || double.class.equals(type);
        }

        return Float.class.equals(type) || Float.class.isAssignableFrom(type) || Double.class.equals(type)
            || Double.class.isAssignableFrom(type);
    }

    private static Object parseValue(String value, Class<?> type, java.lang.reflect.Type generic) {
        /* handle primitive type */
        if (type.isPrimitive()) {
            if (int.class.equals(type)) {
                return Integer.parseInt(value);
            } else if (boolean.class.equals(type)) {
                return Boolean.parseBoolean(value);
            } else if (long.class.equals(type)) {
                return Long.parseLong(value);
            } else if (double.class.equals(type)) {
                return Double.parseDouble(value);
            } else if (float.class.equals(type)) {
                return Float.parseFloat(value);
            } else if (byte.class.equals(type)) {
                return Byte.parseByte(value);
            } else if (short.class.equals(type)) {
                return Short.parseShort(value);
            } else if (char.class.equals(type)) {
                char[] array = value.toCharArray();
                return array.length > 0 ? array[1] : null;
            }

            SwitchRecordLog.warn(PRIMITIVE_TYPE_NOT_FOUND, "value:{0}, type:{1} not found", value, type);
            throw new SwitchCenterException(PRIMITIVE_TYPE_NOT_FOUND, "primitive type:" + type + "not found");
        }

        try {
            return JSON.parseObject(value, generic);
        } catch (Exception e) {
            SwitchRecordLog.error(JSON_DESERIALIZATION_ERROR, "parse json: " + value + " to object occur exception", e);
            throw new SwitchCenterException(JSON_DESERIALIZATION_ERROR,
                "parse json: " + value + " to object occur exception");
        }
    }

    private static void notifyListener(String appName, String key, String value) {
        String[] keys = SwitchUtil.splitKey(key);
        value = SwitchUtil.escapeStringJsonQuotes(value);

        Iterator<Listener> iterator = SwitchContainer.getListeners();
        while (iterator.hasNext()) {
            try {
                Listener listener = iterator.next();
                listener.valueChange(appName, keys[0], keys[1], value);
            } catch (Throwable t) {
                SwitchRecordLog.info("execute listener error!", t);
            }
        }
    }
}
