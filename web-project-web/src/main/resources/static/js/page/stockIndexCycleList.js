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

    $("#index_name").select2();

    indexMonthStatic();
    indexWeekStatic();
    // indexDayStatic();

    $("#searchDataButton").click(function () {
        indexMonthStatic();
        indexWeekStatic();
        // indexDayStatic();
    });
});



function indexMonthStatic() {
    var myChart = echarts.init(document.getElementById('main'));
    var colors = ['#34bd37', '#e80b3e', '#3a9ff5','#d3ff24'];
    $.APIPost("/api/stockIndex/getStockIndexMonthList?indexName=" + $("#index_name").val()
        + "&startDay=" + $("#s_start").val()
        + "&endDay=" + $("#s_end").val()
        ,function (result) {
            console.log(result)
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
                console.log(params.dataIndex);
                sectorCompanyList(params.dataIndex);
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


function sectorCompanyList(month) {
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

function viewSector() {

}

function sectorMonthStatic() {
    var myChart = echarts.init(document.getElementById('main'));
    var colors = ['#34bd37', '#e80b3e', '#3a9ff5','#d3ff24'];
    $.APIPost("/api/stockIndex/getStockIndexMonthList?indexName=" + $("#index_name").val()
        + "&startDay=" + $("#s_start").val()
        + "&endDay=" + $("#s_end").val()
        ,function (result) {
            console.log(result)
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
                console.log(params.dataIndex);
                sectorCompanyList(params.dataIndex);
            });
        });
}