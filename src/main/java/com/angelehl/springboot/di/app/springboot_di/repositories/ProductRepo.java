package com.angelehl.springboot.di.app.springboot_di.repositories;

import java.util.Arrays;
import java.util.List;

import com.angelehl.springboot.di.app.springboot_di.models.Product;

public class ProductRepo {

    private List<Product> data;

    public ProductRepo(){
        
        this.data = Arrays.asList(
            new Product(1L,"Memoria Cosair 32",300L),
            new Product(2L, "Cpu Intel Core i9", 850L),
            new Product(3L, "Teclado Razer Mini", 600L),
            new Product(4L, "Mother Gigabyte", 490L)

        );
    } 

    public List<Product> findAll(){
        return data;
    }

    public Product findById(Long id){
        //API stream de java
        //devuelve el primer producto, sino lo 
        return data.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }
}
