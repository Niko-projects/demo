package com.example.demo.event;

import org.springframework.context.ApplicationEvent;

public class StopIpcReadEvent extends ApplicationEvent {

	public StopIpcReadEvent(Object source) {
		super(source);
	}

}
