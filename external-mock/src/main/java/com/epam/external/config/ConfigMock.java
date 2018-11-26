package com.epam.external.config;

import com.epam.contract.api.external.client.*;
import com.epam.external.data.provider.*;
import com.epam.external.data.provider.impl.*;
import com.epam.external.mock.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigMock {

    @Bean
    @ConditionalOnMissingBean
    public AccountDataProvider accountDataProvider() {
        return new DefaultAccountDataProvider();
    }

    @Bean
    public AccountApiClient accountApiClient(
            AccountDataProvider accountDataProvider,
            EligableDataProvider eligableDataProvider) {

        return new AccountApiClientMock(accountDataProvider, eligableDataProvider);
    }

//    @Bean
//    @ConditionalOnMissingBean
//    public CardCreationDataProvider cardCreationDataProvider() {
//        return new DefaultCardCreationDataProvider();
//    }

//    @Bean
//    @ConditionalOnMissingBean
//    public ClientDataProvider clientDataProvider() {
//        return new DefaultClientDataProvider();
//    }

//    @Bean
//    @ConditionalOnMissingBean
//    public ClientVerificationDataProvider clientVerificationDataProvider() {
//        return new DefaultClientVerificationDataProvider();
//    }

//    @Bean
//    @ConditionalOnMissingBean
//    public DocumentLinkDataProvider documentLinkDataProvider() {
//        return new DefaultDocumentLinkDataProvider();
//    }

//    @Bean
//    @ConditionalOnMissingBean
//    public EligableDataProvider eligableDataProvider() {
//        return new DefaultEligableDataProvider();
//    }

//    @Bean
//    public ClientManagementApiClient clientManagementApiClient(ClientDataProvider clientDataProvider) {
//        return new ClientManagementApiClientMock(clientDataProvider);
//    }

//    @Bean
//    public DocumentRepositoryApiClient documentRepositoryApiClient(DocumentLinkDataProvider documentLinkDataProvider) {
//        return new DocumentRepositoryApiClientMock(documentLinkDataProvider);
//    }

//    @Bean
//    public SecurityManagementApiClient securityCheckApiClient(ClientVerificationDataProvider clientVerificationDataProvider) {
//        return new SecurityManagementApiClientMock(clientVerificationDataProvider);
//    }

//    @Bean
//    public CardIssuerApiClient cardIssuerApiClient(
//            ClientDataProvider clientDataProvider,
//            CardCreationDataProvider cardCreationDataProvider) {
//
//        return new CardIssuerApiClientMock(clientDataProvider, cardCreationDataProvider);
//    }

//    @Bean
//    public TestDataApiClient testDataApiClient() {
//        return new TestDataApiClientMock();
//    }
}
