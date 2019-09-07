$(function () {
    getPointIndexList(this,"xgzb");
});

function getPointIndexList(obj ,parentCode) {
    var thisObj=$(obj);
    var idStr = thisObj.parent().attr("id");
    thisObj.parent().find(".list-group-item").removeClass("active");
    var str = "";
    $.APIPost("/api/stockChose/getStockChoseClassList?parentCode=" + parentCode,function (data) {
        if(data.success) {
            $.each(data.data.result ,function (index ,value) {
                str += '<li class="list-group-item" onclick="getPointIndexList(this ,\''+ value.classCode +'\')">' + value.className + '</li>';
            });
        } else {
            showFailedAlert(data.message);
        }
    });
    if(isEmpty(idStr)){
        $("#list_1").html(str);
    }else{
        var num = parseInt(idStr.substr(5,6));
        if(num == 1){
            $("#list_2").html("");
            $("#list_3").html("");
            $("#list_4").html("");
        }
        if(num == 2){
            $("#list_3").html("");
            $("#list_4").html("");
        }

        idStr = "list_" + (parseInt(idStr.substr(5,6)) + 1);
        $("#" + idStr).html(str);
    }
    thisObj.addClass("active");
}