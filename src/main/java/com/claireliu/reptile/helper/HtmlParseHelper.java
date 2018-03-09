package com.claireliu.reptile.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.claireliu.reptile.model.WebsiteModel;
import com.google.common.collect.Lists;

/**
 * html解析工具；
 * 
 * @author claireliu
 * @date 2018/3/9
 */
public class HtmlParseHelper {

	public static List<WebsiteModel> getDataByHtml(String html) {

		List<WebsiteModel> data = new ArrayList<WebsiteModel>();

		Document doc = Jsoup.parse(html);

		Elements elements = doc.getElementsByTag("a");

		for (Element ele : elements) {

			WebsiteModel websiteModel = new WebsiteModel();
			websiteModel.setTitle(ele.text());
			websiteModel.setUrl(ele.attr("href"));

			data.add(websiteModel);
		}
		return data;
	}

	public static List<WebsiteModel> URLParser(HttpClient client, String url) throws Exception {

		HttpResponse response = HttpHelper.getRawHtml(client, url);
		int StatusCode = response.getStatusLine().getStatusCode();
		if (StatusCode == 200) {
			String entity = EntityUtils.toString(response.getEntity(), "utf-8");

			EntityUtils.consume(response.getEntity());
			return  getDataByHtml(entity);
		} else {
			EntityUtils.consume(response.getEntity());
		}
		return Lists.newArrayList();
	}

}
