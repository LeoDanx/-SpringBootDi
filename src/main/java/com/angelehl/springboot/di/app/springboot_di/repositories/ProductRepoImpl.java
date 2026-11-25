package com.angelehl.springboot.di.app.springboot_di.repositories;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.angelehl.springboot.di.app.springboot_di.models.Product;

//@Component
@Primary  
@Repository("productList") 
public class ProductRepoImpl implements ProductRepo{

    private List<Product> data;

    public ProductRepoImpl(){
        
        this.data = Arrays.asList(
            new Product(1L,"Memoria Cosair 32",300L),
            new Product(2L, "Cpu Intel Core i9", 850L),
            new Product(3L, "Teclado Razer Mini", 600L),
            new Product(4L, "Mother Gigabyte", 490L)

        );
    } 

    @Override 
    public List<Product> findAll(){
        return data;
    }

     @Override
    public Product findById(Long id){
        //API stream de java
        //devuelve el primer producto, sino regresa null
        return data.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }
}
