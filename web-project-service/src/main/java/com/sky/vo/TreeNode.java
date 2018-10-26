package com.sky.vo;

import com.sky.core.model.VoModel;

import java.util.List;

/**
 * Created by ThinkPad on 2018/10/6.
 */
public class TreeNode extends VoModel{

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

    private List<TreeNode> nodes ;

    public TreeNode() {
    }

    public TreeNode(boolean checked , boolean disabled , boolean expanded , boolean selected ) {
        TreeNodeState treeNodeState = new TreeNodeState( checked, disabled, expanded, selected);
        this.state = treeNodeState;
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

    class TreeNodeState{

        /**
         * 选中
         */
        private boolean checked ;

        /**
         * 可用
         */
        private boolean disabled ;

        /**
         * 展开
         */
        private boolean expanded ;

        /**
         * 选择
         */
        private boolean selected ;

        public TreeNodeState() {
        }

        public TreeNodeState(boolean checked, boolean disabled, boolean expanded, boolean selected) {
            this.checked = checked;
            this.disabled = disabled;
            this.expanded = expanded;
            this.selected = selected;
        }

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        public boolean isDisabled() {
            return disabled;
        }

        public void setDisabled(boolean disabled) {
            this.disabled = disabled;
        }

        public boolean isExpanded() {
            return expanded;
        }

        public void setExpanded(boolean expanded) {
            this.expanded = expanded;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        @Override
        public String toString() {
            return "TreeNodeState{" +
                    "checked=" + checked +
                    ", disabled=" + disabled +
                    ", expanded=" + expanded +
                    ", selected=" + selected +
                    '}';
        }
    }
}
