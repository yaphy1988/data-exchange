package com.ai.bdex.dataexchange.solrutil;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Title: ECP <br>
 * Project Name:mall-web <br>
 * Description: <br>
 * Date:2017年4月25日下午1:46:28  <br>
 * Copyright (c) 2017 asia All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
public interface QueryFilter {

    /**
     * 获取过滤条件列表
     * 
     * @param extraQueryInfo
     * @return
     */
    QueryGroup generateQueryGroup(ExtraQueryInfo extraQueryInfo);

    public static class QueryGroup {

        private List<ANDField> andFieldList;

        private List<ORGroup> orGroupList;

        public void addANDField(ANDField andField) {
            if (andFieldList == null) {
                andFieldList = new ArrayList<ANDField>();
            }
            andFieldList.add(andField);
        }

        public void addORGroup(ORGroup orGroup) {
            if (orGroupList == null) {
                orGroupList = new ArrayList<ORGroup>();
            }
            orGroupList.add(orGroup);
        }

        public List<ANDField> getAndFieldList() {
            return andFieldList;
        }

        public void setAndFieldList(List<ANDField> andFieldList) {
            this.andFieldList = andFieldList;
        }

        public List<ORGroup> getOrGroupList() {
            return orGroupList;
        }

        public void setOrGroupList(List<ORGroup> orGroupList) {
            this.orGroupList = orGroupList;
        }

    }

    /**
     * Title: ECP <br>
     * Project Name:ecp-services-search <br>
     * Description: 与查询条件组<br>
     * Date:2015年9月7日下午8:55:51 <br>
     * Copyright (c) 2015 asia All Rights Reserved <br>
     * 
     * @author huangdf
     * @version QueryFilter
     * @since JDK 1.6
     */
    public static class ANDField {

        /**
         * 可能出现需要对同一个索引字段设置多个查询条件，为避免相互覆盖
         */
        private String fieldName;

        private List<String> valueList;

        /**
         * 是否模糊查询
         */
        private boolean ifFuzzyQuery=false;

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public List<String> getValueList() {
            return valueList;
        }

        public void setValueList(List<String> valueList) {
            this.valueList = valueList;
        }

        public boolean isIfFuzzyQuery() {
            return ifFuzzyQuery;
        }

        public void setIfFuzzyQuery(boolean ifFuzzyQuery) {
            this.ifFuzzyQuery = ifFuzzyQuery;
        }

    }

    /**
     * Title: ECP <br>
     * Project Name:ecp-services-search <br>
     * Description: 或查询条件组(可包含多个字段),不同ORGroup之间使用AND操作<br>
     * Date:2015年9月7日下午8:55:21 <br>
     * Copyright (c) 2015 asia All Rights Reserved <br>
     * 
     * @author huangdf
     * @version QueryFilter
     * @since JDK 1.6
     */
    public static class ORGroup {

        /**
         * KEY:索引字段配置的属性名,VALUE:同一个索引字段允许设置多个
         */
        private List<ORField> orFieldList;

        public void addORField(ORField orField) {
            if (orFieldList == null) {
                orFieldList = new ArrayList<ORField>();
            }
            orFieldList.add(orField);
        }

        public List<ORField> getOrFieldList() {
            return orFieldList;
        }

        public void setOrFieldList(List<ORField> orFieldList) {
            this.orFieldList = orFieldList;
        }

        public static class ORField extends ANDField {

        }

    }

}
