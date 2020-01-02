var drawWeight ;
$(function () {
    drawWeight = $('#main').width();
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

    $("#index_name").select2();

    indexMonthStatic();
    differentSectorStatic();

    // indexWeekStatic();
    // indexDayStatic();

    $("#searchDataButton").click(function () {
        indexMonthStatic();
        // indexWeekStatic();
        // indexDayStatic();
    });
});

function drawTechnologyMonthStatic() {
    commonSectorMonthStatic("technologyMain" ,"中证信息");
    commonSubSectorMonthStatic("technologyDiffMain" ,"信息技术");
}

function drawCommunicateMonthStatic() {
    commonSectorMonthStatic("communicateMain" ,"中证电信");
    commonSubSectorMonthStatic("communicateDiffMain" ,"电信业务");
}

function drawChoseCustMonthStatic() {
    commonSectorMonthStatic("choseCustMain" ,"中证可选");
    commonSubSectorMonthStatic("choseCustDiffMain" ,"可选消费");
}

function drawMainCustMonthStatic() {
    commonSectorMonthStatic("mainCustMain" ,"中证消费");
    commonSubSectorMonthStatic("mainCustDiffMain" ,"主要消费");
}

function drawEnergyMonthStatic() {
    commonSectorMonthStatic("energyMain" ,"中证能源");
    commonSubSectorMonthStatic("energyDiffMain" ,"能源");
}

function drawMaterialMonthStatic() {
    commonSectorMonthStatic("materialMain" ,"中证材料");
    commonSubSectorMonthStatic("materialDiffMain" ,"原材料");
}

function drawIndustryMonthStatic() {
    commonSectorMonthStatic("industryMain" ,"中证工业");
    commonSubSectorMonthStatic("industryDiffMain" ,"工业");
}

function drawFinancialMonthStatic() {
    commonSectorMonthStatic("financialMain" ,"中证金融");
    commonSubSectorMonthStatic("financialDiffMain" ,"金融地产");
}

function drawMedicineMonthStatic() {
    commonSectorMonthStatic("medicineMain" ,"中证医药");
    commonSubSectorMonthStatic("medicineDiffMain" ,"医药卫生");
}

function drawPublicServiceMonthStatic() {
    commonSectorMonthStatic("publicServiceMain" ,"中证公用");
    commonSubSectorMonthStatic("publicServiceDiffMain" ,"公用事业");
}



function indexMonthStatic() {
    var myChart = echarts.init(document.getElementById('main'));
    var colors = ['#34bd37', '#e80b3e', '#3a9ff5','#d3ff24'];
    $.APIPost("/api/stockIndex/getStockIndexMonthList?indexName=" + $("#index_name").val()
        + "&startDay=" + $("#s_start").val()
        + "&endDay=" + $("#s_end").val()
        ,function (result) {
            var option = {
                title : {
                    text: $("#index_name").val()
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['涨率','涨幅' ,'震幅']
                },
                toolbox: {
                    show : true,
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

            myChart.on('click', function (params) {
                sectorSectorList(params.dataIndex);
            });
        });
}

function indexWeekStatic() {
    var myChart = echarts.init(document.getElementById('weekBar'));
    var colors = ['#34bd37', '#e80b3e', '#3a9ff5','#d3ff24'];
    $.APIPost("/api/stockIndex/getStockIndexWeekList?indexName=" + $("#index_name").val()
        + "&startDay=" + $("#s_start").val()
        + "&endDay=" + $("#s_end").val()
        ,function (result) {
            var option = {
                title : {
                    text: $("#index_name").val()
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['涨率','涨幅' ,'震幅']
                },
                toolbox: {
                    show : true,
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

function indexDayStatic() {
    var myChart = echarts.init(document.getElementById('dayBar'));
    var colors = ['#34bd37', '#e80b3e', '#3a9ff5','#d3ff24'];
    $.APIPost("/api/stockIndex/getStockIndexDayList?indexName=" + $("#index_name").val()
        + "&startDay=" + $("#s_start").val()
        + "&endDay=" + $("#s_end").val()
        ,function (result) {
            var option = {
                title : {
                    text: $("#index_name").val()
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['涨率','涨幅' ,'震幅']
                },
                toolbox: {
                    show : true,
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


function differentSectorStatic() {
    var myChart = echarts.init(document.getElementById('differentSector'));
    var colors = ['#34bd37', '#e80b3e', '#3a9ff5','#d3ff24'];
    $.APIPost("/api/stockSector/getStockDifferentSectorMonthList?indexNames=中证能源,中证材料,中证工业,中证消费,中证可选,中证信息,中证电信,中证公用,中证金融,中证医药"
        + "&startDay=" + $("#s_start").val()
        + "&endDay=" + $("#s_end").val()
        ,function (result) {
            var option = {
                title : {
                    text: $("#index_name").val()
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:result.data.result.titalArr
                },
                toolbox: {
                    show : true,
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
                        data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : result.data.result.rateArr
            };

            myChart.setOption(option);
        });
}


function commonSectorMonthStatic(idBox ,forthSector) {
    $('#' + idBox).width(drawWeight);
    var myChart = echarts.init(document.getElementById(idBox));
    var colors = ['#34bd37', '#e80b3e', '#3a9ff5','#d3ff24'];
    $.APIPost("/api/stockIndex/getStockIndexMonthList?indexName=" + forthSector
        + "&startDay=" + $("#s_start").val()
        + "&endDay=" + $("#s_end").val()
        ,function (result) {
            var option = {
                title : {
                    text: forthSector
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['涨率','涨幅' ,'震幅']
                },
                toolbox: {
                    show : true,
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

function commonSubSectorMonthStatic(idBox ,forthSector) {
    $('#' + idBox).width(drawWeight);
    var myChart = echarts.init(document.getElementById(idBox));
    var colors = ['#34bd37', '#e80b3e', '#3a9ff5','#d3ff24'];
    $.APIPost("/api/stockSector/getStockSubSectorMonthList?indexName=" + forthSector
        + "&startDay=" + $("#s_start").val()
        + "&endDay=" + $("#s_end").val()
        ,function (result) {
            var option = {
                title : {
                    text: forthSector
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:result.data.result.titalArr
                },
                toolbox: {
                    show : true,
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
                        data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : result.data.result.rateArr
            };

            myChart.setOption(option);
        });
}



function sectorSectorList(month) {
    $('#tableList').bootstrapTable('destroy').bootstrapTable({
        url: '/api/stockIndex/getStockSectorMonthDataList' ,
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        toolbar: '#toolbar',                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: false,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "id desc",                   //排序方式
        queryParams: function (params) {
            var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                size: params.limit,   //页面大小
                page: params.offset/params.limit + 1,  //页码
                sectorMonth: parseInt(month) + 1,
                startDay: $("#s_start").val(),
                endDay: $("#s_end").val()
            };
            return temp;
        },
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: false,
        showColumns: true,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        // height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
        showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        scrollTo: true,
        columns: [
            {
                field: 'firstSector',
                title: '一级',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'secondSector',
                title: '二级',
                align: 'center',
                valign: 'middle'
            },{
                field: 'thirdSecotor',
                title: '三级',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'forthSector',
                title: '四级',
                align: 'center',
                valign: 'middle'
            },{
                field: 'oneRiseRate',
                title: '1月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'towRiseRate',
                title: '2月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'threeRiseRate',
                title: '3月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'fourRiseRate',
                title: '4月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'fiveRiseRate',
                title: '5月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'sixRiseRate',
                title: '6月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'sevenRiseRate',
                title: '7月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'eightRiseRate',
                title: '8月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'nineRiseRate',
                title: '9月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'tenRiseRate',
                title: '10月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'elevenRiseRate',
                title: '11月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'twelveRiseRate',
                title: '12月',
                align: 'center',
                valign: 'middle'
            }, {
                title: "操作",
                align: 'center',
                valign: 'middle',
                width: 20, // 定义列的宽度，单位为像素px
                formatter: function (value, row, index) {
                    return '<button class="btn btn-primary btn-xs"  data-toggle="modal" data-target="#myModal" onclick="viewSector(\'' + row.forthSector + '\')">查看</button>';
                }
            }
        ]
    });
}

function viewSector(forthSector) {
    sectorMonthStatic(forthSector);
    sectorWeekStatic(forthSector);
    $("#mainButton").removeClass("active");
    $("#home").removeClass("in active");
    $("#sectorButton").addClass("active");
    $("#sector").addClass("in active");

}

function sectorMonthStatic(forthSector) {
    $('#sectorMonth').width(drawWeight);
    var myChart = echarts.init(document.getElementById('sectorMonth'));
    var colors = ['#34bd37', '#e80b3e', '#3a9ff5','#d3ff24'];
    $.APIPost("/api/stockIndex/getStockSectorMonthList?sectorName=" + forthSector
        + "&startDay=" + $("#s_start").val()
        + "&endDay=" + $("#s_end").val()
        ,function (result) {
            var option = {
                title : {
                    text: forthSector
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['涨率','涨幅' ,'震幅']
                },
                toolbox: {
                    show : true,
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

            myChart.on('click', function (params) {
                sectorCompanyList(forthSector ,params.dataIndex);
            });
        });
}

function sectorWeekStatic(forthSector) {
    $('#sectorWeek').width(drawWeight);
    var myChart = echarts.init(document.getElementById('sectorWeek'));
    var colors = ['#34bd37', '#e80b3e', '#3a9ff5','#d3ff24'];
    $.APIPost("/api/stockIndex/getStockSectorWeekList?sectorName=" + forthSector
        + "&startDay=" + $("#s_start").val()
        + "&endDay=" + $("#s_end").val()
        + "&dealPeriod=2"
        ,function (result) {
            var option = {
                title : {
                    text: forthSector
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['涨率','涨幅' ,'震幅']
                },
                toolbox: {
                    show : true,
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

function sectorCompanyList(forthSector ,month) {
    $('#companyTableList').bootstrapTable('destroy').bootstrapTable({
        url: '/api/stockIndex/getStockCompanyMonthDataList' ,
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        toolbar: '#toolbar',                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: false,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "id desc",                   //排序方式
        queryParams: function (params) {
            var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                size: params.limit,   //页面大小
                page: params.offset/params.limit + 1,  //页码
                sectorName: forthSector,
                sectorMonth: parseInt(month) + 1,
                startDay: $("#s_start").val(),
                endDay: $("#s_end").val()
            };
            return temp;
        },
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: false,
        showColumns: true,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        // height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
        showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        scrollTo: true,
        columns: [
            {
                field: 'stockCode',
                title: '编码',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'stockName',
                title: '名称',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    return '<a class="text_link_a" href="#" target="view_window" data-toggle="tooltip" data-placement="top" title="'+ row.groupHot +'">'+ value +'</a>';
                }
            },{
                field: 'companyLevel',
                title: '市场',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    var lev = value.substr(0,1);
                    if(lev == 'S' || lev == 'A' || lev == 'B'){
                        return '<a class="text_link_a" href="#" style="color: red;" target="view_window" data-toggle="tooltip" data-placement="top" title="'+ row.groupIndex +'">'+ value +'</a>';
                    }else
                    if(lev == 'C'){
                        return '<a class="text_link_a" href="#" style="color: #b804ff;" target="view_window" data-toggle="tooltip" data-placement="top" title="'+ row.groupIndex +'">'+ value +'</a>';
                    }else
                    if(lev == 'D'){
                        return '<a class="text_link_a" href="#" style="color: #ffc000;" target="view_window" data-toggle="tooltip" data-placement="top" title="'+ row.groupIndex +'">'+ value +'</a>';
                    }else{
                        return '<a class="text_link_a" href="#" style="color: grey;" target="view_window" data-toggle="tooltip" data-placement="top" title="'+ row.groupIndex +'">'+ value +'</a>';
                    }
                }
            },{
                field: 'financialLevel',
                title: '资产',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    var lev = value.substr(1,1);
                    if(lev == 'S' || lev == 'A' ){
                        return '<a class="text_link_a" href="#" style="color: red;">'+ value +'</a>';
                    }else{
                        return '<a class="text_link_a" href="#" style="color: grey;">'+ value +'</a>';
                    }
                }
            },{
                field: 'companyName',
                title: '全称',
                align: 'center',
                valign: 'middle'
            },{
                field: 'companyRegion',
                title: '地域',
                align: 'center',
                valign: 'middle'
            },{
                field: 'establishDate',
                title: '成立日期',
                align: 'center',
                valign: 'middle'
            },{
                field: 'publishDate',
                title: '上市日期',
                align: 'center',
                valign: 'middle'
            },{
                field: 'oneRiseRate',
                title: '1月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'towRiseRate',
                title: '2月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'threeRiseRate',
                title: '3月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'fourRiseRate',
                title: '4月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'fiveRiseRate',
                title: '5月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'sixRiseRate',
                title: '6月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'sevenRiseRate',
                title: '7月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'eightRiseRate',
                title: '8月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'nineRiseRate',
                title: '9月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'tenRiseRate',
                title: '10月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'elevenRiseRate',
                title: '11月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'twelveRiseRate',
                title: '12月',
                align: 'center',
                valign: 'middle'
            }, {
                title: "操作",
                align: 'center',
                valign: 'middle',
                width: 20, // 定义列的宽度，单位为像素px
                formatter: function (value, row, index) {
                    return '<button class="btn btn-primary btn-xs"  data-toggle="modal" data-target="#myModal" onclick="viewCompany(\'' + row.stockCode + '\',\''+ forthSector + '\',\''+ row.stockName + '\')">查看</button>';
                }
            }
        ]
    });
}

function viewCompany(stockCode , forthSector ,stockName) {
    companyMonthStatic(stockCode , forthSector ,stockName);
    companyWeekStatic(stockCode , forthSector ,stockName);
    $("#sectorButton").removeClass("active");
    $("#sector").removeClass("in active");
    $("#companyButton").addClass("active");
    $("#company").addClass("in active");
}

function companyMonthStatic(stockCode , forthSector ,stockName) {
    $('#companyMonth').width(drawWeight);
    var myChart = echarts.init(document.getElementById('companyMonth'));
    var colors = ['#34bd37', '#e80b3e', '#3a9ff5','#d3ff24'];
    $.APIPost("/api/stockIndex/getStockCompanyMonthList?sectorName=" + forthSector
        + "&stockCode=" + stockCode
        + "&startDay=" + $("#s_start").val()
        + "&endDay=" + $("#s_end").val()
        ,function (result) {
            var option = {
                title : {
                    text: stockCode +"/" +stockName
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['涨率','涨幅' ,'震幅']
                },
                toolbox: {
                    show : true,
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

            myChart.on('click', function (params) {
                companyPointMonthDealDate(stockCode , params.dataIndex)
            });
        });
}

function companyWeekStatic(stockCode , forthSector ,stockName) {
    $('#companyWeek').width(drawWeight);
    var myChart = echarts.init(document.getElementById('companyWeek'));
    var colors = ['#34bd37', '#e80b3e', '#3a9ff5','#d3ff24'];
    $.APIPost("/api/stockIndex/getStockSectorWeekList?sectorName=" + forthSector
        + "&stockCode=" + stockCode
        + "&startDay=" + $("#s_start").val()
        + "&endDay=" + $("#s_end").val()
        + "&dealPeriod=1"
        ,function (result) {
            var option = {
                title : {
                    text: stockCode +"/" +stockName
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['涨率','涨幅' ,'震幅']
                },
                toolbox: {
                    show : true,
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


function companyPointMonthDealDate(stockCode , month) {
    $.APIPost("/api/stockIndex/getStockDealDataList?stockCode=" + stockCode
        + "&month=" + (parseInt(month) + 1)
        + "&startDay=" + $("#s_start").val()
        + "&endDay=" + $("#s_end").val()
        ,function (result) {
            $("#echartBox").html("");
            $.each(result.data.result ,function (index ,value) {
                var str = '<div id="companyDay'+ index +'" style="width:100%;height:400px;"></div>';
                $("#echartBox").append(str);
                var cId = "companyDay" + index;
                drawKLine(cId , value);
            });
        });
}


function drawKLine(boxId ,rawData) {
    var upColor = '#ec0000';
    var upBorderColor = '#8A0000';
    var downColor = '#00da3c';
    var downBorderColor = '#008F28';
    var myChart = echarts.init(document.getElementById(boxId));
// 数据意义：开盘(open)，收盘(close)，最低(lowest)，最高(highest)
    var data0 = splitData(rawData);


    function splitData(rawData) {
        var categoryData = [];
        var values = []
        for (var i = 0; i < rawData.length; i++) {
            categoryData.push(rawData[i].splice(0, 1)[0]);
            values.push(rawData[i])
        }
        return {
            categoryData: categoryData,
            values: values
        };
    }

    function calculateMA(dayCount) {
        var result = [];
        for (var i = 0, len = data0.values.length; i < len; i++) {
            if (i < dayCount) {
                result.push('-');
                continue;
            }
            var sum = 0;
            for (var j = 0; j < dayCount; j++) {
                sum += data0.values[i - j][1];
            }
            result.push(sum / dayCount);
        }
        return result;
    }



    var option = {
        title: {
            text: '',
            left: 0
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },
        legend: {
            data: ['日K', 'MA5', 'MA10', 'MA20', 'MA30']
        },
        grid: {
            left: '10%',
            right: '10%',
            bottom: '15%'
        },
        xAxis: {
            type: 'category',
            data: data0.categoryData,
            scale: true,
            boundaryGap : false,
            axisLine: {onZero: false},
            splitLine: {show: false},
            splitNumber: 20,
            min: 'dataMin',
            max: 'dataMax'
        },
        yAxis: {
            scale: true,
            splitArea: {
                show: true
            }
        },
        dataZoom: [{
            type: 'inside'
        }, {
            type: 'slider'
        }],
        series: [
            {
                name: '日K',
                type: 'candlestick',
                data: data0.values,
                itemStyle: {
                    normal: {
                        color: upColor,
                        color0: downColor,
                        borderColor: upBorderColor,
                        borderColor0: downBorderColor
                    }
                },
                markPoint: {
                    label: {
                        normal: {
                            formatter: function (param) {
                                return param != null ? Math.round(param.value) : '';
                            }
                        }
                    },
                    data: [
                        {
                            name: 'XX标点',
                            coord: ['2013/5/31', 2300],
                            value: 2300,
                            itemStyle: {
                                normal: {color: 'rgb(41,60,85)'}
                            }
                        },
                        {
                            name: 'highest value',
                            type: 'max',
                            valueDim: 'highest'
                        },
                        {
                            name: 'lowest value',
                            type: 'min',
                            valueDim: 'lowest'
                        },
                        {
                            name: 'average value on close',
                            type: 'average',
                            valueDim: 'close'
                        }
                    ],
                    tooltip: {
                        formatter: function (param) {
                            return param.name + '<br>' + (param.data.coord || '');
                        }
                    }
                },
                markLine: {
                    symbol: ['none', 'none'],
                    data: [
                        [
                            {
                                name: 'from lowest to highest',
                                type: 'min',
                                valueDim: 'lowest',
                                symbol: 'circle',
                                symbolSize: 10,
                                label: {
                                    normal: {show: false},
                                    emphasis: {show: false}
                                }
                            },
                            {
                                type: 'max',
                                valueDim: 'highest',
                                symbol: 'circle',
                                symbolSize: 10,
                                label: {
                                    normal: {show: false},
                                    emphasis: {show: false}
                                }
                            }
                        ],
                        {
                            name: 'min line on close',
                            type: 'min',
                            valueDim: 'close'
                        },
                        {
                            name: 'max line on close',
                            type: 'max',
                            valueDim: 'close'
                        }
                    ]
                }
            },
            {
                name: 'MA5',
                type: 'line',
                data: calculateMA(5),
                smooth: true,
                lineStyle: {
                    normal: {opacity: 0.5}
                }
            },
            {
                name: 'MA10',
                type: 'line',
                data: calculateMA(10),
                smooth: true,
                lineStyle: {
                    normal: {opacity: 0.5}
                }
            },
            {
                name: 'MA20',
                type: 'line',
                data: calculateMA(20),
                smooth: true,
                lineStyle: {
                    normal: {opacity: 0.5}
                }
            },
            {
                name: 'MA30',
                type: 'line',
                data: calculateMA(30),
                smooth: true,
                lineStyle: {
                    normal: {opacity: 0.5}
                }
            },

        ]
    };

    myChart.setOption(option);
}