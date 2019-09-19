$(function () {
    messagePriceData();
    messagePriceCount();
    $("#searchDataButton").click(function () {
        messagePriceData();
        messagePriceCount();
    });
});

function messagePriceData() {
    $.APIPost("/api/statistics/getMessagePriceStaticData?messageType=" + $("#messageType").val() + "&timeType=" + $("#timeType").val() + "&directType=" + $("#directType").val() ,function (result) {
        console.log(result);
        var myChart = echarts.init(document.getElementById('main'));
        option = {
            title : {
                text: '某地区蒸发量和降水量',
                subtext: '纯属虚构'
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['蒸发量']
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
                    type : 'category',
                    data : result.data.title
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'蒸发量',
                    type:'bar',
                    data:result.data.count ,
                    markPoint : {
                        data : [
                            {type : 'max', name: '最大值'},
                            {type : 'min', name: '最小值'}
                        ]
                    },
                    markLine : {
                        data : [
                            {type : 'average', name: '平均值'}
                        ]
                    }
                }

            ]
        };

        myChart.setOption(option);
    });
}


function messagePriceCount() {
    $.APIPost("/api/statistics/getMessageStaticCount?messageType=" + $("#messageType").val(),function (result) {
        console.log(result);
        var myChart = echarts.init(document.getElementById('top'));
        option = {
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data:['升','平','降']
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
                    data : result.data.title
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'升',
                    type:'bar',
                    stack: '率',
                    itemStyle: {
                        normal: {
                            color: 'red'
                        }
                    },
                    data:result.data.plusList
                },
                {
                    name:'平',
                    type:'bar',
                    stack: '率',
                    itemStyle: {
                        normal: {
                            color: 'grey'
                        }
                    },
                    data:result.data.equalList
                },
                {
                    name:'降',
                    type:'bar',
                    stack: '率',
                    itemStyle: {
                        normal: {
                            color: 'green'
                        }
                    },
                    data:result.data.minusList
                }
            ]
        };

        myChart.setOption(option);
    });
}