package org.primerservidorspring.common;

import net.datafaker.Faker;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public Faker createFaker () {
        return new Faker();
    }
}
