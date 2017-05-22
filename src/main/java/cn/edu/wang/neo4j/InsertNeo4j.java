package cn.edu.wang.neo4j;

import cn.edu.wang.entity.GeneralNode;
import cn.edu.wang.entity.Person;
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
                //StatementResult result = tx.run("match(p:Person)return p.name as name,p.age as age");
                /*tx.run("CREATE (a:Person {name: {name}, title: {title}})",
                        parameters("name", "Arthur", "title", "King"));*/
                /*while (result.hasNext()){
                    Record record = result.next();
                    System.out.println(String.format("%s , %s ",record.get("name"),record.get("age")));

                }*/
                String name ="";
               /* tx.run("CREATE (a:"+"Appel"+" {name: {name}, title: {title}})" +
                                "CREATE (a1:"+"Appel"+" {name: {name2}, title: {title2}})" +
                                "CREATE (a)-[:AC]->(a1)",
                        parameters("name", "Q1", "title", "King3","name2", "Q2", "title2", "King3"));
                */
                Person person = new Person("nameTx","mz","zzmm","csrq","gmsfhm","whcd");
                person.setRel("IN");
                person.setGeneralNode(new GeneralNode(System.currentTimeMillis(),"Pet"));
                tx.run("CREATE (p:Pett {name: {name}, mz: {mz}, zzmm: {zzmm}, csrq: {csrq}, gmsfhm: {gmsfhm}, whcd: {whcd}}) " +
                                "CREATE (a:L" +person.getGeneralNode().getLabel()+"{property: {property}}) " +
                                "CREATE (p)-[:L"+person.getRel()+"]->(a)",
                        parameters("name", person.getName(),
                                "mz",person.getMz(),
                                "zzmm",person.getZzmm(),
                                "csrq",person.getCsrq(),
                                "gmsfhm",person.getGmsfhm(),
                                "whcd",person.getWhcd(),
                                "property",person.getGeneralNode().getValue()));
               tx.success();
            }
//            session.close();
        }
        driver.close();
    }

    public static void main(String[] args){
        InsertNeo4j.InsertNeo4j();
    }
}
