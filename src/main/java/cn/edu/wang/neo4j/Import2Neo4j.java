package cn.edu.wang.neo4j;

import cn.edu.wang.entity.Person;
import org.neo4j.driver.v1.*;

import java.util.Random;

import static org.neo4j.driver.v1.Values.parameters;

/**
 * Created by wangdechang on 2017/5/22.
 */
public class Import2Neo4j {
    public static Driver driver;
    public Import2Neo4j(){
        driver = GraphDatabase.driver("bolt://192.168.0.241:7687", AuthTokens.basic("neo4j",
                "803"));
    }

    public void addPerson(Person person){
        int age = new Random(42).nextInt(60)+10;
        if (person != null){
            //System.out.println(person);
            try (Session session = driver.session()) {
                try (Transaction tx = session.beginTransaction()) {
                    StatementResult result = tx.run("match(p:Person{gmsfhm:{gmsfhm}}) return p",
                            parameters("gmsfhm",person.getGmsfhm()));
                    tx.run("CREATE (p:Person {name: {name},gmsfhm:{gmsfhm},age:{age}})",
                            parameters("name", person.getName(),
                                    "gmsfhm",person.getGmsfhm(),
                                    "age",age));
                    tx.success();

                }
            }
        }
    }
    public void importPerson(Person person){
        if (person != null){
            //System.out.println(person);
            try (Session session = driver.session()) {
                try (Transaction tx = session.beginTransaction()) {
                    StatementResult result = tx.run("match(p:Person{gmsfhm:{gmsfhm}}) return p",
                            parameters("gmsfhm",person.getGmsfhm()));
                    if(result.hasNext()){
                        String statement = "match(p:Person{gmsfhm:{gmsfhm}}) return p;" +
                                " CREATE (a:L" +person.getGeneralNode().getLabel()+"{property:{property})"+
                                " CREATE (p)-[:L"+person.getRel()+"]->(a)";
                        tx.run("match(p:Person{gmsfhm:{gmsfhm}})" +
                                        " CREATE (a: " +person.getGeneralNode().getLabel()+"{property:{property}})"+
                                        " CREATE (p)-[:"+person.getRel()+"]->(a)",
                                parameters("gmsfhm",person.getGmsfhm(),
                                        "property",person.getGeneralNode().getValue()));
                        tx.success();
                    }else {
                        String statement = "CREATE (p:Person {name: {name},mz: {mz},zzmm:{zzmm},csrq:{csrq},gmsfhm:{gmsfhm},whcd:{whcd});" +
                                "CREATE (a:L" +person.getGeneralNode().getLabel()+"{property:{property});" +
                                "CREATE (p)-[:L"+person.getRel()+"]->(a)";
                        tx.run("CREATE (p:Person {name: {name},mz: {mz},zzmm:{zzmm},csrq:{csrq},gmsfhm:{gmsfhm},whcd:{whcd}}) " +
                                        "CREATE (a:" +person.getGeneralNode().getLabel()+"{property:{property}}) " +
                                        "CREATE (p)-[:"+person.getRel()+"]->(a)",
                                parameters("name", person.getName(),
                                        "mz",person.getMz(),
                                        "zzmm",person.getZzmm(),
                                        "csrq",person.getCsrq(),
                                        "gmsfhm",person.getGmsfhm(),
                                        "whcd",person.getWhcd(),
                                        "property",person.getGeneralNode().getValue()));
                        tx.success();

                    }

                /*while (result.hasNext()){
                    Record record = result.next();
                    System.out.println(String.format("%s , %s ",record.get("name"),record.get("age")));

                }*/
                }
//                session.close();
            }
        }
    }

    public void closeDriver(){
        if (driver!= null){
            driver.close();
        }
    }
    public static void InsertNeo4j(){
       // Driver driver
        try (Session session = driver.session()) {
            try (Transaction tx = session.beginTransaction()) {
                StatementResult result = tx.run("match(p:Person)return p.name as name,p.age as age");

                String name ="";
                tx.run("CREATE (a:Person {name: {name}, title: {title}})",
                        parameters("name", name, "title", "King3"));
                tx.success();
            }
            session.close();
        }
        driver.close();
    }
}
