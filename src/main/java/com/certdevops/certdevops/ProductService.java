package com.certdevops.certdevops;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductService {

    private ProductRepository repo;

    @Autowired
    public ProductService (ProductRepository productRepository) {
        this.repo = productRepository;
    }

    public Iterable<Product> listAll() {
        return repo.findAll();
    }

    public Product save(Product product) {
        //repo.save(product);
        return repo.save(product);
    }

    public Product get(Integer id) {
        Product product = null;
        Optional<Product> prodResponse = repo.findById(id);

        if (prodResponse.isPresent()) {
            product = prodResponse.get();
        }
        return product;
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}