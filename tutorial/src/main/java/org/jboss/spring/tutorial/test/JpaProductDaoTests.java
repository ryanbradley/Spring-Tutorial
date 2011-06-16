package org.jboss.spring.tutorial.test;

import java.util.List;

import org.jboss.spring.tutorial.domain.Product;
import org.jboss.spring.tutorial.repo.ProductDao;
import org.junit.Test;

public class JpaProductDaoTests {
	
	private ProductDao productDao;
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	protected String[] getClasspathLocation() {
		return new String[] {"classpath:test-context.xml"};
	}

	@Test
	public void testGetProductList() {
        List<Product> products = productDao.getProductList();
        assert(products.size() == 3);       
    }

	@Test
    public void testSaveProduct() {
        List<Product> products = productDao.getProductList();

        for (Product p : products) {
        	p.setPrice(200.12);
	        productDao.saveProduct(p);
	    }
	        
	    List<Product> updatedProducts = productDao.getProductList();
	    
	    for (Product p : updatedProducts) {	
	            assert(p.getPrice() == 200.12);
	    }
    }
}
