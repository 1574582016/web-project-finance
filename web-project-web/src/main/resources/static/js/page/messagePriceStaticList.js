$(function () {
    messagePriceData();

    $("#searchDataButton").click(function () {
        messagePriceData();
    });
});

function messagePriceData() {
    $.APIPost("/api/statistics/getMessagePriceStaticData?messageType=" + $("#messageType").val() + "&timeType=" + $("#timeType").val() + "&directType=" + $("#directType").val() ,function (result) {
        console.log(result);
        var myChart = echarts.init(document.getElementById('main'));
        option = {
            title : {
                text: '某地区蒸发量和降水量',
                subtext: '纯属虚构'
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['蒸发量']
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    data : result.data.title
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'蒸发量',
                    type:'bar',
                    data:result.data.count ,
                    markPoint : {
                        data : [
                            {type : 'max', name: '最大值'},
                            {type : 'min', name: '最小值'}
                        ]
                    },
                    markLine : {
                        data : [
                            {type : 'average', name: '平均值'}
                        ]
                    }
                }

            ]
        };

        myChart.setOption(option);
    });
}