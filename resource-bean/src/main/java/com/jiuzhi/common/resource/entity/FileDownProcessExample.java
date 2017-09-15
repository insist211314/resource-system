package com.jiuzhi.common.resource.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileDownProcessExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FileDownProcessExample() {
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

        public Criteria andDownUrlMd5IsNull() {
            addCriterion("down_url_md5 is null");
            return (Criteria) this;
        }

        public Criteria andDownUrlMd5IsNotNull() {
            addCriterion("down_url_md5 is not null");
            return (Criteria) this;
        }

        public Criteria andDownUrlMd5EqualTo(String value) {
            addCriterion("down_url_md5 =", value, "downUrlMd5");
            return (Criteria) this;
        }

        public Criteria andDownUrlMd5NotEqualTo(String value) {
            addCriterion("down_url_md5 <>", value, "downUrlMd5");
            return (Criteria) this;
        }

        public Criteria andDownUrlMd5GreaterThan(String value) {
            addCriterion("down_url_md5 >", value, "downUrlMd5");
            return (Criteria) this;
        }

        public Criteria andDownUrlMd5GreaterThanOrEqualTo(String value) {
            addCriterion("down_url_md5 >=", value, "downUrlMd5");
            return (Criteria) this;
        }

        public Criteria andDownUrlMd5LessThan(String value) {
            addCriterion("down_url_md5 <", value, "downUrlMd5");
            return (Criteria) this;
        }

        public Criteria andDownUrlMd5LessThanOrEqualTo(String value) {
            addCriterion("down_url_md5 <=", value, "downUrlMd5");
            return (Criteria) this;
        }

        public Criteria andDownUrlMd5Like(String value) {
            addCriterion("down_url_md5 like", value, "downUrlMd5");
            return (Criteria) this;
        }

        public Criteria andDownUrlMd5NotLike(String value) {
            addCriterion("down_url_md5 not like", value, "downUrlMd5");
            return (Criteria) this;
        }

        public Criteria andDownUrlMd5In(List<String> values) {
            addCriterion("down_url_md5 in", values, "downUrlMd5");
            return (Criteria) this;
        }

        public Criteria andDownUrlMd5NotIn(List<String> values) {
            addCriterion("down_url_md5 not in", values, "downUrlMd5");
            return (Criteria) this;
        }

        public Criteria andDownUrlMd5Between(String value1, String value2) {
            addCriterion("down_url_md5 between", value1, value2, "downUrlMd5");
            return (Criteria) this;
        }

        public Criteria andDownUrlMd5NotBetween(String value1, String value2) {
            addCriterion("down_url_md5 not between", value1, value2, "downUrlMd5");
            return (Criteria) this;
        }

        public Criteria andDownUrlIsNull() {
            addCriterion("down_url is null");
            return (Criteria) this;
        }

        public Criteria andDownUrlIsNotNull() {
            addCriterion("down_url is not null");
            return (Criteria) this;
        }

        public Criteria andDownUrlEqualTo(String value) {
            addCriterion("down_url =", value, "downUrl");
            return (Criteria) this;
        }

        public Criteria andDownUrlNotEqualTo(String value) {
            addCriterion("down_url <>", value, "downUrl");
            return (Criteria) this;
        }

        public Criteria andDownUrlGreaterThan(String value) {
            addCriterion("down_url >", value, "downUrl");
            return (Criteria) this;
        }

        public Criteria andDownUrlGreaterThanOrEqualTo(String value) {
            addCriterion("down_url >=", value, "downUrl");
            return (Criteria) this;
        }

        public Criteria andDownUrlLessThan(String value) {
            addCriterion("down_url <", value, "downUrl");
            return (Criteria) this;
        }

        public Criteria andDownUrlLessThanOrEqualTo(String value) {
            addCriterion("down_url <=", value, "downUrl");
            return (Criteria) this;
        }

        public Criteria andDownUrlLike(String value) {
            addCriterion("down_url like", value, "downUrl");
            return (Criteria) this;
        }

        public Criteria andDownUrlNotLike(String value) {
            addCriterion("down_url not like", value, "downUrl");
            return (Criteria) this;
        }

        public Criteria andDownUrlIn(List<String> values) {
            addCriterion("down_url in", values, "downUrl");
            return (Criteria) this;
        }

        public Criteria andDownUrlNotIn(List<String> values) {
            addCriterion("down_url not in", values, "downUrl");
            return (Criteria) this;
        }

        public Criteria andDownUrlBetween(String value1, String value2) {
            addCriterion("down_url between", value1, value2, "downUrl");
            return (Criteria) this;
        }

        public Criteria andDownUrlNotBetween(String value1, String value2) {
            addCriterion("down_url not between", value1, value2, "downUrl");
            return (Criteria) this;
        }

        public Criteria andFileMd5IsNull() {
            addCriterion("file_md5 is null");
            return (Criteria) this;
        }

        public Criteria andFileMd5IsNotNull() {
            addCriterion("file_md5 is not null");
            return (Criteria) this;
        }

        public Criteria andFileMd5EqualTo(String value) {
            addCriterion("file_md5 =", value, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5NotEqualTo(String value) {
            addCriterion("file_md5 <>", value, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5GreaterThan(String value) {
            addCriterion("file_md5 >", value, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5GreaterThanOrEqualTo(String value) {
            addCriterion("file_md5 >=", value, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5LessThan(String value) {
            addCriterion("file_md5 <", value, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5LessThanOrEqualTo(String value) {
            addCriterion("file_md5 <=", value, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5Like(String value) {
            addCriterion("file_md5 like", value, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5NotLike(String value) {
            addCriterion("file_md5 not like", value, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5In(List<String> values) {
            addCriterion("file_md5 in", values, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5NotIn(List<String> values) {
            addCriterion("file_md5 not in", values, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5Between(String value1, String value2) {
            addCriterion("file_md5 between", value1, value2, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5NotBetween(String value1, String value2) {
            addCriterion("file_md5 not between", value1, value2, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andAccessUrlMd5IsNull() {
            addCriterion("access_url_md5 is null");
            return (Criteria) this;
        }

        public Criteria andAccessUrlMd5IsNotNull() {
            addCriterion("access_url_md5 is not null");
            return (Criteria) this;
        }

        public Criteria andAccessUrlMd5EqualTo(String value) {
            addCriterion("access_url_md5 =", value, "accessUrlMd5");
            return (Criteria) this;
        }

        public Criteria andAccessUrlMd5NotEqualTo(String value) {
            addCriterion("access_url_md5 <>", value, "accessUrlMd5");
            return (Criteria) this;
        }

        public Criteria andAccessUrlMd5GreaterThan(String value) {
            addCriterion("access_url_md5 >", value, "accessUrlMd5");
            return (Criteria) this;
        }

        public Criteria andAccessUrlMd5GreaterThanOrEqualTo(String value) {
            addCriterion("access_url_md5 >=", value, "accessUrlMd5");
            return (Criteria) this;
        }

        public Criteria andAccessUrlMd5LessThan(String value) {
            addCriterion("access_url_md5 <", value, "accessUrlMd5");
            return (Criteria) this;
        }

        public Criteria andAccessUrlMd5LessThanOrEqualTo(String value) {
            addCriterion("access_url_md5 <=", value, "accessUrlMd5");
            return (Criteria) this;
        }

        public Criteria andAccessUrlMd5Like(String value) {
            addCriterion("access_url_md5 like", value, "accessUrlMd5");
            return (Criteria) this;
        }

        public Criteria andAccessUrlMd5NotLike(String value) {
            addCriterion("access_url_md5 not like", value, "accessUrlMd5");
            return (Criteria) this;
        }

        public Criteria andAccessUrlMd5In(List<String> values) {
            addCriterion("access_url_md5 in", values, "accessUrlMd5");
            return (Criteria) this;
        }

        public Criteria andAccessUrlMd5NotIn(List<String> values) {
            addCriterion("access_url_md5 not in", values, "accessUrlMd5");
            return (Criteria) this;
        }

        public Criteria andAccessUrlMd5Between(String value1, String value2) {
            addCriterion("access_url_md5 between", value1, value2, "accessUrlMd5");
            return (Criteria) this;
        }

        public Criteria andAccessUrlMd5NotBetween(String value1, String value2) {
            addCriterion("access_url_md5 not between", value1, value2, "accessUrlMd5");
            return (Criteria) this;
        }

        public Criteria andAccessUrlIsNull() {
            addCriterion("access_url is null");
            return (Criteria) this;
        }

        public Criteria andAccessUrlIsNotNull() {
            addCriterion("access_url is not null");
            return (Criteria) this;
        }

        public Criteria andAccessUrlEqualTo(String value) {
            addCriterion("access_url =", value, "accessUrl");
            return (Criteria) this;
        }

        public Criteria andAccessUrlNotEqualTo(String value) {
            addCriterion("access_url <>", value, "accessUrl");
            return (Criteria) this;
        }

        public Criteria andAccessUrlGreaterThan(String value) {
            addCriterion("access_url >", value, "accessUrl");
            return (Criteria) this;
        }

        public Criteria andAccessUrlGreaterThanOrEqualTo(String value) {
            addCriterion("access_url >=", value, "accessUrl");
            return (Criteria) this;
        }

        public Criteria andAccessUrlLessThan(String value) {
            addCriterion("access_url <", value, "accessUrl");
            return (Criteria) this;
        }

        public Criteria andAccessUrlLessThanOrEqualTo(String value) {
            addCriterion("access_url <=", value, "accessUrl");
            return (Criteria) this;
        }

        public Criteria andAccessUrlLike(String value) {
            addCriterion("access_url like", value, "accessUrl");
            return (Criteria) this;
        }

        public Criteria andAccessUrlNotLike(String value) {
            addCriterion("access_url not like", value, "accessUrl");
            return (Criteria) this;
        }

        public Criteria andAccessUrlIn(List<String> values) {
            addCriterion("access_url in", values, "accessUrl");
            return (Criteria) this;
        }

        public Criteria andAccessUrlNotIn(List<String> values) {
            addCriterion("access_url not in", values, "accessUrl");
            return (Criteria) this;
        }

        public Criteria andAccessUrlBetween(String value1, String value2) {
            addCriterion("access_url between", value1, value2, "accessUrl");
            return (Criteria) this;
        }

        public Criteria andAccessUrlNotBetween(String value1, String value2) {
            addCriterion("access_url not between", value1, value2, "accessUrl");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andExtensionIsNull() {
            addCriterion("extension is null");
            return (Criteria) this;
        }

        public Criteria andExtensionIsNotNull() {
            addCriterion("extension is not null");
            return (Criteria) this;
        }

        public Criteria andExtensionEqualTo(String value) {
            addCriterion("extension =", value, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionNotEqualTo(String value) {
            addCriterion("extension <>", value, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionGreaterThan(String value) {
            addCriterion("extension >", value, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionGreaterThanOrEqualTo(String value) {
            addCriterion("extension >=", value, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionLessThan(String value) {
            addCriterion("extension <", value, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionLessThanOrEqualTo(String value) {
            addCriterion("extension <=", value, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionLike(String value) {
            addCriterion("extension like", value, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionNotLike(String value) {
            addCriterion("extension not like", value, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionIn(List<String> values) {
            addCriterion("extension in", values, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionNotIn(List<String> values) {
            addCriterion("extension not in", values, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionBetween(String value1, String value2) {
            addCriterion("extension between", value1, value2, "extension");
            return (Criteria) this;
        }

        public Criteria andExtensionNotBetween(String value1, String value2) {
            addCriterion("extension not between", value1, value2, "extension");
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

        public Criteria andStatusEqualTo(Short value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Short value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Short value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Short value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Short value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Short value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Short> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Short> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Short value1, Short value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Short value1, Short value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andFileDiskUrlIsNull() {
            addCriterion("file_disk_url is null");
            return (Criteria) this;
        }

        public Criteria andFileDiskUrlIsNotNull() {
            addCriterion("file_disk_url is not null");
            return (Criteria) this;
        }

        public Criteria andFileDiskUrlEqualTo(String value) {
            addCriterion("file_disk_url =", value, "fileDiskUrl");
            return (Criteria) this;
        }

        public Criteria andFileDiskUrlNotEqualTo(String value) {
            addCriterion("file_disk_url <>", value, "fileDiskUrl");
            return (Criteria) this;
        }

        public Criteria andFileDiskUrlGreaterThan(String value) {
            addCriterion("file_disk_url >", value, "fileDiskUrl");
            return (Criteria) this;
        }

        public Criteria andFileDiskUrlGreaterThanOrEqualTo(String value) {
            addCriterion("file_disk_url >=", value, "fileDiskUrl");
            return (Criteria) this;
        }

        public Criteria andFileDiskUrlLessThan(String value) {
            addCriterion("file_disk_url <", value, "fileDiskUrl");
            return (Criteria) this;
        }

        public Criteria andFileDiskUrlLessThanOrEqualTo(String value) {
            addCriterion("file_disk_url <=", value, "fileDiskUrl");
            return (Criteria) this;
        }

        public Criteria andFileDiskUrlLike(String value) {
            addCriterion("file_disk_url like", value, "fileDiskUrl");
            return (Criteria) this;
        }

        public Criteria andFileDiskUrlNotLike(String value) {
            addCriterion("file_disk_url not like", value, "fileDiskUrl");
            return (Criteria) this;
        }

        public Criteria andFileDiskUrlIn(List<String> values) {
            addCriterion("file_disk_url in", values, "fileDiskUrl");
            return (Criteria) this;
        }

        public Criteria andFileDiskUrlNotIn(List<String> values) {
            addCriterion("file_disk_url not in", values, "fileDiskUrl");
            return (Criteria) this;
        }

        public Criteria andFileDiskUrlBetween(String value1, String value2) {
            addCriterion("file_disk_url between", value1, value2, "fileDiskUrl");
            return (Criteria) this;
        }

        public Criteria andFileDiskUrlNotBetween(String value1, String value2) {
            addCriterion("file_disk_url not between", value1, value2, "fileDiskUrl");
            return (Criteria) this;
        }

        public Criteria andLastDownTimeIsNull() {
            addCriterion("last_down_time is null");
            return (Criteria) this;
        }

        public Criteria andLastDownTimeIsNotNull() {
            addCriterion("last_down_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastDownTimeEqualTo(Date value) {
            addCriterion("last_down_time =", value, "lastDownTime");
            return (Criteria) this;
        }

        public Criteria andLastDownTimeNotEqualTo(Date value) {
            addCriterion("last_down_time <>", value, "lastDownTime");
            return (Criteria) this;
        }

        public Criteria andLastDownTimeGreaterThan(Date value) {
            addCriterion("last_down_time >", value, "lastDownTime");
            return (Criteria) this;
        }

        public Criteria andLastDownTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_down_time >=", value, "lastDownTime");
            return (Criteria) this;
        }

        public Criteria andLastDownTimeLessThan(Date value) {
            addCriterion("last_down_time <", value, "lastDownTime");
            return (Criteria) this;
        }

        public Criteria andLastDownTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_down_time <=", value, "lastDownTime");
            return (Criteria) this;
        }

        public Criteria andLastDownTimeIn(List<Date> values) {
            addCriterion("last_down_time in", values, "lastDownTime");
            return (Criteria) this;
        }

        public Criteria andLastDownTimeNotIn(List<Date> values) {
            addCriterion("last_down_time not in", values, "lastDownTime");
            return (Criteria) this;
        }

        public Criteria andLastDownTimeBetween(Date value1, Date value2) {
            addCriterion("last_down_time between", value1, value2, "lastDownTime");
            return (Criteria) this;
        }

        public Criteria andLastDownTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_down_time not between", value1, value2, "lastDownTime");
            return (Criteria) this;
        }

        public Criteria andRetryCountIsNull() {
            addCriterion("retry_count is null");
            return (Criteria) this;
        }

        public Criteria andRetryCountIsNotNull() {
            addCriterion("retry_count is not null");
            return (Criteria) this;
        }

        public Criteria andRetryCountEqualTo(Integer value) {
            addCriterion("retry_count =", value, "retryCount");
            return (Criteria) this;
        }

        public Criteria andRetryCountNotEqualTo(Integer value) {
            addCriterion("retry_count <>", value, "retryCount");
            return (Criteria) this;
        }

        public Criteria andRetryCountGreaterThan(Integer value) {
            addCriterion("retry_count >", value, "retryCount");
            return (Criteria) this;
        }

        public Criteria andRetryCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("retry_count >=", value, "retryCount");
            return (Criteria) this;
        }

        public Criteria andRetryCountLessThan(Integer value) {
            addCriterion("retry_count <", value, "retryCount");
            return (Criteria) this;
        }

        public Criteria andRetryCountLessThanOrEqualTo(Integer value) {
            addCriterion("retry_count <=", value, "retryCount");
            return (Criteria) this;
        }

        public Criteria andRetryCountIn(List<Integer> values) {
            addCriterion("retry_count in", values, "retryCount");
            return (Criteria) this;
        }

        public Criteria andRetryCountNotIn(List<Integer> values) {
            addCriterion("retry_count not in", values, "retryCount");
            return (Criteria) this;
        }

        public Criteria andRetryCountBetween(Integer value1, Integer value2) {
            addCriterion("retry_count between", value1, value2, "retryCount");
            return (Criteria) this;
        }

        public Criteria andRetryCountNotBetween(Integer value1, Integer value2) {
            addCriterion("retry_count not between", value1, value2, "retryCount");
            return (Criteria) this;
        }

        public Criteria andParamsIsNull() {
            addCriterion("params is null");
            return (Criteria) this;
        }

        public Criteria andParamsIsNotNull() {
            addCriterion("params is not null");
            return (Criteria) this;
        }

        public Criteria andParamsEqualTo(String value) {
            addCriterion("params =", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsNotEqualTo(String value) {
            addCriterion("params <>", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsGreaterThan(String value) {
            addCriterion("params >", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsGreaterThanOrEqualTo(String value) {
            addCriterion("params >=", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsLessThan(String value) {
            addCriterion("params <", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsLessThanOrEqualTo(String value) {
            addCriterion("params <=", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsLike(String value) {
            addCriterion("params like", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsNotLike(String value) {
            addCriterion("params not like", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsIn(List<String> values) {
            addCriterion("params in", values, "params");
            return (Criteria) this;
        }

        public Criteria andParamsNotIn(List<String> values) {
            addCriterion("params not in", values, "params");
            return (Criteria) this;
        }

        public Criteria andParamsBetween(String value1, String value2) {
            addCriterion("params between", value1, value2, "params");
            return (Criteria) this;
        }

        public Criteria andParamsNotBetween(String value1, String value2) {
            addCriterion("params not between", value1, value2, "params");
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

        public Criteria andIsProxyIsNull() {
            addCriterion("is_proxy is null");
            return (Criteria) this;
        }

        public Criteria andIsProxyIsNotNull() {
            addCriterion("is_proxy is not null");
            return (Criteria) this;
        }

        public Criteria andIsProxyEqualTo(Boolean value) {
            addCriterion("is_proxy =", value, "isProxy");
            return (Criteria) this;
        }

        public Criteria andIsProxyNotEqualTo(Boolean value) {
            addCriterion("is_proxy <>", value, "isProxy");
            return (Criteria) this;
        }

        public Criteria andIsProxyGreaterThan(Boolean value) {
            addCriterion("is_proxy >", value, "isProxy");
            return (Criteria) this;
        }

        public Criteria andIsProxyGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_proxy >=", value, "isProxy");
            return (Criteria) this;
        }

        public Criteria andIsProxyLessThan(Boolean value) {
            addCriterion("is_proxy <", value, "isProxy");
            return (Criteria) this;
        }

        public Criteria andIsProxyLessThanOrEqualTo(Boolean value) {
            addCriterion("is_proxy <=", value, "isProxy");
            return (Criteria) this;
        }

        public Criteria andIsProxyIn(List<Boolean> values) {
            addCriterion("is_proxy in", values, "isProxy");
            return (Criteria) this;
        }

        public Criteria andIsProxyNotIn(List<Boolean> values) {
            addCriterion("is_proxy not in", values, "isProxy");
            return (Criteria) this;
        }

        public Criteria andIsProxyBetween(Boolean value1, Boolean value2) {
            addCriterion("is_proxy between", value1, value2, "isProxy");
            return (Criteria) this;
        }

        public Criteria andIsProxyNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_proxy not between", value1, value2, "isProxy");
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