package com.epam.rulerunner.kafka.consumer.domain;

//import lombok.Builder;
//import lombok.Getter;

//@Getter
//@Builder
public class KafkaSpan {
    private String traceId;
    private String spanId;
    private String flags;
    private String sampledName;
    private String spanName;
    private String parentId;

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getSpanId() {
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public String getSampledName() {
        return sampledName;
    }

    public void setSampledName(String sampledName) {
        this.sampledName = sampledName;
    }

    public String getSpanName() {
        return spanName;
    }

    public void setSpanName(String spanName) {
        this.spanName = spanName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
