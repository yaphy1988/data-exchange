package com.ai.bdex.dataexchange.tradecenter.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdComplaintAttExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_ord_complaint_att
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_ord_complaint_att
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_ord_complaint_att
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_complaint_att
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public OrdComplaintAttExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_complaint_att
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_complaint_att
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_complaint_att
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_complaint_att
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_complaint_att
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_complaint_att
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_complaint_att
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_complaint_att
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
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
     * This method corresponds to the database table t_ord_complaint_att
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ord_complaint_att
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_ord_complaint_att
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
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

        public Criteria andComplaintAttIdIsNull() {
            addCriterion("COMPLAINT_ATT_ID is null");
            return (Criteria) this;
        }

        public Criteria andComplaintAttIdIsNotNull() {
            addCriterion("COMPLAINT_ATT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andComplaintAttIdEqualTo(Long value) {
            addCriterion("COMPLAINT_ATT_ID =", value, "complaintAttId");
            return (Criteria) this;
        }

        public Criteria andComplaintAttIdNotEqualTo(Long value) {
            addCriterion("COMPLAINT_ATT_ID <>", value, "complaintAttId");
            return (Criteria) this;
        }

        public Criteria andComplaintAttIdGreaterThan(Long value) {
            addCriterion("COMPLAINT_ATT_ID >", value, "complaintAttId");
            return (Criteria) this;
        }

        public Criteria andComplaintAttIdGreaterThanOrEqualTo(Long value) {
            addCriterion("COMPLAINT_ATT_ID >=", value, "complaintAttId");
            return (Criteria) this;
        }

        public Criteria andComplaintAttIdLessThan(Long value) {
            addCriterion("COMPLAINT_ATT_ID <", value, "complaintAttId");
            return (Criteria) this;
        }

        public Criteria andComplaintAttIdLessThanOrEqualTo(Long value) {
            addCriterion("COMPLAINT_ATT_ID <=", value, "complaintAttId");
            return (Criteria) this;
        }

        public Criteria andComplaintAttIdIn(List<Long> values) {
            addCriterion("COMPLAINT_ATT_ID in", values, "complaintAttId");
            return (Criteria) this;
        }

        public Criteria andComplaintAttIdNotIn(List<Long> values) {
            addCriterion("COMPLAINT_ATT_ID not in", values, "complaintAttId");
            return (Criteria) this;
        }

        public Criteria andComplaintAttIdBetween(Long value1, Long value2) {
            addCriterion("COMPLAINT_ATT_ID between", value1, value2, "complaintAttId");
            return (Criteria) this;
        }

        public Criteria andComplaintAttIdNotBetween(Long value1, Long value2) {
            addCriterion("COMPLAINT_ATT_ID not between", value1, value2, "complaintAttId");
            return (Criteria) this;
        }

        public Criteria andComplaintIdIsNull() {
            addCriterion("COMPLAINT_ID is null");
            return (Criteria) this;
        }

        public Criteria andComplaintIdIsNotNull() {
            addCriterion("COMPLAINT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andComplaintIdEqualTo(Long value) {
            addCriterion("COMPLAINT_ID =", value, "complaintId");
            return (Criteria) this;
        }

        public Criteria andComplaintIdNotEqualTo(Long value) {
            addCriterion("COMPLAINT_ID <>", value, "complaintId");
            return (Criteria) this;
        }

        public Criteria andComplaintIdGreaterThan(Long value) {
            addCriterion("COMPLAINT_ID >", value, "complaintId");
            return (Criteria) this;
        }

        public Criteria andComplaintIdGreaterThanOrEqualTo(Long value) {
            addCriterion("COMPLAINT_ID >=", value, "complaintId");
            return (Criteria) this;
        }

        public Criteria andComplaintIdLessThan(Long value) {
            addCriterion("COMPLAINT_ID <", value, "complaintId");
            return (Criteria) this;
        }

        public Criteria andComplaintIdLessThanOrEqualTo(Long value) {
            addCriterion("COMPLAINT_ID <=", value, "complaintId");
            return (Criteria) this;
        }

        public Criteria andComplaintIdIn(List<Long> values) {
            addCriterion("COMPLAINT_ID in", values, "complaintId");
            return (Criteria) this;
        }

        public Criteria andComplaintIdNotIn(List<Long> values) {
            addCriterion("COMPLAINT_ID not in", values, "complaintId");
            return (Criteria) this;
        }

        public Criteria andComplaintIdBetween(Long value1, Long value2) {
            addCriterion("COMPLAINT_ID between", value1, value2, "complaintId");
            return (Criteria) this;
        }

        public Criteria andComplaintIdNotBetween(Long value1, Long value2) {
            addCriterion("COMPLAINT_ID not between", value1, value2, "complaintId");
            return (Criteria) this;
        }

        public Criteria andCmplaintContIdIsNull() {
            addCriterion("CMPLAINT_CONT_ID is null");
            return (Criteria) this;
        }

        public Criteria andCmplaintContIdIsNotNull() {
            addCriterion("CMPLAINT_CONT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCmplaintContIdEqualTo(Long value) {
            addCriterion("CMPLAINT_CONT_ID =", value, "cmplaintContId");
            return (Criteria) this;
        }

        public Criteria andCmplaintContIdNotEqualTo(Long value) {
            addCriterion("CMPLAINT_CONT_ID <>", value, "cmplaintContId");
            return (Criteria) this;
        }

        public Criteria andCmplaintContIdGreaterThan(Long value) {
            addCriterion("CMPLAINT_CONT_ID >", value, "cmplaintContId");
            return (Criteria) this;
        }

        public Criteria andCmplaintContIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CMPLAINT_CONT_ID >=", value, "cmplaintContId");
            return (Criteria) this;
        }

        public Criteria andCmplaintContIdLessThan(Long value) {
            addCriterion("CMPLAINT_CONT_ID <", value, "cmplaintContId");
            return (Criteria) this;
        }

        public Criteria andCmplaintContIdLessThanOrEqualTo(Long value) {
            addCriterion("CMPLAINT_CONT_ID <=", value, "cmplaintContId");
            return (Criteria) this;
        }

        public Criteria andCmplaintContIdIn(List<Long> values) {
            addCriterion("CMPLAINT_CONT_ID in", values, "cmplaintContId");
            return (Criteria) this;
        }

        public Criteria andCmplaintContIdNotIn(List<Long> values) {
            addCriterion("CMPLAINT_CONT_ID not in", values, "cmplaintContId");
            return (Criteria) this;
        }

        public Criteria andCmplaintContIdBetween(Long value1, Long value2) {
            addCriterion("CMPLAINT_CONT_ID between", value1, value2, "cmplaintContId");
            return (Criteria) this;
        }

        public Criteria andCmplaintContIdNotBetween(Long value1, Long value2) {
            addCriterion("CMPLAINT_CONT_ID not between", value1, value2, "cmplaintContId");
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

        public Criteria andVfsNameIsNull() {
            addCriterion("VFS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andVfsNameIsNotNull() {
            addCriterion("VFS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andVfsNameEqualTo(String value) {
            addCriterion("VFS_NAME =", value, "vfsName");
            return (Criteria) this;
        }

        public Criteria andVfsNameNotEqualTo(String value) {
            addCriterion("VFS_NAME <>", value, "vfsName");
            return (Criteria) this;
        }

        public Criteria andVfsNameGreaterThan(String value) {
            addCriterion("VFS_NAME >", value, "vfsName");
            return (Criteria) this;
        }

        public Criteria andVfsNameGreaterThanOrEqualTo(String value) {
            addCriterion("VFS_NAME >=", value, "vfsName");
            return (Criteria) this;
        }

        public Criteria andVfsNameLessThan(String value) {
            addCriterion("VFS_NAME <", value, "vfsName");
            return (Criteria) this;
        }

        public Criteria andVfsNameLessThanOrEqualTo(String value) {
            addCriterion("VFS_NAME <=", value, "vfsName");
            return (Criteria) this;
        }

        public Criteria andVfsNameLike(String value) {
            addCriterion("VFS_NAME like", value, "vfsName");
            return (Criteria) this;
        }

        public Criteria andVfsNameNotLike(String value) {
            addCriterion("VFS_NAME not like", value, "vfsName");
            return (Criteria) this;
        }

        public Criteria andVfsNameIn(List<String> values) {
            addCriterion("VFS_NAME in", values, "vfsName");
            return (Criteria) this;
        }

        public Criteria andVfsNameNotIn(List<String> values) {
            addCriterion("VFS_NAME not in", values, "vfsName");
            return (Criteria) this;
        }

        public Criteria andVfsNameBetween(String value1, String value2) {
            addCriterion("VFS_NAME between", value1, value2, "vfsName");
            return (Criteria) this;
        }

        public Criteria andVfsNameNotBetween(String value1, String value2) {
            addCriterion("VFS_NAME not between", value1, value2, "vfsName");
            return (Criteria) this;
        }

        public Criteria andStaffIdIsNull() {
            addCriterion("STAFF_ID is null");
            return (Criteria) this;
        }

        public Criteria andStaffIdIsNotNull() {
            addCriterion("STAFF_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStaffIdEqualTo(String value) {
            addCriterion("STAFF_ID =", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotEqualTo(String value) {
            addCriterion("STAFF_ID <>", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdGreaterThan(String value) {
            addCriterion("STAFF_ID >", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdGreaterThanOrEqualTo(String value) {
            addCriterion("STAFF_ID >=", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdLessThan(String value) {
            addCriterion("STAFF_ID <", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdLessThanOrEqualTo(String value) {
            addCriterion("STAFF_ID <=", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdLike(String value) {
            addCriterion("STAFF_ID like", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotLike(String value) {
            addCriterion("STAFF_ID not like", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdIn(List<String> values) {
            addCriterion("STAFF_ID in", values, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotIn(List<String> values) {
            addCriterion("STAFF_ID not in", values, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdBetween(String value1, String value2) {
            addCriterion("STAFF_ID between", value1, value2, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotBetween(String value1, String value2) {
            addCriterion("STAFF_ID not between", value1, value2, "staffId");
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
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_ord_complaint_att
     *
     * @mbg.generated do_not_delete_during_merge Mon Apr 17 20:54:45 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_ord_complaint_att
     *
     * @mbg.generated Mon Apr 17 20:54:45 CST 2017
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