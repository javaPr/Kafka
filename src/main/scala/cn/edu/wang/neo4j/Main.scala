package cn.edu.wang.neo4j

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.graphx._
import org.apache.spark.graphx.lib._
import org.apache.spark.rdd.RDD
import org.neo4j.spark._

import scala.collection.mutable.ArrayBuffer

/**
  * Created by wangdechang on 2017/4/16.
  */
object Main {
  def main(args: Array[String]): Unit = {
    println("=============this is Neo4j Graphx ===============")
    val conf = new SparkConf().setAppName("Neo4j GraphX")

    val sc = new SparkContext(conf)
    var arrLog = ArrayBuffer[String]("----------------------------")
    val nodeTime = System.currentTimeMillis();
    val nodeStmt = "MATCH (n:Person) where n.age >10 AND n.age < 30 RETURN id(n) as id, n.name as name,n.age as age"//"MATCH (n:Person{born:1970}) RETURN id(n) as id, n.name as value UNION MATCH (m:Movie) return id(m) as id, m.title as value"
    val nodeStamts = (nodeStmt,Seq[(String,AnyRef)]())
    val rdds =  sc.makeRDD(Executor.execute(sc,nodeStamts._1,nodeStamts._2.toMap).rows.toSeq)
    val nodes:RDD[(Long,(String,Long))] = rdds.map(row => (row(0).asInstanceOf[Long],(row(1).asInstanceOf[String],row(2).asInstanceOf[Long])))
    val nodeEndTime = System.currentTimeMillis();
    arrLog += "node time space = "+(nodeEndTime - nodeTime)

    val relStmt = "MATCH (n:Person)-[r:KNOWS]->(m:Person) where n.age >10 AND n.age < 30 AND m.age >10 AND m.age < 30 RETURN id(n), id(m),type(r)"
    val relStates = (relStmt,Seq[(String,AnyRef)]())
    val rel = sc.makeRDD(Executor.execute(sc,relStates._1,relStates._2.toMap).rows.toSeq)
    val rels: RDD[Edge[(String)]] = rel.map(row => new Edge[(String)](row(0).asInstanceOf[VertexId],row(1).asInstanceOf[VertexId],row(2).asInstanceOf[(String)]))
    val relEndTime = System.currentTimeMillis();
    arrLog += "rel tiem space = "+(relEndTime - nodeEndTime)
    val g = Graph[(String,Long),String](nodes,rels)
    val gEndTime = System.currentTimeMillis()
    arrLog += "graph finishi time space = "+(gEndTime - relEndTime)
    //val g:Graph[(String,Int),String] = Neo4jGraph.loadGraph[(String,Int),String](sc,nodeStamts,relStates);
    val nodeCount = nodes.filter{case (id,(name,age))=> age==20}.count()
    val start = System.currentTimeMillis()
    arrLog += "nodeCount time space = "+(start - gEndTime)
    println("==========================================")
    val count = g.vertices.filter{case (id,(name,age))=> age==20 }.count()
    val end = System.currentTimeMillis()

    arrLog +="graph count time space = "+(end - start)

    println("age ='20' = "+ count)
    println("node count ==============="+nodeCount)
    println("==========================================")
    println("time space = "+(end - start))
    arrLog.foreach(println)
    /*val neo = Neo4j(sc)
    val graphQuery = "match(p:Person)-[:REVIEWED]->(m)<-[:ACTED_IN]-(p1:Person) RETURN id(p) as source, id(p1) as target"
    val graph: Graph[Long, String] = neo.rels(graphQuery).partitions(7).batch(200).loadGraph
    println("==========================================")
    println("vertices count = "+graph.vertices.count())
    println("==========================================")
    val end = System.currentTimeMillis()
    println("time space = "+(end - start))*/

    sc.stop()

  }
}
