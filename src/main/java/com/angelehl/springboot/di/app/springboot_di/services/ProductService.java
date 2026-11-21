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
            //De este modo no se utiliza siempre el mismo producto, sino que en cada llamada, se crea uno nuevo
            //Product newProd = new Product(p.getId(), p.getName(),priceImp.longValue());

            //Esta linea modificaba el objeto que todas las peticiones compartían
            //p.setPrice(priceImp.longValue());
            //return p;

            //Guardo una copia de mi objeto,NO el objeto, por lo que apunta a una espacion de memoria distinto
            Product newProd = (Product)p.clone();
            newProd.setPrice(priceImp.longValue());
            
            return newProd;

        }).collect(Collectors.toList());
    }

    public Product findById(Long Id){

        return repository.findById(Id);
    }

}
