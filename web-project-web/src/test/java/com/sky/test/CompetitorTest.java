package com.sky.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.core.utils.CommonHttpUtil;
import com.sky.core.utils.DateUtils;
import com.sky.core.utils.SpiderUtils;
import com.sky.model.StockCompanySector;
import com.sky.model.StockIndex;
import com.sky.model.StockInvestorInfo;
import com.sky.model.StockInvestorProduct;
import com.sky.service.StockCompanySectorService;
import com.sky.service.StockInvestorInfoService;
import com.sky.service.StockInvestorProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ThinkPad on 2019/12/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CompetitorTest {

   @Autowired
   private StockCompanySectorService stockCompanySectorService;

   @Autowired
   private StockInvestorInfoService stockInvestorInfoService ;

    @Autowired
    private StockInvestorProductService stockInvestorProductService ;

//    public static void main(String[] args){}

    @Test
    public void test(){
        try {
            String radom = radomNum();
//            List<StockCompanySector> list = stockCompanySectorService.selectList(new EntityWrapper<StockCompanySector>().where("stock_code not in (SELECT stock_code FROM stock_investor_product)"));
            List<StockCompanySector> list = stockCompanySectorService.selectList(null);
            for(StockCompanySector sector : list) {
                String stockCode = sector.getStockCode();
                String marketNum = "1";
                if (stockCode.substring(0, 1).equals("0") || stockCode.substring(0, 1).equals("3")) {
                    marketNum = "0";
                }
                System.out.println("===========stockCode=============" + stockCode);

                String Url = "http://push2.eastmoney.com/api/qt/stock/get?ut=fa5fd1943c7b386f172d6893dbfba10b&invt=2&fltt=2&fields=f43,f57,f58,f169,f170,f46,f44,f51,f168,f47,f164,f116,f60,f45,f52,f50,f48,f167,f117,f71,f161,f49,f530,f135,f136,f137,f138,f139,f141,f142,f144,f145,f147,f148,f140,f143,f146,f149,f55,f62,f162,f92,f173,f104,f105,f84,f85,f183,f184,f185,f186,f187,f188,f189,f190,f191,f192,f107,f111,f86,f177,f78,f110,f262,f263,f264,f267,f268,f250,f251,f252,f253,f254,f255,f256,f257,f258,f266,f269,f270,f271,f273,f274,f275,f127,f199,f128,f193,f196,f194,f195,f197,f80,f280,f281,f282,f284,f285,f286,f287&secid=" + marketNum + "." + stockCode + "&cb=jQuery183029528189124867765_"+ radom +"&_=" +radom;
                String resultJson = CommonHttpUtil.sendGet(Url);
                resultJson = resultJson.substring(resultJson.indexOf("(") + 1, resultJson.indexOf(")"));
                JSONObject jsonObject2 = JSON.parseObject(resultJson);
                JSONObject dataJson = jsonObject2.getJSONObject("data");
                String totalCount = "";
                String flowCount = "";
                if (dataJson != null) {
                    totalCount = dataJson.getString("f84");
                    flowCount = dataJson.getString("f85");
                }

                List<StockInvestorInfo> infoList = new ArrayList<>();
                List<StockInvestorProduct> productList = new ArrayList<>();
//                for(int i = 1 ; i <= 30 ; i++ ){
                    String url = "http://data.eastmoney.com/zlsj/detail.aspx?type=ajax&st=2&sr=-1&p=1&ps=1000&jsObj=UwvjQGfy&stat=0&code=" + stockCode + "&date=2019-09-30&rt=" + radom;
                    String resultStrint = SpiderUtils.HttpClientBuilderGet(url);
                    resultStrint = resultStrint.substring(15, resultStrint.length());
                    JSONObject jsonObject = JSON.parseObject(resultStrint);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int j = 0; j < jsonArray.size(); j++) {


                        JSONObject jsonDetail = jsonArray.getJSONObject(j);
                        String SHName = jsonDetail.getString("SHName");
                        String SHCode = jsonDetail.getString("SHCode");
                        String SHType = jsonDetail.getString("Type");
                        String ShareHDNum = jsonDetail.getString("ShareHDNum");
                        String TypeCode = jsonDetail.getString("TypeCode");
                        String RDate = jsonDetail.getString("RDate");
                        RDate = RDate.substring(RDate.indexOf("(") + 1, RDate.indexOf(")"));
                        RDate = DateUtils.formatDate(new Date(Long.parseLong(RDate)));

                        StockInvestorInfo investorInfo = new StockInvestorInfo();
                        investorInfo.setInvestorCode(SHCode);
                        investorInfo.setInvestorName(SHName);
                        investorInfo.setTypeCode(TypeCode);
                        investorInfo.setTypeName(SHType);

                        StockInvestorInfo info = stockInvestorInfoService.selectOne(new EntityWrapper<StockInvestorInfo>().where("investor_code = {0}" , SHCode));
                        if(info == null){
                            infoList.add(investorInfo);
                        }

                        StockInvestorProduct investorProduct = new StockInvestorProduct();
                        investorProduct.setStockCode(sector.getStockCode());
                        investorProduct.setStockName(sector.getStockName());
                        investorProduct.setInvestorCode(SHCode);
                        investorProduct.setStartTime(RDate);
                        investorProduct.setStaticTime(DateUtils.getDate());
                        investorProduct.setInvestCount(ShareHDNum);
                        investorProduct.setTotalCount(totalCount);
                        investorProduct.setFlowCount(flowCount);

                        StockInvestorProduct product = stockInvestorProductService.selectOne(new EntityWrapper<StockInvestorProduct>().where("investor_code = {0} and stock_code = {1} and static_time = {2}" , SHCode ,sector.getStockCode() , investorProduct.getStaticTime()));
                        if(product == null){
                            productList.add(investorProduct);
                        }
                    }
//                }
                if(infoList.size() > 0){
                    stockInvestorInfoService.insertBatch(infoList);
                }
                if(productList.size() > 0){
                    stockInvestorProductService.insertBatch(productList);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private String radomNum(){
        int max=100000000,min=1;
        long randomNum = System.currentTimeMillis();
        int ran3 = (int) (randomNum%(max-min)+min);
        return ran3+"";
    }
}
