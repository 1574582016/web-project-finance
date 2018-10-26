
$(function () {
    var id = $("#id").val();
    if(id !='' && id != null){
        $.APIPost("/api/diary/getLearnDiaryInfo?id="+id ,function (data) {
            $("#name").html(data.data.result.name);
            $("#summary").html("●&nbsp;"+data.data.result.summary);
            $("#content").html(data.data.result.content);
        })
    }

    $("#resetFormButton").click(function () {
        backLastBread();
        location.href="/diary/learnDiaryList";
    });

})
