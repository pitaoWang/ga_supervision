package com.atosenet.ga.company.monitor.counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Service;

@Service
public class AbstractAppCounter implements AppCounter {

	@Autowired
	private CounterService counterService;
	
	public void increment(String metricName) {
		counterService.increment(metricName);
	}
	
	public void decrement(String metricName) {
		counterService.decrement(metricName);
	}
	
	public void reset(String metricName) {
		counterService.reset(metricName);
	}
	
	@Override
	public void enable() {
		// TODO Auto-generated method stub

	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub

	}

}
