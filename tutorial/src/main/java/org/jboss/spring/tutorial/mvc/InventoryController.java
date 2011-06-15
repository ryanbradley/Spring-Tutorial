package org.jboss.spring.tutorial.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import org.jboss.spring.tutorial.service.ProductManager;

@Controller
@RequestMapping("hello.htm")
public class InventoryController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private ProductManager productManager;

    @RequestMapping(method=RequestMethod.GET)
    public @ModelAttribute("model")
    Map<String,Object> handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String now = (new Date()).toString();

        Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put("now", now);
        myModel.put("products", this.productManager.getProducts());
        
        return myModel;
    }

	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}
}
