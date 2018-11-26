package com.epam.external.config;

import com.epam.contract.api.external.client.AccountApiClient;
import com.epam.contract.api.external.server.AccountApiDelegate;
import com.epam.external.adapter.AccountApiAdapter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
@ComponentScan(basePackages = {
        "com.epam.contract.api.external.config",
        "com.epam.contract.api.external.info",
        "com.epam.contract.api.external.server"})
@Import(ConfigMock.class)
public class ServiceConfig {

    @Bean
    public AccountApiDelegate accountApiDelegate(AccountApiClient accountApiClient) {
        return new AccountApiAdapter(accountApiClient);
    }

//    @Bean
//    public CardIssuerApiDelegate cardIssuerApiDelegate(CardIssuerApiClient cardIssuerApiClient) {
//        return new CardIssuerApiAdapter(cardIssuerApiClient);
//    }

//    @Bean
//    public ClientManagementApiDelegate clientManagementApiDelegate(ClientManagementApiClient clientManagementApiClient) {
//        return new ClientManagementApiAdapter(clientManagementApiClient);
//    }

//    @Bean
//    public DocumentRepositoryApiDelegate documentRepositoryApiDelegate(DocumentRepositoryApiClient documentRepositoryApiClient) {
//        return new DocumentRepositoryApiAdapter(documentRepositoryApiClient);
//    }

//    @Bean
//    public SecurityManagementApiDelegate securityManagementApiDelegate(SecurityManagementApiClient securityManagementApiClient) {
//        return new SecurityManagementApiAdapter(securityManagementApiClient);
//    }

//    @Bean
//    public TestDataApiDelegate testDataApiDelegate(TestDataApiClient testDataApiClient) {
//        return new TestDataApiAdapter(testDataApiClient);
//    }
}
