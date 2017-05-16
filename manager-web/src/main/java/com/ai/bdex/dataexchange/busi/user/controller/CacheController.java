package com.ai.bdex.dataexchange.busi.user.controller;

import com.ai.bdex.dataexchange.constants.Constants;
import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.BaseLoginUrlDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IAuthBusiRSV;
import com.ai.paas.util.CacheUtil;
import com.ai.paas.utils.StringUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value="/cache")
public class CacheController {

	private static final Logger log = LoggerFactory.getLogger(CacheController.class);

	private static String UN_LOGIN_URL = "UN_LOGIN_URL";

	@DubboConsumer
	private IAuthBusiRSV iAuthBusiRSV;

	/**
	 *
	 * @param request
	 * @return
	 * @throws Exception
     */
	@RequestMapping(value="/init")
	public String init(HttpServletRequest request,Model model) throws Exception{

		String busiType = request.getParameter("busiType");

		if(UN_LOGIN_URL.equals(busiType)){
			this.loadUnLoginUrlToCache(model);
		}

		if(StringUtil.isBlank(busiType)){
			return "cache";
		}else{
			return "cache :: #loadResult";
		}
	}

	private void loadUnLoginUrlToCache(Model model){
		try {

			//查询要刷新的Urls
			List<BaseLoginUrlDTO> list = iAuthBusiRSV.loadUnLoginUrls();

			//删除旧的Urls
			CacheUtil.delItem(Constants.Cache.UN_LOGIN_URL_MAP);

			//刷新新的Urls
			if(list != null && list.size()>0) {
				int size = 0;
				for (BaseLoginUrlDTO dto : list) {
					if(StringUtil.isBlank(dto.getSysCode())
					|| StringUtil.isBlank(dto.getUrl())
					|| StringUtil.isBlank(dto.getUnloginAccess())){
						continue;
					}

					String key =Constants.Cache.UN_LOGIN_URL_PRE + dto.getSysCode().trim() + "_" + dto.getUrl().trim();

					CacheUtil.addMapItem(Constants.Cache.UN_LOGIN_URL_MAP,key,dto.getUnloginAccess().trim());

					size++;
				}
			}

			model.addAttribute("success",true);
			model.addAttribute("size",(list==null?0:list.size()));
		} catch (BusinessException e) {
			model.addAttribute("success",false);
			model.addAttribute("msg","加载失败："+ e.getMessage());
		}
	}
}