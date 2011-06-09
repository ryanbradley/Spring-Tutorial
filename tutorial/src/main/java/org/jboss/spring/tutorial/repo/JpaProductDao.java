package org.jboss.spring.tutorial.repo;

import java.util.List;

import javax.persistence.EntityManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.spring.tutorial.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class JpaProductDao implements ProductDao {

	@Autowired
	protected EntityManager entityManager;
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	public List<Product> getProductList() {
		logger.info("Getting products!");
		
		List<Product> products = entityManager.createQuery("select p from Product p").getResultList();
		return products;
	}

	public void saveProduct(Product prod) {
		logger.info("Saving product: " + prod.getDescription());

		entityManager.merge(prod);
	}
}
