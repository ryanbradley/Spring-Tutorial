package org.jboss.spring.tutorial.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.jboss.spring.tutorial.service.ProductManager;
import org.jboss.spring.tutorial.service.PriceIncrease;

@Controller
@RequestMapping("priceincrease.htm")
public class PriceIncreaseFormController {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private ProductManager productManager;

    @RequestMapping(method=RequestMethod.POST)
    public String onSubmit(PriceIncrease command)
            throws ServletException {

        int increase = command.getPercentage();

        productManager.increasePrice(increase);

        return "redirect:hello.htm";
    }

    @RequestMapping(method=RequestMethod.GET)
/*
 * Although I want to access the percentage member of a PriceIncrease object, we use @ModelAttribute to get the full object and the .jsp
 * or .xml files can access the required members of the object.
 */
    public @ModelAttribute("priceIncrease")
    Object formBackingObject(HttpServletRequest request) throws ServletException {
        PriceIncrease priceIncrease = new PriceIncrease();
        priceIncrease.setPercentage(20);
        return priceIncrease;
    }

    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }

    public ProductManager getProductManager() {
        return productManager;
    }

}