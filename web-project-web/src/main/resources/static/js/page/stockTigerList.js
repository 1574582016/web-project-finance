
$(function () {

    $('#startDay').datepicker({
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        clearBtn: true ,
        autoclose: true
    });

    $('#endDay').datepicker({
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        clearBtn: true ,
        autoclose: true
    });

    $('#tableList').bootstrapTable('destroy').bootstrapTable({
        url: '/api/stockChose/getStockTigerList',         //请求后台的URL（*）
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
                stockCode: $("#stockCode").val(),
                stockName: $("#stockName").val(),
                startDay: $("#startDay").val(),
                endDay: $("#endDay").val()
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
                field: 'publishTime',
                title: '日期',
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
                valign: 'middle'
            }, {
                field: 'stockPlate', // 返回json数据中的name
                title: '板块', // 表格表头显示文字
                align: 'center', // 左右居中
                valign: 'middle' // 上下居中
            }, {
                field: 'bigContrySector',
                title: '一级行业',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'middleContrySector',
                title: '二级行业',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'stockSector',
                title: '三级行业',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'upperRange',
                title: '振幅(%)',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'handRate',
                title: '换手(%)',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'purBuyMoney',
                title: '净入额(万)',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'buyMoney',
                title: '入额(万)',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'sellMoney',
                title: '出额(万)',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'focusReason',
                title: '上榜原因',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'unusualReason',
                title: '异动原因',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'chooseReason',
                title: '选择原因',
                align: 'center',
                valign: 'middle'
            }, {
                title: "操作",
                align: 'center',
                valign: 'middle',
                width: 60, // 定义列的宽度，单位为像素px
                formatter: function (value, row, index) {
                    return '<button class="btn btn-primary btn-xs" onclick="edit(\'' + row.id + '\',\'' + row.stockCode + '\',\'' + row.stockName + '\',\'' + row.focusReason + '\')"  data-toggle="modal" data-target="#myModal">修改</button>&nbsp;';
                }
            }
        ]
    });

    $("#searchDataButton").click(function () {
        $('#tableList').bootstrapTable('refresh');
    });

    $("#submitDataButton").click(function () {
        var id = $("#f_id").val();
        var unusual_reason = $("#unusual_reason").val();
        var choose_reason =  $("#choose_reason").val();
        $.APIPost("/api/stockChose/editStockTigerList?id=" + id + "&unusualReason=" + unusual_reason + "&chooseReason=" + choose_reason ,function (data) {
            if(data.success){
                hideModal("myModal");
                window.parent.showSuccessAlert(data.message,function () {
                    $('#tableList').bootstrapTable('refresh');
                });
            }else{
                window.parent.showFailedAlert(data.message);
            }
        })
    });


});

function edit(id ,stockCode , stockName, focusReason) {
    $('#myModal').on('show.bs.modal',function() {
        $("#f_id").val(id);
        $("#stock_code").val(stockCode);
        $("#stock_name").val(stockName);
        $("#focus_reason").val(focusReason);
    });
}



