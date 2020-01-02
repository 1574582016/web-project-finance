
$(function () {

    var id = $("#id").val();
    if(!isEmpty(id)){
        $.APIPost("/api/invest/getInvestForexReplay?id="+id ,function (response) {

            $("#replayTime").html(response.data.result.replayTime);
            $("#investStrategy").html(response.data.result.investStrategy);
            $("#currencyPairs").html(response.data.result.currencyPairs);
            $("#timeCycle").html(response.data.result.timeCycle);
            var str = "";
            $.each(response.data.result.detailList ,function (index ,value) {
                str += '<div class="form-group">'
                    +'<div class="col-sm-1 line-input-left" style="text-align: center;">' + (index + 1) +'&nbsp;行</div>'
                    +'<div class="col-sm-1 line-input-center form-input-show" style="text-align: center;">' + value.investType +'</div>'
                    +'<div class="col-sm-2 line-input-center form-input-show" style="text-align: center;">' + (value.startTime).substr(0,10) +'</div>'
                    +'<div class="col-sm-2 line-input-center form-input-show" style="text-align: center;">' + (value.endTime).substr(0,10) +'</div>'
                    +'<div class="col-sm-1 line-input-center form-input-show" style="text-align: center;">' + value.lineNumber +'</div>'
                    +'<div class="col-sm-1 line-input-center form-input-show" style="text-align: center;">' + value.gainPoint +'</div>'
                    +'<div class="col-sm-4 line-input-right form-input-show" style="text-align: center;">' + value.remark +'</div>'
                    +'</div>';
            });
            $("#lineGroupBox").html(str);
        })
    }

    $("#resetFormButton").click(function () {
        backLastBread();
        location.href="/invest/investForexReplayList";
    });



});

function createLineInputGroup(line) {
    var str = "";
    for(var i = 1 ; i <= line ; i ++){
        str += '<div class="form-group">'
            +'<div class="col-sm-1 line-input-left" STYLE="text-align: center;">' + i +'&nbsp;行</div>'
            +'<div class="col-sm-1 line-input-center">'
            +'<select class="form-control" name="investType">'
            +'<option value="1">做多</option>'
            +'<option value="2">做空</option>'
            +'</select>'
            +'</div>'
            +'<div class="col-sm-2 line-input-center">'
            +'<input type="text" class="form-control" name="startTime">'
            +'</div>'
            +'<div class="col-sm-2 line-input-center">'
            +'<input type="text" class="form-control" name="endTime">'
            +'</div>'
            +'<div class="col-sm-1 line-input-center">'
            +'<input type="number" class="form-control" name="lineNumber">'
            +'</div>'
            +'<div class="col-sm-1 line-input-center">'
            +'<input type="number" class="form-control" name="gainPoint">'
            +'</div>'
            +'<div class="col-sm-4 line-input-right">'
            +'<input type="text" class="form-control" name="remark">'
            +'</div>'
            +'</div>';
    }
    $("#lineGroupBox").html(str);
}
