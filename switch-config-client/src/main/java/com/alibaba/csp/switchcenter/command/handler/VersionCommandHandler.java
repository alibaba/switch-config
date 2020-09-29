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

import com.alibaba.csp.switchcenter.command.CommandHandler;
import com.alibaba.csp.switchcenter.command.CommandRequest;
import com.alibaba.csp.switchcenter.command.CommandResponse;
import com.alibaba.csp.switchcenter.core.SwitchManager;

/**
 * @author chudong
 */
public class VersionCommandHandler implements CommandHandler<String> {

    @Override
    public CommandResponse<String> handle(CommandRequest request) {
        return CommandResponse.ofSuccess(version(request));
    }

    /**
     * view version.
     *
     * @param request
     * @throws IOException
     */
    private static String version(CommandRequest request) {
        String version = "0.0.0.0";
        try {
            String[] versionInfo = SwitchManager.class.getProtectionDomain().getCodeSource().getLocation().toString()
                .split("/");
            version = versionInfo[versionInfo.length - 1];
        } catch (Throwable t) {
        }
        return version;
    }

}
