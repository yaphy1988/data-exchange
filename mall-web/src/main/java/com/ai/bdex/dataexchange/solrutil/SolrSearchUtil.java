package com.ai.bdex.dataexchange.solrutil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.bdex.dataexchange.common.dto.PageResponseDTO;
import com.ai.paas.utils.StringUtil;

public class SolrSearchUtil {
    private static final Logger logger = LoggerFactory.getLogger(SolrSearchUtil.class.getName());
    /**
     *  
     * Search:(solr查询商品入口). <br/> 
     * 
     * @author gxq 
     * @param searchParam
     * @return 
     * @since JDK 1.6
     */
    public static PageResponseDTO<ResultRespVO> Search(SearchParam searchParam) {
        long start = System.currentTimeMillis();
        if(searchParam.getSolrClient()==null){
            return null;
        }
        if(StringUtil.isBlank(searchParam.getCollectionName())){
            return null;
        }
        SolrQuery query = null;  
        PageResponseDTO<ResultRespVO> pageInfo = new PageResponseDTO<ResultRespVO>();
        try {  
            // 初始化查询对象  
            query = new SolrQuery();
            /**
             * solr查询条件设置初始化
             */
            setQueryCondiction(searchParam,query);
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        QueryResponse rsp = null;
        List<ResultRespVO> resultlist = new ArrayList<ResultRespVO>();
        try {
            rsp = searchParam.getSolrClient().query(searchParam.getCollectionName(),query);
            resultlist = rsp.getBeans(ResultRespVO.class);
            Map<String, Map<String, List<String>>> map = rsp.getHighlighting();
            //Item即为上面定义的bean类
            //解析结果
            parseResult(resultlist,map);
            pageInfo.setResult(resultlist);
            long numFound = rsp.getResults().getNumFound();
            pageInfo.setCount(numFound);
            String pageCount = (numFound % 20 == 0) ? (numFound / 20)+"" : (numFound/ 20 + 1)+"";
            pageInfo.setPageCount(Integer.parseInt(pageCount));
            pageInfo.setPageNo(searchParam.getPageNo());
            pageInfo.setPageSize(searchParam.getPageSize());
            logger.info("查询内容:" + query);
            logger.info("文档数量：" + rsp.getResults().getNumFound());
            logger.info("查询花费时间:" + rsp.getQTime());
            logger.info("查询到返回数据封装花费时间:" + (System.currentTimeMillis() - start));
        } catch (Exception e) {  
            logger.error("查询失败！", e);
            return null;  
        }  
        // 返回查询结果  
        return pageInfo;  
    }  
    
    public static List<ResultRespVO> suggest(SearchParam searchParam) {
        if(searchParam.getSolrClient()==null){
            return null;
        }
        if(StringUtil.isBlank(searchParam.getCollectionName())){
            return null;
        }
        SolrQuery query = null;  
        try {  
            // 初始化查询对象  
            query = new SolrQuery();  
            query.set("qt", "/suggest");
            query.set("spellcheck.q", searchParam.getKeyWord());
            query.set("spellcheck", "true");
            query.set("spellcheck.build", "true");
            query.set("spellcheck.count", searchParam.getPageSize());
            query.set("spellcheck.extendedResults", "false");
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        QueryResponse rsp = null;
        List<ResultRespVO> resultlist = new ArrayList<ResultRespVO>();
        try {  
            rsp = searchParam.getSolrClient().query(searchParam.getCollectionName(),query, METHOD.POST);
//            resultlist = rsp.getBeans(ResultRespVO.class);
            logger.info("查询内容:" + query);
            logger.info("文档数量：" + rsp.getResults().getNumFound());
            logger.info("查询花费时间:" + rsp.getQTime());
        } catch (Exception e) {  
            logger.error("查询失败！", e);
            return null;  
        }  
        // 返回查询结果  
        return resultlist;  
    }  
    
    /**
     * 
     * facetSuggest:(facet方式suggest内容). <br/> 
     * 
     * @author gxq 
     * @param searchParam
     * @return 
     * @since JDK 1.6
     */
    @SuppressWarnings("rawtypes")
    public static List<FacetRespVO> facetSuggest(SearchParam searchParam) {
        
        if(searchParam.getSolrClient()==null){
            return null;
        }
        if(StringUtil.isBlank(searchParam.getCollectionName())){
            return null;
        }
        List<FacetRespVO> resultCount = new ArrayList<FacetRespVO>(); 
        SolrQuery query = null;  
        try {  
            // 初始化查询对象  
            query = new SolrQuery("*:*");
            String keyWord = "";
            if (!StringUtil.isBlank(searchParam.getKeyWord())) {
                keyWord = searchParam.getKeyWord();
            }
            query.setFacet(true);
            query.addFacetField("name");
            query.setFacetPrefix(keyWord);
            query.setFacetLimit(searchParam.getPageSize());
            query.setFacetMinCount(1);
            Map<String, String> searchFieldList = searchParam.getSearchField();
            if(searchFieldList!= null && searchFieldList.size()>=1){
                for (String str : searchFieldList.keySet()) {  
                    if(!StringUtil.isBlank(str)){
                        query.addFilterQuery(str + ":" + searchFieldList.get(str));  
                    }
                } 
            }
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        QueryResponse rsp = null;
        List<FacetField> docs = new ArrayList<FacetField>();
        try {  
            rsp = searchParam.getSolrClient().query(searchParam.getCollectionName(),query, METHOD.POST);
            docs = rsp.getFacetFields();
            for (FacetField facet : docs) {
                FacetRespVO facetRespVO = null;
                for(Count c : facet.getValues()){
                    facetRespVO = new FacetRespVO();
                    facetRespVO.setCount(c.getCount());
                    facetRespVO.setName(c.getName());
                    resultCount.add(facetRespVO);
                }
            }
            logger.info("查询内容:" + query);
            logger.info("查询花费时间:" + rsp.getQTime());
        } catch (Exception e) {  
            logger.error("查询失败！", e);
            return null;  
        }  
        // 返回查询结果  
        return resultCount;  
    }  
    /**金额为分的格式 */  
    public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";
    public static String changeF2Y(String amount) throws Exception{  
        if(!amount.matches(CURRENCY_FEN_REGEX)) {  
            throw new Exception("金额格式有误");  
        }  
        return BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100)).toString();  
    }  
    
    /**
     * 
     * setQueryCondiction:(设置solr查询条件). <br/> 
     * 
     * @author gxq 
     * @param searchParam
     * @param query 
     * @since JDK 1.6
     */
    @SuppressWarnings({ "rawtypes", "null" })
    public static void setQueryCondiction(SearchParam searchParam, SolrQuery query){
        if(!StringUtil.isBlank(searchParam.getKeyWord())){
            query.setQuery("name:"+searchParam.getKeyWord());
        }else{
            query.setQuery("*:*");
        }
        /**
         * 设置高亮  
         */
        if (searchParam.isIfHightlight()) {
            query.setHighlight(true); // 开启高亮组件  
            query.addHighlightField("gdsName");// 高亮字段  
            query.addHighlightField("gdsSubtitle");// 高亮字段  
            query.addHighlightField("funIntroduction");// 高亮字段  
            query.setHighlightSimplePre("<font color=\"red\">");// 标记  
            query.setHighlightSimplePost("</font>");  
            query.setHighlightSnippets(1);// 结果分片数，默认为1  
            query.setHighlightFragsize(1000);// 每个分片的最大长度，默认为100  

        }  
        /**
         * 查询条件and
         * 
         */
        Map<String, String> searchFieldList = searchParam.getSearchField();
        if(searchFieldList != null && searchFieldList.size()>=1){
            for (String str : searchFieldList.keySet()) {  
                if(!StringUtil.isBlank(str) && !StringUtil.isBlank(searchFieldList.get(str))){
                    query.addFilterQuery(str + ":" + searchFieldList.get(str)); 
                }
            }
        }
        
        /**
         * 查询条件and 用于比如 gdsId =1 and gdsId= 2 and gdsId = 3 多值的&&查询
         * 
         */
        Map<String, List<String>> SearchAndListField = searchParam.getSearchAndListField();
        if(SearchAndListField != null && SearchAndListField.size()>=1){
            for (String key : SearchAndListField.keySet()) {
                if(!StringUtil.isBlank(key) && SearchAndListField.get(key)!=null && SearchAndListField.get(key).size()>=1){
                    query.addFilterQuery(parseSearchAndValue(key,SearchAndListField.get(key)));
                }
            }
        }
        /**
         * 查询条件OR
         */
        Map<String, String> searchOrfieldList = searchParam.getSearchOrField();
        if(searchOrfieldList != null && searchOrfieldList.size()>=1){
            StringBuffer stb = new StringBuffer(" ( ");
            int i = 0;
            int size = searchOrfieldList.size();
            for (String str : searchOrfieldList.keySet()) {  
                if(!StringUtil.isBlank(str)){
                    stb.append(" "+str+":"+searchOrfieldList.get(str)+" ");
                    if(i < size-1){
                        stb.append(" OR ");
                    }
                }
                i ++;
            }
            stb.append(" ) ");
            query.addFilterQuery(stb.toString());
        }
        
        /**
         * 查询条件OR。用于某个字段值多值的||查询
         */
        Map<String, List<String>> searchOrList = searchParam.getSearchOrListField();
        if(searchOrList != null && searchOrList.size()>=1){
            for (String str : searchOrList.keySet()) {
                if(!StringUtil.isBlank(str) && searchOrList.get(str) != null && searchOrList.get(str).size() >=1){
                    query.addFilterQuery(parseSearchOrValue(str,searchOrList.get(str)));
                }
            }
           
        }
        
        /**
         * 查询条件NOT 
         */
        Map<String, String> searchNotField = searchParam.getSearchNotField();
        if(searchNotField != null && searchNotField.size()>=1){
            for (String str : searchNotField.keySet()) {  
                if(!StringUtil.isBlank(str) && !StringUtil.isBlank(searchFieldList.get(str))){
                    query.addFilterQuery(" NOT " +str + ":" + searchFieldList.get(str)); 
                }
            }
        }
        
        /**
         * 查询条件NOT 用于比如 gdsId not in(1,2,3,4,5) 排出多值的
         */
        Map<String, List<String>> searchNotListField = searchParam.getSearchNotListField();
        if(searchNotListField != null && searchNotListField.size()>=1){
            for (String key : searchNotListField.keySet()) {
                if(!StringUtil.isBlank(key) && searchNotListField.get(key) != null && searchNotListField.get(key).size()>=1){
                    query.addFilterQuery(parseSearchNotValue(key,searchNotListField.get(key)));
                }
            }
        }
        /**
         * 设置起始位置与返回结果数  
         */
        int pageNo = 0;
        if(searchParam.getPageNo()==1){
            pageNo = searchParam.getPageNo() -1;
        }else if(searchParam.getPageNo() >= 2){
            pageNo = (searchParam.getPageNo() -1 )*searchParam.getPageSize();
        }
        query.setStart(pageNo);  
        query.setRows(searchParam.getPageSize()); 
        /**
         * 设置排序条件
         */
        List<SortField> sortfield = searchParam.getSortField();
        if (null != sortfield || sortfield.size() >= 1) {  
            for (SortField sortField : sortfield) { 
                query.addSort(sortField.getName(),  SolrQuery.ORDER.valueOf(sortField.getValue().getSort()));  
            }  
        }
    }
    
    /**
     * 
     * parseResult:(解析查询结果). <br/> 
     * 
     * @author gxq 
     * @param resultlist
     * @param map 
     * @since JDK 1.6
     */
    public static void parseResult(List<ResultRespVO> resultlist, Map<String, Map<String, List<String>>> map){
        for (ResultRespVO resultRespVO  : resultlist) {
            //hightlight的键为Item的id，值唯一，我们设置的高亮字段为gdsName
            if(!StringUtil.isBlank(resultRespVO.getGdsPic())){
                resultRespVO.setGdsPic(resultRespVO.getGdsPic());
            }
            resultRespVO.setGdsNameSrc(resultRespVO.getGdsName());
            resultRespVO.setGdsLabel(resultRespVO.getGdsLabel());
            List<String> hlString = map.get(resultRespVO.getId()).get("gdsName");
            if (null != hlString) {
                StringBuffer sbf = new StringBuffer();
                for (String s : hlString) {
                    sbf.append(s);
                }
                resultRespVO.setGdsName(sbf.toString());
            } 
            resultRespVO.setGdsSubtitleSrc(resultRespVO.getGdsSubtitle());
            List<String> hTitlelString = map.get(resultRespVO.getId()).get("gdsSubtitle");
            if (null != hTitlelString) {
                StringBuffer sbf = new StringBuffer();
                for (String s : hTitlelString) {
                    sbf.append(s);
                }
                resultRespVO.setGdsSubtitle(sbf.toString());
            }
            resultRespVO.setFunIntroductionSrc(resultRespVO.getFunIntroduction());
            List<String> hIntroString = map.get(resultRespVO.getId()).get("funIntroduction");
            if (null != hIntroString) {
                StringBuffer sbf = new StringBuffer();
                for (String s : hIntroString) {
                    sbf.append(s);
                }
                resultRespVO.setFunIntroduction(sbf.toString());
            }
        }
    }
    
    /**
     * 
     * parseSearchAndValue:(拼接多值&&条件). <br/> 
     * 
     * @author gxq 
     * @param list 
     * @since JDK 1.6
     */
    public static String parseSearchAndValue(String key,List<String> list){
        StringBuffer stb = new StringBuffer();
        if(list!= null && list.size() >= 1){
            int size = list.size();
            stb.append(key+":( ");
            int i = 0;
            for(String str : list){
                if(!StringUtil.isBlank(str)){
                    stb.append(" "+str+" ");
                    if(i<size-1){
                        stb.append(" , ");
                    }
                    i ++;
                }
            }
            stb.append(" )");
        }
        return stb.toString();
    }
    
    /**
     * 
     * parseSearchOrValue:(拼接多值||条件). <br/> 
     * 
     * @author gxq 
     * @param list 
     * @since JDK 1.6
     */
    public static String parseSearchOrValue(String key,List<String> list){
        StringBuffer stb = new StringBuffer();
        if(list!= null && list.size() >= 1){
            int size = list.size();
            stb.append("( ");
            int i = 0;
            for(String str : list){
                if(!StringUtil.isBlank(str)){
                    stb.append(" "+key+":"+str+" ");
                    if(i<size-1){
                        stb.append(" OR ");
                    }
                    i ++;
                }
            }
            stb.append(" )");
        }
        return stb.toString();
    }
    
    /**
     * 
     * parseSearchNotValue:(拼接多值!条件). <br/> 
     * 
     * @author gxq 
     * @param list 
     * @since JDK 1.6
     */
    public static String parseSearchNotValue(String key,List<String> list){
        StringBuffer stb = new StringBuffer();
        if(list!= null && list.size() >= 1){
            int size = list.size();
            stb.append(" NOT "+key+":( ");
            int i = 0;
            for(String str : list){
                if(!StringUtil.isBlank(str)){
                    stb.append(" "+str+" ");
                    if(i<size-1){
                        stb.append(" , ");
                    }
                    i ++;
                }
            }
            stb.append(" )");
        }
        return stb.toString();
    }
}

