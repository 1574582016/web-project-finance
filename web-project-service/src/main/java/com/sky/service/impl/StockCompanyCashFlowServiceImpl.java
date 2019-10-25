package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.core.utils.CommonHttpUtil;
import com.sky.core.utils.DateUtils;
import com.sky.mapper.StockCompanyCashFlowMapper;
import com.sky.model.StockCompanyCashFlow;
import com.sky.service.StockCompanyCashFlowService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ThinkPad on 2019/10/25.
 */
@Service
@Transactional
public class StockCompanyCashFlowServiceImpl extends ServiceImpl<StockCompanyCashFlowMapper,StockCompanyCashFlow> implements StockCompanyCashFlowService {
    @Override
    public boolean spiderStockCompanyCashFlow(String stockCode ,Integer page) {
        String ukm = "SZ";
        if(stockCode.substring(0,1).equals("6")){
            ukm = "SH";
        }

        String url = "http://f10.eastmoney.com/NewFinanceAnalysis/zcfzbAjax?companyType=4&reportDateType=0&reportType=1&endDate="+ DateUtils.format(DateUtils.addYears(new Date(),-1 * page),"yyyy") +"%2F6%2F30+0%3A00%3A00&code=" + ukm + stockCode;
        if(page == 0){
            url = "http://f10.eastmoney.com/NewFinanceAnalysis/xjllbAjax?companyType=4&reportDateType=0&reportType=1&endDate=&code=" + ukm + stockCode;
        }
        String jsStr = CommonHttpUtil.sendGet(url);
        if(StringUtils.isNotBlank(jsStr)&& !jsStr.equals("\"null\"")){
            jsStr = jsStr.replace("\\","");
            jsStr = jsStr.substring(1,jsStr.length()-1);
            JSONArray jsonArray = JSON.parseArray(jsStr);
            List<StockCompanyCashFlow> assetList = new ArrayList<>();
            for(int i = 0 ; i < jsonArray.size() ; i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                System.out.println(jsonObject.toString());
                StockCompanyCashFlow cashFlow = new StockCompanyCashFlow();
                String publishDay = jsonObject.getString("REPORTDATE");//发布日期
                String businessTotalMoeny = jsonObject.getString("NETOPERATECASHFLOW");//经营活动总现金流
                String businessTotalInMoeny = jsonObject.getString("SUMFINAFLOWIN");//经营活动总流入现金流

                String mainBusinessIncome = jsonObject.getString("SALEGOODSSERVICEREC");//销售商品、提供劳务收到的现金——主营收入
                String customerRepayIncome = jsonObject.getString("NIDEPOSIT");//客户存款和同业存放款项净增加额——客户还钱
                String bankLoanIncome = jsonObject.getString("NIBORROWFROMCBANK");//向中央银行借款净增加额——银行贷款
                String businessCommissionIncome = jsonObject.getString("INTANDCOMMREC");//收取利息、手续费及佣金的现金——业务费
                String loanRepayIncome = jsonObject.getString("NDLOANADVANCES");//发放贷款及垫款的净减少额——外借还款
                String taxRepayIncome = jsonObject.getString("TAXRETURNREC");//收到的税费返还——税收政策优惠
                String businessOtherIncome = jsonObject.getString("OTHEROPERATEREC");//收到其他与经营活动有关的现金

                cashFlow.setStockCode(stockCode);
                cashFlow.setPublishDay(publishDay);
                cashFlow.setBusinessTotalCashFlow(businessTotalMoeny);
                cashFlow.setBusinessTotalInCashFlow(businessTotalInMoeny);
                cashFlow.setMainBusinessIncome(mainBusinessIncome);
                cashFlow.setCustomerRepayIncome(customerRepayIncome);
                cashFlow.setBankLoanIncome(bankLoanIncome);
                cashFlow.setBusinessCommissionIncome(businessCommissionIncome);
                cashFlow.setLoanPayIncome(loanRepayIncome);
                cashFlow.setTaxRepayIncome(taxRepayIncome);
                cashFlow.setBusinessOtherIncome(businessOtherIncome);

                String businessTotalOutMoeny = jsonObject.getString("SUMOPERATEFLOWOUT");//经营活动总流出现金流

                String mainBusinessPay = jsonObject.getString("BUYGOODSSERVICEPAY");//购买商品、接受劳务支付的现金——主营业务支出
                String customerInstallment = jsonObject.getString("NILOANADVANCES");//客户贷款及垫款净增加额——客户分期购买
                String bankLoanPay = jsonObject.getString("NIDEPOSITINCBANKFI");//存放中央银行和同业款项净增加额——付给银行贷款
                String businessCommissionPay = jsonObject.getString("INTANDCOMMPAY");//支付利息、手续费及佣金的现金——办理业务费用
                String staffSalary = jsonObject.getString("EMPLOYEEPAY");//支付给职工以及为职工支付的现金——工资
                String taxPay = jsonObject.getString("TAXPAY");//支付的各项税费——缴纳的税收
                String businessOtherPay = jsonObject.getString("OTHEROPERATEPAY");//支付其他与经营活动有关的现金——

                cashFlow.setBusinessTotalOutCashFlow(businessTotalOutMoeny);
                cashFlow.setMainBusinessPay(mainBusinessPay);
                cashFlow.setCustomerInstallmentBuy(customerInstallment);
                cashFlow.setBankLoanPay(bankLoanPay);
                cashFlow.setMainBusinessPay(businessCommissionPay);
                cashFlow.setStaffSalaryPay(staffSalary);
                cashFlow.setTaxPay(taxPay);
                cashFlow.setBusinessOtherPay(businessOtherPay);

                String investTotalMoeny = jsonObject.getString("NETINVCASHFLOW");//投资活动总现金流
                String investTotalInMoeny = jsonObject.getString("SUMINVFLOWIN");//投资活动总流入现金流

                String investCostIncome = jsonObject.getString("SUMINVFLOWIN");//收回投资收到的现金——投资成本
                String investProfitIncome = jsonObject.getString("SUMINVFLOWIN");//取得投资收益收到的现金——投资收益
                String assetSellIncome = jsonObject.getString("SUMINVFLOWIN");//处置固定资产、无形资产和其他长期资产收回的现金净额——资产售卖
                String sonCompanySellIncome = jsonObject.getString("SUMINVFLOWIN");//处置子公司及其他营业单位收到的现金净额——子公司售卖

                String investTotalOutMoeny = jsonObject.getString("SUMINVFLOWOUT");//投资活动总流出现金流

                String investCostPay = jsonObject.getString("SUMINVFLOWIN");//投资支付的现金——投资支付
                String assetBuyPay = jsonObject.getString("SUMINVFLOWIN");//购建固定资产、无形资产和其他长期资产支付的现金——购买资产
                String sonCompanyPay = jsonObject.getString("SUMINVFLOWIN");//取得子公司及其他营业单位支付的现金净额——投资给子公司的钱

                cashFlow.setInvestTotalCashFlow(investTotalMoeny);
                cashFlow.setInvestTotalInCashFlow(investTotalInMoeny);
                cashFlow.setInvestCostIncome(investCostIncome);
                cashFlow.setInvestProfitIncome(investProfitIncome);
                cashFlow.setAssetSellIncome(assetSellIncome);
                cashFlow.setSonCompanySellIncome(sonCompanySellIncome);
                cashFlow.setInvestTotalOutCashFlow(investTotalOutMoeny);
                cashFlow.setInvestCostPay(investCostPay);
                cashFlow.setAssetBuyPay(assetBuyPay);
                cashFlow.setSonCompanyPay(sonCompanyPay);

                String collectTotalMoeny = jsonObject.getString("NETFINACASHFLOW");//投资活动总现金流
                String collectTotalInMoeny = jsonObject.getString("SUMOPERATEFLOWIN");//投资活动总流入现金流

                String investorPayIncome = jsonObject.getString("ACCEPTINVREC");//吸收投资收到的现金——投资人出资
                String sonCompanyPayIncome = jsonObject.getString("SUBSIDIARYACCEPT");//子公司吸收少数股东投资收到的现金——子公司出资人出资
                String loanPayIncome = jsonObject.getString("LOANREC");//取得借款收到的现金——借的款项

                String collectTotalOutMoeny = jsonObject.getString("SUMFINAFLOWOUT");//投资活动总流出现金流

                String payLoan = jsonObject.getString("REPAYDEBTPAY");//偿还债务支付的现金——还债
                String investorProfit = jsonObject.getString("DIVIPROFITORINTPAY");//分配股利、利润或偿付利息支付的现金——发放投资收益
                String minorityInvestorProfit = jsonObject.getString("SUBSIDIARYPAY");//子公司支付给少数股东的股利、利润——少数投资者投资收益
                String paySonCompanyProfit = jsonObject.getString("OTHERFINAPAY");//支付其他与筹资活动有关的现金

                cashFlow.setCollectTotalCashFlow(collectTotalMoeny);
                cashFlow.setCollectTotalInCashFlow(collectTotalInMoeny);
                cashFlow.setInvestorPayIncome(investorPayIncome);
                cashFlow.setSonCompanyPayIncome(sonCompanyPayIncome);
                cashFlow.setLoanPayIncome(loanPayIncome);
                cashFlow.setCollectTotalOutCashFlow(collectTotalOutMoeny);
                cashFlow.setPayLoan(payLoan);
                cashFlow.setInvestorProfit(investorProfit);
                cashFlow.setMinorityInvestorProfit(minorityInvestorProfit);
                cashFlow.setPaySonCompanyProfit(paySonCompanyProfit);

                System.out.println(jsonObject.toString());
                StockCompanyCashFlow stockCompanyCashFlow = selectOne(new EntityWrapper<StockCompanyCashFlow>().where("stock_code = {0} and publish_day = {1}" ,stockCode ,publishDay));
                if(stockCompanyCashFlow == null){
                    assetList.add(cashFlow);
                }
            }
            if(assetList.size() > 0){
                System.out.println(assetList.toString());
                return insertBatch(assetList);
            }
        }else{
            return false;
        }
        return false;
    }
}
