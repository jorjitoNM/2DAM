package com.hospital_jpa.common;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public Gson provideGson () {
        return new Gson();
    }
}
