package com.auction.integration.controller.testcontainer;

import lombok.extern.slf4j.Slf4j;
import org.testcontainers.containers.PostgreSQLContainer;

@Slf4j
public class PostgreSqlContainer extends PostgreSQLContainer<PostgreSqlContainer> {

    private static final String POSTGRES_IMAGE = "postgres:12.6";
    private static PostgreSqlContainer container;

    public static PostgreSqlContainer getInstance() {
        if (container == null) {
            container =  new PostgreSqlContainer(POSTGRES_IMAGE);
        }

        return  container;
    }

    private PostgreSqlContainer(String dockerImageName) {
        super(dockerImageName);
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("spring.datasource.url", container.getJdbcUrl());
        System.setProperty("spring.datasource.username", container.getUsername());
        System.setProperty("spring.datasource.password", container.getPassword());
        System.setProperty("spring.datasource.database-name", container.getDatabaseName());
        log.info("Postgres url: {}", container.getJdbcUrl());
        log.info("Postgres username: {}", container.getUsername());
        log.info("Postgres password: {}", container.getPassword());
        log.info("Postgres DB name: {}", container.getDatabaseName());
    }

    @Override
    public void stop() {
    }
}

