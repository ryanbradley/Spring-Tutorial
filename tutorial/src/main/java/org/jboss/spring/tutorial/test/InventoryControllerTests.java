package org.jboss.spring.tutorial.test;

import org.jboss.spring.tutorial.mvc.InventoryController;
import org.jboss.spring.tutorial.domain.Product;
import org.jboss.spring.tutorial.repo.InMemoryProductDao;
import org.jboss.spring.tutorial.service.SimpleProductManager;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Map;

public class InventoryControllerTests extends TestCase {

    public void testHandleRequestView() throws Exception{
        InventoryController controller = new InventoryController();
        SimpleProductManager spm = new SimpleProductManager();
        spm.setProductDao(new InMemoryProductDao(new ArrayList<Product>()));
        controller.setProductManager(spm);
        //controller.setProductManager(new SimpleProductManager());
        Map<String,Object> model = controller.handleRequest(null, null);
        assertNotNull(model);
        String nowValue = (String) model.get("now");
        assertNotNull(nowValue);
    }
}