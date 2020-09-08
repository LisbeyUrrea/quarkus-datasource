package com.datasource.service;


import com.datasource.entity.Product;
import com.datasource.repository.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductService {

    @Inject
    private ProductRepository productRepository;

    public List<Product> getProduct(){
        return productRepository.listAll();
    }

    @Transactional
    public Product addProduct(Product product){
        productRepository.persist(product);

        return product;
    }

    public Optional<Product> getProductBySector(String sector){
        return productRepository.findBySector(sector);
    }
}
