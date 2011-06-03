package org.jboss.spring.tutorial.service;

import java.io.Serializable;
import java.util.List;

import org.jboss.spring.tutorial.domain.Product;

public interface ProductManager extends Serializable {
	
	public void increasePrice(int Percentage);
	
	public List<Product> getProducts();

}