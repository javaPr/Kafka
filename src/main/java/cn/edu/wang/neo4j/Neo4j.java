package cn.edu.wang.neo4j;

import cn.edu.wang.entity.GeneralNode;
import cn.edu.wang.entity.Person;
import cn.edu.wang.kafka.MyProducer;
import com.google.gson.Gson;
import org.neo4j.driver.internal.value.NodeValue;
import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Relationship;

import javax.management.relation.Relation;
import java.util.List;
import java.util.Map;

import static org.neo4j.driver.v1.Values.parameters;

/**
 * Created by wangdechang on 2017/5/15.
 */
public class Neo4j {
    public void query() {
        Driver driver = GraphDatabase.driver("bolt://192.168.0.230:7687", AuthTokens.basic("neo4j",
                "803"));
        MyProducer producer = new MyProducer();

        try (Session session = driver.session()) {
            try (Transaction tx = session.beginTransaction()) {
                StatementResult result = tx.run("match(p:Person)-[r]->(n) return p,n,type(r) skip 10 limit(1000000   )");//{gmsfhm:"925678195007284933"}

                while (result.hasNext()){
                    Record record = result.next();
                    List<Value> list = record.values();
                    Person person = new Person();
                    if (list != null && list.size() > 0){
                        Value sourceNode = list.get(0);
                        if (sourceNode instanceof NodeValue){
                            Node node = sourceNode.asNode();

                            Map<String, Object> map = node.asMap();
                            if (map == null || map.size() < 1) continue;
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
                        if (list.size() >= 2){
                            Value destNode = list.get(1);
                            if (destNode instanceof NodeValue){
                                Node node = destNode.asNode();
                                GeneralNode generalNode = new GeneralNode();
                                if(node.labels() != null && node.labels().iterator() != null){
                                    String label = node.labels().iterator().next();
                                    //node.values().iterator().next().asString();
                                    generalNode.setLabel(label);
                                }

                                generalNode.setValue(System.currentTimeMillis());
                                person.setGeneralNode(generalNode);
                               /* System.out.println(node.labels().iterator().next());
                                System.out.println(node.asMap());*/
                            }
                        }

                        if(list.size() > 2){
                            //System.out.println(list.get(2).asString());
                            person.setRel(list.get(2).asString());
                        }

                        String gson = new Gson().toJson(person);
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

    public static void main(String[] args) {
        new Neo4j().query();
    }
}
