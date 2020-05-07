$(function () {
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
                        str += '<td>' + data.stockMarketName +'</td>';
                        str += colorTd + sectorType +'</td>';
                        str += colorTd + 'NO.'+ data.typeOrder +'</td>';
                        str += colorTd + data.stockCode +'</td>';
                        str += colorTd + data.stockName +'</td>';
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