package com.banksone;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Dataset;

public class MoviesTasks {
    public static void main(String[] args) {

        SparkSession spark = SparkSession.builder().appName("Movies Example App").getOrCreate();

        Dataset movies = spark.read().format("csv").option("header", "true").load("/app_data/tmdb_5000_movies.csv");

        System.out.println("movie: " + movies.first());

        spark.stop();
    }
}
