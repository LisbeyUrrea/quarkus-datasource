package com.datasource.service;


import com.datasource.entity.Product;
import com.datasource.repository.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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

    public Product getProductById(Long id){
        return Optional.ofNullable(productRepository.findById(id))
                .orElseThrow(() -> new IllegalArgumentException("No existe un producto con el id enviado."));
    }

    public Product getProductBySector(String sector){
        return productRepository.findBySector(sector)
                .orElseThrow(() -> new IllegalArgumentException("No existe un producto con el sector enviado."));
    }

    @Transactional
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = getProductById(id);
        existingProduct.setName(product.getName());
        existingProduct.setSector(product.getSector());
        existingProduct.setBrand(product.getBrand());
        existingProduct.setPrice(product.getPrice());
        productRepository.persist(existingProduct);
        return existingProduct;
    }

    @Transactional
    public boolean deleteProductByid(Long id){
       return productRepository.deleteById(id);
    }

}
