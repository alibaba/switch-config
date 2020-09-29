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
package com.alibaba.csp.switchcenter.command;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.csp.switchcenter.command.handler.GetCommandHandler;
import com.alibaba.csp.switchcenter.command.handler.ListCommandHandler;
import com.alibaba.csp.switchcenter.command.handler.MonitorCommandHandler;
import com.alibaba.csp.switchcenter.command.handler.SetCommandHandler;
import com.alibaba.csp.switchcenter.command.handler.VersionCommandHandler;
import com.alibaba.csp.switchcenter.log.SwitchRecordLog;
import com.alibaba.csp.switchcenter.util.StringUtil;

/**
 * @author jielong.hjl
 */
public class CommandCenter {
    private static boolean FIRST_INIT = true;

    private static Map<String, CommandHandler> commandHandlerMap = new HashMap<String, CommandHandler>();

    synchronized public static void init () {
        if (!FIRST_INIT){
            return;
        }
        CommandCenter.registerCommand("switch.list", new ListCommandHandler());
        CommandCenter.registerCommand("switch.get", new GetCommandHandler());
        CommandCenter.registerCommand("switch.set", new SetCommandHandler());
        CommandCenter.registerCommand("switch.view", new VersionCommandHandler());
        CommandCenter.registerCommand("switch.monitor", new MonitorCommandHandler());
        CommandCenter.registerCommand("switch.version", new VersionCommandHandler());
        new CommandListener().start();
    }

    /***
     * register a command to command center
     * @param name  command name
     * @param command  command instance
     */
    private static void registerCommand(String name, CommandHandler command) {
        if (StringUtil.isEmpty(name)) {
            return;
        }

        if (commandHandlerMap.containsKey(name)) {
            SwitchRecordLog.info("register command duplicated:" + name);
        }

        commandHandlerMap.put(name, command);
    }

    /***
     * get CommandHandler by commandName
     * @param commandName commandName
     * @return CommandHandler
     */
    public static CommandHandler findHandler(String commandName) {
        return commandHandlerMap.get(commandName);
    }


}
