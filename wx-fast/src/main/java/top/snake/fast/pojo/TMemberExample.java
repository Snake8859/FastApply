package top.snake.fast.pojo;

import java.util.ArrayList;
import java.util.List;

public class TMemberExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TMemberExample() {
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

        public Criteria andAidIsNull() {
            addCriterion("aid is null");
            return (Criteria) this;
        }

        public Criteria andAidIsNotNull() {
            addCriterion("aid is not null");
            return (Criteria) this;
        }

        public Criteria andAidEqualTo(String value) {
            addCriterion("aid =", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidNotEqualTo(String value) {
            addCriterion("aid <>", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidGreaterThan(String value) {
            addCriterion("aid >", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidGreaterThanOrEqualTo(String value) {
            addCriterion("aid >=", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidLessThan(String value) {
            addCriterion("aid <", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidLessThanOrEqualTo(String value) {
            addCriterion("aid <=", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidLike(String value) {
            addCriterion("aid like", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidNotLike(String value) {
            addCriterion("aid not like", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidIn(List<String> values) {
            addCriterion("aid in", values, "aid");
            return (Criteria) this;
        }

        public Criteria andAidNotIn(List<String> values) {
            addCriterion("aid not in", values, "aid");
            return (Criteria) this;
        }

        public Criteria andAidBetween(String value1, String value2) {
            addCriterion("aid between", value1, value2, "aid");
            return (Criteria) this;
        }

        public Criteria andAidNotBetween(String value1, String value2) {
            addCriterion("aid not between", value1, value2, "aid");
            return (Criteria) this;
        }

        public Criteria andIsmemberIsNull() {
            addCriterion("ismember is null");
            return (Criteria) this;
        }

        public Criteria andIsmemberIsNotNull() {
            addCriterion("ismember is not null");
            return (Criteria) this;
        }

        public Criteria andIsmemberEqualTo(String value) {
            addCriterion("ismember =", value, "ismember");
            return (Criteria) this;
        }

        public Criteria andIsmemberNotEqualTo(String value) {
            addCriterion("ismember <>", value, "ismember");
            return (Criteria) this;
        }

        public Criteria andIsmemberGreaterThan(String value) {
            addCriterion("ismember >", value, "ismember");
            return (Criteria) this;
        }

        public Criteria andIsmemberGreaterThanOrEqualTo(String value) {
            addCriterion("ismember >=", value, "ismember");
            return (Criteria) this;
        }

        public Criteria andIsmemberLessThan(String value) {
            addCriterion("ismember <", value, "ismember");
            return (Criteria) this;
        }

        public Criteria andIsmemberLessThanOrEqualTo(String value) {
            addCriterion("ismember <=", value, "ismember");
            return (Criteria) this;
        }

        public Criteria andIsmemberLike(String value) {
            addCriterion("ismember like", value, "ismember");
            return (Criteria) this;
        }

        public Criteria andIsmemberNotLike(String value) {
            addCriterion("ismember not like", value, "ismember");
            return (Criteria) this;
        }

        public Criteria andIsmemberIn(List<String> values) {
            addCriterion("ismember in", values, "ismember");
            return (Criteria) this;
        }

        public Criteria andIsmemberNotIn(List<String> values) {
            addCriterion("ismember not in", values, "ismember");
            return (Criteria) this;
        }

        public Criteria andIsmemberBetween(String value1, String value2) {
            addCriterion("ismember between", value1, value2, "ismember");
            return (Criteria) this;
        }

        public Criteria andIsmemberNotBetween(String value1, String value2) {
            addCriterion("ismember not between", value1, value2, "ismember");
            return (Criteria) this;
        }

        public Criteria andIsassheadIsNull() {
            addCriterion("isasshead is null");
            return (Criteria) this;
        }

        public Criteria andIsassheadIsNotNull() {
            addCriterion("isasshead is not null");
            return (Criteria) this;
        }

        public Criteria andIsassheadEqualTo(String value) {
            addCriterion("isasshead =", value, "isasshead");
            return (Criteria) this;
        }

        public Criteria andIsassheadNotEqualTo(String value) {
            addCriterion("isasshead <>", value, "isasshead");
            return (Criteria) this;
        }

        public Criteria andIsassheadGreaterThan(String value) {
            addCriterion("isasshead >", value, "isasshead");
            return (Criteria) this;
        }

        public Criteria andIsassheadGreaterThanOrEqualTo(String value) {
            addCriterion("isasshead >=", value, "isasshead");
            return (Criteria) this;
        }

        public Criteria andIsassheadLessThan(String value) {
            addCriterion("isasshead <", value, "isasshead");
            return (Criteria) this;
        }

        public Criteria andIsassheadLessThanOrEqualTo(String value) {
            addCriterion("isasshead <=", value, "isasshead");
            return (Criteria) this;
        }

        public Criteria andIsassheadLike(String value) {
            addCriterion("isasshead like", value, "isasshead");
            return (Criteria) this;
        }

        public Criteria andIsassheadNotLike(String value) {
            addCriterion("isasshead not like", value, "isasshead");
            return (Criteria) this;
        }

        public Criteria andIsassheadIn(List<String> values) {
            addCriterion("isasshead in", values, "isasshead");
            return (Criteria) this;
        }

        public Criteria andIsassheadNotIn(List<String> values) {
            addCriterion("isasshead not in", values, "isasshead");
            return (Criteria) this;
        }

        public Criteria andIsassheadBetween(String value1, String value2) {
            addCriterion("isasshead between", value1, value2, "isasshead");
            return (Criteria) this;
        }

        public Criteria andIsassheadNotBetween(String value1, String value2) {
            addCriterion("isasshead not between", value1, value2, "isasshead");
            return (Criteria) this;
        }

        public Criteria andAssidIsNull() {
            addCriterion("assid is null");
            return (Criteria) this;
        }

        public Criteria andAssidIsNotNull() {
            addCriterion("assid is not null");
            return (Criteria) this;
        }

        public Criteria andAssidEqualTo(String value) {
            addCriterion("assid =", value, "assid");
            return (Criteria) this;
        }

        public Criteria andAssidNotEqualTo(String value) {
            addCriterion("assid <>", value, "assid");
            return (Criteria) this;
        }

        public Criteria andAssidGreaterThan(String value) {
            addCriterion("assid >", value, "assid");
            return (Criteria) this;
        }

        public Criteria andAssidGreaterThanOrEqualTo(String value) {
            addCriterion("assid >=", value, "assid");
            return (Criteria) this;
        }

        public Criteria andAssidLessThan(String value) {
            addCriterion("assid <", value, "assid");
            return (Criteria) this;
        }

        public Criteria andAssidLessThanOrEqualTo(String value) {
            addCriterion("assid <=", value, "assid");
            return (Criteria) this;
        }

        public Criteria andAssidLike(String value) {
            addCriterion("assid like", value, "assid");
            return (Criteria) this;
        }

        public Criteria andAssidNotLike(String value) {
            addCriterion("assid not like", value, "assid");
            return (Criteria) this;
        }

        public Criteria andAssidIn(List<String> values) {
            addCriterion("assid in", values, "assid");
            return (Criteria) this;
        }

        public Criteria andAssidNotIn(List<String> values) {
            addCriterion("assid not in", values, "assid");
            return (Criteria) this;
        }

        public Criteria andAssidBetween(String value1, String value2) {
            addCriterion("assid between", value1, value2, "assid");
            return (Criteria) this;
        }

        public Criteria andAssidNotBetween(String value1, String value2) {
            addCriterion("assid not between", value1, value2, "assid");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNull() {
            addCriterion("openid is null");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNotNull() {
            addCriterion("openid is not null");
            return (Criteria) this;
        }

        public Criteria andOpenidEqualTo(String value) {
            addCriterion("openid =", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotEqualTo(String value) {
            addCriterion("openid <>", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThan(String value) {
            addCriterion("openid >", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("openid >=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThan(String value) {
            addCriterion("openid <", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThanOrEqualTo(String value) {
            addCriterion("openid <=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLike(String value) {
            addCriterion("openid like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotLike(String value) {
            addCriterion("openid not like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidIn(List<String> values) {
            addCriterion("openid in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotIn(List<String> values) {
            addCriterion("openid not in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidBetween(String value1, String value2) {
            addCriterion("openid between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotBetween(String value1, String value2) {
            addCriterion("openid not between", value1, value2, "openid");
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