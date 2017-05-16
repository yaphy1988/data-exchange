package com.ai.bdex.dataexchange.usercenter.dubbo.impl;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dao.model.BaseLoginUrl;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.BaseLoginUrlDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IAuthBusiRSV;
import com.ai.bdex.dataexchange.usercenter.service.interfaces.IBaseLoginUrlSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiongqian on 2017/5/15.
 */
@Service("iAuthBusiRSV")
public class AuthBusiRSVImpl implements IAuthBusiRSV {

    private static final Logger log = Logger.getLogger(AuthBusiRSVImpl.class);

    @Autowired
    private IBaseLoginUrlSV iBaseLoginUrlSV;

    @Override
    public List<BaseLoginUrlDTO> loadUnLoginUrls() throws BusinessException {
        try {

            List<BaseLoginUrl> list = iBaseLoginUrlSV.getBaseLoginUrls();

            if(list != null && list.isEmpty() == false){

                List<BaseLoginUrlDTO> resList = new ArrayList<BaseLoginUrlDTO>();
                for (BaseLoginUrl url : list) {
                    BaseLoginUrlDTO  dto = new BaseLoginUrlDTO();
                    ObjectCopyUtil.copyObjValue(url,dto,"",false);
                    resList.add(dto);
                }
                return resList;
            }

        } catch (Exception e) {
            if (e instanceof BusinessException)
                throw (BusinessException) e;
            else {
                log.error("获取免登陆URL异常：" + e.getMessage());
                throw new BusinessException("获取免登陆URL异常:" + e.getMessage());
            }
        }

        return null;
    }
}
