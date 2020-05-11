$(function () {

    var arrs = [];
    $.each(classList,function(key,value){
        var firstSector = value.firstSector;
        var just = false ;
        $.each(arrs,function(key2,value2){
            if(firstSector == value2){
                just = true ;
            }
        });

        if(!just){
            arrs.push(firstSector);
        }

    });

    var firstSectorOption = '';
    $.each(arrs,function(key,value){
        firstSectorOption += '<option value="'+ value +'">'+ value +'</option>';
    });

    $("#first_sector").html(firstSectorOption);


    $("#first_sector").change(function () {
        var firstSector = $(this).val();
        var arrs2 = [];
        $.each(classList,function(key,value){
            if(value.firstSector == firstSector){
                var secondSector = value.secondSector;
                var just = false ;
                $.each(arrs2,function(key2,value2){
                    if(secondSector == value2){
                        just = true ;
                    }
                });

                if(!just){
                    arrs2.push(secondSector);
                }
            }
        });
        var secondSectorOption = '';
        $.each(arrs2,function(key,value){
            secondSectorOption += '<option value="'+ value +'">'+ value +'</option>';
        });

        $("#second_sector").html(secondSectorOption);
        $("#third_sector").html( '');
        $("#forth_sector").html( '');
    });

    $("#second_sector").change(function () {
        var firstSector = $("#first_sector").val();
        var secondSector = $(this).val();
        var arrs2 = [];
        $.each(classList,function(key,value){
            if(value.firstSector == firstSector && value.secondSector == secondSector){
                var thirdSector = value.thirdSector;
                var just = false ;
                $.each(arrs2,function(key2,value2){
                    if(thirdSector == value2){
                        just = true ;
                    }
                });

                if(!just){
                    arrs2.push(thirdSector);
                }
            }
        });
        var thirdSectorOption = '';
        $.each(arrs2,function(key,value){
            thirdSectorOption += '<option value="'+ value +'">'+ value +'</option>';
        });

        $("#third_sector").html(thirdSectorOption);
        $("#forth_sector").html( '');
    });

    $("#third_sector").change(function () {
        var firstSector = $("#first_sector").val();
        var secondSector = $("#second_sector").val();
        var thirdSector = $(this).val();
        var arrs2 = [];
        $.each(classList,function(key,value){
            if(value.firstSector == firstSector && value.secondSector == secondSector && value.thirdSector == thirdSector){
                var forthSector = value.forthSector;
                var just = false ;
                $.each(arrs2,function(key2,value2){
                    if(forthSector == value2){
                        just = true ;
                    }
                });

                if(!just){
                    arrs2.push(forthSector);
                }
            }
        });
        var forthSectorOption = '';
        $.each(arrs2,function(key,value){
            forthSectorOption += '<option value="'+ value +'">'+ value +'</option>';
        });

        $("#forth_sector").html(forthSectorOption);
    });



    loanSearchData('01_' , '' , '');
    loanSearchData('02_' , '' , '');
    loanSearchData('03_' , '' , '');
    loanSearchData('04_' , '' , '');
    loanSearchData('05_' , '' , '');
    loanSearchData('06_' , '' , '');
    loanSearchData('07_' , '' , '');
    loanSearchData('08_' , '' , '');
    loanSearchData('09_' , '' , '');
    loanSearchData('10_' , '' , '');

    $("#searchDataButton").click(function () {
        var sectorTypes = '';
        $("input[name='sectorType']:checked").each(function (index, item) {
            if(this.checked){
                sectorTypes += $(this).val() + ','
            }
        });

        var sectorFocus = '';
        $("input[name='sectorFocus']:checked").each(function (index, item) {
            if(this.checked){
                sectorFocus = $(this).val()
            }
        });

        sectorTypes = sectorTypes.substr(0,sectorTypes.length - 1);
        loanSearchData('01_' , sectorTypes , sectorFocus);
        loanSearchData('02_' , sectorTypes , sectorFocus);
        loanSearchData('03_' , sectorTypes , sectorFocus);
        loanSearchData('04_' , sectorTypes , sectorFocus);
        loanSearchData('05_' , sectorTypes , sectorFocus);
        loanSearchData('06_' , sectorTypes , sectorFocus);
        loanSearchData('07_' , sectorTypes , sectorFocus);
        loanSearchData('08_' , sectorTypes , sectorFocus);
        loanSearchData('09_' , sectorTypes , sectorFocus);
        loanSearchData('10_' , sectorTypes , sectorFocus);
    });


    $("#submitDataButton").click(function () {
        var parent_sector = $("#parent_sector").val();
        var level_id = $("#level_id").val();
        var first_sector = $("#first_sector").val();
        var second_sector = $("#second_sector").val();
        var third_sector = $("#third_sector").val();
        var forth_sector = $("#forth_sector").val();
        var five_sector = $("#five_sector").val();
        var sector_type = $("#sector_type").val();
        var type_order = $("#type_order").val();
        var sector_focus = $("#sector_focus").val();

        if(isEmpty(third_sector)){
            window.parent.showWarningAlert("三级行业不能为空！");
            return ;
        }
        if(isEmpty(forth_sector)){
            window.parent.showWarningAlert("四级行业不能为空！");
            return ;
        }
        if(isEmpty(five_sector)){
            window.parent.showWarningAlert("五级行业不能为空！");
            return ;
        }

        $.APIPost("/api/stock/editStockSectorLevel",JSON.stringify({
            id : level_id ,
            firstSector : first_sector ,
            secondSector : second_sector ,
            thirdSector : third_sector ,
            forthSector : forth_sector ,
            fiveSector : five_sector,
            sectorType : sector_type ,
            typeOrder : type_order ,
            sectorFocus : sector_focus
        }) ,function (data) {
            if(data.success){
                hideModal("myModal");
                window.parent.showSuccessAlert(data.message,function () {
                    var sectorTypes = '';
                    $("input[name='sectorType']:checked").each(function (index, item) {
                        if(this.checked){
                            sectorTypes += $(this).val() + ','
                        }
                    });

                    var sectorFocus = '';
                    $("input[name='sectorFocus']:checked").each(function (index, item) {
                        if(this.checked){
                            sectorFocus = $(this).val()
                        }
                    });
                    sectorTypes = sectorTypes.substr(0,sectorTypes.length - 1);
                    if(parent_sector == first_sector){
                        if(parent_sector.indexOf('01_') > -1){
                            loanSearchData('01_' , sectorTypes , sectorFocus);
                        }
                        if(parent_sector.indexOf('02_') > -1){
                            loanSearchData('02_' , sectorTypes , sectorFocus);
                        }
                        if(parent_sector.indexOf('03_') > -1){
                            loanSearchData('03_' , sectorTypes , sectorFocus);
                        }
                        if(parent_sector.indexOf('04_') > -1){
                            loanSearchData('04_' , sectorTypes , sectorFocus);
                        }
                        if(parent_sector.indexOf('05_') > -1){
                            loanSearchData('05_' , sectorTypes , sectorFocus);
                        }
                        if(parent_sector.indexOf('06_') > -1){
                            loanSearchData('06_' , sectorTypes , sectorFocus);
                        }
                        if(parent_sector.indexOf('07_') > -1){
                            loanSearchData('07_' , sectorTypes , sectorFocus);
                        }
                        if(parent_sector.indexOf('08_') > -1){
                            loanSearchData('08_' , sectorTypes , sectorFocus);
                        }
                        if(parent_sector.indexOf('09_') > -1){
                            loanSearchData('09_' , sectorTypes , sectorFocus);
                        }
                        if(parent_sector.indexOf('10_') > -1){
                            loanSearchData('10_' , sectorTypes , sectorFocus);
                        }
                    }else{
                        if(parent_sector.indexOf('01_') > -1){
                            loanSearchData('01_' , sectorTypes , sectorFocus);
                        }
                        if(parent_sector.indexOf('02_') > -1){
                            loanSearchData('02_' , sectorTypes , sectorFocus);
                        }
                        if(parent_sector.indexOf('03_') > -1){
                            loanSearchData('03_' , sectorTypes , sectorFocus);
                        }
                        if(parent_sector.indexOf('04_') > -1){
                            loanSearchData('04_' , sectorTypes , sectorFocus);
                        }
                        if(parent_sector.indexOf('05_') > -1){
                            loanSearchData('05_' , sectorTypes , sectorFocus);
                        }
                        if(parent_sector.indexOf('06_') > -1){
                            loanSearchData('06_' , sectorTypes , sectorFocus);
                        }
                        if(parent_sector.indexOf('07_') > -1){
                            loanSearchData('07_' , sectorTypes , sectorFocus);
                        }
                        if(parent_sector.indexOf('08_') > -1){
                            loanSearchData('08_' , sectorTypes , sectorFocus);
                        }
                        if(parent_sector.indexOf('09_') > -1){
                            loanSearchData('09_' , sectorTypes , sectorFocus);
                        }
                        if(parent_sector.indexOf('10_') > -1){
                            loanSearchData('10_' , sectorTypes , sectorFocus);
                        }

                        if(first_sector.indexOf('01_') > -1){
                            loanSearchData('01_' , sectorTypes , sectorFocus);
                        }
                        if(first_sector.indexOf('02_') > -1){
                            loanSearchData('02_' , sectorTypes , sectorFocus);
                        }
                        if(first_sector.indexOf('03_') > -1){
                            loanSearchData('03_' , sectorTypes , sectorFocus);
                        }
                        if(first_sector.indexOf('04_') > -1){
                            loanSearchData('04_' , sectorTypes , sectorFocus);
                        }
                        if(first_sector.indexOf('05_') > -1){
                            loanSearchData('05_' , sectorTypes , sectorFocus);
                        }
                        if(first_sector.indexOf('06_') > -1){
                            loanSearchData('06_' , sectorTypes , sectorFocus);
                        }
                        if(first_sector.indexOf('07_') > -1){
                            loanSearchData('07_' , sectorTypes , sectorFocus);
                        }
                        if(first_sector.indexOf('08_') > -1){
                            loanSearchData('08_' , sectorTypes , sectorFocus);
                        }
                        if(first_sector.indexOf('09_') > -1){
                            loanSearchData('09_' , sectorTypes , sectorFocus);
                        }
                        if(first_sector.indexOf('10_') > -1){
                            loanSearchData('10_' , sectorTypes , sectorFocus);
                        }
                    }
                });
            }else{
                window.parent.showFailedAlert(data.message);
            }
        })
    });
})


function loanSearchData(firstSector , sectorTypes , sectorFocus) {
    $.APIPost("/api/stock/getMyStockCompanySectorList?firstSector="+ firstSector +"&sectorTypes=" + sectorTypes + "&sectorFocus=" + sectorFocus,function (res) {
        var str = '';
        $.each(res,function(key,values){
            str += '<div class="panel panel-default"><div class="panel-heading"  data-toggle="collapse" data-parent="#'+ firstSector +'accordion" href="#'+ key +'" style="cursor:pointer;"><h4 class="panel-title">'+ key +'</h4></div><div id="'+ key +'" class="panel-collapse collapse"><div class="panel-body" style="padding: 0;">';
            str += '<table class="table table-bordered" style="margin: 0;">';
            str += '<thead><tr>';
            str += '<th style="text-align: center;width: 7%;">行业1</th>';
            str += '<th style="text-align: center;width: 7%;">行业2</th>';
            str += '<th style="text-align: center;width: 10%;">热点</th>';
            str += '<th style="text-align: center;width: 4%;">类型</th>';
            str += '<th style="text-align: center;width: 4%;">序列</th>';
            str += '<th style="text-align: center;width: 5%;">编码</th>';
            str += '<th style="text-align: center;width: 6%;">名称</th>';
            str += '<th style="text-align: center;width: 7%;">上市时间</th>';
            str += '<th style="text-align: center;width: 5%;">总利润</th>';
            str += '<th style="text-align: center;width: 5%;">净利润</th>';
            str += '<th style="text-align: center;width: 5%;">利润率</th>';
            str += '<th style="text-align: center;width: 6%;">每股收益</th>';
            str += '<th style="text-align: center;width: 6%;">流通股本</th>';
            str += '<th style="text-align: center;width: 5%;">总股本</th>';
            str += '<th style="text-align: center;width: 5%;">价格</th>';
            str += '<th>业务营收比例</th>';
            str += '</tr></thead>';
            str += '<tbody>';
            $.each(values,function(key2,values2){
                var keys = key2.split('_');

                str += '<tr>';
                str += '<td rowspan="'+ keys[2]  +'" style="vertical-align: middle;">'+ keys[0] + '_' + keys[1] +'</td>';

                $.each(values2,function(key3,values3){
                    var just = 0 ;
                    $(values3).each(function(index,data){
                        var sectorType = '';
                        var colorTd = '';
                        var colorA = '';
                        switch (data.sectorType){
                            case 6 :
                                sectorType = '巨头';
                                colorTd = '<td style="background: #A000AF;color: #F2F2F2;">';
                                colorA = '<a href="#" style="cursor: pointer;color:#F2F2F2; " data-toggle="modal" data-target="#myModal" onclick="edit(\'' + data.levelId + '\')">';
                                break;
                            case 5 :
                                sectorType = '龙头';
                                colorTd = '<td style="background: #ED0000;color: #F2F2F2;">';
                                colorA = '<a href="#" style="cursor: pointer;color:#F2F2F2; " data-toggle="modal" data-target="#myModal" onclick="edit(\'' + data.levelId + '\')">';
                                break;
                            case 4 :
                                sectorType = '绩优';
                                colorTd = '<td style="background:#F0F000;">';
                                colorA = '<a href="#" style="cursor: pointer;" data-toggle="modal" data-target="#myModal" onclick="edit(\'' + data.levelId + '\')">';
                                break;
                            case 3 :
                                sectorType = '超微';
                                colorTd = '<td style="background:#00B3FF;color: #F2F2F2;">';
                                colorA = '<a href="#" style="cursor: pointer;color:#F2F2F2; " data-toggle="modal" data-target="#myModal" onclick="edit(\'' + data.levelId + '\')">';
                                break;
                            case 2 :
                                sectorType = '待选';
                                colorTd = '<td>';
                                colorA = '<a href="#" style="cursor: pointer;color:rgb(51, 51, 51); " data-toggle="modal" data-target="#myModal" onclick="edit(\'' + data.levelId + '\')">';
                                break;
                            case 1 :
                                sectorType = '极差';
                                colorTd = '<td style="background:#929292">';
                                colorA = '<a href="#" style="cursor: pointer;color:#F2F2F2; " data-toggle="modal" data-target="#myModal" onclick="edit(\'' + data.levelId + '\')">';
                                break;
                            default :
                                colorTd = '<td>';
                                break;
                        }


                        if(just == 0){
                            str += '<td rowspan="'+ values3.length  +'" style="vertical-align: middle;">'+ key3 +'</td>';
                        }
                        str += '<td>' + data.stockMarketName +'</td>';
                        str += colorTd + sectorType +'</td>';
                        str += colorTd + 'NO.'+ data.typeOrder +'</td>';
                        str += colorTd + data.stockCode +'</td>';
                        str += colorTd + colorA +  data.stockName +'</a></td>';
                        str += colorTd + data.publishTime +'</td>';
                        str += colorTd + data.mainBusinessProfit +'</td>';
                        str += colorTd + data.pureBusinessProfit +'</td>';
                        str += colorTd + data.profitRate +'%</td>';
                        str += colorTd + data.perStockProfit +'</td>';
                        str += colorTd + data.flowStockCount +'</td>';
                        str += colorTd + data.totalStockCount +'</td>';
                        str += colorTd + data.closePrice +'</td>';
                        str += colorTd + data.productRevenueRate +'</td>';
                        str += '</tr>';

                        just += 1 ;
                    });
                });

            });



            str += '</tbody></table>';
            str += '</div></div></div>';
        });
        $('#' + firstSector + 'accordion').html(str);
    });
}


function edit(levelId) {
    $.APIPost("/api/stock/getStockSectorLevelList?levelId="+ levelId ,function (res) {
            if(res.success){
                $("#level_id").val(levelId);

                var firstSector = res.data.result.firstSector;
                var secondSector = res.data.result.secondSector;
                var thirdSector = res.data.result.thirdSector;
                var forthSector = res.data.result.forthSector;
                var fiveSector = res.data.result.fiveSector;
                $("#first_sector").val(firstSector);

                $("#parent_sector").val(firstSector);
                var arrs2 = [];
                $.each(classList,function(key,value){
                    if(value.firstSector == firstSector){
                        var secondSector1 = value.secondSector;
                        var just = false ;
                        $.each(arrs2,function(key2,value2){
                            if(secondSector1 == value2){
                                just = true ;
                            }
                        });

                        if(!just){
                            arrs2.push(secondSector1);
                        }
                    }
                });
                var secondSectorOption = '';
                $.each(arrs2,function(key,value){
                    secondSectorOption += '<option value="'+ value +'">'+ value +'</option>';
                });

                $("#second_sector").html(secondSectorOption);
                $("#second_sector").val(secondSector);

                var arrs3 = [];
                $.each(classList,function(key,value){
                    if(value.firstSector == firstSector && value.secondSector == secondSector){
                        var thirdSector1 = value.thirdSector;
                        var just = false ;
                        $.each(arrs3,function(key2,value2){
                            if(thirdSector1 == value2){
                                just = true ;
                            }
                        });

                        if(!just){
                            arrs3.push(thirdSector1);
                        }
                    }
                });
                var thirdSectorOption = '';
                $.each(arrs3,function(key,value){
                    thirdSectorOption += '<option value="'+ value +'">'+ value +'</option>';
                });

                $("#third_sector").html(thirdSectorOption);
                $("#third_sector").val(thirdSector);


                var arrs4 = [];
                $.each(classList,function(key,value){
                    if(value.firstSector == firstSector && value.secondSector == secondSector && value.thirdSector == thirdSector){
                        var forthSector1 = value.forthSector;
                        var just = false ;
                        $.each(arrs4,function(key2,value2){
                            if(forthSector1 == value2){
                                just = true ;
                            }
                        });

                        if(!just){
                            arrs4.push(forthSector1);
                        }
                    }
                });
                var forthSectorOption = '';
                $.each(arrs4,function(key,value){
                    forthSectorOption += '<option value="'+ value +'">'+ value +'</option>';
                });

                $("#forth_sector").html(forthSectorOption);
                $("#forth_sector").val(forthSector);

                $("#five_sector").val(fiveSector);

                $("#sector_type").val(res.data.result.sectorType);
                $("#type_order").val(res.data.result.typeOrder);
                $("#sector_focus").val(res.data.result.sectorFocus);
            }
    });
}