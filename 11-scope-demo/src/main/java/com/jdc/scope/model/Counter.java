package com.jdc.scope.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Counter implements Serializable {

	private static final long serialVersionUID = 1L;

	private int count;
	
	public int countUp() {
		return ++count;
	}
}
