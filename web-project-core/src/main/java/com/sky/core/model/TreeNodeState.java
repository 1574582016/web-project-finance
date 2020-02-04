package com.sky.core.model;

/**
 * Created by Administrator on 2020/2/4/004.
 */
public class TreeNodeState {

    /**
     * 选中
     */
    private boolean checked;

    /**
     * 可用
     */
    private boolean disabled;

    /**
     * 展开
     */
    private boolean expanded;

    /**
     * 选择
     */
    private boolean selected;

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
}
