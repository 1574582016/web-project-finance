
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

    $.APIPost("/api/param/getParamListByIndetity?indetity=questionType" ,function (data) {
        var str = ""
        $.each(data.data.result ,function (index ,value) {
            str +='<option value="'+ value.paramIdentity +'">' + value.paramName + '</option>'
        });
        var str2 = '<option value="">请选择</option>' + str;
        $("#s_type").html(str2);
        $("#f_type").html(str);

    });

    $('#tableList').bootstrapTable('destroy').bootstrapTable({
        url: '/api/question/getLearnQuestionList',         //请求后台的URL（*）
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
                content: $("#s_content").val(),
                type: $("#s_type").val(),
                startTime: $("#s_start").val(),
                endTime: $("#s_end").val()
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
                field: 'createTime',
                title: '创建时间',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    return value.substr(0,10);
                }
            }, {
                field: 'questionType',
                title: '类型',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'questionContent',
                title: '问题内容',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'isvalid',
                title: '状态',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    if(value == 1){
                        return '<span class="label label-success">使用</span>';
                    }
                    return '<span class="label label-danger">废弃</span>';
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
            $("#f_content").val("");
            $("#f_type").val("A");
            $("#f_answer").val("");
            $("[name='isvalid']").removeAttr("checked");
            $("[name='isvalid'][value='1']").attr("checked", true);
        });
    });

    $("#submitDataButton").click(function () {
        var id = $("#id").val();
        var questionContent = $("#f_content").val();
        var questionType = $("#f_type").val();
        var questionAnswer = $("#f_answer").val();
        var isvalid =  $('input[name="isvalid"]:checked').val();
        $.APIPost("/api/question/editLearnQuestion",JSON.stringify({id : id ,questionType :questionType , questionContent: questionContent, questionAnswer: questionAnswer ,isvalid:isvalid}),function (data) {
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
        $.APIPost("/api/question/getLearnQuestionInfo?id="+id ,function (data) {
            $("#id").val(data.data.result.id);
            $("#f_content").val(data.data.result.questionContent);
            $("#f_type").val(data.data.result.questionType);
            $("#f_answer").val(data.data.result.questionAnswer);
            $("[name='isvalid']").removeAttr("checked");
            $("[name='isvalid'][value='"+ data.data.result.isvalid +"']").attr("checked", true);
        });
    });
}


