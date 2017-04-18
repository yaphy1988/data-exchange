package com.ai.bdex.dataexchange.tradecenter.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GdsLabelExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_gds_label
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_gds_label
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_gds_label
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_label
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public GdsLabelExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_label
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_label
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_label
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_label
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_label
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_label
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_label
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_label
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
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
     * This method corresponds to the database table t_gds_label
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gds_label
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_gds_label
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
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

        public Criteria andLabIdIsNull() {
            addCriterion("lab_id is null");
            return (Criteria) this;
        }

        public Criteria andLabIdIsNotNull() {
            addCriterion("lab_id is not null");
            return (Criteria) this;
        }

        public Criteria andLabIdEqualTo(Integer value) {
            addCriterion("lab_id =", value, "labId");
            return (Criteria) this;
        }

        public Criteria andLabIdNotEqualTo(Integer value) {
            addCriterion("lab_id <>", value, "labId");
            return (Criteria) this;
        }

        public Criteria andLabIdGreaterThan(Integer value) {
            addCriterion("lab_id >", value, "labId");
            return (Criteria) this;
        }

        public Criteria andLabIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("lab_id >=", value, "labId");
            return (Criteria) this;
        }

        public Criteria andLabIdLessThan(Integer value) {
            addCriterion("lab_id <", value, "labId");
            return (Criteria) this;
        }

        public Criteria andLabIdLessThanOrEqualTo(Integer value) {
            addCriterion("lab_id <=", value, "labId");
            return (Criteria) this;
        }

        public Criteria andLabIdIn(List<Integer> values) {
            addCriterion("lab_id in", values, "labId");
            return (Criteria) this;
        }

        public Criteria andLabIdNotIn(List<Integer> values) {
            addCriterion("lab_id not in", values, "labId");
            return (Criteria) this;
        }

        public Criteria andLabIdBetween(Integer value1, Integer value2) {
            addCriterion("lab_id between", value1, value2, "labId");
            return (Criteria) this;
        }

        public Criteria andLabIdNotBetween(Integer value1, Integer value2) {
            addCriterion("lab_id not between", value1, value2, "labId");
            return (Criteria) this;
        }

        public Criteria andGdsIdIsNull() {
            addCriterion("gds_id is null");
            return (Criteria) this;
        }

        public Criteria andGdsIdIsNotNull() {
            addCriterion("gds_id is not null");
            return (Criteria) this;
        }

        public Criteria andGdsIdEqualTo(Integer value) {
            addCriterion("gds_id =", value, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdNotEqualTo(Integer value) {
            addCriterion("gds_id <>", value, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdGreaterThan(Integer value) {
            addCriterion("gds_id >", value, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("gds_id >=", value, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdLessThan(Integer value) {
            addCriterion("gds_id <", value, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdLessThanOrEqualTo(Integer value) {
            addCriterion("gds_id <=", value, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdIn(List<Integer> values) {
            addCriterion("gds_id in", values, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdNotIn(List<Integer> values) {
            addCriterion("gds_id not in", values, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdBetween(Integer value1, Integer value2) {
            addCriterion("gds_id between", value1, value2, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("gds_id not between", value1, value2, "gdsId");
            return (Criteria) this;
        }

        public Criteria andLabNameIsNull() {
            addCriterion("lab_name is null");
            return (Criteria) this;
        }

        public Criteria andLabNameIsNotNull() {
            addCriterion("lab_name is not null");
            return (Criteria) this;
        }

        public Criteria andLabNameEqualTo(String value) {
            addCriterion("lab_name =", value, "labName");
            return (Criteria) this;
        }

        public Criteria andLabNameNotEqualTo(String value) {
            addCriterion("lab_name <>", value, "labName");
            return (Criteria) this;
        }

        public Criteria andLabNameGreaterThan(String value) {
            addCriterion("lab_name >", value, "labName");
            return (Criteria) this;
        }

        public Criteria andLabNameGreaterThanOrEqualTo(String value) {
            addCriterion("lab_name >=", value, "labName");
            return (Criteria) this;
        }

        public Criteria andLabNameLessThan(String value) {
            addCriterion("lab_name <", value, "labName");
            return (Criteria) this;
        }

        public Criteria andLabNameLessThanOrEqualTo(String value) {
            addCriterion("lab_name <=", value, "labName");
            return (Criteria) this;
        }

        public Criteria andLabNameLike(String value) {
            addCriterion("lab_name like", value, "labName");
            return (Criteria) this;
        }

        public Criteria andLabNameNotLike(String value) {
            addCriterion("lab_name not like", value, "labName");
            return (Criteria) this;
        }

        public Criteria andLabNameIn(List<String> values) {
            addCriterion("lab_name in", values, "labName");
            return (Criteria) this;
        }

        public Criteria andLabNameNotIn(List<String> values) {
            addCriterion("lab_name not in", values, "labName");
            return (Criteria) this;
        }

        public Criteria andLabNameBetween(String value1, String value2) {
            addCriterion("lab_name between", value1, value2, "labName");
            return (Criteria) this;
        }

        public Criteria andLabNameNotBetween(String value1, String value2) {
            addCriterion("lab_name not between", value1, value2, "labName");
            return (Criteria) this;
        }

        public Criteria andLabColorIsNull() {
            addCriterion("lab_color is null");
            return (Criteria) this;
        }

        public Criteria andLabColorIsNotNull() {
            addCriterion("lab_color is not null");
            return (Criteria) this;
        }

        public Criteria andLabColorEqualTo(String value) {
            addCriterion("lab_color =", value, "labColor");
            return (Criteria) this;
        }

        public Criteria andLabColorNotEqualTo(String value) {
            addCriterion("lab_color <>", value, "labColor");
            return (Criteria) this;
        }

        public Criteria andLabColorGreaterThan(String value) {
            addCriterion("lab_color >", value, "labColor");
            return (Criteria) this;
        }

        public Criteria andLabColorGreaterThanOrEqualTo(String value) {
            addCriterion("lab_color >=", value, "labColor");
            return (Criteria) this;
        }

        public Criteria andLabColorLessThan(String value) {
            addCriterion("lab_color <", value, "labColor");
            return (Criteria) this;
        }

        public Criteria andLabColorLessThanOrEqualTo(String value) {
            addCriterion("lab_color <=", value, "labColor");
            return (Criteria) this;
        }

        public Criteria andLabColorLike(String value) {
            addCriterion("lab_color like", value, "labColor");
            return (Criteria) this;
        }

        public Criteria andLabColorNotLike(String value) {
            addCriterion("lab_color not like", value, "labColor");
            return (Criteria) this;
        }

        public Criteria andLabColorIn(List<String> values) {
            addCriterion("lab_color in", values, "labColor");
            return (Criteria) this;
        }

        public Criteria andLabColorNotIn(List<String> values) {
            addCriterion("lab_color not in", values, "labColor");
            return (Criteria) this;
        }

        public Criteria andLabColorBetween(String value1, String value2) {
            addCriterion("lab_color between", value1, value2, "labColor");
            return (Criteria) this;
        }

        public Criteria andLabColorNotBetween(String value1, String value2) {
            addCriterion("lab_color not between", value1, value2, "labColor");
            return (Criteria) this;
        }

        public Criteria andShowOrderIsNull() {
            addCriterion("show_order is null");
            return (Criteria) this;
        }

        public Criteria andShowOrderIsNotNull() {
            addCriterion("show_order is not null");
            return (Criteria) this;
        }

        public Criteria andShowOrderEqualTo(Integer value) {
            addCriterion("show_order =", value, "showOrder");
            return (Criteria) this;
        }

        public Criteria andShowOrderNotEqualTo(Integer value) {
            addCriterion("show_order <>", value, "showOrder");
            return (Criteria) this;
        }

        public Criteria andShowOrderGreaterThan(Integer value) {
            addCriterion("show_order >", value, "showOrder");
            return (Criteria) this;
        }

        public Criteria andShowOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("show_order >=", value, "showOrder");
            return (Criteria) this;
        }

        public Criteria andShowOrderLessThan(Integer value) {
            addCriterion("show_order <", value, "showOrder");
            return (Criteria) this;
        }

        public Criteria andShowOrderLessThanOrEqualTo(Integer value) {
            addCriterion("show_order <=", value, "showOrder");
            return (Criteria) this;
        }

        public Criteria andShowOrderIn(List<Integer> values) {
            addCriterion("show_order in", values, "showOrder");
            return (Criteria) this;
        }

        public Criteria andShowOrderNotIn(List<Integer> values) {
            addCriterion("show_order not in", values, "showOrder");
            return (Criteria) this;
        }

        public Criteria andShowOrderBetween(Integer value1, Integer value2) {
            addCriterion("show_order between", value1, value2, "showOrder");
            return (Criteria) this;
        }

        public Criteria andShowOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("show_order not between", value1, value2, "showOrder");
            return (Criteria) this;
        }

        public Criteria andIfEditIsNull() {
            addCriterion("if_edit is null");
            return (Criteria) this;
        }

        public Criteria andIfEditIsNotNull() {
            addCriterion("if_edit is not null");
            return (Criteria) this;
        }

        public Criteria andIfEditEqualTo(String value) {
            addCriterion("if_edit =", value, "ifEdit");
            return (Criteria) this;
        }

        public Criteria andIfEditNotEqualTo(String value) {
            addCriterion("if_edit <>", value, "ifEdit");
            return (Criteria) this;
        }

        public Criteria andIfEditGreaterThan(String value) {
            addCriterion("if_edit >", value, "ifEdit");
            return (Criteria) this;
        }

        public Criteria andIfEditGreaterThanOrEqualTo(String value) {
            addCriterion("if_edit >=", value, "ifEdit");
            return (Criteria) this;
        }

        public Criteria andIfEditLessThan(String value) {
            addCriterion("if_edit <", value, "ifEdit");
            return (Criteria) this;
        }

        public Criteria andIfEditLessThanOrEqualTo(String value) {
            addCriterion("if_edit <=", value, "ifEdit");
            return (Criteria) this;
        }

        public Criteria andIfEditLike(String value) {
            addCriterion("if_edit like", value, "ifEdit");
            return (Criteria) this;
        }

        public Criteria andIfEditNotLike(String value) {
            addCriterion("if_edit not like", value, "ifEdit");
            return (Criteria) this;
        }

        public Criteria andIfEditIn(List<String> values) {
            addCriterion("if_edit in", values, "ifEdit");
            return (Criteria) this;
        }

        public Criteria andIfEditNotIn(List<String> values) {
            addCriterion("if_edit not in", values, "ifEdit");
            return (Criteria) this;
        }

        public Criteria andIfEditBetween(String value1, String value2) {
            addCriterion("if_edit between", value1, value2, "ifEdit");
            return (Criteria) this;
        }

        public Criteria andIfEditNotBetween(String value1, String value2) {
            addCriterion("if_edit not between", value1, value2, "ifEdit");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("create_user like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("create_user not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNull() {
            addCriterion("update_user is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNotNull() {
            addCriterion("update_user is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserEqualTo(String value) {
            addCriterion("update_user =", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotEqualTo(String value) {
            addCriterion("update_user <>", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThan(String value) {
            addCriterion("update_user >", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThanOrEqualTo(String value) {
            addCriterion("update_user >=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThan(String value) {
            addCriterion("update_user <", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThanOrEqualTo(String value) {
            addCriterion("update_user <=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLike(String value) {
            addCriterion("update_user like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotLike(String value) {
            addCriterion("update_user not like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIn(List<String> values) {
            addCriterion("update_user in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotIn(List<String> values) {
            addCriterion("update_user not in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserBetween(String value1, String value2) {
            addCriterion("update_user between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotBetween(String value1, String value2) {
            addCriterion("update_user not between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_gds_label
     *
     * @mbg.generated do_not_delete_during_merge Tue Apr 18 14:57:17 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_gds_label
     *
     * @mbg.generated Tue Apr 18 14:57:17 CST 2017
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