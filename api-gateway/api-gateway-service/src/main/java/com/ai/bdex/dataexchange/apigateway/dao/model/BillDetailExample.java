package com.ai.bdex.dataexchange.apigateway.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BillDetailExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_bill_detail
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_bill_detail
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_bill_detail
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bill_detail
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public BillDetailExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bill_detail
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bill_detail
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bill_detail
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bill_detail
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bill_detail
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bill_detail
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bill_detail
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bill_detail
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
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
     * This method corresponds to the database table t_bill_detail
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bill_detail
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_bill_detail
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andBillIdIsNull() {
            addCriterion("bill_id is null");
            return (Criteria) this;
        }

        public Criteria andBillIdIsNotNull() {
            addCriterion("bill_id is not null");
            return (Criteria) this;
        }

        public Criteria andBillIdEqualTo(String value) {
            addCriterion("bill_id =", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdNotEqualTo(String value) {
            addCriterion("bill_id <>", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdGreaterThan(String value) {
            addCriterion("bill_id >", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdGreaterThanOrEqualTo(String value) {
            addCriterion("bill_id >=", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdLessThan(String value) {
            addCriterion("bill_id <", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdLessThanOrEqualTo(String value) {
            addCriterion("bill_id <=", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdLike(String value) {
            addCriterion("bill_id like", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdNotLike(String value) {
            addCriterion("bill_id not like", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdIn(List<String> values) {
            addCriterion("bill_id in", values, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdNotIn(List<String> values) {
            addCriterion("bill_id not in", values, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdBetween(String value1, String value2) {
            addCriterion("bill_id between", value1, value2, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdNotBetween(String value1, String value2) {
            addCriterion("bill_id not between", value1, value2, "billId");
            return (Criteria) this;
        }

        public Criteria andInvokeSeqIsNull() {
            addCriterion("invoke_seq is null");
            return (Criteria) this;
        }

        public Criteria andInvokeSeqIsNotNull() {
            addCriterion("invoke_seq is not null");
            return (Criteria) this;
        }

        public Criteria andInvokeSeqEqualTo(String value) {
            addCriterion("invoke_seq =", value, "invokeSeq");
            return (Criteria) this;
        }

        public Criteria andInvokeSeqNotEqualTo(String value) {
            addCriterion("invoke_seq <>", value, "invokeSeq");
            return (Criteria) this;
        }

        public Criteria andInvokeSeqGreaterThan(String value) {
            addCriterion("invoke_seq >", value, "invokeSeq");
            return (Criteria) this;
        }

        public Criteria andInvokeSeqGreaterThanOrEqualTo(String value) {
            addCriterion("invoke_seq >=", value, "invokeSeq");
            return (Criteria) this;
        }

        public Criteria andInvokeSeqLessThan(String value) {
            addCriterion("invoke_seq <", value, "invokeSeq");
            return (Criteria) this;
        }

        public Criteria andInvokeSeqLessThanOrEqualTo(String value) {
            addCriterion("invoke_seq <=", value, "invokeSeq");
            return (Criteria) this;
        }

        public Criteria andInvokeSeqLike(String value) {
            addCriterion("invoke_seq like", value, "invokeSeq");
            return (Criteria) this;
        }

        public Criteria andInvokeSeqNotLike(String value) {
            addCriterion("invoke_seq not like", value, "invokeSeq");
            return (Criteria) this;
        }

        public Criteria andInvokeSeqIn(List<String> values) {
            addCriterion("invoke_seq in", values, "invokeSeq");
            return (Criteria) this;
        }

        public Criteria andInvokeSeqNotIn(List<String> values) {
            addCriterion("invoke_seq not in", values, "invokeSeq");
            return (Criteria) this;
        }

        public Criteria andInvokeSeqBetween(String value1, String value2) {
            addCriterion("invoke_seq between", value1, value2, "invokeSeq");
            return (Criteria) this;
        }

        public Criteria andInvokeSeqNotBetween(String value1, String value2) {
            addCriterion("invoke_seq not between", value1, value2, "invokeSeq");
            return (Criteria) this;
        }

        public Criteria andRealServiceIdIsNull() {
            addCriterion("real_service_id is null");
            return (Criteria) this;
        }

        public Criteria andRealServiceIdIsNotNull() {
            addCriterion("real_service_id is not null");
            return (Criteria) this;
        }

        public Criteria andRealServiceIdEqualTo(String value) {
            addCriterion("real_service_id =", value, "realServiceId");
            return (Criteria) this;
        }

        public Criteria andRealServiceIdNotEqualTo(String value) {
            addCriterion("real_service_id <>", value, "realServiceId");
            return (Criteria) this;
        }

        public Criteria andRealServiceIdGreaterThan(String value) {
            addCriterion("real_service_id >", value, "realServiceId");
            return (Criteria) this;
        }

        public Criteria andRealServiceIdGreaterThanOrEqualTo(String value) {
            addCriterion("real_service_id >=", value, "realServiceId");
            return (Criteria) this;
        }

        public Criteria andRealServiceIdLessThan(String value) {
            addCriterion("real_service_id <", value, "realServiceId");
            return (Criteria) this;
        }

        public Criteria andRealServiceIdLessThanOrEqualTo(String value) {
            addCriterion("real_service_id <=", value, "realServiceId");
            return (Criteria) this;
        }

        public Criteria andRealServiceIdLike(String value) {
            addCriterion("real_service_id like", value, "realServiceId");
            return (Criteria) this;
        }

        public Criteria andRealServiceIdNotLike(String value) {
            addCriterion("real_service_id not like", value, "realServiceId");
            return (Criteria) this;
        }

        public Criteria andRealServiceIdIn(List<String> values) {
            addCriterion("real_service_id in", values, "realServiceId");
            return (Criteria) this;
        }

        public Criteria andRealServiceIdNotIn(List<String> values) {
            addCriterion("real_service_id not in", values, "realServiceId");
            return (Criteria) this;
        }

        public Criteria andRealServiceIdBetween(String value1, String value2) {
            addCriterion("real_service_id between", value1, value2, "realServiceId");
            return (Criteria) this;
        }

        public Criteria andRealServiceIdNotBetween(String value1, String value2) {
            addCriterion("real_service_id not between", value1, value2, "realServiceId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andDataAcctIdIsNull() {
            addCriterion("data_acct_id is null");
            return (Criteria) this;
        }

        public Criteria andDataAcctIdIsNotNull() {
            addCriterion("data_acct_id is not null");
            return (Criteria) this;
        }

        public Criteria andDataAcctIdEqualTo(Long value) {
            addCriterion("data_acct_id =", value, "dataAcctId");
            return (Criteria) this;
        }

        public Criteria andDataAcctIdNotEqualTo(Long value) {
            addCriterion("data_acct_id <>", value, "dataAcctId");
            return (Criteria) this;
        }

        public Criteria andDataAcctIdGreaterThan(Long value) {
            addCriterion("data_acct_id >", value, "dataAcctId");
            return (Criteria) this;
        }

        public Criteria andDataAcctIdGreaterThanOrEqualTo(Long value) {
            addCriterion("data_acct_id >=", value, "dataAcctId");
            return (Criteria) this;
        }

        public Criteria andDataAcctIdLessThan(Long value) {
            addCriterion("data_acct_id <", value, "dataAcctId");
            return (Criteria) this;
        }

        public Criteria andDataAcctIdLessThanOrEqualTo(Long value) {
            addCriterion("data_acct_id <=", value, "dataAcctId");
            return (Criteria) this;
        }

        public Criteria andDataAcctIdIn(List<Long> values) {
            addCriterion("data_acct_id in", values, "dataAcctId");
            return (Criteria) this;
        }

        public Criteria andDataAcctIdNotIn(List<Long> values) {
            addCriterion("data_acct_id not in", values, "dataAcctId");
            return (Criteria) this;
        }

        public Criteria andDataAcctIdBetween(Long value1, Long value2) {
            addCriterion("data_acct_id between", value1, value2, "dataAcctId");
            return (Criteria) this;
        }

        public Criteria andDataAcctIdNotBetween(Long value1, Long value2) {
            addCriterion("data_acct_id not between", value1, value2, "dataAcctId");
            return (Criteria) this;
        }

        public Criteria andDataAcctTypeIsNull() {
            addCriterion("data_acct_type is null");
            return (Criteria) this;
        }

        public Criteria andDataAcctTypeIsNotNull() {
            addCriterion("data_acct_type is not null");
            return (Criteria) this;
        }

        public Criteria andDataAcctTypeEqualTo(String value) {
            addCriterion("data_acct_type =", value, "dataAcctType");
            return (Criteria) this;
        }

        public Criteria andDataAcctTypeNotEqualTo(String value) {
            addCriterion("data_acct_type <>", value, "dataAcctType");
            return (Criteria) this;
        }

        public Criteria andDataAcctTypeGreaterThan(String value) {
            addCriterion("data_acct_type >", value, "dataAcctType");
            return (Criteria) this;
        }

        public Criteria andDataAcctTypeGreaterThanOrEqualTo(String value) {
            addCriterion("data_acct_type >=", value, "dataAcctType");
            return (Criteria) this;
        }

        public Criteria andDataAcctTypeLessThan(String value) {
            addCriterion("data_acct_type <", value, "dataAcctType");
            return (Criteria) this;
        }

        public Criteria andDataAcctTypeLessThanOrEqualTo(String value) {
            addCriterion("data_acct_type <=", value, "dataAcctType");
            return (Criteria) this;
        }

        public Criteria andDataAcctTypeLike(String value) {
            addCriterion("data_acct_type like", value, "dataAcctType");
            return (Criteria) this;
        }

        public Criteria andDataAcctTypeNotLike(String value) {
            addCriterion("data_acct_type not like", value, "dataAcctType");
            return (Criteria) this;
        }

        public Criteria andDataAcctTypeIn(List<String> values) {
            addCriterion("data_acct_type in", values, "dataAcctType");
            return (Criteria) this;
        }

        public Criteria andDataAcctTypeNotIn(List<String> values) {
            addCriterion("data_acct_type not in", values, "dataAcctType");
            return (Criteria) this;
        }

        public Criteria andDataAcctTypeBetween(String value1, String value2) {
            addCriterion("data_acct_type between", value1, value2, "dataAcctType");
            return (Criteria) this;
        }

        public Criteria andDataAcctTypeNotBetween(String value1, String value2) {
            addCriterion("data_acct_type not between", value1, value2, "dataAcctType");
            return (Criteria) this;
        }

        public Criteria andConsumeNumIsNull() {
            addCriterion("consume_num is null");
            return (Criteria) this;
        }

        public Criteria andConsumeNumIsNotNull() {
            addCriterion("consume_num is not null");
            return (Criteria) this;
        }

        public Criteria andConsumeNumEqualTo(Integer value) {
            addCriterion("consume_num =", value, "consumeNum");
            return (Criteria) this;
        }

        public Criteria andConsumeNumNotEqualTo(Integer value) {
            addCriterion("consume_num <>", value, "consumeNum");
            return (Criteria) this;
        }

        public Criteria andConsumeNumGreaterThan(Integer value) {
            addCriterion("consume_num >", value, "consumeNum");
            return (Criteria) this;
        }

        public Criteria andConsumeNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("consume_num >=", value, "consumeNum");
            return (Criteria) this;
        }

        public Criteria andConsumeNumLessThan(Integer value) {
            addCriterion("consume_num <", value, "consumeNum");
            return (Criteria) this;
        }

        public Criteria andConsumeNumLessThanOrEqualTo(Integer value) {
            addCriterion("consume_num <=", value, "consumeNum");
            return (Criteria) this;
        }

        public Criteria andConsumeNumIn(List<Integer> values) {
            addCriterion("consume_num in", values, "consumeNum");
            return (Criteria) this;
        }

        public Criteria andConsumeNumNotIn(List<Integer> values) {
            addCriterion("consume_num not in", values, "consumeNum");
            return (Criteria) this;
        }

        public Criteria andConsumeNumBetween(Integer value1, Integer value2) {
            addCriterion("consume_num between", value1, value2, "consumeNum");
            return (Criteria) this;
        }

        public Criteria andConsumeNumNotBetween(Integer value1, Integer value2) {
            addCriterion("consume_num not between", value1, value2, "consumeNum");
            return (Criteria) this;
        }

        public Criteria andConsumeMoneyIsNull() {
            addCriterion("consume_money is null");
            return (Criteria) this;
        }

        public Criteria andConsumeMoneyIsNotNull() {
            addCriterion("consume_money is not null");
            return (Criteria) this;
        }

        public Criteria andConsumeMoneyEqualTo(Integer value) {
            addCriterion("consume_money =", value, "consumeMoney");
            return (Criteria) this;
        }

        public Criteria andConsumeMoneyNotEqualTo(Integer value) {
            addCriterion("consume_money <>", value, "consumeMoney");
            return (Criteria) this;
        }

        public Criteria andConsumeMoneyGreaterThan(Integer value) {
            addCriterion("consume_money >", value, "consumeMoney");
            return (Criteria) this;
        }

        public Criteria andConsumeMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("consume_money >=", value, "consumeMoney");
            return (Criteria) this;
        }

        public Criteria andConsumeMoneyLessThan(Integer value) {
            addCriterion("consume_money <", value, "consumeMoney");
            return (Criteria) this;
        }

        public Criteria andConsumeMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("consume_money <=", value, "consumeMoney");
            return (Criteria) this;
        }

        public Criteria andConsumeMoneyIn(List<Integer> values) {
            addCriterion("consume_money in", values, "consumeMoney");
            return (Criteria) this;
        }

        public Criteria andConsumeMoneyNotIn(List<Integer> values) {
            addCriterion("consume_money not in", values, "consumeMoney");
            return (Criteria) this;
        }

        public Criteria andConsumeMoneyBetween(Integer value1, Integer value2) {
            addCriterion("consume_money between", value1, value2, "consumeMoney");
            return (Criteria) this;
        }

        public Criteria andConsumeMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("consume_money not between", value1, value2, "consumeMoney");
            return (Criteria) this;
        }

        public Criteria andConsumeTimeIsNull() {
            addCriterion("consume_time is null");
            return (Criteria) this;
        }

        public Criteria andConsumeTimeIsNotNull() {
            addCriterion("consume_time is not null");
            return (Criteria) this;
        }

        public Criteria andConsumeTimeEqualTo(Date value) {
            addCriterionForJDBCDate("consume_time =", value, "consumeTime");
            return (Criteria) this;
        }

        public Criteria andConsumeTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("consume_time <>", value, "consumeTime");
            return (Criteria) this;
        }

        public Criteria andConsumeTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("consume_time >", value, "consumeTime");
            return (Criteria) this;
        }

        public Criteria andConsumeTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("consume_time >=", value, "consumeTime");
            return (Criteria) this;
        }

        public Criteria andConsumeTimeLessThan(Date value) {
            addCriterionForJDBCDate("consume_time <", value, "consumeTime");
            return (Criteria) this;
        }

        public Criteria andConsumeTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("consume_time <=", value, "consumeTime");
            return (Criteria) this;
        }

        public Criteria andConsumeTimeIn(List<Date> values) {
            addCriterionForJDBCDate("consume_time in", values, "consumeTime");
            return (Criteria) this;
        }

        public Criteria andConsumeTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("consume_time not in", values, "consumeTime");
            return (Criteria) this;
        }

        public Criteria andConsumeTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("consume_time between", value1, value2, "consumeTime");
            return (Criteria) this;
        }

        public Criteria andConsumeTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("consume_time not between", value1, value2, "consumeTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_bill_detail
     *
     * @mbg.generated do_not_delete_during_merge Fri May 05 16:13:25 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_bill_detail
     *
     * @mbg.generated Fri May 05 16:13:25 CST 2017
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