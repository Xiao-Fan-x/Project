package com.example.demo.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Ranphy
 * @Date: 2021/3/5 14:37
 * @desc: kafka生产者配置
 */
//@Configuration
@EnableKafka
public class KafkaProducerConfig {

    private static String bootstrapServers = "10.161.55.1:9092,10.161.55.2:9092,10.161.55.3:9092,10.161.55.4:9092,10.161.55.5:9092,10.161.55.6:9092,10.161.11.232:9092,10.161.11.233:9092,10.161.11.234:9092,10.161.11.15:9092,10.161.11.25:9092,10.161.11.26:9092";

    private static Integer retries = 0 ;

    private static Integer batchSize = 16785;

    private static Integer bufferMemory = 33554432;

    private static Integer linger = 1;

    @Value("${kafka.producer.bootstrap-servers}")
    public  void setBootstrapServers(String bootstrapServers) {
        KafkaProducerConfig.bootstrapServers = bootstrapServers;
    }

    @Value("${kafka.producer.retries}")
    public  void setRetries(Integer retries) {
        KafkaProducerConfig.retries = retries;
    }

    @Value("${kafka.producer.batch-size}")
    public  void setBatchSize(Integer batchSize) {
        KafkaProducerConfig.batchSize = batchSize;
    }


    @Value("${kafka.producer.buffer-memory}")
    public  void setBufferMemory(Integer bufferMemory) {
        KafkaProducerConfig.bufferMemory = bufferMemory;
    }

    @Value("${kafka.producer.linger}")
    public  void setLinger(Integer linger) {
        KafkaProducerConfig.linger = linger;
    }

    private static Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>(7);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.RETRIES_CONFIG, retries);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        props.put(ProducerConfig.LINGER_MS_CONFIG, linger);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put("security.protocol", "SASL_PLAINTEXT");
        props.put("sasl.mechanism", "PLAIN");
        props.put("sasl.jaas.config",
                "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"tianyan\" password=\"sK5@Om0#1\";");
        return props;
    }

    public static  ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
