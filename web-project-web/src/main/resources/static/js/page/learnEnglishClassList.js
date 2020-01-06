$(function () {

    $.APIPost("/api/english/getEnglishRootClassList?comonRoot=brev,brevi,brief,bridg" ,function (response) {
        drawTable(response);
    })

    $("#searchDataButton").click(function () {
        $.APIPost("/api/english/getEnglishRootClassList?comonRoot=" + $("#s_root").val(),function (response) {
            drawTable(response);
        })
    });
});

function drawTable(response) {
    var str = '';
    $.each(response.data.result ,function (index0 ,value0) {
        str += '<tr><td style="text-align: center;" colspan="5">'+ value0.sameRoot +'</td></tr>';
        $.each(value0.rootList ,function (index ,value) {
            str += '<tr><td style="text-align: center;" rowspan="'+ value.colRow +'">' + value.root + '</td>';
            $.each(value.list ,function (index2 ,value2) {
                if(index2 == 0){
                    if(value2.verbList.length > 0){
                        str += '<td>';
                        $.each(value2.verbList ,function (index3 ,value3) {
                            str += '<a class="text_link_a" href="http://dict.youdao.com/example/blng/eng/'+ value3.english +'/#keyfrom=dict.main.moreblng" target="view_window">'+ value3.english +'</a>'
                                + '&nbsp;&nbsp;' + value3.pronunciation + '</br>' + value3.chinese + '</br>'
                        });
                        str += '</td>';
                    }else{
                        str += '<td>&nbsp;</td>';
                    }

                    if(value2.adjList.length > 0){
                        str += '<td>';
                        $.each(value2.adjList ,function (index3 ,value3) {
                            str += '<a class="text_link_a" href="http://dict.youdao.com/example/blng/eng/'+ value3.english +'/#keyfrom=dict.main.moreblng" target="view_window">'+ value3.english +'</a>'
                                + '&nbsp;&nbsp;' + value3.pronunciation + '</br>' + value3.chinese + '</br>'
                        });
                        str += '</td>';
                    }else{
                        str += '<td>&nbsp;</td>';
                    }

                    if(value2.advList.length > 0){
                        str += '<td>';
                        $.each(value2.advList ,function (index3 ,value3) {
                            str += '<a class="text_link_a" href="http://dict.youdao.com/example/blng/eng/'+ value3.english +'/#keyfrom=dict.main.moreblng" target="view_window">'+ value3.english +'</a>'
                                + '&nbsp;&nbsp;' + value3.pronunciation + '</br>' + value3.chinese + '</br>'
                        });
                        str += '</td>';
                    }else{
                        str += '<td>&nbsp;</td>';
                    }

                    if(value2.noneList.length > 0){
                        str += '<td>';
                        $.each(value2.noneList ,function (index3 ,value3) {
                            str += '<a class="text_link_a" href="http://dict.youdao.com/example/blng/eng/'+ value3.english +'/#keyfrom=dict.main.moreblng" target="view_window">'+ value3.english +'</a>'
                                + '&nbsp;&nbsp;' + value3.pronunciation + '</br>' + value3.chinese + '</br>'
                        });
                        str += '</td>';
                    }else{
                        str += '<td>&nbsp;</td>';
                    }

                    str += '</tr>';
                }else{
                    str += '<tr>';

                    if(value2.verbList.length > 0){
                        str += '<td>';
                        $.each(value2.verbList ,function (index3 ,value3) {
                            str += '<a class="text_link_a" href="http://dict.youdao.com/example/blng/eng/'+ value3.english +'/#keyfrom=dict.main.moreblng" target="view_window">'+ value3.english +'</a>'
                                + '&nbsp;&nbsp;' + value3.pronunciation + '</br>' + value3.chinese + '</br>'
                        });
                        str += '</td>';
                    }else{
                        str += '<td>&nbsp;</td>';
                    }

                    if(value2.adjList.length > 0){
                        str += '<td>';
                        $.each(value2.adjList ,function (index3 ,value3) {
                            str += '<a class="text_link_a" href="http://dict.youdao.com/example/blng/eng/'+ value3.english +'/#keyfrom=dict.main.moreblng" target="view_window">'+ value3.english +'</a>'
                                + '&nbsp;&nbsp;' + value3.pronunciation + '</br>' + value3.chinese + '</br>'
                        });
                        str += '</td>';
                    }else{
                        str += '<td>&nbsp;</td>';
                    }

                    if(value2.advList.length > 0){
                        str += '<td>';
                        $.each(value2.advList ,function (index3 ,value3) {
                            str += '<a class="text_link_a" href="http://dict.youdao.com/example/blng/eng/'+ value3.english +'/#keyfrom=dict.main.moreblng" target="view_window">'+ value3.english +'</a>'
                                + '&nbsp;&nbsp;' + value3.pronunciation + '</br>' + value3.chinese + '</br>'
                        });
                        str += '</td>';
                    }else{
                        str += '<td>&nbsp;</td>';
                    }

                    if(value2.noneList.length > 0){
                        str += '<td>';
                        $.each(value2.noneList ,function (index3 ,value3) {
                            str += '<a class="text_link_a" href="http://dict.youdao.com/example/blng/eng/'+ value3.english +'/#keyfrom=dict.main.moreblng" target="view_window">'+ value3.english +'</a>'
                                + '&nbsp;&nbsp;' + value3.pronunciation + '</br>' + value3.chinese + '</br>'
                        });
                        str += '</td>';
                    }else{
                        str += '<td>&nbsp;</td>';
                    }

                    str += '</tr>';
                }
            });
        });
        str += '<tr><td style="text-align: center;" colspan="5">&nbsp;</td></tr>';
    });
    $("#englishClassBody").html(str);
}