package cn.edu.wang.neo4j;

import org.neo4j.driver.v1.*;

import static org.neo4j.driver.v1.Values.parameters;

/**
 * Created by wangdechang on 2017/5/22.
 */
public class InsertNeo4j {
    public static void InsertNeo4j(){
        Driver driver = GraphDatabase.driver("bolt://192.168.0.241:7687", AuthTokens.basic("neo4j",
                "803"));
        try (Session session = driver.session()) {
            try (Transaction tx = session.beginTransaction()) {
                /*StatementResult result = tx.run("match(p:Person)return p.name as name, " +
                        "p.mz as nation,p.zzmm as politicalIdentity,p.csrq as born, " +
                        "p.gmsfhm as idNumber,p.whcd as degreeOfEducation limit 10");*/
                StatementResult result = tx.run("match(p:Person)return p.name as name,p.age as age");
                /*tx.run("CREATE (a:Person {name: {name}, title: {title}})",
                        parameters("name", "Arthur", "title", "King"));*/
                /*while (result.hasNext()){
                    Record record = result.next();
                    System.out.println(String.format("%s , %s ",record.get("name"),record.get("age")));

                }*/
                String name ="";
                tx.run("CREATE (a:Person {name: {name}, title: {title}})",
                        parameters("name", name, "title", "King3"));
                tx.success();
            }
            session.close();
        }
        driver.close();
    }

    public static void main(String[] args){
        InsertNeo4j.InsertNeo4j();
    }
}
