$(function() {
    function getNodeChecked(id, node) {

        var selectNodes = getChildNodeIdArr(node);//获取所有节点
        if (selectNodes) {//如果子节点不为空，即存在子节点，则选中所有子节点
            //选择指定的节点，接收节点或节点ID
            $('#' + id).treeview('checkNode', [selectNodes, {silent: true}]);
        }
        //返回给定节点ID的单一节点对象
        var parentNodes = getParentNodeIdArr(id, node).reverse();
        $('#' + id).treeview('checkNode', [parentNodes, {silent: true}]);
    }

    function getNodeUnchecked(id, node) {
        var selectNodes = getChildNodeIdArr(node);
        if (selectNodes) {//子节点不为空，则取消选中所有子节点
            $('#' + id).treeview("uncheckNode", [selectNodes, {silent: true}]);
        }
        var parentNodes = getParentNodeIdArr(id, node);
        var listNodes = parentNodes;
        var nums = parentNodes.length;
        for (var i = 0; i < nums; i++) {
            var nodeId = listNodes[i];
            if (typeof nodeId === "undefined") {
                nodeId = 0;
            }
            var num = checkChildNodeState(id, nodeId);
            if (num > 0) {
                var index = parentNodes.indexOf(nodeId);
                if (index > -1) {
                    parentNodes.splice(index, 1);
                }
                if (parentNodes) {
                    $('#' + id).treeview("checkNode", [nodeId, {silent: true}]);
                }

            } else {
                $('#' + id).treeview("uncheckNode", [parentNodes, {silent: true}]);
            }
        }
        if (parentNodes) {
            $('#' + id).treeview("uncheckNode", [parentNodes, {silent: true}]);
        }

    }

//递归所有子节点，找到所有层级节点
    function getChildNodeIdArr(node) {
        var ts = [];
        if (node.nodes) {
            for (x in node.nodes) {
                ts.push(node.nodes[x].nodeId);
                if (node.nodes[x].nodes) {
                    var getNodeDieDai = getChildNodeIdArr(node.nodes[x]);//有第二层，第三层子节点的情况
                    for (j in getNodeDieDai) {
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
    function getParentNodeIdArr(id, node) {
        var parentNode = $("#" + id).treeview("getNode", node.parentId);
        var ts = [];
        if (parentNode.nodeId >= 0) {
            ts.push(parentNode.nodeId);
            var pts = getParentNodeIdArr(id, parentNode);
            Array.prototype.push.apply(ts, pts);
        }
        return ts;
    }

    function checkChildNodeState(id, nodeId) {
        var parentNode = $("#" + id).treeview("getNode", nodeId);
        var checkedCount = 0;
        if (parentNode.nodes) {
            for (x in parentNode.nodes) {
                if (parentNode.nodes[x].state.checked) {
                    checkedCount++;
                }
            }

        }
        return checkedCount;
    }

})