$(function () {
    $.APIPost("/api/stock/getCompanyProfitGrowList?stockCode=" + $("#stock_code").val() ,function (result) {
        mainProfit(result);
        profitGrowMain("profitGrowMain" ,"利润增长"  ,result);
        profitSeasonMain("profitSeasonMain" ,"季度占比" ,result);
        profitBelongMain("profitBelongMain" ,"利润归属" ,result);
        profitOperateRelation("profitOperateRelation" ,"运营成本&利润关系" ,result);
        profitGrowRate("profitGrowRate" ,"利润增长率" ,result);
    });


});

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
