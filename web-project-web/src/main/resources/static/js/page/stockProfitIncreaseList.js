$(function () {
    var first_1_Sector = $("#first_1_Sector").val();
    var second_1_Sector = $("#second_1_Sector").val();
    var third_1_Secotor = $("#third_1_Secotor").val();
    var forth_1_Sector = $("#forth_1_Sector").val();
    var firstCode = 0;
    var secondCode = 0;
    var thirdCode = 0;
    if(isEmpty(first_1_Sector)){
        $.APIPost("/api/stockSector/getStockSectorClassList?parentCode=0",function (data) {
            var str ='<option value="">请选择</option>' ;
            $.each(data.data.result ,function (index ,value) {
                str +='<option value="'+ value.sectorName +'" code="'+ value.sectorCode +'">' + value.sectorName + '</option>'
            });
            $("#first_sector").html(str);

        });
    }else{
        $.APIPost("/api/stockSector/getStockSectorClassList?parentCode=0",function (data) {
            var str = '<option value="">请选择</option>' ;
            $.each(data.data.result ,function (index ,value) {
                if(first_1_Sector == value.sectorName){
                    firstCode = value.sectorCode;
                    str +='<option value="'+ value.sectorName +'" code="'+ value.sectorCode +'" selected="selected">' + value.sectorName + '</option>'
                }else{
                    str +='<option value="'+ value.sectorName +'" code="'+ value.sectorCode +'">' + value.sectorName + '</option>'
                }

            });
            $("#first_sector").html(str);
        });
    }

    if(!isEmpty(second_1_Sector)){
        $.APIPost("/api/stockSector/getStockSectorClassList?parentCode=" + firstCode,function (data) {
            var str = ""
            $.each(data.data.result ,function (index ,value) {
                if(second_1_Sector == value.sectorName){
                    secondCode = value.sectorCode;
                    str +='<option value="'+ value.sectorName +'" code="'+ value.sectorCode +'" selected="selected">' + value.sectorName + '</option>'
                }else{
                    str +='<option value="'+ value.sectorName +'" code="'+ value.sectorCode +'">' + value.sectorName + '</option>'
                }

            });
            var str2 = '<option value="">请选择</option>' + str;
            $("#second_sector").html(str2);
        });
    }

    if(!isEmpty(third_1_Secotor)) {
        $.APIPost("/api/stockSector/getStockSectorClassList?parentCode=" + secondCode, function (data) {
            var str = ""
            $.each(data.data.result, function (index, value) {
                if (third_1_Secotor == value.sectorName) {
                    thirdCode = value.sectorCode;
                    str += '<option value="' + value.sectorName + '" code="' + value.sectorCode + '" selected="selected">' + value.sectorName + '</option>'
                } else {
                    str += '<option value="' + value.sectorName + '" code="' + value.sectorCode + '">' + value.sectorName + '</option>'
                }

            });
            var str2 = '<option value="">请选择</option>' + str;
            $("#third_secotor").html(str2);
        });
    }

    if(!isEmpty(forth_1_Sector)) {
        $.APIPost("/api/stockSector/getStockSectorClassList?parentCode=" + thirdCode, function (data) {
            var str = ""
            $.each(data.data.result, function (index, value) {
                if (forth_1_Sector == value.sectorName) {
                    str += '<option value="' + value.sectorName + '" code="' + value.sectorCode + '" selected="selected">' + value.sectorName + '</option>'
                } else {
                    str += '<option value="' + value.sectorName + '" code="' + value.sectorCode + '">' + value.sectorName + '</option>'
                }

            });
            var str2 = '<option value="">请选择</option>' + str;
            $("#forth_sector").html(str2);
        });
    }


    flashTable('tableList_10' , 1);
    flashTable('tableList_5_10' , 2);
    flashTable('tableList_3_5' , 3);
    flashTable('tableList_2' , 4);


    $("[data-toggle='tooltip']").tooltip();

    $("#searchDataButton").click(function () {
        flashTable('tableList_10' , 1);
        flashTable('tableList_5_10' , 2);
        flashTable('tableList_3_5' , 3);
        flashTable('tableList_2' , 4);
    });


    $("#submitDataButton").click(function () {
        var stockCode = $("#stockCode").val();
        var companyLevel = $("#companyLevel").val();
        var fiveSector = $("#fiveSector").val();
        var fiveOrder = $("#fiveOrder").val();
        var mainBusiness = $("#mainBusiness").val();
        var belongFirstSecotr = $("#belongFirstSecotr").val();
        var belongSecondSector = $("#belongSecondSector").val();
        var belongThirdSector = $("#belongThirdSector").val();
        var belongForthSector = $("#belongForthSector").val();

        var qualityArr = new Array();
        $('input[name="companyQuality"]:checked').each(function(){
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
                hideModal("myModal");
                window.parent.showSuccessAlert(data.message,function () {
                    $('#tableList').bootstrapTable('refresh');
                });
            }else{
                window.parent.showFailedAlert(data.message);
            }
        })
    });
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
                yearType: yearType ,
                firstSector: $("#first_sector").val(),
                secondSector: $("#second_sector").val(),
                thirdSecotor: $("#third_secotor").val(),
                forthSector: $("#forth_sector").val(),
                stockCode: $("#stock_code").val(),
                stockName: $("#stock_name").val(),
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
                valign: 'middle',
                formatter: function (value, row, index) {
                    return '<a class="text_link_a" href="http://'+ row.companyWebsite +'" target="view_window" data-toggle="tooltip" data-placement="top" title="'+ row.companyWebsite +'">'+ value +'</a>';
                }
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
            },{
                title: "操作",
                align: 'center',
                valign: 'middle',
                width: 20, // 定义列的宽度，单位为像素px
                formatter: function (value, row, index) {
                    var btn = "";
                    btn += '<button class="btn btn-primary btn-xs"  data-toggle="modal" data-target="#myModal" onclick="edit(\'' + row.stockCode + '\')">修改</button>';
                    btn += '<button class="btn btn-primary btn-xs"  data-toggle="modal" data-target="#myModal" onclick="view(\'' + row.stockCode + '\')">查看</button>';
                    return btn
                }
            }
        ]
    });
}


function getSelectOption(boxId , obj) {
    var thisObj=$(obj);
    var parentCode = thisObj.find("option:selected").attr("code");
    $.APIPost("/api/stockSector/getStockSectorClassList?parentCode=" + parentCode,function (data) {
        var str = ""
        $.each(data.data.result ,function (index ,value) {
            str +='<option value="'+ value.sectorName +'" code="'+ value.sectorCode +'">' + value.sectorName + '</option>'
        });
        var str2 = '<option value="">请选择</option>' + str;
        $("#" + boxId).html(str2);

    });
}

function view(stock_code) {
    var firstSector = $("#first_sector").val();
    var secondSector = $("#second_sector").val();
    var thirdSecotor = $("#third_secotor").val();
    var forthSector = $("#forth_sector").val();
    var stockCode = $("#stock_code").val();
    var stockName = $("#stock_name").val();
    location.href="/stock/stockPoolDetail?stock_code=" + stock_code
                                        + "&stockCode=" + stockCode
                                        + "&stockName=" + stockName
                                        + "&firstSector=" + firstSector
                                        + "&secondSector=" + secondSector
                                        + "&thirdSecotor=" + thirdSecotor
                                        + "&forthSector=" + forthSector
                                        + "&type=2";
}

function edit(stockCode) {
    $('#myModal').on('show.bs.modal',function() {
        $.APIPost("/api/stock/getStockCompanySector?stockCode="+stockCode ,function (data) {
            $("#stockCode").val(data.data.result.stockCode);
            $("#stockName").val(data.data.result.stockName);
            $("#forthSector").val(data.data.result.forthSector);
            $("#fiveSector").val(data.data.result.fiveSector);
            $("#mainBusiness").val(data.data.result.mainBusiness);
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
            $("#belongSecondSector").html(secondOption);
            $("#belongThirdSector").html(thirdOption);
            $("#belongForthSector").html(forthOption);


            $("#companyLevel").val(data.data.result.companyLevel);
            $("#fiveOrder").val(data.data.result.fiveOrder);
            $("#belongFirstSecotr").val(data.data.result.belongFirstSecotr);
            $("#belongSecondSector").val(data.data.result.belongSecondSector);
            $("#belongThirdSector").val(data.data.result.belongThirdSector);
            $("#belongForthSector").val(data.data.result.belongForthSector);

            var companyQuality = data.data.result.companyQuality;
            if(!isEmpty(companyQuality)){
                var str = companyQuality.split(',');
                $('input[name="companyQuality"]').each(function(){
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
                $('input[name="companyQuality"]').each(function(){
                    $(this).prop("checked",false);
                });
            }
        });
    });
}