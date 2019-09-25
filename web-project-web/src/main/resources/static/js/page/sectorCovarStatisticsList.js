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

    flashCycleStatic();

    $("#searchDataButton").click(function () {
        flashCycleStatic();
    });
});

function flashCycleStatic() {
    $.APIPost("/api/statistics/getCovarIndexAndSector?indexCode=" + $("#index_code").val()
        + "&dealPeriod=" + $("#deal_period").val()
        + "&startDay=" + $("#s_start").val()
        + "&endDay=" + $("#s_end").val()
        ,function (result) {
            var str = "";
            $.each(result.data.result ,function (index ,value) {
                str += "<tr><td>" + value.staticCode + "</td><td>" + value.staticName + "</td><td>" + value.upperRelevant + "</td></tr>";
            })
            $("#dataBox").html(str);
        });
}