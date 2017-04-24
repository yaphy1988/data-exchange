//package com.ai.bdex.dataexchange.solrutil;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.TreeMap;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.collections.MapUtils;
//import org.apache.commons.lang.StringUtils;
//import org.apache.lucene.search.SortField;
//import org.apache.solr.client.solrj.SolrQuery;
//import org.apache.solr.client.solrj.SolrRequest.METHOD;
//import org.apache.solr.client.solrj.SolrServer;
//import org.apache.solr.client.solrj.SolrServerException;
//import org.apache.solr.client.solrj.response.QueryResponse;
//import org.apache.solr.common.SolrDocumentList;
//import org.apache.solr.common.params.CommonParams;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.ai.bdex.dataexchange.util.LocaleUtil;
//import com.ai.ecp.search.dubbo.dto.SecConfigRespDTO;
//import com.ai.ecp.search.dubbo.dto.SecFieldRespDTO;
//import com.ai.ecp.search.dubbo.dto.SecObjectRespDTO;
//import com.ai.ecp.search.dubbo.search.QueryBuilderResp;
//import com.ai.ecp.search.dubbo.search.SearchParam;
//import com.ai.ecp.search.dubbo.search.ext.filter.ExtraQueryInfo;
//import com.ai.ecp.search.dubbo.search.ext.filter.QueryFilter;
//import com.ai.ecp.search.dubbo.search.result.binder.BaseResultBinder;
//import com.ai.ecp.search.dubbo.search.result.binder.BaseResultBinder.EBinderType;
//import com.ai.ecp.search.dubbo.search.result.binder.BeanResultBinder;
//import com.ai.ecp.search.dubbo.search.result.binder.MapResultBinder;
//import com.ai.ecp.search.dubbo.search.result.binder.ReusltBindingException;
//import com.ai.ecp.search.dubbo.search.support.*;
//import com.ai.ecp.search.dubbo.search.support.RedirectSupport.RedirectResult;
//import com.ai.ecp.search.dubbo.search.util.*;
//import com.ai.paas.utils.ResourceMsgUtil;
//import com.alibaba.dubbo.common.utils.Log.
//
///**
// * 
// * Title: ECP <br>
// * Project Name:mall-web <br>
// * Description: <br>
// * Date:2017年4月24日上午9:01:10  <br>
// * Copyright (c) 2017 asia All Rights Reserved <br>
// * 
// * @author gxq
// * @version  
// * @since JDK 1.6
// */
//public class QueryComp extends BaseComp {
//    
//    private static final Logger Log = LoggerFactory.getLogger(QueryComp.class.getName());
//    private final static String MODULE = "【搜索引擎】QueryComp";
//    
//    private final static String TEXT="Query异常";
//
//    @SuppressWarnings({ "unchecked", "rawtypes" })
//    public static <T> SearchResult<T> search(EBinderType binderType, SearchParam searchParam,
//            ExtraQueryInfo extraQueryInfo, List<QueryFilter> filterList) {
//
//        Log.info(MODULE, "ResultBinder实例：【"+binderType.getBinderType()+"】");
//
//        SearchResult<T> searchResult = new SearchResult<T>();
//
//        //联合查询需要做关键字重定向检查
//        if(searchParam.isIfUnionSearch()&&searchParam.isIfRedirectCheck()){
//            RedirectResult redirectResult = RedirectSupport.redirect(searchParam.getKeyword());
//
//            searchResult.setRedirectType(redirectResult.getRedirectType());
//
//            if (StringUtils.equals(redirectResult.getRedirectType(), SearchConstants.Redirect.REDIRECT_TYPE_URL)) {
//
//                searchResult.setRedirectUrl(redirectResult.getResult());
//
//                // 如果重定向指向一个URL，则停止搜索
//                return searchResult;
//            }
//
//            //重定向关键字设置
//            searchParam.setKeyword(redirectResult.getResult());
//        }else{
//            // 非联合查询，且检索文法为空的情况下（检索文法优先级高于检索关键字，自定义检索文法无需重定向），也需要做关键字重定向检查重定向
//            if(StringUtils.isBlank(searchParam.getGrammar())&&searchParam.isIfRedirectCheck()){
//                RedirectResult redirectResult = RedirectSupport.redirect(searchParam.getKeyword());
//
//                searchResult.setRedirectType(redirectResult.getRedirectType());
//
//                if (StringUtils.equals(redirectResult.getRedirectType(), SearchConstants.Redirect.REDIRECT_TYPE_URL)) {
//
//                    searchResult.setRedirectUrl(redirectResult.getResult());
//
//                    // 如果重定向指向一个URL，则停止搜索
//                    return searchResult;
//                }
//
//                //重定向关键字设置
//                searchParam.setKeyword(redirectResult.getResult());
//            }
//        }
//
//        //检索关键字不能为空
//        if(StringUtils.isBlank(searchParam.getKeyword())){
//            searchParam.setKeyword(SearchConstants.STAR);
//        }
//
//        // 集合名称是否为空
//        if (!validateCollectionName(searchParam, searchResult)) {
//            return searchResult;
//        }
//
//        // 获取集合名称所指向的索引配置并验证，若验证不通过也一并返回null
//        long startTime=System.currentTimeMillis();
//        SecConfigRespDTO currSecConfigRespDTO = getAndValidateSecConfig(searchParam,
//                searchResult);
//        long endTime=System.currentTimeMillis();
//        Log.info(MODULE, "getAndValidateSecConfig耗时：【"+(endTime-startTime)+"ms】");
//
//        if (currSecConfigRespDTO == null) {
//            return searchResult;
//        }
//
//        // 获取搜索数据对象列表
//        List<SecObjectRespDTO> secObjectRespDTOList = currSecConfigRespDTO.getSecObjectRespDTOList();
//        if (secObjectRespDTOList == null || secObjectRespDTOList.isEmpty()) {
//            searchResult.setMessage(ResourceMsgUtil.getMessage(
//                    SearchMessageKeyContants.Info.KEY_INFO_COLLECTION_NOTSECOBJECTREGISTERD, new String[]{searchParam.getCollectionName()},
//                    LocaleUtil.getLocale()));
//            return searchResult;
//        }
//
//        List<SecFieldRespDTO> secFieldRespDTOList = new ArrayList<SecFieldRespDTO>();
//        boolean insidePager=false;
//
//        // 获取搜索数据对象字段列表
//        for (SecObjectRespDTO secObjectRespDTO : secObjectRespDTOList) {
//            if(org.apache.commons.collections.CollectionUtils.isNotEmpty(secObjectRespDTO.getSecFieldRespDTOList())){
//                secFieldRespDTOList.addAll(secObjectRespDTO.getSecFieldRespDTOList());
//                if(StringUtils.equals(SearchConstants.STATUS_1, secObjectRespDTO.getInsidepager())){
//                    insidePager=true;
//                }
//            }
//        }
//
//        if (secFieldRespDTOList.isEmpty()) {
//            searchResult.setMessage(ResourceMsgUtil.getMessage(
//                    SearchMessageKeyContants.Info.KEY_INFO_COLLECTION_NOTSECFIELDREGISTERD, new String[]{searchParam.getCollectionName()},
//                    LocaleUtil.getLocale()));
//            return searchResult;
//        }
//
//        // 组装BeanFieldName-IndexName映射信息，同一个索引配置中的多个搜索数据对象字段相同配置部分自动覆盖
//        Map<String, String> beanFieldName2IndexNameMap = new HashMap<String, String>();
//        beanFieldName2IndexNameMap.put(SearchConstants.ID, SearchConstants.ID);
//        beanFieldName2IndexNameMap.put(SearchConstants.FIELD_ID_PARENT, SearchConstants.ID_PARENT);
//        beanFieldName2IndexNameMap.put(SearchConstants.FIELD_ID_CHILD, SearchConstants.ID_CHILD);
//
//        Map<String, String> indexName2BeanFieldNameMap = new HashMap<String, String>();
//        indexName2BeanFieldNameMap.put(SearchConstants.ID, SearchConstants.ID);
//        indexName2BeanFieldNameMap.put(SearchConstants.ID_PARENT,SearchConstants.FIELD_ID_PARENT);
//        indexName2BeanFieldNameMap.put(SearchConstants.ID_CHILD,SearchConstants.FIELD_ID_CHILD);
//
//        Map<String, String> indexName2BeanFieldNameCnMap = new HashMap<String, String>();
//        indexName2BeanFieldNameCnMap.put(SearchConstants.ID, "主键编码");
//        indexName2BeanFieldNameCnMap.put(SearchConstants.ID_PARENT,"外部主键编码");
//        indexName2BeanFieldNameCnMap.put(SearchConstants.ID_CHILD,"内部主键编码");
//
//        //字段属性列表：MapResultBinder默认返回所有字段
//        List<String> fieldList=new ArrayList<String>();
//        for (SecFieldRespDTO secFieldRespDTO : secFieldRespDTOList) {
//            beanFieldName2IndexNameMap.put(secFieldRespDTO.getFieldBeanFieldName(),
//                    secFieldRespDTO.getFieldIndexName());
//            indexName2BeanFieldNameMap.put(secFieldRespDTO.getFieldIndexName(),
//                    secFieldRespDTO.getFieldBeanFieldName());
//            indexName2BeanFieldNameCnMap.put(secFieldRespDTO.getFieldIndexName(),
//                    secFieldRespDTO.getFieldNamecn());
//            fieldList.add(secFieldRespDTO.getFieldBeanFieldName());
//        }
//
//        //数据对象绑定器初始化
//        BaseResultBinder resultBinder = null;
//        try {
//            if(StringUtils.equals(EBinderType.MAP.getBinderType(), binderType.getBinderType())){
//                if(CollectionUtils.isEmpty(searchParam.getFieldList())){
//                    if(insidePager){
//                        fieldList.add(SearchConstants.FIELD_ID_PARENT);
//                        fieldList.add(SearchConstants.FIELD_ID_CHILD);
//                    }else{
//                        fieldList.add(SearchConstants.ID);
//                    }
//                    
//                    searchParam.setFieldList(fieldList);
//                }
//                resultBinder=new MapResultBinder(searchParam.getFieldList());
//            }else if(StringUtils.equals(EBinderType.BEAN.getBinderType(), binderType.getBinderType())){
//                resultBinder=new BeanResultBinder(searchParam.getClazz());
//            }
//        } catch (ReusltBindingException e) {
//            searchResult.setMessage(SolrServerUtils.getExceptionMessage(e));
//            return searchResult;
//        }
//
//        // 查询
//        SolrServer solrServer = null;
//        QueryResponse queryResp = null;
//        QueryBuilderResp queryBuilderResp;
//        
//        try {
//            //获取SOLR服务器抽象对象
//            startTime=System.currentTimeMillis();
//            solrServer = SolrServerUtils.getSolrServer(searchParam.getCollectionName(),true);
//            endTime=System.currentTimeMillis();
//            Log.info(MODULE, "SearchUtils.getSolrServer耗时：【"+(endTime-startTime)+"ms】");
//
//            //请求对象封装
//            startTime=System.currentTimeMillis();
//            queryBuilderResp=query(currSecConfigRespDTO, secFieldRespDTOList,
//                    resultBinder.getFields(),insidePager, searchParam,
//                    extraQueryInfo, filterList, beanFieldName2IndexNameMap);
//            endTime=System.currentTimeMillis();
//            Log.info(MODULE, "queryBuilderResp构造耗时：【"+(endTime-startTime)+"ms】");
//
//            //发起请求
//            startTime=System.currentTimeMillis();
//            queryResp = solrServer.query(queryBuilderResp.getSolrQuery(), METHOD.POST);
//            endTime=System.currentTimeMillis();
//            Log.info(MODULE, "HTTP请求响应耗时-SolrServer.query耗时：【"+(endTime-startTime)+"ms】");
//            
//            // 返回的fieldfacet列表
//            searchResult.setFieldFacetResultMap(FacetSupport.getFieldFacetResult(queryResp,indexName2BeanFieldNameCnMap));
//
//            // TODO 返回的datefacet列表
//
//            // 返回的queryfacet列表
//            searchResult.setQueryFacetResultMap(FacetSupport.getQueryFacetResult(queryResp, indexName2BeanFieldNameCnMap));
//            
//        } catch (BusinessException e) {
//            Log.error(MODULE, TEXT, e);
//
//            // 已国际化信息
//            searchResult.setMessage(SolrServerUtils.getExceptionMessage(e));
//            return searchResult;
//        } catch (SolrServerException e1) {
//            Log.error(MODULE, TEXT, e1);
//            searchResult.setMessage(ResourceMsgUtil.getMessage(
//                    SearchMessageKeyContants.Error.KEY_ERROR_QUERY, new String[]{SolrServerUtils.getExceptionMessage(e1)},
//                    LocaleUtil.getLocale()));
//            return searchResult;
//        } catch (SearchException e) {
//            Log.error(MODULE, TEXT, e);
//            searchResult.setMessage(e.getMessage());
//            return searchResult;
//        } catch (ReusltBindingException e) {
//            Log.error(MODULE, TEXT, e);
//            // 追加具体异常信息(不国际化部分)
//            searchResult.setMessage(ResourceMsgUtil.getMessage(
//                    SearchMessageKeyContants.Error.KEY_ERROR_RESULTBEAN_STRUCTURE_FIELD,
//                    new String[]{SolrServerUtils.getExceptionMessage(e)}, LocaleUtil.getLocale()));
//            return searchResult;
//        }
//
//        //检索返回结果解析
//        SolrDocumentList solrDocList = queryResp.getResults();
//
//        if (solrDocList != null && solrDocList.size() > 0) {
//
//            try {
//                startTime=System.currentTimeMillis();
//                // 对象和属性拷贝
//                searchResult.setResultList(resultBinder.getResults(searchParam.getHighlighterParam(),solrDocList,StringUtils.equals(SearchConstants.STATUS_VALID,currSecConfigRespDTO.getConfigQueryIfHl()),
//                        queryResp.getHighlighting(), beanFieldName2IndexNameMap,currSecConfigRespDTO.getConfigIfMultilan(),queryBuilderResp.getLangs(),queryBuilderResp.getMultiLangMap(),queryBuilderResp.getHlMap()));
//                endTime=System.currentTimeMillis();
//                Log.info(MODULE, "返回结果解析、对象和属性拷贝耗时：【"+(endTime-startTime)+"ms】");
//            } catch (ReusltBindingException e) {
//                Log.error(MODULE, TEXT, e);
//
//                // 追加具体异常信息(不国际化部分)
//                searchResult.setMessage(ResourceMsgUtil.getMessage(
//                        SearchMessageKeyContants.Error.KEY_ERROR_RESULT_BINDING,
//                        new String[]{SolrServerUtils.getExceptionMessage(e)}, LocaleUtil.getLocale()));
//            } catch (Exception e) {
//                Log.error(MODULE, TEXT, e);
//
//                // 追加具体异常信息(不国际化部分)
//                searchResult.setMessage(ResourceMsgUtil.getMessage(
//                        SearchMessageKeyContants.Error.KEY_ERROR_RESULT_BINDING,
//                        new String[]{SolrServerUtils.getExceptionMessage(e)}, LocaleUtil.getLocale()));
//            }
//        }
//
//        // 额外响应信息设置
//        searchResult = addBaseRespInfo(searchResult, queryResp);
//
//        // 分页信息设置
//        searchResult.setNumFound(solrDocList.getNumFound());
//        searchResult.setPageSize(searchParam.getPageSize());
//        searchResult.setPageNo(searchParam.getPageNo());
//        searchResult
//                .setTotallyPage((searchResult.getNumFound() % searchParam.getPageSize() == 0) ? (searchResult
//                        .getNumFound() / searchParam.getPageSize()) : (searchResult.getNumFound()
//                        / searchParam.getPageSize() + 1));
//
//        // 返回结果
//        return searchResult;
//
//    }
//
//    private static QueryBuilderResp query(SecConfigRespDTO currSecConfigRespDTO,
//            List<SecFieldRespDTO> secFieldRespDTOList, List<String> beanFieldNames,boolean insidePager,
//            SearchParam searchParam, ExtraQueryInfo extraQueryInfo, List<QueryFilter> filterList,
//            Map<String, String> beanFieldName2IndexNameMap) throws SearchException {
//        
//        //查询参数校验
//        if(searchParam.getPageNo()<=0){
//            throw new SearchException(ResourceMsgUtil.getMessage(
//                    SearchMessageKeyContants.Info.KEY_INFO_PAGENO_INVALID,new String[]{""+searchParam.getPageNo()}));
//        }
//        
//        if(searchParam.getPageSize()<=0){
//            throw new SearchException(ResourceMsgUtil.getMessage(
//                    SearchMessageKeyContants.Info.KEY_INFO_PAGESIZE_INVALID, new String[]{""+searchParam.getPageSize()}));
//        }
//        
//        QueryBuilderResp queryBuilderResp=new QueryBuilderResp();
//
//        SolrQuery solrQuery = new SolrQuery();
//
//        solrQuery.set(CommonParams.QT, ERequestType.QUERY.getHandler());
//
//        //只从存活的shards获取数据,没有此参数，如果集群内有挂掉的shard，将显示：
//        //no servers hosting shard
//        //需要注意值是Boolean而不是字符串？经测试字符串也是可以的
//        //solrQuery.set("shards.tolerant","true");
//        solrQuery.set("shards.tolerant",true);
//        //solrQuery.setParam("shards.tolerant",true);
//
//        boolean queryAll = StringUtils.equals(searchParam.getKeyword(), SearchConstants.STAR);
//
//        //是否高亮
//        boolean ifHightLight=searchParam.isIfHightlight();
//        if(ifHightLight){
//            //在结果中搜支持高亮
//            ifHightLight=!queryAll||CollectionUtils.isNotEmpty(searchParam.getExtraKeywordList());
//        }
//
//        if(!searchParam.isIfRetDocList()){
//            solrQuery.setRows(0);
//        }else{
//            solrQuery.setStart((int) ((searchParam.getPageNo() - 1) * searchParam.getPageSize()))
//            .setRows(searchParam.getPageSize());
//        }
//
//        //支持q.op检索入参
//        if(searchParam.getqOp()!=null){
//            solrQuery.set("q.op", searchParam.getqOp().getName());
//        }else{
//            //取索引配置
//            solrQuery.set("q.op", currSecConfigRespDTO.getConfigQueryOp());
//        }
//
//        //语言环境
//        List<ELang> langs=null;
//
//        //TODO 若索引配置是多语言配置，则需要根据检索关键字自动分析语言环境，并设置语言参数（注意了，不应该由前店传入，应该是个自动分析过程）
//        if(StringUtils.equals(currSecConfigRespDTO.getConfigIfMultilan(),SearchConstants.STATUS_1)||StringUtils.equals(currSecConfigRespDTO.getConfigIfMultilan(),SearchConstants.STATUS_2)){
//            //户端强制指定检索语言（如界面提供检索语言参数选择）
//            if(searchParam.getLang()!=null){
//                langs=new ArrayList<>();
//                langs.add(searchParam.getLang());
//            }else{//引擎内自动判断语言环境（可能识别匹配多种语言）
//                langs=langAnalysis(searchParam.getKeyword());
//            }
//        }
//
//        //多语言支持。
//        //1、记录多语言：若索引配置配置了多语言，如果搜索不传入多语言参数则直接抛出异常（对于记录多语言无法通过索引配置获取和设置默认语言参数），否则将会以默认字段类型去检索，对于问题排查比较困难。
//        //2、字段多语言：若索引配置配置了多语言，如果搜索不传入多语言参数则也直接抛出异常（虽然对于字段多语言可以设置默认语言参数，默认语言参数取第一个），否则将会以默认字段类型去检索，对于问题排查比较困难。
//        //3、若索引配置没配置多语言，但是搜索传入了多语言参数则也直接抛出异常（虽然可以直接取消多语言参数）
//        /*if(StringUtils.equals(currSecConfigRespDTO.getConfigIfMultilan(),SearchConstants.STATUS_1)&&StringUtils.isNotBlank(currSecConfigRespDTO.getLanField())&&
//                (searchParam.getLang()==null)){//记录多语言
//
//            throw new SearchException("索引配置是记录多语言，但是检索参数未设置检索语言");
//
//        }
//        if(StringUtils.equals(currSecConfigRespDTO.getConfigIfMultilan(),SearchConstants.STATUS_2)&&StringUtils.isNotBlank(currSecConfigRespDTO.getLans())&&
//                (searchParam.getLang()==null)){//字段多语言
//
//            throw new SearchException("索引配置是字段多语言，但是检索参数未设置检索语言");
//
//        }
//        if(!StringUtils.equals(currSecConfigRespDTO.getConfigIfMultilan(),SearchConstants.STATUS_1)&&!StringUtils.equals(currSecConfigRespDTO.getConfigIfMultilan(),SearchConstants.STATUS_2)
//                &&(searchParam.getLang()!=null)){
//            throw new SearchException("索引配置未配置多语言，但是检索参数设置了检索语言");
//        }*/
//
//        //记录多语言的检索和字段多语言的检索过程其实是一样的，都是要根据检索语言替换到字段名中。
//        //区别在于记录多语言还需要额外设置语言字段(lang)值。
//        //非*情况下才需要做语言字段限制
//        //TODO 该filter是可选的
//        if(!queryAll&&StringUtils.equals(currSecConfigRespDTO.getConfigIfMultilan(),SearchConstants.STATUS_1)){
//            if(CollectionUtils.isNotEmpty(langs)){
//                StringBuffer sb=new StringBuffer();
//                for(ELang lang:langs){
//                    if(StringUtils.isNotBlank(sb.toString())) sb.append(" "+EOperator.OR.getName()+" ");
//                    sb.append(SearchConstants.FIELD_LANG+SearchConstants.COLON+lang.getLang());
//                }
//                solrQuery.addFilterQuery(sb.toString());
//            }
//        }
//
//        String indexName="";
//
//        /*=========================以下数据都是经过多语言处理过后的start============================*/
//
//        // 排序字段//TODO 注意了，排序字段（一般是数值、日期）一般不需要设置为多语言字段（排序字段多语言会有问题），因此无需考虑多语言环境下的排序处理
//        Map<String, String> sortMap = new HashMap<String, String>();
//        TreeMap<Short,String> sortIndexMap=new TreeMap<Short,String>();
//
//        // 返回结果字段//TODO 需要考虑多语言下返回字段设置问题
//        Map<String, String> flMap = new HashMap<String, String>();
//
//        // 高亮字段,同一个索引配置中的多个搜索数据对象字段相同配置部分自动覆盖//TODO 需要考虑多语言下返回字段设置问题//检索全部其实可以不设置高亮字段
//        Map<String, String> hlMap = new HashMap<String, String>();
//
//        // Facet字段//TODO 需要考虑多语言字段facet问题，需要考虑多语言字段Facet结果合并的问题
//        Map<String, String> facetMap = new HashMap<String, String>();
//
//        // 同一个索引配置中的多个搜索数据对象字段相同配置部分自动覆盖//根据检索语言设置//TODO 需要考虑多语言字段检索问题
//        Map<String, String> dfMap = new HashMap<String, String>();
//        
//        //多语言字段map
//        Map<String,String> multiLangMap=new HashMap<String,String>();
//
//        //索引字段名2多语言索引字段名列表
//        Map<String,List<String>> indexName2MulLanIndexNameMapList=new HashMap<String,List<String>>();
//
//        /*=========================以下数据都是经过多语言处理过后的end============================*/
//        
//        boolean nodf = true;
//        for (SecFieldRespDTO secFieldRespDTO : secFieldRespDTOList) {
//
//            //非多语言
//            //若字段配置为多语言，但是数据对象没有配置为多语言，则也当成普通字段处理。
//            indexName=secFieldRespDTO.getFieldIndexName();
//
//            //记录多语言
//            if(StringUtils.equals(currSecConfigRespDTO.getConfigIfMultilan(),SearchConstants.STATUS_1)&&StringUtils.equals(SearchConstants.STATUS_1, secFieldRespDTO.getFieldIfMultilan())){
//                multiLangMap.put(secFieldRespDTO.getFieldBeanFieldName(), secFieldRespDTO.getFieldBeanFieldName());
//
//                //TODO 记录多语言，无论是检索全部（*）或者是有指定具体检索关键字的时候都需要根据记录的lang字段，设置返回对应的多语言字段，这是在检索的时候所无法指定的
//                //TODO 因此目前只能是设置服务端返回所有字段才能支持记录多语言数据返回了（记录多语言不设置返回字段列表，默认返回全部）。
//
//                //使用检索语言设置索引字段名（高亮需求）
//                if(CollectionUtils.isNotEmpty(langs)){
//                    List<String> mulLanIndexNameList=new ArrayList<>();
//                    for(ELang lang:langs){
//                        mulLanIndexNameList.add(secFieldRespDTO.getFieldIndexName().replace(
//                                SearchConstants.UNDERLINE + secFieldRespDTO.getFieldTypeName()
//                                        + SearchConstants.UNDERLINE,
//                                SearchConstants.UNDERLINE + lang.getLang()
//                                        + SearchConstants.UNDERLINE));
//                    }
//                    indexName2MulLanIndexNameMapList.put(indexName,mulLanIndexNameList);
//                }
//            }else if(StringUtils.equals(currSecConfigRespDTO.getConfigIfMultilan(),SearchConstants.STATUS_2)&&StringUtils.equals(SearchConstants.STATUS_1, secFieldRespDTO.getFieldIfMultilan())) {//字段多语言
//                multiLangMap.put(secFieldRespDTO.getFieldBeanFieldName(), secFieldRespDTO.getFieldBeanFieldName());
//
//                //TODO 字段多语言暂时只支持返回某种语言下的数据（根据检索语言），后期如果有返回多个语言数据需求的，需要变更返回字段规则（不设置返回字段列表，默认返回全部）
//                if(CollectionUtils.isNotEmpty(langs)){
//                    List<String> mulLanIndexNameList=new ArrayList<>();
//                    for(ELang lang:langs){
//                        mulLanIndexNameList.add(secFieldRespDTO.getFieldIndexName().replace(
//                                SearchConstants.UNDERLINE + secFieldRespDTO.getFieldTypeName()
//                                        + SearchConstants.UNDERLINE,
//                                SearchConstants.UNDERLINE + lang.getLang()
//                                        + SearchConstants.UNDERLINE));
//                    }
//                    indexName2MulLanIndexNameMapList.put(indexName,mulLanIndexNameList);
//                }
//            }
//            
//            if (StringUtils
//                    .equals(secFieldRespDTO.getFieldIfBelongtoDf(), SearchConstants.STATUS_1)) {
//
//                // 去重
//                String boost="1";
//                if(secFieldRespDTO.getFieldBoost()!=null){
//                    boost=secFieldRespDTO.getFieldBoost().doubleValue()+"";
//                }
//                dfMap.put(indexName,boost);
//                nodf = false;
//            }
//
//            //默认排序字段后期加入配置模型
//            if (StringUtils.isNotBlank(secFieldRespDTO.getFieldSort())&&(secFieldRespDTO.getFieldSortNum()!=null)&&StringUtils.isNotBlank(String.valueOf(secFieldRespDTO.getFieldSortNum()))) {
//
//                //排序字段（一般是数值、日期）一般不需要设置为多语言字段（排序字段多语言会有问题），因此无需考虑多语言环境下的排序处理
//                if(indexName2MulLanIndexNameMapList.containsKey(indexName)){
//                    throw new SearchException("排序字段（一般是数值、日期）不允许设置为多语言字段");
//                }
//
//                if (StringUtils.equals(secFieldRespDTO.getFieldSort(), SearchConstants.STATUS_0)) {
//                    sortMap.put(indexName, ESort.DESC.getSort());
//                } else if (StringUtils.equals(secFieldRespDTO.getFieldSort(),
//                        SearchConstants.STATUS_1)) {
//                    sortMap.put(indexName, ESort.ASC.getSort());
//                }
//                sortIndexMap.put(secFieldRespDTO.getFieldSortNum(),indexName);
//            }
//
//            // 按格式化Bean需要返回结果，不返回全部，节省服务器压力和带宽
//            if (beanFieldNames.contains(secFieldRespDTO.getFieldBeanFieldName())) {
//                flMap.put(indexName,indexName);
//
//                if (StringUtils.equals(secFieldRespDTO.getFieldIfHlfield(),
//                        SearchConstants.STATUS_1)) {
//                    hlMap.put(indexName,indexName);
//                }
//
//            }
//
//            if (StringUtils.equals(secFieldRespDTO.getFieldIfFacet(), SearchConstants.STATUS_1)) {
//                facetMap.put(secFieldRespDTO.getFieldBeanFieldName(),
//                        indexName);
//            }
//
//        }
//        
//        if (nodf) {
//            throw new SearchException(ResourceMsgUtil.getMessage(
//                    SearchMessageKeyContants.Info.KEY_INFO_NODF, new String[]{searchParam.getCollectionName()}, LocaleUtil.getLocale()));
//        }
//
//        //主关键字检索串
//        String keywordQueryStr="";
//        if(queryAll){
//
//            //搜索全部，直接设置查询串为*，否则在sort可能会出现意想不到的结果（如sales asc）
//            keywordQueryStr=searchParam.getKeyword();
//        }else{
//
//            String keyword=searchParam.getKeyword();
//            if(searchParam.isIfFuzzyQuery()){
//                keyword=SolrServerUtils.getFuzzyQueryKeyword(keyword);
//            }
//
//            keywordQueryStr=keyword;
//        }
//
//        if(ifHightLight){
//            // 搜索串组装
//            // 1、用拼写k-v检索串的方式拼装检索语句（字段值权重）
//            /*StringBuffer sb_q = new StringBuffer(SearchConstants.BRACKETS_LEFT);
//            boolean isFirst = true;
//            for (String df : dfMap.keySet()) {
//                String boostStr="";
//                if(StringUtils.isNotBlank(dfMap.get(df))){
//                    boostStr="^"+dfMap.get(df);
//                }
//                if (isFirst) {
//                    sb_q.append(df + SearchConstants.COLON + keyword+boostStr);
//                    isFirst = false;
//                } else {
//
//                    // 配置的默认搜索字段，是指要被搜索的字段。和查询转换模式没关联。
//                    sb_q.append(" " + EOperator.OR.getName() + " " + df + SearchConstants.COLON
//                            + keyword+boostStr);
//                }
//            }
//            sb_q.append(SearchConstants.BRACKETS_RIGHT);
//            keywordQueryStr=sb_q.toString();*/
//
//            //2、字段权重配置下的检索语句（字段权重）
//            StringBuffer sb_pf = new StringBuffer();
//            StringBuffer sb_qf = new StringBuffer();
//            boolean isFirst = true;
//            for (String df : dfMap.keySet()) {
//                String boostStr="^"+dfMap.get(df);
//
//                List<String> mulLanIndexNameList=new ArrayList<>();
//                mulLanIndexNameList.add(df);
//                //多语言字段
//                if(indexName2MulLanIndexNameMapList.containsKey(df)){
//                    mulLanIndexNameList=indexName2MulLanIndexNameMapList.get(df);
//                }
//
//                for(String s:mulLanIndexNameList){
//                    if (isFirst) {
//                        sb_pf.append(s);
//                        sb_qf.append(s +boostStr);
//                        isFirst = false;
//                    } else {
//
//                        // 配置的默认搜索字段，是指要被搜索的字段。和查询转换模式没关联。
//                        sb_pf.append(" " +s);
//                        sb_qf.append(" " +s +boostStr);
//                    }
//                }
//            }
//
//            //设置了DF导致qf失效
//            solrQuery.set("pf",sb_pf.toString());
//            solrQuery.set("qf",sb_qf.toString());
//            //bf设置
//            String bf=searchParam.getBf();
//            if(StringUtils.isNotBlank(bf)){
//                //Bean字段名和索引字段名映射列表
//                Map<String,String> var2ValueMap=new HashMap<String,String>();
//                Pattern pattern=Pattern.compile("(\\$)(\\{)(.+?)(\\})");
//                Matcher matcher=pattern.matcher(bf);
//                while(true) {
//                    if (matcher.find()) {
//                        String var = matcher.group(3);
//                        checkSrcField(var, beanFieldName2IndexNameMap);
//                        indexName=getIndexField(var, beanFieldName2IndexNameMap);
//                        if(indexName2MulLanIndexNameMapList.containsKey(indexName)){
//                            throw new SearchException("bf字段不支持多语言字段");
//                        }
//                        var2ValueMap.put(var,indexName);
//                    }else break;
//                }
//                if(MapUtils.isNotEmpty(var2ValueMap)){
//                    for(Map.Entry<String,String> entry:var2ValueMap.entrySet()){
//                        bf=bf.replaceAll("\\$\\{"+entry.getKey()+"\\}",entry.getValue());
//                    }
//                }
//            }
//            //时间衰减bf
//            if(StringUtils.isNotBlank(searchParam.getDemoteTimeField())){
//                BaseComp.checkSrcField(searchParam.getDemoteTimeField(), beanFieldName2IndexNameMap);
//                indexName=BaseComp.getIndexField(searchParam.getDemoteTimeField(), beanFieldName2IndexNameMap);
//                if(indexName2MulLanIndexNameMapList.containsKey(indexName)){
//                    throw new SearchException("时间衰减字段不支持多语言字段");
//                }
//                if(StringUtils.isNotBlank(bf)){
//                    //多个函数之间使用空格间隔
//                    bf+=" ";
//                }
//                bf+="dateFieldCacheSourceParser("+indexName+")";
//            }
//            if(StringUtils.isNotBlank(bf)){
//                solrQuery.set("bf",bf);
//                solrQuery.set(CommonParams.DEBUG,true);
//                solrQuery.set(CommonParams.DEBUG_QUERY,true);
//            }
//            //boost设置
//            String boost=searchParam.getBoost();
//            if(StringUtils.isNotBlank(boost)){
//                //Bean字段名和索引字段名映射列表
//                Map<String,String> var2ValueMap=new HashMap<String,String>();
//                Pattern pattern=Pattern.compile("(\\$)(\\{)(.+?)(\\})");
//                Matcher matcher=pattern.matcher(boost);
//                while(true) {
//                    if (matcher.find()) {
//                        String var = matcher.group(3);
//                        checkSrcField(var, beanFieldName2IndexNameMap);
//                        indexName=getIndexField(var, beanFieldName2IndexNameMap);
//                        if(indexName2MulLanIndexNameMapList.containsKey(indexName)){
//                            throw new SearchException("boost字段不支持多语言字段");
//                        }
//                        var2ValueMap.put(var,indexName);
//                    }else break;
//                }
//                if(MapUtils.isNotEmpty(var2ValueMap)){
//                    for(Map.Entry<String,String> entry:var2ValueMap.entrySet()){
//                        boost=boost.replaceAll("\\$\\{"+entry.getKey()+"\\}",entry.getValue());
//                    }
//                }
//                solrQuery.set("boost",boost);
//                //详细日志输出
//                /*solrQuery.set(CommonParams.DEBUG,true);
//                solrQuery.set(CommonParams.DEBUG_QUERY,true);*/
//            }
//            //defType设置必不可少
//            solrQuery.set("defType",EQueryParser.DEFTYPE_EDISMAX.getName());
//        }
//
//        if(searchParam.isIfUnionSearch()){
//            if(searchParam.getUnionRelationship()==EOperator.AND){//AND
//                solrQuery.setQuery(keywordQueryStr);
//                //使用FilterQuery做AND，FilterQuery就是AND
//                solrQuery.addFilterQuery(searchParam.getGrammar());
//            }else{//OR/NOT
//                if(StringUtils.isBlank(keywordQueryStr)||SearchConstants.STAR.equals(keywordQueryStr)){
//                    if(searchParam.getUnionRelationship()==EOperator.NOT){
//                        solrQuery.setQuery(EOperator.NOT.getName()+" ");
//                    }
//                    solrQuery.setQuery(solrQuery.get(CommonParams.Q)+searchParam.getGrammar());
//                }else{
//                    solrQuery.setQuery(keywordQueryStr+" "+searchParam.getUnionRelationship().getName()+" "+SearchConstants.BRACKETS_LEFT+searchParam.getGrammar()+SearchConstants.BRACKETS_RIGHT);
//                }
//            }
//        }else{
//            //自定义检索文法
//            if(StringUtils.isNotBlank(searchParam.getGrammar())){
//                solrQuery.setQuery(searchParam.getGrammar());
//            }else{
//                solrQuery.setQuery(keywordQueryStr);
//            }
//        }
//
//        // 支持手动指定默认检索字段
//        /*// TODO 没法直接设置默认搜索字段，经验证默认搜索字段只能设置一个
//        if(StringUtils.isNotBlank(searchParam.getDf())){
//            //TODO 目前aiecp_df字段还是不能使用，原因是原先设想未验证成功：
//            //那如果copyFiled的不同字段需要使用不同的分词策略怎么解决呢？如多语言的情况下？copyFiled是把源字段数据分词后copy过去目标字段，还是copy到目标字段后使用目标字段的分词器分词呢？
//            //答：无法通过copyFiled实现。因为copyFiled是源内容原原本本拷贝到dest，dest字段再使用自己的分词器统一分词。
//            //TODO 因此目前暂时还是需要由客户端传入一个默认搜索字段（如商品标题、店铺名）
//            solrQuery.set(CommonParams.DF,searchParam.getDf());
//        }else{
//            //多语言检索，根据语言自动执行默认检索字段
//            if((StringUtils.equals(currSecConfigRespDTO.getConfigIfMultilan(),SearchConstants.STATUS_1)||StringUtils.equals(currSecConfigRespDTO.getConfigIfMultilan(),SearchConstants.STATUS_2)
//            )&&(searchParam.getLang()!=null)){
//                solrQuery.set(CommonParams.DF,SearchConstants.FIELD_DF+SearchConstants.UNDERLINE+searchParam.getLang().getLang());
//            }
//        }*/
//
//        //在结果中搜,受索引配置中的默认搜索字段约束
//        solrQuery=SearchInResultSupport.searchInResult(solrQuery, searchParam, dfMap);
//
//        //在结果中排除过滤,受索引配置中的默认搜索字段约束
//        solrQuery=ExceptInResultSupport.exceptInResult(solrQuery, searchParam, dfMap,indexName2MulLanIndexNameMapList);
//        
//        // 额外查询字段,不受索引配置中的默认搜索
//        solrQuery = ExtraFieldQuerySupport.addExtraFieldQuerySupport(solrQuery, searchParam, beanFieldName2IndexNameMap,multiLangMap,indexName2MulLanIndexNameMapList);
//
//        // 自定义查询过滤,不受索引配置中的默认搜索
//        solrQuery = FilterSupport.filter(searchParam,solrQuery, extraQueryInfo, filterList,
//                beanFieldName2IndexNameMap,multiLangMap,indexName2MulLanIndexNameMapList);
//
//        // 范围查询
//        solrQuery = RangeQuerySupport.addRangeQuerySupport(solrQuery, searchParam,
//                beanFieldName2IndexNameMap,multiLangMap,indexName2MulLanIndexNameMapList);
//        
//        // 字段排序
//        //当用户自定义排序时，评分不应该作为第一排序字段，会响应到用户的自定义排序。评分是否作为排序字段以及第几排序字段也由用户指定。
//        StringBuffer sb_sort=new StringBuffer("");
//        if (CollectionUtils.isNotEmpty(searchParam.getSortFieldList())) {
//            
//            int i=0;
//            for (SortField sortField : searchParam.getSortFieldList()) {
//
//                checkSrcField(sortField.getName(), beanFieldName2IndexNameMap);
//                
//                indexName=getIndexField(sortField.getName(), beanFieldName2IndexNameMap);
//
//                if(indexName2MulLanIndexNameMapList.containsKey(indexName)){
//                    throw new SearchException("排序字段（一般是数值、日期）不允许设置为多语言字段");
//                }
//                
//                if (sb_sort.length() > 0)
//                    sb_sort.append(SearchConstants.COMMA);
//                
//                if(searchParam.isIfSortByScore()&&searchParam.getScorePosition()==i){
//                    sb_sort.append(SearchConstants.FIELD_SCORE+" "+ESort.DESC.getSort()+SearchConstants.COMMA);
//                }
//                
//                sb_sort.append(indexName);
//                sb_sort.append(" ");
//                sb_sort.append(sortField.getValue().getSort());
//                
//                i++;
//            }
//
//            //指定评分排序字段为最后一个字段
//            //若searchParam.getScorePosition()>searchParam.getSortFieldList().size()，则score排序不被处理
//            if(searchParam.getScorePosition()==searchParam.getSortFieldList().size()){
//                sb_sort.append(SearchConstants.COMMA);
//                sb_sort.append(SearchConstants.FIELD_SCORE+" "+ESort.DESC.getSort());
//            }
//
//        }else{
//            //非个性化排序，则按索引配置排序字段顺序排序
//            if (MapUtils.isNotEmpty(sortIndexMap)) {
//
//                for (Short s: sortIndexMap.keySet()) {
//                    if (sb_sort.length() > 0)
//                        sb_sort.append(SearchConstants.COMMA);
//
//                    sb_sort.append(sortIndexMap.get(s));
//                    sb_sort.append(" ");
//                    sb_sort.append(sortMap.get(sortIndexMap.get(s)));
//                }
//            }else{
//                sb_sort.append(SearchConstants.FIELD_SCORE+" desc");
//            }
//        }
//        solrQuery.set(CommonParams.SORT, sb_sort.toString());
//
//        //记录多语言需要返回所有字段
//        if(StringUtils.equals(currSecConfigRespDTO.getConfigIfMultilan(),SearchConstants.STATUS_1)||StringUtils.equals(currSecConfigRespDTO.getConfigIfMultilan(),SearchConstants.STATUS_2)){
//            //默认返回所有字段
//        }else{
//            // 返回结果字段
//            // id字段必须返回，否则无法做高亮字段处理
//            flMap.put(SearchConstants.ID, SearchConstants.ID);
//            if(insidePager){
//                flMap.put(SearchConstants.ID_PARENT, SearchConstants.ID_PARENT);
//                flMap.put(SearchConstants.ID_CHILD, SearchConstants.ID_CHILD);
//            }
//            //结果需要返回语言字段，ResultBinder中记录多语言需要根据语言字段返回多语言结果
//            flMap.put(SearchConstants.FIELD_LANG,SearchConstants.FIELD_LANG);
//            solrQuery.setFields(flMap.values().toArray(new String[] {}));
//        }
//
//        //DEBUG模式返回评分
//        if (SearchConstants.DEBUG) {
//
//            // 是否返回相关度评分
//            solrQuery.setIncludeScore(true);
//        }
//        
//        // 强制开启高亮组件
//        // 如果是搜索全部，无需列出高亮字段
//        if (ifHightLight) {
//            solrQuery = HighlightSupport.addHighlightSupport(searchParam,solrQuery, currSecConfigRespDTO,
//                    hlMap.keySet(),indexName2MulLanIndexNameMapList);
//        }
//
//        // Facet
//        solrQuery = FacetSupport.support(solrQuery, searchParam, facetMap,
//                beanFieldName2IndexNameMap,multiLangMap,indexName2MulLanIndexNameMapList);
//
//        Log.info(MODULE,"===================基本请求参数===================");
//        Log.info(MODULE, "查询请求参数：【"+searchParam.toString()+"】");
//        Log.info(MODULE, "查询转换模式：【"+currSecConfigRespDTO.getConfigQueryOp()+"】");
//        Log.info(MODULE, "语言参数：【");
//        if(CollectionUtils.isNotEmpty(langs)){
//            for(ELang lang:langs){
//                Log.info(MODULE,lang.getLang());
//            }
//        }
//        Log.info(MODULE, "】");
//        Log.info(MODULE,"===================基本检索参数===================");
//        Log.info(MODULE, "最终查询串：【"+solrQuery.getQuery()+"】");
//        Log.info(MODULE, "最终排序串：【"+solrQuery.get(CommonParams.SORT)+"】");
//        Log.info(MODULE, "默认检索字段（df）：【"+solrQuery.get(CommonParams.DF)+"】");
//        Log.info(MODULE, "最终Filter查询串：【");
//        if(solrQuery.getFilterQueries()!=null&&solrQuery.getFilterQueries().length>0){
//            for(String s:solrQuery.getFilterQueries()){
//                Log.info(MODULE, s);
//            }
//        }
//        Log.info(MODULE, "】");
//        Log.info(MODULE,"===================defType===================");
//        Log.info(MODULE, "defType："+solrQuery.get("defType"));
//        Log.info(MODULE, "可设置权重字段pf："+solrQuery.get("pf"));
//        Log.info(MODULE, "字段权重串qf："+solrQuery.get("qf"));
//        Log.info(MODULE, "函数查询Function Query,bf："+solrQuery.get("bf"));
//        Log.info(MODULE, "boost："+solrQuery.get("boost"));
//        Log.info(MODULE,"===================高亮控制参数===================");
//        Log.info(MODULE, "高亮控制参数hl："+solrQuery.get("hl"));
//        Log.info(MODULE, "高亮控制参数hl.fl：【");
//        String fls[]=solrQuery.getParams("hl.fl");
//        if(fls!=null&&fls.length>0){
//            for(String fl:fls){
//                Log.info(MODULE,fl);
//            }
//        }
//        Log.info(MODULE, "】");
//        Log.info(MODULE, "高亮控制参数hl.useFastVectorHighlighter："+solrQuery.get("hl.useFastVectorHighlighter"));
//        Log.info(MODULE, "高亮控制参数hl.fragsize："+solrQuery.get("hl.fragsize"));
//        Log.info(MODULE, "高亮控制参数hl.snippets："+solrQuery.get("hl.snippets"));
//        Log.info(MODULE, "高亮控制参数hl.boundaryScanner："+solrQuery.get("hl.boundaryScanner"));
//        Log.info(MODULE, "高亮控制参数hl.bs.type："+solrQuery.get("hl.bs.type"));
//        Log.info(MODULE, "高亮控制参数hl.bs.maxScan："+solrQuery.get("hl.bs.maxScan"));
//        Log.info(MODULE, "高亮控制参数hl.bs.chars："+solrQuery.get("hl.bs.chars"));
//        Log.info(MODULE, "高亮控制参数hl.bs.language："+solrQuery.get("hl.bs.language"));
//        Log.info(MODULE, "高亮控制参数hl.bs.country："+solrQuery.get("hl.bs.country"));
//
//        queryBuilderResp.setSolrQuery(solrQuery);
//        queryBuilderResp.setDfMap(dfMap);
//        queryBuilderResp.setFacetMap(facetMap);
//        queryBuilderResp.setFlMap(flMap);
//        queryBuilderResp.setHlMap(hlMap);
//        queryBuilderResp.setMultiLangMap(multiLangMap);
//        queryBuilderResp.setLangs(langs);
//        queryBuilderResp.setIndexName2MulLanIndexNameMapList(indexName2MulLanIndexNameMapList);
//
//        return queryBuilderResp;
//
//    }
//}
