package org.jboss.spring.tutorial.test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import org.jboss.spring.tutorial.domain.Product;
import org.jboss.spring.tutorial.repo.InMemoryProductDao;
import org.jboss.spring.tutorial.repo.ProductDao;
import org.jboss.spring.tutorial.service.SimpleProductManager;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {"classpath:test-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SimpleProductManagerTests {

	@Autowired
	private static List<Product> products;
	
	private static int PRODUCT_COUNT = 2;
	
	private static Double CHAIR_PRICE = new Double(20.50);
	private static String CHAIR_DESCRIPTION = new String("Chair");
	
	private static Double TABLE_PRICE = new Double(150.10);
	private static String TABLE_DESCRIPTION = new String("Table");
	
	private static int POSITIVE_PRICE_INCREASE = 10;
	
	@Autowired
    private SimpleProductManager productManager;
        
    protected void setUp() throws Exception {
        productManager = new SimpleProductManager();
        products = new ArrayList<Product>();
        
        Product product = new Product();
        product.setDescription("Chair");
        product.setPrice(CHAIR_PRICE);
        products.add(product);
        
        Product product1 = new Product();
        product1.setDescription("Table");
        product1.setPrice(TABLE_PRICE);
        products.add(product1);
        
        ProductDao productDao = new InMemoryProductDao(products);
        productManager.setProductDao(productDao);
    }

    @Test
    @Ignore
    public void testGetProductsWithNoProducts(){
        productManager.setProductDao(new InMemoryProductDao(null));
        assert(productManager.getProducts() == null);
    }
    
    @Test
    public void testGetProducts() {
        List<Product> products = productManager.getProducts();
        assert(productManager.getProducts() != null);
        assert(PRODUCT_COUNT == productManager.getProducts().size());
        
        Product product = products.get(0);
        assert(CHAIR_DESCRIPTION == product.getDescription());
        assert(CHAIR_PRICE == product.getPrice());
        
        product = products.get(1);
        assert(TABLE_DESCRIPTION == product.getDescription());
        assert(TABLE_PRICE == product.getPrice());
    }
    
    @Test
    @Ignore
    public void testPriceIncreaseWithNullListOfProducts()
    {
    	try{
//    		productManager = new SimpleProductManager();
    		productManager.setProductDao(new InMemoryProductDao(null));
    		productManager.increasePrice(POSITIVE_PRICE_INCREASE);   		
    	}
    	catch(NullPointerException e){
    		fail("Product list is NULL.");
    	}
    }
    
    @Test
    @Ignore
    public void testPriceIncreaseWithEmptyList()
    {
    	try{
//    	productManager = new SimpleProductManager();
    	productManager.setProductDao(new InMemoryProductDao(new ArrayList<Product>()));
    	productManager.increasePrice(POSITIVE_PRICE_INCREASE);
    	}
    	catch(Exception e){
    		fail("Product list was empty.");
    	}
    }
    
    @Test
    public void testPrinceIncreaseWithPositivePercentage()
    {
    	productManager.increasePrice(POSITIVE_PRICE_INCREASE);
    	double expectedChairPriceWithIncrease = 22.55;
    	double expectedTablePriceWithIncrease = 165.11;
    	
    	List<Product> products = productManager.getProducts();
    	
    	Product product = products.get(0);    	
    	assert(expectedChairPriceWithIncrease == product.getPrice());
    	
    	product = products.get(1);
    	assert(expectedTablePriceWithIncrease == product.getPrice());
    }
}