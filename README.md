## Postgis proof of concept

Postgis is not a trusted extension and must be created as Superuser

```shell
createdb postgis01 &&
psql postgis01 -c "CREATE EXTENSION postgis" &&
./gradlew build &&
./gradlew flywayMigrate
```

Flyway db migrations
https://documentation.red-gate.com/fd/gradle-task-184127407.html
