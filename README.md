# spark-dataprocessing

The example bases on docker container you can easly start using included docker-compose.yml
Docker will start master service and as many worker services you declare.

```
docker-compose up --scale spark-worker=3
```

Current directory will be accessible from docker container under path: /app_data.
Any application jar or data file will be visible there.
