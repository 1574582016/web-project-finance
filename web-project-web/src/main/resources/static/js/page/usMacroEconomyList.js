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
    $.APIPost("/api/macroEconomy/getContryMacroEconomy?contry=美国&indexCode=&startDay=" + $("#s_start").val() + "&endDay=" + $("#s_end").val(),function (result) {
        mainGDP("mainGDP" ,"GDP增长"  ,result);
    });
}

function mainGDP(boxId ,name ,result) {
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
            data:['GDP年化季率']
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
            data: result.data.gdpTitle
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'GDP年化季率',
                type:'line',
                stack: '总量0',
                data:result.data.gdpArr
            }
        ]
    };
    myChart.setOption(option);
}

function ecnomyDraw(boxId ,name ,result) {
    $('#'+ boxId).width($('#mainGDP').width());
    var myChart = echarts.init(document.getElementById(boxId));
    var colors = ['#3a9ff5'];
    var option = {
        color: colors,
        title: {
            text: name
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['谘商会消费者信心指数']
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
            data: result.data.consumConfTitle
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'谘商会消费者信心指数',
                type:'line',
                stack: '总量0',
                data:result.data.consumConfArr
            }
        ]
    };
    myChart.setOption(option);
}



