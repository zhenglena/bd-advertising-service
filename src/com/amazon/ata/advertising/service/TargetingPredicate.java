package com.amazon.ata.advertising.service;

import java.util.List;
import java.util.Map;

public class TargetingPredicate {
    private TargetingPredicateType targetingPredicateType;
    private boolean negate;
    private Map<String, String> attributes;

    public TargetingPredicate(TargetingPredicateType targetingPredicateType, boolean negate, Map<String, String> targetingPredicateAttributes) {
        this.targetingPredicateType = targetingPredicateType;
        this.negate = negate;
        this.attributes = targetingPredicateAttributes;
    }

    public TargetingPredicateType getTargetingPredicateType() {
        return targetingPredicateType;
    }

    public void setTargetingPredicateType(TargetingPredicateType targetingPredicateType) {
        this.targetingPredicateType = targetingPredicateType;
    }

    public boolean isNegate() {
        return negate;
    }

    public void setNegate(boolean negate) {
        this.negate = negate;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public TargetingPredicate(Builder builder) {
        targetingPredicateType = builder.targetingPredicateType;
        negate = builder.negate;
        attributes = builder.attributes;
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private TargetingPredicateType targetingPredicateType;
        private boolean negate;
        private Map<String, String> attributes;

        private Builder() {

        }

        public Builder withTargetingPredicateType(TargetingPredicateType targetingPredicateTypeToUse) {
            this.targetingPredicateType = targetingPredicateTypeToUse;
            return this;
        }

        public Builder withNegate(boolean negateToUse) {
            this.negate = negateToUse;
            return this;
        }

        public Builder withAttributes(Map<String, String> attributesToUse) {
            this.attributes = attributesToUse;
            return this;
        }

        public TargetingPredicate build() { return new TargetingPredicate(this); }
    }
}
