package org.jboss.spring.tutorial.test;

import java.util.ArrayList;
import java.util.List;
import org.jboss.spring.tutorial.domain.Product;
import org.jboss.spring.tutorial.repo.InMemoryProductDao;
import org.jboss.spring.tutorial.repo.ProductDao;
import org.jboss.spring.tutorial.service.SimpleProductManager;

import junit.framework.TestCase;

public class SimpleProductManagerTests extends TestCase {

	private static List<Product> products;
	
	private static int PRODUCT_COUNT = 2;
	
	private static Double CHAIR_PRICE = new Double(20.50);
	private static String CHAIR_DESCRIPTION = new String("Chair");
	
	private static Double TABLE_PRICE = new Double(150.10);
	private static String TABLE_DESCRIPTION = new String("Table");
	
	private static int POSITIVE_PRICE_INCREASE = 10;
	
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
        //productManager.setProducts(products);
    }

    public void testGetProductsWithNoProducts(){
        //productManager = new SimpleProductManager();
        productManager.setProductDao(new InMemoryProductDao(null));
        assertNull(productManager.getProducts());
    }
    
    public void testGetProducts() {
        List<Product> products = productManager.getProducts();
        assertNotNull(productManager.getProducts());
        assertEquals(PRODUCT_COUNT, productManager.getProducts().size());
        
        Product product = products.get(0);
        assertEquals(CHAIR_DESCRIPTION, product.getDescription());
        assertEquals(CHAIR_PRICE, product.getPrice());
        
        product = products.get(1);
        assertEquals(TABLE_DESCRIPTION, product.getDescription());
        assertEquals(TABLE_PRICE, product.getPrice());
    }
    
    public void testPriceIncreaseWithNullListOfProducts()
    {
    	try{
    		productManager = new SimpleProductManager();
    		//productManager.setProducts(null);
    		productManager.setProductDao(new InMemoryProductDao(null));
    		productManager.increasePrice(POSITIVE_PRICE_INCREASE);   		
    	}
    	catch(NullPointerException e){
    		fail("Product list is NULL.");
    	}
    }
    
    public void testPriceIncreaseWithEmptyList()
    {
    	try{
    	productManager = new SimpleProductManager();
    	productManager.setProductDao(new InMemoryProductDao(new ArrayList<Product>()));
    	//productManager.setProducts(new ArrayList<Product>());
    	productManager.increasePrice(POSITIVE_PRICE_INCREASE);
    	}
    	catch(Exception e){
    		fail("Product list was empty.");
    	}
    }
    
    public void testPrinceIncreaseWithPositivePercentage()
    {
    	productManager.increasePrice(POSITIVE_PRICE_INCREASE);
    	double expectedChairPriceWithIncrease = 22.55;
    	double expectedTablePriceWithIncrease = 165.11;
    	
    	List<Product> products = productManager.getProducts();
    	
    	Product product = products.get(0);    	
    	assertEquals(expectedChairPriceWithIncrease, product.getPrice());
    	
    	product = products.get(1);
    	assertEquals(expectedTablePriceWithIncrease, product.getPrice());
    }
}