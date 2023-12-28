package tech.abishek.crudredis.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import tech.abishek.crudredis.entity.Product;

import java.util.List;

@Repository
public class ProductDao {

    public static final String HASH_KEY = "PRODUCT";
    public static final String PRODUCTS = "products";

    @Autowired
    private RedisTemplate redisTemplate;

    @CacheEvict(value=PRODUCTS, allEntries=true)
    public Product save(Product product) {
        redisTemplate.opsForHash().put(HASH_KEY, product.getId(), product);
        return product;
    }

    @Cacheable(value = "products")
    public List<Product> findAll() {
        return redisTemplate.opsForHash().values(HASH_KEY);
    }

    public Product findProductById(Long id) {
        return (Product) redisTemplate.opsForHash().get(HASH_KEY, id);
    }

    @CacheEvict(value=PRODUCTS, allEntries=true)
    public String deleteProduct(Long id) {
        redisTemplate.opsForHash().delete(HASH_KEY, id);
        return "Product removed !! " + id;
    }


}
