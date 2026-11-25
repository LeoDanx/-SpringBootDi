package com.angelehl.springboot.di.app.springboot_di.repositories;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.angelehl.springboot.di.app.springboot_di.models.Product;

//Solo un repo puede ser primary
//@Primary 
@Repository("productFoo")
public class ProductRepoFoo implements ProductRepo{

    //Collections.singletonList() devuelve una lista que contiene exactamente un elemento. 
    // Es una lista de tamaño fijo, por lo que no puedes añadir ni eliminar elementos, aunque sí puedes modificar el objeto contenido si este lo permite.
    @Override
    public List<Product> findAll() {
        // TODO Auto-generated method stub
        return Collections.singletonList(new Product(1L,"Monitor Asus 27",600L));
    }

    @Override
    public Product findById(Long Id) {
        // TODO Auto-generated method stub
        return new Product(1L,"Monitor Asus 27",600L);
    }
    
}
