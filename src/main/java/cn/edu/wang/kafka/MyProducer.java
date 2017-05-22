package cn.edu.wang.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created by wangdechang on 2017/5/15.
 */
public class MyProducer {
    Producer<String, String> producer;
    public MyProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.0.241:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<String,String>(props);

    }

    public void produceMessage(){
        for(int i = 0; i < 100; i++)
            producer.send(new ProducerRecord<String, String>("test", Integer.toString(i), Integer.toString(i)));

        producer.close();
    }

    public void sendMessage(String key,String value){
        producer.send(new ProducerRecord<String, String>("test", key, value));
    }

    public void closeProducer() throws Exception{
        if (producer != null){
            producer.close();
        }
    }
    public static void main(String[] args){
        new MyProducer().produceMessage();
    }
}
