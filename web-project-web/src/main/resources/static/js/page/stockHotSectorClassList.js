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

    var firstSectorOption = '<option value="">请选择</option>';
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
        var secondSectorOption = '<option value="">请选择</option>';
        $.each(arrs2,function(key,value){
            secondSectorOption += '<option value="'+ value +'">'+ value +'</option>';
        });

        $("#second_sector").html(secondSectorOption);
        $("#third_sector").html( '<option value="">请选择</option>');
        $("#forth_sector").html( '<option value="">请选择</option>');
        $("#five_sector").html( '<option value="">请选择</option>');
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
        var thirdSectorOption = '<option value="">请选择</option>';
        $.each(arrs2,function(key,value){
            thirdSectorOption += '<option value="'+ value +'">'+ value +'</option>';
        });

        $("#third_sector").html(thirdSectorOption);
        $("#forth_sector").html( '<option value="">请选择</option>');
        $("#five_sector").html( '<option value="">请选择</option>');
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
        var forthSectorOption = '<option value="">请选择</option>';
        $.each(arrs2,function(key,value){
            forthSectorOption += '<option value="'+ value +'">'+ value +'</option>';
        });

        $("#forth_sector").html(forthSectorOption);
        $("#five_sector").html( '<option value="">请选择</option>');
    });


    $("#forth_sector").change(function () {
        var firstSector = $("#first_sector").val();
        var secondSector = $("#second_sector").val();
        var thirdSector = $("#third_sector").val();
        var forthSector = $(this).val();
        var arrs2 = [];
        $.each(classList,function(key,value){
            if(value.firstSector == firstSector && value.secondSector == secondSector && value.thirdSector == thirdSector && value.forthSector == forthSector){
                var hotCode = value.hotCode;
                var just = false ;
                $.each(arrs2,function(key2,value2){
                    if(hotCode == value2.hotCode){
                        just = true ;
                    }
                });

                if(!just){
                    arrs2.push(value);
                }
            }
        });
        var fiveSectorOption = '<option value="">请选择</option>';
        $.each(arrs2,function(key,value){
            fiveSectorOption += '<option value="'+ value.hotCode +'">'+ value.hotName +'</option>';
        });

        $("#five_sector").html(fiveSectorOption);
    });


    $("#searchDataButton").click(function () {
        var sectorTypes = '';
        $("input[name='sectorType']:checked").each(function (index, item) {
            if(this.checked){
                sectorTypes += $(this).val() + ','
            }
        });
        sectorTypes = sectorTypes.substr(0,sectorTypes.length - 1);
        $.APIPost("/api/stock/getStockHotSectorClassList?firstSector=" + $("#first_sector").val()
                                                    + "&secondSector=" + $("#second_sector").val()
                                                    + "&thirdSector=" + $("#third_sector").val()
                                                    + "&forthSector=" + $("#forth_sector").val()
                                                    + "&fiveSector=" + $("#five_sector").val()
                                                    + "&sectorTypes=" + sectorTypes
            ,function (res) {
                $.each(res,function(key0,values0){
                    var str = '';
                    var idAppix = '';
                    if(key0.indexOf('01_') > -1){
                        idAppix = '01';
                    }
                    if(key0.indexOf('02_') > -1){
                        idAppix = '02';
                    }
                    if(key0.indexOf('03_') > -1){
                        idAppix = '03';
                    }
                    if(key0.indexOf('04_') > -1){
                        idAppix = '04';
                    }
                    if(key0.indexOf('05_') > -1){
                        idAppix = '05';
                    }
                    if(key0.indexOf('06_') > -1){
                        idAppix = '06';
                    }
                    if(key0.indexOf('07_') > -1){
                        idAppix = '07';
                    }
                    if(key0.indexOf('08_') > -1){
                        idAppix = '08';
                    }
                    if(key0.indexOf('09_') > -1){
                        idAppix = '09';
                    }
                    if(key0.indexOf('10_') > -1){
                        idAppix = '10';
                    }

                    $.each(values0,function(key,values){
                        str += '<div class="panel panel-default"><div class="panel-heading"  data-toggle="collapse" data-parent="#'+ idAppix +'_accordion" href="#'+ key +'" style="cursor:pointer;"><h4 class="panel-title">'+ key +'</h4></div><div id="'+ key +'" class="panel-collapse collapse"><div class="panel-body" style="padding: 0;">';
                        str += '<table class="table table-bordered" style="margin: 0;">';
                        str += '<thead><tr>';
                        str += '<th style="text-align: center;width: 8%;">行业1</th>';
                        str += '<th style="text-align: center;width: 8%;">行业2</th>';
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
                                    switch (data.sectorType){
                                        case 6 :
                                            sectorType = '巨头';
                                            colorTd = '<td style="background: #A000AF;color: #F2F2F2;">';
                                            break;
                                        case 5 :
                                            sectorType = '龙头';
                                            colorTd = '<td style="background: #ED0000;color: #F2F2F2;">';
                                            break;
                                        case 4 :
                                            sectorType = '绩优';
                                            colorTd = '<td style="background:#F0F000;">';
                                            break;
                                        case 3 :
                                            sectorType = '超微';
                                            colorTd = '<td style="background:#00B3FF;color: #F2F2F2;">';
                                            break;
                                        case 2 :
                                            sectorType = '待选';
                                            colorTd = '<td>';
                                            break;
                                        case 1 :
                                            sectorType = '极差';
                                            colorTd = '<td style="color:#929292">';
                                            break;
                                        default :
                                            colorTd = '<td>';
                                            break;
                                    }


                                    if(just == 0){
                                        str += '<td rowspan="'+ values3.length  +'" style="vertical-align: middle;">'+ key3 +'</td>';
                                    }
                                    str += colorTd + sectorType +'</td>';
                                    str += colorTd + 'NO.'+ data.typeOrder +'</td>';
                                    str += colorTd + data.stockCode +'</td>';
                                    str += colorTd + data.stockName +'</td>';
                                    str += colorTd + data.publishTime +'</td>';
                                    str += colorTd + data.mainBusinessProfit +'</td>';
                                    str += colorTd + data.pureBusinessProfit +'</td>';
                                    str += colorTd + data.profitRate +'</td>';
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

                        $('#' + idAppix + '_accordion').html(str);
                    });
                });


            // $("#dataBox").html(str);
        })
    });
});