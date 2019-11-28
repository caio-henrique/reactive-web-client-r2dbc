package com.reactive.configuration;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer;
import org.springframework.transaction.ReactiveTransactionManager;

@Configuration
class DatabaseConfig extends AbstractR2dbcConfiguration {

    @Bean
    @Override
    public ConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                        .host("localhost")
                        .port(5432)
                        .database("reactive")
                        .username("teste")
                        .password("teste")
                        .build());
    }

    @Bean
    ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

    @Bean
    public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);

//        CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
//        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("com/foo/sql/db-schema.sql")));
//        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("com/foo/sql/test-data1.sql")));
//        initializer.setDatabasePopulator(populator);

        return initializer;
    }

}