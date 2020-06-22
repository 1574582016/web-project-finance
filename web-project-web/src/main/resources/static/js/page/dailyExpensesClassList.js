$(function(){

    var resultData = drawMenuTree();

    var option = '<option value="0">根目录</option>';
    $.each(resultData,function (index ,value) {
        option += '<option value="'+ value.href +'">'+ value.text +'</option>';
    });
    $("#p_parentCode").html(option);
    $("#d_parentCode").html(option);

    $("#cancelButton").click(function () {
        var attr = $("#editDataButton").attr("disabled");
        if(attr == 'disabled'){
            $("#editMenuForm").find("input").removeAttr("disabled");
            $("#editMenuForm").find("select").removeAttr("disabled");
            $("#editMenuForm").find("#editDataButton").removeAttr("disabled");
            $("#cancelButton").html("取消");
        }else{
            changeFormState();
            $("#cancelButton").html("修改");
        }
    });


    $("#addDataButton").click(function () {
        var parentCode = $("#d_parentCode").val();
        var menuName = $("#d_menuName").val();
        var orderNum =  $("#d_orderNum").val();
        editMenuInfo(null , parentCode, menuName ,orderNum);
    });

    $("#editDataButton").click(function () {
        var menuCode = $("#p_menuCode").val();
        var parentCode = $("#p_parentCode").val();
        var menuName = $("#p_menuName").val();
        var orderNum =  $("#p_orderNum").val();
        editMenuInfo(menuCode , parentCode, menuName , orderNum);
    });

    function editMenuInfo(menuCode , parentCode, menuName , orderNum) {
        $.APIPost("/api/bankCard/checkExpensesClass?menuName=" + menuName + "&parentCode="+ parentCode +"&menuCode=" + menuCode,function (data) {
            if(data.data.result){
                alert("类型已存在");
            }else{
                $.APIPost("/api/bankCard/editExpensesClass",JSON.stringify({id:menuCode , parentId:parentCode , className:menuName ,classOrder:orderNum}),function (data) {
                    if(data.success){
                        hideModal("myModal");
                        window.parent.showSuccessAlert(data.message,function () {
                            drawMenuTree();
                            // changeFormState();
                            getMenuInfo(menuCode);
                        });
                    }else{
                        window.parent.showFailedAlert("保存失败");
                    }
                });
            }
        });
    }

    function getMenuInfo(menuId) {
        $.APIPost("/api/bankCard/getExpensesClass?classId=" + menuId ,function (data) {
            $("#p_menuCode").val(data.data.result.id);
            $("#p_parentCode").val(data.data.result.parentId);
            $("#p_menuName").val(data.data.result.className);
            $("#p_orderNum").val(data.data.result.classOrder);
        });
        changeFormState();
    }

    function changeFormState() {
        $("#editMenuForm").find("input").attr("disabled",true);
        $("#editMenuForm").find("select").attr("disabled",true);
        $("#editMenuForm").find("#editDataButton").attr("disabled",true);
        $("#cancelButton").html("修改");
    }

    /*------------------------------------------------------------------------------------------------------------------*/

    function drawMenuTree() {
        var arrayData ;
        $.APIPost("/api/bankCard/getExpensesClassTree",function (data) {
            arrayData = data.data.result;
            $('#tree').treeview({
                data: data.data.result,         // 数据源
                showCheckbox: false,   //是否显示复选框
                highlightSelected: false,    //是否高亮选中
                multiSelect: false,    //多选
                emptyIcon: "",
                onNodeSelected: function (event, data) {
                    getMenuInfo(data.href);
                },
                onNodeChecked : function(event, node) {
                    getNodeChecked("tree" , node);
                },
                onNodeUnchecked : function(event, node){
                    getNodeUnchecked("tree" , node);
                }
            });
        });
        return arrayData;
    }

    function getNodeChecked(id , node){

        var selectNodes= getChildNodeIdArr(node);//获取所有节点
        if(selectNodes){//如果子节点不为空，即存在子节点，则选中所有子节点
            //选择指定的节点，接收节点或节点ID
            $('#' + id).treeview('checkNode',[selectNodes,{silent:true}]);
        }
        //返回给定节点ID的单一节点对象
        var parentNodes = getParentNodeIdArr( id , node).reverse();
        $('#' + id).treeview('checkNode',[parentNodes,{silent:true}]);
    }

    function getNodeUnchecked(id , node) {
        var selectNodes=getChildNodeIdArr(node);
        if(selectNodes){//子节点不为空，则取消选中所有子节点
            $('#' + id).treeview("uncheckNode",[selectNodes,{silent:true}]);
        }
        var parentNodes = getParentNodeIdArr( id , node);
        var listNodes = parentNodes;
        var nums = parentNodes.length;
        for(var i=0 ; i<nums ; i++){
            var nodeId = listNodes[i];
            if(typeof nodeId === "undefined") {
                nodeId = 0;
            }
            var num = checkChildNodeState(id ,nodeId);
            if(num > 0){
                var index = parentNodes.indexOf(nodeId);
                if (index > -1) {
                    parentNodes.splice(index, 1);
                }
                if(parentNodes){
                    $('#' + id).treeview("checkNode",[nodeId,{silent:true}]);
                }

            }else{
                $('#' + id).treeview("uncheckNode",[parentNodes,{silent:true}]);
            }
        }
        if(parentNodes){
            $('#' + id).treeview("uncheckNode",[parentNodes,{silent:true}]);
        }

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

    function checkChildNodeState( id ,nodeId) {
        var parentNode = $("#" + id).treeview("getNode", nodeId);
        var checkedCount = 0;
        if(parentNode.nodes) {
            for(x in parentNode.nodes) {
                if(parentNode.nodes[x].state.checked) {
                    checkedCount++;
                }
            }

        }
        return checkedCount;
    }

});