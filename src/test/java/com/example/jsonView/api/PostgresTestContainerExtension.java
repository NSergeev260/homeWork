package com.example.jsonView.api;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

public class PostgresTestContainerExtension implements BeforeAllCallback, AfterAllCallback {

    private static final PostgreSQLContainer<?> POSTGRES_CONTAINER;

    static {
        POSTGRES_CONTAINER = new PostgreSQLContainer<>(DockerImageName.parse("postgres:15-alpine"))
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test")
            .withReuse(true);
        POSTGRES_CONTAINER.start();
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        System.setProperty("spring.datasource.url", POSTGRES_CONTAINER.getJdbcUrl());
        System.setProperty("spring.datasource.username", POSTGRES_CONTAINER.getUsername());
        System.setProperty("spring.datasource.password", POSTGRES_CONTAINER.getPassword());
        System.setProperty("spring.datasource.driver-class-name", "org.postgresql.Driver");
        System.setProperty("spring.jpa.hibernate.ddl-auto", "create-drop");
        System.setProperty("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) {
        // Контейнер будет автоматически закрыт
    }
}