package com.amazon.ata.advertising.service;

public class CreateContentResponse {
    private AdvertisingContent advertisingContent;
    private TargetingGroup targetingGroup;

    public CreateContentResponse(AdvertisingContent advertisingContent, TargetingGroup targetingGroup) {
        this.advertisingContent = advertisingContent;
        this.targetingGroup = targetingGroup;
    }

    public CreateContentResponse() {
    }

    public AdvertisingContent getAdvertisingContent() {
        return advertisingContent;
    }

    public void setAdvertisingContent(AdvertisingContent advertisingContent) {
        this.advertisingContent = advertisingContent;
    }

    public TargetingGroup getTargetingGroup() {
        return targetingGroup;
    }

    public void setTargetingGroup(TargetingGroup targetingGroup) {
        this.targetingGroup = targetingGroup;
    }

    public CreateContentResponse(Builder builder) {
        this.targetingGroup = builder.targetingGroup;
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private AdvertisingContent advertisingContent;
        private TargetingGroup targetingGroup;

        private Builder() {

        }

        public Builder withAdvertisingContent(AdvertisingContent advertisingContentToUse) {
            this.advertisingContent = advertisingContent;
            return this;
        }

        public Builder withTargetingGroup(TargetingGroup targetingGroupToUse) {
            this.targetingGroup = targetingGroup;
            return this;
        }

        public CreateContentResponse build() { return new CreateContentResponse(this); }
    }
}
