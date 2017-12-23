package cn.edu.wang.neo4j;

import cn.edu.wang.Utils;
import org.neo4j.driver.v1.*;

import static org.neo4j.driver.v1.Values.parameters;

/**
 * Created by wangdechang on 2017/5/25.
 */
public class CreateVertex {
    public void addVertex() {
        Driver driver = GraphDatabase.driver("bolt://localhost:7687",
                AuthTokens.basic("neo4j",
                        "wang"));
        try (Session session = driver.session()) {
            try (Transaction tx = session.beginTransaction()) {

               /* for (int i = 0;i<10000000;i++){
                    try (Transaction tx = session.beginTransaction()) {
                        tx.run("CREATE (p:Person {name: {name},age:{age}})",
                                parameters("name","name"+i,"age",i%100+1));
                        tx.success();
                    }
                }*/
               boolean isFirst = false;
               boolean newLine = true;
               //if(i == 0) newLine = true;
               long start = System.currentTimeMillis();
               StatementResult result = tx.run("match(p:Person) where p.age = 20 return count(p) as count");
               while (result.hasNext()){
                   Record record = result.next();
                   long count = record.get("count").asLong();
                   long end = System.currentTimeMillis();
                   System.out.println("count = "+count);
                   System.out.println("time space = "+(end -start));
                   String pre = "5000w: ";
                   if (!newLine){
                       pre = "";
                   }
                   String res = pre + (end - start)+" ";
                   Utils.write(res,newLine&!isFirst);
               }
            }
        }
    }

    public void createVertex() {
        Driver driver = GraphDatabase.driver("bolt://localhost:7687",
                AuthTokens.basic("neo4j",
                        "wang"));
        try (Session session = driver.session()) {
            for (int i = 0;i<10000000;i++){
                try (Transaction tx = session.beginTransaction()) {
                    tx.run("CREATE (p:Person {name: {name},age:{age}})",
                            parameters("name","name"+i,"age",i%100+1));
                    tx.success();
                }
            }
        }
    }

    public static void main(String[] args) {

//        for(int i = 0;i<10;i++){
//            new CreateVertex().createVertex();
//            for(int j = 0;j<10;j++){
//                new CreateVertex().addVertex(i,j);
//            }
//        }
        new CreateVertex().addVertex();
    }
}
