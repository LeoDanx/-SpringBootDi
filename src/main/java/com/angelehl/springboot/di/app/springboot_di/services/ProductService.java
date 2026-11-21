package com.angelehl.springboot.di.app.springboot_di.services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.angelehl.springboot.di.app.springboot_di.models.Product;
import com.angelehl.springboot.di.app.springboot_di.repositories.ProductRepo;
//Capa de datos
public class ProductService {

     //No interactua directamente con la bd
    //pero inyecta los metodos que si lo hacen
    //logica de negocio

    private ProductRepo repository = new ProductRepo();

    public List<Product> findAll(){
       
        return repository.findAll().stream().map(p->{
            Double priceImp = p.getPrice()*1.25;
            Product newProd = new Product(p.getId(), p.getName(),priceImp.longValue());

            //p.setPrice(priceImp.longValue());
            return p;

        }).collect(Collectors.toList());
    }

    public Product findById(Long Id){

        return repository.findById(Id);
    }

}
