# spark-dataprocessing

The example bases on docker container you can easly start using included docker-compose.yml
Docker will start Apache Spark v3 with master service and as many worker services as you declare.

```
docker-compose up --scale spark-worker=3
```

Current directory will be accessible from docker container under path: /app_data.
Any application jar or data file will be visible there.

Execute command below in order to login to master container.
```
docker exec -it spark-test_spark_1 /bin/bash
```

