$(function(){

    $.APIPost("/api/notice/getStockNoticeClassTree",function (data) {
        $('#tree').treeview({
            data: data.data.result,         // 数据源
            showCheckbox: false,   //是否显示复选框
            highlightSelected: false,    //是否高亮选中
            multiSelect: false,    //多选
            emptyIcon: "",
            onNodeSelected: function (event, data) {
                getNoticeClass(data.href);
            }
        });
    });
    
    function getNoticeClass(url) {
        $('#tableList').bootstrapTable('destroy').bootstrapTable({
            url: url,         //请求后台的URL（*）
            method: 'post',
            contentType: "application/x-www-form-urlencoded",
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: false,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "id desc",                   //排序方式
            queryParams: function (params) {
                var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                    size: params.limit,   //页面大小
                    page: params.offset/params.limit + 1
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
                    field: 'className',
                    title: '名称',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'classLevel',
                    title: '级别',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row, index) {
                        if(value == 1){
                            return '<span class="glyphicon glyphicon-star"></span>';
                        }
                        if(value == 2){
                            return '<span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span>';
                        }
                        if(value == 3){
                            return '<span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span>';
                        }
                        if(value == 4){
                            return '<span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span>';
                        }
                        if(value == 5){
                            return '<span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span>';
                        }
                    }
                }, {
                    field: 'orderNum',
                    title: '排序',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'classDesc',
                    title: '描述',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: "操作",
                    align: 'center',
                    valign: 'middle',
                    width: 80, // 定义列的宽度，单位为像素px
                    formatter: function (value, row, index) {
                        return '<button class="btn btn-default btn-xs" onclick="view(\'' + row.classCode  + '\')" data-toggle="modal" data-target="#myModal">修改</button>&nbsp;';
                    }
                }
            ]
        });
    }

    $("#submitDataButton").click(function () {
        var id = $("#id").val();
        var classLevel = $("#class_level").val();
        var orderNum = $("#order_num").val();
        var classDesc = $("#class_desc").val();
        $.APIPost("/api/notice/editStockNoticeClass?id=" + id + "&classLevel=" + classLevel + "&orderNum=" + orderNum + "&classDesc=" + classDesc ,function (data) {
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

function view(classCode) {
    $('#myModal').on('show.bs.modal',function() {
        $.APIPost("/api/notice/getStockNoticeClass?classCode="+classCode ,function (data) {
            $("#id").val(data.data.result.id);
            $("#class_level").val(data.data.result.classLevel);
            $("#order_num").val(data.data.result.orderNum);
            $("#class_desc").val(data.data.result.classDesc);
        });
    });
}