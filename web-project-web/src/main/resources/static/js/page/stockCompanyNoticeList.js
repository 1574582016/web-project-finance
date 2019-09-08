
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
        url: '/api/notice/getStockNoticeCompanyList',         //请求后台的URL（*）
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
        detailView: true,                   //是否显示父子表
        columns: [
            {
                field: 'stockCode',
                title: '股票编码',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    return '<a class="text_link_a" onclick="view(\'' + value  + '\')">'+ value +'</a>';
                }
            }, {
                field: 'stockName',
                title: '股票名称',
                align: 'center',
                valign: 'middle'
            },{
                field: 'stockPlate',
                title: '股票类型',
                align: 'center',
                valign: 'middle'
            },{
                field: 'companyRegion',
                title: '所属地域',
                align: 'center',
                valign: 'middle'
            },{
                field: 'stockSector',
                title: '行业细类',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'establishDate',
                title: '成立日期',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'companyRegistMoney',
                title: '注册资本',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'publishDate',
                title: '上市日期',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'publishAmount',
                title: '发行数量',
                align: 'center',
                valign: 'middle'
            }
        ],
        onExpandRow: function (index, row, $detail) {
            InitSubTable(index, row, $detail);
        }
    });


    function InitSubTable(index, row, $detail) {
        var cur_table = $detail.html('<table></table>').find('table');
        $(cur_table).bootstrapTable({
            url: '/api/notice/getStockNoticeDetailList?stockCode=' + row.stockCode + "&startDay=" + $("#startDay").val() + "&endDay=" + $("#endDay").val() ,
            method: 'post',
            contentType: 'application/json;charset=UTF-8',//这里我就加了个utf-8
            dataType: 'json',
            clickToSelect: true,
            detailView: false,//父子表
            uniqueId: "noticeTitle",
            pageSize: 10,
            pageList: [10, 25],
            columns: [
                {
                    field: 'publishTime',
                    title: '发布日期',
                    align: 'center',
                    valign: 'middle'
                },{
                    field: 'classLevel',
                    title: '重要程度',
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
                },{
                    field: 'bigNoticeClass',
                    title: '所属大类',
                    align: 'center',
                    valign: 'middle'
                },{
                    field: 'middleNoticeClass',
                    title: '所属中类',
                    align: 'center',
                    valign: 'middle'
                },{
                    field: 'noticeType',
                    title: '公告类型',
                    align: 'center',
                    valign: 'middle'
                },{
                    field: 'noticeTitle',
                    title: '公告标题',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row, index) {
                        return '<a class="text_link_a" href="'+ row.noticeDetail +'" target="view_window">'+ value +'</a>';
                    }
                }
            ]
        });
    }

    $("#searchDataButton").click(function () {
        $('#tableList').bootstrapTable('refresh');
    });


});

function view(id , replayCode) {
    addNextBread("查看复盘信息");
    location.href="/invest/investForexReplayView?id="+id + "&replayCode=" + replayCode;
}
