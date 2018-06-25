package br.com.iftm.webserver;

import br.com.iftm.dbserver.model.api.DatabaseApi;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Bean(name = "database01")
    DatabaseApi getDatabaseApi01() {
        return Feign.builder()
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder())
                .target(DatabaseApi.class, "http://localhost:8081");
    }

    @Bean(name = "database02")
    DatabaseApi getDatabaseApi02() {
        return Feign.builder()
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder())
                .target(DatabaseApi.class, "http://localhost:8082");
    }
}
