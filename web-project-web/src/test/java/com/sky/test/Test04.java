package com.sky.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.core.utils.CommonHttpUtil;
import com.sky.model.*;
import com.sky.service.*;
import lombok.ToString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2019/10/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test04 {

    @Autowired
    private SectorDealDataService sectorDealDataService ;

    @Autowired
    private StockIndexClassService stockIndexClassService ;

    @Autowired
    private StockCompanySectorService stockCompanySectorService;

    @Autowired
    private StockHotClassService stockHotClassService ;

    @Autowired
    private StockMarketClassService stockMarketClassService ;

    @Test
    public void test(){
        List<SectorDealData> dataList = sectorDealDataService.spiderSectorDealData(1 , "BK0735");
        System.out.println(dataList.toString());
    }

    @Test
    public void test2(){
        List<StockIndexClass> list = new ArrayList<>();
        for(int i = 1 ; i < 35 ;i++){
            String url = "http://www.csindex.com.cn/zh-CN/indices/index?page="+ i +"&page_size=50&by=asc&order=%E5%8F%91%E5%B8%83%E6%97%B6%E9%97%B4&data_type=json&class_1=1&class_2=2&class_3=3";
            String jsStr = CommonHttpUtil.sendGet(url);
            System.out.println(jsStr);
            JSONObject jsonObject = JSON.parseObject(jsStr);
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            for(int j = 0 ; j < jsonArray.size() ; j++){
               JSONObject jsonObject1 = jsonArray.getJSONObject(j);
                StockIndexClass indexClass = new StockIndexClass();
                indexClass.setIndexCode(jsonObject1.getString("index_code"));
                indexClass.setIndxSname(jsonObject1.getString("indx_sname"));
                indexClass.setStockNum(jsonObject1.getString("num"));
                indexClass.setBasePoint(jsonObject1.getString("base_point"));
                indexClass.setBaseDate(jsonObject1.getString("base_date"));
                indexClass.setOnlineDate(jsonObject1.getString("online_date"));
                indexClass.setIndexIntro(jsonObject1.getString("index_c_intro"));
                indexClass.setIndexFullname(jsonObject1.getString("index_c_fullname"));
                indexClass.setClassSeries(jsonObject1.getString("class_series"));
                indexClass.setClassRegion(jsonObject1.getString("class_region"));
                indexClass.setClassAssets(jsonObject1.getString("class_assets"));
                indexClass.setClassClassify(jsonObject1.getString("class_classify"));
                indexClass.setClassCurrency(jsonObject1.getString("class_currency"));
                list.add(indexClass);
            }
        }
        if(list.size() > 0){
            stockIndexClassService.insertBatch(list);
        }

    }

    @Test
    public void test3(){
        List<StockCompanySector> list = stockCompanySectorService.selectList(new EntityWrapper<StockCompanySector>().where("company_intr IS NULL"));
        for(StockCompanySector sector : list){
            String stockCode = sector.getStockCode();
            String mk = "SZ";
            if(stockCode.substring(0,1).equals("6")){
                mk = "SH";
            }
            String url = "http://f10.eastmoney.com/CompanySurvey/CompanySurveyAjax?code=" + mk + stockCode;
            String jsStr = CommonHttpUtil.sendGet(url);
            JSONObject jsonObject = JSON.parseObject(jsStr).getJSONObject("jbzl");
            String gsjj = jsonObject.getString("gsjj").replace(" ","");
            String jyfw = jsonObject.getString("jyfw").replace(" ","");
            sector.setCompanyIntr(gsjj);
            sector.setCompanyProduct(jyfw);
            stockCompanySectorService.updateById(sector);
        }
    }

    @Test
    public void test4(){
        EntityWrapper<StockMarketClass> entityWrapper = new EntityWrapper();
        entityWrapper.where("class_type = '概念板块'");
        List<StockMarketClass> list = stockMarketClassService.selectList(entityWrapper);
        for(StockMarketClass marketClass : list){
            List<StockHotClass> subList = stockHotClassService.spiderStockHotClass(marketClass.getClassCode() , marketClass.getClassName());
            if(subList.size() > 0){
                stockHotClassService.insertBatch(subList);
            }
        }

    }

    @Test
    public void test5(){
        String url = "http://61.push2.eastmoney.com/api/qt/clist/get?cb=jQuery11240980439168164756_1573119912798&pn=1&pz=1000&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&fid=f3&fs=m:1+s:2&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f26,f22,f33,f11,f62,f128,f136,f115,f152&_=1573119913039";
        String jsStr = CommonHttpUtil.sendGet(url);
        jsStr = jsStr.substring(jsStr.indexOf("({") + 1 ,jsStr.indexOf("})") + 1);
        JSONArray jsonArray = JSON.parseObject(jsStr).getJSONObject("data").getJSONArray("diff");
        List<StockIndexClass> list = new ArrayList<>();
        for(int i = 0 ; i < jsonArray.size() ; i++){
           JSONObject jsonObject = jsonArray.getJSONObject(i);
           StockIndexClass indexClass = new StockIndexClass();
           indexClass.setIndexCode(jsonObject.getString("f12"));
           indexClass.setIndxSname(jsonObject.getString("f14"));
            indexClass.setMarketType(1);
           StockIndexClass exist = stockIndexClassService.selectOne(new EntityWrapper<StockIndexClass>().where("index_code = {0}" , indexClass.getIndexCode()));
           if(exist == null){
               list.add(indexClass);
           }else{
               exist.setMarketType(1);
               stockIndexClassService.updateById(exist);
           }
        }
        if(list.size() > 0){
            stockIndexClassService.insertBatch(list);
        }
    }


/**
 *level 3
 *语法：
 *    1、词组——喜欢——be interested in
 *                   ——like
 *                   ——love
 *                   ——enjoy
 *                   ——prefer
 *                   ——care for
 *           ——擅长——be good at
 *                   ——be okay at
 *                   ——be bad at
 *                   ——be terrible at
 *           ——频率——always   ——100%
 *                   ——usually  ——80%
 *                   ——often    ——60%
 *                   ——sometime ——40%
 *                   ——rarely   ——20%
 *                   ——never    ——0%
 *            ——可能——probably——动词前、be后——极大的肯定
 *                    ——could   ——be前
 *                    ——might   ——be前
 *                    ——maybe   ——句首
 *                    ——perhaps ——句首或句中
 *            ——位置——next to
 *                    ——behind
 *                    ——in the front of
 *                    ——between
 *                    ——on the corner of xxx and xxx
 *                    ——across the stress
 *                    ——across the stress from
 *            ——坐标——street = St.
 *                    ——road = Rd.
 *                    ——avenue = Ave.
 *                    ——boulevard = Blvd.
 *                    ——drive = Dr.
 *                    ——way
 *            ——方向——go straight on
 *                    ——go down
 *                    ——go/turn left/right on
 *                    ——go/turn north/south/east/west
 *                    ——make a left/right on
 *                    ——xxx on the right/left
 *
 *    2、句型——一般询问——What are/do you ...——什么都不知道，进行提问
 *                         ——Do you ...——针对特定的事物，进行提问
 *                         ——Which xxx do/are you ...——两个选一个，进行提问
 *           ——频率询问——How often do you ...
 *                       ——Do/Are you ever ....
 *           ——能力询问——Can you ...?
 *           ——请求询问——Could you ...?
 *                       ——Would you ...?——更礼貌
 *           ——进行询问——Like what?
 *           ——省略询问——You ... ?
 *           ——询问计划——Do you have any plans ...?
 *           ——询问事件——What's up?
 *           ——询问地址——What's the address of xxx,please?
 *                       ——Where's xxx,please?
 *                       ——How do I get to xxx,please?
 *                       ——Can you please tell me how to get xxx?
 *
 *   3、词性——冠词——乐器通用词前(instrument) + an/a
 *                  ——乐器特定词前(guitar) + the
 *          ——介词——on——街道
 *                  ——at——街道门牌号
 *
 *
 *阅读：
 *    1、技巧——看标题
 *           ——看图
 *           ——阅读主题句、第一句
 *
 *句子：——Can you show him around?
 *      ——I know he ......
 *
 *      ——What's your address?/Where do you live?
 *      ——What's the cross street?
 *      ——What's your apartment number?
 *      ——What floor do you live on?
 *
 *      ——Which direction is it?
 *      ——It's west.
 *
 *
 *主题：——询问爱好——询问对方的爱好
 *                  ——询问第三人的爱好
 *      ——询问能力——询问对方能干什么吗
 *      ——询问计划——询问对方有什么安排
 *      ——介绍方位——介绍建筑间的方位
 *      ——指路    ——指路
 *      ——问路    ——问路
 *      ——
 *
 *拓展：——询问爱好——问爱好
 *                  ——为什么喜爱
 *                  ——你能做到同样的事吗
 *                  ——如果我也想尝试、能不能推荐一下
 *      ——询问事物——问是什么
 *                  ——有什么用
 *                  ——怎么用
 *                  ——有什么优缺点
 *                  ——价格多少
 *                  ——哪里可以获得
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
  */
}
