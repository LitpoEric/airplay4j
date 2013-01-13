package com.yutel.sample.handler;

import java.util.Date;
import java.util.Map;

import com.dd.plist.NSDictionary;
import com.dd.plist.PropertyListParser;
import com.yutel.silver.AikaProxy;
import com.yutel.silver.exception.AirplayException;
import com.yutel.silver.http.HttpWrap;
import com.yutel.silver.http.handler.BaseHttpHandler;

public class PlayHandler extends BaseHttpHandler {

	public PlayHandler(AikaProxy proxy) {
		super(proxy);
	}

	public void handle(HttpWrap hw) throws AirplayException {
		Map<String, String> headers = hw.getRequestHeads();
		for (Map.Entry<String, String> item : headers.entrySet()) {
			System.out.println("name=" + item.getKey() + ",value="
					+ item.getValue());
		}
		try {
			NSDictionary rootDict = (NSDictionary) PropertyListParser.parse(hw
					.getRequestBodys());
			String url = rootDict.objectForKey("Content-Location").toString();
			String rate = rootDict.objectForKey("rate").toString();
			String pos = rootDict.objectForKey("Start-Position").toString();
			System.out.println("url=" + url + ",rate=" + rate);
			if (mProxy != null) {
				mProxy.video(pos, rate, url);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		response(hw);
	}

	private void response(HttpWrap hw) throws AirplayException {
		System.out.println("response");
		hw.getResponseHeads().put("date", new Date().toString());
		hw.sendResponseHeaders(200, 0);
	}
}
