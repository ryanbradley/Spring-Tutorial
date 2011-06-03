package org.jboss.spring.tutorial.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PriceIncrease {

	// Logger for this class and its subclasses.
	protected final Log logger = LogFactory.getLog(getClass());
	
	private int percentage;

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int i) {
		percentage = i;
		logger.info("Percentage set to i.");
	}
}
