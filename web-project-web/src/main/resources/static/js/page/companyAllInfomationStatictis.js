
$(function () {
    var id = $("#id").val();
    if(!isEmpty(id)){
        $.APIPost("/api/company/getCompanyBaseInformationById?id="+id ,function (response) {
            console.log(response);
            $("#companyName").html(response.data.result.companyName);
            $("#companyRegion").html(response.data.result.companyRegion);
            $("#establishTime").html(response.data.result.establishTime);
            $("#registerMoney").html(response.data.result.registerMoney);
            $("#companyIndustry").html(response.data.result.companyIndustry);
            $("#companyIndustryDetail").html(response.data.result.companyIndustryDetail);
            $("#listCode").html(response.data.result.listCode);
            $("#listName").html(response.data.result.listName);
            $("#listTime").html(response.data.result.listTime);
            var listMarket = response.data.result.listMarket ;
            if(listMarket == 1){
                $("#listMarket").html("上海证券交易所");
            }else{
                $("#listMarket").html("深圳证券交易所");
            }
            var listSector = response.data.result.listSector ;
            if(listSector ==1){
                $("#listSector").html("主板");
            }else if(listSector ==2){
                $("#listSector").html("中小板");
            }else if(listSector ==3){
                $("#listSector").html("创业板");
            }else{
                $("#listSector").html("风险警示板");
            }
            $("#listNumber").html(response.data.result.listNumber);
            $("#listPrice").html(response.data.result.listPrice);
            $("#listMoney").html(response.data.result.listMoney);
            $("#listIndustry").html(response.data.result.listIndustry);
            $("#listOrder").html(response.data.result.listOrder);
            var mainRelateIndustry = response.data.result.mainRelateIndustry;
            if(isEmpty(mainRelateIndustry)){
                $("#mainRelateIndustry").html("&nbsp;");
            }else{
                $("#mainRelateIndustry").html(mainRelateIndustry);
            }
            var viceRelateIndustryOne = response.data.result.viceRelateIndustryOne;
            if(isEmpty(viceRelateIndustryOne)){
                $("#viceRelateIndustryOne").html("&nbsp;");
            }else{
                $("#viceRelateIndustryOne").html(viceRelateIndustryOne);
            }
            var viceRelateIndustryTwo = response.data.result.viceRelateIndustryTwo;
            if(isEmpty(viceRelateIndustryTwo)){
                $("#viceRelateIndustryTwo").html("&nbsp;");
            }else{
                $("#viceRelateIndustryTwo").html(viceRelateIndustryTwo);
            }
            var viceRelateIndustryThree = response.data.result.viceRelateIndustryThree;
            if(isEmpty(viceRelateIndustryThree)){
                $("#viceRelateIndustryThree").html("&nbsp;");
            }else{
                $("#viceRelateIndustryThree").html(viceRelateIndustryThree);
            }
            var viceRelateIndustryFour = response.data.result.viceRelateIndustryFour;
            if(isEmpty(viceRelateIndustryFour)){
                $("#viceRelateIndustryFour").html("&nbsp;");
            }else{
                $("#viceRelateIndustryFour").html(viceRelateIndustryFour);
            }
            var viceRelateIndustryFive = response.data.result.viceRelateIndustryFive;
            if(isEmpty(viceRelateIndustryFive)){
                $("#viceRelateIndustryFive").html("&nbsp;");
            }else{
                $("#viceRelateIndustryFive").html(viceRelateIndustryFive);
            }
            var mainProduceProduct = response.data.result.mainProduceProduct;
            if(isEmpty(mainProduceProduct)){
                $("#mainProduceProduct").html("&nbsp;");
            }else{
                $("#mainProduceProduct").html(mainProduceProduct);
            }
            var viceProduceProductOne = response.data.result.viceProduceProductOne;
            if(isEmpty(viceProduceProductOne)){
                $("#viceProduceProductOne").html("&nbsp;");
            }else{
                $("#viceProduceProductOne").html(viceProduceProductOne);
            }
            var viceProduceProductTwo = response.data.result.viceProduceProductTwo;
            if(isEmpty(viceProduceProductTwo)){
                $("#viceProduceProductTwo").html("&nbsp;");
            }else{
                $("#viceProduceProductTwo").html(viceProduceProductTwo);
            }
            var viceProduceProductThree = response.data.result.viceProduceProductThree;
            if(isEmpty(viceProduceProductThree)){
                $("#viceProduceProductThree").html("&nbsp;");
            }else{
                $("#viceProduceProductThree").html(viceProduceProductThree);
            }
            $("#viceProduceProductFour").html(response.data.result.viceProduceProductFour);
            var viceProduceProductFour = response.data.result.viceProduceProductFour;
            if(isEmpty(viceProduceProductFour)){
                $("#viceProduceProductFour").html("&nbsp;");
            }else{
                $("#viceProduceProductFour").html(viceProduceProductFour);
            }
            var viceProduceProductFive = response.data.result.viceProduceProductFive;
            if(isEmpty(viceProduceProductFive)){
                $("#viceProduceProductFive").html("&nbsp;");
            }else{
                $("#viceProduceProductFive").html(viceProduceProductFive);
            }
            var viceProduceProductSix = response.data.result.viceProduceProductSix;
            if(isEmpty(viceProduceProductSix)){
                $("#viceProduceProductSix").html("&nbsp;");
            }else{
                $("#viceProduceProductSix").html(viceProduceProductSix);
            }
            var viceProduceProductSeven = response.data.result.viceProduceProductSeven;
            if(isEmpty(viceProduceProductSeven)){
                $("#viceProduceProductSeven").html("&nbsp;");
            }else{
                $("#viceProduceProductSeven").html(viceProduceProductSeven);
            }
            var businessArea = "";
            $.each(response.data.result.businessArea.split(","), function(i, n){
                if(n==1){
                    businessArea += "华中地区，"
                }
                if(n==2){
                    businessArea += "华东地区，"
                }
                if(n==3){
                    businessArea += "华北地区，"
                }
                if(n==4){
                    businessArea += "华南地区，"
                }
                if(n==5){
                    businessArea += "东北地区，"
                }
                if(n==6){
                    businessArea += "西北地区，"
                }
                if(n==7){
                    businessArea += "西南地区"
                }
            });
            $("#businessArea").html(businessArea.substr(0,businessArea.length-1));
        })
    }

    var pieChart = echarts.init(document.getElementById('companyProductPie'));
    pieOption = {
        title : {
            text: '南丁格尔玫瑰图',
            subtext: '纯属虚构',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            x : 'center',
            y : 'bottom',
            data:['rose1','rose2','rose3','rose4','rose5','rose6','rose7','rose8']
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {
                    show: true,
                    type: ['pie', 'funnel']
                },
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        series : [
            {
                name:'面积模式',
                type:'pie',
                radius : [30, 110],
                center : ['15%', 200],
                roseType : 'area',
                // x: '50%',               // for funnel
                max: 40,                // for funnel
                sort : 'ascending',     // for funnel
                data:[
                    {value:10, name:'rose1'},
                    {value:5, name:'rose2'},
                    {value:15, name:'rose3'},
                    {value:25, name:'rose4'},
                    {value:20, name:'rose5'},
                    {value:35, name:'rose6'},
                    {value:30, name:'rose7'},
                    {value:40, name:'rose8'}
                ]
            },
            {
                name:'面积模式',
                type:'pie',
                radius : [30, 110],
                center : ['50%', 200],
                roseType : 'area',
                x: '50%',               // for funnel
                max: 40,                // for funnel
                sort : 'ascending',     // for funnel
                data:[
                    {value:10, name:'rose1'},
                    {value:5, name:'rose2'},
                    {value:15, name:'rose3'},
                    {value:25, name:'rose4'},
                    {value:20, name:'rose5'},
                    {value:35, name:'rose6'},
                    {value:30, name:'rose7'},
                    {value:40, name:'rose8'}
                ]
            },
            {
                name:'面积模式',
                type:'pie',
                radius : [30, 110],
                center : ['85%', 200],
                roseType : 'area',
                x: '50%',               // for funnel
                max: 40,                // for funnel
                sort : 'ascending',     // for funnel
                data:[
                    {value:10, name:'rose1'},
                    {value:5, name:'rose2'},
                    {value:15, name:'rose3'},
                    {value:25, name:'rose4'},
                    {value:20, name:'rose5'},
                    {value:35, name:'rose6'},
                    {value:30, name:'rose7'},
                    {value:40, name:'rose8'}
                ]
            }
        ]
    };
    pieChart.setOption(pieOption);

    var barChart = echarts.init(document.getElementById('companyProductBar'));
    barOption = {
        title : {
            text: 'ECharts2 vs ECharts1',
            subtext: 'Chrome下测试数据'
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:[
                'ECharts1 - 2k数据','ECharts1 - 2w数据','ECharts1 - 20w数据','',
                'ECharts2 - 2k数据','ECharts2 - 2w数据','ECharts2 - 20w数据'
            ]
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        grid: {y: 70, y2:30, x2:20},
        xAxis : [
            {
                type : 'category',
                data : ['Line','Bar','Scatter','K','Map']
            },
            {
                type : 'category',
                axisLine: {show:false},
                axisTick: {show:false},
                axisLabel: {show:false},
                splitArea: {show:false},
                splitLine: {show:false},
                data : ['Line','Bar','Scatter','K','Map']
            }
        ],
        yAxis : [
            {
                type : 'value',
                axisLabel:{formatter:'{value} ms'}
            }
        ],
        series : [
            {
                name:'ECharts2 - 2k数据',
                type:'bar',
                itemStyle: {normal: {color:'rgba(193,35,43,1)', label:{show:true}}},
                data:[40,155,95,75, 0]
            },
            {
                name:'ECharts2 - 2w数据',
                type:'bar',
                itemStyle: {normal: {color:'rgba(181,195,52,1)', label:{show:true,textStyle:{color:'#27727B'}}}},
                data:[100,200,105,100,156]
            },
            {
                name:'ECharts2 - 20w数据',
                type:'bar',
                itemStyle: {normal: {color:'rgba(252,206,16,1)', label:{show:true,textStyle:{color:'#E87C25'}}}},
                data:[906,911,908,778,0]
            },
            {
                name:'ECharts1 - 2k数据',
                type:'bar',
                xAxisIndex:1,
                itemStyle: {normal: {color:'rgba(193,35,43,0.5)', label:{show:true,formatter:function(p){return p.value > 0 ? (p.value +'\n'):'';}}}},
                data:[96,224,164,124,0]
            },
            {
                name:'ECharts1 - 2w数据',
                type:'bar',
                xAxisIndex:1,
                itemStyle: {normal: {color:'rgba(181,195,52,0.5)', label:{show:true}}},
                data:[491,2035,389,955,347]
            },
            {
                name:'ECharts1 - 20w数据',
                type:'bar',
                xAxisIndex:1,
                itemStyle: {normal: {color:'rgba(252,206,16,0.5)', label:{show:true,formatter:function(p){return p.value > 0 ? (p.value +'+'):'';}}}},
                data:[3000,3000,2817,3000,0]
            }
        ]
    };
    barChart.setOption(barOption);

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
                    return '<button class="btn btn-default btn-xs" onclick="view(\'' + row.id + '\')">查看</button>&nbsp;'
                        + '<button class="btn btn-primary btn-xs" onclick="edit(\'' + row.id + '\')">修改</button>';
                }
            }
        ]
    });

    $("#addCompanyProductData").click(function () {
        $('#companyProductModal').on('show.bs.modal',function() {
            // $("#id").val("");
            // $("#english").val("");
            // $("#pronunciation").val("");
            // $("#chinese").val("");
            // $("#level").val("A");
            // $("#sentence").val("");
            // $("[name='master']").removeAttr("checked");
            // $("[name='master'][value='1']").attr("checked", true);
        });
    });
});
