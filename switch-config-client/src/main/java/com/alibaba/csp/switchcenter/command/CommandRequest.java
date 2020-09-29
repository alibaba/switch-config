/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.csp.switchcenter.command;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.csp.switchcenter.core.SwitchContainer;
import com.alibaba.csp.switchcenter.util.StringUtil;
import com.alibaba.csp.switchcenter.util.SwitchUtil;

/**
 * Command request representation of command center.
 *
 * @author Eric Zhao
 */
public class CommandRequest {
    private String target;
    private final Map<String, String> parameters = new HashMap<String, String>();

    public Map<String, String> getParameters() {
        return parameters;
    }

    public String getParam(String key) {
        return parameters.get(key);
    }

    public String getParam(String key, String defaultValue) {
        String value = parameters.get(key);
        return StringUtil.isBlank(value) ? defaultValue : value;
    }

    public CommandRequest addParam(String key, String value) {
        if (StringUtil.isBlank(key)) {
            throw new IllegalArgumentException("Parameter key cannot be empty");
        }
        parameters.put(key, value);
        return this;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * Process request parameters.
     * @return [0]:appName,[1]:key,[2]:value,[3]:itemKey
     */
    public String[] getRequestParams() {
        String[] params = new String[4];

        String appName = this.getParam("appName");

        if (StringUtil.nullOrBlank(appName)) {
            params[0] = SwitchContainer.DEFAULT_APP_NAME;
        } else {
            params[0] = appName;
        }

        String nameSpace = this.getParam("namespace");
        String oldVersionKey = SwitchUtil.calculateKey(this.getParam("name"), nameSpace);

        params[1] = SwitchUtil.adapterOldVersionKey(params[0], oldVersionKey);
        params[2] = this.getParam("value");
        params[3] = this.getParam("key");

        return trimParams(params);
    }

    private static String[] trimParams(String[] params) {
        if (params == null) {
            return params;
        }

        for (int index = 0; index < params.length; index++) {
            params[index] = params[index] != null ? params[index].trim() : params[index];
        }
        return params;
    }
}
