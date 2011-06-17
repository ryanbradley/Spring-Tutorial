package org.jboss.spring.tutorial.test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import org.jboss.spring.tutorial.domain.Product;
import org.jboss.spring.tutorial.repo.InMemoryProductDao;
import org.jboss.spring.tutorial.repo.ProductDao;
import org.jboss.spring.tutorial.service.ProductManager;
import org.jboss.spring.tutorial.service.SimpleProductManager;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = {"classpath:test-context.xml",
									"classpath:META-INF/spring/applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional @TransactionConfiguration(defaultRollback = true)
public class SimpleProductManagerTest {

	@Autowired
	private static List<Product> products;
	
	private static int PRODUCT_COUNT = 3;
	
	private static Double CHAIR_PRICE = new Double(22.81);
	private static String CHAIR_DESCRIPTION = new String("Chair");
	
	private static Double TABLE_PRICE = new Double(75.29);
	private static String TABLE_DESCRIPTION = new String("Table");
	
	private static int POSITIVE_PRICE_INCREASE = 10;
	
	@Autowired
    private ProductManager productManager;
    
    @Test
    public void testGetProducts() {
        List<Product> products = productManager.getProducts();
        Assert.assertNotNull(productManager.getProducts());
        Assert.assertEquals(PRODUCT_COUNT, productManager.getProducts().size());
        
        Product product = products.get(0);
        Assert.assertEquals(CHAIR_DESCRIPTION, product.getDescription());
        Assert.assertEquals(CHAIR_PRICE, product.getPrice());
        
        product = products.get(1);
        Assert.assertEquals(TABLE_DESCRIPTION, product.getDescription());
        Assert.assertEquals(TABLE_PRICE, product.getPrice());
    }
    
    @Test
    public void testPrinceIncreaseWithPositivePercentage()
    {
    	productManager.increasePrice(POSITIVE_PRICE_INCREASE);
    	Double expectedChairPriceWithIncrease = 25.091;
    	Double expectedTablePriceWithIncrease = 82.81900000000002;
    	
    	List<Product> products = productManager.getProducts();
    	
    	Product product = products.get(0);    	
    	Assert.assertEquals(expectedChairPriceWithIncrease, product.getPrice());
    	
    	product = products.get(1);
    	Assert.assertEquals(expectedTablePriceWithIncrease, product.getPrice());
    }
}