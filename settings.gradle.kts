pluginManagement {
    repositories {
        mavenLocal()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
        gradlePluginPortal()
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "sqldelight-postgis-concept"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            val vSqlDelight = "FIXME-SNAPSHOT" // add locally published snapshot
            plugin("kotlin", "org.jetbrains.kotlin.jvm").version("2.0.20")
            plugin("sqldelight", "app.cash.sqldelight").version(vSqlDelight)
            plugin("flyway", "org.flywaydb.flyway").version("10.1.0")
            library("sqldelight-jdbc-driver", "app.cash.sqldelight:jdbc-driver:$vSqlDelight")
            library("sqldelight-postgresql-dialect", "app.cash.sqldelight:postgresql-dialect:$vSqlDelight")
            library("postgresql-jdbc-driver", "org.postgresql:postgresql:42.5.4")
            library("flyway-database-postgresql", "org.flywaydb:flyway-database-postgresql:10.1.0")
            library("google-truth", "com.google.truth:truth:1.4.2")
        }
    }
}
