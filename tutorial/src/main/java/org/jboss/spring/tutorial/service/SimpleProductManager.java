package org.jboss.spring.tutorial.service;

import java.util.List;

import org.jboss.spring.tutorial.domain.Product;
import org.jboss.spring.tutorial.repo.ProductDao;

@SuppressWarnings("serial")
public class SimpleProductManager implements ProductManager{

	//private List<Product> products;
	private ProductDao productDao;

	public void increasePrice(int Percentage) {
		double price = 0;
		List<Product> products = productDao.getProductList();
		if(products != null)
			for(Product product : products){
				price = product.getPrice();
				price *= (1+(Percentage*0.01));
				product.setPrice(price);
				productDao.saveProduct(product);
			}
	}

	public List<Product> getProducts() {
		//return products;
		return productDao.getProductList();
	}
	
	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
    /*public void setProducts(List<Product> products) {
		this.products = products;
	}*/
}
