package com.angelehl.springboot.di.app.springboot_di.services;

import java.util.List;     
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.angelehl.springboot.di.app.springboot_di.models.Product;
import com.angelehl.springboot.di.app.springboot_di.repositories.ProductRepo;
import com.angelehl.springboot.di.app.springboot_di.repositories.ProductRepoImpl;

//Capa de datos
//@Component 
@Service 
public class ProductServiceImpl implements ProductService{

     //No interactua directamente con la bd
    //pero inyecta los metodos que si lo hacen
    //logica de negocio

    //@Autowired//Con esta anotacion proveemos a la clase ProductServiceImpl de una instancia de repository que gestionara en auto el contenedor de Spring
    //Principio Hollywood
    //private ProductRepoImpl repository = new ProductRepoImpl();

    //Implementamos un atributo mas generico para NO casarnos con una implementacion
    //Inyectamos una interfaz en lugar de una implementación concreta
    //@Autowired Lo quitamos para inyectar mediante constructor o setter
    private ProductRepo repository;

    //Esta es la alternativa
    /*@Autowired 
    public void setRepository(ProductRepo repository) {
        this.repository = repository;
    }*/

    //Es otra alternativa
    //No es necesario el @autowired
    public ProductServiceImpl(ProductRepo repository) {
        this.repository = repository;
    }

    @Override 
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

    @Override
    public Product findById(Long Id){

        return repository.findById(Id);
    }

  

}
