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
package com.alibaba.csp.switchcenter.command.handler;

import java.io.IOException;

import com.alibaba.csp.switchcenter.bean.Result;
import com.alibaba.csp.switchcenter.command.CommandHandler;
import com.alibaba.csp.switchcenter.command.CommandRequest;
import com.alibaba.csp.switchcenter.command.CommandResponse;
import com.alibaba.csp.switchcenter.core.SwitchManager;
import com.alibaba.csp.switchcenter.message.ErrorType;
import com.alibaba.csp.switchcenter.message.Message;
import com.alibaba.fastjson.JSON;

import static com.alibaba.csp.switchcenter.message.ErrorType.SWITCH_DO_NOT_EXITS;

/**
 * @author chudong
 */
public class SetCommandHandler implements CommandHandler<String> {

    @Override
    public CommandResponse<String> handle(CommandRequest commandRequest) {
        return CommandResponse.ofSuccess(set(commandRequest));
    }

    /**
     * Change switch's value.
     *
     * @param request
     * @throws IOException
     */
    private static String set(CommandRequest request) {
        String[] params = request.getRequestParams();

        if (SwitchManager.contains(params[0], params[1])) {
            String oldValue = SwitchManager.getStrValue(params[0], params[1]);

            @SuppressWarnings("rawtypes")
            Result setResult = SwitchManager.setValue(params[0], params[1], params[2]);
            if (setResult.isSuccess()) {
                String value = SwitchManager.getStrValue(params[0], params[1]);
                params[2] = value;
                params[3] = oldValue;

                Result<String> result = Result.newSuccess(
                    Message.buildMessage(params, ErrorType.SET_SWITCH_VALUE_SUCCESS));
                return JSON.toJSONString(result);
            } else {
                return JSON.toJSONString(setResult);
            }
        }

        return JSON.toJSONString(Result.newFail(SWITCH_DO_NOT_EXITS));
    }
}
