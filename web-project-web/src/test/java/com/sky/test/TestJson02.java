package com.sky.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.core.utils.CommonHttpUtil;
import com.sky.core.utils.SpiderUtils;
import com.sky.model.StockCompanyProduct;
import com.sky.model.StockCompanySector;
import com.sky.model.StockDealDataVol;
import com.sky.service.StockCompanyProductService;
import com.sky.service.StockCompanySectorService;
import com.sky.service.StockDealDataVolService;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2020/3/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJson02 {

    @Autowired
    private StockCompanySectorService stockCompanySectorService;


    @Autowired
    private StockCompanyProductService stockCompanyProductService;

    @Test
    public void test1(){
        List<StockCompanySector> sectorList = stockCompanySectorService.selectList(new EntityWrapper<StockCompanySector>().where("stock_code not in ( select stock_code from stock_company_product)"));
        for(StockCompanySector sector : sectorList){
            System.out.println("=================================" + sector.getStockCode());
            String market = "SH";
            if(!sector.getStockCode().substring(0,1).equals("6")){
                market = "SZ";
            }
            try{
                String url = "http://webf10.gw.com.cn/"+ market +"/B3/"+ market + sector.getStockCode() +"_B3.html";
                Document document = SpiderUtils.HtmlJsoupGet(url);
                Element table = document.getElementsByClass("zyjg_tabel_item").get(0);
                Elements trs = table.getElementsByTag("tr");
                String rows = "";
                List<StockCompanyProduct> productList = new ArrayList<>();
                for(int i = 1 ; i < trs.size() ; i++){
                    Element tr = trs.get(i);
                    Elements types = tr.getElementsByClass("first_child");
                    if(types.size() > 0){
                        Element type = types.first();
                        if(StringUtils.isBlank(rows)){
                            rows = type.text();
                        }else{
                            break;
                        }
                    }
                    Elements tds = tr.getElementsByTag("td");
                    String name = "";
                    String rate = "";
                    if(i == 1){
                        name = tds.get(1).text();
                        rate = tds.get(3).text();
                    }else {
                        name = tds.get(0).text();
                        rate = tds.get(2).text();
                    }
//                System.out.println(name + "=============" + rate);
                    StockCompanyProduct product = new StockCompanyProduct();
                    product.setStockCode(sector.getStockCode());
                    product.setProductName(name);
                    product.setProductRevenueRate(rate);
                    productList.add(product);
                }
                if(productList.size() > 0){
                    stockCompanyProductService.insertBatch(productList);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }


    @Test
    public void test2(){
        List<StockCompanySector> sectorList = stockCompanySectorService.selectList(null);
        for(StockCompanySector sector : sectorList) {
            System.out.println("=================================" + sector.getStockCode());
            String market = "SH";
            if (!sector.getStockCode().substring(0, 1).equals("6")) {
                market = "SZ";
            }

            try {
                String url = "http://webf10.gw.com.cn/" + market + "/B7/" + market + sector.getStockCode() + "_B7.html";
                Document document = SpiderUtils.HtmlJsoupGet(url);
                Elements elements = document.getElementsByClass("gsxq_table");
                Element td = elements.get(0).getElementsByTag("tr").get(0);
                String publishTime = td.getElementsByClass("w4").text();
                sector.setPublishTime(publishTime);
                stockCompanySectorService.updateById(sector);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Autowired
    private StockDealDataVolService stockDealDataVolService;

    @Test
    public void test3(){

        String stockCode = "300659";
        String mk = "1";
        if(!stockCode.substring(0,1).equals("6")){
            mk = "2";
        }
        String day = "2020-05-06";

        for(int page = 0 ; page < 33 ; page ++){
            String url = "http://push2ex.eastmoney.com/getStockFenShi?pagesize=144&ut=7eea3edcaed734bea9cbfc24409ed989&dpt=wzfscj&cb=jQuery112308016765534968187_1585036699321&pageindex="+ page +"&id="+ stockCode + mk +"&sort=1&ft=1&code="+ stockCode +"&market="+ mk +"&_=1588810344031";
            String jsStr = CommonHttpUtil.sendGet(url);
            jsStr = jsStr.substring(jsStr.indexOf("(") + 1 , jsStr.indexOf(")"));
            JSONObject jsonObject = JSON.parseObject(jsStr);
            JSONObject dataObject = jsonObject.getJSONObject("data");
            JSONArray jsonArray = dataObject.getJSONArray("data");
            List<StockDealDataVol> list = new ArrayList<>();
            for(int i = 0 ; i < jsonArray.size() ; i ++){
                JSONObject volJSON = jsonArray.getJSONObject(i);
                BigDecimal price = volJSON.getBigDecimal("p").divide(BigDecimal.valueOf(1000) , 2 ,BigDecimal.ROUND_HALF_UP);
                int type = volJSON.getInteger("bs");
                String time = volJSON.getString("t");
                BigDecimal vol = volJSON.getBigDecimal("v");

                String miao = time.substring(time.length() - 2 , time.length());
                String fen = time.substring(time.length() - 4 , time.length() - 2);
                String shi = time.substring(0 , time.length() - 4);
                if(shi.equals("9")){
                    time = "09:" + fen + ":" + miao ;
                }else{
                    time = shi + ":" + fen + ":" + miao ;
                }

                StockDealDataVol dataVol = new StockDealDataVol();
                dataVol.setStockCode(stockCode);
                dataVol.setDealTime(day + " " + time);
                dataVol.setDealType(type);
                dataVol.setDealPrice(price);
                dataVol.setDealCount(vol);
//            System.out.println(time + "================" + price + "====================" + vol + "==================" + type);
                list.add(dataVol);
            }
//            System.out.println(list.toString());
            if(list.size()>0){
                stockDealDataVolService.insertBatch(list);
            }
        }

    }
}
