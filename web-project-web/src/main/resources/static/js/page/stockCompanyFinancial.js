$(function () {
    $.APIPost("/api/stock/getCompanyProfitGrowList?stockCode=" + $("#stock_code").val() ,function (result) {
        mainProfit(result);
        profitGrowMain("profitGrowMain" ,"利润增长"  ,result);
        profitSeasonMain("profitSeasonMain" ,"季度占比" ,result);
        profitBelongMain("profitBelongMain" ,"利润归属" ,result);
        profitOperateRelation("profitOperateRelation" ,"运营成本&利润关系" ,result);
        profitGrowRate("profitGrowRate" ,"利润增长率" ,result);
    });

    $.APIPost("/api/stock/getCompanyAssetGrowList?stockCode=" + $("#stock_code").val() ,function (result) {
        // mainProfit(result);
        assetGrowMain("assetGrowMain" ,"资产增长"  ,result);
        assetDebtRate("assetDebtRate" ,"资产负债比例"  ,result);
        flowAssetRate("flowAssetRate" ,"流动&固定比例"  ,result);

        flowAssetComponent("flowAssetComponent" ,"流动资产成分比例"  ,result);
        unflowAssetComponent("unflowAssetComponent" ,"非流动资产成分比例"  ,result);

        flowDetbComponent("flowDetbComponent" ,"流动负债成分比例"  ,result);
        unflowDetbComponent("unflowDetbComponent" ,"非流动负债成分比例"  ,result);
    });


});


function assetGrowMain(boxId ,name ,result) {
    $('#'+ boxId).width($('#mainProfit').width());
    var myChart = echarts.init(document.getElementById(boxId));
    var colors = ['#3a9ff5', '#34bd37','#e80b3e'];
    var option = {
        color: colors,
        title: {
            text: name
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['总资产','总负债','净资产']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: result.data.title
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'总资产',
                type:'line',
                stack: '总量0',
                data:result.data.totalAsset
            },{
                name:'总负债',
                type:'line',
                stack: '总量1',
                data:result.data.totalDebt
            },{
                name:'净资产',
                type:'line',
                stack: '总量2',
                data:result.data.pureAsset
            }
        ]
    };
    myChart.setOption(option);
}

function assetDebtRate(boxId ,name ,result) {
    $('#'+ boxId).width($('#mainProfit').width());
    var myChart = echarts.init(document.getElementById(boxId));
    var colors = ['#3a9ff5','#e80b3e'];
    var option = {
        color: colors,
        title: {
            text: name
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['负债比例','资产比例']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: result.data.title
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'负债比例',
                type:'line',
                stack: '总量0',
                data:result.data.debtAssetRate
            },{
                name:'资产比例',
                type:'line',
                stack: '总量1',
                data:result.data.pureAssetRate
            }
        ]
    };
    myChart.setOption(option);
}

function flowAssetRate(boxId ,name ,result) {
    $('#'+ boxId).width($('#mainProfit').width());
    var myChart = echarts.init(document.getElementById(boxId));
    var colors = ['#e80b3e' ,'#3a9ff5', '#34bd37', '#d3ff24'];
    var option = {
        color: colors,
        title: {
            text: name
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['固定资产比例','流动资产比例','固定负债比例','流动负债比例']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: result.data.title
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'固定资产比例',
                type:'line',
                stack: '总量0',
                data:result.data.totalUnflowAssetRate
            },{
                name:'流动资产比例',
                type:'line',
                stack: '总量1',
                data:result.data.totalFlowAssetRate
            },{
                name:'固定负债比例',
                type:'line',
                stack: '总量2',
                data:result.data.unflowDebtRate
            },{
                name:'流动负债比例',
                type:'line',
                stack: '总量3',
                data:result.data.flowDebtRate
            }
        ]
    };
    myChart.setOption(option);
}

function flowAssetComponent(boxId ,name ,result) {
    $('#'+ boxId).width($('#mainProfit').width());
    var myChart = echarts.init(document.getElementById(boxId));
    var colors = ['#e80b3e' ,'#3a9ff5', '#34bd37'];
    var option = {
        color: colors,
        title: {
            text: name
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['可用资本','票据资本','其他资本']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: result.data.title
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'可用资本',
                type:'line',
                stack: '总量0',
                data:result.data.useAssetRate
            },{
                name:'票据资本',
                type:'line',
                stack: '总量1',
                data:result.data.billAssetRate
            },{
                name:'其他资本',
                type:'line',
                stack: '总量2',
                data:result.data.flowOtherAssetRate
            }
        ]
    };
    myChart.setOption(option);
}

function unflowAssetComponent(boxId ,name ,result) {
    $('#'+ boxId).width($('#mainProfit').width());
    var myChart = echarts.init(document.getElementById(boxId));
    var colors = ['#e80b3e' ,'#3a9ff5', '#34bd37', '#d3ff24', '#86846b'];
    var option = {
        color: colors,
        title: {
            text: name
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['固定资产','投资资产','无形资产','延期资产','其他资产']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: result.data.title
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'固定资产',
                type:'line',
                stack: '总量0',
                data:result.data.fixedAssetRate
            },{
                name:'投资资产',
                type:'line',
                stack: '总量1',
                data:result.data.investAssetRate
            },{
                name:'无形资产',
                type:'line',
                stack: '总量2',
                data:result.data.virtualAssetRate
            },{
                name:'延期资产',
                type:'line',
                stack: '总量3',
                data:result.data.deferredAssetRate
            },{
                name:'其他资产',
                type:'line',
                stack: '总量4',
                data:result.data.unflowOtherAssetRate
            }
        ]
    };
    myChart.setOption(option);
}

function flowDetbComponent(boxId ,name ,result) {
    $('#'+ boxId).width($('#mainProfit').width());
    var myChart = echarts.init(document.getElementById(boxId));
    var colors = ['#e80b3e' ,'#3a9ff5', '#34bd37', '#d3ff24'];
    var option = {
        color: colors,
        title: {
            text: name
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['贷款负债','票据负债','支付负债','其他负债']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: result.data.title
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'贷款负债',
                type:'line',
                stack: '总量0',
                data:result.data.loanDebtRate
            },{
                name:'票据负债',
                type:'line',
                stack: '总量1',
                data:result.data.billDebtRate
            },{
                name:'支付负债',
                type:'line',
                stack: '总量2',
                data:result.data.payDebtRate
            },{
                name:'其他负债',
                type:'line',
                stack: '总量3',
                data:result.data.otherDebtRate
            }
        ]
    };
    myChart.setOption(option);
}

function unflowDetbComponent(boxId ,name ,result) {
    $('#'+ boxId).width($('#mainProfit').width());
    var myChart = echarts.init(document.getElementById(boxId));
    var colors = ['#e80b3e' ,'#3a9ff5', '#34bd37', '#d3ff24'];
    var option = {
        color: colors,
        title: {
            text: name
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['贷款负债','支付负债','延期负债','其他负债']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: result.data.title
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'贷款负债',
                type:'line',
                stack: '总量0',
                data:result.data.unflowLoanDebtRate
            },{
                name:'支付负债',
                type:'line',
                stack: '总量1',
                data:result.data.unflowPayDebtRate
            },{
                name:'延期负债',
                type:'line',
                stack: '总量2',
                data:result.data.unflowDeferredDebtRate
            },{
                name:'其他负债',
                type:'line',
                stack: '总量3',
                data:result.data.unflowOtherDebtRate
            }
        ]
    };
    myChart.setOption(option);
}

function mainProfit(result) {
    $("#total_profit_score").html(result.data.profitLevel.rightRightRate);
    $("#total_profit_levle").html(result.data.profitLevel.rightRightLevel);

    $("#profit_grow_score").html(result.data.profitLevel.isGrowRate);
    $("#profit_grow_levle").html(result.data.profitLevel.isGrowLevel);

    $("#profit_belong_score").html(result.data.profitLevel.isBlongRate);
    $("#profit_belong_levle").html(result.data.profitLevel.isBlongLevel);

    $("#profit_other_score").html(result.data.profitLevel.belongOtherRate);
    $("#profit_other_levle").html(result.data.profitLevel.belongOtherLevel);

    var myChart = echarts.init(document.getElementById("mainProfit"));
    var colors = ['#3a9ff5', '#e80b3e','#34bd37', '#d3ff24'];
    var option = {
        color: colors,
        title: {
            text: "利润分析"
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['总利润','归属利润','最终利润','对外利润']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: result.data.title
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'总利润',
                type:'line',
                stack: '总量0',
                data:result.data.totalProfit
            },{
                name:'归属利润',
                type:'line',
                stack: '总量1',
                data:result.data.belongProfitRate
            },{
                name:'最终利润',
                type:'line',
                stack: '总量2',
                data:result.data.finalProfitRate
            },{
                name:'对外利润',
                type:'line',
                stack: '总量3',
                data:result.data.otherCompanyProfitRate
            }
        ]
    };
    myChart.setOption(option);
}

function profitGrowMain(boxId ,name ,result) {
    $('#'+ boxId).width($('#mainProfit').width());
    var myChart = echarts.init(document.getElementById(boxId));
    var colors = ['#3a9ff5', '#e80b3e','#34bd37', '#d3ff24'];
    var option = {
        color: colors,
        title: {
            text: name
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['总利润','主营利润','辅营利润','其他利润']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: result.data.title
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'总利润',
                type:'line',
                stack: '总量0',
                data:result.data.totalProfit
            },{
                name:'主营利润',
                type:'line',
                stack: '总量1',
                data:result.data.mainBusinessProfit
            },{
                name:'辅营利润',
                type:'line',
                stack: '总量2',
                data:result.data.viceBusinessProfit
            },{
                name:'其他利润',
                type:'line',
                stack: '总量3',
                data:result.data.otherProfit
            }
        ]
    };
    myChart.setOption(option);
}

function profitSeasonMain(boxId ,name ,result) {
    $('#'+ boxId).width($('#mainProfit').width());
    var myChart = echarts.init(document.getElementById(boxId));
    var colors = ['#34bd37','#3a9ff5', '#e80b3e', '#d3ff24'];
    var option = {
        color: colors,
        title: {
            text: name
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['第一季度','第二季度','第三季度','第四季度']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: result.data.title
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'第一季度',
                type:'line',
                stack: '总量0',
                data:result.data.firstProfitRate
            },{
                name:'第二季度',
                type:'line',
                stack: '总量1',
                data:result.data.secondProfitRate
            },{
                name:'第三季度',
                type:'line',
                stack: '总量2',
                data:result.data.threeProfitRate
            },{
                name:'第四季度',
                type:'line',
                stack: '总量3',
                data:result.data.forthtProfitRate
            }
        ]
    };
    myChart.setOption(option);
}

function profitBelongMain(boxId ,name ,result) {
    $('#'+ boxId).width($('#mainProfit').width());
    var myChart = echarts.init(document.getElementById(boxId));
    var colors = ['#d3ff24','#e80b3e','#34bd37'];
    var option = {
        color: colors,
        title: {
            text: name
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['归属利润','最终利润','对外利润']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: result.data.title
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'归属利润',
                type:'line',
                stack: '总量1',
                data:result.data.belongProfitRate
            },{
                name:'最终利润',
                type:'line',
                stack: '总量2',
                data:result.data.finalProfitRate
            },{
                name:'对外利润',
                type:'line',
                stack: '总量3',
                data:result.data.otherCompanyProfitRate
            }
        ]
    };
    myChart.setOption(option);
}

function profitOperateRelation(boxId ,name ,result) {
    $('#'+ boxId).width($('#mainProfit').width());
    var myChart = echarts.init(document.getElementById(boxId));
    var colors = ['#3a9ff5', '#d3ff24', '#e80b3e'];
    var option = {
        color: colors,
        title: {
            text: name
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['总利润','经营成本','归属利润']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: result.data.title
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'总利润',
                type:'line',
                stack: '总量0',
                data:result.data.totalProfit
            },{
                name:'经营成本',
                type:'line',
                stack: '总量1',
                data:result.data.operateCost
            },{
                name:'归属利润',
                type:'line',
                stack: '总量2',
                data:result.data.belongProfit
            }
        ]
    };
    myChart.setOption(option);
}

function profitGrowRate(boxId ,name ,result) {
    $('#'+ boxId).width($('#mainProfit').width());
    var myChart = echarts.init(document.getElementById(boxId));
    var colors = ['#3a9ff5', '#e80b3e'];
    var option = {
        color: colors,
        title: {
            text: name
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['总利润增长率','主营利润增长率']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: result.data.title
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'总利润增长率',
                type:'line',
                stack: '总量0',
                data:result.data.totalProfitGrowRate
            },{
                name:'主营利润增长率',
                type:'line',
                stack: '总量1',
                data:result.data.mainBusinessProfitRate
            }
        ]
    };
    myChart.setOption(option);
}