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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.alibaba.csp.switchcenter.bean.Result;
import com.alibaba.csp.switchcenter.bean.Switch;
import com.alibaba.csp.switchcenter.bean.SwitchWrapper;
import com.alibaba.csp.switchcenter.command.CommandHandler;
import com.alibaba.csp.switchcenter.command.CommandRequest;
import com.alibaba.csp.switchcenter.command.CommandResponse;
import com.alibaba.csp.switchcenter.core.SwitchManager;
import com.alibaba.csp.switchcenter.util.SwitchUtil;
import com.alibaba.fastjson.JSON;

/**
 * @author chudong
 */
public class ListCommandHandler implements CommandHandler<String> {

    @Override
    public CommandResponse<String> handle(CommandRequest request) {
        return CommandResponse.ofSuccess(list(request));
    }

    /**
     * Get application's switches list.
     *
     * @param request
     * @throws IOException
     * @see SwitchWrapper
     */
    private static String list(CommandRequest request) {
        String[] params = request.getRequestParams();

        Collection<Switch> switches = SwitchManager.getSwitchList(params[0]);
        Collection<SwitchWrapper> wrapperSwitches = new ArrayList<SwitchWrapper>();

        Iterator<Switch> iterator = switches.iterator();
        while (iterator != null & iterator.hasNext()) {
            Switch bean = iterator.next();
            wrapperSwitches.add(new SwitchWrapper(bean, SwitchUtil.getTypeString(bean.getType())));
        }

        Result<Collection<SwitchWrapper>> result = new Result<Collection<SwitchWrapper>>(wrapperSwitches);
        return JSON.toJSONString(result);
    }
}
