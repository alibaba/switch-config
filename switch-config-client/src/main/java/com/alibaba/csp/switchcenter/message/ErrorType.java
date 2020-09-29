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

import com.alibaba.csp.switchcenter.util.StringUtil;

/**
 * @author chudong
 */
public enum ErrorType {

    SWITCH_DO_NOT_EXITS("SWITCH-001", "The switch does not exist", "开关不存在"),

    JSON_DESERIALIZATION_ERROR("SWITCH-002", "switch json deserialization error", "开关内容反序列化错误"),

    PRIMITIVE_TYPE_NOT_FOUND("SWITCH-003", "switch primitive type not found", "未找到对应开关类型"),

    SWITCH_VALUE_TYPE_NOT_MATCH("SWITCH-004", "switch value type not match", "开关值，类型不匹配"),

    SET_SWITCH_VALUE_SUCCESS("SWITCH-SET-SUCCESS", "set switch value success", "修改开关成功"),

    ATOMIC_TYPE_SET_ERROR("SWITCH-005", "set switch atomic type fail", "修改atomic类型开关失败"),

    EXECUTE_SWITCH_CALLBACK_FAIL("SWITCH-006", "execute switch callback fail", "执行开关回调失败"),

    EXECUTE_CALLBACK_FAIL("SWITCH-007", "execute switch callback fail", "执行开关回调失败"),

    SET_VALUE_UNKNOWN_ERROR("SWITCH-008", "set value unknown error", "修改开关未知错误"),

    SET_SWITCH_BY_SET_METHOD_FAIL("SWITCH-009", "set switch value by set method fail", "通过set方法反射修改开关值失败"),

    SET_SWITCH_BY_REFLECT_FAIL("SWITCH-010", "set switch value by reflect fail", "反射修改开关值失败"),

    SWITCH_CLASS_NOT_FOUND("SWITCH-011", "switch class not found", "switch类未找到"),

    SWITCH_INIT_UNKNOWN_ERROR("SWITCH-012", "switch init unknown error", "switch初始化未知错误"),

    NOT_SUPPORT_SWITCH_FILED_MODIFIERS("SWITCH-013", "not support switch field modifiers", "不支持的字段修饰符"),

    REGISTER_SWITCH_CLASS_UNKNOWN_ERROR("SWITCH-014", "register switch class unknown error", "注册switch类未知错误"),

    REGISTER_SWITCH_FIELD_UNKNOWN_ERROR("SWITCH-015", "register switch field unknown error", "注册switch字段未知错误"),

    LISTEN_ACM_SET_VAIL("SWITCH-016", "listen acm set switch fail", "监听持久化配置失败"),

    PERSIST_FAIL("SWITCH-017", "persist fail", "开关持久化失败"),

    PUSH_MACHINE_FAIL("SWITCH-018", "push machine fail", "推送机器失败"),

    GET_SWITCH_FAIL("SWITCH-019", "get machine switch fail", "查询机器开关失败");

    private String errorCode;

    private String desc;

    private String cnDesc;

    ErrorType(String errorCode, String desc, String cnDesc) {
        this.errorCode = errorCode;
        this.desc = desc;
        this.cnDesc = cnDesc;
    }

    public static ErrorType getTypeByCode(String errorCode) {
        if (StringUtil.nullOrBlank(errorCode)) {
            return null;
        }
        for (ErrorType errorType : ErrorType.values()) {
            if (errorCode.equals(errorType)) {
                return errorType;
            }
        }
        return null;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getDesc() {
        return desc;
    }

    public String getCnDesc() {
        return cnDesc;
    }
}