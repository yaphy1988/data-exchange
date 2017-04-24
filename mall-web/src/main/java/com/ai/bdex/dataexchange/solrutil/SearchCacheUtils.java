//package com.ai.bdex.dataexchange.solrutil;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import javax.annotation.Resource;
//
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.collections.MapUtils;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.stereotype.Service;
//
//import com.ai.bdex.dataexchange.util.LocaleUtil;
//import com.ai.ecp.search.dubbo.dto.*;
//import com.ai.ecp.search.dubbo.interfaces.*;
//import com.ai.ecp.search.dubbo.search.util.EOperator;
//import com.ai.ecp.search.dubbo.search.util.SecConfigConfigurationWatcher;
//import com.ai.paas.config.ConfigurationCenter;
//import com.ai.paas.util.CacheUtil;
//import com.alibaba.dubbo.common.utils.LogUtil;
//import com.alibaba.fastjson.JSON;
//
///**
// * 
// * Title: ECP <br>
// * Project Name:mall-web <br>
// * Description: <br>
// * Date:2017年4月24日上午9:09:45  <br>
// * Copyright (c) 2017 asia All Rights Reserved <br>
// * 
// * @author gxq
// * @version  
// * @since JDK 1.6
// */
//@Service
//public class SearchCacheUtils {
//
//    public final static String MODULE = "【搜索引擎】SearchCacheUtils";
//
//    /*-------------------------------------------缓存KEY---------------------------------------------*/
//    
//    public static final String CACHE_KEY_SECCONFIGMAP="secConfigMap";
//    
//    public static final String CACHE_KEY_SECZKSTR="secZkStr";
//    
//    public static final String CACHE_KEY_SECSOLRSERVERLIST="secSolrServerList";
//    
//    public static final String CACHE_KEY_SECMASTERSOLRSERVER="secMasterSolrServer";
//    
//    public static final String CACHE_KEY_SECREDIRECTLIST="secRedirectList";
//    
//    public static final String CACHE_KEY_SECARGSMAP="secArgsMap";
//    
//    public static final String CACHE_KEY_SECFIELDTYPELIST="secFieldTypeList";
//    
//    public static final String CACHE_KEY_SECFIELDPROCESSORLIST="secFieldProcessorList";
//    
//    public static final String CACHE_KEY_SECOBJECTPROCESSORLIST="secObjectProcessorList";
//
//    /*-------------------------------------------静态缓存---------------------------------------------*/
//    //TODO 增加Zookeeper节点配置的异步缓存刷新通知功能（监听到节点变化则清空本地缓存，设置为null表示本地缓存未初始化（区分没数据和未初始化））
//
//    public static volatile HashMap<String,String> cacheSecArgsMap;
//
//    public static volatile Map<String, SecConfigRespDTO> cacheSecConfigRespDTOMap;
//
//    public static volatile Map<String,Map<String,String>> cacheFieldParam2FieldNameMap;
//
//    public static volatile String cacheZkHost ;
//
//    public static volatile List<String> cacheSolrServerList ;
//
//    public static volatile List<SecRedirectRespDTO> cacheSecRedirectRespDTOList;
//
//    public static volatile String cacheMasterServer;
//
//    /*-------------------------------------------服务注入---------------------------------------------*/
//
//    private static ISecConfigRSV secConfigRSV;
//
//    private static ISecRedirectRSV secRedirectRSV;
//
//    private static ISecSolrServerRSV secSolrServerRSV;
//
//    private static ISecZkRSV secZkRSV;
//    
//    private static ISecArgsRSV secArgsRSV;
//    
//    private static ISecFieldTypeRSV secFieldTypeRSV;
//    
//    private static ISecFieldProcessorRSV secFieldProcessorRSV;
//    
//    private static ISecObjectProcessorRSV secObjectProcessorRSV;
//
//    @Resource
//    public void setSecConfigRSV(ISecConfigRSV secConfigRSV) {
//        SearchCacheUtils.secConfigRSV = secConfigRSV;
//    }
//
//    @Resource
//    public void setSecRedirectRSV(ISecRedirectRSV secRedirectRSV) {
//        SearchCacheUtils.secRedirectRSV = secRedirectRSV;
//    }
//
//    @Resource
//    public void setSecSolrServerRSV(ISecSolrServerRSV secSolrServerRSV) {
//        SearchCacheUtils.secSolrServerRSV = secSolrServerRSV;
//    }
//
//    @Resource
//    public void setSecZkRSV(ISecZkRSV secZkRSV) {
//        SearchCacheUtils.secZkRSV = secZkRSV;
//    }
//    
//    @Resource
//    public void setSecArgsRSV(ISecArgsRSV secArgsRSV) {
//        SearchCacheUtils.secArgsRSV = secArgsRSV;
//    }
//    
//    @Resource
//    public void setSecFieldTypeRSV(ISecFieldTypeRSV secFieldTypeRSV) {
//        SearchCacheUtils.secFieldTypeRSV = secFieldTypeRSV;
//    }
//    
//    @Resource
//    public void setSecFieldProcessorRSV(ISecFieldProcessorRSV secFieldProcessorRSV) {
//        SearchCacheUtils.secFieldProcessorRSV = secFieldProcessorRSV;
//    }
//    
//    @Resource
//    public void setSecObjectProcessorRSV(ISecObjectProcessorRSV secObjectProcessorRSV) {
//        SearchCacheUtils.secObjectProcessorRSV = secObjectProcessorRSV;
//    }
//    @SuppressWarnings("unchecked")
//    public static List<SecFieldTypeRespDTO> getSecFieldTypeList() {
//
//        List<SecFieldTypeRespDTO> cacheSecFieldTypeRespDTOList = new ArrayList<SecFieldTypeRespDTO>();
//
//        // 缓存数据是最新的
//        if (CacheUtil.exists(CACHE_KEY_SECFIELDTYPELIST)) {
//
//            List<String> list = CacheUtil
//                    .getItemFromList(CACHE_KEY_SECFIELDTYPELIST);
//
//            if(CollectionUtils.isNotEmpty(list)){
//                for (String s : list) {
//
//                    SecFieldTypeRespDTO secFieldTypeRespDTO = JSON.parseObject(s, SecFieldTypeRespDTO.class);
//
//                    cacheSecFieldTypeRespDTOList.add(secFieldTypeRespDTO);
//
//                }
//            }
//
//        } else {
//
//            if (!CacheUtil.exists(CACHE_KEY_SECFIELDTYPELIST)) {
//                synchronized (SearchCacheUtils.class) {
//                    if (!CacheUtil.exists(CACHE_KEY_SECFIELDTYPELIST)) {
//                        // 缓存失效或第一次初始化缓存，重新查表
//                        SecFieldTypeReqDTO secFieldTypeReqDTO=new SecFieldTypeReqDTO();
//                        secFieldTypeReqDTO.setStatus(SearchConstants.STATUS_VALID);
//                        cacheSecFieldTypeRespDTOList = secFieldTypeRSV.listSecFieldType(secFieldTypeReqDTO);
//
//                        if (cacheSecFieldTypeRespDTOList != null && !cacheSecFieldTypeRespDTOList.isEmpty()) {
//
//                            for (SecFieldTypeRespDTO secFieldTypeRespDTO : cacheSecFieldTypeRespDTOList) {
//
//                                // 重设分布式缓存
//                                CacheUtil.addItemToList(CACHE_KEY_SECFIELDTYPELIST, JSON.toJSONString(secFieldTypeRespDTO));
//                            }
//
//                        }
//                    }
//                }
//            }
//
//        }
//
//        return cacheSecFieldTypeRespDTOList;
//    }
//    
//    @SuppressWarnings("unchecked")
//    public static List<SecFieldProcessorRespDTO> getSecFieldProcessorList() {
//
//        List<SecFieldProcessorRespDTO> cacheSecFieldProcessorRespDTOList = new ArrayList<SecFieldProcessorRespDTO>();
//
//        // 缓存数据是最新的
//        if (CacheUtil.exists(CACHE_KEY_SECFIELDPROCESSORLIST)) {
//
//            List<String> list = CacheUtil
//                    .getItemFromList(CACHE_KEY_SECFIELDPROCESSORLIST);
//
//            if(CollectionUtils.isNotEmpty(list)){
//                for (String s : list) {
//
//                    SecFieldProcessorRespDTO secFieldProcessorRespDTO = JSON.parseObject(s, SecFieldProcessorRespDTO.class);
//
//                    cacheSecFieldProcessorRespDTOList.add(secFieldProcessorRespDTO);
//
//                }
//            }
//
//        } else {
//
//            if (!CacheUtil.exists(CACHE_KEY_SECFIELDPROCESSORLIST)) {
//                synchronized (SearchCacheUtils.class) {
//                    if (!CacheUtil.exists(CACHE_KEY_SECFIELDPROCESSORLIST)) {
//                        // 缓存失效或第一次初始化缓存，重新查表
//                        SecFieldProcessorReqDTO secFieldProcessorReqDTO=new SecFieldProcessorReqDTO();
//                        secFieldProcessorReqDTO.setStatus(SearchConstants.STATUS_VALID);
//                        cacheSecFieldProcessorRespDTOList = secFieldProcessorRSV.listSecFieldProcessor(secFieldProcessorReqDTO);
//
//                        if (cacheSecFieldProcessorRespDTOList != null && !cacheSecFieldProcessorRespDTOList.isEmpty()) {
//
//                            for (SecFieldProcessorRespDTO secFieldProcessorRespDTO : cacheSecFieldProcessorRespDTOList) {
//
//                                // 重设分布式缓存
//                                CacheUtil.addItemToList(CACHE_KEY_SECFIELDPROCESSORLIST, JSON.toJSONString(secFieldProcessorRespDTO));
//                            }
//
//                        }
//                    }
//                }
//            }
//
//        }
//
//        return cacheSecFieldProcessorRespDTOList;
//    }
//    
//    @SuppressWarnings("unchecked")
//    public static List<SecObjectProcessorRespDTO> getSecObjectProcessorList() {
//
//        List<SecObjectProcessorRespDTO> cacheSecObjectProcessorRespDTOList = new ArrayList<SecObjectProcessorRespDTO>();
//
//        // 缓存数据是最新的
//        if (CacheUtil.exists(CACHE_KEY_SECOBJECTPROCESSORLIST)) {
//
//            List<String> list = CacheUtil
//                    .getItemFromList(CACHE_KEY_SECOBJECTPROCESSORLIST);
//
//            if(CollectionUtils.isNotEmpty(list)){
//                for (String s : list) {
//
//                    SecObjectProcessorRespDTO secObjectProcessorRespDTO = JSON.parseObject(s, SecObjectProcessorRespDTO.class);
//
//                    cacheSecObjectProcessorRespDTOList.add(secObjectProcessorRespDTO);
//
//                }
//            }
//
//        } else {
//
//            if (!CacheUtil.exists(CACHE_KEY_SECOBJECTPROCESSORLIST)) {
//                synchronized (SearchCacheUtils.class) {
//                    if (!CacheUtil.exists(CACHE_KEY_SECOBJECTPROCESSORLIST)) {
//                        // 缓存失效或第一次初始化缓存，重新查表
//                        SecObjectProcessorReqDTO secObjectProcessorReqDTO=new SecObjectProcessorReqDTO();
//                        secObjectProcessorReqDTO.setStatus(SearchConstants.STATUS_VALID);
//                        cacheSecObjectProcessorRespDTOList = secObjectProcessorRSV.listSecObjectProcessor(secObjectProcessorReqDTO);
//
//                        if (cacheSecObjectProcessorRespDTOList != null && !cacheSecObjectProcessorRespDTOList.isEmpty()) {
//
//                            for (SecObjectProcessorRespDTO secObjectProcessorRespDTO : cacheSecObjectProcessorRespDTOList) {
//
//                                // 重设分布式缓存
//                                CacheUtil.addItemToList(CACHE_KEY_SECOBJECTPROCESSORLIST, JSON.toJSONString(secObjectProcessorRespDTO));
//                            }
//
//                        }
//                    }
//                }
//            }
//
//        }
//
//        return cacheSecObjectProcessorRespDTOList;
//    }
//    
//    /**
//     * 缓存大的关联配置对象，使用DTO对象缓存。缓存服务包含数据验证
//     * 
//     * @return
//     * @throws SearchException
//     */
//    public static Map<String, SecConfigRespDTO> getSecConfigReqDTOMap() throws SearchException {
//
//        //存在本地缓存
//        if(cacheSecConfigRespDTOMap==null){
//
//            //区分没数据和未初始化
//            Map<String, SecConfigRespDTO> cacheSecConfigRespDTOMap_ = null;
//
//            // 缓存数据是最新的
//            if (CacheUtil.exists(CACHE_KEY_SECCONFIGMAP)) {
//                Map<String, String> map = com.ai.paas.utils.CacheUtil
//                        .getHashMap(CACHE_KEY_SECCONFIGMAP);
//                if(MapUtils.isNotEmpty(map)){
//                    cacheSecConfigRespDTOMap_ = new HashMap<String, SecConfigRespDTO>();
//                    for (Entry<String, String> entry : map.entrySet()) {
//
//                        // JSON--->SecConfigRespDTO
//                        SecConfigRespDTO secConfigRespDTO = JSON.parseObject(entry.getValue(), SecConfigRespDTO.class);
//                        cacheSecConfigRespDTOMap_.put(entry.getKey(), secConfigRespDTO);
//                    }
//                }else{
//                    cacheSecConfigRespDTOMap_ = querySecConfigRespDTOListFromDB();
//                }
//
//            } else {
//
//                if (!CacheUtil.exists(CACHE_KEY_SECCONFIGMAP)) {
//                    cacheSecConfigRespDTOMap_ = querySecConfigRespDTOListFromDB();
//                }
//
//            }
//
//            //设置本地缓存
//            cacheSecConfigRespDTOMap=cacheSecConfigRespDTOMap_;
//        }
//
//        if (cacheSecConfigRespDTOMap == null || cacheSecConfigRespDTOMap.isEmpty()) {
//            throw new SearchException(ResourceMsgUtil.getMessage(
//                    SearchMessageKeyContants.Error.KEY_ERROR_SECCONFIG_EMPTY, null,
//                    LocaleUtil.getLocale()));
//        }
//
//        return cacheSecConfigRespDTOMap;
//    }
//
//	private static Map<String, SecConfigRespDTO> querySecConfigRespDTOListFromDB() {
//		Map<String, SecConfigRespDTO> cacheSecConfigRespDTOMap_=null;
//		synchronized (SearchCacheUtils.class) {
//		    if (!CacheUtil.exists(CACHE_KEY_SECCONFIGMAP)) {
//                cacheSecConfigRespDTOMap_ = new HashMap<String, SecConfigRespDTO>();
//		        // 缓存失效或第一次初始化缓存，重新查表
//		        SecConfigReqDTO secConfigReqDTO=new SecConfigReqDTO();
//		        secConfigReqDTO.setStatus(SearchConstants.STATUS_VALID);
//		        List<SecConfigRespDTO> secConfigRespDTOList = secConfigRSV.listSecConfigAll(secConfigReqDTO);
//
//		        if (secConfigRespDTOList != null && !secConfigRespDTOList.isEmpty()) {
//
//		            HashMap<String, String> map = new HashMap<String, String>(
//		                    secConfigRespDTOList.size());
//
//		            for (SecConfigRespDTO secConfigRespDTO : secConfigRespDTOList) {
//		                cacheSecConfigRespDTOMap_.put(secConfigRespDTO.getConfigCollectionName(),
//		                        secConfigRespDTO);
//		                map.put(secConfigRespDTO.getConfigCollectionName(),
//		                        JSON.toJSONString(secConfigRespDTO));
//		            }
//
//		            // 重设分布式缓存
//		            CacheUtil.setHashMap(CACHE_KEY_SECCONFIGMAP, map);
//
//		        }
//		    }
//		}
//		return cacheSecConfigRespDTOMap_;
//	}
//
//    /**
//     * 缓存大的关联配置对象，使用DTO对象缓存。缓存服务包含数据验证
//     *
//     * @param collectionName
//     * @return
//     * @throws SearchException
//     */
//    public static Map<String,String> getSecConfigFieldParam2FieldNameMap(String collectionName) throws SearchException {
//
//        if(cacheFieldParam2FieldNameMap==null){
//
//            Map<String, SecConfigRespDTO> cacheSecConfigRespDTOMap=getSecConfigReqDTOMap();
//
//            //区分没数据和未初始化
//            Map<String,Map<String,String>> cacheFieldParam2FieldNameMap_ = new HashMap<String,Map<String,String>>(
//                    cacheSecConfigRespDTOMap.size());
//
//            for (SecConfigRespDTO secConfigRespDTO : cacheSecConfigRespDTOMap.values()) {
//
//                Map<String,String> map=new HashMap<String,String>();
//                cacheFieldParam2FieldNameMap_.put(secConfigRespDTO.getConfigCollectionName(),map);
//
//                if(CollectionUtils.isEmpty(secConfigRespDTO.getSecObjectRespDTOList())){
//                    continue;
//                }
//
//                for(SecObjectRespDTO secObjectRespDTO:secConfigRespDTO.getSecObjectRespDTOList()){
//                    if(CollectionUtils.isEmpty(secObjectRespDTO.getSecFieldRespDTOList())){
//                        continue;
//                    }
//                    for(SecFieldRespDTO secFieldRespDTO:secObjectRespDTO.getSecFieldRespDTOList()){
//                        if(StringUtils.isNotBlank(secFieldRespDTO.getFieldParams())){
//                            map.put(secFieldRespDTO.getFieldParams(),secFieldRespDTO.getFieldBeanFieldName());
//                        }
//                    }
//                }
//            }
//
//            //设置本地缓存
//            cacheFieldParam2FieldNameMap=cacheFieldParam2FieldNameMap_;
//        }
//
//        if(!cacheFieldParam2FieldNameMap.containsKey(collectionName)){
//            throw new SearchException("集合不存在");
//        }
//
//        return cacheFieldParam2FieldNameMap.get(collectionName);
//    }
//
//    /**
//     * 稳定数据，且可直接缓存串。缓存服务包含数据验证
//     * 
//     * @return
//     * @throws SearchException
//     */
//    public static String getSecZkStr() throws SearchException {
//
//        //存在本地缓存
//        if(cacheZkHost==null){
//            //区分没数据和未初始化
//            String zkHost = "";
//
//            // 缓存数据是最新的
//            if (CacheUtil.exists(CACHE_KEY_SECZKSTR)) {
//
//                Object o=CacheUtil.getItem(CACHE_KEY_SECZKSTR);
//                if(o != null){
//                    zkHost = (String) o;
//                }
//
//            } else {
//
//                if (!CacheUtil.exists(CACHE_KEY_SECZKSTR)) {
//                    synchronized (SearchCacheUtils.class) {
//                        if (!CacheUtil.exists(CACHE_KEY_SECZKSTR)) {
//                            // 缓存失效或第一次初始化缓存，重新查表
//                            List<SecZkRespDTO> secZkRespDTOList = secZkRSV.listSecZk();
//
//                            if (secZkRespDTOList != null && !secZkRespDTOList.isEmpty()) {
//
//                                StringBuffer zkHostSB = new StringBuffer();
//
//                                for (SecZkRespDTO secZkRespDTO : secZkRespDTOList) {
//                                    if (zkHostSB.length() > 0)
//                                        zkHostSB.append(SearchConstants.COMMA);
//
//                                    zkHostSB.append(secZkRespDTO.getZkIp());
//                                    zkHostSB.append(SearchConstants.COLON);
//                                    zkHostSB.append(secZkRespDTO.getZkPort());
//                                }
//
//                                // 重设分布式缓存
//                                CacheUtil.addItem(CACHE_KEY_SECZKSTR, zkHostSB.toString());
//
//                                zkHost = zkHostSB.toString();
//
//                            }
//                        }
//                    }
//                }
//
//            }
//
//            //设置本地缓存
//            cacheZkHost=zkHost;
//        }
//
//        if (StringUtils.isBlank(cacheZkHost)) {
//            throw new SearchException(ResourceMsgUtil.getMessage(
//                    SearchMessageKeyContants.Error.KEY_ERROR_ZK_EMPTY, null,
//                    LocaleUtil.getLocale()));
//        }
//
//        return cacheZkHost;
//    }
//
//    /**
//     * 稳定数据，直接缓存基本数据 结构:<b>ip:port</b>。缓存服务包含数据验证
//     * 
//     * @return
//     * @throws SearchException
//     */
//    @SuppressWarnings("unchecked")
//    public static List<String> getSecSolrServerList() throws SearchException {
//
//        //存在本地缓存
//        if(cacheSolrServerList==null){
//            //区分没数据和未初始化
//            List<String> solrServerList = new ArrayList<String>();
//
//            String item;
//
//            // 缓存数据是最新的
//            if (CacheUtil.exists(CACHE_KEY_SECSOLRSERVERLIST)) {
//
//                List list=CacheUtil.getItemFromList(CACHE_KEY_SECSOLRSERVERLIST);
//
//                if(list!=null){
//                    solrServerList = list;
//                }
//
//            } else {
//
//                if (!CacheUtil.exists(CACHE_KEY_SECSOLRSERVERLIST)) {
//                    synchronized (SearchCacheUtils.class) {
//                        if (!CacheUtil.exists(CACHE_KEY_SECSOLRSERVERLIST)) {
//                            // 缓存失效或第一次初始化缓存，重新查表
//                            List<SecSolrServerRespDTO> secSolrServerRespDTOList = secSolrServerRSV
//                                    .listSecSolrServer();
//
//                            if (secSolrServerRespDTOList != null && !secSolrServerRespDTOList.isEmpty()) {
//
//                                solrServerList = new ArrayList<String>();
//
//                                for (SecSolrServerRespDTO secSolrServerRespDTO : secSolrServerRespDTOList) {
//
//                                    item=secSolrServerRespDTO.getSolrserverIp()
//                                            + SearchConstants.COLON + secSolrServerRespDTO.getSolrserverPort();
//
//                                    solrServerList.add(item);
//
//                                    // 重设分布式缓存
//                                    CacheUtil.addItemToList(CACHE_KEY_SECSOLRSERVERLIST, item);
//
//                                }
//
//                            }
//                        }
//                    }
//                }
//
//            }
//
//            //设置本地缓存
//            cacheSolrServerList=solrServerList;
//        }
//
//        if (cacheSolrServerList == null || cacheSolrServerList.isEmpty()) {
//            throw new SearchException(ResourceMsgUtil.getMessage(
//                    SearchMessageKeyContants.Error.KEY_ERROR_SOLRCLOUD_EMPTY, null,
//                    LocaleUtil.getLocale()));
//        }
//
//        return cacheSolrServerList;
//    }
//
//    /**
//     * 稳定数据，直接缓存基本数据 结构:<b>ip:port</b>。缓存服务包含数据验证
//     * 
//     * @return
//     * @throws SearchException
//     */
//    public static String getSecMasterSolrServer() throws SearchException {
//
//        //存在本地缓存
//        if(cacheMasterServer==null){
//            //区分没数据和未初始化
//            String masterServer="";
//
//            // 缓存数据是最新的
//            if (CacheUtil.exists(CACHE_KEY_SECMASTERSOLRSERVER)) {
//
//                Object o=CacheUtil.getItem(CACHE_KEY_SECMASTERSOLRSERVER);
//                if(o!=null){
//                    masterServer= (String)o;
//                }
//
//            } else {
//
//                if (!CacheUtil.exists(CACHE_KEY_SECMASTERSOLRSERVER)) {
//                    synchronized (SearchCacheUtils.class) {
//                        if (!CacheUtil.exists(CACHE_KEY_SECMASTERSOLRSERVER)) {
//                            // 缓存失效或第一次初始化缓存，重新查表
//                            List<SecSolrServerRespDTO> secSolrServerRespDTOList = secSolrServerRSV
//                                    .listSecSolrServer();
//
//                            if (secSolrServerRespDTOList != null && !secSolrServerRespDTOList.isEmpty()) {
//
//                                for (SecSolrServerRespDTO secSolrServerRespDTO : secSolrServerRespDTOList) {
//
//                                    //主服务器
//                                    if(StringUtils.equals(secSolrServerRespDTO.getIfMaster(),SearchConstants.STATUS_1)){
//                                        masterServer=secSolrServerRespDTO.getSolrserverIp()
//                                                + SearchConstants.COLON + secSolrServerRespDTO.getSolrserverPort();
//
//                                        // 重设分布式缓存
//                                        CacheUtil.addItem(CACHE_KEY_SECMASTERSOLRSERVER, masterServer);
//                                        break;
//                                    }
//
//                                }
//
//                            }
//                        }
//                    }
//                }
//
//            }
//
//            //设置本地缓存
//            cacheMasterServer=masterServer;
//        }
//
//        if (StringUtils.isBlank(cacheMasterServer)) {
//            throw new SearchException(ResourceMsgUtil.getMessage(
//                    SearchMessageKeyContants.Error.KEY_ERROR_MASTER_NOTFOUND, null,
//                    LocaleUtil.getLocale()));
//        }
//
//        return cacheMasterServer;
//    }
//    
//    /**
//     * 缓存搜索重定向数据，可以为空
//     * 
//     * @return
//     * @throws SearchException
//     */
//    @SuppressWarnings("unchecked")
//    public static List<SecRedirectRespDTO> getSecRedirectList() {
//
//        //存在本地缓存
//        if(cacheSecRedirectRespDTOList==null){
//            //区分没数据和未初始化
//            List<SecRedirectRespDTO> cacheSecRedirectRespDTOList_ = new ArrayList<SecRedirectRespDTO>();
//
//            // 缓存数据是最新的
//            if (CacheUtil.exists(CACHE_KEY_SECREDIRECTLIST)) {
//
//                List<String> list = CacheUtil
//                        .getItemFromList(CACHE_KEY_SECREDIRECTLIST);
//
//                if(CollectionUtils.isNotEmpty(list)){
//                    for (String s : list) {
//
//                        // JSON--->SecRedirectRespDTO
//                        SecRedirectRespDTO secRedirectRespDTO = JSON.parseObject(s, SecRedirectRespDTO.class);
//
//                        cacheSecRedirectRespDTOList_.add(secRedirectRespDTO);
//
//                    }
//                }
//
//            } else {
//
//                if (!CacheUtil.exists(CACHE_KEY_SECREDIRECTLIST)) {
//                    synchronized (SearchCacheUtils.class) {
//                        if (!CacheUtil.exists(CACHE_KEY_SECREDIRECTLIST)) {
//                            // 缓存失效或第一次初始化缓存，重新查表
//                            cacheSecRedirectRespDTOList_ = secRedirectRSV.listSecRedirect();
//
//                            if (cacheSecRedirectRespDTOList_ != null && !cacheSecRedirectRespDTOList_.isEmpty()) {
//
//                                for (SecRedirectRespDTO secRedirectRespDTO : cacheSecRedirectRespDTOList_) {
//
//                                    // 重设分布式缓存
//                                    CacheUtil.addItemToList(CACHE_KEY_SECREDIRECTLIST, JSON.toJSONString(secRedirectRespDTO));
//                                }
//
//                            }
//                        }
//                    }
//                }
//
//            }
//
//            //设置本地缓存
//            cacheSecRedirectRespDTOList=cacheSecRedirectRespDTOList_;
//        }
//
//        // 重定向配置允许为空
//        return cacheSecRedirectRespDTOList;
//    }
//    
//    /**
//     * 搜索引擎参数
//     * 
//     * @return
//     * @throws SearchException
//     */
//    public static HashMap<String,String> getSecArgsMap(){
//
//        //存在本地缓存
//        if(cacheSecArgsMap==null){
//            //区分没数据和未初始化
//            HashMap<String,String> cacheSecArgsMap_=new HashMap<String, String>();;
//
//            // 缓存数据是最新的
//            if (CacheUtil.exists(CACHE_KEY_SECARGSMAP)) {
//
//                Map<String, String> map = CacheUtil.getMap(CACHE_KEY_SECARGSMAP);
//
//                if(map!=null){
//                    cacheSecArgsMap_=(HashMap<String, String>) map;
//                }
//
//            } else {
//
//                if (!CacheUtil.exists(CACHE_KEY_SECARGSMAP)) {
//                    synchronized (SearchCacheUtils.class) {
//                        if (!CacheUtil.exists(CACHE_KEY_SECARGSMAP)) {
//                            // 缓存失效或第一次初始化缓存，重新查表
//                            List<SecArgsRespDTO> secArgsRespDTOList = secArgsRSV.listSecArgs();
//
//                            if (secArgsRespDTOList != null && !secArgsRespDTOList.isEmpty()) {
//
//                                // 重设本地缓存
//                                cacheSecArgsMap_ = new HashMap<String, String>(
//                                        secArgsRespDTOList.size());
//
//                                for (SecArgsRespDTO secArgsRespDTO : secArgsRespDTOList) {
//                                    cacheSecArgsMap_.put(secArgsRespDTO.getArgsKey(),
//                                            secArgsRespDTO.getArgsValue());
//                                }
//
//                                //参数校验
//                                if(Integer.parseInt(String.valueOf(cacheSecArgsMap_.get("PAGE_FULLIMPORT")))<100){
//                                    cacheSecArgsMap_.put("PAGE_FULLIMPORT","100");
//                                }
//                                if(Integer.parseInt(String.valueOf(cacheSecArgsMap_.get("NTHREADS")))<15){
//                                    cacheSecArgsMap_.put("NTHREADS", "15");
//                                }
//                                if(Integer.parseInt(String.valueOf(cacheSecArgsMap_.get("SHARD_NUM")))<1){
//                                    cacheSecArgsMap_.put("SHARD_NUM", "1");
//                                }
//                                if(Integer.parseInt(String.valueOf(cacheSecArgsMap_.get("REPLICATION_FACTOR")))<1){
//                                    cacheSecArgsMap_.put("REPLICATION_FACTOR", "1");
//                                }
//                                if(Integer.parseInt(String.valueOf(cacheSecArgsMap_.get("MAXSHARDSPERNODE")))<1){
//                                    cacheSecArgsMap_.put("MAXSHARDSPERNODE", "1");
//                                }
//                                if(Integer.parseInt(String.valueOf(cacheSecArgsMap_.get("SUGGESTION_MAXTERMS")))<3){
//                                    cacheSecArgsMap_.put("SUGGESTION_MAXTERMS", "3");
//                                }
//                                //时空考虑，不允许分词组合长度超过5
//                                if(Integer.parseInt(String.valueOf(cacheSecArgsMap_.get("SUGGESTION_MAXTERMS")))>5){
//                                    cacheSecArgsMap_.put("SUGGESTION_MAXTERMS", "5");
//                                }
//
//                                // 重设分布式缓存
//                                CacheUtil.setHashMap(CACHE_KEY_SECARGSMAP, cacheSecArgsMap_);
//
//                            }
//                        }
//                    }
//                }
//
//            }
//
//            if (cacheSecArgsMap_ == null || cacheSecArgsMap_.isEmpty()) {
////            throw new SearchException(ResourceMsgUtil.getMessage(
////                    SearchMessageKeyContants.ExeException.KEY_ERROR_SECCONFIG_EMPTY, null,
////                    LocaleUtil.getLocale()));
//                if (cacheSecArgsMap_ == null){
//                    cacheSecArgsMap_=new HashMap<String,String>();
//                }
//                cacheSecArgsMap_.put("PAGE_FULLIMPORT","100");
//                cacheSecArgsMap_.put("NTHREADS", "15");
//                cacheSecArgsMap_.put("LOG_INTERVAL", "5");
//                cacheSecArgsMap_.put("SHARD_NUM", "1");
//                cacheSecArgsMap_.put("REPLICATION_FACTOR", "1");
//                cacheSecArgsMap_.put("DEPLOY_TYPE", "1");
//                cacheSecArgsMap_.put("MAXSHARDSPERNODE", "1");
//                cacheSecArgsMap_.put("SUGGESTION_MAXTERMS", "3");
//                cacheSecArgsMap_.put("REC_CB_PROPERTIES_OP", EOperator.AND.getName());
//                cacheSecArgsMap_.put("REC_PERSONAS_TAG_LIMIT", "3");
//                cacheSecArgsMap_.put("REC_PERSONAS_TAG_OP", EOperator.AND.getName());
//            }
//
//            //设置本地缓存
//            cacheSecArgsMap=cacheSecArgsMap_;
//        }
//
//        return cacheSecArgsMap;
//    }
//
//    public static int getSuggestionMaxterms(){
//        return Integer.parseInt(getSecArgsMap().get("SUGGESTION_MAXTERMS"));
//    }
//
//    /**
//     * 静态缓存初始化
//     */
//    public static void init(){
//        try {
//            getSecArgsMap();
//            getSecConfigReqDTOMap();
//            getSecMasterSolrServer();
//            getSecZkStr();
//            getSecRedirectList();
//            getSecSolrServerList();
//        } catch (SearchException e) {
//            LogUtil.error(MODULE, "静态缓存初始化异常", e);
//        }
//    }
//
//    /**
//     * 清理搜索配置缓存数据
//     */
//    public static void cleanRedis(){
//        CacheUtil.delItem(CACHE_KEY_SECCONFIGMAP);
//        CacheUtil.delItem(CACHE_KEY_SECREDIRECTLIST);
//        CacheUtil.delItem(CACHE_KEY_SECSOLRSERVERLIST);
//        CacheUtil.delItem(CACHE_KEY_SECMASTERSOLRSERVER);
//        CacheUtil.delItem(CACHE_KEY_SECZKSTR);
//        CacheUtil.delItem(CACHE_KEY_SECARGSMAP);
//        CacheUtil.delItem(CACHE_KEY_SECFIELDTYPELIST);
//        CacheUtil.delItem(CACHE_KEY_SECFIELDPROCESSORLIST);
//        CacheUtil.delItem(CACHE_KEY_SECOBJECTPROCESSORLIST);
//    }
//
//    /**
//     * 清理搜索配置缓存数据
//     */
//    public static void cleanLocale(){
//        cacheSecConfigRespDTOMap=null;
//        cacheFieldParam2FieldNameMap=null;
//        cacheSecArgsMap=null;
//        cacheMasterServer=null;
//        cacheSolrServerList=null;
//        cacheZkHost=null;
//        cacheSecRedirectRespDTOList=null;
//    }
//
//    /**
//     * 清理搜索配置缓存数据
//     */
//    public static void notityWatchers(){
//
//        //修改Zookeeper节点数据，触发Watchers监听执行（更新各个Watcher所在的本地缓存）
//        setZkData(SecConfigConfigurationWatcher.CONFPATH,"1");
//
//    }
//
//    /**
//     *  写Zookeeper节点数据
//     */
//    public static void setZkData(String path, String data){
//
//        try {
//            ConfigurationCenter cc=PaasContextHolder.getContext().getBean(ConfigurationCenter.class);
//            cc.setData(path,data);
//
//            LogUtil.info(MODULE, "zk节点数据修改成功。path："+path+"；data："+data);
//        }catch (Exception e){
//            LogUtil.error(MODULE,"zk节点数据修改失败。path："+path+"；data："+data,e);
//        }
//
//
//    }
//    
//    /**
//     * 清理搜索配置缓存数据，用于管理平台等前店工程的直接缓存清空
//     */
//    public static void clean(){
//
//        //清空Redis分布式缓存在这，DAO层也有一个同样的处理
//        cleanRedis();
//
//        //修改Zookeeper节点数据，触发Watchers监听执行（更新各个Watcher所在的本地缓存）
//        notityWatchers();
//
//    }
//
//}
