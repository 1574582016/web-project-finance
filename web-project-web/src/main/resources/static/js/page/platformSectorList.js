var resultData ;

$(function(){

    resultData = drawMenuTree();

    var option = '<option value="market">根目录</option>';
    $.each(resultData,function (index ,value) {
        option += '<option value="'+ value.href +'">'+ value.text +'</option>';
    });
    $("#p_firstCode").html(option);
    $("#d_firstCode").html(option);
    $("#d_secondCode").html('<option value="">请选择</option>');
    $("#d_thirdCode").html('<option value="">请选择</option>');
    $("#d_forthCode").html('<option value="">请选择</option>');

    $("#cancelButton").click(function () {
        var attr = $("#editDataButton").attr("disabled");
        if(attr == 'disabled'){
            $("#editMenuForm").find("input").removeAttr("disabled");
            $("#editMenuForm").find("select").removeAttr("disabled");
            $("#editMenuForm").find("textarea").removeAttr("disabled");
            $("#editMenuForm").find("#editDataButton").removeAttr("disabled");
            $("#cancelButton").html("取消");
        }else{
            changeFormState();
            $("#cancelButton").html("修改");
        }
    });


    $("#addDataButton").click(function () {
        var parentCode = "";
        if(!isEmpty($("#d_thirdCode").val())){
            parentCode = $("#d_thirdCode").val();
        }else{
            if(!isEmpty($("#d_secondCode").val())){
                parentCode = $("#d_secondCode").val();
            }else{
                parentCode = $("#d_firstCode").val();
            }
        }
        var marketName = $("#d_sectorName").val();
        var orderNum =  $("#d_orderNum").val();
        var describe =  $("#p_describe").text();
        editMenuInfo(null , parentCode, marketName , orderNum, describe , 1);
    });

    $("#editDataButton").click(function () {
        var parentCode = "";
        if(!isEmpty($("#d_thirdCode").val())){
            parentCode = $("#d_thirdCode").val();
        }else{
            if(!isEmpty($("#d_secondCode").val())){
                parentCode = $("#d_secondCode").val();
            }else{
                parentCode = $("#d_firstCode").val();
            }
        }
        var marketCode = $("#p_marketCode").val();
        var marketName = $("#p_menuName").val();
        var orderNum =  $("#p_orderNum").val();
        var describe =  $("#p_describe").text();
        var isvalid =  $('input[name="p_isvalid"]:checked').val();
        editMenuInfo(marketCode , parentCode, marketName , orderNum , describe , isvalid);
    });

    $("#addButton").click(function () {
        $("#p_marketCode").val("");
        $("#p_parentCode").val("market");
        $("#p_sectorName").val("");
        $("#p_orderNum").val("");
        $("#p_describe").text("");
        $("input[name='p_isvalid']").removeAttr("checked");
        $("input[name='p_isvalid'][value='"+ data.data.result.isvalid +"']").attr("checked", true);
    });

    function editMenuInfo(marketCode , parentCode, marketName , orderNum , describe , isvalid) {
        $.APIPost("/api/finance/editFinanceMarket",JSON.stringify({marketCode:marketCode , parentCode:parentCode , marketName:marketName ,orderNum:orderNum ,describe: describe ,isvalid:isvalid , marketType: 3}),function (data) {
            if(data.success){
                hideModal("myModal");
                window.parent.showSuccessAlert(data.message,function () {
                    drawMenuTree();
                    changeFormState();
                    getMenuInfo(marketCode);
                });
            }else{
                window.parent.showFailedAlert(data.message);
            }
        });
    }

    function getMenuInfo(marketCode) {
        $.APIPost("/api/finance/getFinanceMarket?marketCode=" + marketCode ,function (data) {
            $("#p_firstCode").val(data.data.result.marketCode);
            $("#p_marketCode").val(data.data.result.marketCode);
            $("#p_sectorName").val(data.data.result.marketName);
            $("#p_orderNum").val(data.data.result.orderNum);
            $("#p_describe").text(data.data.result.describe);
            $("input[name='p_isvalid']").removeAttr("checked");
            $("input[name='p_isvalid'][value='"+ data.data.result.isvalid +"']").attr("checked", true);
        });
        changeFormState();
    }

    function changeFormState() {
        $("#editMenuForm").find("input").attr("disabled",true);
        $("#editMenuForm").find("select").attr("disabled",true);
        $("#editMenuForm").find("textarea").attr("disabled",true);
        $("#editMenuForm").find("#editDataButton").attr("disabled",true);
        $("#cancelButton").html("修改");
    }

    /*------------------------------------------------------------------------------------------------------------------*/

    function drawMenuTree() {
        var arrayData ;
        $.APIPost("/api/finance/getPlatformSectorTree",function (data) {
            arrayData = data.data.result;
            $('#tree').treeview({
                data: data.data.result,         // 数据源
                showCheckbox: false,   //是否显示复选框
                highlightSelected: false,    //是否高亮选中
                multiSelect: false,    //多选
                emptyIcon: "",
                onNodeSelected: function (event, data) {
                    getMenuInfo(data.href);
                }
            });
        });
        return arrayData;
    }

    //递归所有子节点，找到所有层级节点
    function getChildNodeIdArr(node) {
        var ts = [];
        if(node.nodes) {
            for(x in node.nodes) {
                ts.push(node.nodes[x].nodeId);
                if(node.nodes[x].nodes) {
                    var getNodeDieDai = getChildNodeIdArr(node.nodes[x]);//有第二层，第三层子节点的情况
                    for(j in getNodeDieDai) {
                        ts.push(getNodeDieDai[j]);
                    }
                }
            }
        } else {
            ts.push(node.nodeId);
        }
        return ts;
    }

    //递归所有子节点，找到所有层级节点
    function getParentNodeIdArr(id ,node) {
        var parentNode = $("#" + id).treeview("getNode", node.parentId);
        var ts = [];
        if(parentNode.nodeId >= 0) {
            ts.push(parentNode.nodeId);
            var pts = getParentNodeIdArr(id ,parentNode);
            Array.prototype.push.apply(ts, pts);
        }
        return ts;
    }

});

function changeSecondSector(obj) {
    var $obj = $(obj);
    var code = $obj.val();
    var secondOption = '<option value="">请选择</option>';
    $.each(resultData,function (index ,value) {
        if(value.href == code){
            $.each(value.nodes ,function (index2 ,value2) {
                secondOption += '<option value="'+ value2.href +'">'+ value2.text +'</option>';
            });
        }
    });
    $("#d_secondCode").html(secondOption);
    $("#d_thirdCode").html('<option value="">请选择</option>');
    $("#d_forthCode").html('<option value="">请选择</option>');
}

function changeThirdSector(obj) {
    var firstCode = $("#d_firstCode").val();
    var $obj = $(obj);
    var code = $obj.val();
    var thirdOption = '<option value="">请选择</option>';
    $.each(resultData,function (index ,value) {
        if(value.href == firstCode){
            $.each(value.nodes ,function (index2 ,value2) {
                if(value2.href == code){
                    $.each(value2.nodes ,function (index3 ,value3) {
                        thirdOption += '<option value="'+ value3.href +'">'+ value3.text +'</option>';
                    });
                }
            });
        }
    });
    $("#d_thirdCode").html(thirdOption);
    $("#d_forthCode").html('<option value="">请选择</option>');
}

function changeSecondSector2(obj) {
    var $obj = $(obj);
    var code = $obj.val();
    var secondOption = '<option value="">请选择</option>';
    $.each(resultData,function (index ,value) {
        if(value.href == code){
            $.each(value.nodes ,function (index2 ,value2) {
                secondOption += '<option value="'+ value2.href +'">'+ value2.text +'</option>';
            });
        }
    });
    $("#f_secondCode").html(secondOption);
    $("#f_thirdCode").html('<option value="">请选择</option>');
    $("#f_forthCode").html('<option value="">请选择</option>');
}

function changeThirdSector2(obj) {
    var firstCode = $("#f_firstCode").val();
    var $obj = $(obj);
    var code = $obj.val();
    var thirdOption = '<option value="">请选择</option>';
    $.each(resultData,function (index ,value) {
        if(value.href == firstCode){
            $.each(value.nodes ,function (index2 ,value2) {
                if(value2.href == code){
                    $.each(value2.nodes ,function (index3 ,value3) {
                        thirdOption += '<option value="'+ value3.href +'">'+ value3.text +'</option>';
                    });
                }
            });
        }
    });
    $("#f_thirdCode").html(thirdOption);
    $("#f_forthCode").html('<option value="">请选择</option>');
}