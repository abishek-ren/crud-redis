package tech.abishek.crudredis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.abishek.crudredis.entity.Product;
import tech.abishek.crudredis.repository.ProductDao;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @PostMapping
    public Product save(@RequestBody Product product) {
        return productDao.save(product);
    }

    @GetMapping
    public Iterable<Product> findAll() {
        return productDao.findAll();
    }

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable Long id) {
        return productDao.findProductById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return productDao.deleteProduct(id);
    }

}
