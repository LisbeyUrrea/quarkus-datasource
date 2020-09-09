package com.datasource.mocks;

import com.datasource.entity.Product;
import com.datasource.service.ProductService;
import io.quarkus.test.Mock;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@Mock
@ApplicationScoped
public class ProductServiceMock extends ProductService {

    @Override
    public List<Product> getProduct() {
        List<Product> list = new ArrayList<>();
        list.add(new Product("samsung galaxy s20", "Tecnologia","Samsung",120.0));
        list.add(new Product("Iphone 11", "Tecnologia","Iphone",130.0));
        list.add(new Product("Xiaomi mi band 5", "Tecnologia","Xiaomi",140.0));
        list.add(new Product("Huawei P30", "Tecnologia","Huawei",150.0));
        return list;
    }

    @Override
    public Product addProduct(Product product) {
        return product;
    }

    @Override
    public Product getProductById(Long id) {
        return new Product("Xiaomi mi band 5", "Tecnologia","Xiaomi",140D);
    }

    @Override
    public Product getProductBySector(String sector) {
        return new Product("Iphone 11", sector,"Iphone",130.000);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return product;
    }

    @Override
    public boolean deleteProductByid(Long id) {
        return true;
    }

}
