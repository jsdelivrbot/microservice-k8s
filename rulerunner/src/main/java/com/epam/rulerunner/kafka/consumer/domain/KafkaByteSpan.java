package com.epam.rulerunner.kafka.consumer.domain;

//import lombok.Builder;
//import lombok.Data;

//@Data
//@Builder
public class KafkaByteSpan {
    private byte[] traceId;
    private byte[] spanId;
    private byte[] flags;
    private byte[] sampledName;
    private byte[] spanName;
    private byte[] parentId;

    public byte[] getTraceId() {
        return traceId;
    }

    public void setTraceId(byte[] traceId) {
        this.traceId = traceId;
    }

    public byte[] getSpanId() {
        return spanId;
    }

    public void setSpanId(byte[] spanId) {
        this.spanId = spanId;
    }

    public byte[] getFlags() {
        return flags;
    }

    public void setFlags(byte[] flags) {
        this.flags = flags;
    }

    public byte[] getSampledName() {
        return sampledName;
    }

    public void setSampledName(byte[] sampledName) {
        this.sampledName = sampledName;
    }

    public byte[] getSpanName() {
        return spanName;
    }

    public void setSpanName(byte[] spanName) {
        this.spanName = spanName;
    }

    public byte[] getParentId() {
        return parentId;
    }

    public void setParentId(byte[] parentId) {
        this.parentId = parentId;
    }
}
