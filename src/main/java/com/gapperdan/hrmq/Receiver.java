package com.gapperdan.hrmq;

import java.util.concurrent.CountDownLatch;

public class Receiver {
	
	private CountDownLatch latch = new CountDownLatch(1);
	
	public void receiveMessage(String message) {
		System.out.println("Message received: "+message);
		latch.countDown();
	}

	public CountDownLatch getLatch() {
		return latch;
	}
}
