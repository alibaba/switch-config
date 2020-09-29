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
import com.alibaba.fastjson.JSON;

/**
 * @author chudong
 */
public class MonitorCommandHandler implements CommandHandler<String> {

    @Override
    public CommandResponse<String> handle(CommandRequest request) {
        return CommandResponse.ofSuccess(monitor(request));
    }

    /**
     * Get application's swicthes real-time MD5 string.
     *
     * @param request
     * @throws IOException
     */
    private static String monitor(CommandRequest request) {
        String[] params = request.getRequestParams();

        Result<String> result = new Result<String>(SwitchManager.monitor(params[0]));
        return JSON.toJSONString(result);
    }

}
