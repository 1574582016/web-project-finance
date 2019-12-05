package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.core.utils.CommonHttpUtil;
import com.sky.core.utils.SpiderUtils;
import com.sky.core.utils.Tools;
import com.sky.mapper.StockCompanyValueCompareMapper;
import com.sky.model.StockCompanyValueCompare;
import com.sky.service.StockCompanyValueCompareSerivce;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2019/10/6.
 */
@Service
@Transactional
public class StockCompanyValueCompareSerivceImpl extends ServiceImpl<StockCompanyValueCompareMapper,StockCompanyValueCompare> implements StockCompanyValueCompareSerivce {
    @Override
    public StockCompanyValueCompare spiderStockCompanyValueCompare(String staticYear ,String market , String marketNum,String stockCodes, String companyName) {
        StockCompanyValueCompare valueCompare = new StockCompanyValueCompare();
        try{
            valueCompare.setStaticYear(staticYear);
            String url = "http://push2.eastmoney.com/api/qt/stock/get?ut=fa5fd1943c7b386f172d6893dbfba10b&invt=2&fltt=2&fields=f43,f57,f58,f169,f170,f46,f44,f51,f168,f47,f164,f116,f60,f45,f52,f50,f48,f167,f117,f71,f161,f49,f530,f135,f136,f137,f138,f139,f141,f142,f144,f145,f147,f148,f140,f143,f146,f149,f55,f62,f162,f92,f173,f104,f105,f84,f85,f183,f184,f185,f186,f187,f188,f189,f190,f191,f192,f107,f111,f86,f177,f78,f110,f262,f263,f264,f267,f268,f250,f251,f252,f253,f254,f255,f256,f257,f258,f266,f269,f270,f271,f273,f274,f275,f127,f199,f128,f193,f196,f194,f195,f197,f80,f280,f281,f282,f284,f285,f286,f287&secid="+ marketNum +"."+ stockCodes +"&cb=jQuery183029528189124867765_1570337713424&_=1570337713781";
            String resultJson = CommonHttpUtil.sendGet(url);
            System.out.println("=======1=========" + resultJson);
            resultJson = resultJson.substring(resultJson.indexOf("(")  + 1 ,resultJson.indexOf(")"));
            JSONObject jsonObject = JSON.parseObject(resultJson);
            JSONObject dataJson = jsonObject.getJSONObject("data");
            if(dataJson != null){
                String totalValue = dataJson.getString("f116");
                String flowValue = dataJson.getString("f117");
                String currentPrice = dataJson.getString("f43");
                String totalCount = dataJson.getString("f84");
                String flowCount = dataJson.getString("f85");
                valueCompare.setPublishCount(totalCount);
                valueCompare.setFlowCount(flowCount);
                valueCompare.setPublishValue(totalValue);
                valueCompare.setFlowValue(flowValue);
                valueCompare.setCurrentPrice(currentPrice);
            }




            String url2 = "http://push2.eastmoney.com/api/qt/slist/get?spt=1&np=3&fltt=2&invt=2&fields=f9,f12,f13,f14,f20,f23,f37,f45,f49,f134,f135,f129,f1000,f2000,f3000&ut=bd1d9ddb04089700cf9c27f6f7426281&cb=jQuery18303250630669859258_1570336648576&secid="+ marketNum + "."+ stockCodes +"&_=1570336648910";
            String resultJson2 = CommonHttpUtil.sendGet(url2);
            System.out.println("=======2=========" + resultJson2);
            resultJson2 = resultJson2.substring(resultJson2.indexOf("(")  + 1 ,resultJson2.indexOf(")"));
            JSONArray jsonArray = JSON.parseObject(resultJson2).getJSONObject("data").getJSONArray("diff");
            System.out.println(jsonArray.toString());
            String totalAsset = "" ;
            String pureAsset = "" ;
            String pureIntrest = "" ;
            String marketIntrestRate = "" ;
            String marktePureRate = "" ;
            String pocessIntrestRate = "" ;
            String pureIntrestRate = "" ;
            String roe = "" ;

            String mtotalAsset = "" ;
            String mpureAsset = "" ;
            String mpureIntrest = "" ;
            String mmarketIntrestRate = "" ;
            String mmarktePureRate = "" ;
            String mpocessIntrestRate = "" ;
            String mpureIntrestRate = "" ;
            String mroe = "" ;

            String ototalAsset = "" ;
            String opureAsset = "" ;
            String opureIntrest = "" ;
            String omarketIntrestRate = "" ;
            String omarktePureRate = "" ;
            String opocessIntrestRate = "" ;
            String opureIntrestRate = "" ;
            String oroe = "" ;

            String stockCode = "";
            String stockName = "";
            String sectorCode = "";
            String sectorName = "";

            String orderCount = "";
            for(int i = 0 ; i < jsonArray.size() ; i++){
                if(i == 0){
                    JSONObject comJson = jsonArray.getJSONObject(i);
                    stockCode = comJson.getString("f12");
                    stockName = comJson.getString("f14");

                    totalAsset = comJson.getString("f20");
                    pureAsset = comJson.getString("f135");
                    pureIntrest = comJson.getString("f45");
                    marketIntrestRate = comJson.getString("f9");
                    marktePureRate = comJson.getString("f23");
                    pocessIntrestRate = comJson.getString("f49");
                    pureIntrestRate = comJson.getString("f129");
                    roe = comJson.getString("f37");

                    ototalAsset = comJson.getString("f1020");
                    opureAsset = comJson.getString("f1135");
                    opureIntrest = comJson.getString("f1045");
                    omarketIntrestRate = comJson.getString("f1009");
                    omarktePureRate = comJson.getString("f1023");
                    opocessIntrestRate = comJson.getString("f1049");
                    opureIntrestRate = comJson.getString("f1129");
                    oroe = comJson.getString("f1037");
                }

                if(i == 1){
                    JSONObject comJson = jsonArray.getJSONObject(i);
                    sectorCode = comJson.getString("f12");
                    sectorName = comJson.getString("f14");
                    orderCount = comJson.getString("f134");

                    mtotalAsset = comJson.getString("f2020");
                    mpureAsset = comJson.getString("f2135");
                    mpureIntrest = comJson.getString("f2045");
                    mmarketIntrestRate = comJson.getString("f2009");
                    mmarktePureRate = comJson.getString("f2023");
                    mpocessIntrestRate = comJson.getString("f2049");
                    mpureIntrestRate = comJson.getString("f2129");
                    mroe = comJson.getString("f2037");

                }
            }

            valueCompare.setStockCode(stockCode);
            valueCompare.setStockName(stockName);
            valueCompare.setSectorCode(sectorCode);
            valueCompare.setSectorName(sectorName);
            valueCompare.setCompanyCount(orderCount);

            valueCompare.setNetAssets(pureAsset);
            valueCompare.setNetProfit(pureIntrest);
            valueCompare.setPeRatio(marketIntrestRate);
            valueCompare.setPbRatio(marktePureRate);
            valueCompare.setGrossProfitRatio(pocessIntrestRate);
            valueCompare.setNetProfitRatio(pureIntrestRate);
            valueCompare.setRoeRatio(roe);

            valueCompare.setSectorPublishValueAverage(mtotalAsset);
            valueCompare.setSectorNetAssetsAverage(mpureAsset);
            valueCompare.setSectorNetProfitAverage(mpureIntrest);
            valueCompare.setSectorPeRatioAverage(mmarketIntrestRate);
            valueCompare.setSectorPbRatioAverage(mmarktePureRate);
            valueCompare.setSectorGrossProfitRatioAverage(mpocessIntrestRate);
            valueCompare.setSectorNetProfitRatioAverage(mpureIntrestRate);
            valueCompare.setSectorRoeRatioAverage(mroe);

            valueCompare.setSectorPublishValueOrder(ototalAsset);
            valueCompare.setSectorNetAssetsOrder(opureAsset);
            valueCompare.setSectorNetProfitOrder(opureIntrest);
            valueCompare.setSectorPeRatioOrder(omarketIntrestRate);
            valueCompare.setSectorPbRatioOrder(omarktePureRate);
            valueCompare.setSectorGrossProfitRatioOrder(opocessIntrestRate);
            valueCompare.setSectorNetProfitRatioOrder(opureIntrestRate);
            valueCompare.setSectorRoeRatioOrder(oroe);

            String url3 = "http://f10.eastmoney.com/IndustryAnalysis/IndustryAnalysisAjax?code="+ market + stockCodes +"&icode=456";
            String resultJson3 = CommonHttpUtil.sendGet(url3);
            System.out.println("=======3=========" + resultJson3);
            JSONObject jsonObject9 = JSONObject.parseObject(resultJson3);
            JSONArray czxbj = jsonObject9.getJSONObject("czxbj").getJSONArray("data");
            JSONArray gzbj = jsonObject9.getJSONObject("gzbj").getJSONArray("data");
            JSONArray dbfxbj = jsonObject9.getJSONObject("dbfxbj").getJSONArray("data");
            JSONArray gsgmzsz = jsonObject9.getJSONArray("gsgmzsz");

            String czxOrder = "" ;
            String mgsyGrowthRate = "" ;
            String yysrGrowthRate = "" ;
            String jlrGrowthRate = "" ;

            String vmgsyGrowthRate = "" ;
            String vyysrGrowthRate = "" ;
            String vjlrGrowthRate = "" ;

            String mmgsyGrowthRate = "" ;
            String myysrGrowthRate = "" ;
            String mjlrGrowthRate = "" ;

            for(int i = 0 ; i < czxbj.size() ; i ++){
                JSONObject jsonObject5 = czxbj.getJSONObject(i);
                if(i == 0){
                    czxOrder = jsonObject5.getString("pm");
                    mgsyGrowthRate = jsonObject5.getString("jbmgsyzzlfh");
                    yysrGrowthRate = jsonObject5.getString("yysrzzlfh");
                    jlrGrowthRate = jsonObject5.getString("jlrzzlfh");
                }

                if(i == 1){
                    vmgsyGrowthRate = jsonObject5.getString("jbmgsyzzlfh");
                    vyysrGrowthRate = jsonObject5.getString("yysrzzlfh");
                    vjlrGrowthRate = jsonObject5.getString("jlrzzlfh");
                }

                if(i == 2){
                    mmgsyGrowthRate = jsonObject5.getString("jbmgsyzzlfh");
                    myysrGrowthRate = jsonObject5.getString("yysrzzlfh");
                    mjlrGrowthRate = jsonObject5.getString("jlrzzlfh");
                }
            }

            valueCompare.setGrowOrder(czxOrder);
            valueCompare.setGrowEpsg(mgsyGrowthRate);
            valueCompare.setGrowRevinr(yysrGrowthRate);
            valueCompare.setGrowNpgr(jlrGrowthRate);
            valueCompare.setSectorGrowEpsgAverage(vmgsyGrowthRate);
            valueCompare.setSectorGrowEpsgMiddle(mmgsyGrowthRate);
            valueCompare.setSectorGrowRevinrAverage(vyysrGrowthRate);
            valueCompare.setSectorGrowRevinrMiddle(myysrGrowthRate);
            valueCompare.setSectorGrowNpgrAverage(vjlrGrowthRate);
            valueCompare.setSectorGrowNpgrMiddle(mjlrGrowthRate);


            String gzOrder = "" ;
            String gzPEG = "" ;
            String VgzPEG = "" ;
            String MgzPEG = "" ;
            for(int i = 0 ; i < gzbj.size() ; i ++){
                JSONObject jsonObject5 = gzbj.getJSONObject(i);
                if(i == 0){
                    gzOrder = jsonObject5.getString("pm");
                    gzPEG = jsonObject5.getString("peg");
                }

                if(i == 1){
                    VgzPEG = jsonObject5.getString("peg");
                }
                if(i == 2){
                    MgzPEG = jsonObject5.getString("peg");
                }
            }

            valueCompare.setAppraiseOrder(gzOrder);
            valueCompare.setAppraisePeg(gzPEG);
            valueCompare.setSectorAppraisePegAverage(VgzPEG);
            valueCompare.setSectorAppraisePegMiddle(MgzPEG);


            String dbOrder = "" ;
            String dbROE = "" ;
            String dbJLR = "" ;
            String dbZCZZL = "" ;
            String dbQYCS = "" ;

            String VdbROE = "" ;
            String VdbJLR = "" ;
            String VdbZCZZL = "" ;
            String VdbQYCS = "" ;

            String MdbROE = "" ;
            String MdbJLR = "" ;
            String MdbZCZZL = "" ;
            String MdbQYCS = "" ;

            for(int i = 0 ; i < dbfxbj.size() ; i ++){
                JSONObject jsonObject5 = dbfxbj.getJSONObject(i);
                if(i == 0){
                    dbOrder = jsonObject5.getString("pm");
                    dbROE = jsonObject5.getString("roepj");
                    dbJLR = jsonObject5.getString("jllpj");
                    dbZCZZL = jsonObject5.getString("zzczzlpj");
                    dbQYCS = jsonObject5.getString("qycspj");
                }

                if(i == 1){
                    VdbROE = jsonObject5.getString("roepj");
                    VdbJLR = jsonObject5.getString("jllpj");
                    VdbZCZZL = jsonObject5.getString("zzczzlpj");
                    VdbQYCS = jsonObject5.getString("qycspj");
                }

                if(i == 2){
                    MdbROE = jsonObject5.getString("roepj");
                    MdbJLR = jsonObject5.getString("jllpj");
                    MdbZCZZL = jsonObject5.getString("zzczzlpj");
                    MdbQYCS = jsonObject5.getString("qycspj");
                }
            }

            valueCompare.setDupontOrder(dbOrder);
            valueCompare.setDupontRoe(dbROE);
            valueCompare.setDupontNetProfitRatio(dbJLR);
            valueCompare.setDupontAto(dbZCZZL);
            valueCompare.setDupontEm(dbQYCS);

            valueCompare.setSectorDupontRoeAverage(VdbROE);
            valueCompare.setSectorDupontNetProfitRatioAverage(VdbJLR);
            valueCompare.setSectorDupontAtoAverage(VdbZCZZL);
            valueCompare.setSectorDupontEmAverage(VdbQYCS);

            valueCompare.setSectorDupontRoeMiddle(MdbROE);
            valueCompare.setSectorDupontNetProfitRatioMiddle(MdbJLR);
            valueCompare.setSectorDupontAtoMiddle(MdbZCZZL);
            valueCompare.setSectorDupontEmMiddle(MdbQYCS);


            String ltOrder = "" ;
            String zsz = "" ;
            String ltsz = "" ;
            String yysr = "" ;
            String jlr = "" ;

            String Vzsz = "" ;
            String Vltsz = "" ;
            String Vyysr = "" ;
            String Vjlr = "" ;

            String Mzsz = "" ;
            String Mltsz = "" ;
            String Myysr = "" ;
            String Mjlr = "" ;

            for(int i = 0 ; i < gsgmzsz.size() ; i ++){
                JSONObject jsonObject5 = gsgmzsz.getJSONObject(i);
                if(i == 0){
                    ltOrder = jsonObject5.getString("pm");
                    zsz = jsonObject5.getString("zsz");
                    ltsz = jsonObject5.getString("ltsz");
                    yysr = jsonObject5.getString("yysr");
                    jlr = jsonObject5.getString("jlr");
                }

                if(i == 1){
                    Vzsz = jsonObject5.getString("zsz");
                    Vltsz = jsonObject5.getString("ltsz");
                    Vyysr = jsonObject5.getString("yysr");
                    Vjlr = jsonObject5.getString("jlr");
                }

                if(i == 2){
                    Mzsz = jsonObject5.getString("zsz");
                    Mltsz = jsonObject5.getString("ltsz");
                    Myysr = jsonObject5.getString("yysr");
                    Mjlr = jsonObject5.getString("jlr");
                }

            }

            valueCompare.setScaleOrder(ltOrder);
            valueCompare.setTotalMarketValue(zsz);
            valueCompare.setFlowMarketValue(ltsz);
            valueCompare.setBusinessTotalProfit(yysr);
            valueCompare.setBusinessNetProfit(jlr);

            valueCompare.setTotalMarketValueAverage(Vzsz);
            valueCompare.setFlowMarketValueAverage(Vltsz);
            valueCompare.setBusinessTotalProfitAverage(Vyysr);
            valueCompare.setBusinessNetProfitAverage(Vjlr);

            valueCompare.setTotalMarketValueMiddle(Mzsz);
            valueCompare.setFlowMarketValueMiddle(Mltsz);
            valueCompare.setBusinessTotalProfitMiddle(Myysr);
            valueCompare.setBusinessNetProfitMiddle(Mjlr);
            try {
                String compayNa = Tools.stringToUtf8(companyName);
                String url4 = "http://baike.eastmoney.com/item/" + compayNa;
                Document doc = SpiderUtils.HtmlJsoupGet(url4);
                Elements elements = doc.getElementsByClass("company_intro");
                String business = elements.get(0).html();
                valueCompare.setBusinessProduct(business);
            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return valueCompare;
    }
}
