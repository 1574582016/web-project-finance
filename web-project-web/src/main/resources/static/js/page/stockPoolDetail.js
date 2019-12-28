var arrayData = new Array();
$(function () {

    $.APIPost("/api/finance/getPlatformSectorTree",function (data) {
        var firstOption = "";
        $.each(data.data.result ,function (index ,value) {
            arrayData.push(value);
            firstOption += '<option value="' + value.text + '">' + value.text + "</option>";
        });
        $("#f_belongFirstSecotr").html(firstOption);
    });

    $.APIPost("/api/stockPool/stockPoolDetail?stockCode=" + $("#stock_code").val() ,function (response) {
        drawMainTable(response);
        profitGrowMain("profitGrowMain" ,"总利润"  ,response.data.result.profitMap);
        profitSeasonMain("profitSeasonMain" ,"季度利润" ,response.data.result.profitMap);

        assetGrowMain("assetGrowMain" ,"资产增长"  ,response.data.result.assetMap);

        companyMonthStatic(response.data.result.cycleMap);
    });

    $("#backButton").click(function () {
        var firstSector = $("#firstSector").val();
        var secondSector = $("#secondSector").val();
        var thirdSecotor = $("#thirdSecotor").val();
        var forthSector = $("#forthSector").val();
        var stockCode = $("#stockCode").val();
        var stockName = $("#stockName").val();
        location.href="/stock/stockPoolList?stockCode=" + stockCode
            + "&stockName=" + stockName
            + "&firstSector=" + firstSector
            + "&secondSector=" + secondSector
            + "&thirdSecotor=" + thirdSecotor
            + "&forthSector=" + forthSector;
    });

    $('#myModal').on('show.bs.modal',function() {
        $.APIPost("/api/stock/getStockCompanySector?stockCode="+$("#stock_code").val() ,function (data) {
            $("#f_stockCode").val(data.data.result.stockCode);
            $("#f_stockName").val(data.data.result.stockName);
            $("#f_forthSector").val(data.data.result.forthSector);
            $("#f_fiveSector").val(data.data.result.fiveSector);
            $("#f_mainBusiness").val(data.data.result.mainBusiness);
            // $("#companyDscr").html(data.data.result.companyIntr + data.data.result.companyProduct);

            var secondOption = "";
            var thirdOption = "";
            var forthOption = "";
            $.each(arrayData ,function (index ,value) {
                if(value.text == data.data.result.belongFirstSecotr){
                    $.each(value.nodes ,function (index2 ,value2) {
                        secondOption += '<option value="' + value2.text + '">' + value2.text + "</option>";
                        if(value2.text == data.data.result.belongSecondSector){
                            $.each(value2.nodes ,function (index3 ,value3) {
                                thirdOption += '<option value="' + value3.text + '">' + value3.text + "</option>";
                                if(value3.text == data.data.result.belongThirdSector){
                                    $.each(value3.nodes ,function (index4 ,value4) {
                                        forthOption += '<option value="' + value4.text + '">' + value4.text + "</option>";
                                    });
                                }
                            });
                        }
                    });
                }
            });
            $("#f_belongSecondSector").html(secondOption);
            $("#f_belongThirdSector").html(thirdOption);
            $("#f_belongForthSector").html(forthOption);

            $("#f_companyLevel").val(data.data.result.companyLevel);
            $("#f_fiveOrder").val(data.data.result.fiveOrder);
            $("#f_belongFirstSecotr").val(data.data.result.belongFirstSecotr);
            $("#f_belongSecondSector").val(data.data.result.belongSecondSector);
            $("#f_belongThirdSector").val(data.data.result.belongThirdSector);
            $("#f_belongForthSector").val(data.data.result.belongForthSector);

            var companyQuality = data.data.result.companyQuality;
            if(!isEmpty(companyQuality)){
                var str = companyQuality.split(',');
                $('input[name="f_companyQuality"]').each(function(){
                    console.log($(this).val());
                    var just = false ;
                    for(var i = 0 ; i < str.length ; i++){
                        var value = str[i];
                        if($(this).val() == value){
                            just = true;
                        }
                    }
                    if(just){
                        $(this).prop("checked",true);
                    }
                });
            }else{
                $('input[name="f_companyQuality"]').each(function(){
                    $(this).prop("checked",false);
                });
            }
        });
    });

    $("#submitDataButton").click(function () {
        var stockCode = $("#stock_code").val();
        var companyLevel = $("#f_companyLevel").val();
        var fiveSector = $("#f_fiveSector").val();
        var fiveOrder = $("#f_fiveOrder").val();
        var mainBusiness = $("#f_mainBusiness").val();
        var belongFirstSecotr = $("#f_belongFirstSecotr").val();
        var belongSecondSector = $("#f_belongSecondSector").val();
        var belongThirdSector = $("#f_belongThirdSector").val();
        var belongForthSector = $("#f_belongForthSector").val();

        var qualityArr = new Array();
        $('input[name="f_companyQuality"]:checked').each(function(){
            qualityArr.push($(this).val());
        });

        $.APIPost("/api/stock/editStockCompanySector",JSON.stringify({
            stockCode : stockCode ,
            companyLevel : companyLevel ,
            fiveSector : fiveSector ,
            fiveOrder : fiveOrder ,
            belongFirstSecotr : belongFirstSecotr ,
            belongSecondSector : belongSecondSector,
            belongThirdSector : belongThirdSector ,
            belongForthSector : belongForthSector ,
            mainBusiness : mainBusiness ,
            companyQuality : qualityArr.join(',')
        }) ,function (data) {
            if(data.success){
                window.top.showSuccessAlert(data.message,function () {
                    hideModal("myModal");
                });
            }else{
                window.top.showFailedAlert(data.message);
            }
        })
    });
});

function drawMainTable(response) {
    var stockCodeStr = '<a class="text_link_a" href="http://'+ response.data.result.companyWebsite +'" target="view_window">'+ response.data.result.stockCode +'</a>';
    $("#t_stockCode").html(stockCodeStr);
    $("#t_companyName").html(response.data.result.companyName);
    $("#t_companyClass").html(response.data.result.companyClass);

    $("#t_establishDay").html(response.data.result.establishDay);
    $("#t_publishDay").html(response.data.result.publishDay);
    $("#t_spaceYear").html(response.data.result.spaceYear);

    $("#t_publishCount").html(response.data.result.publishCount);
    $("#t_flowCount").html(response.data.result.flowCount);
    $("#t_flowRate").html(response.data.result.flowRate);

    $("#t_marketOrder").html(response.data.result.marketOrder);
    $("#t_sectorOrder").html(response.data.result.sectorOrder);
    $("#t_companyType").html(response.data.result.companyType);

    $("#t_companyRegion").html(response.data.result.companyRegion);
    $("#t_companyStrength").html(response.data.result.companyStrength);
    $("#t_companyChance").html(response.data.result.companyChance);

    $("#t_mainBusiness").html(response.data.result.mainBusiness);
    $("#t_marketClass").html(response.data.result.marketClass);
    $("#t_internalOrganize").html(response.data.result.internalOrganize);

    $("#t_focusOrganize").html(response.data.result.focusOrganize);
    $("#t_groupHot").html(response.data.result.groupHot);
    var num = response.data.result.rowSpan ;
    var str = "";
    for(var i = 0 ; i < num ; i ++){
        if(i == 0){
            var product = response.data.result.productList[i];
            var region = response.data.result.regionList[i];
            str += '<tr>';
            str += '<td class="my_table_title" rowspan="'+ num +'" style="border: 0 ;width: 9.9%">主营产品</td>';
            str += '<td style="border-top: 0 ;width: 30.1%;">'+ product.productName +'</td>';
            str += '<td style="border-top: 0 ;width: 10%;">'+ product.productRevenueRate +'</td>';
            str += '<td class="my_table_title" rowspan="'+ num +'" style="border: 0 ;width: 9.9%">业务区域</td>';
            str += '<td style="border-top: 0 ;border-right: 0;width: 30.1%;">'+ region.productName +'</td>';
            str += '<td style="border-top: 0 ;border-right: 0;width: 10%;">'+ region.productRevenueRate +'</td>';
            str += '</tr>';
        }else{
            if(i < response.data.result.productList.length){
                var product = response.data.result.productList[i];
                if(i < num - 1){
                    str += '<tr>';
                    str += '<td style="width: 30.1%;">'+ product.productName +'</td>';
                    str += '<td style="width: 10%;">'+ product.productRevenueRate +'</td>';
                }else{
                    str += '<tr>';
                    str += '<td style="border-bottom: 0;width: 30.1%;">'+ product.productName +'</td>';
                    str += '<td style="border-bottom: 0;width: 10%;">'+ product.productRevenueRate +'</td>';
                }

            }else {
                if(i < num - 1){
                    str += '<tr>';
                    str += '<td style="width: 30.1%;"></td>';
                    str += '<td style="width: 10%;"></td>';
                }else{
                    str += '<tr>';
                    str += '<td style="border-bottom: 0;width: 30.1%;"></td>';
                    str += '<td style="border-bottom: 0;width: 10%;"></td>';
                }
            }
            if(i < response.data.result.regionList.length){
                var region = response.data.result.regionList[i];
                if(i < num - 1){
                    str += '<td style="width: 30.1%;">'+ region.productName +'</td>';
                    str += '<td style="width: 10%;">'+ region.productRevenueRate +'</td>';
                    str += '</tr>';
                }else{
                    str += '<td style="border-bottom: 0;width: 30.1%;">'+ region.productName +'</td>';
                    str += '<td style="border-bottom: 0;width: 10%;">'+ region.productRevenueRate +'</td>';
                    str += '</tr>';
                }
            }else{

                if(i < num - 1){
                    str += '<td style="width: 30.1%;"></td>';
                    str += '<td style="width: 10%;"></td>';
                    str += '</tr>';
                }else{
                    str += '<td style="border-bottom: 0;width: 30.1%;"></td>';
                    str += '<td style="border-bottom: 0;width: 10%;"></td>';
                    str += '</tr>';
                }
            }
        }
    }
    $("#productBox").html(str);
}


function assetGrowMain(boxId ,name ,result) {
    var myChart = echarts.init(document.getElementById(boxId));
    var colors = ['#3a9ff5', '#34bd37','#e80b3e'];
    var option = {
        color: colors,
        title: {
            text: name
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['总资产','总负债','净资产']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: result.assetTitle
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'总资产',
                type:'line',
                stack: '总量0',
                data:result.totalAsset
            },{
                name:'总负债',
                type:'line',
                stack: '总量1',
                data:result.totalDebt
            },{
                name:'净资产',
                type:'line',
                stack: '总量2',
                data:result.pureAsset
            }
        ]
    };
    myChart.setOption(option);
}

function profitGrowMain(boxId ,name ,result) {
    var myChart = echarts.init(document.getElementById(boxId));
    var colors = ['#3a9ff5', '#e80b3e','#34bd37', '#d3ff24'];
    var option = {
        color: colors,
        title: {
            text: name
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['总利润','归属利润']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: result.profitTitle
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'总利润',
                type:'line',
                stack: '总量0',
                data:result.totalProfit
            },{
                name:'归属利润',
                type:'line',
                stack: '总量1',
                data:result.belongProfit
            }
        ]
    };
    myChart.setOption(option);
}

function profitSeasonMain(boxId ,name ,result) {
    var myChart = echarts.init(document.getElementById(boxId));
    var colors = ['#34bd37','#3a9ff5', '#e80b3e', '#d3ff24'];
    var option = {
        color: colors,
        title: {
            text: name
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['第一季度','第二季度','第三季度','第四季度']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: result.profitTitle
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'第一季度',
                type:'line',
                stack: '总量0',
                data:result.firstProfitRate
            },{
                name:'第二季度',
                type:'line',
                stack: '总量1',
                data:result.secondProfitRate
            },{
                name:'第三季度',
                type:'line',
                stack: '总量2',
                data:result.threeProfitRate
            },{
                name:'第四季度',
                type:'line',
                stack: '总量3',
                data:result.forthtProfitRate
            }
        ]
    };
    myChart.setOption(option);
}

function companyMonthStatic(result) {
    var myChart = echarts.init(document.getElementById('companyMonth'));
    var colors = ['#34bd37', '#e80b3e', '#3a9ff5','#d3ff24'];
    var option = {
        title : {
            text: "月度周期"
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['涨率','涨幅' ,'震幅']
        },
        toolbox: {
            show : false,
            feature : {
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'涨率',
                type:'bar',
                data:result.rateArr,
                itemStyle : {
                    normal : {
                        label: {
                            show: true,
                            position: 'top',
                            textStyle: {
                                color: 'black'
                            }
                        }
                    },
                }
            },
            {
                name:'涨幅',
                type:'bar',
                data:result.upperArr,
                itemStyle : {
                    normal : {
                        label: {
                            show: true,
                            position: 'top',
                            textStyle: {
                                color: 'black'
                            }
                        }
                    },
                }
            },
            {
                name:'震幅',
                type:'bar',
                data:result.shockArr,
                itemStyle : {
                    normal : {
                        label: {
                            show: true,
                            position: 'top',
                            textStyle: {
                                color: 'black'
                            }
                        }
                    },
                }
            }
        ]
    };
    myChart.setOption(option);
}


function changeSecondBelongSector(obj) {
    var thisObj=$(obj);

    var secondOption = "";
    $.each(arrayData ,function (index ,value) {
        if(value.text == thisObj.val()){
            $.each(value.nodes ,function (index2 ,value2) {
                secondOption += '<option value="' + value2.text + '">' + value2.text + "</option>";
            });
        }
    });
    $("#f_belongSecondSector").html(secondOption);
}

function changeThirdBelongSector(obj) {
    var thisObj=$(obj);
    var belongFirstSecotr = $("#f_belongFirstSecotr").val();

    var thirdOption = "";
    $.each(arrayData ,function (index ,value) {
        if(value.text == belongFirstSecotr){
            $.each(value.nodes ,function (index2 ,value2) {
                if(value2.text == thisObj.val()){
                    $.each(value2.nodes ,function (index3 ,value3) {
                        thirdOption += '<option value="' + value3.text + '">' + value3.text + "</option>";
                    });
                }
            });
        }
    });
    $("#f_belongThirdSector").html(thirdOption);
}

function changeForthBelongSector(obj) {
    var thisObj=$(obj);
    var belongFirstSecotr = $("#f_belongFirstSecotr").val();
    var belongSecondSector = $("#f_belongSecondSector").val();

    var forthOption = "";
    $.each(arrayData ,function (index ,value) {
        if(value.text == belongFirstSecotr){
            $.each(value.nodes ,function (index2 ,value2) {
                if(value2.text == belongSecondSector){
                    $.each(value2.nodes ,function (index3 ,value3) {
                        if(value3.text == thisObj.val()){
                            $.each(value3.nodes ,function (index4 ,value4) {
                                forthOption += '<option value="' + value4.text + '">' + value4.text + "</option>";
                            });
                        }
                    });
                }
            });
        }
    });
    $("#f_belongForthSector").html(forthOption);
}


