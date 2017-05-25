package cn.edu.wang.kafka;

import cn.edu.wang.entity.Person;
import cn.edu.wang.neo4j.Import2Neo4j;
import cn.edu.wang.titan.Import2Titan;
import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * Created by wangdechang on 2017/5/15.
 */
public class MyConsumer {
    KafkaConsumer<String, String> consumer;
    public MyConsumer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.0.241:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<String,String>(props);
        consumer.subscribe(Arrays.asList("test"));

    }

    public void getMessage(){
        Import2Neo4j import2Neo4j = new Import2Neo4j();
        Import2Titan import2Titan = new Import2Titan();
        while (true) {

            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records){
                //System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                String gson = record.value();
                //System.out.println(gson);
                Person person = new Gson().fromJson(gson,Person.class);
                //System.out.println(person.getName());
                import2Neo4j.addPerson(person);
//                import2Titan.importPerson(person);
            }
        }
    }

    public static void main(String[] args){
        new MyConsumer().getMessage();
    }
}
