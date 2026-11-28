package com.ecommerce_microservices.search_service.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import co.elastic.clients.transport.rest5_client.Rest5ClientTransport;
import co.elastic.clients.transport.rest5_client.low_level.Rest5Client;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import org.apache.hc.core5.http.message.BasicHeader;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.Header;
import java.net.URISyntaxException;

@Configuration
public class ElasticSearchConfiguration {

    @Value("${elasticsearch.server-url}")
    private String serverUrl;

    @Value("${elasticsearch.api-key}")
    private String apiKey;

    @Bean
    public ElasticsearchClient elasticsearchClient() throws URISyntaxException {
        Rest5Client rest5Client = Rest5Client.builder(HttpHost.create(serverUrl))
            .setDefaultHeaders(new Header[] {
                new BasicHeader("Authorization", "ApiKey " + apiKey)
            })
            .build();

        Rest5ClientTransport transport = new Rest5ClientTransport(
            rest5Client,
            new JacksonJsonpMapper()
        );

        return new ElasticsearchClient(transport);
    }

}
