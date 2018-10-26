
$(function () {

    $.APIPost("/api/role/getRoleList",function (data) {
        var p_option = '';
        var s_option = '<option value="">请选择</option>';
        $.each(data.data.result,function (index ,value) {
            p_option += '<label class="checkbox-inline"><input type="checkbox" id="p_role" name="p_role" value="'+ value.roleCode +'"> '+ value.roleName +'</label>';
            s_option +='<option value="'+ value.roleCode +'">'+ value.roleName +'</option>'
        });
        $("#p_roleBox").html(p_option);
        $("#s_role").html(s_option);
    });

    $('#tableList').bootstrapTable('destroy').bootstrapTable({
        url: '/api/user/getSystemUserList',         //请求后台的URL（*）
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
                userName: $("#s_userName").val(),
                realName: $("#s_realName").val(),
                roleCode: $("#s_role").val()
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
                field: 'userName',
                title: '用户名',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'realName',
                title: '姓名',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'phone',
                title: '电话',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'email', // 返回json数据中的name
                title: 'email', // 表格表头显示文字
                align: 'center', // 左右居中
                valign: 'middle' // 上下居中
            }, {
                field: 'roleName',
                title: '角色',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'isvalid',
                title: '状态',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    if(value == 1){
                        return '<span class="label label-success">正常</span>';
                    }
                    return'<span class="label label-danger">禁用</span>';
                }
            }, {
                title: "操作",
                align: 'center',
                valign: 'middle',
                width: 160, // 定义列的宽度，单位为像素px
                formatter: function (value, row, index) {
                    return '<button class="btn btn-primary btn-xs" onclick="edit(\'' + row.id + '\')"  data-toggle="modal" data-target="#myModal">修改</button>&nbsp;'
                           +'<button class="btn btn-default btn-xs" onclick="author(\'' + row.userCode + '\')"  data-toggle="modal" data-target="#myModal3">授权</button>';
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
            $("#userName").val("");
            $("#realName").val("");
            $("#headPicture").val("");
            $("#phone").val("");
            $("#email").val("");
            $("[name='isvalid']").removeAttr("checked");
            $("[name='isvalid'][value='1']").attr("checked", true);
        });
    });

    $("#submitDataButton").click(function () {
        var id = $("#id").val();
        var userName = $("#userName").val();
        var realName =  $("#realName").val();
        var headPicture =  $("#headPicture").val();
        var phone = $("#phone").val();
        var email = $("#email").val();
        var isvalid =  $('input[name="isvalid"]:checked').val();
        $.APIPost("/api/user/editSystemUser",JSON.stringify({id : id ,userName :userName , realName: realName, headPicture: headPicture ,phone :phone , email: email , isvalid: isvalid}),function (data) {
            if(data.success){
                if(id == null){
                    hideModal("myModal2");
                }else{
                    hideModal("myModal");
                }
                showSuccessAlert(data.message,function () {
                    $('#tableList').bootstrapTable('refresh');
                });
            }else{
                showFailedAlert(data.message);
            }
        })

    });

    $("#submitDataButton3").click(function () {
        var userCode = $("#userCode").val();
        var id_array=new Array();
        $('input[name="p_role"]:checked').each(function(){
            id_array.push($(this).val());
        });
        var roleId=id_array.join(',');
        if(id_array.length != 0){
            $.APIPost("/api/user/linkUserRole?roleId=" + roleId + "&userCode="+ userCode ,function (data) {
                if(data.success){
                    hideModal("myModal3");
                    showSuccessAlert(data.message,function () {
                        $('#tableList').bootstrapTable('refresh');
                    });
                }else{
                    showFailedAlert(data.message);
                }
            });
        }else {
            showFailedAlert("请选择角色！");
        }


    });

});

function edit(id) {
    $('#myModal').on('show.bs.modal',function() {
        $.APIPost("/api/user/getSystemUserInfo?id="+id ,function (data) {
            $("#id").val(data.data.result.id);
            $("#userName").val(data.data.result.userName);
            $("#realName").val(data.data.result.realName);
            $("#headPicture").val(data.data.result.headPicture);
            $("#phone").val(data.data.result.phone);
            $("#email").val(data.data.result.email);
            $("[name='isvalid']").removeAttr("checked");
            $("[name='isvalid'][value='"+ data.data.result.isvalid +"']").attr("checked", true);
        });
    });
}

function author(userCode) {
    $("#userCode").val(userCode);
    $.APIPost("/api/role/getRoleList?userCode="+userCode,function (data) {
        $("[name='p_role']").removeAttr("checked");
        $.each(data.data.result, function (index, value) {
            $("[name='p_role'][value='"+ value.roleCode +"']").attr("checked", true);
        });
    });
}

