package com.ai.bdex.dataexchange.tradecenter.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SortInfoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_sort_info
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_sort_info
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_sort_info
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sort_info
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public SortInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sort_info
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sort_info
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sort_info
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sort_info
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sort_info
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sort_info
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sort_info
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sort_info
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
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
     * This method corresponds to the database table t_sort_info
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sort_info
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_sort_info
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
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

        public Criteria andSortTypeIsNull() {
            addCriterion("SORT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSortTypeIsNotNull() {
            addCriterion("SORT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSortTypeEqualTo(String value) {
            addCriterion("SORT_TYPE =", value, "sortType");
            return (Criteria) this;
        }

        public Criteria andSortTypeNotEqualTo(String value) {
            addCriterion("SORT_TYPE <>", value, "sortType");
            return (Criteria) this;
        }

        public Criteria andSortTypeGreaterThan(String value) {
            addCriterion("SORT_TYPE >", value, "sortType");
            return (Criteria) this;
        }

        public Criteria andSortTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SORT_TYPE >=", value, "sortType");
            return (Criteria) this;
        }

        public Criteria andSortTypeLessThan(String value) {
            addCriterion("SORT_TYPE <", value, "sortType");
            return (Criteria) this;
        }

        public Criteria andSortTypeLessThanOrEqualTo(String value) {
            addCriterion("SORT_TYPE <=", value, "sortType");
            return (Criteria) this;
        }

        public Criteria andSortTypeLike(String value) {
            addCriterion("SORT_TYPE like", value, "sortType");
            return (Criteria) this;
        }

        public Criteria andSortTypeNotLike(String value) {
            addCriterion("SORT_TYPE not like", value, "sortType");
            return (Criteria) this;
        }

        public Criteria andSortTypeIn(List<String> values) {
            addCriterion("SORT_TYPE in", values, "sortType");
            return (Criteria) this;
        }

        public Criteria andSortTypeNotIn(List<String> values) {
            addCriterion("SORT_TYPE not in", values, "sortType");
            return (Criteria) this;
        }

        public Criteria andSortTypeBetween(String value1, String value2) {
            addCriterion("SORT_TYPE between", value1, value2, "sortType");
            return (Criteria) this;
        }

        public Criteria andSortTypeNotBetween(String value1, String value2) {
            addCriterion("SORT_TYPE not between", value1, value2, "sortType");
            return (Criteria) this;
        }

        public Criteria andSortLevelIsNull() {
            addCriterion("SORT_LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andSortLevelIsNotNull() {
            addCriterion("SORT_LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andSortLevelEqualTo(String value) {
            addCriterion("SORT_LEVEL =", value, "sortLevel");
            return (Criteria) this;
        }

        public Criteria andSortLevelNotEqualTo(String value) {
            addCriterion("SORT_LEVEL <>", value, "sortLevel");
            return (Criteria) this;
        }

        public Criteria andSortLevelGreaterThan(String value) {
            addCriterion("SORT_LEVEL >", value, "sortLevel");
            return (Criteria) this;
        }

        public Criteria andSortLevelGreaterThanOrEqualTo(String value) {
            addCriterion("SORT_LEVEL >=", value, "sortLevel");
            return (Criteria) this;
        }

        public Criteria andSortLevelLessThan(String value) {
            addCriterion("SORT_LEVEL <", value, "sortLevel");
            return (Criteria) this;
        }

        public Criteria andSortLevelLessThanOrEqualTo(String value) {
            addCriterion("SORT_LEVEL <=", value, "sortLevel");
            return (Criteria) this;
        }

        public Criteria andSortLevelLike(String value) {
            addCriterion("SORT_LEVEL like", value, "sortLevel");
            return (Criteria) this;
        }

        public Criteria andSortLevelNotLike(String value) {
            addCriterion("SORT_LEVEL not like", value, "sortLevel");
            return (Criteria) this;
        }

        public Criteria andSortLevelIn(List<String> values) {
            addCriterion("SORT_LEVEL in", values, "sortLevel");
            return (Criteria) this;
        }

        public Criteria andSortLevelNotIn(List<String> values) {
            addCriterion("SORT_LEVEL not in", values, "sortLevel");
            return (Criteria) this;
        }

        public Criteria andSortLevelBetween(String value1, String value2) {
            addCriterion("SORT_LEVEL between", value1, value2, "sortLevel");
            return (Criteria) this;
        }

        public Criteria andSortLevelNotBetween(String value1, String value2) {
            addCriterion("SORT_LEVEL not between", value1, value2, "sortLevel");
            return (Criteria) this;
        }

        public Criteria andSortNameIsNull() {
            addCriterion("SORT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSortNameIsNotNull() {
            addCriterion("SORT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSortNameEqualTo(String value) {
            addCriterion("SORT_NAME =", value, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameNotEqualTo(String value) {
            addCriterion("SORT_NAME <>", value, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameGreaterThan(String value) {
            addCriterion("SORT_NAME >", value, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameGreaterThanOrEqualTo(String value) {
            addCriterion("SORT_NAME >=", value, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameLessThan(String value) {
            addCriterion("SORT_NAME <", value, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameLessThanOrEqualTo(String value) {
            addCriterion("SORT_NAME <=", value, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameLike(String value) {
            addCriterion("SORT_NAME like", value, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameNotLike(String value) {
            addCriterion("SORT_NAME not like", value, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameIn(List<String> values) {
            addCriterion("SORT_NAME in", values, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameNotIn(List<String> values) {
            addCriterion("SORT_NAME not in", values, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameBetween(String value1, String value2) {
            addCriterion("SORT_NAME between", value1, value2, "sortName");
            return (Criteria) this;
        }

        public Criteria andSortNameNotBetween(String value1, String value2) {
            addCriterion("SORT_NAME not between", value1, value2, "sortName");
            return (Criteria) this;
        }

        public Criteria andParentSortIdIsNull() {
            addCriterion("PARENT_SORT_ID is null");
            return (Criteria) this;
        }

        public Criteria andParentSortIdIsNotNull() {
            addCriterion("PARENT_SORT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andParentSortIdEqualTo(Integer value) {
            addCriterion("PARENT_SORT_ID =", value, "parentSortId");
            return (Criteria) this;
        }

        public Criteria andParentSortIdNotEqualTo(Integer value) {
            addCriterion("PARENT_SORT_ID <>", value, "parentSortId");
            return (Criteria) this;
        }

        public Criteria andParentSortIdGreaterThan(Integer value) {
            addCriterion("PARENT_SORT_ID >", value, "parentSortId");
            return (Criteria) this;
        }

        public Criteria andParentSortIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("PARENT_SORT_ID >=", value, "parentSortId");
            return (Criteria) this;
        }

        public Criteria andParentSortIdLessThan(Integer value) {
            addCriterion("PARENT_SORT_ID <", value, "parentSortId");
            return (Criteria) this;
        }

        public Criteria andParentSortIdLessThanOrEqualTo(Integer value) {
            addCriterion("PARENT_SORT_ID <=", value, "parentSortId");
            return (Criteria) this;
        }

        public Criteria andParentSortIdIn(List<Integer> values) {
            addCriterion("PARENT_SORT_ID in", values, "parentSortId");
            return (Criteria) this;
        }

        public Criteria andParentSortIdNotIn(List<Integer> values) {
            addCriterion("PARENT_SORT_ID not in", values, "parentSortId");
            return (Criteria) this;
        }

        public Criteria andParentSortIdBetween(Integer value1, Integer value2) {
            addCriterion("PARENT_SORT_ID between", value1, value2, "parentSortId");
            return (Criteria) this;
        }

        public Criteria andParentSortIdNotBetween(Integer value1, Integer value2) {
            addCriterion("PARENT_SORT_ID not between", value1, value2, "parentSortId");
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
     * This class corresponds to the database table t_sort_info
     *
     * @mbg.generated do_not_delete_during_merge Tue Apr 18 20:36:16 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_sort_info
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
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