package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.core.utils.CommonHttpUtil;
import com.sky.core.utils.DateUtils;
import com.sky.mapper.StockCompanyAssetMapper;
import com.sky.model.StockCompanyAsset;
import com.sky.service.StockCompanyAssetService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ThinkPad on 2019/10/22.
 */
@Service
@Transactional
public class StockCompanyAssetServiceImpl extends ServiceImpl<StockCompanyAssetMapper,StockCompanyAsset> implements StockCompanyAssetService {

    @Override
    public boolean spiderStockCompanyAsset(String stockCode, Integer page) {
        String ukm = "SZ";
        if(stockCode.substring(0,1).equals("6")){
            ukm = "SH";
        }

        String url = "http://f10.eastmoney.com/NewFinanceAnalysis/zcfzbAjax?companyType=4&reportDateType=0&reportType=1&endDate="+ DateUtils.format(DateUtils.addYears(new Date(),-1 * page),"yyyy") +"%2F6%2F30+0%3A00%3A00&code=" + ukm + stockCode;
        if(page == 0){
            url = "http://f10.eastmoney.com/NewFinanceAnalysis/zcfzbAjax?companyType=4&reportDateType=0&reportType=1&endDate=&code=" + ukm + stockCode;
        }
        String jsStr = CommonHttpUtil.sendGet(url);
        if(StringUtils.isNotBlank(jsStr)&& !jsStr.equals("\"null\"")){
            jsStr = jsStr.replace("\\","");
            jsStr = jsStr.substring(1,jsStr.length()-1);
            JSONArray jsonArray = JSON.parseArray(jsStr);
            List<StockCompanyAsset> assetList = new ArrayList<>();
            for(int i = 0 ; i < jsonArray.size() ; i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                StockCompanyAsset asset = new StockCompanyAsset();
                String publishDay = jsonObject.getString("REPORTDATE");//发布日期
                String totalAssetDebt = jsonObject.getString("SUMLIABSHEQUITY");//总资产负债
                //资产
                String totalAssets = jsonObject.getString("SUMASSET");//总资产
                //流动资产
                String totalFlowAssets = jsonObject.getString("SUMLASSET");//总流动资产

                String flowAssetCurrency = jsonObject.getString("MONETARYFUND");//货币资产
                String flowAssetPay = jsonObject.getString("ADVANCEPAY");//预付款资产
                String flowAssetStorage = jsonObject.getString("INVENTORY");//库存资产

                String flowAssetBill = jsonObject.getString("ACCOUNTBILLREC");//应收账款资产
                String flowAssetOherBill = jsonObject.getString("OTHERREC");//其他应收账款资产

                String flowAssetOther = jsonObject.getString("OTHERLASSET");//其他资产

                asset.setStockCode(stockCode);
                asset.setPublishDay(publishDay);
                asset.setTotalAssetDebt(totalAssetDebt);
                asset.setTotalAsset(totalAssets);
                asset.setTotalFlowAsset(totalFlowAssets);
                asset.setFlowCurrencyAsset(flowAssetCurrency);
                asset.setFlowPayAsset(flowAssetPay);
                asset.setFlowStorageAsset(flowAssetStorage);
                asset.setFlowBillAsset(flowAssetBill);
                asset.setFlowOtherBill(flowAssetOherBill);
                asset.setFlowOtherAsset(flowAssetOther);

                //非流动资产
                String totalUnFlowAssets = jsonObject.getString("SUMNONLASSET");//非流动总资产

                String unflowFixedAssets = jsonObject.getString("FIXEDASSET");//固定资产
                String unflowBuildProduct = jsonObject.getString("CONSTRUCTIONPROGRESS");//在建工程
                String unflowHouse = jsonObject.getString("ESTATEINVEST");//投资型房地产

                String unflowStockRight = jsonObject.getString("LTEQUITYINV");//长期股权投资
                String unflowLongBill = jsonObject.getString("LTREC");//长期应收款
                String unflowFinacial = jsonObject.getString("SALEABLEFASSET");//可供出售金融资产

                String unflowIntangible = jsonObject.getString("INTANGIBLEASSET");//无形资产
                String unflowReputation = jsonObject.getString("GOODWILL");//商誉

                String unflowPrepaidExpenses = jsonObject.getString("LTDEFERASSET");//长期待摊费用
                String unflowDeferredTax = jsonObject.getString("DEFERINCOMETAXASSET");//递延所得税资产

                String unflowOher = jsonObject.getString("OTHERNONLASSET");//其他非流动资产


                asset.setTotalUnflowAsset(totalUnFlowAssets);
                asset.setUnflowFixedAsset(unflowFixedAssets);
                asset.setUnflowBuildProject(unflowBuildProduct);
                asset.setUnflowInvestHouse(unflowHouse);
                asset.setUnflowStockRight(unflowStockRight);
                asset.setUnflowLongBill(unflowLongBill);
                asset.setUnflowSellFinacial(unflowFinacial);
                asset.setUnflowIntangibleAsset(unflowIntangible);
                asset.setUnflowCompanyReputation(unflowReputation);
                asset.setUnflowPrepaidExpenses(unflowPrepaidExpenses);
                asset.setUnflowDeferredTaxAsset(unflowDeferredTax);
                asset.setUnflowOtherAsset(unflowOher);

                //负债
                String totalDebt = jsonObject.getString("SUMLIAB");//总负债
                //流动负债
                String totalFlowDebt = jsonObject.getString("SUMLLIAB");//总流动负债

                String flowSortLoan = jsonObject.getString("STBORROW");//短期借款
                String flowCenterLoan = jsonObject.getString("BORROWFROMCBANK");//向中央银行借款
                String flowSameCompanyLoan = jsonObject.getString("DEPOSIT");//吸收存款及同业存放

                String flowBill = jsonObject.getString("ACCOUNTBILLPAY");//应付票据及应付账款
                String flowReceive = jsonObject.getString("ADVANCERECEIVE");//预收款项

                String flowSalary = jsonObject.getString("SALARYPAY");//应付职工薪酬
                String flowTax = jsonObject.getString("TAXPAY");//应交税费
                String flowOherPay = jsonObject.getString("OTHERPAY");//其他应付款合计

                String flowSoonDeadLine = jsonObject.getString("NONLLIABONEYEAR");//一年内到期的非流动负债
                String flowOher = jsonObject.getString("OTHERLLIAB");//其他流动负债


                asset.setTotalDebt(totalDebt);
                asset.setTotalFlowDebt(totalFlowDebt);
                asset.setFlowSortLoan(flowSortLoan);
                asset.setFlowCenterBankLoan(flowCenterLoan);
                asset.setFlowSameCompanyLoan(flowSameCompanyLoan);
                asset.setFlowPayBill(flowBill);
                asset.setFlowAdvanceReceive(flowReceive);
                asset.setFlowPaySalary(flowSalary);
                asset.setFlowPayTax(flowTax);
                asset.setFlowPayOther(flowOherPay);
                asset.setFlowSortDebt(flowSoonDeadLine);
                asset.setFlowOther(flowOher);

                //非流动负债
                String totalUnFlowDebt = jsonObject.getString("SUMNONLLIAB");//总非流动负债

                String unflowLongLoan = jsonObject.getString("STBORROW");//长期借款
                String unflowLongPay = jsonObject.getString("STBORROW");//长期应付款

                String unflowLongSalary = jsonObject.getString("STBORROW");//长期应付职工薪酬
                String unflowSpecialPay = jsonObject.getString("STBORROW");//专项应付款
                String unflowEstimateLoan = jsonObject.getString("STBORROW");//预计负债

                String unflowDeferredProfit = jsonObject.getString("STBORROW");//递延收益
                String unflowDeferredTaxDebt = jsonObject.getString("STBORROW");//递延所得税负债

                String unflowOherDebt = jsonObject.getString("STBORROW");//其他非流动负债

                asset.setTotalUnflowDebt(totalUnFlowDebt);
                asset.setUnflowLongLoan(unflowLongLoan);
                asset.setUnflowPayLong(unflowLongPay);
                asset.setUnflowPaySalary(unflowLongSalary);
                asset.setUnflowPaySpecial(unflowSpecialPay);
                asset.setUnflowEstimateLoan(unflowEstimateLoan);
                asset.setUnflowDeferredProfit(unflowDeferredProfit);
                asset.setUnflowDeferredTaxDebt(unflowDeferredTaxDebt);
                asset.setUnflowOtherDebt(unflowOherDebt);
                StockCompanyAsset stockCompanyAsset = selectOne(new EntityWrapper<StockCompanyAsset>().where("stock_code = {0} and publish_day = {1}" ,stockCode ,publishDay));
                if(stockCompanyAsset == null){
                    assetList.add(asset);
                }
            }
            if(assetList.size() > 0){
                return insertBatch(assetList);
            }
        }else{
            return false;
        }
        return false;
    }
}
