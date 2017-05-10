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
    /* 
     * field 查询的字段名称数组 key 查询的字段名称对应的值 start 查询的起始位置 count 一次查询出来的数量 sortfield 
     * 需要排序的字段数组 flag 需要排序的字段的排序方式如果为true 升序 如果为false 降序 hightlight 是否需要高亮显示 
     */  
    @SuppressWarnings("rawtypes")
    public static PageResponseDTO<ResultRespVO> Search(SearchParam searchParam) {
        long start = System.currentTimeMillis();
        if(searchParam.getSolrClient()==null){
            return null;
        }
        if(StringUtil.isBlank(searchParam.getCollectionName())){
            return null;
        }
        List<SearchField> searchfield = searchParam.getSearchField(); 
        List<SortField> sortfield = searchParam.getSortField();
        SolrQuery query = null;  
        PageResponseDTO<ResultRespVO> pageInfo = new PageResponseDTO<ResultRespVO>();
        try {  
            // 初始化查询对象  
            query = new SolrQuery();
            if(!StringUtil.isBlank(searchParam.getKeyWord())){
                query.setQuery("name:"+searchParam.getKeyWord());
            }else{
                query.setQuery("*:*");
            }
            // 设置高亮  
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
            for (SearchField searchField : searchfield) {  
                if(searchField.getValue() == null){
                    continue;
                }
                query.addFilterQuery(searchField.getName() + ":" + searchField.getValue().toString());  
            }  
            // 设置起始位置与返回结果数  
            int pageNo = 0;
            if(searchParam.getPageNo()==1){
                pageNo = searchParam.getPageNo() -1;
            }else if(searchParam.getPageNo() >= 2){
                pageNo = (searchParam.getPageNo() -1 )*searchParam.getPageSize();
            }
            query.setStart(pageNo);  
            query.setRows(searchParam.getPageSize());  
            // 设置排序  
            if (null != sortfield || sortfield.size() >= 1) {  
                for (SortField sortField : sortfield) { 
                    query.addSort(sortField.getName(),  SolrQuery.ORDER.valueOf(sortField.getValue().getSort()));  
                }  
            }
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
            for (ResultRespVO resultRespVO  : resultlist) {
                   //hightlight的键为Item的id，值唯一，我们设置的高亮字段为gdsName
                   if(!StringUtil.isBlank(resultRespVO.getGdsPic())){
                       resultRespVO.setGdsPic(resultRespVO.getGdsPic());
                   }
                   resultRespVO.setPackPriceShow(changeF2Y(resultRespVO.getPackPrice()+""));
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
    
    @SuppressWarnings("rawtypes")
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
            List<SearchField> searchFieldList = searchParam.getSearchField();
            if(searchFieldList!= null && searchFieldList.size()>=1){
                for (SearchField searchField : searchFieldList) {  
                    if(searchField.getValue() == null){
                        continue;
                    }
                    query.addFilterQuery(searchField.getName() + ":" + searchField.getValue().toString());  
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
}

