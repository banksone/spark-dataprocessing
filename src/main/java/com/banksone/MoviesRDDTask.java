package com.banksone;

import static com.datastax.spark.connector.japi.CassandraJavaUtil.javaFunctions;
import com.datastax.spark.connector.japi.CassandraJavaUtil;
import com.datastax.spark.connector.japi.CassandraRow;
import com.datastax.spark.connector.japi.rdd.CassandraTableScanJavaRDD;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;

import java.io.Serializable;
import java.util.List;

import static com.datastax.spark.connector.japi.CassandraJavaUtil.javaFunctions;
import static com.datastax.spark.connector.japi.CassandraJavaUtil.mapColumnTo;

public class MoviesRDDTask implements Serializable {

    public void start(JavaSparkContext jsc) throws Exception {

        CassandraTableScanJavaRDD<CassandraRow> movies = javaFunctions(jsc)
                .cassandraTable("vod", "movies").select("title");

        //JavaRDD<CassandraRow> sample = movies.sample(true, 0.02);
        //List<CassandraRow> sampleList = sample.collect();
        System.out.println("title: " + movies.isEmpty());
    }

    public static void main(String[] args) throws Exception {

        SparkSession spark = SparkSession.builder()
                .appName("Movies Example App")
                .config("spark.cassandra.connection.host", "cass_seedprovider,cass_node_1,cass_node_2")
                .config("spark.cassandra.connection.port", "9042")
                .config("spark.cassandra.output.consistency.level","ONE")
                .master("local[3]")
                .getOrCreate();

        SparkContext sc = spark.sparkContext();
        MoviesRDDTask task = new MoviesRDDTask();

        task.start(new JavaSparkContext(sc));

    }
}
