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
package com.alibaba.csp.switchcenter.json.comparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by xuyue on 16/7/19.
 */
public final class JSONCompareUtil {
    private static Integer INTEGER_ONE = new Integer(1);

    private JSONCompareUtil() {
    }

    public static <K, V> boolean mapKVEquals(Map<K, V> map1, Map<K, V> map2) {
        if ((map1 == null && map2 != null) || (map1 != null && map2 == null)) {
            return false;
        }

        for (K k : map1.keySet()) {
            if (!map2.containsKey(k)) {
                return false;
            } else if (!map2.get(k).equals(map1.get(k))) {
                return false;
            }
        }

        for (K k : map2.keySet()) {
            if (!map1.containsKey(k)) {
                return false;
            } else if (!map1.get(k).equals(map2.get(k))) {
                return false;
            }
        }
        return true;
    }

    public static <K, V> V getNumKEqualsV(K key, Map<K, V> map) {
        if (key == null || map == null) {
            return null;
        }
        if (key instanceof Number) {
            Number kNumber = (Number) key;
            for (Map.Entry<K, V> entry : map.entrySet()) {
                Number k = (Number) entry.getKey();
                if (k != null && kNumber.doubleValue() == k.doubleValue()) {
                    return entry.getValue();
                }
            }
            return null;
        } else {
            return map.get(key);
        }
    }

    public static boolean allSimpleValues(JSONArray array) throws JSONException {
        for (int i = 0; i < array.size(); ++i) {
            if (!isSimpleValue(array.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSimpleValue(Object o) {
        return !(o instanceof JSONObject) && !(o instanceof JSONArray);
    }

    public static boolean allJSONObjects(JSONArray array) throws JSONException {
        for (int i = 0; i < array.size(); ++i) {
            if (!(array.get(i) instanceof JSONObject)) {
                return false;
            }
        }
        return true;
    }

    public static List<Object> jsonArrayToList(JSONArray expected) throws JSONException {
        List<Object> jsonObjects = new ArrayList<Object>(expected.size());
        for (int i = 0; i < expected.size(); ++i) {
            jsonObjects.add(expected.get(i));
        }
        return jsonObjects;
    }

    public static <T> Map<T, Integer> getCardinalityMap(final Collection<T> coll) {
        Map<T, Integer> count = new HashMap<T, Integer>();
        for (T item : coll) {
            Integer c = (Integer) (count.get(item));
            if (c == null) {
                count.put(item, INTEGER_ONE);
            } else {
                count.put(item, new Integer(c.intValue() + 1));
            }
        }
        return count;
    }

    public static String findUniqueKey(JSONArray expected) throws JSONException {
        // Find a unique key for the object (id, name, whatever)
        JSONObject o = (JSONObject) expected.get(0); // There's at least one at this point
        for (String candidate : getKeys(o)) {
            if (isUsableAsUniqueKey(candidate, expected)) { return candidate; }
        }
        // No usable unique key :-(
        return null;
    }

    /**
     * {@code candidate} is usable as a unique key if every element in the
     * {@code array} is a JSONObject having that key, and no two values are the
     * same.
     */
    public static boolean isUsableAsUniqueKey(String candidate, JSONArray array) throws JSONException {
        Set<Object> seenValues = new HashSet<Object>();
        for (int i = 0; i < array.size(); i++) {
            Object item = array.get(i);
            if (item instanceof JSONObject) {
                JSONObject o = (JSONObject) item;
                if (o.containsKey(candidate)) {
                    Object value = o.get(candidate);
                    if (isSimpleValue(value) && !seenValues.contains(value)) {
                        seenValues.add(value);
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public static Map<Object, JSONObject> arrayOfJsonObjectToMap(JSONArray array, String uniqueKey)
        throws JSONException {
        Map<Object, JSONObject> valueMap = new HashMap<Object, JSONObject>();
        for (int i = 0; i < array.size(); ++i) {
            JSONObject jsonObject = (JSONObject) array.get(i);
            Object id = jsonObject.get(uniqueKey);

            // escape double 100.0 == 100
            if (id instanceof Number) {
                valueMap.put(((Number) id).doubleValue(), jsonObject);
            } else {
                valueMap.put(id, jsonObject);
            }
        }
        return valueMap;
    }

    public static Set<String> getKeys(JSONObject jsonObject) {
        Set<String> keys = jsonObject.keySet();
        return keys;
    }

}
