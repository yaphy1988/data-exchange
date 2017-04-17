package com.ai.bdex.dataexchange.usercenter.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuthStaffPassExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_auth_staff_pass
     *
     * @mbg.generated Mon Apr 17 20:36:45 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_auth_staff_pass
     *
     * @mbg.generated Mon Apr 17 20:36:45 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_auth_staff_pass
     *
     * @mbg.generated Mon Apr 17 20:36:45 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_staff_pass
     *
     * @mbg.generated Mon Apr 17 20:36:45 CST 2017
     */
    public AuthStaffPassExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_staff_pass
     *
     * @mbg.generated Mon Apr 17 20:36:45 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_staff_pass
     *
     * @mbg.generated Mon Apr 17 20:36:45 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_staff_pass
     *
     * @mbg.generated Mon Apr 17 20:36:45 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_staff_pass
     *
     * @mbg.generated Mon Apr 17 20:36:45 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_staff_pass
     *
     * @mbg.generated Mon Apr 17 20:36:45 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_staff_pass
     *
     * @mbg.generated Mon Apr 17 20:36:45 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_staff_pass
     *
     * @mbg.generated Mon Apr 17 20:36:45 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_staff_pass
     *
     * @mbg.generated Mon Apr 17 20:36:45 CST 2017
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
     * This method corresponds to the database table t_auth_staff_pass
     *
     * @mbg.generated Mon Apr 17 20:36:45 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth_staff_pass
     *
     * @mbg.generated Mon Apr 17 20:36:45 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_auth_staff_pass
     *
     * @mbg.generated Mon Apr 17 20:36:45 CST 2017
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

        public Criteria andStaffPasswdIsNull() {
            addCriterion("STAFF_PASSWD is null");
            return (Criteria) this;
        }

        public Criteria andStaffPasswdIsNotNull() {
            addCriterion("STAFF_PASSWD is not null");
            return (Criteria) this;
        }

        public Criteria andStaffPasswdEqualTo(String value) {
            addCriterion("STAFF_PASSWD =", value, "staffPasswd");
            return (Criteria) this;
        }

        public Criteria andStaffPasswdNotEqualTo(String value) {
            addCriterion("STAFF_PASSWD <>", value, "staffPasswd");
            return (Criteria) this;
        }

        public Criteria andStaffPasswdGreaterThan(String value) {
            addCriterion("STAFF_PASSWD >", value, "staffPasswd");
            return (Criteria) this;
        }

        public Criteria andStaffPasswdGreaterThanOrEqualTo(String value) {
            addCriterion("STAFF_PASSWD >=", value, "staffPasswd");
            return (Criteria) this;
        }

        public Criteria andStaffPasswdLessThan(String value) {
            addCriterion("STAFF_PASSWD <", value, "staffPasswd");
            return (Criteria) this;
        }

        public Criteria andStaffPasswdLessThanOrEqualTo(String value) {
            addCriterion("STAFF_PASSWD <=", value, "staffPasswd");
            return (Criteria) this;
        }

        public Criteria andStaffPasswdLike(String value) {
            addCriterion("STAFF_PASSWD like", value, "staffPasswd");
            return (Criteria) this;
        }

        public Criteria andStaffPasswdNotLike(String value) {
            addCriterion("STAFF_PASSWD not like", value, "staffPasswd");
            return (Criteria) this;
        }

        public Criteria andStaffPasswdIn(List<String> values) {
            addCriterion("STAFF_PASSWD in", values, "staffPasswd");
            return (Criteria) this;
        }

        public Criteria andStaffPasswdNotIn(List<String> values) {
            addCriterion("STAFF_PASSWD not in", values, "staffPasswd");
            return (Criteria) this;
        }

        public Criteria andStaffPasswdBetween(String value1, String value2) {
            addCriterion("STAFF_PASSWD between", value1, value2, "staffPasswd");
            return (Criteria) this;
        }

        public Criteria andStaffPasswdNotBetween(String value1, String value2) {
            addCriterion("STAFF_PASSWD not between", value1, value2, "staffPasswd");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeIsNull() {
            addCriterion("INVALID_TIME is null");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeIsNotNull() {
            addCriterion("INVALID_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeEqualTo(String value) {
            addCriterion("INVALID_TIME =", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeNotEqualTo(String value) {
            addCriterion("INVALID_TIME <>", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeGreaterThan(String value) {
            addCriterion("INVALID_TIME >", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeGreaterThanOrEqualTo(String value) {
            addCriterion("INVALID_TIME >=", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeLessThan(String value) {
            addCriterion("INVALID_TIME <", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeLessThanOrEqualTo(String value) {
            addCriterion("INVALID_TIME <=", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeLike(String value) {
            addCriterion("INVALID_TIME like", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeNotLike(String value) {
            addCriterion("INVALID_TIME not like", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeIn(List<String> values) {
            addCriterion("INVALID_TIME in", values, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeNotIn(List<String> values) {
            addCriterion("INVALID_TIME not in", values, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeBetween(String value1, String value2) {
            addCriterion("INVALID_TIME between", value1, value2, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeNotBetween(String value1, String value2) {
            addCriterion("INVALID_TIME not between", value1, value2, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andIsFirstIsNull() {
            addCriterion("IS_FIRST is null");
            return (Criteria) this;
        }

        public Criteria andIsFirstIsNotNull() {
            addCriterion("IS_FIRST is not null");
            return (Criteria) this;
        }

        public Criteria andIsFirstEqualTo(String value) {
            addCriterion("IS_FIRST =", value, "isFirst");
            return (Criteria) this;
        }

        public Criteria andIsFirstNotEqualTo(String value) {
            addCriterion("IS_FIRST <>", value, "isFirst");
            return (Criteria) this;
        }

        public Criteria andIsFirstGreaterThan(String value) {
            addCriterion("IS_FIRST >", value, "isFirst");
            return (Criteria) this;
        }

        public Criteria andIsFirstGreaterThanOrEqualTo(String value) {
            addCriterion("IS_FIRST >=", value, "isFirst");
            return (Criteria) this;
        }

        public Criteria andIsFirstLessThan(String value) {
            addCriterion("IS_FIRST <", value, "isFirst");
            return (Criteria) this;
        }

        public Criteria andIsFirstLessThanOrEqualTo(String value) {
            addCriterion("IS_FIRST <=", value, "isFirst");
            return (Criteria) this;
        }

        public Criteria andIsFirstLike(String value) {
            addCriterion("IS_FIRST like", value, "isFirst");
            return (Criteria) this;
        }

        public Criteria andIsFirstNotLike(String value) {
            addCriterion("IS_FIRST not like", value, "isFirst");
            return (Criteria) this;
        }

        public Criteria andIsFirstIn(List<String> values) {
            addCriterion("IS_FIRST in", values, "isFirst");
            return (Criteria) this;
        }

        public Criteria andIsFirstNotIn(List<String> values) {
            addCriterion("IS_FIRST not in", values, "isFirst");
            return (Criteria) this;
        }

        public Criteria andIsFirstBetween(String value1, String value2) {
            addCriterion("IS_FIRST between", value1, value2, "isFirst");
            return (Criteria) this;
        }

        public Criteria andIsFirstNotBetween(String value1, String value2) {
            addCriterion("IS_FIRST not between", value1, value2, "isFirst");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIsNull() {
            addCriterion("CREATE_STAFF is null");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIsNotNull() {
            addCriterion("CREATE_STAFF is not null");
            return (Criteria) this;
        }

        public Criteria andCreateStaffEqualTo(String value) {
            addCriterion("CREATE_STAFF =", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNotEqualTo(String value) {
            addCriterion("CREATE_STAFF <>", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffGreaterThan(String value) {
            addCriterion("CREATE_STAFF >", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_STAFF >=", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLessThan(String value) {
            addCriterion("CREATE_STAFF <", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLessThanOrEqualTo(String value) {
            addCriterion("CREATE_STAFF <=", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLike(String value) {
            addCriterion("CREATE_STAFF like", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNotLike(String value) {
            addCriterion("CREATE_STAFF not like", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIn(List<String> values) {
            addCriterion("CREATE_STAFF in", values, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNotIn(List<String> values) {
            addCriterion("CREATE_STAFF not in", values, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffBetween(String value1, String value2) {
            addCriterion("CREATE_STAFF between", value1, value2, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNotBetween(String value1, String value2) {
            addCriterion("CREATE_STAFF not between", value1, value2, "createStaff");
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

        public Criteria andUpdateStaffIsNull() {
            addCriterion("UPDATE_STAFF is null");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIsNotNull() {
            addCriterion("UPDATE_STAFF is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffEqualTo(String value) {
            addCriterion("UPDATE_STAFF =", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNotEqualTo(String value) {
            addCriterion("UPDATE_STAFF <>", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffGreaterThan(String value) {
            addCriterion("UPDATE_STAFF >", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_STAFF >=", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffLessThan(String value) {
            addCriterion("UPDATE_STAFF <", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_STAFF <=", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffLike(String value) {
            addCriterion("UPDATE_STAFF like", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNotLike(String value) {
            addCriterion("UPDATE_STAFF not like", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIn(List<String> values) {
            addCriterion("UPDATE_STAFF in", values, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNotIn(List<String> values) {
            addCriterion("UPDATE_STAFF not in", values, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffBetween(String value1, String value2) {
            addCriterion("UPDATE_STAFF between", value1, value2, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNotBetween(String value1, String value2) {
            addCriterion("UPDATE_STAFF not between", value1, value2, "updateStaff");
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
     * This class corresponds to the database table t_auth_staff_pass
     *
     * @mbg.generated do_not_delete_during_merge Mon Apr 17 20:36:45 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_auth_staff_pass
     *
     * @mbg.generated Mon Apr 17 20:36:45 CST 2017
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