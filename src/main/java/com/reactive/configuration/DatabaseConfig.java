package com.reactive.configuration;

import com.reactive.repository.PersonRepository;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.data.r2dbc.repository.support.R2dbcRepositoryFactory;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;

@Configuration
class DatabaseConfig {

    @Bean
    public ConnectionFactory connectionFactory() {

        return new PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                        .host("localhost")
                        .database("reactive")
                        .username("teste")
                        .password("teste")
                        .build());
    }

    @Bean
    public DatabaseClient databaseClient(@Qualifier("connectionFactory") ConnectionFactory connectionFactory) {

        return DatabaseClient.builder()
                .connectionFactory(connectionFactory)
                .build();
    }

    @Bean
    public R2dbcRepositoryFactory repositoryFactory(DatabaseClient client) {

        RelationalMappingContext context = new RelationalMappingContext();
        context.afterPropertiesSet();

        return new R2dbcRepositoryFactory(client, context);
    }

    @Bean
    public PersonRepository personRepository(R2dbcRepositoryFactory factory) {
        return factory.getRepository(PersonRepository.class);
    }
}