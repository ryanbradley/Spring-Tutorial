package org.jboss.spring.tutorial.test;

import org.jboss.spring.tutorial.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {"classpath:test-context.xml",
									"classpath:META-INF/spring/applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductTest {

	private Product product;
	
	@Before
	public void setUp()
	{
		product = new Product();
	}
	
	@Test
    public void testSetAndGetDescription() {
        String testDescription = "aDescription";
        assert(product.getDescription() == null);
        product.setDescription(testDescription);
        assert(testDescription == product.getDescription());
    }

	@Test
    public void testSetAndGetPrice() {
        double testPrice = 100.00;   
        product.setPrice(testPrice);
        assert(testPrice == product.getPrice());
    }
}
