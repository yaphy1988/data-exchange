package com.ai.bdex.dataexchange.usercenter.dubbo.impl;

import com.ai.bdex.dataexchange.constants.Constants;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dao.model.AuthRole2Menu;
import com.ai.bdex.dataexchange.usercenter.dao.model.BaseLoginUrl;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.MenuDisPlayDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IAuthBusiRSV;
import com.ai.bdex.dataexchange.usercenter.service.interfaces.IAuthBusiSV;
import com.ai.bdex.dataexchange.usercenter.service.interfaces.IBaseLoginUrlSV;
import com.ai.paas.util.CacheUtil;
import com.ai.paas.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiongqian on 2017/5/15.
 */
@Service("iAuthBusiRSV")
public class AuthBusiRSVImpl implements IAuthBusiRSV {

    private static Logger log = LoggerFactory.getLogger(AuthBusiRSVImpl.class);

    @Autowired
    private IBaseLoginUrlSV iBaseLoginUrlSV;

    @Autowired
    private IAuthBusiSV iAuthBusiSV;

    @Override
    public int loadUnLoginUrls() throws BusinessException {
        try {

            List<BaseLoginUrl> list = iBaseLoginUrlSV.getBaseLoginUrls();
            //删除旧的Urls
            CacheUtil.delItem(Constants.Cache.UN_LOGIN_URL_MAP);
            //刷新新的Urls
            if(list != null && list.isEmpty() == false){
                int size = 0;
                for (BaseLoginUrl url : list) {
                    if(StringUtil.isBlank(url.getSysCode())
                            || StringUtil.isBlank(url.getUrl())
                            || StringUtil.isBlank(url.getUnloginAccess())){
                        continue;
                    }

                    String key =Constants.Cache.UN_LOGIN_URL_PRE + url.getSysCode().trim() + "_" + url.getUrl().trim();

                    CacheUtil.addMapItem(Constants.Cache.UN_LOGIN_URL_MAP,key,url.getUnloginAccess().trim());

                    size++;
                }

                return size;
            }

        } catch (Exception e) {
            if (e instanceof BusinessException) {
                throw (BusinessException) e;
            }else {
                log.error("获取免登陆URL异常：" + e.getMessage());
                throw new BusinessException("获取免登陆URL异常:" + e.getMessage());
            }
        }

        return 0;
    }

    /**
     *获取角色菜单数据，用户刷新缓存
     * @return
     * @throws BusinessException
     */
    @Override
    public int loadRoleMenus()throws BusinessException{
        try {
            List<AuthRole2Menu> roles = iAuthBusiSV.getValidRoles();
            if(roles != null && roles.size()>0){

                Map<String,List<MenuDisPlayDTO>> datas = new HashMap<String,List<MenuDisPlayDTO>>();

                //查询菜单信息
                List<MenuDisPlayDTO> menus = iAuthBusiSV.getValidMenus();
                //放到map用于快速访问
                Map<String,MenuDisPlayDTO> menusMap = new HashMap<String,MenuDisPlayDTO>();
                for (MenuDisPlayDTO menu: menus) {
                    menusMap.put(menu.getMenuId()+"",menu);
                }

                //遍历角色找菜单
                for (AuthRole2Menu role: roles) {

                    String roleId = role.getRoleId()+"";
                    if(datas.containsKey(roleId) == false){
                        datas.put(roleId,new ArrayList<MenuDisPlayDTO>());
                    }

                    if(menusMap.containsKey(role.getMenuId()+"")) {
                        datas.get(roleId).add(menusMap.get(role.getMenuId() + ""));
                    }
                }

                int size = 0;
                for (String roleId:datas.keySet()) {
                    List<MenuDisPlayDTO> m =datas.get(roleId);
                    if(m != null && m.size()>0){
                        String key = Constants.Cache.AUTH_MENU_PRE + roleId;
                        String menuJson = datas.get(roleId).toString();
                        CacheUtil.addMapItem(Constants.Cache.AUTH_MENU_MAP,key,menuJson);
                        size++;
                    }
                }

                return size;
            }
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                throw (BusinessException) e;
            }else {
                log.error("获取角色菜单数据异常：" + e.getMessage());
                throw new BusinessException("获取角色菜单数据异常:" + e.getMessage());
            }
        }
        return 0;
    }

    /**
     * 获取用户菜单
     * @param staffId
     * @return
     * @throws BusinessException
     */
    @Override
    public List<MenuDisPlayDTO> getStaffAuthMenus(String staffId)throws BusinessException{
        try{
            return iAuthBusiSV.getMenusByStaffId(staffId);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof BusinessException) {
                throw (BusinessException) e;
            }else {
                log.error("获取用户菜单异常：" + e.getMessage());
                throw new BusinessException("获取用户菜单异常:" + e.getMessage());
            }
        }
    }
}
