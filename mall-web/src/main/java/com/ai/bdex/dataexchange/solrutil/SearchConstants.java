package com.ai.bdex.dataexchange.solrutil;
/**
 * 
 * Title: ECP <br>
 * Project Name:mall-web <br>
 * Description: 常量<br>
 * Date:2017年4月24日上午8:59:16  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
public class SearchConstants {

    public static final String STATUS_0 = "0";
    public static final String STATUS_1 = "1";
    public static final String STATUS_2 = "2";
    public static final String STATUS_VALID = STATUS_1;
    public static final String STATUS_INVALID = STATUS_0;
    public static final int STATUS_NEGATIVE_1= -1;
    public static final String COLLECTION_SUGGEST_SUFFIX = "4suggest";
    public static final String COLON = ":";
    public static final String COMMA = ",";
    public static final String UNDERLINE="_";
    public static final boolean DEBUG = true;
    public static final String QUERY_ALL = "*:*";
    public static final String FIELD_DF="aiecp_df";
    public static final String FIELD_SCORE="score";
    public static final String FIELD_ID_PARENT = "parentId";//数据源接口内部分页时存的外部主键
    public static final String FIELD_ID_CHILD = "childId";//数据源接口内部分页时存的内部主键(二级分页主键)
    public static final String ID_PARENT = FIELD_ID_PARENT+"_string_sv_none";
    public static final String ID_CHILD = FIELD_ID_CHILD+"_string_sv_none";
    public static final String ID = "id";//此id==（二级分页主键）；或==（外部主键+二级分页主键），如一个商品可能参与了多个促销商品编码无法保证在索引中唯一。
    public static final String TO="TO";
    public static final String STAR="*";
    public static final String BRACKETS_LEFT="(";
    public static final String BRACKETS_RIGHT=")";
    public static final String SEPERATOR="@";
    public static final String LANG_SEPERATOR=SEPERATOR;
    //注意了！！！该字段是SOLR字段，因此是固定的。记录多语言（非字段多语言），语言区分字段。
    // 区分与索引配置中的langField，langField（默认为"$lang$"）需要具备一定区分度（有的索引配置中字段也配置了语言字段名字可能也写成lang导致相互覆盖）
    public static final String FIELD_LANG="lang";

    //TODO 映射不单独通过Map定义，通过枚举统一定义


    public final static class DeployType{
        public static final String STANDALONE = "1";//单点
        public static final String REPLICATION = "2";//主从
        public static final String CLOUD = "3";//cloud
    }
    
    public final static class Redirect{
        public static final String MATCH_TYPE_ALL = STATUS_1;
        public static final String MATCH_TYPE_CONTAIN = STATUS_2;
        public static final String REDIRECT_TYPE_NONEMATCHES = STATUS_0;
        public static final String REDIRECT_TYPE_URL = STATUS_1;
        public static final String REDIRECT_TYPE_NEWQUERY = STATUS_2;
    }
    
    public final static class Schema{
        public static final String FIELD_MV="mv";
        public static final String FIELD_SV="sv";
        public static final String FIELD_FACET="facet";
        public static final String FIELD_SPELLCHECK="spellcheck";//目前对于spellcheck拼写检查字段和suggest搜索建议的字段类型处理是一致的。
        public static final String FIELD_NONE="none";
        public static final String FIELD_DF="df";
    }
    
}
