package com.atosenet.ga.company.monitor.counter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WebCounter extends AbstractAppCounter {
	private static final Logger logger = LoggerFactory.getLogger(WebCounter.class);
	
	public void visitCount(String name) {
		logger.info("visit increase: counter name={}", name);
		this.increment(name);
	}
}
