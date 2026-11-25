package com.angelehl.springboot.di.app.springboot_di.services;

import java.util.List;     
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
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
    // @Autowired Comentamos para inyectar por constructor
    // @Qualifier("productFoo")
    private ProductRepo repository;

    //Esta es la alternativa
    /*@Autowired 
    public void setRepository(ProductRepo repository) {
        this.repository = repository;
    }*/
   private Environment environment;

    //Es otra alternativa
    //No es necesario el @autowired
    //productFoo es el nombre del repo seleccionado para esta implementación
    public ProductServiceImpl(@Qualifier("productList") ProductRepo repository, Environment environment) {//el qualifier inicia por defecto en minúscula, se inyecta el repo que no es rprimary
         this.repository = repository;
         this.environment = environment;
     }

    @Override 
    public List<Product> findAll(){
       
        return repository.findAll().stream().map(p->{
            System.out.println(environment.getProperty("config.price.tax",Double.class));
            Double priceImp = p.getPrice()* environment.getProperty("config.price.tax",Double.class);
            //De este modo no se utiliza siempre el mismo producto, sino que en cada llamada, se crea uno nuevo
            //Product newProd = new Product(p.getId(), p.getName(),priceImp.longValue());

            //Esta linea modificaba el objeto que todas las peticiones compartían
            // p.setPrice(priceImp.longValue());
            // return p;

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
