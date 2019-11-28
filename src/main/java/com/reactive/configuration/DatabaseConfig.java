package com.reactive.configuration;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.data.r2dbc.connectionfactory.init.CompositeDatabasePopulator;
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer;
import org.springframework.data.r2dbc.connectionfactory.init.DatabasePopulator;
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
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

//        ConnectionFactory connectionFactory = ConnectionFactories.get(ConnectionFactoryOptions.builder()
//                .option(ConnectionFactoryOptions.DRIVER, "postgresql")
//                .option(ConnectionFactoryOptions.HOST, "localhost")
//                .option(ConnectionFactoryOptions.PORT, 5432)  // optional, defaults to 5432
//                .option(ConnectionFactoryOptions.USER, "teste")
//                .option(ConnectionFactoryOptions.PASSWORD, "teste")
//                .option(ConnectionFactoryOptions.DATABASE, "reactive")  // optional
////                .option(OPTIONS, options) // optional
//                .build());
//
//        return connectionFactory;
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