package com.certdevops.certdevops;

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

    public String getProductName() {
        return "Honey";
     } 

    public Iterable<Product> listAll() {
        return repo.findAll();
    }

    public Product save(String name, float price) {
        return repo.save(new Product(name, price));
    }

    public Product update(Product product) {
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