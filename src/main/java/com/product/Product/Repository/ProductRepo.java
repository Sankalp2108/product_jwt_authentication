package com.product.Product.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.product.Product.model.Product;

@Repository
public interface ProductRepo extends MongoRepository<Product, String> {
	
	List<Product> findByProduct(String product);
	
	List<Product> findAllByProduct(String product);

}
