package com.heydie.demo;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

public class MyDemo implements ValueChangeListener {

	@Override
	public void processValueChange(ValueChangeEvent e) throws AbortProcessingException {
		
		System.out.println(e.getOldValue()+" === "+e.getNewValue());
	}

}
