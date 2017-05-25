package cn.edu.wang.neo4j;

import cn.edu.wang.entity.GeneralNode;
import cn.edu.wang.entity.Person;
import cn.edu.wang.kafka.MyProducer;
import com.google.gson.Gson;
import org.neo4j.driver.internal.value.NodeValue;
import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.types.Node;

import java.util.List;
import java.util.Map;

/**
 * Created by wangdechang on 2017/5/25.
 */
public class ReadDataFromNeo4j {
    public void query() {
        Driver driver = GraphDatabase.driver("bolt://192.168.0.230:7687", AuthTokens.basic("neo4j",
                "803"));
        MyProducer producer = new MyProducer();

        try (Session session = driver.session()) {
            try (Transaction tx = session.beginTransaction()) {
                StatementResult result = tx.run("match(p:Person) return p skip 100000 limit(868128)");//{gmsfhm:"925678195007284933"}

                while (result.hasNext()){
                    Record record = result.next();
                    List<Value> list = record.values();
                    Person person = new Person();
                    if (list != null && list.size() > 0){
                        Value sourceNode = list.get(0);
                        if (sourceNode instanceof NodeValue){
                            Node node = sourceNode.asNode();
                            Map<String, Object> map = node.asMap();
                            for (Map.Entry entry :node.asMap().entrySet()){
                                String key = ((String) entry.getKey()).trim();
                                String value = ((String) entry.getValue()).trim();
                                switch (key){
                                    case "name":{
                                        person.setName(value);
                                        break;
                                    }
                                    case "mz":{
                                        person.setMz(value);
                                        break;
                                    }
                                    case "zzmm":{
                                        person.setZzmm(value);
                                        break;
                                    }
                                    case "csrq":{
                                        person.setCsrq(value);
                                        break;
                                    }
                                    case "gmsfhm":{
                                        person.setGmsfhm(value);
                                        break;
                                    }
                                    case "whcd":{
                                        person.setWhcd(value);
                                        break;
                                    }

                                }

                            }

                        }

                        String gson = new Gson().toJson(person);
                        //System.out.println("==================="+gson);
                        producer.sendMessage("person",gson);
                    }
                }

            }
            //session.close();
        }
        driver.close();
        try {
            producer.closeProducer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new ReadDataFromNeo4j().query();
    }
}
