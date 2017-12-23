package cn.edu.wang.titan;

import com.thinkaurelius.titan.core.TitanGraph;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;

/**
 * Created by wangdechang on 2017/5/22.
 */
public class Titan {
    public static void main(String[] args) {
        TitanGraph graph = TitanUtils.getGraph();
        GraphTraversalSource g = graph.traversal();

        long start = System.currentTimeMillis();
        long count = g.V().has("age", 81).count().next();
        long end = System.currentTimeMillis();

       /* g.V(12).outE("Person").next();
        g.V(12).repeat(out()).times(2).values("name");
        long num = g.V().has("age",gt(20)).count().next();*/
        /*g.V('name','dan').outE('knows').has('type',types.get(r.nextInt(3)).inV
                g.V('name','dan').outE('likes').interval('rating',4,6).inV
                g.V('name','dan').outE('tweets').has('time',T.gt,(i*100)-10).inV*/

       /* GraphTraversalSource g = graph.traversal();
        System.out.println(g.V().count());*/
        graph.close();
        System.out.println(" age = 20, count = " + count);
        System.out.println(" time space = "+(end - start));
        System.out.println("=========================================");
    }
}
