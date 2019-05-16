
$(function () {
    var id = $("#id").val();
    var newsCode = $("#newsCode").val();

    if(!isEmpty(id)){
        $.APIPost("/api/economy/getEconomyNewsStatisticsById?id="+id ,function (response) {
            console.log(response.data.result.newsTitle);
            $("#newsTitle").html(response.data.result.newsTitle);
            $("#newsContry").html(response.data.result.newsContry);
            $("#newsTime").html(response.data.result.newsTime.substr(0,10));
            $("#newsKeyWord").html(response.data.result.newsKeyWord);
            $("#newsContent").html(response.data.result.newsContent);
            $("#newsRegion").html(response.data.result.newsRegion);
            $("#newsType").html(response.data.result.newsType);
            var strBox = "";
            $.each(response.data.result.influencelist ,function (index ,value) {
                var influenceDegree = value.influenceDegree ;
                strBox +='<div><div class="form-group" style="text-align: center">'
                strBox += '<span class="glyphicon glyphicon-flag"></span> 影响' + (index + 1);
                strBox +='</div><div class="form-group">'
                    +'<label class="control-label col-sm-2">影响范围：</label>'
                    +'<div class="col-sm-8 form-input-show">'+ value.influenceScope +'</div>'
                    +'</div>'
                    +'<div class="form-group">'
                    +'<label class="control-label col-sm-2">影响时间：</label>'
                    +'<div class="col-sm-3 form-input-show">'+ value.influenceTime +'</div>'
                    +'<label class="control-label col-sm-2">影响深度：</label>'
                    +'<div class="col-sm-3 form-input-show">';
                    for(var i = 0 ; i < 6 - influenceDegree ; i ++){
                        strBox += '<span class="glyphicon glyphicon-star-empty"></span>&nbsp;';
                    }
                strBox += '</div>'
                    +'</div>'
                    +'<div class="form-group">'
                    +'<label class="control-label col-sm-2">影响内容：</label>'
                    +'<div class="col-sm-8 form-input-show">'+ value.influenceContent +'</div>'
                    +'</div></div>';

            })
            $("#influenceBox").html(strBox);
        })
    }
});

