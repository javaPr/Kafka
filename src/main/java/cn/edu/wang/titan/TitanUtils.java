package cn.edu.wang.titan;

import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;

/**
 * Created by wangdechang on 2017/5/22.
 */
public class TitanUtils {
    public static final String INDEX_NAME = "search";
    public static TitanGraph getGraph(){
        /*TitanFactory.Builder config = TitanFactory.build();
        config.set("storage.backend", "cassandra");

        config.set("storage.hostname", "192.168.171.128");
        config.set("index.search.directory", "/home/andy/titan-1.0.0-hadoop1/db/es");
        config.set("index." + INDEX_NAME + ".backend", "elasticsearch");
        config.set("index." + INDEX_NAME + ".elasticsearch.local-mode", true);
        config.set("index." + INDEX_NAME + ".elasticsearch.client-only", false);*/

        TitanFactory.Builder config = TitanFactory.build();
        config.set("storage.backend", "cassandra");
//        config.set("storage.hostname","localhost");
//        config.set("storage.directory", "/home/titan-1.0.0-hadoop1/db/berkeley");
        config.set("storage.hostname", "192.168.0.241,192.168.0.242,192.168.0.243,192.168.0.244");
		/*config.set("index.search.directory", "/home/hadoop/titan-1.0.0-hadoop1/db/es");*/
        config.set("index." + INDEX_NAME + ".backend", "elasticsearch");
        config.set("index." + INDEX_NAME + ".elasticsearch.local-mode", true);
        config.set("index." + INDEX_NAME + ".elasticsearch.client-only", false); //index.search.hostname=
        config.set("index." + INDEX_NAME +".hostname", "192.168.0.241,192.168.0.242,192.168.0.243,192.168.0.244");


        //TitanGraph graph = TitanFactory.open("/home/andy/titan-1.0.0-hadoop1/conf/titan-cassandra-es.properties");
        TitanGraph graph = config.open();
        return graph;
    }
}
