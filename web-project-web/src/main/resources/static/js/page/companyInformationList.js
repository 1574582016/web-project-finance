
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

    $.APIPost("/api/param/getParamListByIndetity?indetity=diaryType" ,function (data) {
        var str = ""
        $.each(data.data.result ,function (index ,value) {
            str +='<option value="'+ value.paramIdentity +'">' + value.paramName + '</option>'
        });
        var str2 = '<option value="">请选择</option>' + str;
        $("#s_type").html(str2);
        $("#x_type").html(str);

    });

    $('#tableList').bootstrapTable('destroy').bootstrapTable({
        url: '/api/company/getCompanyBaseInformationList',         //请求后台的URL（*）
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
                name: $("#s_name").val(),
                type: $("#s_type").val(),
                startDate: $("#s_start").val(),
                endDate: $("#s_end").val()
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
                field: 'listCode',
                title: '股票编码',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'listName',
                title: '股票名称',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'listIndustry',
                title: '行业板块',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'listMarket',
                title: '交易所',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    if(value == 1){
                       return "上交所";
                    }
                    if(value == 2){
                        return "深交所";
                    }
                }
            }, {
                field: 'listSector',
                title: '市场板块',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    if(value == 1){
                        return "主板";
                    }
                    if(value == 2){
                        return "中小板";
                    }
                    if(value == 3){
                        return "创业板";
                    }
                    if(value == 4){
                        return "风险警示板";
                    }
                }
            }, {
                field: 'companyRegion',
                title: '所属区域',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'companyIndustry',
                title: '公司行业',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'companyIndustryDetail',
                title: '行业细分',
                align: 'center',
                valign: 'middle'
            }, {
                title: "操作",
                align: 'center',
                valign: 'middle',
                width: 160, // 定义列的宽度，单位为像素px
                formatter: function (value, row, index) {
                    return '<button class="btn btn-default btn-xs" onclick="view(\'' + row.id + '\',\'' + row.listCode  + '\')">查看</button>&nbsp;'
                        + '<button class="btn btn-primary btn-xs" onclick="edit(\'' + row.id + '\')">修改</button>';
                }
            }
        ]
    });

    $("#searchDataButton").click(function () {
        $('#tableList').bootstrapTable('refresh');
    });


    $("#addDataButton").click(function () {
        addNextBread("新增公司信息");
        location.href="/company/companyInformationEdit";
    })


});

function edit(id) {
    addNextBread("修改公司信息");
    location.href="/company/companyInformationEdit?id="+id;
}

function view(id , listCode) {
    addNextBread("查看公司信息");
    location.href="/company/companyAllInfomationStatictis?id="+id + "&listCode=" + listCode;
}
