package com.angelehl.springboot.di.app.springboot_di.repositories;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import com.angelehl.springboot.di.app.springboot_di.models.Product;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

@Repository("productJson")
public class ProductRepoJson implements ProductRepo{

    private List<Product> list;

    public ProductRepoJson() {
        
     Resource rs  = new ClassPathResource("json/product.json");
     ObjectMapper objectMapper = new ObjectMapper();
    try {
        //list = Arrays.asList(objectMapper.readValue(rs.getFile(), Product[].class));
        list = Arrays.asList(objectMapper.readValue(rs.getInputStream(), Product[].class));

    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } 
    }

    @Override
    public List<Product> findAll() {
        // TODO Auto-generated method stub
        return list;
    }

    @Override
    public Product findById(Long Id) {
        // TODO Auto-generated method stub
        return list.stream().filter(p -> {
            return p.getId().equals(Id);
        }).findFirst().orElseThrow();//Variante del orElse
    }


}
