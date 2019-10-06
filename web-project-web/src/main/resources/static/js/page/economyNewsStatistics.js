
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
        url: '/api/economy/getEconomyNewsStatisticsList',         //请求后台的URL（*）
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
                newsTitle: $("#s_name").val(),
                newsType: $("#s_type").val(),
                startDate: $("#s_start").val(),
                endDate: $("#s_end").val(),
                newsTopic: $("#s_topic").val(),
                newsHot: $("#s_hot").val(),
                newsLevel:$("#s_level").val()
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
                field: 'newsTime', // 返回json数据中的name
                title: '日期', // 表格表头显示文字
                align: 'center', // 左右居中
                valign: 'middle',
                width: 180
             }, {
                field: 'newsType',
                title: '类型',
                align: 'center',
                valign: 'middle',
                width: 100
            }, {
                field: 'newsTitle',
                title: '标题',
                align: 'center',
                valign: 'middle',
                width: 400,
                formatter: function (value, row, index) {
                    return '<a class="text_link_a" href="'+ row.newsUrl +'" target="view_window" data-toggle="tooltip" data-placement="top" title="'+ row.newsContent +'">'+ value +'</a>';
                }
            }, {
                field: 'newsLevel',
                title: '级别',
                align: 'center',
                valign: 'middle',
                width: 100,
                formatter: function (value, row, index) {
                    var str = '';
                    for(var i = 0 ; i < value ; i++){
                        str += '<span class="glyphicon glyphicon-star"></span>';
                    }
                    return str ;
                }
            }, {
                field: 'newsTopic',
                title: '主题',
                align: 'center',
                valign: 'middle',
                width: 100
            }, {
                field: 'newsHot',
                title: '热点',
                align: 'center',
                valign: 'middle',
                width: 100
            }, {
                field: 'keyWord',
                title: '关键词',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'newsInvolve',
                title: '牵扯内容',
                align: 'center',
                valign: 'middle',
                width: 400
            }, {
                field: 'linkStock',
                title: '涉及股票',
                align: 'center',
                valign: 'middle',
                width: 400
            }, {
                title: "操作",
                align: 'center',
                valign: 'middle',
                width: 20, // 定义列的宽度，单位为像素px
                formatter: function (value, row, index) {
                    return '<button class="btn btn-primary btn-xs"  data-toggle="modal" data-target="#myModal" onclick="edit(\'' + row.id + '\')">修改</button>';
                }
            }
        ]
    });

    $("[data-toggle='tooltip']").tooltip();

    $("#searchDataButton").click(function () {
        $('#tableList').bootstrapTable('refresh');
    });

    $("#submitDataButton").click(function () {
        var id = $("#id").val();
        var newsLevel = $("#newsLevel").val();
        var newsTopic = $("#newsTopic").val();
        var newsHot = $("#newsHot").val();
        var keyWord = $("#keyWord").val();
        var newsInvolve = $("#newsInvolve").val();
        var linkStock = $("#linkStock").val();
        $.APIPost("/api/economy/editEconomyNewsStatistics",JSON.stringify({id : id ,newsLevel :newsLevel , newsTopic: newsTopic, newsHot: newsHot ,keyWord :keyWord ,newsInvolve:newsInvolve ,linkStock:linkStock }),function (data) {
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
        $.APIPost("/api/economy/getEconomyNewsStatisticsById?id="+id ,function (data) {
            $("#id").val(data.data.result.id);
            $("#newsTitle").val(data.data.result.newsTitle);
            $("#newsLevel").val(data.data.result.newsLevel);
            $("#newsTopic").val(data.data.result.newsTopic);
            $("#newsHot").val(data.data.result.newsHot);
            $("#keyWord").val(data.data.result.keyWord);
            $("#newsInvolve").val(data.data.result.newsInvolve);
            $("#linkStock").val(data.data.result.linkStock);
            $("#newsContent").html(data.data.result.newsContent);
        });
    });
}


