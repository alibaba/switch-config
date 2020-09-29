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
package com.alibaba.csp.switchcenter.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.alibaba.csp.switchcenter.bean.Switch;
import com.alibaba.csp.switchcenter.bean.ValueConfig;
import com.alibaba.csp.switchcenter.core.SwitchContainer;
import com.alibaba.csp.switchcenter.core.SwitchManager;
import com.alibaba.csp.switchcenter.json.JSONParser;
import com.alibaba.csp.switchcenter.json.JSONString;
import com.alibaba.csp.switchcenter.json.comparator.DefaultComparator;
import com.alibaba.csp.switchcenter.json.comparator.JSONComparator;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author jielong.hjl
 */
public class SwitchUtil {

    private static JSONComparator comparator = new DefaultComparator();

    public static String getVersion() {
        String version = "0.0.0.0";
        try {
            String[] versionInfo = SwitchManager.class.getProtectionDomain().getCodeSource().getLocation().toString()
                .split("/");
            version = versionInfo[versionInfo.length - 1];
        } catch (Throwable t) {
        }
        return version;
    }

    /**
     * remove string type json string beginning and ending quotes
     *
     * @param stringJson
     * @return
     */
    public static String escapeStringJsonQuotes(String stringJson) {
        if (StringUtil.nullOrBlank(stringJson)) {
            return stringJson;
        }
        try {
            if (stringJson.startsWith("\"") && stringJson.endsWith("\"")) {
                return stringJson.substring(1, stringJson.length() - 1);
            }
        } catch (IndexOutOfBoundsException e) {
        }
        return stringJson;
    }

    /**
     * @param name
     * @param nameSpace
     * @return
     */
    public static String calculateKey(String name, String nameSpace) {
        if (StringUtil.nullOrBlank(nameSpace)) {
            return name;
        } else {
            StringBuilder sb = new StringBuilder(nameSpace).append(".").append(name);

            return sb.toString();
        }
    }

    /**
     * @param key
     * @return [0]: namespace, [1]:name
     */
    public static String[] splitKey(String key) {
        String[] keys = new String[2];

        if (!StringUtil.nullOrBlank(key) && key.contains(".")) {
            int index = key.lastIndexOf(".");
            keys[0] = key.substring(0, index);
            keys[1] = index < key.length() - 1 ? key.substring(index + 1, key.length()) : "";
        }

        return keys;
    }

    /**
     * @param field
     * @param config
     * @return
     */
    public static Method getGetterMethod(Field field, Class<?> config) {
        String name = getMethodName(field.getName());

        Method method = null;
        try {
            method = config.getDeclaredMethod("get" + name);
        } catch (Exception e) {
            // do nothing
        }
        if (method == null) {
            try {
                method = config.getDeclaredMethod("is" + name);
            } catch (Exception e) {
                // do nothing
            }
        }

        return method;
    }

    /**
     * @param field
     * @param config
     * @return
     */
    public static Method getSetterMethod(Field field, Class<?> config) {
        String name = getMethodName(field.getName());

        Method method = null;
        try {
            method = config.getDeclaredMethod("set" + name, field.getType());
        } catch (Exception e) {
            // do nothing
        }

        return method;
    }

    /**
     * Get type string
     *
     * @param
     * @return
     */
    public static String getTypeString(Class<?> clz) {

        if (clz == int.class || clz == Integer.class) {
            return "int";
        } else if (clz == double.class || clz == Double.class) {
            return "double";
        } else if (clz == long.class || clz == Long.class) {
            return "long";
        } else if (clz == float.class || clz == Float.class) {
            return "float";
        } else if (clz == boolean.class || clz == Boolean.class) {
            return "boolean";
        } else if (clz == char.class || clz == Character.class) {
            return "char";
        } else if (clz == short.class || clz == Short.class) {
            return "short";
        } else if (clz == byte.class || clz == Byte.class) {
            return "byte";
        } else if (clz == String.class) {
            return "string";
        } else {
            return "object";
        }

    }

    /**
     * @param values
     * @return
     */
    public static ValueConfig[] getValueConfig(String[] values) {
        if (values != null && values.length > 0) {
            ValueConfig[] configs = new ValueConfig[values.length];

            for (int i = 0; i < values.length; i++) {
                String[] c = values[i].split(",");

                if (c.length == 2) {
                    configs[i] = new ValueConfig(c[0], c[1]);
                } else {
                    configs[i] = new ValueConfig(c[0]);
                }
            }

            return configs;
        } else {
            return null;
        }
    }

    /**
     * @param switches
     * @return
     */
    public static String prettySwitchString(Collection<Switch> switches) {
        StringBuilder prettyString = new StringBuilder();

        if (switches != null) {
            int maxLength = 0;

            for (Switch appSwitch : switches) {
                int length = appSwitch.getName().length();
                if (length > maxLength) {
                    maxLength = length;
                }
            }

            maxLength += 5;

            for (Switch appSwitch : switches) {
                prettyString.append(StringUtil.flushLeft(appSwitch.getName(), maxLength));
                prettyString.append(appSwitch.getValue());
                prettyString.append("\r\n");
            }
        }

        return prettyString.toString();
    }

    /**
     * @param appName
     * @param key
     * @return
     */
    public static String adapterOldVersionKey(String appName, String key) {
        if (StringUtil.nullOrBlank(key)) {
            return key;
        }

        if (key.contains(".")) {
            return key;
        }

        List<String> nameSpaces = SwitchContainer.getAppNameSpaces(appName);

        for (String namespace : nameSpaces) {
            String newVersionKey = SwitchUtil.calculateKey(key, namespace);

            if (SwitchContainer.contains(appName, newVersionKey)) {
                return newVersionKey;
            }

        }
        return key;
    }

    /**
     * @param primitiveType
     * @param type
     * @return
     */
    public static boolean primitiveTypeEquals(Class<?> primitiveType, Class<?> type) {
        if (primitiveType == int.class && type == Integer.class) {
            return true;
        } else if (primitiveType == boolean.class && type == Boolean.class) {
            return true;
        } else if (primitiveType == long.class && type == Long.class) {
            return true;
        } else if (primitiveType == double.class && type == Double.class) {
            return true;
        } else if (primitiveType == float.class && type == Float.class) {
            return true;
        } else if (primitiveType == short.class && type == Short.class) {
            return true;
        } else if (primitiveType == byte.class && type == Byte.class) {
            return true;
        } else if (primitiveType == char.class && type == Character.class) {
            return true;
        }
        return false;
    }

    /**
     * To json string
     *
     * @param obj
     * @return
     */
    public static String toJSONString(Object obj) {
        return JSON.toJSONString(obj, SerializerFeature.DisableCircularReferenceDetect,
            SerializerFeature.WriteDateUseDateFormat, SerializerFeature.SortField, SerializerFeature.MapSortField);
    }

    /**
     * @param object
     * @param type
     * @return
     */
    public static String handle(Object object, Class<?> type) {
        // avoid fastjson serialize byte array to base64 string.
        if (type == java.lang.reflect.Array.newInstance(byte.class, 0).getClass()) {
            return Arrays.toString((byte[]) object).replaceAll(" ", "");
        } else if (type == java.lang.reflect.Array.newInstance(Byte.class, 0).getClass()) {
            return Arrays.toString((Byte[]) object).replaceAll(" ", "");
        } else {
            return toJSONString(object);
        }
    }

    private static String getMethodName(String fieldName) {
        // fixme
        byte[] items = fieldName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }

    public static boolean jsonEquals(String expectedStr, String actualStr) {
        if (expectedStr == actualStr) { return true; }

        if (expectedStr == null) {
            return false;
        } else if (actualStr == null) {
            return false;
        }

        try {
            Object expected = JSONParser.parseJSON(expectedStr);
            Object actual = JSONParser.parseJSON(actualStr);

            if ((expected instanceof JSONObject) && (actual instanceof JSONObject)) {
                return comparator.compareJSON((JSONObject) expected, (JSONObject) actual);
            } else if ((expected instanceof JSONArray) && (actual instanceof JSONArray)) {
                return comparator.compareJSONArray((JSONArray) expected, (JSONArray) actual);
            } else if (expected instanceof JSONString && actual instanceof JSONString) {
                return comparator.compareJsonIfStr((JSONString) expected, (JSONString) actual);
            } else {
                return false;
            }
        } catch (JSONException e) {
            return false;
        }
    }

}
