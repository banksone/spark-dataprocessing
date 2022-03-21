# spark-dataprocessing (project inprogress)

Apache Spark is a mass data-processing and mass-calculation tool. Its strength lies in seamless clustering and in-memory processing.
Apache Spark is used by Netflix for ETL, feature generation, model training, and validation effectively preparing personalized recommendations (according to Databricks.com)

The example is based on Docker container, you can easily start using included docker-compose.yml
Docker will start Apache Spark v3 with master service and as many worker services as you declare.
Additionally there will be started three-node cluster of Apache Cassandra nosql database (albo used by NETFLIX) and a JAVA node (alpine linux) ready to provide Java Spring Boot REST services we will build in the project 'java-example-services' https://github.com/banksone/java-example-services

```
docker-compose up --scale spark-worker=3
```

Current directory will be accessible from docker container under path: /app_data.
Any application jar or data file will be visible there.

Spark task source will be compiled and assembled with maven into fat-jar - including all dependences. 

```
mvn package
```

Apache Spark should run binaries comiled with JDK 8 and 11 (what I haven't succided yet with the later one)

Execute command below in order to login to master container.
```
docker exec -it spark-dataprocessing_spark_1 /bin/bash
```
If you used another directory name for the project check correct name of the container:

```
docker container ps
```

Once we login to containers shell we can submit our Spark task

```
spark-submit --class com.banksone.MoviesTasks --master local[4] ./spark-vod-tasks-jar-with-dependencies.jar
```
