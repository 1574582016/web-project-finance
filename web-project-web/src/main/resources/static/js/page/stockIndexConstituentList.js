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

    $("#sector_code").select2();

    var myChart = echarts.init(document.getElementById('main'));
    var colors = ['#34bd37', '#e80b3e', '#3a9ff5','#d3ff24'];

    var option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c}%"
        },
        legend: {
            data: ['上证50','上证180','上证100','上证380','上证150','其他']
        },
        calculable: true,
        series: [
            {
                name:'漏斗图',
                type:'funnel',
                left: '10%',
                top: 60,
                //x2: 80,
                bottom: 60,
                width: '80%',
                // height: {totalHeight} - y - y2,
                // min: 0,
                // max: 100,
                minSize: '0%',
                maxSize: '100%',
                sort: 'ascending',
                gap: 2,
                label: {
                    show: true,
                    position: 'inside'
                },
                labelLine: {
                    length: 10,
                    lineStyle: {
                        width: 1,
                        type: 'solid'
                    }
                },
                itemStyle: {
                    borderColor: '#fff',
                    borderWidth: 1
                },
                emphasis: {
                    label: {
                        fontSize: 20
                    }
                },
                data: [
                    {value: 120, name: '其他'},
                    {value: 100, name: '上证150'},
                    {value: 80, name: '上证380'},
                    {value: 60, name: '上证100'},
                    {value: 40, name: '上证180'},
                    {value: 20, name: '上证50'}
                ]
            }
        ]
    };

    myChart.setOption(option);

    myChart.on('click', function (params) {
        indexConstituent(params.name);
    });

});


function indexConstituent(indexName) {
    $('#tableList').bootstrapTable('destroy').bootstrapTable({
        url: '/api/stockIndex/getStockIndexConstituentList' ,
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        toolbar: '#toolbar',                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: false,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "id desc",                   //排序方式
        queryParams: function (params) {
            var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                size: params.limit,   //页面大小
                page: params.offset/params.limit + 1,  //页码
                indexName: indexName
            };
            return temp;
        },
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: false,
        showColumns: true,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        // height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
        showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        scrollTo: true,
        columns: [
            {
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
                field: 'stockSector',
                title: '行业',
                align: 'center',
                valign: 'middle'
            },{
                field: 'indexName',
                title: '上榜',
                align: 'center',
                valign: 'middle'
            }
        ]
    });
}
