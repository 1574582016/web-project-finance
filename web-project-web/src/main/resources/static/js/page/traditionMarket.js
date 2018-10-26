
$(function () {

    $.APIPost("/api/param/getParamListByIndetity?indetity=bankType" ,function (data) {
        var str = ""
        $.each(data.data.result ,function (index ,value) {
            str +='<option value="'+ value.paramIdentity +'">' + value.paramName + '</option>'
        });
        var str2 = '<option value="">请选择</option>' + str;
        $("#s_marketType").html(str2);
        $("#f_marketType").html(str);

    });

    $('#tableList').bootstrapTable('destroy').bootstrapTable({
        url: '/api/tradition/getTraditionMarketList',         //请求后台的URL（*）
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
                marketName: $("#s_marketName").val(),
                marketType: $("#s_marketType").val()
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
                field: 'marketName', // 返回json数据中的name
                title: '名称', // 表格表头显示文字
                align: 'center', // 左右居中
                valign: 'middle' // 上下居中
            }, {
                field: 'marketType',
                title: '类型',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'describe',
                title: '简介',
                align: 'left',
                valign: 'middle',
                formatter: function (value, row, index) {
                    if(value == null || value == ""){
                        return "......"
                    }else{
                        return value.substr(1 , 20) + "......";
                    }
                }
            }, {
                title: "操作",
                align: 'center',
                valign: 'middle',
                width: 160, // 定义列的宽度，单位为像素px
                formatter: function (value, row, index) {
                    return '<button class="btn btn-primary btn-xs" onclick="edit(\'' + row.id + '\')"  data-toggle="modal" data-target="#myModal">修改</button>';
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
            $("#f_marketName").val("");
            $("#f_describe").val("");
        });
    });

    $("#submitDataButton").click(function () {
        var id = $("#id").val();
        var marketName = $("#f_marketName").val();
        var marketType = $("#f_marketType").val();
        var describe = $("#f_describe").val();
        $.APIPost("/api/tradition/editTraditionMarket",JSON.stringify({id : id ,marketName :marketName , marketType: marketType, describe: describe }),function (data) {
            if(data.success){
                hideModal("myModal");
                showSuccessAlert(data.message,function () {
                    $('#tableList').bootstrapTable('refresh');
                });
            }else{
                showFailedAlert(data.message);
            }
        })

    });

});

function edit(id) {
    $('#myModal').on('show.bs.modal',function() {
        $.APIPost("/api/tradition/getTraditionMarketInfo?id="+id ,function (data) {
            $("#id").val(data.data.result.id);
            $("#f_marketName").val(data.data.result.marketName);
            $("#f_marketType").val(data.data.result.marketType);
            $("#f_describe").val(data.data.result.describe);
        });
    });
}
