package com.sky.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.core.utils.CommonHttpUtil;
import com.sky.model.SectorDealData;
import com.sky.model.StockMarketClass;
import com.sky.model.StockTigerList;
import com.sky.service.IndexDealDataService;
import com.sky.service.SectorDealDataService;
import com.sky.service.StockMarketClassService;
import com.sky.service.StockTigerListService;
import com.sky.vo.CovarDeal_VO;
import com.sky.vo.CovarStatic_VO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2019/9/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test01 {

    @Autowired
    private StockTigerListService stockTigerListService ;

    @Autowired
    private IndexDealDataService indexDealDataService ;

    @Autowired
    private SectorDealDataService sectorDealDataService ;

    @Autowired
    private StockMarketClassService stockMarketClassService ;

    @Test
    public void test(){
        for(int x = 1 ; x <=50 ; x++){
            String url = "http://data.eastmoney.com/DataCenter_V3/stock2016/TradeDetail/pagesize=50,page="+ x +",sortRule=-1,sortType=,startDate=2019-08-16,endDate=2019-09-27,gpfw=0,js=var%20data_tab_2.html?rt=26160927";
            String jsStr = CommonHttpUtil.sendPost(url);
//        System.out.println(jsStr);
            jsStr = jsStr.substring(jsStr.indexOf("data_tab_2=") + 11 , jsStr.length());
//        System.out.println(jsStr);
            JSONArray jsonArray = JSON.parseObject(jsStr).getJSONArray("data");
            List<StockTigerList> list = new ArrayList<>();
            for(int i = 0 ; i < jsonArray.size() ; i ++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                StockTigerList tigerList = new StockTigerList();
                tigerList.setStockCode(jsonObject.getString("SCode"));
                tigerList.setStockName(jsonObject.getString("SName"));
                tigerList.setPublishTime(jsonObject.getString("Tdate"));
                tigerList.setUpperRange(jsonObject.getBigDecimal("Chgradio"));
                tigerList.setHandRate(jsonObject.getBigDecimal("Dchratio"));
                tigerList.setFocusReason(jsonObject.getString("Ctypedes"));
                tigerList.setBuyMoney(jsonObject.getBigDecimal("Bmoney"));
                tigerList.setSellMoney(jsonObject.getBigDecimal("Smoney"));
                list.add(tigerList);
            }
            if(list.size() > 0){
                stockTigerListService.insertBatch(list);
            }
        }

    }


    @Test
    public void test1(){
        int pointNum = 4;
        List<CovarStatic_VO> staticVoList = new ArrayList<>();
        List<CovarDeal_VO> indexList = indexDealDataService.getIndexDealCovarList("1.000001" ,"1" ,null ,null);
        List<StockMarketClass> marketClassList = stockMarketClassService.selectList(new EntityWrapper<StockMarketClass>().where("class_type = '行业板块'"));
        for(StockMarketClass marketClass : marketClassList){
            String sectorCode = marketClass.getClassCode();
            List<CovarDeal_VO> sectorList = sectorDealDataService.getSectorDealCovarList(sectorCode.substring(0,sectorCode.length()-1) ,"1" ,null ,null);
            if(sectorList.size() > 0){
                BigDecimal count = BigDecimal.ZERO ;
                BigDecimal sectorUpTotal = BigDecimal.ZERO ;
                BigDecimal indexUpTotal = BigDecimal.ZERO ;
                BigDecimal mixUpTotal = BigDecimal.ZERO ;
                BigDecimal sectorQsrTotal = BigDecimal.ZERO ;
                BigDecimal indexQsrTotal = BigDecimal.ZERO ;
                for(CovarDeal_VO setorVo : sectorList){
                    for(CovarDeal_VO indexVo : indexList){
                        if(setorVo.getDealTime().equals(indexVo.getDealTime())){
                            count = count.add(BigDecimal.ONE) ;
                            sectorUpTotal = sectorUpTotal.add(setorVo.getIsUpper());
                            indexUpTotal = indexUpTotal.add(indexVo.getIsUpper());
                            mixUpTotal = mixUpTotal.add(setorVo.getIsUpper().multiply(indexVo.getIsUpper()));
                            sectorQsrTotal = sectorQsrTotal.add(setorVo.getIsUpper().pow(2));
                            indexQsrTotal = indexQsrTotal.add(indexVo.getIsUpper().pow(2));
                        }
                    }
                }
                BigDecimal EXY = mixUpTotal.divide(count , pointNum ,BigDecimal.ROUND_HALF_UP);
                BigDecimal EX_Y = indexUpTotal.multiply(sectorUpTotal).divide(count.multiply(count) , pointNum , BigDecimal.ROUND_HALF_UP);
                BigDecimal COVXY = EXY.subtract(EX_Y);
                BigDecimal DX = (sectorQsrTotal.divide(count , pointNum ,BigDecimal.ROUND_HALF_UP)).subtract((sectorUpTotal.divide(count , pointNum ,BigDecimal.ROUND_HALF_UP)).pow(2));
                BigDecimal DY = (indexQsrTotal.divide(count , pointNum ,BigDecimal.ROUND_HALF_UP)).subtract((indexUpTotal.divide(count , pointNum ,BigDecimal.ROUND_HALF_UP)).pow(2));
                BigDecimal RXY = COVXY.divide(DX.multiply(DY) ,pointNum ,BigDecimal.ROUND_HALF_UP);

                CovarStatic_VO static_vo = new CovarStatic_VO();
                static_vo.setStaticCode(marketClass.getClassCode());
                static_vo.setStaticName(marketClass.getClassName());
                static_vo.setUpperRelevant(RXY);
                staticVoList.add(static_vo);
                System.out.println(static_vo.toString());
            }
        }
        System.out.println(staticVoList.toString());
    }


/**
 *介绍新人——介绍名字
 *        ——介绍职务
 *        ——介绍工作背景、教育经历、个人信息
 *        ——表示欢迎
 *        ——答谢
 *
 *介绍新人——介绍名字——I'd like to introduce xxx
 *                    ——Let me introduce xxx
 *                    ——I'd like you to meet xxx
 *                    ——It's my pleasure to introduce xxx
 *        ——介绍职务——xxx is our new xxxx
 *        ——介绍工作背景——xxx comes to us from xxx ,xxx was the xxx there for five years.
 *                教育经历——xxx has an MBA from Boston University.
 *                个人信息——xxx is married and has three kids
 *        ——表示欢迎——Let's welcome xxx to the company!
 *                    ——Let's all make xxx feel very welcome!
 *        ——答谢——Thanks, everyone, for the warm welcome.
 *                ——Thank you very much. I'm excited to be here.
 *                ——I'd like to thank xxx for that great introduction.
 *
 *KAREN: Good morning, everyone.
 *       It's my pleasure to introduce Kim Zhang.
 *       Kim is our new director of sales.
 *       Kim comes to us from Green Products. He worked there for three years as sales manager.
 *       Kim has an MBA from Harvard University.
 *       He speaks both Chinese and English.
 *       Let's welcome Kim to the company!
 *KIM:  Thank you all very much.
 *      I'm so happy to be here at New Futures.
 *      First, I'd like to thank Karen for that warm introduction
 *
 *BOSS:  Julie, I'd like to introduce Ken Stevens.
 *       Ken is our new director of engineering.
 *JULIE: It's a pleasure to meet you, Ken. Welcome. I work in sales here.
 *KEN:   Oh, thanks, Julie. Nice to meet you, too.
 *BOSS:  Ken comes to us from XYZ Systems. He was the engineering manager for five years.
 *JULIE: Oh, nice. How was it?
 *KEN:   It was good, but I'm happy to be here now.
 *JULIE: This is a great place to work.
 *BOSS:  Good answer, Julie!
 *
 *JOAN:  Excuse me,everyone.
 *       I'd like to introduce Simon Price,
 *       our new director of engineering.
 *       Simon comes to us from Dyna-Tek Systems.He was the engineering manager there for three years.
 *       He has a master's degree in computer science from Stanford University.
 *       Simon worked in Paris for three years,and he speaks fluent French.
 *       Let's welcome Simon to his new company.
 *SIMON: Thanks,everyone,for the warm welcome.
 *       I'm really happy to be here.
 *
 *规则动词过去时——以ed结尾
 *              ——以e结尾—加d
 *              ——以元音y结尾—加ed
 *              ——以辅音y结尾—变y为i—加ed
 *
 *规则动词语态——否定——did + not + 原型
 *            ——疑问——did + 主语 + 原型
 *
 *I worked as a manager.
 *He worked in Paris for three years.
 *They worked in a restaurant last summer.
 *We worked in sales.
 *I moved to a new apartment last week.
 *We played tennis last weekend.
 *She studied for four hours last night.
 *We didn't study last night.
 *He didn't work in London.
 *Did you study last night?
 *Where did you work before?
 *We really enjoyed our vacation.
 *Did you work on Saturday?
 *She wanted to fly, but it was too expensive.
 *When did you move to New York?
 *They didn't play golf. The weather was too bad.
 *He studied all day Saturday and Sunday.
 *We lived in Beijing for two years.
 *The weekend was very busy. I didn't relax for a minute!
 *What did you do last night?
 *I relaxed and played computer games.
 *I really enjoyed the movie. How about you?
 *I didn't like it very much.
 *How long did you work there?
 *I worked there for three years.
 *
 *交换名片——看名字
 *        ——看公司
 *        ——看部门
 *        ——看职务
 *        ——看地址
 *        ——看联系方式
 *
 *部门——研究和发展部门——research and development——R&D
 *    ——信息技术部    ——information technology——IT
 *    ——财务部        ——finance
 *    ——法务部门      ——legal
 *    ——人力资源部门  ——human resource——HR
 *    ——采购部门      ——purchasing
 *    ——运营部门      ——operations
 *    ——客户服务部    ——customer service
 *    ——销售和市场部  ——sales and marketing
 *
 *职位——总裁      ——   ——president
 *    ——副总裁    ——VP ——vice president
 *    ——首席执行官——CEO——chief executive officer
 *    ——首席技术官——CTO——chief technology officer
 *    ——首席财务官——CFO——chief financial officer
 *    ——首席运营官——COO——chief operations officer
 *    ——部门经理  ——   ——the xxx manager
 *    ——部门主管  ——   ——he director of xxx
 *
 * Let me give you my business card.
 *Thanks. Here's mine.
 *
 *JOAN: Did you meet anyone good last night?
 *TODD: I got 20 business cards.I talked to a lot of people.A few of them,very interesting.
 *JOAN: How about for the Finance Department?
 *TODD: Take a look at this one.I wrote some notes on the back.
 *      She has an MBA from Harvard.Right now ,She's working at Network Tek as the CFO.
 *JOAN: Interesting.Is she available?
 *TODD: She wants to talk to us.
 *JOAN: Great! How about for HR?
 *TODD: this guy looks good.
 *      He's an HR manager in a small company in Springdale.
 *      He has a BA in HR management.
 *JOAN: He looks good.That's it for HR?
 *TODD: Yeah.Sorry.
 *JOAN: It's not a problem.Thanks for going last night.
 *
 *
 *不规则动词时态——
 *不规则动词语态——否定——did + not + 原型
 *              ——疑问——did + 主语 + 原型
 *
 *I found a great person for the job.
 *We had a meeting with the CEO.
 *Did you see him at the dinner?
 *He didn't get her business card.
 *She gave a great presentation last week.
 *They took a taxi to the airport.
 *Did you give him your business card?
 *She came too late for the meeting.
 *When did you meet your wife?
 *He didn't go to the movie.
 *They took the train to New York.
 *I got an email from my boss.
 *I was so busy that I didn't eat lunch!
 *We had a wonderful vacation last month.
 *She didn't go with them.
 *I didn't buy it.
 *Did you see him last night?
 *Did they have dinner at the hotel?
 *
 *
 *
 *
 *工作经历——职业
 *        ——所在公司
 *        ——是否喜欢该公司
 *        ——工作中的问题
 *        ——上家公司和这家公司比较
 *
 *职业——banker
 *    ——designer
 *    ——cleaner
 *    ——actor
 *    ——director
 *    ——editor
 *    ——manager
 *    ——driver
 *    ——supervisor
 *    ——illustrator
 *    ——programmer
 *所在公司——Where were you before?
 *
 *公司问题——How's your job?
 *        ——What's your job like?
 *        ——How was your last job?
 *        ——What was your last job like?
 *        ——I like my job, but I work too many hours.
 *        ——The pay is really good in my new job.
 *        ——My boss is OK, but sometimes he's difficult.
 *
 *公司比较——My last job was easier than this job.
 *        ——My pay is better now, but I work longer hours.
 *        ——I was at LM Computers for only one year.
 *        ——My last boss was terrible.
 *
 *
 *SIMON: how's your new job?
 *KELLY: I love it,I'm so happy I got it.
 *SIMON: Cool.That's a great news.
 *KELLY: Yeah. The pay's higher that my last job,and my boss is really nice.
 *SIMON: Where were you before?
 *KELLY: Sunset computer systems.
 *SIMON: How was there?
 *KELLY: It was terrible.The pay was low,my boss was horrible and business was really bad.
 *SIMON: How long were you there for?
 *KELLY: I was there for eight months.Long enough.
 *       But I'm much happier at Nuway Networks.
 *SIMON: That's great.Hey!I have some news.
 *       I have a new job .too.
 *KELLY: Really?
 *SIMON: I can't tell you too much at the moment,because it's a bit of top secret,but...
 *
 *MAN:   I'm very happy in my new job at Tomorrow Computers. I'm a programmer. My last boss was awful. I wasn't happy there.
 *WOMAN: Where were you before ?
 *MAN:   I was at Dyno Systems. I was an engineer.
 *WOMAN: Was that your first job?
 *MAN:   No. Before that , I was at LM Computers.
 *WOMAN: Well, I'm happy that you like your new job.
 *MAN:   Me, too. Thanks.
 *
 *
 *一般过去时时间标志——last——Last week I was on vacation.
 *                          ——Where were you last night?
 *                          ——I like my job now, but my last job was terrible!
 *                  ——before, before that, after ,after that
 *                      ——She was a waiter before she was a supervisor.
 *                      ——She was a supervisor. Before that, she was a waiter.
 *                      ——After she was a supervisor, she was the manager.
 *                      ——She was a supervisor. After that, she was the manager.
 *
 *be动词过去式——I was a banker.
 *            ——He was a computer programmer.
 *            ——She was a supervisor.
 *            ——It was very difficult.
 *            ——You were an actor.
 *            ——We were designers.
 *            ——They were drivers.
 *            ——You were a writer.
 *            ——You were writers.
 *be动词语态——He was an illustrator. / Was he an illustrator?
 *          ——They were managers. / Were they managers?
 *          ——She was a driver. / She was not a driver.
 *          ——I was not a cleaner. / I wasn't a cleaner.
 *          ——They were not editors. / They weren't editors.
 *
 *She's got a great job. She's a computer programmer
 *He works as a waiter in a restaurant, but he wants to be an actor .
 *I'm going to ask my manager for some vacation time.
 *He's a designer for an Italian clothing company.
 *I'm not sure you can help me. I'd like to talk with your supervisor
 *The taxi driver is driving too fast!
 *She manages the managers. She's a director .
 *He doesn't write the books. He's the illustrator .
 *
 *Was the party crowded?
 *Carol wasn't at work today.
 *Were you in the meeting this afternoon?
 *The food was terrible. My friends weren't happy.
 *He was a computer programmer for three years.
 *The fireworks were fantastic!
 *Was he a cleaner?
 *They weren't managers.
 *She wasn't an actor.
 *He was a teacher.
 *Were you a supervisor?
 *They were managers.
 *
 *He was a salesman. After that, he was a manager.
 *Her last job wasn't very good.
 *After he was a writer, he was an actor.
 *Where were you last night?
 *He was a waiter before he was a manager.
 *She was a teacher. Before that, she was a student.
 *
 *How is your boss?——She's very hardworking.
 *How was your last boss?——He wasn't very intelligent.
 *How was your last job?——It was boring.
 *How is your job?——It's great.
 *How long were you there?——I was there for three years.
 *Where were you before?——I was at LM Computers.
 *
 *I was at LM Computers before.
 *I was a manager.
 *It was boring.
 *It wasn't interesting.
 *I was there for about two years.
 *
 *
 *
 *描述工作——职位
 *        ——工作年限
 *        ——职责
 *        ——直接领导人
 *
 *工作职位——I work at Blue Sky Tech. I'm the production manager.
 *        ——My job title is production manager.
 *        ——I worked at Pharmatek for three years. I was a project manager.
 *        ——My job title was project manager.
 *
 *工作时间——I worked there for three years.
 *        ——I worked there from 2009 to 2012.
 *
 *工作职责——I am responsible for marketing new products.
 *        ——I was responsible for the production schedule.
 *
 *报告部门——I report to the CEO.
 *        ——I reported to the vice president of production.
 *
 *
 *I worked at Bio Products for four years.
 *I was the sales manager.
 *I was responsible for all of the salespeople.
 *I managed 15 people.
 *I reported to the director of sales.
 *
 *My job now is at a restaurant.
 *I'm a supervisor.
 *I'm responsible for around 12 servers.
 *It's difficult work, but I like it.
 *Before this job, I worked in a different restaurant as a server.
 *I worked there for about 15 months.
 *I wasn't very happy there.
 *I didn't like my boss.
 *She was very unfriendly. Before that, I was a student.
 *
 *
 *时间的读法——以 19 起头，一般说出 19 + 后两个数字
 *              ——1997 = nineteen ninety-seven
 *          ——以 20 起头，你可以说 20 + 后两个数字，或者 two thousand + 后两个数字
 *              ——2014 = twenty fourteen
 *              ——2014 = two thousand fourteen
 *          ——英国，以 20 起头，期表达中听到 and
 *              ——2014 = two thousand and fourteen
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
