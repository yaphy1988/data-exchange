//package com.ai.ecp.search.dubbo.search;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.ai.ecp.search.dubbo.search.util.ELang;
//import com.ai.ecp.search.dubbo.search.util.EOperator;
//import com.ai.ecp.search.dubbo.search.util.EQOp;
//
///**
// * 
// * Title: ECP <br>
// * Project Name:mall-web <br>
// * Description: <br>
// * Date:2017年4月25日下午1:46:55  <br>
// * Copyright (c) 2017 asia All Rights Reserved <br>
// * 
// * @author gxq
// * @version  
// * @since JDK 1.6
// */
//public class SearchParam {
//    
//    /**
//     * 站点Id
//     */
//    private Long currentSiteId = -1L;
//
//    /**
//     * 集合名称，直接指定或由站点Id配置路由生成
//     */
//    private String collectionName;
//
//    /**
//     * 不指定关键字默认搜索全部，主搜索关键字不需要支持NOT。
//     */
//    private String keyword;
//
//    /**
//     * 是否进行检索关键字重定向校验
//     */
//    private boolean ifRedirectCheck=false;
//
//    /**
//     * 文法：由文法生成器生成。不受索引配置中的默认搜索字段约束
//     */
//    private String grammar;
//
//    /**
//     * 默认搜索字段，解决多关键字检索无法出结果问题
//     */
//    private String df;
//
//    /**
//     * 查询转换（查询分词关系）
//     */
//    private EQOp qOp=null;
//
//    /**
//     * 检索是否高亮（覆盖索引配置）
//     */
//    private boolean ifHightlight=true;
//
//    /**
//     * 高亮详细控制参数
//     */
//    private HighlighterParam highlighterParam=new HighlighterParam();
//
//    /**
//     * 是否联合查询：keyword+grammar
//     */
//    private boolean ifUnionSearch=false;
//
//    /**
//     * 联合查询关系：默认是AND
//     */
//    private EOperator unionRelationship=EOperator.AND;
//    
//    /**
//     * 是否模糊查询
//     */
//    private boolean ifFuzzyQuery=false;
//    
//    /**
//     * 返回字段列表
//     */
//    private List<String> fieldList;
//    
//    /**
//     * 返回Bean实例类型 
//     */
//    @SuppressWarnings("rawtypes")
//    private Class clazz;
//    
//    /**
//     * 文档id，支持通配符
//     */
//    private String id;
//
//    /**
//     * 文档id是否是NOT查询
//     */
//    private boolean ifIdNot=false;
//    
//    /**
//     * 在结果中搜，AND。受索引配置中的默认搜索字段约束
//     */
//    private List<String> extraKeywordList;
//    
//    /**
//     * 在结果中过滤，AND。受索引配置中的默认搜索字段约束
//     */
//    private List<String> exceptKeywordList;
//    
//    /**
//     * 额外查询字段OR Group。不受索引配置中的默认搜索字段约束
//     */
//    private List<ExtraORFieldQueryGroup> extraORFieldQueryGroupList;
//    
//    /**
//     * 额外查询字段,AND。不受索引配置中的默认搜索字段约束
//     */
//    private List<ExtraFieldQueryField> extraANDFieldQueryList;
//
//    /**
//     * 额外查询字段（字段多值查询）,AND。不受索引配置中的默认搜索字段约束
//     */
//    private List<MulValueExtraFieldQueryField> extraANDMulValueFieldQueryList;
//
//    /**
//     * 拼写检查或搜索建议提示个数
//     */
//    private int tipCount=10;
//
//    /**
//     * 范围查询条件列表，需要注意的是，范围查询会影响*Facet统计结果
//     */
//    private List<RangeQueryField> rangeQueryFieldList;
//    
//    /**
//     * facet返回条数。默认值为10.如果此值为负数,表示不限制（容易导致服务很慢，并且有内存溢出风险）。
//     */
//    private int facetLimit=10;
//    
//    /**
//     * 是否返回查询结果
//     */
//    private boolean ifRetDocList=true;
//    
//    /**
//     * 字段Facet
//     */
//    private List<String> fieldFacetFieldList;
//
//    /**
//     * 日期Facet
//     */
//    private List<DateFacetField> dataFacetFieldList;
//
//    /**
//     * 查询范围Facet
//     */
//    private List<QueryFacetField> queryFacetFieldList;
//    
//    /**
//     * 是否按评分排序
//     */
//    private boolean ifSortByScore=false;
//    
//    /**
//     * 评分字段排序位置，默认0最前面。下标从0开始，设置为2，则说明前面有两个排序字段。
//     */
//    private int scorePosition=0;
//    
//    /**
//     * 字段排序List
//     */
//    private List<SortField> sortFieldList;
//
//    /**
//     * 当前页
//     */
//    private long pageNo=1;
//
//    /**
//     * 搜索结果pageSize平台统一，由前店后端负责参数初始化
//     */
//    private int pageSize = 10;
//    
//    /**
//     * 语种，用于多语言查询。此参数仅仅用于当客户端需要强制指定检索语言的时候使用（如界面提供检索语言参数选择）。否则语言环境由引擎内自动判断（可能识别匹配多种语言）
//     */
//    private ELang lang;
//
//    /**
//     * 时间衰减字段（用于带入BoostFunction bf查询参数），目前只支持单个时间字段。时间衰减字段不支持多语言字段。
//     */
//    private String demoteTimeField="";
//
//    /**
//     * Boost Function（自定义bf参数入口）。如：dateFieldCacheSourceParser(${test})，字段引用形如：${field}。bf字段不支持多语言字段。
//     */
//    private String bf="";
//
//    /**
//     * boost（自定义boost参数入口）。如：recip(ms(NOW/HOUR,${publishDate}),3.16e-9,1,1)，字段引用形如：${field}。通过boost参数可以在原有的评分基础上再乘以这个参数，该参数可以为某个field。boost字段不支持多语言字段。
//     */
//    private String boost="";
//
//    public String getCollectionName() {
//        return collectionName;
//    }
//
//    public void setCollectionName(String collectionName) {
//        this.collectionName = collectionName;
//    }
//
//    public Long getCurrentSiteId() {
//        return currentSiteId;
//    }
//
//    public void setCurrentSiteId(Long currentSiteId) {
//        this.currentSiteId = currentSiteId;
//    }
//
//    public String getKeyword() {
//        return keyword;
//    }
//
//    public void setKeyword(String keyword) {
//        this.keyword = keyword;
//    }
//
//    public boolean isIfRedirectCheck() {
//        return ifRedirectCheck;
//    }
//
//    public void setIfRedirectCheck(boolean ifRedirectCheck) {
//        this.ifRedirectCheck = ifRedirectCheck;
//    }
//
//    public String getGrammar() {
//        return grammar;
//    }
//
//    public void setGrammar(String grammar) {
//        this.grammar = grammar;
//    }
//
//    public String getDf() {
//        return df;
//    }
//
//    public void setDf(String df) {
//        this.df = df;
//    }
//
//    public EQOp getqOp() {
//        return qOp;
//    }
//
//    public void setqOp(EQOp qOp) {
//        this.qOp = qOp;
//    }
//
//    public boolean isIfHightlight() {
//        return ifHightlight;
//    }
//
//    public void setIfHightlight(boolean ifHightlight) {
//        this.ifHightlight = ifHightlight;
//    }
//
//    public HighlighterParam getHighlighterParam() {
//        return highlighterParam;
//    }
//
//    public void setHighlighterParam(HighlighterParam highlighterParam) {
//        this.highlighterParam = highlighterParam;
//    }
//
//    public boolean isIfUnionSearch() {
//        return ifUnionSearch;
//    }
//
//    public void setIfUnionSearch(boolean ifUnionSearch) {
//        this.ifUnionSearch = ifUnionSearch;
//    }
//
//    public EOperator getUnionRelationship() {
//        return unionRelationship;
//    }
//
//    public void setUnionRelationship(EOperator unionRelationship) {
//        this.unionRelationship = unionRelationship;
//    }
//
//    public boolean isIfFuzzyQuery() {
//		return ifFuzzyQuery;
//	}
//
//	public void setIfFuzzyQuery(boolean ifFuzzyQuery) {
//		this.ifFuzzyQuery = ifFuzzyQuery;
//	}
//
//	public List<String> getFieldList() {
//        return fieldList;
//    }
//
//    public void setFieldList(List<String> fieldList) {
//        this.fieldList = fieldList;
//    }
//
//    @SuppressWarnings("rawtypes")
//    public Class getClazz() {
//        return clazz;
//    }
//
//    @SuppressWarnings("rawtypes")
//    public void setClazz(Class clazz) {
//        this.clazz = clazz;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public boolean isIfIdNot() {
//        return ifIdNot;
//    }
//
//    public void setIfIdNot(boolean ifIdNot) {
//        this.ifIdNot = ifIdNot;
//    }
//
//    public List<String> getExtraKeywordList() {
//        return extraKeywordList;
//    }
//
//    public void setExtraKeywordList(List<String> extraKeywordList) {
//        this.extraKeywordList = extraKeywordList;
//    }
//    
//    public SearchParam and(String extraKeyword) {
//        if(extraKeywordList==null){
//            extraKeywordList=new ArrayList<String>();
//        }
//        this.extraKeywordList.add(extraKeyword);
//        return this;
//    }
//    
//    public List<String> getExceptKeywordList() {
//        return exceptKeywordList;
//    }
//
//    public void setExceptKeywordList(List<String> exceptKeywordList) {
//        this.exceptKeywordList = exceptKeywordList;
//    }
//    
//    public SearchParam andExcept(String exceptKeyword) {
//        if(exceptKeywordList==null){
//            exceptKeywordList=new ArrayList<String>();
//        }
//        this.exceptKeywordList.add(exceptKeyword);
//        return this;
//    }
//
//    public int getTipCount() {
//        return tipCount;
//    }
//
//    public void setTipCount(int tipCount) {
//        this.tipCount = tipCount;
//    }
//
//    public long getPageNo() {
//        return pageNo;
//    }
//
//    public void setPageNo(long pageNo) {
//        this.pageNo = pageNo;
//    }
//
//    public int getPageSize() {
//        return pageSize;
//    }
//
//    public void setPageSize(int pageSize) {
//        this.pageSize = pageSize;
//    }
//    
//    public ELang getLang() {
//        return lang;
//    }
//
//    public void setLang(ELang lang) {
//        this.lang = lang;
//    }
//
//    public List<DateFacetField> getDataFacetFieldList() {
//        return dataFacetFieldList;
//    }
//
//    public void setDataFacetFieldList(List<DateFacetField> dataFacetFieldList) {
//        this.dataFacetFieldList = dataFacetFieldList;
//    }
//
//    public List<QueryFacetField> getQueryFacetFieldList() {
//        return queryFacetFieldList;
//    }
//
//    public void setQueryFacetFieldList(List<QueryFacetField> queryFacetFieldList) {
//        this.queryFacetFieldList = queryFacetFieldList;
//    }
//    
//    public List<SortField> getSortFieldList() {
//        return sortFieldList;
//    }
//
//    public void setSortFieldList(List<SortField> sortFieldList) {
//        this.sortFieldList = sortFieldList;
//    }
//
//    public List<RangeQueryField> getRangeQueryFieldList() {
//        return rangeQueryFieldList;
//    }
//
//    public void setRangeQueryFieldList(List<RangeQueryField> rangeQueryFieldList) {
//        this.rangeQueryFieldList = rangeQueryFieldList;
//    }
//    
//    public int getFacetLimit() {
//        return facetLimit;
//    }
//
//    public void setFacetLimit(int facetLimit) {
//        this.facetLimit = facetLimit;
//    }
//    
//    public boolean isIfRetDocList() {
//        return ifRetDocList;
//    }
//
//    public void setIfRetDocList(boolean ifRetDocList) {
//        this.ifRetDocList = ifRetDocList;
//    }
//
//    public List<String> getFieldFacetFieldList() {
//        return fieldFacetFieldList;
//    }
//
//    public void setFieldFacetFieldList(List<String> fieldFacetFieldList) {
//        this.fieldFacetFieldList = fieldFacetFieldList;
//    }
//    
//    public List<ExtraORFieldQueryGroup> getExtraORFieldQueryGroupList() {
//        return extraORFieldQueryGroupList;
//    }
//
//    public void setExtraORFieldQueryGroupList(List<ExtraORFieldQueryGroup> extraORFieldQueryGroupList) {
//        this.extraORFieldQueryGroupList = extraORFieldQueryGroupList;
//    }
//
//    public List<ExtraFieldQueryField> getExtraANDFieldQueryList() {
//        return extraANDFieldQueryList;
//    }
//
//    public void setExtraANDFieldQueryList(List<ExtraFieldQueryField> extraANDFieldQueryList) {
//        this.extraANDFieldQueryList = extraANDFieldQueryList;
//    }
//
//    public List<MulValueExtraFieldQueryField> getExtraANDMulValueFieldQueryList() {
//        return extraANDMulValueFieldQueryList;
//    }
//
//    public void setExtraANDMulValueFieldQueryList(List<MulValueExtraFieldQueryField> extraANDMulValueFieldQueryList) {
//        this.extraANDMulValueFieldQueryList = extraANDMulValueFieldQueryList;
//    }
//
//    public boolean isIfSortByScore() {
//        return ifSortByScore;
//    }
//
//    public void setIfSortByScore(boolean ifSortByScore) {
//        this.ifSortByScore = ifSortByScore;
//    }
//    
//    public void setIfSortByScore(boolean ifSortByScore,int scorePosition) {
//        this.ifSortByScore = ifSortByScore;
//        this.scorePosition = scorePosition;
//    }
//
//    public int getScorePosition() {
//        return scorePosition;
//    }
//
//    public void setScorePosition(int scorePosition) {
//        this.scorePosition = scorePosition;
//    }
//
//    public String getDemoteTimeField() {
//        return demoteTimeField;
//    }
//
//    public void setDemoteTimeField(String demoteTimeField) {
//        this.demoteTimeField = demoteTimeField;
//    }
//
//    public String getBf() {
//        return bf;
//    }
//
//    public void setBf(String bf) {
//        this.bf = bf;
//    }
//
//    public String getBoost() {
//        return boost;
//    }
//
//    public void setBoost(String boost) {
//        this.boost = boost;
//    }
//
//    @Override
//    public String toString() {
//        return "SearchParam{" +
//                "clazz=" + clazz +
//                ", currentSiteId=" + currentSiteId +
//                ", collectionName='" + collectionName + '\'' +
//                ", keyword='" + keyword + '\'' +
//                ", grammar='" + grammar + '\'' +
//                ", ifUnionSearch=" + ifUnionSearch +
//                ", unionRelationship=" + unionRelationship +
//                ", ifFuzzyQuery=" + ifFuzzyQuery +
//                ", fieldList=" + fieldList +
//                ", id='" + id + '\'' +
//                ", ifIdNot=" + ifIdNot +
//                ", extraKeywordList=" + extraKeywordList +
//                ", exceptKeywordList=" + exceptKeywordList +
//                ", extraORFieldQueryGroupList=" + extraORFieldQueryGroupList +
//                ", extraANDFieldQueryList=" + extraANDFieldQueryList +
//                ", extraANDMulValueFieldQueryList=" + extraANDMulValueFieldQueryList +
//                ", tipCount=" + tipCount +
//                ", rangeQueryFieldList=" + rangeQueryFieldList +
//                ", facetLimit=" + facetLimit +
//                ", ifRetDocList=" + ifRetDocList +
//                ", fieldFacetFieldList=" + fieldFacetFieldList +
//                ", dataFacetFieldList=" + dataFacetFieldList +
//                ", queryFacetFieldList=" + queryFacetFieldList +
//                ", ifSortByScore=" + ifSortByScore +
//                ", scorePosition=" + scorePosition +
//                ", sortFieldList=" + sortFieldList +
//                ", pageNo=" + pageNo +
//                ", pageSize=" + pageSize +
//                ", lang='" + lang + '\'' +
//                '}';
//    }
//}
