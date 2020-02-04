package com.sky.core.model;

import java.util.List;

/**
 * Created by ThinkPad on 2018/10/17.
 */
public class TreeNode extends VoModel {
    /**
     *标题
     */
    private String text ;

    /**
     *图标
     */
    private String icon ;

    /**
     *连接
     */
    private  String href ;

    /**
     *状态
     */
    private  TreeNodeState state ;

    private String code ;

    private String parentCode ;

    private List<TreeNode> nodes ;

    public TreeNode() {
    }

    public TreeNode(boolean checked , boolean disabled , boolean expanded , boolean selected ) {
        TreeNodeState treeNodeState = new TreeNodeState( checked, disabled, expanded, selected);
        this.state = treeNodeState;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public TreeNodeState getState() {
        return state;
    }

    public void setState(TreeNodeState state) {
        this.state = state;
    }

    public List<TreeNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeNode> nodes) {
        this.nodes = nodes;
    }
    }
