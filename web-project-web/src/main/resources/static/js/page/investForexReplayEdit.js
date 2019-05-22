
$(function () {

    createLineInputGroup(100);

    $('#replayTime').datepicker({
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        clearBtn: true ,
        autoclose: true
    });
    $( "input[name='startTime']" ).datepicker({
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        clearBtn: true ,
        autoclose: true
    });

    $( "input[name='endTime']" ).datepicker({
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        clearBtn: true ,
        autoclose: true
    });

    $("#submitFormButton").click(function () {
        var replayTime = $("#replayTime").val();
        if(isEmpty(replayTime)){
            showWarningAlert("时间不能为空！");
            return ;
        }
        var investStrategy = $("#investStrategy").val();
        var currencyPairs = $("#currencyPairs").val();
        var timeCycle = $("#timeCycle").val();

        var typeArray = new Array();
        $("select[name='investType']").each(function(){
            typeArray.push($(this).val());
        });

        var startArray = new Array();
        $("input[name='startTime']").each(function(){
            startArray.push($(this).val());
        });

        var endArray = new Array();
        $("input[name='endTime']").each(function(){
            endArray.push($(this).val());
        });

        var lineArray = new Array();
        $("input[name='lineNumber']").each(function(){
            lineArray.push($(this).val());
        });

        var pointArray = new Array();
        $("input[name='gainPoint']").each(function(){
            pointArray.push($(this).val());
        });

        var remarkArray = new Array();
        $("input[name='remark']").each(function(){
            remarkArray.push($(this).val());
        });

        var  params = [];
        for(var i = 0 ; i < typeArray.length ; i ++){
            var investType = typeArray[i] ;
            var startTime = startArray[i];
            var row = i + 1 ;
            if(isEmpty(startTime)){
                showWarningAlert("第"+ row +"行开始时间不能为空！");
                return ;
            }
            var endTime = endArray[i];
            if(isEmpty(endTime)){
                showWarningAlert("第"+ row +"行结束时间不能为空！");
                return ;
            }
            var lineNumber = lineArray[i];
            if(isEmpty(lineNumber)){
                showWarningAlert("第"+ row +"行k线不能为空！");
                return ;
            }
            var gainPoint = pointArray[i];
            if(isEmpty(gainPoint)){
                showWarningAlert("第"+ row +"行点数不能为空！");
                return ;
            }
            var remark = remarkArray[i];
            params.push({"investType" : investType , "startTime": startTime , "endTime": endTime, "lineNumber":lineNumber , "gainPoint":gainPoint ,"remark" : remark});
        }

        $.APIPost("/api/invest/editInvestForexReplay",JSON.stringify({
            replayTime:replayTime ,
            investStrategy:investStrategy ,
            currencyPairs:currencyPairs ,
            timeCycle:timeCycle ,
            detailList:params
        }),function (data) {
            if(data.success){
                showSuccessAlert(data.message,function () {
                    location.href="/invest/investForexReplayList";
                });
            }else{
                showFailedAlert(data.message);
            }
        })
    });

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
            +'<option value="做多">做多</option>'
            +'<option value="做空">做空</option>'
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
