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
package com.alibaba.csp.switchcenter.bean;

import java.io.UnsupportedEncodingException;

import com.alibaba.csp.switchcenter.message.ErrorType;

/**
 * @author jielong.hjl
 */
public class Result<T> {
    private boolean success;
    private String errorMessage;

    private String errorCode;
    private T data;

    private Result() {
    }

    public Result(T data) {
        this.data = data;
        this.success = true;
    }

    public static <K> Result<K> newSuccess(K data) {
        Result<K> result = new Result<K>();
        result.setSuccess(true);
        result.setData(data);

        return result;
    }

    @SuppressWarnings("rawtypes")
	public static Result newFail(ErrorType errorType) {
        Result result = new Result();
        result.setSuccess(false);
        try {
            result.setErrorMessage(new String(errorType.getCnDesc().getBytes("gbk"),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        result.setErrorCode(errorType != null ? errorType.getErrorCode() : null);

        return result;
    }


    public Result(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        if (!success) {
            return "[" + errorCode + "]" + errorMessage;
        }
        return "Result [success=" + success + ", data=" + data + "]";
    }

}
