package com.claireliu.reptile.task;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Component;

import com.claireliu.reptile.helper.HtmlParseHelper;
import com.claireliu.reptile.model.WebsiteModel;

/**
 *
 * @author claireliu
 * @date 2018/3/9
 */
@Component
public class GetTitleTask {

	@PostConstruct
	public void task() throws Exception {
		// 初始化一个httpclient
		HttpClient client = new DefaultHttpClient();

		String url = "https://news.zhibo8.cc/zuqiu/";

		List<WebsiteModel> urlsData = HtmlParseHelper.URLParser(client, url);

		for (WebsiteModel urlData : urlsData) {
			System.out.println(urlData.getTitle() + "==>" + urlData.getUrl());
		}
	}
}
