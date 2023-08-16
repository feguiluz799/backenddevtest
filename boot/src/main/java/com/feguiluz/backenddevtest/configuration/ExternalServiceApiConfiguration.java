package com.feguiluz.backenddevtest.configuration;

import com.feguiluz.backenddevtest.client.api.DefaultApi;
import com.feguiluz.backenddevtest.client.invoker.ApiClient;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class ExternalServiceApiConfiguration {

    @Bean
    public DefaultApi defaultApi(){
        DefaultApi defaultApi = new DefaultApi(apiClient());
        return defaultApi;
    }

    @Bean
    public ApiClient apiClient() {
        return new ApiClient(restTemplate(new RestTemplateBuilder()));
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {

        return builder
                .setConnectTimeout(Duration.ofMillis(3000))
                .setReadTimeout(Duration.ofMillis(3000))
                .build();
    }


}
