# springboot-rest-api-example

This is an project to showcase building a REST API with spring

You can build with maven or gradle within IntelliJ by using the `pom.xml`
or by using the standard maven commands:
```
mvn clean package
```

This runs the tests and builds the jar file in `target/rest-service-0.0.1-SNAPSHOT.jar`

Then the container (api, mariadb) can be built with docker-compose:

```
docker-compose up -d --b
```

Note that the Dockerfile targets the `*.jar` file built in the `target/` directory to build the image.

The API can be called at `localhost:8080/api/v1/deliveries`

# API

Endpoints:

`GET /` - Returns a default message

`GET /api/v1/deliveries` - returns all deliveries

`POST /api/v1/deliveries` creates a delivery with a JSON body with `name` and `address` fields

`GET /api/v1/deliveries/{id}` - returns a delivery by ID

`POST /api/v1/deliveries/{id}` - updates an existing delivery with that ID

`DELETE /api/v1/deliveries/{id}` - deletes a delivery by ID

