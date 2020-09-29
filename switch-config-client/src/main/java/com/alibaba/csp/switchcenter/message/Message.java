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
package com.alibaba.csp.switchcenter.message;

/**
 * @author jielong.hjl
 */
public class Message {

    /**
     * @param appName
     * @param key
     * @param value
     * @param oldValue
     * @param type
     * @return
     */
    public static String buildMessage(String appName, String key, String value, String oldValue, ErrorType type) {
        String[] params = new String[4];

        params[0] = appName;
        params[1] = key;
        params[2] = value;
        params[3] = oldValue;

        return buildMessage(params, type);
    }

    /**
     * @param params
     * @param type
     * @return
     */
    public static String buildMessage(String[] params, ErrorType type) {
        StringBuilder message = new StringBuilder();

        switch (type) {
            case SWITCH_DO_NOT_EXITS:
                message.append("App: ").append(params[0]).
                    append(" , switch: ").
                    append(params[1]).
                    append(" not exits.");
                break;
            case SET_SWITCH_VALUE_SUCCESS:
                message.append("Change switch value success!").
                    append(" App: ").
                    append(params[0]).
                    append(", switch: ").
                    append(params[1]).
                    append(", value: ").
                    append(params[2]).
                    append(", oldvalue: ").
                    append(params[3]);
                break;
            default:
                message.append("Undefined message type.");
                break;

        }
        return message.toString();
    }

}
