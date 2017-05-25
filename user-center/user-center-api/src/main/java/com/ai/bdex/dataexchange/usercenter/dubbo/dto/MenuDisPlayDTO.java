package com.ai.bdex.dataexchange.usercenter.dubbo.dto;

import com.ai.bdex.dataexchange.common.dto.BaseResponseDTO;

import java.util.List;

/**
 * Created by xiongqian on 2017/5/22.
 */
public class MenuDisPlayDTO extends BaseResponseDTO {

    private int menuId;
    private String menuType;//菜单类型（1：目录，2：功能）
    private String menuTitle;
    private int menuOrder;
    private int menuParentId;//父ID
    private String menuPic;//图标
    private String sysCode;//所属系统（01：mall，02：manager）
    private String menuModule;
    private String relativeMenuUrl;//相对URL
    private String absoluteMenuUrl;//绝对URL
    private String openWay; //打开方式（0、当前窗口，1、新窗口）

    private List<MenuDisPlayDTO> subMenus;

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getMenuModule() {
        return menuModule;
    }

    public void setMenuModule(String menuModule) {
        this.menuModule = menuModule;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public int getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(int menuOrder) {
        this.menuOrder = menuOrder;
    }

    public int getMenuParentId() {
        return menuParentId;
    }

    public void setMenuParentId(int menuParentId) {
        this.menuParentId = menuParentId;
    }

    public String getMenuPic() {
        return menuPic;
    }

    public void setMenuPic(String menuPic) {
        this.menuPic = menuPic;
    }

    public String getRelativeMenuUrl() {
        return relativeMenuUrl;
    }

    public void setRelativeMenuUrl(String relativeMenuUrl) {
        this.relativeMenuUrl = relativeMenuUrl;
    }

    public String getAbsoluteMenuUrl() {
        return absoluteMenuUrl;
    }

    public void setAbsoluteMenuUrl(String absoluteMenuUrl) {
        this.absoluteMenuUrl = absoluteMenuUrl;
    }

    public String getOpenWay() {
        return openWay;
    }

    public void setOpenWay(String openWay) {
        this.openWay = openWay;
    }

    public List<MenuDisPlayDTO> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<MenuDisPlayDTO> subMenus) {
        this.subMenus = subMenus;
    }
}
