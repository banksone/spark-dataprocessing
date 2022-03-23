package com.banksone;

import com.datastax.spark.connector.japi.CassandraRow;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;

import java.io.Serializable;
import java.util.List;

import static com.datastax.spark.connector.japi.CassandraJavaUtil.javaFunctions;

public class MoviesRDDTask implements Serializable {

    public void start(JavaSparkContext jsc) throws Exception {

        JavaRDD<CassandraRow> movies = javaFunctions(jsc)
                .cassandraTable("vod", "movies");
        JavaRDD<CassandraRow> sample = movies.sample(true, 0.02);
        List<CassandraRow> sampleList = sample.collect();
        System.out.println("title: " + sampleList.get(0).getString("title"));
    }

    public static void main(String[] args) throws Exception {

        SparkSession spark = SparkSession.builder()
                .appName("Movies Example App")
                .config("spark.cassandra.connection.host", "cass_seedprovider")
                .config("spark.cassandra.connection.port", "9042")
                .getOrCreate();

        SparkContext sc = spark.sparkContext();
        MoviesRDDTask task = new MoviesRDDTask();

        task.start(new JavaSparkContext(sc));

    }
}
