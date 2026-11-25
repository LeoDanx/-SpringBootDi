package com.angelehl.springboot.di.app.springboot_di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import com.angelehl.springboot.di.app.springboot_di.repositories.ProductRepo;
import com.angelehl.springboot.di.app.springboot_di.repositories.ProductRepoJson;

@Configuration 
@PropertySource("classpath:config.properties")
public class AppConfig {

    //Estas es una alternativa para crear un componente Spring
    @Bean //Con Bean el public se puede omitir
    ProductRepo productRepo(){
        return new ProductRepoJson();
    }
}
