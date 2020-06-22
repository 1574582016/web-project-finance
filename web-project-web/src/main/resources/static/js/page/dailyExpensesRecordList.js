
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


    $('#expenses_day').datepicker({
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        clearBtn: true ,
        autoclose: true
    });

    var firstOption = '';
    var f_parentId = 0;
    $.each(expenseList,function(key,value){
        if(value.parentId == 0 || value.parentId == '0'){
            firstOption += '<option value="'+ value.id +'">'+ value.className +'</option>';
            if(f_parentId == 0){
                f_parentId = value.id;
            }
        }

    });
    $("#s_firstType").html('<option value="">请选择</option>' + firstOption);
    $("#first_type").html(firstOption);

    var secondOption = '';
    $.each(expenseList,function(key,value){
        if(value.parentId == f_parentId){
            secondOption += '<option value="'+ value.id +'">'+ value.className +'</option>';
        }

    });
    $("#second_type").html(secondOption);

    $('#tableList').bootstrapTable('destroy').bootstrapTable({
        url: '/api/bankCard/getDailyExpensesRecordList',         //请求后台的URL（*）
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
                firstType: $("#s_firstType").val(),
                secondType: $("#s_secondType").val(),
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
                field: 'firstName',
                title: '一级分类',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'secondName',
                title: '二级分类',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'expensesDay',
                title: '消费日期',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'expensesMoney', // 返回json数据中的name
                title: '消费金额', // 表格表头显示文字
                align: 'center', // 左右居中
                valign: 'middle' // 上下居中
            }, {
                title: "操作",
                align: 'center',
                valign: 'middle',
                width: 160, // 定义列的宽度，单位为像素px
                formatter: function (value, row, index) {
                    return '<button class="btn btn-primary btn-xs" onclick="edit(\'' + row.id + '\')">修改</button>&nbsp;';
                }
            }
        ]
    });

    $("#searchDataButton").click(function () {
        $('#tableList').bootstrapTable('refresh');
    });


    $("#addDataButton").click(function () {
        $("#id").val("");
        $("#first_type").html(firstOption);
        $("#second_type").html(secondOption);
        $("#expenses_day").val("");
        $("#expenses_money").val("0");
        $('#myModal').modal();
    });

    $("#submitDataButton").click(function () {
        var id = $("#id").val();
        var first_type = $("#first_type").val();
        var second_type = $("#second_type").val();
        var expenses_day = $("#expenses_day").val();
        var expenses_money = $("#expenses_money").val();

        if(isEmpty(first_type)){
            window.parent.showWarningAlert("一级分类，不能为空！");
            return ;
        }
        if(isEmpty(second_type)){
            window.parent.showWarningAlert("二级分类，不能为空！");
            return ;
        }
        if(isEmpty(expenses_day)){
            window.parent.showWarningAlert("消费日期，不能为空！");
            return ;
        }
        if(isEmpty(expenses_money)){
            window.parent.showWarningAlert("消费金额，不能为空！");
            return ;
        }

        if(parseFloat(expenses_money) <=0 ){
            window.parent.showWarningAlert("消费金额，需大于0！");
            return ;
        }

        $.APIPost("/api/bankCard/editDailyExpensesRecord",JSON.stringify({id : id ,firstType :first_type , secondType: second_type, expensesDay: expenses_day ,expensesMoney :expenses_money}),function (data) {
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
    $.APIPost("/api/bankCard/getDailyExpensesRecord?id="+id ,function (data) {
        $("#id").val(data.data.result.id);
        $("#first_type").val(data.data.result.firstType);
        var secondOption = '';
        $.each(expenseList,function(key,value){
            if(value.parentId == data.data.result.firstType){
                secondOption += '<option value="'+ value.id +'">'+ value.className +'</option>';
            }

        });
        $("#second_type").html(secondOption);

        $("#second_type").val(data.data.result.secondType);
        $("#expenses_day").val(data.data.result.expensesDay);
        $("#expenses_money").val(data.data.result.expensesMoney);
        $('#myModal').modal();
    });
}

function changeSecondType(obj) {
    var parentId = $(obj).val();
    var secondOption = '';
    $.each(expenseList,function(key,value){
        if(value.parentId == parentId){
            secondOption += '<option value="'+ value.id +'">'+ value.className +'</option>';
        }

    });
    $("#s_secondType").html('<option value="">请选择</option>' + secondOption);
}

function changeFSecondType(obj) {
    var parentId = $(obj).val();
    var secondOption = '';
    $.each(expenseList,function(key,value){
        if(value.parentId == parentId){
            secondOption += '<option value="'+ value.id +'">'+ value.className +'</option>';
        }

    });
    $("#second_type").html(secondOption);
}

