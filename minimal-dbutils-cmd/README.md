# minimal-dbutils-cmd project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

The application needs a Postgres database, you can launch one easily with the following Docker command :
```
docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 \
    -e POSTGRES_USER=sarah -e POSTGRES_PASSWORD=connor -e POSTGRES_DB=mydatabase \
    -p 5433:5432 postgres:11.3
```

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
mvn quarkus:dev
```


## Packaging and running the application

The application can be packaged using:
```shell script
mvn package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.


The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
mvn package -Pnative
```


You can then execute your native executable with: `./target/minimal-dbutils-cmd-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.