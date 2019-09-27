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

    flashCycleStatic();

    $("#searchDataButton").click(function () {
        flashCycleStatic();
    });
});



function flashCycleStatic() {
    var myChart = echarts.init(document.getElementById('main'));
    var colors = ['#34bd37', '#e80b3e', '#3a9ff5','#d3ff24'];
    $.APIPost("/api/statistics/getSectorMonthData?sectorCode=" + $("#sector_code").val()
        + "&dealPeriod=" + $("#deal_period").val()
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
                    data:['跌率','涨率','涨幅','振幅']
                },
                xAxis: [
                    {
                        type: 'category',
                        axisTick: {
                            alignWithLabel: true
                        },
                        data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        name: '跌率',
                        min: 0,
                        max: 100,
                        position: 'right',
                        axisLine: {
                            lineStyle: {
                                color: colors[0]
                            }
                        },
                        axisLabel: {
                            formatter: '{value} %'
                        }
                    },
                    {
                        type: 'value',
                        name: '涨率',
                        min: 0,
                        max: 100,
                        position: 'left',
                        axisLine: {
                            lineStyle: {
                                color: colors[1]
                            }
                        },
                        axisLabel: {
                            formatter: '{value} %'
                        }
                    },
                    {
                        type: 'value',
                        name: '涨幅',
                        min: 0,
                        max: result.data.maxChangeValue,
                        position: 'right',
                        axisLine: {
                            lineStyle: {
                                color: colors[2]
                            }
                        },
                        axisLabel: {
                            formatter: '{value} 点'
                        }
                    },
                    {
                        type: 'value',
                        name: '振幅',
                        min: 0,
                        max: result.data.maxShockValue,
                        position: 'right',
                        axisLine: {
                            lineStyle: {
                                color: colors[3]
                            }
                        },
                        axisLabel: {
                            formatter: '{value} 点'
                        }
                    }
                ],
                series: [
                    {
                        name:'跌率',
                        type:'bar',
                        data:result.data.downArr
                    },
                    {
                        name:'涨率',
                        type:'bar',
                        yAxisIndex: 1,
                        data:result.data.upperArr
                    },
                    {
                        name:'涨幅',
                        type:'line',
                        yAxisIndex: 2,
                        data:result.data.changeArr
                    },
                    {
                        name:'振幅',
                        type:'line',
                        yAxisIndex: 3,
                        data:result.data.shockArr
                    }
                ]
            };

            myChart.setOption(option);
        });

    myChart.on('click', function (params) {
        console.log(params.dataIndex);
        falshWeekBar(params.dataIndex);
    });
}

function falshWeekBar(dataIndex) {
    var myChart = echarts.init(document.getElementById('weekBar'));
    var colors = ['#34bd37', '#e80b3e', '#3a9ff5','#d3ff24'];
    $.APIPost("/api/statistics/getSectorWeekData?sectorCode=" + $("#sector_code").val()
        + "&dataIndex=" + dataIndex
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
                    data:['跌率','涨率','涨幅','振幅']
                },
                xAxis: [
                    {
                        type: 'category',
                        axisTick: {
                            alignWithLabel: true
                        },
                        data: result.data.titleArr
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        name: '跌率',
                        min: 0,
                        max: 100,
                        position: 'right',
                        axisLine: {
                            lineStyle: {
                                color: colors[0]
                            }
                        },
                        axisLabel: {
                            formatter: '{value} %'
                        }
                    },
                    {
                        type: 'value',
                        name: '涨率',
                        min: 0,
                        max: 100,
                        position: 'right',
                        offset: 80,
                        axisLine: {
                            lineStyle: {
                                color: colors[1]
                            }
                        },
                        axisLabel: {
                            formatter: '{value} %'
                        }
                    },
                    {
                        type: 'value',
                        name: '涨幅',
                        min: 0,
                        max: 100,
                        position: 'left',
                        axisLine: {
                            lineStyle: {
                                color: colors[2]
                            }
                        },
                        axisLabel: {
                            formatter: '{value} 点'
                        }
                    },
                    {
                        type: 'value',
                        name: '振幅',
                        min: 0,
                        max: 300,
                        position: 'left',
                        axisLine: {
                            lineStyle: {
                                color: colors[3]
                            }
                        },
                        axisLabel: {
                            formatter: '{value} 点'
                        }
                    }
                ],
                series: [
                    {
                        name:'跌率',
                        type:'bar',
                        data:result.data.downArr
                    },
                    {
                        name:'涨率',
                        type:'bar',
                        yAxisIndex: 1,
                        data:result.data.upperArr
                    },
                    {
                        name:'涨幅',
                        type:'line',
                        yAxisIndex: 2,
                        data:result.data.changeArr
                    },
                    {
                        name:'振幅',
                        type:'line',
                        yAxisIndex: 3,
                        data:result.data.shockArr
                    }
                ]
            };
            myChart.setOption(option);

            myChart.on('click', function (params) {
                console.log(params.dataIndex);
                falshDayBar(params.dataIndex);
            });
        });
}

function falshDayBar(dataIndex) {
    var myChart = echarts.init(document.getElementById('dayBar'));
    var colors = ['#34bd37', '#e80b3e', '#3a9ff5','#d3ff24'];
    $.APIPost("/api/statistics/getSectorDayData?sectorCode=" + $("#sector_code").val()
        + "&dataIndex=" + dataIndex
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
                    data:['跌率','涨率','涨幅','振幅']
                },
                xAxis: [
                    {
                        type: 'category',
                        axisTick: {
                            alignWithLabel: true
                        },
                        data: result.data.titleArr
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        name: '跌率',
                        min: 0,
                        max: 100,
                        position: 'right',
                        axisLine: {
                            lineStyle: {
                                color: colors[0]
                            }
                        },
                        axisLabel: {
                            formatter: '{value} %'
                        }
                    },
                    {
                        type: 'value',
                        name: '涨率',
                        min: 0,
                        max: 100,
                        position: 'right',
                        offset: 80,
                        axisLine: {
                            lineStyle: {
                                color: colors[1]
                            }
                        },
                        axisLabel: {
                            formatter: '{value} %'
                        }
                    },
                    {
                        type: 'value',
                        name: '涨幅',
                        min: 0,
                        max: 100,
                        position: 'left',
                        axisLine: {
                            lineStyle: {
                                color: colors[2]
                            }
                        },
                        axisLabel: {
                            formatter: '{value} 点'
                        }
                    },
                    {
                        type: 'value',
                        name: '振幅',
                        min: 0,
                        max: 300,
                        position: 'left',
                        axisLine: {
                            lineStyle: {
                                color: colors[3]
                            }
                        },
                        axisLabel: {
                            formatter: '{value} 点'
                        }
                    }
                ],
                series: [
                    {
                        name:'跌率',
                        type:'bar',
                        data:result.data.downArr
                    },
                    {
                        name:'涨率',
                        type:'bar',
                        yAxisIndex: 1,
                        data:result.data.upperArr
                    },
                    {
                        name:'涨幅',
                        type:'line',
                        yAxisIndex: 2,
                        data:result.data.changeArr
                    },
                    {
                        name:'振幅',
                        type:'line',
                        yAxisIndex: 3,
                        data:result.data.shockArr
                    }
                ]
            };
            myChart.setOption(option);
        });
}
