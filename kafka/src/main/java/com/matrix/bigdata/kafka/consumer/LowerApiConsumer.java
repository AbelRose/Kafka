package com.matrix.bigdata.kafka.consumer;

import kafka.api.FetchRequestBuilder;
import kafka.api.FetchRequest;
import kafka.cluster.BrokerEndPoint;
import kafka.javaapi.*;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.javaapi.message.ByteBufferMessageSet;
import kafka.message.MessageAndOffset;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class LowerApiConsumer {
    @SuppressWarnings("all")
    public static void main(String[] args) throws Exception {
        // 创建一个简单的Consumer
        String host = "hadoop101";
        int port = 9092;
        BrokerEndPoint leader = null;

        // 获取分区的Leader
        SimpleConsumer metaConsumer = new SimpleConsumer(host, port, 500, 10*1024, "metadata");
        // 获取元数据
        TopicMetadataRequest request1 = new TopicMetadataRequest(Arrays.asList("abel"));
        TopicMetadataResponse response1 = metaConsumer.send(request1);

        leaderLabel: // 做一个标记
        for (TopicMetadata topicsMetadatum : response1.topicsMetadata()) {
            // 得到的是每一个topic的元数据信息
            if ("abel".equals(topicsMetadatum.topic())) {
                // 关心的主题元数据信息
                for (PartitionMetadata partitionsMetadatum : topicsMetadatum.partitionsMetadata()) {
                    // 得到了每一个分区的元数据信息
                    int partId = partitionsMetadatum.partitionId();
                    if (partId == 1) {
                        // 关心的分区信息 即可得到leader 和 flower
                        leader = partitionsMetadatum.leader();
                        System.out.println("取到Leader咯!");
                        break leaderLabel; // 此处不能直接使用break 跳不出最外面的循环
                    }
                }
            }
        }

        // 如果没取到
        if (leader == null) {
            System.out.println("分区信息不正确");
            return;
        }

        // 注意此处的host 和 port 应该是指定分区的leader
        SimpleConsumer consumer = new SimpleConsumer(leader.host(), leader.port(), 500, 10*1024, "access");
        // 抓取数据
        FetchRequest request = new FetchRequestBuilder().addFetch("abel", 1, 1, 1024).build();
        FetchResponse response = consumer.fetch(request);
        // 消息集
        ByteBufferMessageSet messageSet = response.messageSet("abel", 1);
        for (MessageAndOffset messageAndOffset : messageSet) {
            ByteBuffer buffer = messageAndOffset.message().payload(); // 得到的是ByteBuffer
            // 转成字符串
            byte[] bs = new byte[buffer.limit()];
            buffer.get(bs);
            String value = new String(bs, "UTF-8");
            System.out.println(value);
        }
    }
}
