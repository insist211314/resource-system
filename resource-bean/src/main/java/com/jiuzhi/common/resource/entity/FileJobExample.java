package com.jiuzhi.common.resource.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileJobExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FileJobExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIsNull() {
            addCriterion("business_type is null");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIsNotNull() {
            addCriterion("business_type is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeEqualTo(String value) {
            addCriterion("business_type =", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeNotEqualTo(String value) {
            addCriterion("business_type <>", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeGreaterThan(String value) {
            addCriterion("business_type >", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeGreaterThanOrEqualTo(String value) {
            addCriterion("business_type >=", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeLessThan(String value) {
            addCriterion("business_type <", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeLessThanOrEqualTo(String value) {
            addCriterion("business_type <=", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeLike(String value) {
            addCriterion("business_type like", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeNotLike(String value) {
            addCriterion("business_type not like", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIn(List<String> values) {
            addCriterion("business_type in", values, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeNotIn(List<String> values) {
            addCriterion("business_type not in", values, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeBetween(String value1, String value2) {
            addCriterion("business_type between", value1, value2, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeNotBetween(String value1, String value2) {
            addCriterion("business_type not between", value1, value2, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessIdIsNull() {
            addCriterion("business_id is null");
            return (Criteria) this;
        }

        public Criteria andBusinessIdIsNotNull() {
            addCriterion("business_id is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessIdEqualTo(String value) {
            addCriterion("business_id =", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotEqualTo(String value) {
            addCriterion("business_id <>", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdGreaterThan(String value) {
            addCriterion("business_id >", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdGreaterThanOrEqualTo(String value) {
            addCriterion("business_id >=", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdLessThan(String value) {
            addCriterion("business_id <", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdLessThanOrEqualTo(String value) {
            addCriterion("business_id <=", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdLike(String value) {
            addCriterion("business_id like", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotLike(String value) {
            addCriterion("business_id not like", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdIn(List<String> values) {
            addCriterion("business_id in", values, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotIn(List<String> values) {
            addCriterion("business_id not in", values, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdBetween(String value1, String value2) {
            addCriterion("business_id between", value1, value2, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotBetween(String value1, String value2) {
            addCriterion("business_id not between", value1, value2, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessAppIsNull() {
            addCriterion("business_app is null");
            return (Criteria) this;
        }

        public Criteria andBusinessAppIsNotNull() {
            addCriterion("business_app is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessAppEqualTo(String value) {
            addCriterion("business_app =", value, "businessApp");
            return (Criteria) this;
        }

        public Criteria andBusinessAppNotEqualTo(String value) {
            addCriterion("business_app <>", value, "businessApp");
            return (Criteria) this;
        }

        public Criteria andBusinessAppGreaterThan(String value) {
            addCriterion("business_app >", value, "businessApp");
            return (Criteria) this;
        }

        public Criteria andBusinessAppGreaterThanOrEqualTo(String value) {
            addCriterion("business_app >=", value, "businessApp");
            return (Criteria) this;
        }

        public Criteria andBusinessAppLessThan(String value) {
            addCriterion("business_app <", value, "businessApp");
            return (Criteria) this;
        }

        public Criteria andBusinessAppLessThanOrEqualTo(String value) {
            addCriterion("business_app <=", value, "businessApp");
            return (Criteria) this;
        }

        public Criteria andBusinessAppLike(String value) {
            addCriterion("business_app like", value, "businessApp");
            return (Criteria) this;
        }

        public Criteria andBusinessAppNotLike(String value) {
            addCriterion("business_app not like", value, "businessApp");
            return (Criteria) this;
        }

        public Criteria andBusinessAppIn(List<String> values) {
            addCriterion("business_app in", values, "businessApp");
            return (Criteria) this;
        }

        public Criteria andBusinessAppNotIn(List<String> values) {
            addCriterion("business_app not in", values, "businessApp");
            return (Criteria) this;
        }

        public Criteria andBusinessAppBetween(String value1, String value2) {
            addCriterion("business_app between", value1, value2, "businessApp");
            return (Criteria) this;
        }

        public Criteria andBusinessAppNotBetween(String value1, String value2) {
            addCriterion("business_app not between", value1, value2, "businessApp");
            return (Criteria) this;
        }

        public Criteria andTaskCountIsNull() {
            addCriterion("task_count is null");
            return (Criteria) this;
        }

        public Criteria andTaskCountIsNotNull() {
            addCriterion("task_count is not null");
            return (Criteria) this;
        }

        public Criteria andTaskCountEqualTo(Integer value) {
            addCriterion("task_count =", value, "taskCount");
            return (Criteria) this;
        }

        public Criteria andTaskCountNotEqualTo(Integer value) {
            addCriterion("task_count <>", value, "taskCount");
            return (Criteria) this;
        }

        public Criteria andTaskCountGreaterThan(Integer value) {
            addCriterion("task_count >", value, "taskCount");
            return (Criteria) this;
        }

        public Criteria andTaskCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_count >=", value, "taskCount");
            return (Criteria) this;
        }

        public Criteria andTaskCountLessThan(Integer value) {
            addCriterion("task_count <", value, "taskCount");
            return (Criteria) this;
        }

        public Criteria andTaskCountLessThanOrEqualTo(Integer value) {
            addCriterion("task_count <=", value, "taskCount");
            return (Criteria) this;
        }

        public Criteria andTaskCountIn(List<Integer> values) {
            addCriterion("task_count in", values, "taskCount");
            return (Criteria) this;
        }

        public Criteria andTaskCountNotIn(List<Integer> values) {
            addCriterion("task_count not in", values, "taskCount");
            return (Criteria) this;
        }

        public Criteria andTaskCountBetween(Integer value1, Integer value2) {
            addCriterion("task_count between", value1, value2, "taskCount");
            return (Criteria) this;
        }

        public Criteria andTaskCountNotBetween(Integer value1, Integer value2) {
            addCriterion("task_count not between", value1, value2, "taskCount");
            return (Criteria) this;
        }

        public Criteria andTaskWaitCountIsNull() {
            addCriterion("task_wait_count is null");
            return (Criteria) this;
        }

        public Criteria andTaskWaitCountIsNotNull() {
            addCriterion("task_wait_count is not null");
            return (Criteria) this;
        }

        public Criteria andTaskWaitCountEqualTo(Integer value) {
            addCriterion("task_wait_count =", value, "taskWaitCount");
            return (Criteria) this;
        }

        public Criteria andTaskWaitCountNotEqualTo(Integer value) {
            addCriterion("task_wait_count <>", value, "taskWaitCount");
            return (Criteria) this;
        }

        public Criteria andTaskWaitCountGreaterThan(Integer value) {
            addCriterion("task_wait_count >", value, "taskWaitCount");
            return (Criteria) this;
        }

        public Criteria andTaskWaitCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_wait_count >=", value, "taskWaitCount");
            return (Criteria) this;
        }

        public Criteria andTaskWaitCountLessThan(Integer value) {
            addCriterion("task_wait_count <", value, "taskWaitCount");
            return (Criteria) this;
        }

        public Criteria andTaskWaitCountLessThanOrEqualTo(Integer value) {
            addCriterion("task_wait_count <=", value, "taskWaitCount");
            return (Criteria) this;
        }

        public Criteria andTaskWaitCountIn(List<Integer> values) {
            addCriterion("task_wait_count in", values, "taskWaitCount");
            return (Criteria) this;
        }

        public Criteria andTaskWaitCountNotIn(List<Integer> values) {
            addCriterion("task_wait_count not in", values, "taskWaitCount");
            return (Criteria) this;
        }

        public Criteria andTaskWaitCountBetween(Integer value1, Integer value2) {
            addCriterion("task_wait_count between", value1, value2, "taskWaitCount");
            return (Criteria) this;
        }

        public Criteria andTaskWaitCountNotBetween(Integer value1, Integer value2) {
            addCriterion("task_wait_count not between", value1, value2, "taskWaitCount");
            return (Criteria) this;
        }

        public Criteria andNotifyRetryCountIsNull() {
            addCriterion("notify_retry_count is null");
            return (Criteria) this;
        }

        public Criteria andNotifyRetryCountIsNotNull() {
            addCriterion("notify_retry_count is not null");
            return (Criteria) this;
        }

        public Criteria andNotifyRetryCountEqualTo(Integer value) {
            addCriterion("notify_retry_count =", value, "notifyRetryCount");
            return (Criteria) this;
        }

        public Criteria andNotifyRetryCountNotEqualTo(Integer value) {
            addCriterion("notify_retry_count <>", value, "notifyRetryCount");
            return (Criteria) this;
        }

        public Criteria andNotifyRetryCountGreaterThan(Integer value) {
            addCriterion("notify_retry_count >", value, "notifyRetryCount");
            return (Criteria) this;
        }

        public Criteria andNotifyRetryCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("notify_retry_count >=", value, "notifyRetryCount");
            return (Criteria) this;
        }

        public Criteria andNotifyRetryCountLessThan(Integer value) {
            addCriterion("notify_retry_count <", value, "notifyRetryCount");
            return (Criteria) this;
        }

        public Criteria andNotifyRetryCountLessThanOrEqualTo(Integer value) {
            addCriterion("notify_retry_count <=", value, "notifyRetryCount");
            return (Criteria) this;
        }

        public Criteria andNotifyRetryCountIn(List<Integer> values) {
            addCriterion("notify_retry_count in", values, "notifyRetryCount");
            return (Criteria) this;
        }

        public Criteria andNotifyRetryCountNotIn(List<Integer> values) {
            addCriterion("notify_retry_count not in", values, "notifyRetryCount");
            return (Criteria) this;
        }

        public Criteria andNotifyRetryCountBetween(Integer value1, Integer value2) {
            addCriterion("notify_retry_count between", value1, value2, "notifyRetryCount");
            return (Criteria) this;
        }

        public Criteria andNotifyRetryCountNotBetween(Integer value1, Integer value2) {
            addCriterion("notify_retry_count not between", value1, value2, "notifyRetryCount");
            return (Criteria) this;
        }

        public Criteria andNotifyStatusIsNull() {
            addCriterion("notify_status is null");
            return (Criteria) this;
        }

        public Criteria andNotifyStatusIsNotNull() {
            addCriterion("notify_status is not null");
            return (Criteria) this;
        }

        public Criteria andNotifyStatusEqualTo(Short value) {
            addCriterion("notify_status =", value, "notifyStatus");
            return (Criteria) this;
        }

        public Criteria andNotifyStatusNotEqualTo(Short value) {
            addCriterion("notify_status <>", value, "notifyStatus");
            return (Criteria) this;
        }

        public Criteria andNotifyStatusGreaterThan(Short value) {
            addCriterion("notify_status >", value, "notifyStatus");
            return (Criteria) this;
        }

        public Criteria andNotifyStatusGreaterThanOrEqualTo(Short value) {
            addCriterion("notify_status >=", value, "notifyStatus");
            return (Criteria) this;
        }

        public Criteria andNotifyStatusLessThan(Short value) {
            addCriterion("notify_status <", value, "notifyStatus");
            return (Criteria) this;
        }

        public Criteria andNotifyStatusLessThanOrEqualTo(Short value) {
            addCriterion("notify_status <=", value, "notifyStatus");
            return (Criteria) this;
        }

        public Criteria andNotifyStatusIn(List<Short> values) {
            addCriterion("notify_status in", values, "notifyStatus");
            return (Criteria) this;
        }

        public Criteria andNotifyStatusNotIn(List<Short> values) {
            addCriterion("notify_status not in", values, "notifyStatus");
            return (Criteria) this;
        }

        public Criteria andNotifyStatusBetween(Short value1, Short value2) {
            addCriterion("notify_status between", value1, value2, "notifyStatus");
            return (Criteria) this;
        }

        public Criteria andNotifyStatusNotBetween(Short value1, Short value2) {
            addCriterion("notify_status not between", value1, value2, "notifyStatus");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Boolean value) {
            addCriterion("is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Boolean value) {
            addCriterion("is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Boolean value) {
            addCriterion("is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Boolean value) {
            addCriterion("is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Boolean value) {
            addCriterion("is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Boolean> values) {
            addCriterion("is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Boolean> values) {
            addCriterion("is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete not between", value1, value2, "isDelete");
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

        public Criteria andFailProcessIdsIsNull() {
            addCriterion("fail_process_ids is null");
            return (Criteria) this;
        }

        public Criteria andFailProcessIdsIsNotNull() {
            addCriterion("fail_process_ids is not null");
            return (Criteria) this;
        }

        public Criteria andFailProcessIdsEqualTo(String value) {
            addCriterion("fail_process_ids =", value, "failProcessIds");
            return (Criteria) this;
        }

        public Criteria andFailProcessIdsNotEqualTo(String value) {
            addCriterion("fail_process_ids <>", value, "failProcessIds");
            return (Criteria) this;
        }

        public Criteria andFailProcessIdsGreaterThan(String value) {
            addCriterion("fail_process_ids >", value, "failProcessIds");
            return (Criteria) this;
        }

        public Criteria andFailProcessIdsGreaterThanOrEqualTo(String value) {
            addCriterion("fail_process_ids >=", value, "failProcessIds");
            return (Criteria) this;
        }

        public Criteria andFailProcessIdsLessThan(String value) {
            addCriterion("fail_process_ids <", value, "failProcessIds");
            return (Criteria) this;
        }

        public Criteria andFailProcessIdsLessThanOrEqualTo(String value) {
            addCriterion("fail_process_ids <=", value, "failProcessIds");
            return (Criteria) this;
        }

        public Criteria andFailProcessIdsLike(String value) {
            addCriterion("fail_process_ids like", value, "failProcessIds");
            return (Criteria) this;
        }

        public Criteria andFailProcessIdsNotLike(String value) {
            addCriterion("fail_process_ids not like", value, "failProcessIds");
            return (Criteria) this;
        }

        public Criteria andFailProcessIdsIn(List<String> values) {
            addCriterion("fail_process_ids in", values, "failProcessIds");
            return (Criteria) this;
        }

        public Criteria andFailProcessIdsNotIn(List<String> values) {
            addCriterion("fail_process_ids not in", values, "failProcessIds");
            return (Criteria) this;
        }

        public Criteria andFailProcessIdsBetween(String value1, String value2) {
            addCriterion("fail_process_ids between", value1, value2, "failProcessIds");
            return (Criteria) this;
        }

        public Criteria andFailProcessIdsNotBetween(String value1, String value2) {
            addCriterion("fail_process_ids not between", value1, value2, "failProcessIds");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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