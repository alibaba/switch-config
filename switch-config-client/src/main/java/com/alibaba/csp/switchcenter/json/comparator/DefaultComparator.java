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

import java.util.Set;

import com.alibaba.csp.switchcenter.json.JSONString;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by xuyue on 16/7/18.
 */
public class DefaultComparator extends AbstractComparator {

    @Override
    public boolean compareJSONArray(JSONArray expected, JSONArray actual) throws JSONException {
        if (expected.size() != actual.size()) {
            return false;
        } else if (expected.size() == 0) {
            return true;
        }

        if (JSONCompareUtil.allSimpleValues(expected)) {
            return compareJSONArrayOfSimpleValues(expected, actual);
        } else if (JSONCompareUtil.allJSONObjects(expected)) {
            return compareJSONArrayOfJsonObjects(expected, actual);
        } else {
            // An expensive last resort
            return recursivelyCompareJSONArray(expected, actual);
        }
    }

    @Override
    public boolean compareValues(Object expectedValue, Object actualValue) throws JSONException {
        if (expectedValue instanceof Number && actualValue instanceof Number) {
            if (((Number) expectedValue).doubleValue() != ((Number) actualValue).doubleValue()) {
                return false;
            }
        } else if (expectedValue.getClass().isAssignableFrom(actualValue.getClass())) {
            if (expectedValue instanceof JSONObject) {
                return compareJSON((JSONObject) expectedValue, (JSONObject) actualValue);
            } else if (expectedValue instanceof JSONArray) {
                return compareJSONArray((JSONArray) expectedValue, (JSONArray) actualValue);
            } else if (!expectedValue.equals(actualValue)) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * @param expected
     * @param actual
     * @return
     */
    @Override
    public boolean compareJsonIfStr(final JSONString expected, final JSONString actual) {
        final String expectedJson = expected.toJSONString();
        final String actualJson = actual.toJSONString();
        if (!expectedJson.equals(actualJson)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean compareJSON(JSONObject expected, JSONObject actual) throws JSONException {
        Set<?> expectedKeys = JSONCompareUtil.getKeys(expected);
        for (Object key : expectedKeys) {
            Object expectedValue = expected.get(key);
            if (actual.containsKey(key)) {
                Object actualValue = actual.get(key);
                if (!compareValues(expectedValue, actualValue)) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return true;
    }
}
