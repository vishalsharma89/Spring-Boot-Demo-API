package com.learnjava.learn.configuration;


import com.learnjava.learn.DB;
import com.learnjava.learn.DevDB;
import com.learnjava.learn.ProdDB;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    @ConditionalOnProperty(name = "project.mode",havingValue = "Development")
    public DB getDevDBean(){
        return new DevDB();
    }

    @Bean
    @ConditionalOnProperty(name = "project.mode",havingValue = "Production")
    public DB getProdDBean(){
        return new ProdDB();
    }
    
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
