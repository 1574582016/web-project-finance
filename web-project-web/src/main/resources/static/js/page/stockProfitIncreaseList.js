$(function () {
    flashTable('tableList_10' , 1);
    flashTable('tableList_5_10' , 2);
    flashTable('tableList_3_5' , 3);


    $("[data-toggle='tooltip']").tooltip();



});
function flashTable(boxId , yearType) {
    $('#' + boxId ).bootstrapTable('destroy').bootstrapTable({
        url: '/api/stockPool/getStockProfitIncreaseList' ,
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
                yearType: yearType
            };
            return temp;
        },
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: false,
        showColumns: false,                  //是否显示所有的列
        showRefresh: false,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        // height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
        showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        columns: [
            {
                field: 'forthSector',
                title: '行业',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'stockCode',
                title: '编码',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'stockName',
                title: '名称',
                align: 'center',
                valign: 'middle'
            },{
                field: 'publishTime',
                title: '上市时间',
                align: 'center',
                valign: 'middle'
            },{
                field: 'spaceYear',
                title: '上市年限',
                align: 'center',
                valign: 'middle'
            },{
                field: 'averageGrowRate',
                title: '业绩增加率',
                align: 'center',
                valign: 'middle'
            },{
                field: 'investor',
                title: '投资个数',
                align: 'center',
                valign: 'middle'
            },{
                field: 'averageGrowProfit',
                title: '业绩增加幅度',
                align: 'center',
                valign: 'middle'
            },{
                field: 'averageIncreaseRate',
                title: '5年增幅均值',
                align: 'center',
                valign: 'middle'
            },{
                field: 'totalIncreaseRate',
                title: '今年增率',
                align: 'center',
                valign: 'middle'
            },{
                field: 'firstIncreaseRate',
                title: '一季度增率',
                align: 'center',
                valign: 'middle'
            },{
                field: 'secondIncreaseRate',
                title: '二季度增率',
                align: 'center',
                valign: 'middle'
            },{
                field: 'thirdIncreaseRate',
                title: '三季度增率',
                align: 'center',
                valign: 'middle'
            },{
                field: 'forthIncreaseRate',
                title: '四季度增率',
                align: 'center',
                valign: 'middle'
            }
        ]
    });
}
