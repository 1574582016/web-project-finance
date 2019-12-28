$(function () {
    $.APIPost("/api/stock/getCompanyProfitGrowList?stockCode=" + $("#stock_code").val() ,function (result) {
        mainProfit(result);
        profitGrowMain("profitGrowMain" ,"总利润增长"  ,result);
        profitSeasonMain("profitSeasonMain" ,"季度利润增长" ,result);
        profitGrowRate("profitGrowRate" ,"利润增长率" ,result);
    });

    $.APIPost("/api/stock/getCompanyAssetGrowList?stockCode=" + $("#stock_code").val() ,function (result) {
        mainAsset(result);
        assetGrowMain("assetGrowMain" ,"资产增长"  ,result);
        assetDebtRate("assetDebtRate" ,"资产负债比例"  ,result);
        flowAssetRate("flowAssetRate" ,"流动&固定比例"  ,result);
        flowAssetComponent("flowAssetComponent" ,"流动资产成分比例"  ,result);
        unflowAssetComponent("unflowAssetComponent" ,"非流动资产成分比例"  ,result);
        flowDetbComponent("flowDetbComponent" ,"流动负债成分比例"  ,result);
        unflowDetbComponent("unflowDetbComponent" ,"非流动负债成分比例"  ,result);
    });

    companyMonthStatic();

    companyWeekStatic();

    companyDayStatic();

    $("#backButton").click(function () {
        var firstSector = $("#firstSector").val();
        var secondSector = $("#secondSector").val();
        var thirdSecotor = $("#thirdSecotor").val();
        var forthSector = $("#forthSector").val();
        var stockCode = $("#stockCode").val();
        var stockName = $("#stockName").val();
        var firstHot = $("#firstHot").val();
        var secondHot = $("#secondHot").val();
        var thirdHot = $("#thirdHot").val();
        var forthHot = $("#forthHot").val();
        location.href="/stock/stockSectorCompanyList?stockCode=" + stockCode
            + "&stockName=" + stockName
            + "&firstSector=" + firstSector
            + "&secondSector=" + secondSector
            + "&thirdSecotor=" + thirdSecotor
            + "&forthSector=" + forthSector
            + "&firstHot=" + firstHot
            + "&secondHot=" + secondHot
            + "&thirdHot=" + thirdHot
            + "&forthHot=" + forthHot ;
    });

});
function mainAsset(result) {
    $("#total_asset_score").html(result.data.assetLevel.assetDebtRate);
    $("#total_asset_levle").html(result.data.assetLevel.assetDebtRateLevel);

    $("#asset_grow_score").html(result.data.assetLevel.averageGrowLevel);
    $("#asset_grow_levle").html(result.data.assetLevel.growLevel);

    $("#asset_debt_score").html(result.data.assetLevel.averageAssetDebtLevel);
    $("#asset_debt_levle").html(result.data.assetLevel.assetDebtLevel);

    $("#asset_component_score").html(result.data.assetLevel.averageAssetLevel);
    $("#asset_component_levle").html(result.data.assetLevel.assetLevel);

    var myChart = echarts.init(document.getElementById("mainAsset"));
    var colors = ['#3a9ff5', '#e80b3e', '#34bd37', '#d3ff24'];
    var option = {
        color: colors,
        title: {
            text: "利润分析"
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['总资产','净资产','固定资产比例','流动资产比例']
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
                name:'净资产',
                type:'line',
                stack: '总量1',
                data:result.data.pureAsset
            },{
                name:'固定资产比例',
                type:'line',
                stack: '总量2',
                data:result.data.totalUnflowAssetRate
            },{
                name:'流动资产比例',
                type:'line',
                stack: '总量3',
                data:result.data.totalFlowAssetRate
            }
        ]
    };
    myChart.setOption(option);
}

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
            data:['总利润','归属利润']
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
                data:result.data.belongProfit
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
            data:['总利润','归属利润']
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
                data:result.data.belongProfit
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
            data:['总利润增长率','归属利润增长率']
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
                data:result.data.totalProfitRate
            },{
                name:'归属利润增长率',
                type:'line',
                stack: '总量1',
                data:result.data.belongProfitRate
            }
        ]
    };
    myChart.setOption(option);
}

function companyMonthStatic() {
    $('#companyMonth').width($('#mainProfit').width());
    var myChart = echarts.init(document.getElementById('companyMonth'));
    var colors = ['#34bd37', '#e80b3e', '#3a9ff5','#d3ff24'];
    $.APIPost("/api/handleCycle/getPointStockMonthList?stockCode=" + $("#stock_code").val()
        + "&staticDate=" + $("#static_date").val()
        ,function (result) {
            var option = {
                title : {
                    text: $("#stock_code").val()
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['涨率','涨幅' ,'震幅']
                },
                toolbox: {
                    show : false,
                    feature : {
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
                        data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'涨率',
                        type:'bar',
                        data:result.data.result.rateArr,
                        itemStyle : {
                            normal : {
                                label: {
                                    show: true,
                                    position: 'top',
                                    textStyle: {
                                        color: 'black'
                                    }
                                }
                            },
                        }
                    },
                    {
                        name:'涨幅',
                        type:'bar',
                        data:result.data.result.upperArr,
                        itemStyle : {
                            normal : {
                                label: {
                                    show: true,
                                    position: 'top',
                                    textStyle: {
                                        color: 'black'
                                    }
                                }
                            },
                        }
                    },
                    {
                        name:'震幅',
                        type:'bar',
                        data:result.data.result.shockArr,
                        itemStyle : {
                            normal : {
                                label: {
                                    show: true,
                                    position: 'top',
                                    textStyle: {
                                        color: 'black'
                                    }
                                }
                            },
                        }
                    }
                ]
            };

            myChart.setOption(option);
        });
}

function companyWeekStatic() {
    $('#companyWeek').width($('#mainProfit').width());
    var myChart = echarts.init(document.getElementById('companyWeek'));
    var colors = ['#34bd37', '#e80b3e', '#3a9ff5','#d3ff24'];
    $.APIPost("/api/handleCycle/getPointStockWeekList?stockCode=" + $("#stock_code").val()
        + "&staticDate=" + $("#static_date").val()
        ,function (result) {
            var option = {
                title : {
                    text: $("#stock_code").val()
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['涨率','涨幅' ,'震幅']
                },
                toolbox: {
                    show : false,
                    feature : {
                        dataView : {show: true, readOnly: false},
                        magicType : {show: true, type: ['line', 'bar']},
                        restore : {show: true},
                        saveAsImage : {show: true},
                        dataZoom: {}
                    }
                },
                dataZoom: [{
                    type: 'inside'
                }, {
                    type: 'slider'
                }],
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        data : result.data.result.titleArr
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'涨率',
                        type:'bar',
                        data:result.data.result.rateArr,
                        itemStyle : {
                            normal : {
                                label: {
                                    show: true,
                                    position: 'top',
                                    textStyle: {
                                        color: 'black'
                                    }
                                }
                            },
                        }
                    },
                    {
                        name:'涨幅',
                        type:'bar',
                        data:result.data.result.upperArr,
                        itemStyle : {
                            normal : {
                                label: {
                                    show: true,
                                    position: 'top',
                                    textStyle: {
                                        color: 'black'
                                    }
                                }
                            },
                        }
                    },
                    {
                        name:'震幅',
                        type:'bar',
                        data:result.data.result.shockArr,
                        itemStyle : {
                            normal : {
                                label: {
                                    show: true,
                                    position: 'top',
                                    textStyle: {
                                        color: 'black'
                                    }
                                }
                            },
                        }
                    }
                ]
            };

            myChart.setOption(option);
        });
}

function companyDayStatic() {
    $('#companyDay').width($('#mainProfit').width());
    var myChart = echarts.init(document.getElementById('companyDay'));
    var colors = ['#34bd37', '#e80b3e', '#3a9ff5','#d3ff24'];
    $.APIPost("/api/handleCycle/getPointStockDayList?stockCode=" + $("#stock_code").val()
        + "&staticDate=" + $("#static_date").val()
        ,function (result) {
            var option = {
                title : {
                    text: $("#stock_code").val()
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['涨率','涨幅' ,'震幅']
                },
                toolbox: {
                    show : false,
                    feature : {
                        dataView : {show: true, readOnly: false},
                        magicType : {show: true, type: ['line', 'bar']},
                        restore : {show: true},
                        saveAsImage : {show: true},
                        dataZoom: {}
                    }
                },
                dataZoom: [{
                    type: 'inside'
                }, {
                    type: 'slider'
                }],
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        data : result.data.result.titleArr
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'涨率',
                        type:'bar',
                        data:result.data.result.rateArr,
                        itemStyle : {
                            normal : {
                                label: {
                                    show: true,
                                    position: 'top',
                                    textStyle: {
                                        color: 'black'
                                    }
                                }
                            },
                        }
                    },
                    {
                        name:'涨幅',
                        type:'bar',
                        data:result.data.result.upperArr,
                        itemStyle : {
                            normal : {
                                label: {
                                    show: true,
                                    position: 'top',
                                    textStyle: {
                                        color: 'black'
                                    }
                                }
                            },
                        }
                    },
                    {
                        name:'震幅',
                        type:'bar',
                        data:result.data.result.shockArr,
                        itemStyle : {
                            normal : {
                                label: {
                                    show: true,
                                    position: 'top',
                                    textStyle: {
                                        color: 'black'
                                    }
                                }
                            },
                        }
                    }
                ]
            };

            myChart.setOption(option);
        });
}

