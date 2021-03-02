package com.certdevops.certdevops;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService service;

    @Autowired
    public ProductController(ProductService productService) {
        this.service = productService;
    }


    @GetMapping(path = "/hello")
    public String sayHello() {
        return "Hello Products!";
    }

    // RESTful API methods for Retrieval operations
    @GetMapping
    public List<Product> list() {
        List<Product> result = new ArrayList<>();
        service.listAll().forEach(result::add);
        return result;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> get(@PathVariable Integer id) {
        try {
            Product product = service.get(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // RESTful API method for Create operation
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody ProductDto productDto) {
        service.save(productDto.getName(), productDto.getPrice());
    }

    // RESTful API method for Update operation
    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> update(@RequestBody ProductDto productDto, @PathVariable Integer id) {
        try {
            Product existProduct = service.get(id);
            existProduct.setName(productDto.getName());
            existProduct.setPrice(productDto.getPrice());
            service.update(existProduct);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // RESTful API method for Delete operation
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

}