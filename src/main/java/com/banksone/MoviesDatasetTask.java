package com.banksone;

import org.apache.spark.api.java.function.ReduceFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.sum;

import java.io.Serializable;

public class MoviesDatasetTask implements Serializable {

    public static void main(String[] args) {

        SparkSession spark = SparkSession.builder().appName("Movies Example App").getOrCreate();
        Dataset<Row> moviesDataset = spark.read()
                .format("org.apache.spark.sql.cassandra")
                .option("keyspace", "vod")
                .option("table", "movies_stats")
                .option("spark.cassandra.connection.host", "cass_seedprovider,cass_node_1,cass_node_2")
                .option("spark.cassandra.connection.port", "9042")
                .load();


        moviesDataset
                .where("budget > 100")
                .agg(sum("budget").as("budget"))
                .write()
                .format("org.apache.spark.sql.cassandra")
                .option("spark.cassandra.connection.host", "cass_seedprovider,cass_node_1,cass_node_2")
                .option("spark.cassandra.connection.port", "9042")
                .option("table", "movies_stats")
                .option("keyspace", "vod")
                .mode("append")
                .save();

        spark.stop();
    }
}
