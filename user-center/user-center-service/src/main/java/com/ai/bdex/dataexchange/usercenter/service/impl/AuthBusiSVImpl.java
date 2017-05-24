package com.ai.bdex.dataexchange.usercenter.service.impl;

import com.ai.bdex.dataexchange.constants.Constants;
import com.ai.bdex.dataexchange.usercenter.dao.mapper.AuthMenuExtMapper;
import com.ai.bdex.dataexchange.usercenter.dao.mapper.AuthMenuMapper;
import com.ai.bdex.dataexchange.usercenter.dao.mapper.AuthRole2MenuMapper;
import com.ai.bdex.dataexchange.usercenter.dao.mapper.AuthRoleExtMapper;
import com.ai.bdex.dataexchange.usercenter.dao.model.*;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.MenuDisPlayDTO;
import com.ai.bdex.dataexchange.usercenter.service.interfaces.IAuthBusiSV;
import com.ai.paas.config.ModuleInfo;
import com.ai.paas.util.CacheUtil;
import com.ai.paas.util.SystemConfUtil;
import com.ai.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("iAuthBusiSV")
public class AuthBusiSVImpl implements IAuthBusiSV {
	private static final Logger log = Logger.getLogger(AuthBusiSVImpl.class);

    @Autowired
    private AuthMenuMapper menuMapper;

    @Autowired
    private AuthRole2MenuMapper roleMapper;

    @Autowired
    private AuthMenuExtMapper menuExtMapper;

    @Autowired
    private AuthRoleExtMapper roleExtMapper;

    public List<MenuDisPlayDTO> getValidMenus()throws Exception{

        //查询条件
        AuthMenuExample example = new AuthMenuExample();
        example.createCriteria().andStatusEqualTo(Constants.Page.STATUS_VALID);//有效的

        List<AuthMenu> list =  menuMapper.selectByExample(example);

        List<MenuDisPlayDTO> disMenus = this.convertDisPlayMenus(list);

        return disMenus;
    }

    public List<AuthRole2Menu> getValidRoles()throws Exception{
        AuthRole2MenuExample example = new AuthRole2MenuExample();
        example.createCriteria().andStatusEqualTo(Constants.Page.STATUS_VALID);//有效的
        return roleMapper.selectByExample(example);
    }

    /**
     * 通过用户ID查询角色
     * @param staffId
     * @return
     * @throws Exception
     */
    @Override
    public List<AuthRole> getRoleByStaffId(String staffId) throws Exception {
        return roleExtMapper.selectByStaffId(staffId);
    }

    /**
     * 根据用ID查询用户菜单
     * @return
     * @throws Exception
     */
    @Override
    public List<MenuDisPlayDTO> getMenusByStaffId(String staffId) throws Exception {

        List<AuthRole> roles = this.getRoleByStaffId(staffId);
        if(roles != null && roles.size()>0){
            List<MenuDisPlayDTO> menus = new ArrayList<MenuDisPlayDTO>();
            List<Integer> menusIds = new ArrayList<Integer>();
            for (AuthRole role: roles) {
                //从缓存获取菜单
                String key = Constants.Cache.AUTH_MENU_PRE + role.getRoleId();
                String menuJson = CacheUtil.getMapItem(Constants.Cache.AUTH_MENU_MAP,key);
                List<MenuDisPlayDTO> roleMenus = null;
                if(StringUtil.isBlank(menuJson)) {
                    //缓存查不到则查表
                    List<AuthMenu> qMenus = menuExtMapper.selectByRoleId(role.getRoleId());

                    List<MenuDisPlayDTO> disMenus = this.convertDisPlayMenus(qMenus);
                    if(disMenus != null){
                        menuJson = disMenus.toString();
                    }

                    //加入缓存
                    CacheUtil.addMapItem(Constants.Cache.AUTH_MENU_MAP,key,menuJson);
                }

                if(StringUtil.isBlank(menuJson)){
                    continue;
                }
                //json to list
                roleMenus = new ObjectMapper().readValue(menuJson.getBytes("UTF-8"), new TypeReference<List<MenuDisPlayDTO>>() {});

                //遍历菜单，移除角色包含的重复菜单
                for (MenuDisPlayDTO menu:roleMenus) {
                    if(menusIds.contains(menu.getMenuId()) == false){

                        //设置菜单全路径
                        if(StringUtils.isBlank(menu.getRelativeMenuUrl()) == false) {
                            String absoluteMenuUrl = menu.getRelativeMenuUrl();
                            ModuleInfo moduleInfo = SystemConfUtil.getSystemModuleInfo(menu.getSysCode(), menu.getMenuModule());
                            if (moduleInfo != null) {
                                absoluteMenuUrl = moduleInfo.genFullUrl() + absoluteMenuUrl;
                            }
                            menu.setAbsoluteMenuUrl(absoluteMenuUrl);
                        }

                        menus.add(menu);
                    }

                    menusIds.add(menu.getMenuId());
                }
            }

            //生成菜单结构
            Map<Integer,List<MenuDisPlayDTO>> menuMap = new HashMap<Integer,List<MenuDisPlayDTO>>();
            //先按父ID将菜单分组
            List<MenuDisPlayDTO> pMenus = new ArrayList<MenuDisPlayDTO>();
            for (MenuDisPlayDTO menu: menus) {
                int menuParentId = menu.getMenuParentId();
                if(menuMap.containsKey(menuParentId) == false){
                    menuMap.put(menuParentId,new ArrayList<MenuDisPlayDTO>());
                }
                menuMap.get(menuParentId).add(menu);

                if(menuParentId == 0){
                    pMenus.add(menu);
                }
            }

            //从父菜单开始生成菜单
            for (MenuDisPlayDTO menu: pMenus) {
                this.genDisPlayMenus(menu,menuMap);
            }

            return pMenus;
        }

        return null;
    }

    /**
     * 表中的菜单信息转展示数据
     * @param qMenus
     * @return
     */
    private List<MenuDisPlayDTO> convertDisPlayMenus(List<AuthMenu> qMenus){
        if(qMenus == null || qMenus.size() == 0) return null;

        List<MenuDisPlayDTO> disMenus = new ArrayList<MenuDisPlayDTO>();
        for (AuthMenu menu: qMenus) {

            MenuDisPlayDTO dto = new MenuDisPlayDTO();

            BeanUtils.copyProperties(menu,dto);

            if(StringUtils.isBlank(menu.getMenuUrl()) == false) {
                dto.setRelativeMenuUrl(menu.getMenuUrl());
            }

            disMenus.add(dto);
        }

        return disMenus;
    }

    /**
     * 递归生成菜单结构，并排序
     * @param menus
     * @param menuMap
     */
    private void genDisPlayMenus(MenuDisPlayDTO menus,Map<Integer,List<MenuDisPlayDTO>> menuMap){

        if(menuMap.containsKey(menus.getMenuId())){
            List<MenuDisPlayDTO> subMenus = menuMap.get(menus.getMenuId());
            if(subMenus != null && subMenus.size()>0){
                //排序
                Collections.sort(subMenus, new Comparator<MenuDisPlayDTO>() {
                    @Override
                    public int compare(MenuDisPlayDTO dto1, MenuDisPlayDTO dto2) {
                        if(dto1.getMenuOrder() > dto2.getMenuOrder()){
                            return 0;
                        }
                        return 1;
                    }
                });

                menus.setSubMenus(subMenus);

                //继续递归找子菜单
                for (MenuDisPlayDTO subMenu: subMenus) {
                    this.genDisPlayMenus(subMenu,menuMap);
                }
            }
        }
    }
}
