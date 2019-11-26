package com.xm.web.xm.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Timer {
	
	//@Scheduled(fixedRate=3000,initialDelay=3000)
	public void say(){
		System.out.println("1");
	}

}
