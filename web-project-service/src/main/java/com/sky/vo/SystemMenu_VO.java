package com.sky.vo;

import com.sky.core.model.VoModel;

import java.util.List;

/**
 * Created by ThinkPad on 2018/10/6.
 */
public class SystemMenu_VO extends VoModel {

    private String menuCode ;


    private String menuName ;


    private String menuUrl ;


    private String menuIcon ;


    private List<SystemMenu_VO> childMenu ;

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public List<SystemMenu_VO> getChildMenu() {
        return childMenu;
    }

    public void setChildMenu(List<SystemMenu_VO> childMenu) {
        this.childMenu = childMenu;
    }
}
