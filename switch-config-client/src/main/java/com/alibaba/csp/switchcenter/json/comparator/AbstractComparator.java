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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * @author xuyue
 */
public abstract class AbstractComparator implements JSONComparator {

    protected boolean compareJSONArrayOfSimpleValues(JSONArray expected, JSONArray actual) throws JSONException {
        Map<Object, Integer> expectedCount = JSONCompareUtil.getCardinalityMap(JSONCompareUtil.jsonArrayToList(expected));
        Map<Object, Integer> actualCount = JSONCompareUtil.getCardinalityMap(JSONCompareUtil.jsonArrayToList(actual));

        Map<Object, Integer> commonTypeExpectedCount = new HashMap<Object, Integer>(expectedCount.size());
        Map<Object, Integer> commonTypeActualCount = new HashMap<Object, Integer>(actualCount.size());

        Map<Double, Integer> numberTypeExpectedCount = new HashMap<Double, Integer>(expectedCount.size());
        Map<Double, Integer> numberTypeActualCount = new HashMap<Double, Integer>(actualCount.size());
        for (Object o : expectedCount.keySet()) {
            // escape 100.0 == 100
            if (o instanceof Number) {
                Number numer = (Number) o;
                numberTypeExpectedCount.put(numer.doubleValue(), expectedCount.get(o));
            } else {
                commonTypeExpectedCount.put(o, expectedCount.get(o));
            }
        }
        for (Object o : actualCount.keySet()) {
            // escape 100.0 == 100
            if (o instanceof Number) {
                Number numer = (Number) o;
                numberTypeActualCount.put(numer.doubleValue(), actualCount.get(o));
            } else {
                commonTypeActualCount.put(o, actualCount.get(o));
            }
        }

        return JSONCompareUtil.mapKVEquals(commonTypeExpectedCount, commonTypeActualCount)
            && JSONCompareUtil.mapKVEquals(numberTypeExpectedCount, numberTypeActualCount);
    }

    protected boolean compareJSONArrayOfJsonObjects(JSONArray expected, JSONArray actual) throws JSONException {
        String uniqueKey = JSONCompareUtil.findUniqueKey(expected);
        if (uniqueKey == null || !JSONCompareUtil.isUsableAsUniqueKey(uniqueKey, actual)) {
            // An expensive last resort
            return recursivelyCompareJSONArray(expected, actual);
        }
        Map<Object, JSONObject> expectedValueMap = JSONCompareUtil.arrayOfJsonObjectToMap(expected, uniqueKey);
        Map<Object, JSONObject> actualValueMap = JSONCompareUtil.arrayOfJsonObjectToMap(actual, uniqueKey);

        for (Object id : expectedValueMap.keySet()) {
            if (!actualValueMap.containsKey(id)) {
                return false;
            }

            JSONObject expectedValue = expectedValueMap.get(id);
            JSONObject actualValue = actualValueMap.get(id);
            if (!compareValues(expectedValue, actualValue)) {
                return false;
            }
        }
        for (Object id : actualValueMap.keySet()) {
            if (!expectedValueMap.containsKey(id)) {
                return false;
            }
        }
        return true;
    }

    // This is expensive (O(n^2) -- yuck), but may be the only resort for some cases
    // with loose array ordering, and no
    // easy way to uniquely identify each element.
    protected boolean recursivelyCompareJSONArray(JSONArray expected, JSONArray actual) throws JSONException {
        Set<Integer> matched = new HashSet<Integer>();
        for (int i = 0; i < expected.size(); ++i) {
            Object expectedElement = expected.get(i);
            boolean matchFound = false;
            for (int j = 0; j < actual.size(); ++j) {
                Object actualElement = actual.get(j);
                if (matched.contains(j) || !actualElement.getClass().equals(expectedElement.getClass())) {
                    continue;
                }
                if (expectedElement instanceof JSONObject) {
                    if (compareJSON((JSONObject) expectedElement, (JSONObject) actualElement)) {
                        matched.add(j);
                        matchFound = true;
                        break;
                    }
                } else if (expectedElement instanceof JSONArray) {
                    if (compareJSONArray((JSONArray) expectedElement, (JSONArray) actualElement)) {
                        matched.add(j);
                        matchFound = true;
                        break;
                    }
                } else if (expectedElement.equals(actualElement)) {
                    matched.add(j);
                    matchFound = true;
                    break;
                }
            }
            if (!matchFound) {
                return false;
            }
        }
        return true;
    }

}
