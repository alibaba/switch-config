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

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.TimeUnit;

import com.alibaba.csp.switchcenter.log.SwitchRecordLog;
import com.alibaba.csp.switchcenter.util.StringUtil;

/**
 * @author lixin.lb
 */
public class CommandListener extends Thread {

    private static volatile int port = 7888;

    private static final String CSP_PORT = System.getProperty("csp.port");

    private static volatile boolean useCustomerPort = false;

    private static ExecutorService bizExecutor = new ThreadPoolExecutor(
        Runtime.getRuntime().availableProcessors(),
        Runtime.getRuntime().availableProcessors(),
        0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(100),
        new DiscardPolicy());;

    @Override
    public void run() {
        if (StringUtil.isNotBlank(CSP_PORT)) {
            try {
                port = Integer.parseInt(CSP_PORT.trim());
                useCustomerPort = true;
            } catch(Exception e) {
                SwitchRecordLog.info("[CommandCenter] port format error: " + CSP_PORT);
            }
        }
        long repeat = 0;
        while(true) {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(port);
            } catch(Exception e) {
                SwitchRecordLog.info("holding CommandCenter failed,repeat:." + repeat, e);
                adjustPort(repeat);
                threadSleepSecond("CommandListener", 12);
                repeat++;
            }
            if (serverSocket != null) {
                SwitchRecordLog.info("[CommandCenter] begin listening at port " + serverSocket.getLocalPort());
                new ServerThread(serverSocket).start();
                break;
            }
        }
    }


    private void adjustPort(long repeat) {
        if (useCustomerPort) {
            return;
        }
        int mod = (int) repeat / 10;
        port = port + mod;
    }

    private static class ServerThread extends Thread {
        private ServerSocket serverSocket;

        private ServerThread(ServerSocket s) {
            this.serverSocket = s;
        }

        @Override
        public void run() {
            while(true) {
                Socket socket = null;
                try {
                    socket = this.serverSocket.accept();
                    socket.setSoTimeout(3000);
                    CommandTask eventTask = new CommandTask(socket);
                    bizExecutor.submit(eventTask);
                } catch(Exception e) {
                    SwitchRecordLog.info("server error!", e);
                    threadSleepSecond("ServerThread", 1);
                }
            }
        }
    }

    private static void threadSleepSecond(String name, long time){
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            SwitchRecordLog.info(name + " Thread sleep error!", e);
        }
    }
}
