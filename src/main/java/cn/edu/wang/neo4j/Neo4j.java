package cn.edu.wang.neo4j;

import cn.edu.wang.entity.GeneralNode;
import cn.edu.wang.entity.Person;
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
        try (Session session = driver.session()) {
            try (Transaction tx = session.beginTransaction()) {
                StatementResult result = tx.run("match(p:Person)-[r]->(n) return p,n,type(r) limit(100000)");//{gmsfhm:"925678195007284933"}
                //int size = result.list().size();
               /* List<Record> records = result.list();
               *//* for (Record record:records){

                }*//*

                System.out.println(records.size());*/

                while (result.hasNext()){
                    Record record = result.next();
                    List<Value> list = record.values();
                    Person person = new Person();
                    if (list != null && list.size() > 0){
                        Value sourceNode = list.get(0);
                        if (sourceNode instanceof NodeValue){
                            Node node = sourceNode.asNode();
                           /* System.out.println(node.labels().iterator().next());
                            System.out.println(node.asMap());*/
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

//                            person = new Person(map.get("name"),map.get("mz"),
//                                    map.get("zzmm"),map.get("csrq"),map.get("gmsfhm"),map.get("whcd"));
                           /* for (String key : node.asMap().keySet()) {

                            }
*/
                        }
                        if (list.size() >= 2){
                            Value destNode = list.get(1);
                            if (destNode instanceof NodeValue){
                                Node node = destNode.asNode();
                                GeneralNode generalNode = new GeneralNode();
                                String label = node.labels().iterator().next();
                                node.values().iterator().next().asString();
                                generalNode.setLabel(label);
                                generalNode.setValue(System.currentTimeMillis());
                                person.setGeneralNode(generalNode);
                               /* System.out.println(node.labels().iterator().next());
                                System.out.println(node.asMap());*/
                            }
                        }

                        if(list.size() > 2){
                            //System.out.println(list.get(2).asString());
                            person.setRel(list.get(2).asString());
                            /*Value rel = list.get(2);
                            if (rel instanceof Relationship){
                                Relationship relationship = rel.asRelationship();
                                System.out.println("relationship = "+relationship.type());
                            }*/
                        }
                       /* for (int i = 0;i<list.size();i++){
                            Value value = list.get(i);
                            if (va)
                            System.out.println(value.values());
                        }*/
                    }
                    //System.out.println("=========================");
                }
                /*StatementResult result = tx.run("match(p:Person)return p.name as name, " +
                        "p.mz as nation,p.zzmm as politicalIdentity,p.csrq as born,
                         p.gmsfhm as idNumber,p.whcd as degreeOfEducation limit 10");
                *//*tx.run("CREATE (a:Person {name: {name}, title: {title}})",
                        parameters("name", "Arthur", "title", "King"));*//*
                while (result.hasNext()) {
                    Record record = result.next();

                    System.out.println(String.format("%s %s %s %s %s %s", record.get("name").asString(),
                            record.get("idNumber").asString(),
                            record.get("nation").asString(),
                            record.get("politicalIdentity").asString(),
                            record.get("born").asString(), record.get("degreeOfEducation").asString()));
                }*/
                //System.out.println(size);
            }
            //session.close();
        }
        driver.close();
    }

    public static void main(String[] args) {
        new Neo4j().query();
    }
}
