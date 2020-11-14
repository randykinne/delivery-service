# delivery-service

This is an app to test building a REST API with spring

You can build with maven or gradle within IntelliJ by using the `pom.xml`

Then the container can be built with docker-compose:

```
docker-compose up -d --b
```

Note that docker targets the `*.jar` file built in the `target/` directory to build the image.

The API can be called at `localhost:8080/api/v1/deliveries`

