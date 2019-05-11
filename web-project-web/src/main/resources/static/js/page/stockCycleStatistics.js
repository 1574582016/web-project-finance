$(function () {
    $.APIPost("/api/statistics/getStockStatisticsData" ,function (data) {
        console.log(data);
        var myChart = echarts.init(document.getElementById('main'));
        option = {
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data:[ '跌', '涨']
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'value'
                }
            ],
            yAxis : [
                {
                    type : 'category',
                    axisTick : {show: false},
                    data : ['十二月','十一月','十月','九月','八月','七月','六月','五月','四月','三月','二月','一月']
                }
            ],
            series : [
                {
                    name:'涨',
                    type:'bar',
                    stack: '总量',
                    barWidth : 15,
                    itemStyle: {normal: {
                        label : {show: true}
                    }},
                    data:data.rise
                },
                {
                    name:'跌',
                    type:'bar',
                    stack: '总量',
                    itemStyle: {normal: {
                        label : {show: true, position: 'left'}
                    }},
                    data:data.down
                }
            ]
        };
        myChart.setOption(option);
    });
})