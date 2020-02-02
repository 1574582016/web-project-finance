var arrayData = new Array();
$(function () {

    $.APIPost("/api/finance/getPlatformSectorTree",function (data) {
        var firstOption = "";
        $.each(data.data.result ,function (index ,value) {
            arrayData.push(value);
            firstOption += '<option value="' + value.text + '">' + value.text + "</option>";
        });
        $("#belongFirstSecotr").html(firstOption);
    });

    var first_1_Sector = $("#first_1_Sector").val();
    var second_1_Sector = $("#second_1_Sector").val();
    var third_1_Secotor = $("#third_1_Secotor").val();
    var forth_1_Sector = $("#forth_1_Sector").val();
    var firstCode = 0;
    var secondCode = 0;
    var thirdCode = 0;
    if(isEmpty(first_1_Sector)){
        $.APIPost("/api/stockSector/getStockSectorClassList?parentCode=0",function (data) {
            var str = ""
            $.each(data.data.result ,function (index ,value) {
                str +='<option value="'+ value.sectorName +'" code="'+ value.sectorCode +'">' + value.sectorName + '</option>'
            });
            var str2 = str + '<option value="">请选择</option>' ;
            $("#first_sector").html(str2);

        });
    }else{
        $.APIPost("/api/stockSector/getStockSectorClassList?parentCode=0",function (data) {
            var str = ""
            $.each(data.data.result ,function (index ,value) {
                if(first_1_Sector == value.sectorName){
                    firstCode = value.sectorCode;
                    str +='<option value="'+ value.sectorName +'" code="'+ value.sectorCode +'" selected="selected">' + value.sectorName + '</option>'
                }else{
                    str +='<option value="'+ value.sectorName +'" code="'+ value.sectorCode +'">' + value.sectorName + '</option>'
                }

            });
            var str2 = str + '<option value="">请选择</option>' ;
            $("#first_sector").html(str2);
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

    // $("#mark_hot").select2();
    flashTableS('tableList_S' , "");
    // flashTableS('tableList_S' , 6);
    // flashTableS('tableList_A' , 5);
    // flashTableS('tableList_B' , 4);
    // flashTableS('tableList_C' , 3);
    // flashTableS('tableList_D' , 2);
    // flashTableS('tableList_E' , 1);
    // flashTableS('tableList_G' , 9);

    $("[data-toggle='tooltip']").tooltip();

    $("#searchDataButton").click(function () {

        flashTableS('tableList_S' , "");

        // flashTableS('tableList_S' , 6);
        // flashTableS('tableList_A' , 5);
        // flashTableS('tableList_B' , 4);
        // flashTableS('tableList_C' , 3);
        // flashTableS('tableList_D' , 2);
        // flashTableS('tableList_E' , 1);
        // flashTableS('tableList_G' , 9);
    });

    function flashTableS(boxId , level) {
        $('#' + boxId ).bootstrapTable('destroy').bootstrapTable({
            url: '/api/stockPool/getStockCompanyPoolList' ,
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
                    firstSector: $("#first_sector").val(),
                    secondSector: $("#second_sector").val(),
                    thirdSecotor: $("#third_secotor").val(),
                    forthSector: $("#forth_sector").val(),
                    stockCode: $("#stock_code").val(),
                    stockName: $("#stock_name").val(),
                    companyLevel : level
                };
                return temp;
            },
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100, 500, 1000],        //可供选择的每页的行数（*）
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
                    field: 'fiveSector',
                    title: '细业',
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
                    title: '简称',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row, index) {
                        return '<a class="text_link_a" href="#" target="view_window" data-toggle="tooltip" data-placement="top" title="'+ row.mainBusiness +'">'+ value +'</a>';
                    }
                }, {
                    field: 'companyName',
                    title: '名称',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row, index) {
                        return '<a class="text_link_a" href="#" target="view_window" data-toggle="tooltip" data-placement="top" title="'+ row.groupHot +'">'+ value +'</a>';
                    }
                },{
                    field: 'companyClass',
                    title: '性质',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'companyLevel',
                    title: '市值排行',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row, index) {
                        var lev = value;
                        if(lev == 'S' || lev == 'A' || lev == 'B'){
                            return '<a class="text_link_a" href="#" style="color: red;" target="view_window" data-toggle="tooltip" data-placement="top" title="'+ row.groupIndex +'">'+ value +'</a>';
                        }else
                        if(lev == 'C'){
                            return '<a class="text_link_a" href="#" style="color: #b804ff;" target="view_window" data-toggle="tooltip" data-placement="top" title="'+ row.groupIndex +'">'+ value +'</a>';
                        }else
                        if(lev == 'D'){
                            return '<a class="text_link_a" href="#" style="color: #ffc000;" target="view_window" data-toggle="tooltip" data-placement="top" title="'+ row.groupIndex +'">'+ value +'</a>';
                        }else{
                            return '<a class="text_link_a" href="#" style="color: grey;" target="view_window" data-toggle="tooltip" data-placement="top" title="'+ row.groupIndex +'">'+ value +'</a>';
                        }
                    }
                },{
                    field: 'financialLevel',
                    title: '盈&资',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row, index) {
                        var lev = value.substr(0,1);
                        if(lev == 'S' || lev == 'A' ){
                            return '<a class="text_link_a" href="#" style="color: red;">'+ value +'</a>';
                        }else{
                            return '<a class="text_link_a" href="#" style="color: grey;">'+ value +'</a>';
                        }
                    }
                },{
                    field: 'establishDay',
                    title: '成立日期',
                    align: 'center',
                    valign: 'middle'
                },{
                    field: 'publishDay',
                    title: '上市日期',
                    align: 'center',
                    valign: 'middle'
                },{
                    field: 'spaceYear',
                    title: '上市时间',
                    align: 'center',
                    valign: 'middle'
                },{
                    field: 'publishCount',
                    title: '发行股本',
                    align: 'center',
                    valign: 'middle'
                },{
                    field: 'flowCount',
                    title: '流通股本',
                    align: 'center',
                    valign: 'middle'
                },{
                    field: 'flowRate',
                    title: '流通比例',
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
        + "&type=1";
}


function changeSecondBelongSector(obj) {
    var thisObj=$(obj);

    var secondOption = "";
    var nextText = "";
    $.each(arrayData ,function (index ,value) {
        if(value.text == thisObj.val()){
            $.each(value.nodes ,function (index2 ,value2) {
                secondOption += '<option value="' + value2.text + '">' + value2.text + "</option>";
                if(index2 == 0){
                    nextText = value2.text ;
                }
            });
        }
    });
    $("#belongSecondSector").html(secondOption);

    changeThirdBelongSector(obj , nextText);
}

function changeThirdBelongSector(obj , nextText) {
    var thisObj=$(obj);
    var belongFirstSecotr = $("#belongFirstSecotr").val();
    var thirdOption = "";
    var nextText3 = "";
    $.each(arrayData ,function (index ,value) {
        if(value.text == belongFirstSecotr){
            $.each(value.nodes ,function (index2 ,value2) {
                if(isEmpty(nextText)){
                    if(value2.text == thisObj.val()){
                        $.each(value2.nodes ,function (index3 ,value3) {
                            thirdOption += '<option value="' + value3.text + '">' + value3.text + "</option>";
                            if(index3 == 0){
                                nextText3 = value3.text ;
                            }
                        });
                    }
                }else {
                    if(value2.text == nextText){
                        $.each(value2.nodes ,function (index3 ,value3) {
                            thirdOption += '<option value="' + value3.text + '">' + value3.text + "</option>";
                            if(index3 == 0){
                                nextText3 = value3.text ;
                            }
                        });
                    }
                }

            });
        }
    });
    $("#belongThirdSector").html(thirdOption);

    changeForthBelongSector(obj , nextText3);
}

function changeForthBelongSector(obj , nextText) {
    var thisObj=$(obj);
    var belongFirstSecotr = $("#belongFirstSecotr").val();
    var belongSecondSector = $("#belongSecondSector").val();

    var forthOption = "";
    $.each(arrayData ,function (index ,value) {
        if(value.text == belongFirstSecotr){
            $.each(value.nodes ,function (index2 ,value2) {
                if(value2.text == belongSecondSector){
                    $.each(value2.nodes ,function (index3 ,value3) {
                        if(isEmpty(nextText)){
                            if(value3.text == thisObj.val()){
                                $.each(value3.nodes ,function (index4 ,value4) {
                                    forthOption += '<option value="' + value4.text + '">' + value4.text + "</option>";
                                });
                            }
                        }else {
                            if(value3.text == nextText){
                                $.each(value3.nodes ,function (index4 ,value4) {
                                    forthOption += '<option value="' + value4.text + '">' + value4.text + "</option>";
                                });
                            }
                        }
                    });
                }
            });
        }
    });
    $("#belongForthSector").html(forthOption);
}
