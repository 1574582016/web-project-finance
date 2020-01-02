$(function(){

    var resultData = drawMenuTree();

    var option = '<option value="1048460137181315074">根目录</option>';
    $.each(resultData,function (index ,value) {
        option += '<option value="'+ value.href +'">'+ value.text +'</option>';
    });
    $("#p_parentCode").html(option);
    $("#d_parentCode").html(option);

    $.APIPost("/api/role/getRoleList",function (data) {
        var p_option = '';
        var d_option = '';
        $.each(data.data.result,function (index ,value) {
            p_option += '<label class="checkbox-inline"><input type="checkbox" id="p_role" name="p_role" value="'+ value.roleCode +'"> '+ value.roleName +'</label>';
            d_option += '<label class="checkbox-inline"><input type="checkbox" id="d_role" name="d_role" value="'+ value.roleCode +'"> '+ value.roleName +'</label>';
        });
        $("#p_roleBox").html(p_option);
        $("#d_roleBox").html(d_option);
        $("#editMenuForm").find("input").attr("disabled",true);
        $("#editMenuForm").find("select").attr("disabled",true);
        $("#editMenuForm").find("#editDataButton").attr("disabled",true);
    });
    
    $("#cancelButton").click(function () {
        var attr = $("#editDataButton").attr("disabled");
        if(attr == 'disabled'){
            $("#editMenuForm").find("input").removeAttr("disabled");
            $("#editMenuForm").find("select").removeAttr("disabled");
            $("#editMenuForm").find("#editDataButton").removeAttr("disabled");
            $("#cancelButton").html("取消");
            var menuLevel = $("#p_menuLevel").val();
            if(menuLevel == 2){
                $("#p_menuIcon").attr("disabled",true);
            }else{
                $("#p_menuIcon").removeAttr("disabled");
            }
        }else{
            changeFormState();
            $("#cancelButton").html("修改");
        }
    });

    $("#d_parentCode").change(function(){
        if($(this).val() != '1048460137181315074'){
            $("#d_menuIcon").attr("disabled",true);
        }else{
            $("#d_menuIcon").removeAttr("disabled");
        }
    });
    
    
    $("#addDataButton").click(function () {
        var parentCode = $("#d_parentCode").val();
        var menuName = $("#d_menuName").val();
        var menuIcon = $("#d_menuIcon").val();
        var menuUrl = $("#d_menuUrl").val();
        var orderNum =  $("#d_orderNum").val();

        var id_array=new Array();
        $('input[name="d_role"]:checked').each(function(){
            id_array.push($(this).val());
        });
        var roleId=id_array.join(',');
        editMenuInfo(null , parentCode, menuName , menuIcon, menuUrl, orderNum, roleId , null);
    });
    
    $("#editDataButton").click(function () {
        var menuCode = $("#p_menuCode").val();
        var parentCode = $("#p_parentCode").val();
        var menuName = $("#p_menuName").val();
        var menuIcon = $("#p_menuIcon").val();
        var menuUrl = $("#p_menuUrl").val();
        var orderNum =  $("#p_orderNum").val();
        var isvalid =  $('input[name="p_isvalid"]:checked').val();

        var id_array=new Array();
        $('input[name="p_role"]:checked').each(function(){
            id_array.push($(this).val());
        });
        var roleId=id_array.join(',');
        editMenuInfo(menuCode , parentCode, menuName , menuIcon ,menuUrl, orderNum, roleId , isvalid);
    });
    
    function editMenuInfo(menuCode , parentCode, menuName , menuIcon, menuUrl, orderNum, roleId , isvalid) {
        $.APIPost("/api/menu/checkMenuName?menuName=" + menuName + "&parentCode="+ parentCode +"&menuCode=" + menuCode,function (data) {
            if(data.data.result){
                alert("菜单已存在");
            }else{
                $.APIPost("/api/menu/editMenu?roleId="+roleId,JSON.stringify({menuCode:menuCode , parentCode:parentCode , menuName:menuName ,menuIcon:menuIcon , menuUrl:menuUrl ,orderNum:orderNum ,isvalid:isvalid}),function (data) {
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
        $.APIPost("/api/menu/getMenuInfo?menuCode=" + menuId ,function (data) {
            $("#p_menuCode").val(data.data.result.menuCode)
            $("#p_parentCode").val(data.data.result.parentCode);
            $("#p_menuName").val(data.data.result.menuName);
            $("#p_menuIcon").val(data.data.result.menuIcon);
            $("#p_menuUrl").val(data.data.result.menuUrl);
            $("#p_orderNum").val(data.data.result.orderNum);
            $("#p_menuLevel").val(data.data.result.menuLevel);
            $("input[name='p_isvalid']").removeAttr("checked");
            $("input[name='p_isvalid'][value='"+ data.data.result.isvalid +"']").prop("checked", true);
            $("input[name='p_role']").removeAttr("checked");
            $.each(data.data.role, function (index, value) {
                $("input[name='p_role'][value='"+ value.roleCode +"']").prop("checked", true);
            });
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
        $.APIPost("/api/menu/getMenuTree",function (data) {
            arrayData = data.data.result;
            $('#tree').treeview({
                data: data.data.result,         // 数据源
                showCheckbox: true,   //是否显示复选框
                highlightSelected: false,    //是否高亮选中
                multiSelect: false,    //多选
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