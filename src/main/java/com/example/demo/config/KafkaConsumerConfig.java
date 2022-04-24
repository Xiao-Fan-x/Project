package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Ranphy
 * @Date: 2021/3/5 14:37
 * @desc: kafka消费者配置
 */
//@Configuration
@EnableKafka
@Slf4j
public class KafkaConsumerConfig {

    @Value("${kafka.consumer.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${kafka.consumer.enable-auto-commit}")
    private Boolean autoCommit;

    @Value("${kafka.consumer.auto-commit-interval}")
    private Integer autoCommitInterval;

    @Value("${kafka.consumer.max-poll-records}")
    private Integer maxPollRecords;

    @Value("${kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;

    @Value("#{'${kafka.listener.concurrencys}'.split(',')[0]}")
    private Integer concurrency3;

    @Value("#{'${kafka.listener.concurrencys}'.split(',')[1]}")
    private Integer concurrency6;

    @Value("${kafka.listener.poll-timeout}")
    private Long pollTimeout;

    @Value("${kafka.consumer.session-timeout}")
    private String sessionTimeout;

    @Value("${kafka.listener.batch-listener}")
    private Boolean batchListener;

    @Value("${kafka.consumer.max-partition-fetch-bytes}")
    private String maxPartitionFetchBytes;

    @Value("${kafka.consumer.fetch-min-bytes}")
    private String fetchMinBytes;

    @Value("${kafka.consumer.fetch-max-wait-ms}")
    private String fetchMaxWaitMs;

    @Value("#{'${jsLog.jsLogStartup}'}")
    private boolean jsLogStartup;

    @Value("#{'${jsLog.jsLogOtherStartup}'}")
    private boolean jsLogOtherStartup;

    @Value("#{'${ajax.jsAjaxStartup}'}")
    private boolean jsAjaxStartup;

    @Value("#{'${alert.jsAlertStartup}'}")
    private boolean jsAlertStartup;

    @Value("#{'${error.jsErrorStartup}'}")
    private boolean jsErrorStartup;

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactoryJsLog() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = kafkaListenerContainerFactory();
        factory.setConcurrency(concurrency6);
        //设置监听是否开启
        factory.setAutoStartup(jsLogStartup);
        log.info("jsLogStartup监听是否开启:" + jsLogStartup);
        return factory;
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactoryJsAjax() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = kafkaListenerContainerFactory();
        factory.setConcurrency(concurrency6);
        //设置监听是否开启
        factory.setAutoStartup(jsAjaxStartup);
        log.info("jsAjaxStartup监听是否开启:" + jsAjaxStartup);
        return factory;
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactoryJsAlert() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = kafkaListenerContainerFactory();
        factory.setConcurrency(concurrency3);
        //设置监听是否开启
        factory.setAutoStartup(jsAlertStartup);
        log.info("jsAlertStartup监听是否开启:" + jsAlertStartup);
        return factory;
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactoryJsError() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = kafkaListenerContainerFactory();
        factory.setConcurrency(concurrency3);
        //设置监听是否开启
        factory.setAutoStartup(jsErrorStartup);
        log.info("jsErrorStartup监听是否开启:" + jsErrorStartup);
        return factory;
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactoryJsLogOther() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = kafkaListenerContainerFactory();
        factory.setConcurrency(concurrency3);
        //设置监听是否开启
        factory.setAutoStartup(jsLogOtherStartup);
        log.info("jsLogOtherStartup监听是否开启:" + jsLogOtherStartup);
        return factory;
    }

    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        //批量消费
        factory.setBatchListener(batchListener);
        //如果消息队列中没有消息，等待timeout毫秒后，调用poll()方法。
        //如果队列中有消息，立即消费消息，每次消费的消息的多少可以通过max.poll.records配置。
        //手动提交无需配置
        factory.getContainerProperties().setPollTimeout(pollTimeout);
        //设置提交偏移量的方式，
//        factory.getContainerProperties().setAckMode(AbstractMessageListenerContainer.AckMode.MANUAL_IMMEDIATE);
        return factory;
    }

    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>(8);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitInterval);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, autoCommit);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, sessionTimeout);

        //增加消费量
        props.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, maxPartitionFetchBytes);
        props.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, fetchMinBytes);
        props.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, fetchMaxWaitMs);

        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put("security.protocol", "SASL_PLAINTEXT");
        props.put("sasl.mechanism", "PLAIN");
        props.put("sasl.jaas.config",
                "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"tianyan\" password=\"sK5@Om0#1\";");
        return props;
    }


}
