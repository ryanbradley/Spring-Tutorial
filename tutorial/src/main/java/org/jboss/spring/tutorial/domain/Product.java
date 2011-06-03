package org.jboss.spring.tutorial.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Product implements Serializable {

	private int id;
	private String description;
    private Double price;

    public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	} 
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Description: " + description + ";");
        buffer.append("Price: " + price);
        return buffer.toString();
    }
}