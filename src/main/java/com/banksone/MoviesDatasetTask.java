package com.banksone;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.Serializable;

public class MoviesDatasetTask implements Serializable {

    public static void main(String[] args) {

        SparkSession spark = SparkSession.builder().appName("Movies Example App").getOrCreate();
        Dataset<Row> moviesDataset = spark.read()
                .format("org.apache.spark.sql.cassandra")
                .option("keyspace", "vod")
                .option("table", "movies")
                .option("spark.cassandra.connection.host", "cass_seedprovider")
                .option("spark.cassandra.connection.port", "9042")
                .load();

        //Dataset<String> titles = moviesDataset.map((Row movie) -> movie.title, Encoders.STRING());
        Row row = moviesDataset.first();
//        System.out.println("title: " + row.<String>getAs("title"));

        spark.stop();
    }
}
