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
    $.APIPost("/api/stockIndex/getStockIndexDataList?indexCodes=1.000903,1.000904,1.000905,1.000906,1.000852&dealPeriod="+ $("#dealPeriod").val() +"&startDay=" + $("#s_start").val() + "&endDay=" + $("#s_end").val(),function (result) {
        zzMain("zzMain" ,"中证主指"  ,result);
    });

    $.APIPost("/api/stockIndex/getStockIndexDataList?indexCodes=1.000928,1.000929,1.000930,1.000931,1.000932,1.000933,1.000934,1.000935,1.000936,1.000937&dealPeriod="+ $("#dealPeriod").val() +"&startDay=" + $("#s_start").val() + "&endDay=" + $("#s_end").val(),function (result) {
        zzMain("zzSector" ,"中证800行指"  ,result);
    });

    $.APIPost("/api/stockIndex/getStockIndexDataList?indexCodes=1.000986,1.000987,1.000988,1.000989,1.000990,1.000991,1.000992,1.000993,1.000994,1.000995&dealPeriod="+ $("#dealPeriod").val() +"&startDay=" + $("#s_start").val() + "&endDay=" + $("#s_end").val(),function (result) {
        zzMain("zzSectorAll" ,"中证全行指"  ,result);
    });

    $.APIPost("/api/stockIndex/getStockIndexDataList?indexCodes=1.000002,1.000016,1.000010,1.000009,1.000132,1.000133&dealPeriod="+ $("#dealPeriod").val() +"&startDay=" + $("#s_start").val() + "&endDay=" + $("#s_end").val(),function (result) {
        commonLine("shMain" ,"上证主指"  ,result);
    });

    $.APIPost("/api/stockIndex/getStockIndexDataList?indexCodes=1.000032,1.000033,1.000034,1.000035,1.000036,1.000037,1.000038,1.000039,1.000040,1.000041&dealPeriod="+ $("#dealPeriod").val() +"&startDay=" + $("#s_start").val() + "&endDay=" + $("#s_end").val(),function (result) {
        commonLine("shSector" ,"上证行指"  ,result);
    });
}

function zzMain(boxId ,name ,result) {
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

function commonLine(boxId ,name ,result) {
    $('#'+ boxId).width($('#zzMain').width());
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



