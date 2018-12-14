package com.kjw.bean;

import java.util.ArrayList;
import java.util.List;

public class WordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WordExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andWordIsNull() {
            addCriterion("word is null");
            return (Criteria) this;
        }

        public Criteria andWordIsNotNull() {
            addCriterion("word is not null");
            return (Criteria) this;
        }

        public Criteria andWordEqualTo(String value) {
            addCriterion("word =", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordNotEqualTo(String value) {
            addCriterion("word <>", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordGreaterThan(String value) {
            addCriterion("word >", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordGreaterThanOrEqualTo(String value) {
            addCriterion("word >=", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordLessThan(String value) {
            addCriterion("word <", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordLessThanOrEqualTo(String value) {
            addCriterion("word <=", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordLike(String value) {
            addCriterion("word like", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordNotLike(String value) {
            addCriterion("word not like", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordIn(List<String> values) {
            addCriterion("word in", values, "word");
            return (Criteria) this;
        }

        public Criteria andWordNotIn(List<String> values) {
            addCriterion("word not in", values, "word");
            return (Criteria) this;
        }

        public Criteria andWordBetween(String value1, String value2) {
            addCriterion("word between", value1, value2, "word");
            return (Criteria) this;
        }

        public Criteria andWordNotBetween(String value1, String value2) {
            addCriterion("word not between", value1, value2, "word");
            return (Criteria) this;
        }

        public Criteria andChineseIsNull() {
            addCriterion("chinese is null");
            return (Criteria) this;
        }

        public Criteria andChineseIsNotNull() {
            addCriterion("chinese is not null");
            return (Criteria) this;
        }

        public Criteria andChineseEqualTo(String value) {
            addCriterion("chinese =", value, "chinese");
            return (Criteria) this;
        }

        public Criteria andChineseNotEqualTo(String value) {
            addCriterion("chinese <>", value, "chinese");
            return (Criteria) this;
        }

        public Criteria andChineseGreaterThan(String value) {
            addCriterion("chinese >", value, "chinese");
            return (Criteria) this;
        }

        public Criteria andChineseGreaterThanOrEqualTo(String value) {
            addCriterion("chinese >=", value, "chinese");
            return (Criteria) this;
        }

        public Criteria andChineseLessThan(String value) {
            addCriterion("chinese <", value, "chinese");
            return (Criteria) this;
        }

        public Criteria andChineseLessThanOrEqualTo(String value) {
            addCriterion("chinese <=", value, "chinese");
            return (Criteria) this;
        }

        public Criteria andChineseLike(String value) {
            addCriterion("chinese like", value, "chinese");
            return (Criteria) this;
        }

        public Criteria andChineseNotLike(String value) {
            addCriterion("chinese not like", value, "chinese");
            return (Criteria) this;
        }

        public Criteria andChineseIn(List<String> values) {
            addCriterion("chinese in", values, "chinese");
            return (Criteria) this;
        }

        public Criteria andChineseNotIn(List<String> values) {
            addCriterion("chinese not in", values, "chinese");
            return (Criteria) this;
        }

        public Criteria andChineseBetween(String value1, String value2) {
            addCriterion("chinese between", value1, value2, "chinese");
            return (Criteria) this;
        }

        public Criteria andChineseNotBetween(String value1, String value2) {
            addCriterion("chinese not between", value1, value2, "chinese");
            return (Criteria) this;
        }

        public Criteria andRootIsNull() {
            addCriterion("root is null");
            return (Criteria) this;
        }

        public Criteria andRootIsNotNull() {
            addCriterion("root is not null");
            return (Criteria) this;
        }

        public Criteria andRootEqualTo(String value) {
            addCriterion("root =", value, "root");
            return (Criteria) this;
        }

        public Criteria andRootNotEqualTo(String value) {
            addCriterion("root <>", value, "root");
            return (Criteria) this;
        }

        public Criteria andRootGreaterThan(String value) {
            addCriterion("root >", value, "root");
            return (Criteria) this;
        }

        public Criteria andRootGreaterThanOrEqualTo(String value) {
            addCriterion("root >=", value, "root");
            return (Criteria) this;
        }

        public Criteria andRootLessThan(String value) {
            addCriterion("root <", value, "root");
            return (Criteria) this;
        }

        public Criteria andRootLessThanOrEqualTo(String value) {
            addCriterion("root <=", value, "root");
            return (Criteria) this;
        }

        public Criteria andRootLike(String value) {
            addCriterion("root like", value, "root");
            return (Criteria) this;
        }

        public Criteria andRootNotLike(String value) {
            addCriterion("root not like", value, "root");
            return (Criteria) this;
        }

        public Criteria andRootIn(List<String> values) {
            addCriterion("root in", values, "root");
            return (Criteria) this;
        }

        public Criteria andRootNotIn(List<String> values) {
            addCriterion("root not in", values, "root");
            return (Criteria) this;
        }

        public Criteria andRootBetween(String value1, String value2) {
            addCriterion("root between", value1, value2, "root");
            return (Criteria) this;
        }

        public Criteria andRootNotBetween(String value1, String value2) {
            addCriterion("root not between", value1, value2, "root");
            return (Criteria) this;
        }

        public Criteria andCorewordIsNull() {
            addCriterion("coreword is null");
            return (Criteria) this;
        }

        public Criteria andCorewordIsNotNull() {
            addCriterion("coreword is not null");
            return (Criteria) this;
        }

        public Criteria andCorewordEqualTo(String value) {
            addCriterion("coreword =", value, "coreword");
            return (Criteria) this;
        }

        public Criteria andCorewordNotEqualTo(String value) {
            addCriterion("coreword <>", value, "coreword");
            return (Criteria) this;
        }

        public Criteria andCorewordGreaterThan(String value) {
            addCriterion("coreword >", value, "coreword");
            return (Criteria) this;
        }

        public Criteria andCorewordGreaterThanOrEqualTo(String value) {
            addCriterion("coreword >=", value, "coreword");
            return (Criteria) this;
        }

        public Criteria andCorewordLessThan(String value) {
            addCriterion("coreword <", value, "coreword");
            return (Criteria) this;
        }

        public Criteria andCorewordLessThanOrEqualTo(String value) {
            addCriterion("coreword <=", value, "coreword");
            return (Criteria) this;
        }

        public Criteria andCorewordLike(String value) {
            addCriterion("coreword like", value, "coreword");
            return (Criteria) this;
        }

        public Criteria andCorewordNotLike(String value) {
            addCriterion("coreword not like", value, "coreword");
            return (Criteria) this;
        }

        public Criteria andCorewordIn(List<String> values) {
            addCriterion("coreword in", values, "coreword");
            return (Criteria) this;
        }

        public Criteria andCorewordNotIn(List<String> values) {
            addCriterion("coreword not in", values, "coreword");
            return (Criteria) this;
        }

        public Criteria andCorewordBetween(String value1, String value2) {
            addCriterion("coreword between", value1, value2, "coreword");
            return (Criteria) this;
        }

        public Criteria andCorewordNotBetween(String value1, String value2) {
            addCriterion("coreword not between", value1, value2, "coreword");
            return (Criteria) this;
        }

        public Criteria andSentenceIsNull() {
            addCriterion("sentence is null");
            return (Criteria) this;
        }

        public Criteria andSentenceIsNotNull() {
            addCriterion("sentence is not null");
            return (Criteria) this;
        }

        public Criteria andSentenceEqualTo(String value) {
            addCriterion("sentence =", value, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceNotEqualTo(String value) {
            addCriterion("sentence <>", value, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceGreaterThan(String value) {
            addCriterion("sentence >", value, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceGreaterThanOrEqualTo(String value) {
            addCriterion("sentence >=", value, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceLessThan(String value) {
            addCriterion("sentence <", value, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceLessThanOrEqualTo(String value) {
            addCriterion("sentence <=", value, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceLike(String value) {
            addCriterion("sentence like", value, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceNotLike(String value) {
            addCriterion("sentence not like", value, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceIn(List<String> values) {
            addCriterion("sentence in", values, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceNotIn(List<String> values) {
            addCriterion("sentence not in", values, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceBetween(String value1, String value2) {
            addCriterion("sentence between", value1, value2, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceNotBetween(String value1, String value2) {
            addCriterion("sentence not between", value1, value2, "sentence");
            return (Criteria) this;
        }

        public Criteria andNoteIsNull() {
            addCriterion("note is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("note is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("note =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("note <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("note >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("note >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("note <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("note <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("note like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("note not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("note in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("note not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("note between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("note not between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andPhoneticIsNull() {
            addCriterion("phonetic is null");
            return (Criteria) this;
        }

        public Criteria andPhoneticIsNotNull() {
            addCriterion("phonetic is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneticEqualTo(String value) {
            addCriterion("phonetic =", value, "phonetic");
            return (Criteria) this;
        }

        public Criteria andPhoneticNotEqualTo(String value) {
            addCriterion("phonetic <>", value, "phonetic");
            return (Criteria) this;
        }

        public Criteria andPhoneticGreaterThan(String value) {
            addCriterion("phonetic >", value, "phonetic");
            return (Criteria) this;
        }

        public Criteria andPhoneticGreaterThanOrEqualTo(String value) {
            addCriterion("phonetic >=", value, "phonetic");
            return (Criteria) this;
        }

        public Criteria andPhoneticLessThan(String value) {
            addCriterion("phonetic <", value, "phonetic");
            return (Criteria) this;
        }

        public Criteria andPhoneticLessThanOrEqualTo(String value) {
            addCriterion("phonetic <=", value, "phonetic");
            return (Criteria) this;
        }

        public Criteria andPhoneticLike(String value) {
            addCriterion("phonetic like", value, "phonetic");
            return (Criteria) this;
        }

        public Criteria andPhoneticNotLike(String value) {
            addCriterion("phonetic not like", value, "phonetic");
            return (Criteria) this;
        }

        public Criteria andPhoneticIn(List<String> values) {
            addCriterion("phonetic in", values, "phonetic");
            return (Criteria) this;
        }

        public Criteria andPhoneticNotIn(List<String> values) {
            addCriterion("phonetic not in", values, "phonetic");
            return (Criteria) this;
        }

        public Criteria andPhoneticBetween(String value1, String value2) {
            addCriterion("phonetic between", value1, value2, "phonetic");
            return (Criteria) this;
        }

        public Criteria andPhoneticNotBetween(String value1, String value2) {
            addCriterion("phonetic not between", value1, value2, "phonetic");
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