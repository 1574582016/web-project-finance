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

    $("#orderType").val($("#b_orderType").val());
    $("#search_angle").val($("#b_searchAngle").val());

    var search_angle = $("#search_angle").val();
    if(search_angle == 1){
        angleRate();
    }
    if(search_angle == 2){
        angleUpper();
    }
    if(search_angle == 3){
        angleSock();
    }

    $("#searchDataButton").click(function () {
        var search_angle = $("#search_angle").val();
        if(search_angle == 1){
            angleRate();
        }
        if(search_angle == 2){
            angleUpper();
        }
        if(search_angle == 3){
            angleSock();
        }
    });

    $("#backforthButton").click(function () {
        var orderType = $("#b_orderType").val();
        var searchAngle = $("#b_searchAngle").val();
        var startDay = $("#b_startDay").val();
        var endDay = $("#b_endDay").val();
        window.location.href = "/statistics/sectorOrderStatisticsList?orderType=" + orderType + "&searchAngle=" + searchAngle + "&startDay=" + startDay + "&endDay=" + endDay;

    });
});

function angleRate() {
    $('#tableList').bootstrapTable('destroy').bootstrapTable({
        url: '/api/statistics/getStockOrderStaticList' ,
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
                orderType: $("#orderType").val(),
                startDay: $("#s_start").val(),
                endDay: $("#s_end").val(),
                sectorName: $("#sectorName").val()
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
                valign: 'middle'
            },{
                field: 'publishDate',
                title: '上市时间',
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
                    return '<button class="btn btn-primary btn-xs"  data-toggle="modal" data-target="#myModal" onclick="view(\'' + row.sectorName + '\')">查看</button>';
                }
            }
        ]
    });
}

function angleUpper() {
    $('#tableList').bootstrapTable('destroy').bootstrapTable({
        url: '/api/statistics/getStockOrderStaticList' ,
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
                orderType: $("#orderType").val(),
                startDay: $("#s_start").val(),
                endDay: $("#s_end").val(),
                sectorName: $("#sectorName").val()
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
                valign: 'middle'
            },{
                field: 'publishDate',
                title: '上市时间',
                align: 'center',
                valign: 'middle'
            },{
                field: 'oneUpperAverage',
                title: '1月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'towUpperAverage',
                title: '2月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'threeUpperAverage',
                title: '3月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'fourUpperAverage',
                title: '4月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'fiveUpperAverage',
                title: '5月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'sixUpperAverage',
                title: '6月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'sevenUpperAverage',
                title: '7月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'eightUpperAverage',
                title: '8月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'nineUpperAverage',
                title: '9月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'tenUpperAverage',
                title: '10月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'elvenUpperAverage',
                title: '11月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'twelveUpperAverage',
                title: '12月',
                align: 'center',
                valign: 'middle'
            }
        ]
    });
}

function angleSock() {
    $('#tableList').bootstrapTable('destroy').bootstrapTable({
        url: '/api/statistics/getStockOrderStaticList' ,
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
                orderType: $("#orderType").val(),
                startDay: $("#s_start").val(),
                endDay: $("#s_end").val(),
                sectorName: $("#sectorName").val()
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
                valign: 'middle'
            },{
                field: 'publishDate',
                title: '上市时间',
                align: 'center',
                valign: 'middle'
            },{
                field: 'oneShockAverage',
                title: '1月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'towShockAverage',
                title: '2月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'threeShockAverage',
                title: '3月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'fourShockAverage',
                title: '4月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'fiveShockAverage',
                title: '5月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'sixShockAverage',
                title: '6月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'sevenShockAverage',
                title: '7月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'eightShockAverage',
                title: '8月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'nineShockAverage',
                title: '9月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'tenShockAverage',
                title: '10月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'elvenShockAverage',
                title: '11月',
                align: 'center',
                valign: 'middle'
            },{
                field: 'twelveShockAverage',
                title: '12月',
                align: 'center',
                valign: 'middle'
            }
        ]
    });
}

function view(sectorName) {
    var orderType = $("#orderType").val();
    var searchAngle = $("#search_angle").val();
    var startDay = $("#s_start").val();
    var endDay = $("#s_end").val();
    window.location.href = "/statistics/stockOrderStatisticsList?sectorName=" + sectorName + "&orderType=" + orderType + "&searchAngle=" + searchAngle + "&startDay=" + startDay + "&endDay=" + endDay;
}