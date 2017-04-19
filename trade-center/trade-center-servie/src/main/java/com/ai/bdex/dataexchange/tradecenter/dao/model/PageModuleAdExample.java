package com.ai.bdex.dataexchange.tradecenter.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PageModuleAdExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_page_module_ad
     *
     * @mbg.generated Tue Apr 18 20:43:09 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_page_module_ad
     *
     * @mbg.generated Tue Apr 18 20:43:09 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_page_module_ad
     *
     * @mbg.generated Tue Apr 18 20:43:09 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_module_ad
     *
     * @mbg.generated Tue Apr 18 20:43:09 CST 2017
     */
    public PageModuleAdExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_module_ad
     *
     * @mbg.generated Tue Apr 18 20:43:09 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_module_ad
     *
     * @mbg.generated Tue Apr 18 20:43:09 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_module_ad
     *
     * @mbg.generated Tue Apr 18 20:43:09 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_module_ad
     *
     * @mbg.generated Tue Apr 18 20:43:09 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_module_ad
     *
     * @mbg.generated Tue Apr 18 20:43:09 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_module_ad
     *
     * @mbg.generated Tue Apr 18 20:43:09 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_module_ad
     *
     * @mbg.generated Tue Apr 18 20:43:09 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_module_ad
     *
     * @mbg.generated Tue Apr 18 20:43:09 CST 2017
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
     * This method corresponds to the database table t_page_module_ad
     *
     * @mbg.generated Tue Apr 18 20:43:09 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_module_ad
     *
     * @mbg.generated Tue Apr 18 20:43:09 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_page_module_ad
     *
     * @mbg.generated Tue Apr 18 20:43:09 CST 2017
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

        public Criteria andAdIdIsNull() {
            addCriterion("AD_ID is null");
            return (Criteria) this;
        }

        public Criteria andAdIdIsNotNull() {
            addCriterion("AD_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAdIdEqualTo(Integer value) {
            addCriterion("AD_ID =", value, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdNotEqualTo(Integer value) {
            addCriterion("AD_ID <>", value, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdGreaterThan(Integer value) {
            addCriterion("AD_ID >", value, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("AD_ID >=", value, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdLessThan(Integer value) {
            addCriterion("AD_ID <", value, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdLessThanOrEqualTo(Integer value) {
            addCriterion("AD_ID <=", value, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdIn(List<Integer> values) {
            addCriterion("AD_ID in", values, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdNotIn(List<Integer> values) {
            addCriterion("AD_ID not in", values, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdBetween(Integer value1, Integer value2) {
            addCriterion("AD_ID between", value1, value2, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdNotBetween(Integer value1, Integer value2) {
            addCriterion("AD_ID not between", value1, value2, "adId");
            return (Criteria) this;
        }

        public Criteria andModuleIdIsNull() {
            addCriterion("MODULE_ID is null");
            return (Criteria) this;
        }

        public Criteria andModuleIdIsNotNull() {
            addCriterion("MODULE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andModuleIdEqualTo(Integer value) {
            addCriterion("MODULE_ID =", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotEqualTo(Integer value) {
            addCriterion("MODULE_ID <>", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdGreaterThan(Integer value) {
            addCriterion("MODULE_ID >", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("MODULE_ID >=", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdLessThan(Integer value) {
            addCriterion("MODULE_ID <", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdLessThanOrEqualTo(Integer value) {
            addCriterion("MODULE_ID <=", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdIn(List<Integer> values) {
            addCriterion("MODULE_ID in", values, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotIn(List<Integer> values) {
            addCriterion("MODULE_ID not in", values, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdBetween(Integer value1, Integer value2) {
            addCriterion("MODULE_ID between", value1, value2, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("MODULE_ID not between", value1, value2, "moduleId");
            return (Criteria) this;
        }

        public Criteria andAdTitleIsNull() {
            addCriterion("AD_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andAdTitleIsNotNull() {
            addCriterion("AD_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andAdTitleEqualTo(String value) {
            addCriterion("AD_TITLE =", value, "adTitle");
            return (Criteria) this;
        }

        public Criteria andAdTitleNotEqualTo(String value) {
            addCriterion("AD_TITLE <>", value, "adTitle");
            return (Criteria) this;
        }

        public Criteria andAdTitleGreaterThan(String value) {
            addCriterion("AD_TITLE >", value, "adTitle");
            return (Criteria) this;
        }

        public Criteria andAdTitleGreaterThanOrEqualTo(String value) {
            addCriterion("AD_TITLE >=", value, "adTitle");
            return (Criteria) this;
        }

        public Criteria andAdTitleLessThan(String value) {
            addCriterion("AD_TITLE <", value, "adTitle");
            return (Criteria) this;
        }

        public Criteria andAdTitleLessThanOrEqualTo(String value) {
            addCriterion("AD_TITLE <=", value, "adTitle");
            return (Criteria) this;
        }

        public Criteria andAdTitleLike(String value) {
            addCriterion("AD_TITLE like", value, "adTitle");
            return (Criteria) this;
        }

        public Criteria andAdTitleNotLike(String value) {
            addCriterion("AD_TITLE not like", value, "adTitle");
            return (Criteria) this;
        }

        public Criteria andAdTitleIn(List<String> values) {
            addCriterion("AD_TITLE in", values, "adTitle");
            return (Criteria) this;
        }

        public Criteria andAdTitleNotIn(List<String> values) {
            addCriterion("AD_TITLE not in", values, "adTitle");
            return (Criteria) this;
        }

        public Criteria andAdTitleBetween(String value1, String value2) {
            addCriterion("AD_TITLE between", value1, value2, "adTitle");
            return (Criteria) this;
        }

        public Criteria andAdTitleNotBetween(String value1, String value2) {
            addCriterion("AD_TITLE not between", value1, value2, "adTitle");
            return (Criteria) this;
        }

        public Criteria andLinkPageIsNull() {
            addCriterion("LINK_PAGE is null");
            return (Criteria) this;
        }

        public Criteria andLinkPageIsNotNull() {
            addCriterion("LINK_PAGE is not null");
            return (Criteria) this;
        }

        public Criteria andLinkPageEqualTo(String value) {
            addCriterion("LINK_PAGE =", value, "linkPage");
            return (Criteria) this;
        }

        public Criteria andLinkPageNotEqualTo(String value) {
            addCriterion("LINK_PAGE <>", value, "linkPage");
            return (Criteria) this;
        }

        public Criteria andLinkPageGreaterThan(String value) {
            addCriterion("LINK_PAGE >", value, "linkPage");
            return (Criteria) this;
        }

        public Criteria andLinkPageGreaterThanOrEqualTo(String value) {
            addCriterion("LINK_PAGE >=", value, "linkPage");
            return (Criteria) this;
        }

        public Criteria andLinkPageLessThan(String value) {
            addCriterion("LINK_PAGE <", value, "linkPage");
            return (Criteria) this;
        }

        public Criteria andLinkPageLessThanOrEqualTo(String value) {
            addCriterion("LINK_PAGE <=", value, "linkPage");
            return (Criteria) this;
        }

        public Criteria andLinkPageLike(String value) {
            addCriterion("LINK_PAGE like", value, "linkPage");
            return (Criteria) this;
        }

        public Criteria andLinkPageNotLike(String value) {
            addCriterion("LINK_PAGE not like", value, "linkPage");
            return (Criteria) this;
        }

        public Criteria andLinkPageIn(List<String> values) {
            addCriterion("LINK_PAGE in", values, "linkPage");
            return (Criteria) this;
        }

        public Criteria andLinkPageNotIn(List<String> values) {
            addCriterion("LINK_PAGE not in", values, "linkPage");
            return (Criteria) this;
        }

        public Criteria andLinkPageBetween(String value1, String value2) {
            addCriterion("LINK_PAGE between", value1, value2, "linkPage");
            return (Criteria) this;
        }

        public Criteria andLinkPageNotBetween(String value1, String value2) {
            addCriterion("LINK_PAGE not between", value1, value2, "linkPage");
            return (Criteria) this;
        }

        public Criteria andBmpNameIsNull() {
            addCriterion("BMP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBmpNameIsNotNull() {
            addCriterion("BMP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBmpNameEqualTo(String value) {
            addCriterion("BMP_NAME =", value, "bmpName");
            return (Criteria) this;
        }

        public Criteria andBmpNameNotEqualTo(String value) {
            addCriterion("BMP_NAME <>", value, "bmpName");
            return (Criteria) this;
        }

        public Criteria andBmpNameGreaterThan(String value) {
            addCriterion("BMP_NAME >", value, "bmpName");
            return (Criteria) this;
        }

        public Criteria andBmpNameGreaterThanOrEqualTo(String value) {
            addCriterion("BMP_NAME >=", value, "bmpName");
            return (Criteria) this;
        }

        public Criteria andBmpNameLessThan(String value) {
            addCriterion("BMP_NAME <", value, "bmpName");
            return (Criteria) this;
        }

        public Criteria andBmpNameLessThanOrEqualTo(String value) {
            addCriterion("BMP_NAME <=", value, "bmpName");
            return (Criteria) this;
        }

        public Criteria andBmpNameLike(String value) {
            addCriterion("BMP_NAME like", value, "bmpName");
            return (Criteria) this;
        }

        public Criteria andBmpNameNotLike(String value) {
            addCriterion("BMP_NAME not like", value, "bmpName");
            return (Criteria) this;
        }

        public Criteria andBmpNameIn(List<String> values) {
            addCriterion("BMP_NAME in", values, "bmpName");
            return (Criteria) this;
        }

        public Criteria andBmpNameNotIn(List<String> values) {
            addCriterion("BMP_NAME not in", values, "bmpName");
            return (Criteria) this;
        }

        public Criteria andBmpNameBetween(String value1, String value2) {
            addCriterion("BMP_NAME between", value1, value2, "bmpName");
            return (Criteria) this;
        }

        public Criteria andBmpNameNotBetween(String value1, String value2) {
            addCriterion("BMP_NAME not between", value1, value2, "bmpName");
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

        public Criteria andAdOrderIsNull() {
            addCriterion("AD_ORDER is null");
            return (Criteria) this;
        }

        public Criteria andAdOrderIsNotNull() {
            addCriterion("AD_ORDER is not null");
            return (Criteria) this;
        }

        public Criteria andAdOrderEqualTo(Integer value) {
            addCriterion("AD_ORDER =", value, "adOrder");
            return (Criteria) this;
        }

        public Criteria andAdOrderNotEqualTo(Integer value) {
            addCriterion("AD_ORDER <>", value, "adOrder");
            return (Criteria) this;
        }

        public Criteria andAdOrderGreaterThan(Integer value) {
            addCriterion("AD_ORDER >", value, "adOrder");
            return (Criteria) this;
        }

        public Criteria andAdOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("AD_ORDER >=", value, "adOrder");
            return (Criteria) this;
        }

        public Criteria andAdOrderLessThan(Integer value) {
            addCriterion("AD_ORDER <", value, "adOrder");
            return (Criteria) this;
        }

        public Criteria andAdOrderLessThanOrEqualTo(Integer value) {
            addCriterion("AD_ORDER <=", value, "adOrder");
            return (Criteria) this;
        }

        public Criteria andAdOrderIn(List<Integer> values) {
            addCriterion("AD_ORDER in", values, "adOrder");
            return (Criteria) this;
        }

        public Criteria andAdOrderNotIn(List<Integer> values) {
            addCriterion("AD_ORDER not in", values, "adOrder");
            return (Criteria) this;
        }

        public Criteria andAdOrderBetween(Integer value1, Integer value2) {
            addCriterion("AD_ORDER between", value1, value2, "adOrder");
            return (Criteria) this;
        }

        public Criteria andAdOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("AD_ORDER not between", value1, value2, "adOrder");
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

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
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
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_page_module_ad
     *
     * @mbg.generated do_not_delete_during_merge Tue Apr 18 20:43:09 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_page_module_ad
     *
     * @mbg.generated Tue Apr 18 20:43:09 CST 2017
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