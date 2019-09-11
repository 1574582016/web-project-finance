package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.core.utils.SpiderUtils;
import com.sky.mapper.StockCompanyNoticeClassMapper;
import com.sky.mapper.StockCompanyNoticeMapper;
import com.sky.model.StockCompanyNotice;
import com.sky.model.StockCompanyNoticeClass;
import com.sky.service.StockCompanyNoticeClassService;
import com.sky.service.StockCompanyNoticeService;
import com.sky.vo.StockNoticeCompany_VO;
import com.sky.vo.StockNoticeDetail_VO;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2019/9/4.
 */
@Service
@Transactional
public class StockCompanyNoticeServiceImpl extends ServiceImpl<StockCompanyNoticeMapper,StockCompanyNotice> implements StockCompanyNoticeService {

    @Autowired
    private StockCompanyNoticeClassService noticeClassService ;

    @Override
    public boolean spiderStockCompanyNotice(Integer bigType ,Integer middleType ,Integer page) {
        boolean just = true ;
        try{
            String url = "";
            String bigClass = "";
            String middleClass = "";
            if(bigType == 1){
                bigClass = "重大事件";
                if(middleType == 1){
                    middleClass = "重大合同";
                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=5&CodeType=1&PageIndex="+ page +"&PageSize=100&jsObj=CDQEMdrj&SecNodeType=7&TIME=&rt=52248959";
                }
                if(middleType == 2){
                    middleClass = "投资相关";
                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=5&CodeType=1&PageIndex="+ page +"&PageSize=100&jsObj=ozcAWkTr&SecNodeType=8&TIME=&rt=52248960";
                }
                if(middleType == 3){
                    middleClass = "股权激励";
                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=5&CodeType=1&PageIndex="+ page +"&PageSize=100&jsObj=bURaPnRJ&SecNodeType=9&TIME=&rt=52248961";
                }
            }

            if(bigType == 2){
                bigClass = "财务报告";
                if(middleType == 1){
                    middleClass = "定期报告";
                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=1&CodeType=1&PageIndex="+ page +"&PageSize=100&jsObj=yKryDOMa&SecNodeType=1&TIME=&rt=52248963";
                }
                if(middleType == 2){
                    middleClass = "利润分配";
                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=1&CodeType=1&PageIndex="+ page +"&PageSize=100&jsObj=pjlsLIWT&SecNodeType=13&TIME=&rt=52248964";
                }
                if(middleType == 3){
                    middleClass = "业绩报告";
                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=1&CodeType=1&PageIndex="+ page +"&PageSize=100&jsObj=shoSSvdL&SecNodeType=5&TIME=&rt=52248964";
                }
                if(middleType == 4){
                    middleClass = "业绩快报";
                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=1&CodeType=1&PageIndex="+ page +"&PageSize=100&jsObj=zQwFlkzb&SecNodeType=6&TIME=&rt=52248966";
                }
            }

            if(bigType == 3){
                bigClass = "融资公告";
                if(middleType == 1){
                    middleClass = "增发";
                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=2&CodeType=1&PageIndex="+ page +"&PageSize=100&jsObj=ZyMUZLii&SecNodeType=3&TIME=&rt=52248967";
                }
                if(middleType == 2){
                    middleClass = "新股发行";
                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=2&CodeType=1&PageIndex="+ page +"&PageSize=100&jsObj=eWwiIBWO&SecNodeType=2&TIME=&rt=52248968";
                }
                if(middleType == 3){
                    middleClass = "配股";
                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=2&CodeType=1&PageIndex="+ page +"&PageSize=100&jsObj=YjRTOdkP&SecNodeType=4&TIME=&rt=52248969";
                }
            }

            if(bigType == 4){
                bigClass = "资产重组";
                if(middleType == 1){
                    middleClass = "要约收购";
                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=6&CodeType=1&PageIndex="+ page +"&PageSize=100&jsObj=MlXyjZIG&SecNodeType=10&TIME=&rt=52248970";
                }
                if(middleType == 2){
                    middleClass = "吸收合并";
                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=6&CodeType=1&PageIndex="+ page +"&PageSize=100&jsObj=bTpiJWNq&SecNodeType=11&TIME=&rt=52248971";
                }
                if(middleType == 3){
                    middleClass = "回购";
                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=6&CodeType=1&PageIndex="+ page +"&PageSize=100&jsObj=bIbPRAPP&SecNodeType=12&TIME=&rt=52248972";
                }
            }

            if(bigType == 5){
                bigClass = "资产重组";
                url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=3&CodeType=1&PageIndex="+ page +"&PageSize=100&jsObj=XQMcKBjl&SecNodeType=0&TIME=&rt=52248944";
            }
            if(bigType == 6){
                bigClass = "信息变更";
                url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=4&CodeType=1&PageIndex="+ page +"&PageSize=100&jsObj=WNyrOKvn&SecNodeType=0&TIME=&rt=52248946";
            }
            if(bigType == 7){
                bigClass = "持股变动";
                url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=7&CodeType=1&PageIndex="+ page +"&PageSize=100&jsObj=fOGcQIzJ&SecNodeType=0&TIME=&rt=52248947";
            }

            if(StringUtils.isNotBlank(url)){
                System.out.println(bigClass + "======================" + middleClass + "==========================" + page);
                String jsonString = SpiderUtils.HttpClientBuilderGet(url);
                jsonString =  jsonString.substring(jsonString.indexOf("data")-2,jsonString.length()-1);
                System.out.println("===================" + jsonString);
                String data = JSON.parseObject(jsonString).getString("data");
                if(StringUtils.isNotBlank(data)){
                    JSONArray jsonArray = JSON.parseObject(jsonString).getJSONArray("data");
                    List<StockCompanyNotice> list = new ArrayList<>();
                    for(int x = 0 ; x < jsonArray.size() ; x++){
                        JSONObject jsonObject = jsonArray.getJSONObject(x);
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
//            Document doc = SpiderUtils.HtmlJsoupGet(containUrl);
//            Elements elements = doc.body().getElementsByClass("detail-body");
//            Element element = elements.get(0);
//            String containText = element.text();
//			containText = containText.substring(containText.indexOf("误导性陈述或重大遗漏") + 12 ,containText.indexOf("特此公告"));

                        StockCompanyNotice companyNotice = new StockCompanyNotice();
                        companyNotice.setStockCode(stockCode);
                        companyNotice.setStockName(stockName);
                        companyNotice.setPublishTime(publishTime);
                        companyNotice.setBigNoticeClass(bigClass);
                        companyNotice.setMiddleNoticeClass(middleClass);
                        companyNotice.setNoticeTitle(titleName);
                        companyNotice.setNoticeType(nticeType);
                        companyNotice.setNoticeContain(nticetain);
                        companyNotice.setNoticeDetail(containUrl);

                        String className = noticeClassService.getStockNoticeClassCode(bigClass ,middleClass ,nticeType);
                        companyNotice.setClassCode(className);
//                    System.out.println("==============================" + nticeType);
                        StockCompanyNotice notice = selectOne(new EntityWrapper<StockCompanyNotice>().where("stock_code = {0}" , stockCode).where("publish_time = {0}" , publishTime).where("notice_type = {0}" , nticeType).where("notice_title = {0}" , titleName));
                        if(notice == null){
                            list.add(companyNotice);
                        }
                    }
//                System.out.println("=================" + list.toString());
                    if(list.size()>0){
                        just = insertBatch(list);
                    }else{
                        just = false;
                    }
                }else{
                    just = false;
                }
            }else{
                just = false;
            }


        }catch (Exception e){
            just = false;
            e.printStackTrace();
        }
        return just;
    }

    @Override
    public Page<StockNoticeCompany_VO> getStockNoticeCompanyList(Integer page, Integer size, String stockCode, String stockName, String startDay, String endDay) {
        Page<StockNoticeCompany_VO> pageInfo = new Page<StockNoticeCompany_VO>(page, size);
        List<StockNoticeCompany_VO> list = baseMapper.getStockNoticeCompanyList( pageInfo, stockCode, stockName, startDay, endDay);
        list.forEach(company->{
            List<StockNoticeDetail_VO> detailVoList = baseMapper.getStockNoticeDetailList(company.getStockCode(), startDay, endDay);
            company.setDetailVoList(detailVoList);
        });
        pageInfo.setRecords(list);
        return pageInfo;
    }

    @Override
    public Page<StockNoticeDetail_VO> getStockNoticeDetailList(Integer page, Integer size, String stockCode, String startDay, String endDay) {
        Page<StockNoticeDetail_VO> pageInfo = new Page<StockNoticeDetail_VO>(page, size);
        List<StockNoticeDetail_VO> list = baseMapper.getStockNoticeDetailList( pageInfo, stockCode, startDay, endDay);
        pageInfo.setRecords(list);
        return pageInfo;
    }
}
