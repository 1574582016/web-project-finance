var ue = UE.getEditor('editor');

$(function () {
    var id = $("#id").val();
    if(id !='' && id != null){
        $.APIPost("/api/diary/getLearnDiaryInfo?id="+id ,function (data) {
            // console.log(data);
            $("#id").val(data.data.result.id);
            $("#type").val(data.data.result.type);
            $("#diaryCode").val(data.data.result.diaryCode);
            $("#name").val(data.data.result.name);
            $("#summary").val(data.data.result.summary);
            $("#diaryCode").val(data.data.result.diaryCode);
            ue.ready(function() {//编辑器初始化完成再赋值
                ue.setContent(data.data.result.content);  //赋值给UEditor
            });
        })
    }



    $("#submitFormButton").click(function () {
        var id = $("#id").val();
        var type = $("#type").val();
        var name = $("#name").val();
        var summary = $("#summary").val();
        var diaryCode = $("#diaryCode").val();
        var content = ue.getContent();
        $.APIPost("/api/diary/editLearnDiary",JSON.stringify({id:id ,diaryCode:diaryCode ,name:name ,type:type , summary : summary, content : content}),function (data) {
            if(data.success){
                showSuccessAlert(data.message,function () {
                    location.href="/diary/learnDiaryList";
                });
            }else{
                showFailedAlert(data.message);
            }
        })
    });
    
    $("#resetFormButton").click(function () {
        backLastBread();
        location.href="/diary/learnDiaryList";
    });

})
