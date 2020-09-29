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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.Charset;

import com.alibaba.csp.switchcenter.log.SwitchRecordLog;
import com.alibaba.csp.switchcenter.util.StringUtil;

/***
 * 
 * @Description: comandcenter command task
 * @author youji.zj youji.zj@taobao.com
 * @since 2014-5-21
 *
 */
public class CommandTask implements Runnable {

	private final String DEFAULT_CHARSET = "utf-8";

	Socket socket;

	public CommandTask(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		if (socket == null) {
			return;
		}

		BufferedReader in = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream(), DEFAULT_CHARSET));
			OutputStream outputStream = socket.getOutputStream();

			out = new PrintWriter(
					new OutputStreamWriter(outputStream, Charset.forName(DEFAULT_CHARSET)));

			// 通过输入流接收客户端信息
			String line = in.readLine();
			CommandRequest request = parseRequest(line);

			out.print("HTTP/1.1 200 OK\r\n\r\n");
			out.flush();
			if (StringUtil.isBlank(request.getTarget())) {
				out.println("query paramater is blank");
				out.flush();
				return;
			}

			CommandHandler command = CommandCenter.findHandler(request.getTarget());
			if (null != command) {
				CommandResponse response = command.handle(request);
				out.println(response.getResult());
			} else {
				out.println(request.getTarget() + " command is not correct");
			}
			out.flush();

			SwitchRecordLog.info("[CommandCenter] deal a socket task:" + line + "," + socket.getInetAddress());
		} catch (Throwable e) {
			SwitchRecordLog.info("CommandCenter error", e);

			try {
				out.println("CommandCenter failed, message is " + e.getMessage());
				out.flush();
			} catch (Exception e1) {
				SwitchRecordLog.info("CommandCenter close serverSocket failed", e);
			}
		} finally {
			try {
				out.close();
				in.close();
				socket.close();
			} catch (Exception e) {
				SwitchRecordLog.info("CommandCenter close resource failed", e);
			}
		}

	}

	/**
	 * GET /forceUpdateEvent?bizIndentify=123456 HTTP/1.1
	 * 
	 * @param line
	 * @return
	 */
	private CommandRequest parseRequest(String line) {
		CommandRequest request = new CommandRequest();
		if (StringUtil.isBlank(line)) {
			return request;
		}
		int start = line.indexOf('/');
		int ask = line.indexOf('?') == -1 ? line.lastIndexOf(' ') : line.indexOf('?');
		int space = line.lastIndexOf(' ');
		String target = line.substring(start != -1 ? start + 1 : 0, ask != -1 ? ask : line.length());
		request.setTarget(target);
		if (ask == -1 || ask == space) {
			return request;
		}
		String parameterStr = line.substring(ask != -1 ? ask + 1 : 0, space != -1 ? space : line.length());
		for (String parameter : parameterStr.split("&")) {
			if (StringUtil.isBlank(parameter)) {
				continue;
			}

			String[] key_value = parameter.split("=");
			if (key_value.length != 2) {
				continue;
			}

			String value = StringUtil.trim(key_value[1]);
			try {
				value = URLDecoder.decode(value, DEFAULT_CHARSET);
			} catch (UnsupportedEncodingException e) {
			}

			request.addParam(StringUtil.trim(key_value[0]), value);
		}
		return request;
	}

	private PrintWriter getPrintWriter(OutputStream out) throws Exception{
		String charSetName = DEFAULT_CHARSET;
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(out, DEFAULT_CHARSET)));

		return pw;
	}

}
