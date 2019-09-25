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

    falshTimeBar();
    falshTimeLine();
    $("#searchDataButton").click(function () {
        falshTimeBar();
        falshTimeLine();
    });
});


function falshTimeBar() {
    var myChart = echarts.init(document.getElementById('timeBar'));
    var colors = ['#34bd37', '#e80b3e', '#3a9ff5','#d3ff24'];
    $.APIPost("/api/statistics/getIndexTimeData?indexCode=" + $("#index_code").val()
        + "&dealPeriod=" +  $("#deal_period").val()
        + "&startDay=" + $("#s_start").val()
        + "&endDay=" + $("#s_end").val()
        ,function (result) {
            var option = {
                color: colors,

                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'cross'
                    }
                },
                grid: {
                    right: '20%'
                },
                toolbox: {
                    feature: {
                        dataView: {show: true, readOnly: false},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                legend: {
                    data:['平均成交量','成家量占比']
                },
                xAxis: [
                    {
                        type: 'category',
                        axisTick: {
                            alignWithLabel: true
                        },
                        data: result.data.title
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        name: '平均成交量',
                        min: 0,
                        max: result.data.maxValue,
                        position: 'left',
                        axisLine: {
                            lineStyle: {
                                color: colors[0]
                            }
                        },
                        axisLabel: {
                            formatter: '{value} 万'
                        }
                    },
                    {
                        type: 'value',
                        name: '成家量占比',
                        min: 0,
                        max: 100,
                        position: 'right',
                        axisLine: {
                            lineStyle: {
                                color: colors[2]
                            }
                        },
                        axisLabel: {
                            formatter: '{value} %'
                        }
                    }
                ],
                series: [
                    {
                        name:'平均成交量',
                        type:'bar',
                        data:result.data.average
                    },
                    {
                        name:'成家量占比',
                        type:'line',
                        yAxisIndex: 1,
                        data:result.data.line
                    }
                ]
            };
            myChart.setOption(option);
        });
}

function falshTimeLine() {
    var myChart = echarts.init(document.getElementById('timeLine'));
    var colors = ['#34bd37', '#e80b3e', '#3a9ff5','#d3ff24'];
    $.APIPost("/api/statistics/getIndexTimeValueData?indexCode=" + $("#index_code").val()
        + "&dealPeriod=" +  $("#deal_period").val()
        + "&startDay=" + $("#s_start").val()
        + "&endDay=" + $("#s_end").val()
        ,function (result) {
            var colors = ['#34bd37', '#e80b3e', '#3a9ff5','#d3ff24'];
            var option = {
                title: {
                    text: '折线图堆叠'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data:['涨幅均值','涨幅范围','振幅均值','振幅范围']
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
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
                        name:'涨幅均值',
                        type:'line',
                        stack: '总量',
                        itemStyle : {
                            normal : {
                                lineStyle:{
                                    color:colors[0]
                                }
                            }
                        },
                        data:result.data.changeAverage
                    },
                    {
                        name:'涨幅范围',
                        type:'line',
                        stack: '总量',
                        itemStyle : {
                            normal : {
                                lineStyle:{
                                    color:colors[1]
                                }
                            }
                        },
                        data:result.data.changeRange
                    },
                    {
                        name:'振幅均值',
                        type:'line',
                        stack: '总量',
                        itemStyle : {
                            normal : {
                                lineStyle:{
                                    color:colors[2]
                                }
                            }
                        },
                        data:result.data.shockAverage
                    },
                    {
                        name:'振幅范围',
                        type:'line',
                        stack: '总量',
                        itemStyle : {
                            normal : {
                                lineStyle:{
                                    color:colors[3]
                                }
                            }
                        },
                        data:result.data.shockRange
                    }
                ]
            };
            myChart.setOption(option);
        });
}