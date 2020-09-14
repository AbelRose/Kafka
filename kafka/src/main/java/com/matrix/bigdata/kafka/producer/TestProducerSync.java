package com.matrix.bigdata.kafka.producer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class TestProducer {
    public static void main(String[] args) {
        // 创建配置对象
        Properties prop = new Properties();
        // kafka 集群
        prop.setProperty("bootstrap.servers", "hadoop101:9092");
        // k, v 序列化
        prop.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // 应答机制
        prop.setProperty("acks", "1");
        // 创建生产者
        Producer<String, String> producer = new KafkaProducer<String, String>(prop);
        // 准备数据
        String topic = "abel";
        String value = "matrix";
        ProducerRecord record = new ProducerRecord(topic, value);
        // 生产(生产)数据
//        producer.send(record); // 异步的
        // 生产(生产)数据
        producer.send(record, new Callback() {
            // 回调方法
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                // 发送哪一个分区
                System.out.println(recordMetadata.partition());
                // 发送数据的偏移量
                System.out.println(recordMetadata.offset());
            }
        }); // 同步实现 异步加回调函数
        // 生产完成
        System.out.println("生产完成");
        // 关闭生产者
        producer.close();
    }
}
