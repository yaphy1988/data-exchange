package com.ai.bdex.dataexchange.solrutil;

/**
 * 
 * Title: ECP <br>
 * Project Name:mall-web <br>
 * Description: <br>
 * Date:2017年4月24日上午8:40:26  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
public class SearchMessageKeyContants {

    /**
     * 
     * Title: ECP <br>
     * Project Name:mall-web <br>
     * Description: <br>
     * Date:2017年4月24日上午8:40:36  <br>
     * Copyright (c) 2017 asia All Rights Reserved <br>
     * 
     * @author gxq
     * @version SearchMessageKeyContants 
     * @since JDK 1.6
     */
    public static class Info {

        /**
         * 查询不到指定id的记录
         */
        public static final String KEY_INFO_ROW_NOTFOUND = "search.info.row_notfound";

        /**
         * 请求集合名称为空
         */
        public static final String KEY_INFO_COLLECTION_NULL = "search.info.collection.null";

        /**
         * 请求集合不在配置范围
         */
        public static final String KEY_INFO_COLLECTION_NOTREGISTERD = "search.info.collection.notregisterd";

        /**
         * 请求集合未配置搜索数据对象
         */
        public static final String KEY_INFO_COLLECTION_NOTSECOBJECTREGISTERD = "search.info.collection.notsecobjectregisterd";

        /**
         * 请求集合未配置搜索数据对象字段
         */
        public static final String KEY_INFO_COLLECTION_NOTSECFIELDREGISTERD = "search.info.collection.notsecfieldregisterd";

        /**
         * 请求集合未激活
         */
        public static final String KEY_INFO_COLLECTION_NOTACTIVED = "search.info.collection.notactived";

        /**
         * 请求集合未创建
         */
        public static final String KEY_INFO_COLLECTION_NOTCREATED = "search.info.collection.notcreated";

        /**
         * 请求集合未索引
         */
        public static final String KEY_INFO_COLLECTION_NOTINDEXD = "search.info.collection.notindexd";

        /**
         * 未配置默认搜索字段
         */
        public static final String KEY_INFO_NODF = "search.info.nodf";

        /**
         * 请求索引字段/属性在索引配置中不存在，请进行确定后操作
         */
        public static final String KEY_INFO_FIELD_NOTREGISTERD = "search.info.field.notregisterd";
        
        /**
         * 想要做字段分组统计，但是分组统计字段配置列表为空。
         */
        public static final String KEY_INFO_FIELDFACET_EMPTY = "search.info.fieldfacet.empty";
        
        /**
         * 字段分组统计字段未配置，或未配置成需要分组
         */
        public static final String KEY_INFO_FIELDFACET_NOTREGISTERD = "search.info.fieldfacet.notregisterd";
        
        /**
         * 当前页码不合法
         */
        public static final String KEY_INFO_PAGENO_INVALID = "search.info.pageno.invalid";
        
        /**
         * 分页值大小不合法
         */
        public static final String KEY_INFO_PAGESIZE_INVALID = "search.info.pagesize.invalid";
        
        /**
         * 拼写检查提示个数不合法
         */
        public static final String KEY_INFO_SPELLCHECK_TIPCOUNT_INVALID = "search.info.spellcheck.tipcount.invalid";
        
        /**
         * 站点Id未设值或站点Id所映射的商品目录列表为空
         */
        public static final String KEY_INFO_ILLEGALSITEID = "search.info.illegalsiteid";
        
        /**
         * 站点Id所映射的商品目录列表找不到匹配的索引配置
         */
        public static final String KEY_INFO_ILLEGALCATELOGIDS = "serach.info.illegalcatelogids";
    }

    /**
     * 
     * Title: ECP <br>
     * Project Name:mall-web <br>
     * Description: 异常信息<br>
     * Date:2017年4月24日上午8:40:48  <br>
     * Copyright (c) 2017 asia All Rights Reserved <br>
     * 
     * @author gxq
     * @version SearchMessageKeyContants 
     * @since JDK 1.6
     */
    public static class Error {
        
        /**
         * 非法部署模式
         */
        public static final String KEY_ERROR_DEPLOYTYPE = "search.error.deploytype";

        /**
         * 创建集合异常
         */
        public static final String KEY_ERROR_COLLECTION_CREATE = "search.error.collection.create";

        /**
         * 删除集合异常
         */
        public static final String KEY_ERROR_COLLECTION_DELETE = "search.error.collection.delete";

        /**
         * 全量索引异常
         */
        public static final String KEY_ERROR_IMPORT_FULL = "search.error.import.full";

        /**
         * 增量索引异常
         */
        public static final String KEY_ERROR_IMPORT_DELTA = "search.error.import.delta";

        /**
         * 删除所有索引数据异常
         */
        public static final String KEY_ERROR_INDEX_CLEAN = "search.error.index.clean";
        
        /**
         * 删除单条索引数据异常
         */
        public static final String KEY_ERROR_INDEX_DELETE = "search.error.index.delete";

        /**
         * zk链接异常
         */
        public static final String KEY_ERROR_CONNECTTO_ZK = "search.error.connect.zk";

        /**
         * solrcloud链接异常
         */
        public static final String KEY_ERROR_CONNECTTO_SOLR = "search.error.connect.solrcloud";

        /**
         * DTO转BO出现异常
         */
        public static final String KEY_ERROR_DTO2BO = "search.error.dto2bo";

        /**
         * BO转DTO出现异常
         */
        public static final String KEY_ERROR_BO2DTO = "search.error.bo2dto";
        
        /**
         * 获取格式化Bean字段列表异常
         */
        public static final String KEY_ERROR_RESULTBEAN_STRUCTURE_FIELD = "search.error.resultbean.structure.field";

        /**
         * 返回结果数据绑定异常
         */
        public static final String KEY_ERROR_RESULT_BINDING = "search.error.result.binding";
        
        /**
         * 调用其它域服务抓取索引数据返回异常
         */
        public static final String KEY_ERROR_GETSEARCHDATA = "search.error.getsearchdata";

        /**
         * 执行查询出现异常
         */
        public static final String KEY_ERROR_QUERY = "search.error.query";

        /**
         * 索引配置列表为空
         */
        public static final String KEY_ERROR_SECCONFIG_EMPTY = "search.error.secconfig.empty";
        
        /**
         * 搜索引擎配置列表为空
         */
        public static final String KEY_ERROR_SECARGS_EMPTY = "search.error.secargs.empty";

        /**
         * zk记录为空
         */
        public static final String KEY_ERROR_ZK_EMPTY = "search.error.zk.empty";
        
        /**
         * 不存在solr主服务器记录
         */
        public static final String KEY_ERROR_MASTER_NOTFOUND = "search.error.master.notfound";

        /**
         * solr集群记录为空
         */
        public static final String KEY_ERROR_SOLRCLOUD_EMPTY = "search.error.solrcloud.empty";

        /**
         * SolrServer创建失败
         */
        public static final String KEY_ERROR_SOLRSERVER_CREATE = "search.error.solrserver.create";
        
        /**
         * 索引配置创建失败
         */
        public static final String KEY_ERROR_CONFIG_ADD = "serach.error.config.add";
        
        /**
         * 索引配置删除失败
         */
        public static final String KEY_ERROR_CONFIG_DELETE = "serach.error.config.delete";
        
        /**
         * 索引配置更新失败
         */
        public static final String KEY_ERROR_CONFIG_UPDATE = "serach.error.config.update";
        
        /**
         * 数据对象创建失败
         */
        public static final String KEY_ERROR_OBJECT_ADD = "serach.error.object.add";
        
        /**
         * 数据对象删除失败
         */
        public static final String KEY_ERROR_OBJECT_DELETE = "serach.error.object.delete";
        
        /**
         * 数据对象更新失败
         */
        public static final String KEY_ERROR_OBJECT_UPDATE = "serach.error.object.update";
        
        /**
         * 数据对象字段创建失败
         */
        public static final String KEY_ERROR_FIELD_ADD = "serach.error.field.add";
        
        /**
         * 数据对象字段删除失败
         */
        public static final String KEY_ERROR_FIELD_DELETE = "serach.error.field.delete";
        
        /**
         * 数据对象字段更新失败
         */
        public static final String KEY_ERROR_FIELD_UPDATE = "serach.error.field.update";

    }

}
