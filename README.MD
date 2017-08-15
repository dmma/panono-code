Panono code challenge
=====================


URI                                                         |  Http method  | Response
------------------------------------------------------------|---------------|--------------------------
**_http://localhost:7707/panono-code/panorama/statistics_** | GET           | StatisticData
**_http://localhost:7707/panono-code/panorama/upload_**     | POST          | 201/204

You can run the application using Jetty as follows:
>   mvn clean package jetty:run

To stop this application use following command in different console
> mvn jetty:stop

Run external tests
> mvn test -Prun-external-tests