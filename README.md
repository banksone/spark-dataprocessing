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
spark-submit --class com.banksone.MoviesTasks --master spark://spark:7077 ./spark-vod-tasks-jar-with-dependencies.jar
```

# DATA
Let's see how the data can be imported

Movies database for now consists of one table named 'movies'. It contains a few columns storing lists of UDTs (User Defined Types).
That's nothing complex - the first contains two fields: 'id' and 'name'.

Using CQL we can insert such a object easily: 

```
insert into movies (id, genres) values (999, [{id:'28',name:'Action'}, {id:'12', name:'Adventure'}]);
```

As you can see this is actually a JSON except there is a catch. We have to keep in mind field names must not be quoted. If so interpreter throw an exception assuming be are creating list of map objects. 
What is, of course, not true here as we provided a UDT type for the column - so we end up with an error.

Ok let's move to import more data. For now, we are going to try simple way and import a csv file. Content of the file have to be consistent with UDTs we set in the table as the types.

We have to log into cassandra master container 'cass_seedprovider'. Next start cqlsh and run script prepared to create KEYSPACE.
```
COPY vod.movies (budget,genres,homepage,id,keywords,original_language,original_title,overview,popularity,production_companies,production_countries,release_date,revenue,runtime,spoken_languages,status,tagline,title,vote_average,vote_count) FROM './tmdb_5000_movies_2.csv' WITH HEADER = TRUE AND DELIMITER = '|';
```