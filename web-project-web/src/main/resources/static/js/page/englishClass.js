
$(function () {

    showDataTable();

    $("#searchDataButton").click(function () {
        showDataTable();
    });


})

function showDataTable() {
    $.APIPost("/api/english/getEnglishClassList?fragment=" + $("#s_fragment").val(),function (data) {
        if(data.success){
            var trs = "";
            var fragment = "";
            $.each(data.data.result, function(index, value) {
                trs += '<tr>';
                if(fragment != value.fragment){
                    fragment = value.fragment ;
                    trs += '<td rowspan="' + value.size + '">&nbsp;' + value.fragment + '</td>';
                }
                trs += '<td>&nbsp;' + value.english.substr(0,value.english.indexOf(value.fragment)) + '<span style="color: #007bff;text-decoration:underline;">'+ value.fragment +'</span>' + value.english.substr(value.english.indexOf(value.fragment)+ value.fragment.length , value.english.length) + '</td>'
                    + '<td>&nbsp;' + value.pronunciation + '</td>'
                    + '<td>&nbsp;' + value.chinese + '</td>'
                    + '</tr>';
            });
            $("#englishClassBody").html(trs);
        }
    })
}
