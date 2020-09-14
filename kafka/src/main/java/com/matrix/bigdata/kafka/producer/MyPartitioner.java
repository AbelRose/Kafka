package com.matrix.bigdata.kafka.producer;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * 分区算法类 计算分区
 */
public class MyPartitioner implements Partitioner {
    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        return 2; // 返回的阿是分区号
    }

    public void close() {

    }

    public void configure(Map<String, ?> map) {

    }
}
