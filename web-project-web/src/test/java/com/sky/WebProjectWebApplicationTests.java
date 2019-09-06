package com.sky;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.sky.core.utils.SaltUtils;
import com.sky.core.utils.SpiderUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebProjectWebApplicationTests {

	@Test
	public void contextLoads() {
//		System.out.println("====================" + SaltUtils.getMD5Password("liuxiaolong"));
//		System.out.println("====================" + SaltUtils.getMD5Password("liuxiaojing"));

		for(int i = 0 ; i < 20 ; i ++ ){
			System.out.println("==========="+ i +"==========" + IdWorker.getIdStr());
		}
	}

	@Test
	public void test(){
		String url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=0&CodeType=1&PageIndex=1&PageSize=500&jsObj=tNCgxDeJ&SecNodeType=0&TIME=&rt=52248931";
		String jsonString = SpiderUtils.HttpClientBuilderGet(url);
		jsonString =  jsonString.substring(jsonString.indexOf("data")-2,jsonString.length()-1);
		JSONArray jsonArray = JSON.parseObject(jsonString).getJSONArray("data");
		for(int x = 0 ; x < jsonArray.size() ; x++){
           JSONObject jsonObject = jsonArray.getJSONObject(x);
//			System.out.println(jsonObject.toString());
			String titleName = jsonObject.getString("NOTICETITLE");
			titleName = titleName.substring(titleName.indexOf(":")+1,titleName.length());
			titleName = titleName.replace("的公告","").replace("公告","").replace("关于","");

			String publishTime = jsonObject.getString("NOTICEDATE");
			publishTime = publishTime.substring(0,10);

			JSONArray containArray = jsonObject.getJSONArray("ANN_RELCOLUMNS");
			String nticeType = "";
			String nticetain = "";
			for(int y = 0 ; y < containArray.size() ; y++){
                if(y == 0){
					nticeType = containArray.getJSONObject(y).getString("COLUMNNAME");
				}
				if(y == 1){
					nticetain = containArray.getJSONObject(y).getString("COLUMNNAME");
				}
			}

			JSONArray stockArray = jsonObject.getJSONArray("CDSY_SECUCODES");
			String stockCode = stockArray.getJSONObject(0).getString("SECURITYCODE");
			String stockName = stockArray.getJSONObject(0).getString("SECURITYFULLNAME");

			String containUrl = jsonObject.getString("Url");
			Document doc = SpiderUtils.HtmlJsoupGet(containUrl);
			Elements elements = doc.body().getElementsByClass("detail-body");
			Element element = elements.get(0);
			String containText = element.text();
//			containText = containText.substring(containText.indexOf("误导性陈述或重大遗漏") + 12 ,containText.indexOf("特此公告"));

			System.out.println("=========titleName==========" + titleName);
			System.out.println("=========publishTime==========" + publishTime);
			System.out.println("=========containUrl==========" + containUrl);
			System.out.println("=========nticeType==========" + nticeType);
			System.out.println("=========nticetain==========" + nticetain);
			System.out.println("=========stockCode==========" + stockCode);
			System.out.println("=========stockName==========" + stockName);
			System.out.println("=========containText==========" + containText);
		}
	}

	@Test
	public void test3(){
        String url = "http://data.eastmoney.com/notices/detail/300453/AN201909041348470867,JWU0JWI4JTg5JWU5JTkxJWFiJWU1JThjJWJiJWU3JTk2JTk3.html";
		Document doc = SpiderUtils.HtmlJsoupGet(url);
		Elements elements = doc.body().getElementsByClass("detail-body");
		Element element = elements.get(0);
		String containText = element.text();
		containText = containText.substring(containText.indexOf("误导性陈述或重大遗漏") + 12 ,containText.indexOf("特此公告"));
		System.out.println("============" + containText);
		String[] str = containText.split(" ");
		for(int t = 0 ; t < str.length ; t++){
			System.out.println("============" + str[t]);
		}

	}

}
