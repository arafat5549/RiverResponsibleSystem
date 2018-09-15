package com.jqm.ssm.misc.es;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created by Administrator on 2018/7/27.
 * kafka客户端produecer 产生数据传送给其他集群
 */
public class KafkaDataProducer {


    public Producer<String, String> produer = null;
    public KafkaDataProducer()
    {
        Properties props = new Properties();
        props.put("bootstrap.servers", ELKConstants.HOST_KAFKA+":9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        produer = new KafkaProducer<String, String>(props);
    }
    public void publish(String topic, String content)
    {
        if(produer == null)
        {
            System.out.println("kafkaSpoondriftOut is NULL!");
            return;
        }
        produer.send(new ProducerRecord<String, String>(topic, content));
    }
}
