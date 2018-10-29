$(function(){
    createRoleList();
    
    function createRoleList() {
        $.APIPost("/api/role/getRoleList",function (data) {
            var str = '';
            $.each(data.data.result,function (index ,value) {
                str += '<li class="list-group-item" onclick="getRoleInfo(\''+ value.roleCode +'\')"><span class="badge">';
                if(value.isvalid == 1){
                    str += '启用';
                }else{
                    str += '禁用';
                }
                str += '</span>'+ value.roleName +'</li>';
            });
            $("#roleListBox").html(str);
            changeFormState(1);
        });
    }

    $("#cancelButton").click(function () {
        var attr = $("#editDataButton").attr("disabled");
        if(attr == 'disabled'){
            changeFormState(2);
        }else{
            changeFormState(1);
        }
    });

    $("#addDataButton").click(function () {
        var roleName = $("#f_roleName").val();
        var describe = $("#f_describe").val();
        editRoleInfo(null , roleName, describe , 1);
    });

    $("#editDataButton").click(function () {
        var roleCode = $("#p_roleCode").val();
        var roleName = $("#p_roleName").val();
        var describe = $("#p_describe").val();
        var isvalid =  $('input[name="p_isvalid"]:checked').val();
        editRoleInfo(roleCode , roleName, describe , isvalid);
    });
    
    $("#authButton").click(function () {
       var check =  $('#tree').treeview('getChecked');
        if(typeof check != "undefined"){
            var id_array=new Array();
            for (var i= 0; i < check.length;i++){
                id_array.push(check[i].href);
            }
            var menuCodes=id_array.join(',');
            showDialog("系统提示" ,"您是否授权此角色？",function () {
                $.APIPost("/api/role/authRole?roleCode=" + $("#p_roleCode").val() + "&menuCodes=" + menuCodes,function (data) {
                    console.log(data);
                    if(data.success){
                        showSuccessAlert(data.message,function () {
                            getRoleInfo(data.data.result);
                        });
                    }else{
                        showFailedAlert("保存失败");
                    }
                });
            })
        }else{
            showFailedAlert("请选择授权菜单！");
        }

    });

    function editRoleInfo(roleCode , roleName, describe , isvalid) {
        $.APIPost("/api/role/checkRoleName?roleName=" + roleName +"&roleCode=" + roleCode,function (data) {
            if(data.data.result){
                showFailedAlert("角色已存在！");
            }else{
                $.APIPost("/api/role/editRole",JSON.stringify({roleCode:roleCode , roleName:roleName , describe:describe ,isvalid:isvalid}),function (data) {
                    if(data.success){
                        hideModal("myModal");
                        showSuccessAlert(data.message,function () {
                            createRoleList();
                            getRoleInfo(data.data.result);
                        });
                    }else{
                        showFailedAlert("保存失败");
                    }
                });
            }
        });
    }
});

function getRoleInfo(roleCode) {
    $.APIPost("/api/role/getRoleInfo?roleCode=" + roleCode ,function (data) {
        $("#p_roleCode").val(data.data.result.roleCode)
        $("#p_roleName").val(data.data.result.roleName)
        $("#p_describe").val(data.data.result.describe);
        $("input[name='p_isvalid']").removeAttr("checked");
        $("input[name='p_isvalid'][value='"+ data.data.result.isvalid +"']").attr("checked", true);
        creatMenuTree(data.data.menu);
        changeFormState(1);
    });
}

function creatMenuTree(data) {
    $('#tree').treeview({
        data: data,         // 数据源
        showCheckbox: true,   //是否显示复选框
        highlightSelected: false,    //是否高亮选中
        multiSelect: false,    //多选
        onNodeChecked : function(event, node) {
            getNodeChecked("tree" , node);
        },
        onNodeUnchecked : function(event, node){
            getNodeUnchecked("tree" , node);
        }
    });
}

function changeFormState(type) {
    if(type == 1){
        $("#editRoleForm").find("input").attr("disabled",true);
        $("#editRoleForm").find("textarea").attr("disabled",true);
        $("#editRoleForm").find("#editDataButton").attr("disabled",true);
        $("#cancelButton").html("修改");
    }else if(type == 2){
        $("#editRoleForm").find("input").removeAttr("disabled");
        $("#editRoleForm").find("textarea").removeAttr("disabled");
        $("#editRoleForm").find("#editDataButton").removeAttr("disabled");
        $("#cancelButton").html("取消");
    }
}

/*------------------------------------------------------------------------------------------------------------------*/

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