package com.matrix.bigdata.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class TestConsumer {
    public static void main(String[] args) {
        // 创建配置对象
        Properties prop = new Properties();
        // kafka 集群
        prop.setProperty("bootstrap.servers", "hadoop101:9092");
        // k, v 序列化
        prop.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        prop.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // kafka 组
        prop.setProperty("group.id", "matrix");
        // 是否自动确认offset
        prop.setProperty("enable.auto.commit", "true");
        // 自动确认offset的时间间隔
        prop.setProperty("auto.commit.interval.ms", "1000");

        // 创建消费者
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(prop);
        // 订阅主题 可以订阅多个
        consumer.subscribe(Arrays.asList("abel"));
        while (true) {
            // 拉取数据
            ConsumerRecords<String, String> records = consumer.poll(500);
            for (ConsumerRecord<String, String> record : records) {
                // 用增强for循环 底层是迭代的
                System.out.println(record);
            }
        }

    }
}
