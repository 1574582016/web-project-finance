
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

    $('#tableList').bootstrapTable('destroy').bootstrapTable({
        url: '/api/stockChose/getStockChoseStrategyList',         //请求后台的URL（*）
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
                stockCode: $("#s_stockCode").val(),
                stockName: $("#s_stockName").val(),
                stockSector: $("#s_stockSector").val(),
                stockExchange: $("#s_stockExchange").val(),
                startDay: $("#s_start").val(),
                endDay: $("#s_end").val(),
            };
            return temp;
        },
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,
        showColumns: true,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        // height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
        showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        columns: [
            {
                field: 'stockCode',
                title: '股票编码',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'stockName',
                title: '股票名称',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'dealTime',
                title: '交易日',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'forthSector',
                title: '行业',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'companyLevel',
                title: '市场',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'financialLevel',
                title: '财务',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'openPrice',
                title: '开盘价',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'closePrice',
                title: '收盘价',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'highPrice',
                title: '最高价',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'lowPrice',
                title: '最低价',
                align: 'center',
                valign: 'middle'
            },{
                field: 'averagePrice',
                title: '均价',
                align: 'center',
                valign: 'middle'
            },{
                field: 'standarPrice',
                title: '标准差',
                align: 'center',
                valign: 'middle'
            },{
                field: 'topPrice',
                title: '上轨价',
                align: 'center',
                valign: 'middle'
            },{
                field: 'bottomPrice',
                title: '下轨价',
                align: 'center',
                valign: 'middle'
            },{
                field: 'topDistance',
                title: '顶部距离',
                align: 'center',
                valign: 'middle'
            },{
                field: 'middleDistance',
                title: '中规距离',
                align: 'center',
                valign: 'middle'
            },{
                field: 'bottomDistance',
                title: '底部距离',
                align: 'center',
                valign: 'middle'
            },{
                field: 'averageStock',
                title: '平均振幅',
                align: 'center',
                valign: 'middle'
            }
        ]
    });

    $("#searchDataButton").click(function () {
        $('#tableList').bootstrapTable('refresh');
    });

});

