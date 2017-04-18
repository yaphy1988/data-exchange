package com.ai.bdex.dataexchange.tradecenter.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SortContentExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_sort_content
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_sort_content
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_sort_content
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sort_content
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public SortContentExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sort_content
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sort_content
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sort_content
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sort_content
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sort_content
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sort_content
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sort_content
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sort_content
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sort_content
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sort_content
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_sort_content
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andSortContentIdIsNull() {
            addCriterion("SORT_CONTENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andSortContentIdIsNotNull() {
            addCriterion("SORT_CONTENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSortContentIdEqualTo(Integer value) {
            addCriterion("SORT_CONTENT_ID =", value, "sortContentId");
            return (Criteria) this;
        }

        public Criteria andSortContentIdNotEqualTo(Integer value) {
            addCriterion("SORT_CONTENT_ID <>", value, "sortContentId");
            return (Criteria) this;
        }

        public Criteria andSortContentIdGreaterThan(Integer value) {
            addCriterion("SORT_CONTENT_ID >", value, "sortContentId");
            return (Criteria) this;
        }

        public Criteria andSortContentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("SORT_CONTENT_ID >=", value, "sortContentId");
            return (Criteria) this;
        }

        public Criteria andSortContentIdLessThan(Integer value) {
            addCriterion("SORT_CONTENT_ID <", value, "sortContentId");
            return (Criteria) this;
        }

        public Criteria andSortContentIdLessThanOrEqualTo(Integer value) {
            addCriterion("SORT_CONTENT_ID <=", value, "sortContentId");
            return (Criteria) this;
        }

        public Criteria andSortContentIdIn(List<Integer> values) {
            addCriterion("SORT_CONTENT_ID in", values, "sortContentId");
            return (Criteria) this;
        }

        public Criteria andSortContentIdNotIn(List<Integer> values) {
            addCriterion("SORT_CONTENT_ID not in", values, "sortContentId");
            return (Criteria) this;
        }

        public Criteria andSortContentIdBetween(Integer value1, Integer value2) {
            addCriterion("SORT_CONTENT_ID between", value1, value2, "sortContentId");
            return (Criteria) this;
        }

        public Criteria andSortContentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("SORT_CONTENT_ID not between", value1, value2, "sortContentId");
            return (Criteria) this;
        }

        public Criteria andSortIdIsNull() {
            addCriterion("SORT_ID is null");
            return (Criteria) this;
        }

        public Criteria andSortIdIsNotNull() {
            addCriterion("SORT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSortIdEqualTo(Integer value) {
            addCriterion("SORT_ID =", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdNotEqualTo(Integer value) {
            addCriterion("SORT_ID <>", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdGreaterThan(Integer value) {
            addCriterion("SORT_ID >", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("SORT_ID >=", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdLessThan(Integer value) {
            addCriterion("SORT_ID <", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdLessThanOrEqualTo(Integer value) {
            addCriterion("SORT_ID <=", value, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdIn(List<Integer> values) {
            addCriterion("SORT_ID in", values, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdNotIn(List<Integer> values) {
            addCriterion("SORT_ID not in", values, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdBetween(Integer value1, Integer value2) {
            addCriterion("SORT_ID between", value1, value2, "sortId");
            return (Criteria) this;
        }

        public Criteria andSortIdNotBetween(Integer value1, Integer value2) {
            addCriterion("SORT_ID not between", value1, value2, "sortId");
            return (Criteria) this;
        }

        public Criteria andContentNameIsNull() {
            addCriterion("CONTENT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andContentNameIsNotNull() {
            addCriterion("CONTENT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andContentNameEqualTo(String value) {
            addCriterion("CONTENT_NAME =", value, "contentName");
            return (Criteria) this;
        }

        public Criteria andContentNameNotEqualTo(String value) {
            addCriterion("CONTENT_NAME <>", value, "contentName");
            return (Criteria) this;
        }

        public Criteria andContentNameGreaterThan(String value) {
            addCriterion("CONTENT_NAME >", value, "contentName");
            return (Criteria) this;
        }

        public Criteria andContentNameGreaterThanOrEqualTo(String value) {
            addCriterion("CONTENT_NAME >=", value, "contentName");
            return (Criteria) this;
        }

        public Criteria andContentNameLessThan(String value) {
            addCriterion("CONTENT_NAME <", value, "contentName");
            return (Criteria) this;
        }

        public Criteria andContentNameLessThanOrEqualTo(String value) {
            addCriterion("CONTENT_NAME <=", value, "contentName");
            return (Criteria) this;
        }

        public Criteria andContentNameLike(String value) {
            addCriterion("CONTENT_NAME like", value, "contentName");
            return (Criteria) this;
        }

        public Criteria andContentNameNotLike(String value) {
            addCriterion("CONTENT_NAME not like", value, "contentName");
            return (Criteria) this;
        }

        public Criteria andContentNameIn(List<String> values) {
            addCriterion("CONTENT_NAME in", values, "contentName");
            return (Criteria) this;
        }

        public Criteria andContentNameNotIn(List<String> values) {
            addCriterion("CONTENT_NAME not in", values, "contentName");
            return (Criteria) this;
        }

        public Criteria andContentNameBetween(String value1, String value2) {
            addCriterion("CONTENT_NAME between", value1, value2, "contentName");
            return (Criteria) this;
        }

        public Criteria andContentNameNotBetween(String value1, String value2) {
            addCriterion("CONTENT_NAME not between", value1, value2, "contentName");
            return (Criteria) this;
        }

        public Criteria andContentLinkIsNull() {
            addCriterion("CONTENT_LINK is null");
            return (Criteria) this;
        }

        public Criteria andContentLinkIsNotNull() {
            addCriterion("CONTENT_LINK is not null");
            return (Criteria) this;
        }

        public Criteria andContentLinkEqualTo(String value) {
            addCriterion("CONTENT_LINK =", value, "contentLink");
            return (Criteria) this;
        }

        public Criteria andContentLinkNotEqualTo(String value) {
            addCriterion("CONTENT_LINK <>", value, "contentLink");
            return (Criteria) this;
        }

        public Criteria andContentLinkGreaterThan(String value) {
            addCriterion("CONTENT_LINK >", value, "contentLink");
            return (Criteria) this;
        }

        public Criteria andContentLinkGreaterThanOrEqualTo(String value) {
            addCriterion("CONTENT_LINK >=", value, "contentLink");
            return (Criteria) this;
        }

        public Criteria andContentLinkLessThan(String value) {
            addCriterion("CONTENT_LINK <", value, "contentLink");
            return (Criteria) this;
        }

        public Criteria andContentLinkLessThanOrEqualTo(String value) {
            addCriterion("CONTENT_LINK <=", value, "contentLink");
            return (Criteria) this;
        }

        public Criteria andContentLinkLike(String value) {
            addCriterion("CONTENT_LINK like", value, "contentLink");
            return (Criteria) this;
        }

        public Criteria andContentLinkNotLike(String value) {
            addCriterion("CONTENT_LINK not like", value, "contentLink");
            return (Criteria) this;
        }

        public Criteria andContentLinkIn(List<String> values) {
            addCriterion("CONTENT_LINK in", values, "contentLink");
            return (Criteria) this;
        }

        public Criteria andContentLinkNotIn(List<String> values) {
            addCriterion("CONTENT_LINK not in", values, "contentLink");
            return (Criteria) this;
        }

        public Criteria andContentLinkBetween(String value1, String value2) {
            addCriterion("CONTENT_LINK between", value1, value2, "contentLink");
            return (Criteria) this;
        }

        public Criteria andContentLinkNotBetween(String value1, String value2) {
            addCriterion("CONTENT_LINK not between", value1, value2, "contentLink");
            return (Criteria) this;
        }

        public Criteria andVfsIdIsNull() {
            addCriterion("VFS_ID is null");
            return (Criteria) this;
        }

        public Criteria andVfsIdIsNotNull() {
            addCriterion("VFS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andVfsIdEqualTo(String value) {
            addCriterion("VFS_ID =", value, "vfsId");
            return (Criteria) this;
        }

        public Criteria andVfsIdNotEqualTo(String value) {
            addCriterion("VFS_ID <>", value, "vfsId");
            return (Criteria) this;
        }

        public Criteria andVfsIdGreaterThan(String value) {
            addCriterion("VFS_ID >", value, "vfsId");
            return (Criteria) this;
        }

        public Criteria andVfsIdGreaterThanOrEqualTo(String value) {
            addCriterion("VFS_ID >=", value, "vfsId");
            return (Criteria) this;
        }

        public Criteria andVfsIdLessThan(String value) {
            addCriterion("VFS_ID <", value, "vfsId");
            return (Criteria) this;
        }

        public Criteria andVfsIdLessThanOrEqualTo(String value) {
            addCriterion("VFS_ID <=", value, "vfsId");
            return (Criteria) this;
        }

        public Criteria andVfsIdLike(String value) {
            addCriterion("VFS_ID like", value, "vfsId");
            return (Criteria) this;
        }

        public Criteria andVfsIdNotLike(String value) {
            addCriterion("VFS_ID not like", value, "vfsId");
            return (Criteria) this;
        }

        public Criteria andVfsIdIn(List<String> values) {
            addCriterion("VFS_ID in", values, "vfsId");
            return (Criteria) this;
        }

        public Criteria andVfsIdNotIn(List<String> values) {
            addCriterion("VFS_ID not in", values, "vfsId");
            return (Criteria) this;
        }

        public Criteria andVfsIdBetween(String value1, String value2) {
            addCriterion("VFS_ID between", value1, value2, "vfsId");
            return (Criteria) this;
        }

        public Criteria andVfsIdNotBetween(String value1, String value2) {
            addCriterion("VFS_ID not between", value1, value2, "vfsId");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNull() {
            addCriterion("ORDER_NO is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("ORDER_NO is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("ORDER_NO =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("ORDER_NO <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("ORDER_NO >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_NO >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("ORDER_NO <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("ORDER_NO <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("ORDER_NO like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("ORDER_NO not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("ORDER_NO in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("ORDER_NO not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("ORDER_NO between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("ORDER_NO not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andMoreUrlIsNull() {
            addCriterion("more_url is null");
            return (Criteria) this;
        }

        public Criteria andMoreUrlIsNotNull() {
            addCriterion("more_url is not null");
            return (Criteria) this;
        }

        public Criteria andMoreUrlEqualTo(String value) {
            addCriterion("more_url =", value, "moreUrl");
            return (Criteria) this;
        }

        public Criteria andMoreUrlNotEqualTo(String value) {
            addCriterion("more_url <>", value, "moreUrl");
            return (Criteria) this;
        }

        public Criteria andMoreUrlGreaterThan(String value) {
            addCriterion("more_url >", value, "moreUrl");
            return (Criteria) this;
        }

        public Criteria andMoreUrlGreaterThanOrEqualTo(String value) {
            addCriterion("more_url >=", value, "moreUrl");
            return (Criteria) this;
        }

        public Criteria andMoreUrlLessThan(String value) {
            addCriterion("more_url <", value, "moreUrl");
            return (Criteria) this;
        }

        public Criteria andMoreUrlLessThanOrEqualTo(String value) {
            addCriterion("more_url <=", value, "moreUrl");
            return (Criteria) this;
        }

        public Criteria andMoreUrlLike(String value) {
            addCriterion("more_url like", value, "moreUrl");
            return (Criteria) this;
        }

        public Criteria andMoreUrlNotLike(String value) {
            addCriterion("more_url not like", value, "moreUrl");
            return (Criteria) this;
        }

        public Criteria andMoreUrlIn(List<String> values) {
            addCriterion("more_url in", values, "moreUrl");
            return (Criteria) this;
        }

        public Criteria andMoreUrlNotIn(List<String> values) {
            addCriterion("more_url not in", values, "moreUrl");
            return (Criteria) this;
        }

        public Criteria andMoreUrlBetween(String value1, String value2) {
            addCriterion("more_url between", value1, value2, "moreUrl");
            return (Criteria) this;
        }

        public Criteria andMoreUrlNotBetween(String value1, String value2) {
            addCriterion("more_url not between", value1, value2, "moreUrl");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdIsNull() {
            addCriterion("CREATE_STAFF_ID is null");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdIsNotNull() {
            addCriterion("CREATE_STAFF_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdEqualTo(String value) {
            addCriterion("CREATE_STAFF_ID =", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdNotEqualTo(String value) {
            addCriterion("CREATE_STAFF_ID <>", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdGreaterThan(String value) {
            addCriterion("CREATE_STAFF_ID >", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_STAFF_ID >=", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdLessThan(String value) {
            addCriterion("CREATE_STAFF_ID <", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdLessThanOrEqualTo(String value) {
            addCriterion("CREATE_STAFF_ID <=", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdLike(String value) {
            addCriterion("CREATE_STAFF_ID like", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdNotLike(String value) {
            addCriterion("CREATE_STAFF_ID not like", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdIn(List<String> values) {
            addCriterion("CREATE_STAFF_ID in", values, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdNotIn(List<String> values) {
            addCriterion("CREATE_STAFF_ID not in", values, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdBetween(String value1, String value2) {
            addCriterion("CREATE_STAFF_ID between", value1, value2, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdNotBetween(String value1, String value2) {
            addCriterion("CREATE_STAFF_ID not between", value1, value2, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdIsNull() {
            addCriterion("UPDATE_STAFF_ID is null");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdIsNotNull() {
            addCriterion("UPDATE_STAFF_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdEqualTo(String value) {
            addCriterion("UPDATE_STAFF_ID =", value, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdNotEqualTo(String value) {
            addCriterion("UPDATE_STAFF_ID <>", value, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdGreaterThan(String value) {
            addCriterion("UPDATE_STAFF_ID >", value, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_STAFF_ID >=", value, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdLessThan(String value) {
            addCriterion("UPDATE_STAFF_ID <", value, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_STAFF_ID <=", value, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdLike(String value) {
            addCriterion("UPDATE_STAFF_ID like", value, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdNotLike(String value) {
            addCriterion("UPDATE_STAFF_ID not like", value, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdIn(List<String> values) {
            addCriterion("UPDATE_STAFF_ID in", values, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdNotIn(List<String> values) {
            addCriterion("UPDATE_STAFF_ID not in", values, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdBetween(String value1, String value2) {
            addCriterion("UPDATE_STAFF_ID between", value1, value2, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdNotBetween(String value1, String value2) {
            addCriterion("UPDATE_STAFF_ID not between", value1, value2, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_sort_content
     *
     * @mbg.generated do_not_delete_during_merge Tue Apr 18 19:17:03 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_sort_content
     *
     * @mbg.generated Tue Apr 18 19:17:03 CST 2017
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}