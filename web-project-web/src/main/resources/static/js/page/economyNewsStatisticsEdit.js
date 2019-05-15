
$(function () {

    $('#newsTime').datepicker({
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        clearBtn: true ,
        autoclose: true
    });

    var id = $("#id").val();
    var newsCode = "";
    if(!isEmpty(id)){
        $.APIPost("/api/economy/getEconomyNewsStatisticsById?id="+id ,function (response) {
            newsCode = response.data.result.newsCode ;
            $("#newsTitle").val(response.data.result.newsTitle);
            $("#newsContry").val(response.data.result.newsContry);
            $("#newsTime").val(response.data.result.newsTime.substr(0,10));
            $("#newsKeyWord").val(response.data.result.newsKeyWord);
            $("#newsContent").val(response.data.result.newsContent);
            $("#newsRegion").val(response.data.result.newsRegion);
            $("#newsType").val(response.data.result.newsType);
            var influencelist = response.data.result.influencelist ;
            var strBox = "";
            $.each(response.data.result.influencelist ,function (index ,value) {
                     strBox +='<div><div class="form-group">'
                            +'<label class="control-label col-sm-2">影响范围：</label>'
                            +'<div class="col-sm-8">'
                            +'<input type="hidden" class="form-control" name="influenceId" value="'+ value.id +'">'
                            +'<input type="hidden" class="form-control" name="influenceCode" value="'+ value.influenceCode +'">'
                            +'<input type="text" class="form-control" name="influenceScope" value="'+ value.influenceScope +'" placeholder="影响范围">'
                            +'</div>'
                            +'<div class="col-sm-2"><button type="button" class="btn btn-primary"  onclick="minusButton(this)"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span>移除</button></div>'
                            +'</div>'
                            +'<div class="form-group">'
                            +'<label class="control-label col-sm-2">影响时间：</label>'
                            +'<div class="col-sm-3">'
                            +'<input type="text" class="form-control" name="influenceTime" value="'+ value.influenceTime +'" placeholder="影响时间">'
                            +'</div>'
                            +'<label class="control-label col-sm-2">影响深度：</label>'
                            +'<div class="col-sm-3">'
                            +'<select class="form-control" name="influenceDegree">';
                            if(value.influenceDegree == 1){
                                strBox += '<option value="1" selected="selected">严重影响</option>'
                                         +'<option value="2">比较影响</option>'
                                         +'<option value="3">一般影响</option>'
                                         +'<option value="4">较弱影响</option>'
                                         +'<option value="5">无影响</option>';
                            }
                            if(value.influenceDegree == 2){
                                strBox += '<option value="1">严重影响</option>'
                                         +'<option value="2" selected="selected">比较影响</option>'
                                         +'<option value="3">一般影响</option>'
                                         +'<option value="4">较弱影响</option>'
                                         +'<option value="5">无影响</option>';
                            }
                            if(value.influenceDegree == 3){
                                strBox +='<option value="1">严重影响</option>'
                                        +'<option value="2">比较影响</option>'
                                        +'<option value="3" selected="selected">一般影响</option>'
                                        +'<option value="4">较弱影响</option>'
                                        +'<option value="5">无影响</option>';

                            }
                            if(value.influenceDegree == 4){
                                strBox += '<option value="1">严重影响</option>'
                                         +'<option value="2">比较影响</option>'
                                         +'<option value="3">一般影响</option>'
                                         +'<option value="4" selected="selected">较弱影响</option>'
                                         +'<option value="5">无影响</option>';
                            }
                            if(value.influenceDegree == 5){
                                strBox += '<option value="1">严重影响</option>'
                                         +'<option value="2">比较影响</option>'
                                         +'<option value="3">一般影响</option>'
                                         +'<option value="4">较弱影响</option>'
                                         +'<option value="5" selected="selected">无影响</option>';
                            }
                strBox +=  '</select>'
                            +'</div>'
                            +'</div>'
                            +'<div class="form-group">'
                            +'<label class="control-label col-sm-2">影响内容：</label>'
                            +'<div class="col-sm-8">'
                            +'<textarea class="form-control" rows="5" name="influenceContent" placeholder="影响内容">' + value.influenceContent + '</textarea>'
                            +'</div>'
                            +'</div></div>';

            })
            $("#influenceBox").html(strBox);
        })
    }

    $("#submitFormButton").click(function () {
        var id = $("#id").val();
        var newsTitle = $("#newsTitle").val();
        if(isEmpty(newsTitle)){
            showFailedAlert("新闻标题不能为空！");
            return ;
        }
        var newsContry = $("#newsContry").val();
        if(isEmpty(newsContry)){
            showFailedAlert("所属国家不能为空！");
            return ;
        }
        var newsTime = $("#newsTime").val();
        if(isEmpty(newsTime)){
            showFailedAlert("时间不能为空！");
            return ;
        }
        var newsKeyWord = $("#newsKeyWord").val();
        if(isEmpty(newsKeyWord)){
            showFailedAlert("关键词不能为空！");
            return ;
        }
        var newsContent = $("#newsContent").val();
        if(isEmpty(newsContent)){
            showFailedAlert("新闻内容不能为空！");
            return ;
        }

        var newsRegion = $("#newsRegion").val();
        var newsType = $("#newsType").val();

        var scopeArray = new Array();
        $("input[name='influenceScope']").each(function(){
            scopeArray.push($(this).val());
        });

        var timeArray = new Array();
        $("input[name='influenceTime']").each(function(){
            timeArray.push($(this).val());
        });

        var degreeArray = new Array();
        $("select[name='influenceDegree']").each(function(){
            degreeArray.push($(this).val());
        });

        var contentArray = new Array();
        $("textarea[name='influenceContent']").each(function(){
            contentArray.push($(this).val());
        });

        var influenceIdArray = new Array();
        $("textarea[name='influenceId']").each(function(){
            influenceIdArray.push($(this).val());
        });
        var influenceCodeArray = new Array();
        $("textarea[name='influenceCode']").each(function(){
            influenceCodeArray.push($(this).val());
        });

        var  params = [];
        for(var i = 0 ; i < scopeArray.length ; i ++){
            params.push({"id":influenceIdArray[i] , "influenceCode" : influenceCodeArray[i] , "influenceScope": scopeArray[i] , "influenceTime":timeArray[i] , "influenceDegree":degreeArray[i] , "influenceContent":contentArray[i] ,"newsCode" : newsCode});
        }

        $.APIPost("/api/economy/editEconomyNewsStatistics",JSON.stringify({
            id:id ,
            newsCode:newsCode ,
            newsTitle:newsTitle ,
            newsContry:newsContry ,
            newsTime:newsTime ,
            newsKeyWord:newsKeyWord ,
            newsContent:newsContent ,
            newsRegion:newsRegion ,
            newsType:newsType ,
            influencelist:params
        }),function (data) {
            if(data.success){
                showSuccessAlert(data.message,function () {
                    location.href="/economy/economyNewsStatisticsList";
                });
            }else{
                showFailedAlert(data.message);
            }
        })
    });

    $("#resetFormButton").click(function () {
        backLastBread();
        location.href="/economy/economyNewsStatisticsList";
    });
    
    $("#addInfluenceButton").click(function () {
        var strBox ='<div><div class="form-group">'
                    +'<label class="control-label col-sm-2">影响范围：</label>'
                    +'<div class="col-sm-8">'
                    +'<input type="hidden" class="form-control" name="influenceId">'
                    +'<input type="hidden" class="form-control" name="influenceCode">'
                    +'<input type="text" class="form-control" name="influenceScope" placeholder="影响范围">'
                    +'</div>'
                    +'<div class="col-sm-2"><button type="button" class="btn btn-primary"  onclick="minusButton(this)"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span>移除</button></div>'
                    +'</div>'
                    +'<div class="form-group">'
                    +'<label class="control-label col-sm-2">影响时间：</label>'
                    +'<div class="col-sm-3">'
                    +'<input type="text" class="form-control" name="influenceTime" placeholder="影响时间">'
                    +'</div>'
                    +'<label class="control-label col-sm-2">影响深度：</label>'
                    +'<div class="col-sm-3">'
                    +'<select class="form-control" name="influenceDegree">'
                    +'<option value="1">严重影响</option>'
                    +'<option value="2">比较影响</option>'
                    +'<option value="3">一般影响</option>'
                    +'<option value="4">较弱影响</option>'
                    +'<option value="5">无影响</option>'
                    +'</select>'
                    +'</div>'
                    +'</div>'
                    +'<div class="form-group">'
                    +'<label class="control-label col-sm-2">影响内容：</label>'
                    +'<div class="col-sm-8">'
                    +'<textarea class="form-control" rows="5" name="influenceContent" placeholder="影响内容"></textarea>'
                    +'</div>'
                    +'</div></div>';
         $("#formSearch").append(strBox);
    });

});
function minusButton(obj){
    var thisObj=$(obj);
    thisObj.parent().parent().parent().remove();
}