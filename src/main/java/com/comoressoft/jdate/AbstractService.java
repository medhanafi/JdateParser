package com.comoressoft.jdate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractService {

	 private final Logger logger;

	    /**
	     * Logger constructor
	     */
	    public AbstractService() {
	        this.logger = LoggerFactory.getLogger(this.getClass());
	    }

	    protected final Logger getLogger() {
	        return this.logger;
	    }

}
