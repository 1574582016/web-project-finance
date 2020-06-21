
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
        url: '/api/bankCard/getBankCardList',         //请求后台的URL（*）
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
                bankName: $("#s_bankName").val(),
                bankType: $("#s_bankType").val()
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
                field: 'bankName',
                title: '银行名称',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'bankType',
                title: '银行类型',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    if(value == 1){
                        return '商业银行';
                    }
                    return'多元金融';
                }
            }, {
                field: 'startDay',
                title: '开始日期',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'endDay', // 返回json数据中的name
                title: '结束日期', // 表格表头显示文字
                align: 'center', // 左右居中
                valign: 'middle' // 上下居中
            }, {
                field: 'creditMoney',
                title: '授信额度',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'yearMoney',
                title: '年费',
                align: 'center',
                valign: 'middle'
            }, {
                title: "操作",
                align: 'center',
                valign: 'middle',
                width: 160, // 定义列的宽度，单位为像素px
                formatter: function (value, row, index) {
                    return '<button class="btn btn-primary btn-xs" onclick="edit(\'' + row.id + '\')"  data-toggle="modal" data-target="#myModal">修改</button>&nbsp;';
                }
            }
        ]
    });

    $("#searchDataButton").click(function () {
        $('#tableList').bootstrapTable('refresh');
    });


    $("#addDataButton").click(function () {
        $('#myModal').on('show.bs.modal',function() {
            $("#id").val("");
            $("#bankName").val("");
            $("#bankType").val(1);
            $("#startDay").val("");
            $("#endDay").val("");
            $("#creditMoney").val("");
            $("#yearMoney").val("");
        });
    });

    $("#submitDataButton").click(function () {
        var id = $("#id").val();
        var bankName = $("#bankName").val();
        var bankType =  $("#bankType").val();
        var startDay =  $("#startDay").val();
        var endDay = $("#endDay").val();
        var creditMoney = $("#creditMoney").val();
        var yearMoney = $("#yearMoney").val();
        $.APIPost("/api/bankCard/editBankCard",JSON.stringify({id : id ,bankName :bankName , bankType: bankType, startDay: startDay ,endDay :endDay , creditMoney: creditMoney , yearMoney: yearMoney}),function (data) {
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

function edit(id) {
    $('#myModal').on('show.bs.modal',function() {
        $.APIPost("/api/bankCard/getBankCard?id="+id ,function (data) {
            $("#id").val(data.data.result.id);
            $("#bankName").val(data.data.result.bankName);
            $("#bankType").val(data.data.result.bankType);
            $("#startDay").val(data.data.result.startDay);
            $("#endDay").val(data.data.result.endDay);
            $("#creditMoney").val(data.data.result.creditMoney);
            $("#yearMoney").val(data.data.result.yearMoney);
        });
    });
}


