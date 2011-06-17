package org.jboss.spring.tutorial.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.jboss.spring.tutorial.mvc.InventoryController;
import org.jboss.spring.tutorial.domain.Product;
import org.jboss.spring.tutorial.repo.InMemoryProductDao;
import org.jboss.spring.tutorial.service.SimpleProductManager;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Map;

@ContextConfiguration(locations = {"classpath:test-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class InventoryControllerTests {

	@Test
    public void testHandleRequestView() throws Exception{
        InventoryController controller = new InventoryController();
        SimpleProductManager spm = new SimpleProductManager();
        spm.setProductDao(new InMemoryProductDao(new ArrayList<Product>()));
        controller.setProductManager(spm);
        Map<String,Object> model = controller.handleRequest(null, null);
        assert(model != null);
        String nowValue = (String) model.get("now");
        assert(nowValue != null);
    }
}