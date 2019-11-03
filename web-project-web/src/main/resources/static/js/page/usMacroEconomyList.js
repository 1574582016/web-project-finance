$(function () {
    $('#s_start').datepicker({
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        clearBtn: true ,
        autoclose: true
    });

    $('#s_end').datepicker({
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        clearBtn: true ,
        autoclose: true
    });

    searchUSdata();

    $("#searchDataButton").click(function () {
        searchUSdata();
    });

});

function searchUSdata() {
    $.APIPost("/api/macroEconomy/getContryMacroEconomy?contry=美国&indexCodes=375,343&startDay=" + $("#s_start").val() + "&endDay=" + $("#s_end").val(),function (result) {
        main("mainGDP" ,"GDP增长"  ,result);
    });

    $.APIPost("/api/macroEconomy/getContryMacroEconomy?contry=美国&indexCodes=168&startDay=" + $("#s_start").val() + "&endDay=" + $("#s_end").val(),function (result) {
        main("mainInterest" ,"利率决议"  ,result);
    });

    $.APIPost("/api/macroEconomy/getContryMacroEconomy?contry=美国&indexCodes=888&startDay=" + $("#s_start").val() + "&endDay=" + $("#s_end").val(),function (result) {
        commonLine("firePeople" ,"挑战者企业裁员人数"  ,result);
    });

    $.APIPost("/api/macroEconomy/getContryMacroEconomy?contry=美国&indexCodes=1307,161,1755,31&startDay=" + $("#s_start").val() + "&endDay=" + $("#s_end").val(),function (result) {
        commonLine("factoryProduce" ,"工业产出(滞后)"  ,result);
    });

    $.APIPost("/api/macroEconomy/getContryMacroEconomyMonth?contry=美国&indexCodes=890,895,234,235&startDay=" + $("#s_start").val() + "&endDay=" + $("#s_end").val(),function (result) {
        commonLine("incomeRate" ,"个人收入&支出月率"  ,result);
    });

    $.APIPost("/api/macroEconomy/getContryMacroEconomyMonth?contry=美国&indexCodes=1046,1048&startDay=" + $("#s_start").val() + "&endDay=" + $("#s_end").val(),function (result) {
        console.log(result);
        commonLine("ISMJob" ,"ISM就业指数"  ,result);
    });

    $.APIPost("/api/macroEconomy/getContryMacroEconomy?contry=美国&indexCodes=320,900&startDay=" + $("#s_start").val() + "&endDay=" + $("#s_end").val(),function (result) {
        commonLine("zshXX" ,"密歇根大学消费者预期指数"  ,result);
    });

    $.APIPost("/api/macroEconomy/getContryMacroEconomy?contry=美国&indexCodes=48&startDay=" + $("#s_start").val() + "&endDay=" + $("#s_end").val(),function (result) {
        commonLine("custRate" ,"谘商会消费者信心指数"  ,result);
    });

    $.APIPost("/api/macroEconomy/getUsMarkitPMIIndex?contry=美国&startDay=" + $("#s_start").val() + "&endDay=" + $("#s_end").val(),function (result) {
        commonLine("markitPMI" ,"Markit采购经理人指数(PMI)"  ,result);
    });

    $.APIPost("/api/macroEconomy/getContryMacroEconomyMonth?contry=美国&indexCodes=173,176&startDay=" + $("#s_start").val() + "&endDay=" + $("#s_end").val(),function (result) {
        commonLine("ISMPMI" ,"ISM采购经理人指数(PMI)"  ,result);
    });

    $.APIPost("/api/macroEconomy/getContryMacroEconomy?contry=美国&indexCodes=294,522,1041&startDay=" + $("#s_start").val() + "&endDay=" + $("#s_end").val(),function (result) {
        commonLine("jobWeek" ,"每周请失业金人数"  ,result);
    });

    $.APIPost("/api/macroEconomy/getWeekLossJobCount?startDay=" + $("#s_start").val() + "&endDay=" + $("#s_end").val(),function (result) {
        console.log(result);
        jobWeekLoss("jobWeekLoss" ,"每周净失业人数"  ,result);
    });

    $.APIPost("/api/macroEconomy/getContryMacroEconomy?contry=美国&indexCodes=1&startDay=" + $("#s_start").val() + "&endDay=" + $("#s_end").val(),function (result) {
        commonLine("jobADP" ,"ADP就业人数"  ,result);
    });

    $.APIPost("/api/macroEconomy/getContryMacroEconomy?contry=美国&indexCodes=227,300,1581&startDay=" + $("#s_start").val() + "&endDay=" + $("#s_end").val(),function (result) {
        commonLine("jobEmployee" ,"非农就业人口"  ,result);
    });
}

function main(boxId ,name ,result) {
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
            data:result.data.nameArr
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
            data: result.data.titleArr
        },
        yAxis: {
            type: 'value'
        },
        series: result.data.dataArr
    };
    myChart.setOption(option);
}

function commonLine(boxId ,name ,result) {
    $('#'+ boxId).width($('#mainGDP').width());
    var myChart = echarts.init(document.getElementById(boxId));
    var colors = ['#3a9ff5', '#34bd37','#e80b3e','#15d2cb', '#322de8','#be09bf','#9e9b0d', '#839cc3','#3e712e'];
    var option = {
        color: colors,
        title: {
            text: name
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:result.data.nameArr
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
            data: result.data.titleArr
        },
        yAxis: {
            type: 'value'
        },
        series: result.data.dataArr
    };
    myChart.setOption(option);
}

function jobWeekLoss(boxId ,name ,result) {
    $('#'+ boxId).width($('#mainGDP').width());
    var myChart = echarts.init(document.getElementById(boxId));
    var colors = ['#3a9ff5', '#34bd37','#e80b3e','#15d2cb', '#322de8','#be09bf','#9e9b0d', '#839cc3','#3e712e'];
    var option = {
        color: colors,
        title: {
            text: name
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:result.data.nameArr
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
            data: result.data.titleArr
        },
        yAxis: {
            type: 'value'
        },
        series: result.data.dataArr
    };

    var option = {
        color: colors,
        title: {
            text: name
        },
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            data:result.data.nameArr
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data: result.data.titleArr
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series: result.data.dataArr
    };
    myChart.setOption(option);
}



