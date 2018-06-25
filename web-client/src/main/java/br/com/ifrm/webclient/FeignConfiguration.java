package br.com.ifrm.webclient;

import br.com.iftm.dbserver.model.api.PerformOperationsApi;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Bean
    PerformOperationsApi getPerformOperationsApi() {
        return Feign.builder()
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder())
                .target(PerformOperationsApi.class, "http://localhost:8080");
    }
}
