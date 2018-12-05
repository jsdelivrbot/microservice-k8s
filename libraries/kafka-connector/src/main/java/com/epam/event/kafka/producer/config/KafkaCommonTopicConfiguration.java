package com.epam.event.kafka.producer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

//@Setter
//@Getter
public class KafkaCommonTopicConfiguration {
    @Value("${kafka.common_topic:default_topic}")
    private String commonTopic;

    public String getCommonTopic() {
        return commonTopic;
    }

    public void setCommonTopic(String commonTopic) {
        this.commonTopic = commonTopic;
    }
}
