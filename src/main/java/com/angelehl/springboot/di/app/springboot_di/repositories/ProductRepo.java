package com.angelehl.springboot.di.app.springboot_di.repositories;

import java.util.List;

import com.angelehl.springboot.di.app.springboot_di.models.Product;

public interface ProductRepo {

    List<Product> findAll();

    Product findById(Long Id);


}
