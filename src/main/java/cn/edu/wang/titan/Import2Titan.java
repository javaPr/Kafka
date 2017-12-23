package cn.edu.wang.titan;

import cn.edu.wang.entity.GeneralNode;
import cn.edu.wang.entity.Person;
import com.thinkaurelius.titan.core.PropertyKey;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.TitanTransaction;
import com.thinkaurelius.titan.core.TitanVertex;
import com.thinkaurelius.titan.core.schema.ConsistencyModifier;
import com.thinkaurelius.titan.core.schema.TitanGraphIndex;
import com.thinkaurelius.titan.core.schema.TitanManagement;
import com.thinkaurelius.titan.core.util.TitanCleanup;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.T;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.neo4j.driver.v1.Transaction;

import java.util.Random;

/**
 * Created by wangdechang on 2017/5/22.
 */
public class Import2Titan {
    public static TitanGraph graph;
    public static GraphTraversalSource g;
    Random random;
    public Import2Titan(){
        graph = TitanUtils.getGraph();
        g = graph.traversal();
        random = new Random(100);
    }



    public void importPerson(Person person){
        /*graph.addVertex(T.label,"Pet","name","thisisname","gmsfhm","123456789");*/
        TitanTransaction tx = graph.newTransaction();
       // g.V().has("gmsfhm")
        int age = random.nextInt(60)+10;
        TitanVertex vertex =  tx.addVertex(T.label,"person","name",person.getName(),
                "gmsfhm",person.getGmsfhm(),"age",age);
        /*TitanVertex vertex1 = tx.addVertex(T.label,person.getGeneralNode().getLabel(),
                "value",person.getGeneralNode().getValue());
        vertex.addEdge(person.getRel(),vertex1);*/
        tx.commit();
    }

    public void closeGraph() throws Exception{
        graph.close();
    }

    public void clearGraphDB(){
        graph.close();
        TitanCleanup.clear(graph);
    }


    public void setSchema(){
        TitanManagement mgmt = graph.openManagement();
        final PropertyKey name = mgmt.makePropertyKey("name").dataType(String.class).make();
        TitanManagement.IndexBuilder nameIndexBuilder = mgmt.buildIndex("name", Vertex.class).addKey(name);
        TitanGraphIndex namei = nameIndexBuilder.buildCompositeIndex();
        mgmt.setConsistency(namei, ConsistencyModifier.LOCK);
        final PropertyKey gmsfhm = mgmt.makePropertyKey("gmsfhm").dataType(String.class).make();
        mgmt.buildIndex("verticeGmsfhm", Vertex.class).addKey(gmsfhm).buildMixedIndex("search");

        mgmt.makeEdgeLabel("Hukou_in").make();
        mgmt.makeEdgeLabel("Case").make();
        mgmt.makeEdgeLabel("Person").make();

        mgmt.commit();

        TitanTransaction tx = graph.newTransaction();

        // commit the transaction to disk
        tx.commit();
    }

    public void test(){
        TitanTransaction tx  = graph.newTransaction();
        tx.addVertex(T.label,"person","name","Jason","age",10,"gmsfhm","123456789");
        tx.commit();
        System.out.println(g.V().count().next());
        String name = g.V().has("age",10).next().property("name").value().toString();
        System.out.println("===== name = "+name);
        graph.close();
    }

    public static void main(String[] args){
        new Import2Titan().test();
        /*Person person = new Person("nameTx","mz","zzmm","csrq","gmsfhm","whcd");
        person.setRel("IN");
        person.setGeneralNode(new GeneralNode(System.currentTimeMillis(),"Pet"));

        new Import2Titan().clearGraphDB();*/
    }
}
