$(function () {
    loanSearchData('01_' , '');
    loanSearchData('02_' , '');
    loanSearchData('03_' , '');
    loanSearchData('04_' , '');
    loanSearchData('05_' , '');
    loanSearchData('06_' , '');
    loanSearchData('07_' , '');
    loanSearchData('08_' , '');
    loanSearchData('09_' , '');
    loanSearchData('10_' , '');

    $("#searchDataButton").click(function () {
        var sectorTypes = '';
        $.each($('input:checkbox'),function(){
            if(this.checked){
                sectorTypes += $(this).val() + ','
            }
        });
        sectorTypes = sectorTypes.substr(0,sectorTypes.length - 1);
        loanSearchData('01_' ,sectorTypes);
        loanSearchData('02_' , sectorTypes);
        loanSearchData('03_' , sectorTypes);
        loanSearchData('04_' ,sectorTypes);
        loanSearchData('05_' , sectorTypes);
        loanSearchData('06_' , sectorTypes);
        loanSearchData('07_' , sectorTypes);
        loanSearchData('08_' , sectorTypes);
        loanSearchData('09_' , sectorTypes);
        loanSearchData('10_' , sectorTypes);
    });
})


function loanSearchData(firstSector , sectorTypes) {
    $.APIPost("/api/stock/getMyStockCompanySectorList?firstSector="+ firstSector +"&sectorTypes=" + sectorTypes,function (res) {
        var str = '';
        $.each(res,function(key,values){
            str += '<div class="panel panel-default"><div class="panel-heading"  data-toggle="collapse" data-parent="#01_accordion" href="#'+ key +'" style="cursor:pointer;"><h4 class="panel-title">'+ key +'</h4></div><div id="'+ key +'" class="panel-collapse collapse"><div class="panel-body" style="padding: 0;">';
            str += '<table class="table table-bordered" style="margin: 0;">';
            str += '<thead><tr>';
            str += '<th style="width: 8%;">行业1</th>';
            str += '<th style="width: 8%;">行业2</th>';
            str += '<th style="width: 4%;">类型</th>';
            str += '<th style="width: 4%;">序列</th>';
            str += '<th style="width: 5%;">编码</th>';
            str += '<th style="width: 6%;">名称</th>';
            str += '<th style="width: 7%;">上市时间</th>';
            str += '<th style="width: 5%;">总利润</th>';
            str += '<th style="width: 5%;">净利润</th>';
            str += '<th style="width: 5%;">利润率</th>';
            str += '<th style="width: 6%;">每股收益</th>';
            str += '<th style="width: 6%;">流通股本</th>';
            str += '<th style="width: 5%;">总股本</th>';
            str += '<th>业务营收比例</th>';
            str += '</tr></thead>';
            str += '<tbody>';
            $(values).each(function(index,data){
                var sectorType = '';
                var colorTr = '';
                switch (data.sectorType){
                    case 6 :
                        sectorType = '巨头';
                        colorTr = '<tr style="background: deepskyblue">';
                        break;
                    case 5 :
                        sectorType = '龙头';
                        colorTr = '<tr style="background: red">';
                        break;
                    case 4 :
                        sectorType = '绩优';
                        colorTr = '<tr style="background:yellow">';
                        break;
                    case 3 :
                        sectorType = '超微';
                        colorTr = '<tr style="background:lawngreen">';
                        break;
                    case 2 :
                        sectorType = '待选';
                        colorTr = '<tr>';
                        break;
                    case 1 :
                        sectorType = '极差';
                        colorTr = '<tr style="color:grey">';
                        break;
                }

                str += colorTr;
                str += '<td>'+ data.thirdSector +'</td>';
                str += '<td>'+ data.forthSector +'</td>';
                str += '<td>'+ sectorType +'</td>';
                str += '<td>NO.'+ data.typeOrder +'</td>';
                str += '<td>'+ data.stockCode +'</td>';
                str += '<td>'+ data.stockName +'</td>';
                str += '<td>'+ data.publishTime +'</td>';
                str += '<td>'+ data.mainBusinessProfit +'</td>';
                str += '<td>'+ data.pureBusinessProfit +'</td>';
                str += '<td>'+ data.profitRate +'</td>';
                str += '<td>'+ data.perStockProfit +'</td>';
                str += '<td>'+ data.flowStockCount +'</td>';
                str += '<td>'+ data.totalStockCount +'</td>';
                str += '<td>'+ data.productRevenueRate +'</td>';
                str += '</tr>';
            });
            str += '</tbody></table>';
            str += '</div></div></div>';
        });
        // console.log(str);
        $('#' + firstSector + 'accordion').html(str);
    });
}