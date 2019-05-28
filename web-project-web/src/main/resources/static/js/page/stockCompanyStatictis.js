
$(function () {
    var id = $("#id").val();
    var stockCode = $("#a_stockCode").val();

    if(!isEmpty(id)){
        $.APIPost("/api/stock/getStockCompanyBaseById?id="+id ,function (response) {
            $("#stockCode").html(response.data.companyBase.stockACode);
            $("#stockName").html(response.data.companyBase.stockAName);
            $("#stockSector").html(response.data.companyBase.stockSector);

            $("#companyRegion").html(response.data.companyBase.companyRegion);
            $("#contrySector").html(response.data.companyBase.contrySector);

            $("#publishDate").html(response.data.companyBase.publishDate);
            $("#companyRegistMoney").html(response.data.companyBase.companyRegistMoney);
            $("#publishAmount").html(response.data.companyBase.publishAmount);
            $("#businessScope").html(response.data.companyBase.companyBusinessScope);
            $("#subjectMatter").html(response.data.companyBase.companySubjectMatter);
            var sector = "";
            $.each(response.data.sectorList,function (index ,value) {
                sector += "<tr><td>" + value.productName + "</td><td>" + value.productRevenue + "</td></tr>";
            });
            $("#stockSectorBox").html(sector);
            var product = "";
            $.each(response.data.productList,function (index ,value) {
                product += "<tr><td>" + value.productName + "</td><td>" + value.productRevenue + "</td></tr>";
            });
            $("#stockProductBox").html(product);
            var region = "";
            $.each(response.data.regionList,function (index ,value) {
                region += "<tr><td>" + value.productName + "</td><td>" + value.productRevenue + "</td></tr>";
            });
            $("#stockRegionBox").html(region);

            var analyse = "";
            $.each(response.data.analyseList,function (index ,value) {
                analyse += "<p style='font-weight: bold'><span class='glyphicon glyphicon-star'>&nbsp;</span>" + value.operatePoint + "</p><p>" + value.pointAnalyse + "</p>";
            });
            $("#analyseBox").html(analyse);
        })
    }

    createCompanyOpratePie(listCode);

    createCompanyOprateBar(listCode);

    $('#companyProductTable').bootstrapTable('destroy').bootstrapTable({
        url: '/api/company/getCompanyOperateInformationList',         //请求后台的URL（*）
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
                listCode: listCode
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
                field: 'operateType',
                title: '营运分类',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    if(value == 1){
                        return "按行业分类";
                    }
                    if(value == 2){
                        return "按产品分类";
                    }
                    if(value == 3){
                        return "按地域分类";
                    }
                }
            }, {
                field: 'publishDate',
                title: '发布日期',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'typeName',
                title: '主营构成',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'operateRevenue',
                title: '主营收入',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'operateRevenueRate',
                title: '主营收入率(%)',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'operateCost',
                title: '主营成本',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'operateCostRate',
                title: '主营成本率(%)',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'operateProfit',
                title: '主营利润',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'operateProfitRate',
                title: '主营利润率(%)',
                align: 'center',
                valign: 'middle'
            }, {
                title: "操作",
                align: 'center',
                valign: 'middle',
                width: 160, // 定义列的宽度，单位为像素px
                formatter: function (value, row, index) {
                    return '<button class="btn btn-primary btn-xs" data-toggle="modal" data-target="#companyProductModal" onclick="editOperate(\'' + row.id + '\')">修改</button>';
                }
            }
        ]
    });

    $("#addCompanyProductData").click(function () {
        $('#companyProductModal').on('show.bs.modal',function() {
            $("#p_id").val("");
            $("#p_operateType").val("1");
            $("#p_typeName").val("");
            $("#p_publishDate").val("");
            $("#p_operateRevenue").val("");
            $("#p_operateRevenueRate").val("");
            $("#p_operateCost").val("");
            $("#p_operateCostRate").val("");
            $("#p_operateProfit").val("");
            $("#p_operateProfitRate").val("");
        });
    });

    $("#p_submitDataButton").click(function () {
        var p_id = $("#p_id").val();
        var operateType = $("#p_operateType").val();
        var typeName = $("#p_typeName").val();
        if(isEmpty(typeName)){
            showWarningAlert("主营构成不能为空！");
            return ;
        }
        var publishDate = $("#p_publishDate").val();
        if(isEmpty(publishDate)){
            showWarningAlert("发布日期不能为空！");
            return ;
        }
        var operateRevenue = $("#p_operateRevenue").val();
        if(isEmpty(operateRevenue)){
            showWarningAlert("主营收入不能为空！");
            return ;
        }
        var operateRevenueRate = $("#p_operateRevenueRate").val();
        if(isEmpty(operateRevenueRate)){
            showWarningAlert("主营收入率不能为空！");
            return ;
        }
        var operateCost = $("#p_operateCost").val();
        if(isEmpty(operateCost)){
            showWarningAlert("主营成本不能为空！");
            return ;
        }
        var operateCostRate = $("#p_operateCostRate").val();
        if(isEmpty(operateCostRate)){
            showWarningAlert("主营成本率不能为空！");
            return ;
        }
        var operateProfit = $("#p_operateProfit").val();
        if(isEmpty(operateProfit)){
            showWarningAlert("主营利润不能为空！");
            return ;
        }
        var operateProfitRate = $("#p_operateProfitRate").val();
        if(isEmpty(operateProfitRate)){
            showWarningAlert("主营利润率不能为空！");
            return ;
        }

        $.APIPost("/api/company/editCompanyOperateInformation",JSON.stringify({
            id : p_id ,
            listCode: listCode ,
            operateType :operateType ,
            typeName: typeName,
            publishDate:publishDate ,
            operateRevenue:operateRevenue ,
            operateRevenueRate:operateRevenueRate ,
            operateCost:operateCost ,
            operateCostRate:operateCostRate ,
            operateProfit:operateProfit ,
            operateProfitRate:operateProfitRate
        }),function (data) {
            if(data.success){
                hideModal("companyProductModal");
                showSuccessAlert(data.message,function () {
                    $('#companyProductTable').bootstrapTable('refresh');
                });
            }else{
                showFailedAlert(data.message);
            }
        })
    });
});

function editOperate(id) {
    $('#companyProductModal').on('show.bs.modal',function() {
        $.APIPost("/api/company/getCompanyOperateInformationById?id="+id ,function (response) {
            $("#p_id").val(response.data.result.id);
            $("#p_operateType").val(response.data.result.operateType);
            $("#p_typeName").val(response.data.result.typeName);
            $("#p_publishDate").val(response.data.result.publishDate);
            $("#p_operateRevenue").val(response.data.result.operateRevenue);
            $("#p_operateRevenueRate").val(response.data.result.operateRevenueRate);
            $("#p_operateCost").val(response.data.result.operateCost);
            $("#p_operateCostRate").val(response.data.result.operateCostRate);
            $("#p_operateProfit").val(response.data.result.operateProfit);
            $("#p_operateProfitRate").val(response.data.result.operateProfitRate);
        });
    });
}


function createCompanyOpratePie(listCode){
    $.APIPost("/api/company/getCompanyOpratePieData?listCode="+listCode ,function (response) {
        var pieChart = echarts.init(document.getElementById('companyProductPie'));
        pieOption = {
            title : {
                text: '营运产品构成图',
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                x : 'center',
                y : 'bottom',
                data:response.data.titles
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: false},
                    dataView : {show: false, readOnly: false},
                    magicType : {
                        show: false,
                        type: ['pie', 'funnel']
                    },
                    restore : {show: false},
                    saveAsImage : {show: false}
                }
            },
            calculable : true,
            series : [
                {
                    name:'收入构成',
                    type:'pie',
                    radius : [30, 90],
                    center : ['15%', 200],
                    roseType : 'area',
                    // x: '50%',               // for funnel
                    max: 40,                // for funnel
                    sort : 'ascending',     // for funnel
                    data:response.data.revenue
                },
                {
                    name:'成本构成',
                    type:'pie',
                    radius : [30, 90],
                    center : ['50%', 200],
                    roseType : 'area',
                    x: '50%',               // for funnel
                    max: 40,                // for funnel
                    sort : 'ascending',     // for funnel
                    data:response.data.cost
                },
                {
                    name:'利润构成',
                    type:'pie',
                    radius : [30, 90],
                    center : ['85%', 200],
                    roseType : 'area',
                    x: '50%',               // for funnel
                    max: 40,                // for funnel
                    sort : 'ascending',     // for funnel
                    data:response.data.profit
                }
            ]
        };
        pieChart.setOption(pieOption);
    })
}

function createCompanyOprateBar(listCode){
    $.APIPost("/api/company/getCompanyOprateBarData?listCode="+listCode ,function (response) {
        var barChart = echarts.init(document.getElementById('companyProductBar'));
        barOption = {
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['收入','成本','利润','毛利']
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataZoom : {show: true},
                    dataView : {show: true},
                    magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            dataZoom : {
                show : true,
                realtime : true,
                start : 20,
                end : 80
            },
            xAxis : [
                {
                    type : 'category',
                    boundaryGap : false,
                    data : response.data.titles
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'收入',
                    type:'line',
                    data:response.data.revenue
                },
                {
                    name:'成本',
                    type:'line',
                    data:response.data.cost
                },
                {
                    name:'利润',
                    type:'line',
                    data:response.data.profit
                },
                {
                    name:'毛利率',
                    type:'line',
                    data:response.data.gross
                }
            ]
        };
        barChart.setOption(barOption);
    })
}
