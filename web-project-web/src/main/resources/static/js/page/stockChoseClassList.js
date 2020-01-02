$(function () {
    getPointIndexList(this,"xgzb");
    // getPointIndexTree();
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
            window.parent.showFailedAlert(data.message);
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


function getPointIndexTree() {
    $.APIPost("/api/stockChose/getStockChoseClassTree",function (data) {
        var myChart = echarts.init(document.getElementById('main'));
        option = {
            title : {
                text: '冰桶挑战'
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            series : [
                {
                    name:'树图',
                    type:'tree',
                    orient: 'horizontal',  // vertical horizontal
                    rootLocation: {x: 100,y: 230}, // 根节点位置  {x: 100, y: 'center'}
                    nodePadding: 8,
                    layerPadding: 200,
                    hoverable: false,
                    roam: true,
                    symbolSize: 6,
                    itemStyle: {
                        normal: {
                            color: '#4883b4',
                            label: {
                                show: true,
                                position: 'right',
                                formatter: "{b}",
                                textStyle: {
                                    color: '#000',
                                    fontSize: 5
                                }
                            },
                            lineStyle: {
                                color: '#ccc',
                                type: 'curve' // 'curve'|'broken'|'solid'|'dotted'|'dashed'

                            }
                        },
                        emphasis: {
                            color: '#4883b4',
                            label: {
                                show: false
                            },
                            borderWidth: 0
                        }
                    },

                    data: [
                        data.data.result
                        ]
                }
            ]
        };

        myChart.setOption(option);
    });
}