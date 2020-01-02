
$(function () {

    $('#tableList').bootstrapTable('destroy').bootstrapTable({
        url: '/api/param/getSystemParamList',         //请求后台的URL（*）
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
                paramIdentity: $("#s_paramIdentity").val(),
                paramName: $("#s_paramName").val()
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
                field: 'paramIdentity',
                title: '参数标识',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'paramName',
                title: '参数名称',
                align: 'center',
                valign: 'middle'
            },  {
                field: 'childParam',
                title: '参数值',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    var str="<table>"
                    $.each(value ,function (index ,value) {
                       str +="<tr><td style='border: none;'>" + value.paramIdentity + "</td><td style='border: none;'>" + value.paramName + "</td></tr>";
                    })
                    str += "</table>";
                    
                    return str;
                }
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
                    return '<button class="btn btn-primary btn-xs" onclick="edit(\'' + row.id + '\')"  data-toggle="modal" data-target="#myModal">修改</button>';
                }
            }
        ]
    });

    $("#searchDataButton").click(function () {
        $('#tableList').bootstrapTable('refresh');
    });

    $("#addBoxButton").click(function(){
        var str = '<div class="form-group">'
            +'<label class="col-sm-3 control-label"></label>'
            +'<div class="col-sm-3 param-left-box">'
            +'   <input type="text" class="form-control" name="sub_param_identity">'
            +'</div>'
            +'<div class="col-sm-3 param-middle-box">'
            +'   <input type="text" class="form-control" name="sub_param_name">'
            +'</div>'
            +'<div class="col-sm-2 param-right-box">'
            +'   <label class="checkbox">'
            +'        <input type="checkbox" name="isvalid" checked>'
            +'    </label>'
            +'</div>'
            +'<div class="col-sm-1 param-button-box">'
            +'   <button type="button" class="btn btn-primary btn-xs"  onclick="minusButton(this)"><span class="glyphicon glyphicon-minus"></span></button>'
            +'</div>'
            +'</div>'
        $("#param-form-box").append(str);
    });

    $("input[name='isvalid']").click(function () {
        var attr = $(this).attr('checked');
        if (typeof attr !== typeof undefined && attr !== false) {
           $(this).removeAttr("checked");
        }else{
            $(this).attr("checked",true);
        }
    });


    $("#addDataButton").click(function () {
        $('#myModal').on('show.bs.modal',function() {
            $("#id").val("");
            $("#param_identity").val("");
            $("#param_name").val("");
            for(var i = 0 ; i< 3 ; i++){
                $("#addBoxButton").click();
            }
        });
    });

    $("#submitDataButton").click(function () {
        var id = $("#id").val();
        var paramCode = $("#param_code").val();
        var paramIdentity = $("#param_identity").val();
        var paramName =  $("#param_name").val();
        var subParamId = new Array();
        $("input[name='sub_param_id']").each(function(){
            subParamId.push($(this).val());
        });
        var subParamIdentity = new Array();
        $("input[name='sub_param_identity']").each(function(){
            subParamIdentity.push($(this).val());
        });
        var subParamName = new Array();
        $("input[name='sub_param_name']").each(function(){
            subParamName.push($(this).val());
        });
        var isvalid = new Array();
        $("input[name='isvalid']").each(function(){
            var attr = $(this).attr('checked');
            if (typeof attr !== typeof undefined && attr !== false) {
                isvalid.push(1);
            }else{
                isvalid.push(0);
            }
        });
        var subParam = "";
        for(var i = 0 ; i < subParamIdentity.length ; i ++){
            subParam += subParamId[i] + "=" + subParamIdentity[i] + "=" + subParamName[i] + "=" +isvalid[i] + ",";
        }
        $.APIPost("/api/param/editSystemParam",JSON.stringify({id : id ,paramCode:paramCode ,paramIdentity :paramIdentity , paramName: paramName, subParamString: subParam }),function (data) {
            if(data.success) {
                hideModal("myModal");
                window.parent.showSuccessAlert(data.message, function () {
                    $('#tableList').bootstrapTable('refresh');
                });
            } else {
                window.parent.showFailedAlert(data.message);
            }

        });
    });

});

function edit(id) {
    $('#myModal').on('show.bs.modal',function() {
        $("input[name='sub_param_identity']").each(function(){
            var obj = $(this);
            obj.parent().parent().remove();
        });

        $.APIPost("/api/param/getSystemParamInfo?id="+id ,function (data) {
            $("#id").val(data.data.result.id);
            $("#param_code").val(data.data.result.paramCode);
            $("#param_identity").val(data.data.result.paramIdentity);
            $("#param_name").val(data.data.result.paramName);
            $.each(data.data.result.childParam ,function (index ,value) {
                var str = '<div class="form-group">'
                        +'<label class="col-sm-3 control-label"></label>'
                        +'<div class="col-sm-3 param-left-box">'
                        +'   <input type="hidden" class="form-control" name="sub_param_id" value="'+ value.id +'">'
                        +'   <input type="text" class="form-control" name="sub_param_identity" value="'+ value.paramIdentity +'">'
                        +'</div>'
                        +'<div class="col-sm-3 param-middle-box">'
                        +'   <input type="text" class="form-control" name="sub_param_name" value="'+ value.paramName +'">'
                        +'</div>'
                        +'<div class="col-sm-2 param-right-box">'
                        +'   <label class="checkbox">';
                        if(value.isvalid == 1){
                            str += '        <input type="checkbox" name="isvalid" checked>';
                        }else{
                            str += '        <input type="checkbox" name="isvalid">';
                        }

                   str += '  </label>'
                        +'</div>'
                        +'<div class="col-sm-1 param-button-box">'
                        +'   <button type="button" class="btn btn-primary btn-xs"  onclick="minusButton(this)"><span class="glyphicon glyphicon-minus"></span></button>'
                        +'</div>'
                        +'</div>'
                $("#param-form-box").append(str);
            })
        });
    });
}

function minusButton(obj){
    var thisObj=$(obj);
    thisObj.parent().parent().remove();
}

