var drawWeight ;
$(function () {
    drawWeight = $('#firstSector').width();

    sectorCycleStatic(1 ,'一级行业');
    stockMonthList('orderMonthRateTable');
    stockWeekList("orderOneWeekRateTable" ,1);
    stockWeekList("orderTowWeekRateTable" ,2);
    stockWeekList("orderThreeWeekRateTable" ,3);
    stockWeekList("orderFourWeekRateTable" ,4);
    stockWeekList("orderFiveWeekRateTable" ,5);

    $("#searchDataButton").click(function () {
        sectorCycleStatic(1 ,'一级行业');
        stockMonthList('orderMonthRateTable');
        stockWeekList("orderOneWeekRateTable" ,1);
        stockWeekList("orderTowWeekRateTable" ,2);
        stockWeekList("orderThreeWeekRateTable" ,3);
        stockWeekList("orderFourWeekRateTable" ,4);
        stockWeekList("orderFiveWeekRateTable" ,5);
    });
});

function sectorCycleStatic(sectorType , sectorName) {
    var barBox = "firstSector" ;
    var firstSector = "";
    var secondSector = "";
    var thirdSecotor = "";
    if(sectorType === 2){
        barBox = "secondSector" ;
        firstSector = sectorName ;
    }
    if(sectorType === 3){
        barBox = "thirdSector" ;
        secondSector = sectorName ;
    }
    if(sectorType === 4){
        barBox = "forthSector" ;
        thirdSecotor = sectorName ;
    }

    var myChart = echarts.init(document.getElementById(barBox));
    var colors = ['#34bd37', '#e80b3e', '#3a9ff5','#d3ff24'];
    $.APIPost("/api/handleCycle/getStaticSectorNum?staticDate=" + $("#static_date").val()
        + "&staticMonth=" + $("#static_month").val()
        + "&staticRate=" + $("#static_rate").val()
        + "&staticAmplitude=" + $("#static_amplitude").val()
        + "&sectorType=" +sectorType
        + "&firstSector=" +firstSector
        + "&secondSector=" +secondSector
        + "&thirdSecotor=" +thirdSecotor
        ,function (result) {
            var option = {
                title : {
                    text: sectorName,
                    x:'center'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                series : [
                    {
                        name: '个数',
                        type: 'pie',
                        radius : '75%',
                        center: ['50%', '60%'],
                        data:result.data.result.sectorNum ,
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };

            myChart.setOption(option);

            myChart.on('click', function (params) {
                // sectorSectorList(params.dataIndex);
                if(sectorType < 4){
                    sectorCycleStatic( sectorType + 1 , params.data.name);
                }
                stockMonthList('pointSectorTable' ,sectorType , params.data.name)
            });
        });
}

function stockMonthList(idBox ,sectorType , sectorName) {
    var firstSector = "";
    var secondSector = "";
    var thirdSecotor = "";
    var forthSector = "" ;
    if(sectorType === 1){
        firstSector = sectorName ;
    }
    if(sectorType === 2){
        secondSector = sectorName ;
    }
    if(sectorType === 3){
        thirdSecotor = sectorName ;
    }
    if(sectorType === 4){
        forthSector = sectorName ;
    }
    $('#' + idBox).bootstrapTable('destroy').bootstrapTable({
        url: '/api/handleCycle/getPointMonthStockList' ,
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        toolbar: '#toolbar',                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "id desc",                   //排序方式
        queryParams: function (params) {
            var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                size: params.limit,   //页面大小
                page: params.offset/params.limit + 1,  //页码
                staticDate: $("#static_date").val() ,
                staticMonth: $("#static_month").val() ,
                staticRate: $("#static_rate").val() ,
                staticAmplitude: $("#static_amplitude").val() ,
                firstSector : firstSector ,
                secondSector : secondSector ,
                thirdSecotor : thirdSecotor ,
                forthSector : forthSector
            };
            return temp;
        },
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,
        showColumns: false,                  //是否显示所有的列
        showRefresh: false,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        // height: 536,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
        showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        columns: [
            {
                field: 'forthSector',
                title: '行业',
                align: 'center',
                valign: 'middle'
            }, {
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
                field: 'publishTime',
                title: '上市日期',
                align: 'center',
                valign: 'middle'
            },{
                field: 'weekLevel',
                title: '周级',
                align: 'center',
                valign: 'middle'
            },{
                field: 'commonRise',
                title: '涨率',
                align: 'center',
                valign: 'middle'
            },{
                field: 'commonAmplitude',
                title: '涨幅',
                align: 'center',
                valign: 'middle'
            },{
                field: 'commonShock',
                title: '振幅',
                align: 'center',
                valign: 'middle'
            },{
                title: "操作",
                align: 'center',
                valign: 'middle',
                width: 20, // 定义列的宽度，单位为像素px
                formatter: function (value, row, index) {
                    var btn = "";
                    btn += '<button class="btn btn-primary btn-xs"  data-toggle="modal" data-target="#myModal" onclick="view(\'' + row.stockCode + '\')">查看</button>';
                    return btn
                }
            }
        ]
    });
}



function stockWeekList(idBox ,staticWeek) {
    $('#' + idBox).bootstrapTable('destroy').bootstrapTable({
        url: '/api/handleCycle/getPointWeekStockList' ,
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        toolbar: '#toolbar',                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "id desc",                   //排序方式
        queryParams: function (params) {
            var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                size: params.limit,   //页面大小
                page: params.offset/params.limit + 1,  //页码
                staticDate: $("#static_date").val() ,
                staticMonth: $("#static_month").val() ,
                staticWeek: staticWeek ,
                staticRate: $("#static_rate").val() ,
                staticAmplitude: $("#static_amplitude").val()
            };
            return temp;
        },
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,
        showColumns: false,                  //是否显示所有的列
        showRefresh: false,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        // height: 536,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
        showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        columns: [
            {
                field: 'forthSector',
                title: '行业',
                align: 'center',
                valign: 'middle'
            }, {
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
                field: 'publishTime',
                title: '上市日期',
                align: 'center',
                valign: 'middle'
            },{
                field: 'pointWeekRise',
                title: '月涨率',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    if(row.isPool == 1){
                        return '<span class="label label-success">'+ value +'</span>';
                    }else{
                        return value;
                    }
                }
            },{
                field: 'weekLevel',
                title: '周级',
                align: 'center',
                valign: 'middle'
            },{
                field: 'dayLevel',
                title: '天级',
                align: 'center',
                valign: 'middle'
            },{
                field: 'commonRise',
                title: '涨率',
                align: 'center',
                valign: 'middle'
            },{
                field: 'commonAmplitude',
                title: '涨幅',
                align: 'center',
                valign: 'middle'
            },{
                field: 'commonShock',
                title: '振幅',
                align: 'center',
                valign: 'middle'
            },{
                title: "操作",
                align: 'center',
                valign: 'middle',
                width: 20, // 定义列的宽度，单位为像素px
                formatter: function (value, row, index) {
                    var btn = "";
                    btn += '<button class="btn btn-primary btn-xs"  data-toggle="modal" data-target="#myModal" onclick="view(\'' + row.stockCode + '\')">查看</button>';
                    return btn
                }
            }
        ]
    });
}