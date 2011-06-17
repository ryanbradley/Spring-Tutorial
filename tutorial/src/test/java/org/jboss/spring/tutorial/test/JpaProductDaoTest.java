package org.jboss.spring.tutorial.test;

import java.util.List;

import org.jboss.spring.tutorial.domain.Product;
import org.jboss.spring.tutorial.repo.ProductDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml",
									"classpath:META-INF/spring/applicationContext.xml"})
@TransactionConfiguration(defaultRollback=true)
public class JpaProductDaoTest {
	
	@Autowired
	private ProductDao productDao;
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	protected String[] getClasspathLocation() {
		return new String[] {"classpath:test-context.xml"};
	}

	@Transactional
	@Test
	public void testGetProductList() {
        List<Product> products = productDao.getProductList();
        assert(products.size() == 3);       
    }

	@Transactional
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
