package com.sky;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sky.core.utils.CommonHttpUtil;
import com.sky.core.utils.DateUtils;
import com.sky.core.utils.SpiderUtils;
import com.sky.core.utils.Tools;
import com.sky.model.ForexNewsStatictis;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ThinkPad on 2019/9/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test02 {

    @Test
    public void test(){
        Document doc = SpiderUtils.HtmlJsoupGet("https://news.fx678.com/column/toutiao");
        Elements elements = doc.getElementsByClass("list");
        List<ForexNewsStatictis> list = new ArrayList<>();
        for(int i = 0 ; i < elements.size() ; i++){
            Elements elementLi = elements.get(i).getElementsByTag("li");
            for(int j = 0 ; j < elementLi.size() ; j++){
               Element element = elementLi.get(j);
                String time = element.getElementsByClass("recent").text();
                Elements cElements = element.getElementsByTag("a");

                ForexNewsStatictis newsStatictis = new ForexNewsStatictis();

                newsStatictis.setNewsTime(time + ":00");

                String href = "";
                String title = "";
                String contain = "";
                for(int x = 0 ; x < cElements.size() ; x ++){
                    Element cElement = cElements.get(x);
                    String urlLink = cElement.getElementsByAttribute("href").get(0).attr("href");
                    if(StringUtils.isBlank(title)){
                        title = cElement.getElementsByTag("h3").text();
                    }
                    if(StringUtils.isBlank(contain)){
                        contain = cElement.getElementsByTag("p").text();
                    }
                    if(StringUtils.isBlank(href)){
                        if(urlLink.indexOf(".shtml") > 0){
                            href = "https:" + urlLink;
                        }
                    }
                }
                newsStatictis.setNewsTitle(title);
                newsStatictis.setNewsUrl(href);
                newsStatictis.setNewsContent(contain);
                list.add(newsStatictis);
            }
        }
        System.out.println(list.toString());
    }

    @Test
    public void test02(){
        for(int x = 0 ; x < 20 ; x ++){
            System.out.println(DateUtils.format(DateUtils.addYears(new Date(),-1 * x),"yyyy"));
            String url = "http://f10.eastmoney.com/NewFinanceAnalysis/lrbAjax?companyType=4&reportDateType=0&reportType=1&endDate="+ DateUtils.format(DateUtils.addYears(new Date(),-1 * x),"yyyy") +"%2F6%2F30+0%3A00%3A00&code=SZ000333";
            String jsStr = CommonHttpUtil.sendGet(url);
            if(StringUtils.isNotBlank(jsStr)&& !jsStr.equals("\"null\"")){
                jsStr = jsStr.replace("\\","");
                jsStr = jsStr.substring(1,jsStr.length()-1);
                JSONArray jsonArray = JSON.parseArray(jsStr);
                for(int i = 0 ; i < jsonArray.size() ; i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    System.out.println(jsonObject.getString("REPORTDATE"));
                }
            }else{
                break;
            }

        }
    }

    @Test
    public void test03(){
            String url = "http://f10.eastmoney.com/NewFinanceAnalysis/lrbAjax?companyType=4&reportDateType=0&reportType=1&endDate=&code=SZ000333";
            String jsStr = CommonHttpUtil.sendGet(url);
            jsStr = jsStr.replace("\\","");
            jsStr = jsStr.substring(1,jsStr.length()-1);
            JSONArray jsonArray = JSON.parseArray(jsStr);
            for(int i = 0 ; i < jsonArray.size() ; i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                BigDecimal totalIncome = jsonObject.getBigDecimal("TOTALOPERATEREVE");//总收入
                BigDecimal businessIncome = jsonObject.getBigDecimal("OPERATEREVE");//营业收入
                BigDecimal totalCost = jsonObject.getBigDecimal("TOTALOPERATEEXP");//总成本
                BigDecimal businessCost = jsonObject.getBigDecimal("OPERATEEXP");//营业成本
                BigDecimal totalRevenue = jsonObject.getBigDecimal("OPERATEPROFIT");//营业利润 + 其他营业利润
                BigDecimal otherTotalRevenue = jsonObject.getBigDecimal("SUMPROFIT");//营业利润 + 其他营业利润 + 其他收入利润
                BigDecimal taxMoney = jsonObject.getBigDecimal("INCOMETAX");//扣税金额
                BigDecimal belongProfit = jsonObject.getBigDecimal("PARENTNETPROFIT");//扣除其他利润分配后，归属公司利润
                BigDecimal lastProfit = jsonObject.getBigDecimal("KCFJCXSYJLR");//扣税损益后，最终的剩余利润
                System.out.println(jsonObject.toString());
            }
    }

    @Test
    public void test04(){
        String url = "http://f10.eastmoney.com/NewFinanceAnalysis/zcfzbAjax?companyType=4&reportDateType=0&reportType=1&endDate=&code=SZ000333";
        String jsStr = CommonHttpUtil.sendGet(url);
        jsStr = jsStr.replace("\\","");
        jsStr = jsStr.substring(1,jsStr.length()-1);
        JSONArray jsonArray = JSON.parseArray(jsStr);
        for(int i = 0 ; i < jsonArray.size() ; i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String publishDay = jsonObject.getString("REPORTDATE");//发布日期
            BigDecimal totalAssetDebt = jsonObject.getBigDecimal("SUMLIABSHEQUITY");//总资产负债
            //资产
            BigDecimal totalAssets = jsonObject.getBigDecimal("SUMASSET");//总资产
            //流动资产
            BigDecimal totalFlowAssets = jsonObject.getBigDecimal("SUMLASSET");//总流动资产

            BigDecimal flowAssetCurrency = jsonObject.getBigDecimal("MONETARYFUND");//货币资产
            BigDecimal flowAssetPay = jsonObject.getBigDecimal("ADVANCEPAY");//预付款资产
            BigDecimal flowAssetStorage = jsonObject.getBigDecimal("INVENTORY");//库存资产

            BigDecimal flowAssetBill = jsonObject.getBigDecimal("ACCOUNTBILLREC");//应收账款资产
            BigDecimal flowAssetOherBill = jsonObject.getBigDecimal("OTHERREC");//其他应收账款资产

            BigDecimal flowAssetOther = jsonObject.getBigDecimal("OTHERLASSET");//其他资产
            //非流动资产
            BigDecimal totalUnFlowAssets = jsonObject.getBigDecimal("SUMNONLASSET");//非流动总资产

            BigDecimal unflowFixedAssets = jsonObject.getBigDecimal("FIXEDASSET");//固定资产
            BigDecimal unflowBuildProduct = jsonObject.getBigDecimal("CONSTRUCTIONPROGRESS");//在建工程
            BigDecimal unflowHouse = jsonObject.getBigDecimal("ESTATEINVEST");//投资型房地产

            BigDecimal unflowStockRight = jsonObject.getBigDecimal("LTEQUITYINV");//长期股权投资
            BigDecimal unflowLongBill = jsonObject.getBigDecimal("LTREC");//长期应收款
            BigDecimal unflowFinacial = jsonObject.getBigDecimal("SALEABLEFASSET");//可供出售金融资产

            BigDecimal unflowIntangible = jsonObject.getBigDecimal("INTANGIBLEASSET");//无形资产
            BigDecimal unflowReputation = jsonObject.getBigDecimal("GOODWILL");//商誉

            BigDecimal unflowPrepaidExpenses = jsonObject.getBigDecimal("LTDEFERASSET");//长期待摊费用
            BigDecimal unflowDeferredTax = jsonObject.getBigDecimal("DEFERINCOMETAXASSET");//递延所得税资产

            BigDecimal unflowOher = jsonObject.getBigDecimal("OTHERNONLASSET");//其他非流动资产

            //负债
            BigDecimal totalDebt = jsonObject.getBigDecimal("SUMLIAB");//总负债
            //流动负债
            BigDecimal totalFlowDebt = jsonObject.getBigDecimal("SUMLLIAB");//总流动负债

            BigDecimal flowSortLoan = jsonObject.getBigDecimal("STBORROW");//短期借款
            BigDecimal flowCenterLoan = jsonObject.getBigDecimal("BORROWFROMCBANK");//向中央银行借款
            BigDecimal flowSameCompanyLoan = jsonObject.getBigDecimal("DEPOSIT");//吸收存款及同业存放

            BigDecimal flowBill = jsonObject.getBigDecimal("ACCOUNTBILLPAY");//应付票据及应付账款
            BigDecimal flowReceive = jsonObject.getBigDecimal("ADVANCERECEIVE");//预收款项

            BigDecimal flowSalary = jsonObject.getBigDecimal("SALARYPAY");//应付职工薪酬
            BigDecimal flowTax = jsonObject.getBigDecimal("TAXPAY");//应交税费
            BigDecimal flowOherPay = jsonObject.getBigDecimal("OTHERPAY");//其他应付款合计

            BigDecimal flowSoonDeadLine = jsonObject.getBigDecimal("NONLLIABONEYEAR");//一年内到期的非流动负债
            BigDecimal flowOher = jsonObject.getBigDecimal("OTHERLLIAB");//其他流动负债

            //非流动负债
            BigDecimal totalUnFlowDebt = jsonObject.getBigDecimal("SUMNONLLIAB");//总非流动负债


            BigDecimal unflowLongLoan = jsonObject.getBigDecimal("STBORROW");//长期借款
            BigDecimal unflowLongPay = jsonObject.getBigDecimal("STBORROW");//长期应付款

            BigDecimal unflowLongSalary = jsonObject.getBigDecimal("STBORROW");//长期应付职工薪酬
            BigDecimal unflowSpecialPay = jsonObject.getBigDecimal("STBORROW");//专项应付款
            BigDecimal unflowEstimateLoan = jsonObject.getBigDecimal("STBORROW");//预计负债

            BigDecimal unflowDeferredProfit = jsonObject.getBigDecimal("STBORROW");//递延收益
            BigDecimal unflowDeferredTaxDebt = jsonObject.getBigDecimal("STBORROW");//递延所得税负债

            BigDecimal unflowOherDebt = jsonObject.getBigDecimal("STBORROW");//其他非流动负债

            System.out.println(jsonObject.toString());
        }
    }

/**
 * 开会——议程
 *     ——企业问题
 *
 *JOAN:  Okay,Let's get started.Do you all have the agenda?
 *       The purpose of this meeting is to talk about our relationship with Sunset Computers since the merger.
 *       The five of us are here because we do the most business with them.
 *       The first item on the agenda is news about Sunset.
 *       Are you all aware that Archie Morgan,their CEO,has resigned?
 *HARRY: I didn’t know that.
 *SALLY: No,neither did I.
 *JOAN:  Yes,well,our relationship with him just wasn’t working.
 *       Sunset’s chief financial officer,Mary Ozaki,will also be leaving.
 *SIMON: That’s good news.
 *       I’m sorry,but it’s time for a change over there.
 *JOAN:  Okay.Let’s move on to the next item.
 *       I’ll probably be flying out to meet the Sunset executives on Monday.
 *       The CEO’s in a meeting about this right now.
 *       I’ll have more information later today.
 *       The last item on the agenda is our problems with Sunset.
 *       I’ve been hearing some complaints from different people here,but I need more detailed information.
 *SALLY: If you ask me,the primary problem’s communication,or lack of it.
 *JOAN:  Could you be more specific,Sally?
 *SALLY: Well,they’re very slow in answering our questions.
 *       For instance,just yesterday,I phoned their director of marketing and left a message.
 *       Then I sent an email.Still no answer.
 *HARRY: Do they even have cellphones?
 *       I’m serious.I can’t get cellphone numbers from them.
 *TODD:  A huge challenge for me is figuring our who’s responsible for what.
 *SIMON: I have to say that their director of engineering,Ed Fife,is incredible.
 *       He gets me what I need quickly,and it’s always accurate.
 *JOAN:  This is all very interesting.
 *JOAN:  All right,so communication has really been the big issue with Sunset.
 *       What can we do about it?
 *       I mean,when I’m there on Monday,in a meeting with them,what solution can I propose?
 *SALLY: Well,a new CEO and CFO is a good start,but is it enough?
 *       One way to solve the problem might be to replace even more managers.
 *SIMON: I’m not sure I agree with that.
 *TODD:  And I really disagree.
 *       We have to be careful about their morale.
 *       They’re losing their two biggest executives.
 *       That’s a huge change already.
 *HARRY: Well,at least let’s send them to some communication training.They’re awful !
 *JOAN:  That’s a possibility.
 *TODD:  I’d like to propose some formal team-building.
 *JOAN:  Okay.What do you have in mind?
 *TODD:  How difficult would it be to bring some of the Sunset managers here for,say,a week?
 *JOAN:  Well,it’s not in the budget,but...
 *SALLY: I really like that idea.
 *       We need to meet these people.
 *HARRY: I think so,too.
 *       If they come here and see how we do things...
 *SIMON: Let’s take that one step further and send people over there,too.
 *       We need to start building personal relationships with them.
 *JOAN:  Well,I think those are all excellent ideas.
 *       I can’t make any promises,but I’ll try to find some money for them.
 *TODD:  And,of course,if you do go there,you’re going to be evaluating their managers.
 *       ......
 *JOAN:  So,I just had a short meeting with our CEO,David.
 *       I’ve been given the task of coming up with a plan of coordination between our two companies.
 *       I’ll be flying out there on Monday.
 *HARRY: Congratulations !
 *JOAN:  Thank you,Harry.
 *       Now,as I was saying,it’ll be my job to get the two companies working together smoothly.
 *       I’ll probably be flying back and forth for the next few weeks.
 *       Todd will in charge here whenever I’m at Sunset.
 *       That’s going to involve a lot of planing,and I don’t have much time,so,let’s conclude this meeting quickly.
 *       Now,where was I?Oh,yes.
 *       As a next step,in the next week or so,please keep me informed about any problems that you’re having with Sunset.
 *       Give me details.
 *       I’d really appreciate your input on this.
 *       Let me see.
 *       I’ll follow up on the idea of team building,and I’ll also send an email summarizing this meeting.
 *       Oh,and one more thing:This discussion is confidential.Nothing leaves this room.
 *TODD:  Yes,please.
 *       No talking about firing people at Sunset or flying them here.
 *       Those poor people have enough rumors to worry about already.
 *JOAN:  Okay.That’s it for now.Thank you all for coming.
 *       Todd and Simon.Could you stay behind?
 *       ......
 *       Okay,so David met with the board of directors this morning.
 *       They’ve decided to move quickly to integrate the two companies.
 *TODD:  Smart move.Finally.
 *JOAN:  Yes.Simon made me aware of major problems there three months ago.
 *       I did tell David about it,but,CEOs sometimes just...
 *       Anyway,after listening to Sally and Harry today,I wish that I’d pushed David more.
 *       Now the board is not happy.
 *TODD:  We all should’ve pushed David more.
 *JOAN:  Yes,maybe we should have.
 *       But what’s done is done.
 *       Now,I need help with a decision.
 *       The board has decided not to replace the CEO of Sunset.
 *SIMON: I’m not surprised.
 *TODD:  It’s a reasonable move.
 *       If we’re integrating the two companies,we don’t need two CEOs.
 *JOAN:  But we’ll be hiring a chief operating officer for Sunset.
 *SIMON: A COO? That makes sense.
 *       Someone has to be in charge over there.
 *JOAN:  And I was offered the job.
 *TODD:  Should we congratulate you?
 *JOAN:  No.I turned it down.
 *       I have a great team and family in this city.
 *       And Sunset is a mess.
 *SIMON: I’m glad that you’re staying.
 *       And I think that I know why I’m in this meeting.
 *JOAN:  Tell me about your friend,Ed Fife.
 *SIMON: Oh,he’s wonderful.
 *       Excellent manager.
 *       His team loves him.
 *JOAN:  Thanks.
 *       I’ll talk to him when I’m at Sunset.
 *       Well,that’s it for now.
 *       Thanks for everything,guys.
 *TODD:  Any doubts or regrets about turning down the job?
 *JOAN:  None.
 *
 *
 *一些商务会议中可以用到的词汇：
 *    There are five items on today's agenda.
 *    The merger of our two companies is still going ahead.
 *    The final decision will be made by the CEO.
 *    The CFO is responsible for reporting all company income.
 *    John is our new marketing executive.
 *    Our biggest challenge is to increase our market share.
 *    We have to figure out a solution to this problem.
 *    Their CEO resigned last week.
 *    They just fired their marketing executive.
 *    The figures in the report were not accurate.
 *    I was not aware how serious the situation was.
 *
 *    I'm sure if we all work together, we can figure it out.
 *    The financial report must be completely accurate.
 *    Any last minute items to be added to the agenda ?
 *    It's not one company buying another. It's a merger .
 *    A good manager can deal with any challenge .
 *    The board of directors forced her to resign.
 *
 *    How many items are there on the agenda?
 *    I wasn't aware of the merger until yesterday.
 *    Did the CEO resign or was he fired?
 *
 *在会议开始时通常我们有必要吸引每个人的注意力。检查一下是否人手一份日程表。务必要感谢每一个人的当场，然后告诉他们此次会议的目的。
 *    OK, everyone. If I could have your attention, please. Let's get started.
 *    Does everyone have an agenda? As you can see, we have three items to discuss today.
 *对到场的人表示感谢十分重要。
 *    Thank you all for coming today. I know how busy you all are.
 *确保每个到场人员明白会议目的。
 *    The purpose of today's meeting is to discuss the CFO's resignation and her replacement.
 *    We are here because a competitor has proposed a merger with our company.
 *可以用日程来开始会议。
 *    The first item on the agenda is a company update from the CEO.
 *    OK. Let's begin with item number one, the company update.
 *
 *    Thank you all for coming.
 *    We are here to figure out a solution.
 *    Let's get started.
 *    The second item on the agenda is the update.
 *    If I could have your attention,please.
 *    The purpose of the meeting is to discuss the merger.
 *
 *    The first item on the agenda is an update from the CFO.
 *    Does everyone have an agenda?
 *    If I could have your attention, please.
 *    OK, everyone, let's get started.
 *    The purpose of today's meeting is a company update.
 *    Thank you all for coming. I know that you're busy.
 *
 *陈述问题时请务必做到简明扼要。用问题或挑战这类词汇来清晰地说明问题。
 *    The primary problem is a difference in management style.
 *    The biggest challenge we face is growing competition.
 *如果有问题没有搞懂，请要求对方澄清。
 *    A: The biggest problem is their CFO.
 *    B: Could you be more specific?
 *用细节和例子增强针对性。
 *    For instance, they don't return our phone calls.
 *    The best example I can give is our decreasing market share.
 *
 *    We need more detailed information?
 *    What's the primary problem?
 *    We face many challenges.
 *    For instance,our marketing share is small.
 *    There are a number of issues.
 *    Could you be more specific?
 *
 *    OK, everyone. Could I have your attention?
 *    Does everyone have a copy of the agenda?
 *    The purpose of our meeting is to discuss salaries.
 *    The primary problem is our competitiveness.
 *    For instance, we're losing sales in Asia.
 *    This is the biggest challenge we face.
 *
 *以下是提出和讨论议案可以用到的词汇：
 *    How could we take the idea one step further?
 *    What do you think about team building? Would that solve the problem?
 *    We need to do something to improve employee morale.
 *
 *    What about the solution I proposed last month?
 *    We hope the salary increase will improve morale .
 *    After an excellent start , the idea went nowhere.
 *    We like your idea, but we'd like to take it further .
 *    I like team building , but it doesn't solve everything.
 *    Unfortunately, the money just isn't in the budget.
 *
 *    Every employee is evaluated by a manager.
 *    What exactly do you propose as a solution?
 *    I can't promise you a bigger budget for that project.
 *    How could we take the idea one step further?
 *    Do team-building exercises improve morale?
 *    The negotiations are off to a good start.
 *
 *对某人或某事表示同意或不同意有很多种说法。以下表示同意：
 *    Yeah, that would be OK. (mild agreement)
 *    I think so, too. (agreement)
 *    I agree with you. (agreement)
 *    We're on the same page. (agreement)
 *    I really like that idea. (strong agreement)
 *    I think those are excellent ideas. (strong agreement)
 *
 *以下是不同意的表达：
 *    I'm not sure I agree with that. (mild disagreement)
 *    I'm sorry, but I disagree. (polite disagreement)
 *    I'm not with you on that. (disagreement)
 *    I really disagree with you. (strong disagreement)
 *    I think that's a terrible idea. (very strong disagreement)
 *
 *    We're definitely on the same page.
 *
 *间接或礼貌的提议
 *    One way to solve the problem might be to increase the training budget.
 *    At least let's talk about increasing the training budget.
 *    How difficult would it be to increase the training budget?
 *
 *正式或直接的提议
 *    I'd like to propose that we increase the training budget.
 *    I propose that we increase the training budget.
 *    Let's take that one step further and increase the training budget.
 *
 *    I propose that excellent employees get rewarded.
 *    How difficult would it be to increase the budget?
 *    I didn't think we'd have all of these problems.
 *    One way to solve the problem is to change the seating.
 *    Team members who miss deadlines should be punished.
 *    Hang on a minute. I'm not sure I agree with you.
 *
 *
 *    She completed all of her tasks smoothly.
 *    The meeting will involve a lot of different people.
 *    Who will be in charge while you're away?
 *    We need to come up with a coordination plan.
 *    This back and forth between Paris and London is tough.
 *    The sooner we can conclude the deal, the better.
 *    I've informed the board about the merger rumors.
 *
 *用一般将来时will和其否定形式won't来表示自愿做某事、承诺或预测。
 *    I'll help you.
 *    I promise I won't spread any rumors.
 *    I'm sure that team coordination will improve.
 *
 *用一般将来时be going to来谈论计划或预测。
 *    He is going to travel to Mexico City next week.
 *    I believe we're going to have a merger.
 *
 *用将来进行时will或be going to加动词be和一个现在分词来谈论将来会发生的持续性的动作。
 *    I'll be doing some back and forth between here and Mexico.
 *    She's going to be running the office while he's away.
 *
 * 用将来完成时*will*或be going to，动词have加上一个过去分词来讨论将来某个时间点已经完成的动作。
 *    The meeting will have concluded by the time you return.
 *    We won't have finished the project by next week.
 *
 *    Will the meeting have concluded by noon?
 *    Do you think she will come up with a solution soon?
 *    He'll be traveling all next month.
 *    She will be managing the office next week.
 *    They promised they won't spread any rumors.
 *    I will be glad to help you. What do you need?
 *
 *当需要在讨论中开始一个新观点时可以用到这些表达。当你需要一点时间思考时可以用let me see。
 *    Let me see. The next point of discussion is challenges in the coming year.
 *    The next item on the agenda is an update from our CFO.
 *    Moving on, let's discuss the plan to expand overseas.
 *    Oh, and one more thing: we need to discuss a change in employee insurance.
 *有时候讨论会偏题。如果讨论偏题，可以用这些表达来带回主题：
 *    Now, as I was saying, our competitive situation in Europe has changed.
 *    Now, where was I? Oh, yes – the sales figures for last quarter.
 *    Getting back to my point, I think a merger is not an option.
 *
 *用这些表达来说明会议即将结束：
 *    OK, I know we're all busy, so let's conclude this meeting quickly.
 *    I think that's about it. Let's wrap things up.
 *    Unless anyone has anything else, I think we're ready to talk about next steps.
 *
 *用这些表达来谈论未来计划。表达未来自愿做某事时，人们会使用will。
 *    I'll follow up on finding a better location for the office.
 *    Moving forward, we will need to learn more about our competition.
 *
 *当下一步需要间接地提出命令时，经理可以用“let's”。
 *    As a next step, let's all think about ways to improve communication.
 *
 *用这些表达来讨论保密性：
 *    I just want to say again that this discussion is confidential.
 *    Nothing we've discussed here leaves this room.
 *
 *用这些表达来结束会议以及感谢与会者：
 *    OK, that's it for now. Thank you all for coming.
 *    I think we're finished here. Thanks very much to you all.
 *    This has been a very productive meeting. I appreciate all of you being here.
 *
 *    As a next step, I'll follow up with a company report.
 *    Everything we've discussed is strictly confidential.
 *    I want to thank you all for a very productive meeting.
 *    That's about it. Let's wrap things up.
 *    Unless anyone has anything else, I think we're done.
 *    Nothing we've discussed here leaves this room.
 *    I think we're ready to talk about next steps.
 *
 *注意以下对话中动词和名词的不同形式：
 *    A: Do you regret turning down the job?
 *    B: Yes, I do have some doubts.
 *    A: I'm under a lot of pressure right now.
 *    B: You mean, due to the integration of the two companies?
 *    A: Yes. I'm afraid that if I don't do my job well, they'll replace me.
 *    A: Congratulations on your promotion!
 *    B: Thanks, but no need to congratulate me. It just means more work.
 *
 *    She was replaced because her department was a mess.
 *    He turned down the job of chief operating officer.
 *    We think it makes sense to integrate the two companies.
 *    There's a lot of pressure from the board of directors.
 *    I don't have any doubts or regrets about my decision.
 *
 *用这些表达来询问是否有遗憾。记住，regret这个词既可以是名词，又可以是动词。
 *    Do you have any regrets about leaving the company?
 *    Do you regret turning down the job?
 *用这些表达来表示遗憾。注意，that可用可不用。
 *    I wish I had accepted the job.
 *    She regrets that she didn't accept the position.
 *    She regrets turning down the position.
 *    We should have hired the other candidate.
 *用What's done is done.来表示接受现实。
 *    What's done is done.
 *
 *    I think the decision was a smart move.
 *    I think it's a reasonable move on your part.
 *    He's decided to move quickly to replace the COO.
 *    I need help with a very difficult decision.
 *    I'm not surprised about the decision.
 *    The board has decided to hire him. Finally.
 *
 *名词性从句在句子中做名词用。用动词+that+名词性从句来写会议总结非常有效。注意，that可用可不用。
 *    She proposed that the board of directors replace the CFO.
 *    He decided the merger didn't make sense.
 *    We all agreed that it was a reasonable move.
 *    I suggested we move quickly.
 *
 *注意，有些表示紧急情况或重要性的动词后面跟的是名词性从句，句中动词用的是原形。这叫the subjunctive（虚拟语气）。
 *   The CEO insisted that we be on time.
 *   I suggested that he take the job.
 *
 *
 *
 *
 *
 *
 *
 */
}
