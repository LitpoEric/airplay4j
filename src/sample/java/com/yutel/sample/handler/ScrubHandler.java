package com.yutel.sample.handler;

import java.util.Date;
import java.util.Map;

import com.yutel.silver.exception.AirplayException;
import com.yutel.silver.http.HttpWrap;
import com.yutel.silver.http.handler.HttpHandler;

public class ScrubHandler implements HttpHandler {

	public void handle(HttpWrap hw) throws AirplayException {
		Map<String, String> headers = hw.getRequestHeads();
		for (Map.Entry<String, String> item : headers.entrySet()) {
			System.out.println("name=" + item.getKey() + ",value=" + item.getValue());
		}
		response(hw);
	}

	private void response(HttpWrap hw) throws AirplayException {
		System.out.println("response");
		hw.getResponseHeads().put("date", new Date().toString());
		hw.sendResponseHeaders(200, 0);
	}
}
