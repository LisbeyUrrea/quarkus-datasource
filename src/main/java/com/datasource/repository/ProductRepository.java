package com.datasource.repository;

import com.datasource.entity.Product;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {

    public Optional<Product> findBySector(String sector) {
        return Optional.ofNullable(find("sector", sector).firstResult());
    }

}
