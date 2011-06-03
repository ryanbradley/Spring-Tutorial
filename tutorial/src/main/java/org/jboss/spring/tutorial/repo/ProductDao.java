package org.jboss.spring.tutorial.repo;

import java.util.List;

import org.jboss.spring.tutorial.domain.Product;

public interface ProductDao {
	
	public List<Product> getProductList();
	
	public void saveProduct(Product prod);
	
}
