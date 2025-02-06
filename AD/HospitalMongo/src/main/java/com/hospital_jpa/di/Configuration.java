package com.hospital_jpa.di;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public Gson getGson () { return new Gson();}


}
