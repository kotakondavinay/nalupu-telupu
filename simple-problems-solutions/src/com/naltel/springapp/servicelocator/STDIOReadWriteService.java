package com.naltel.springapp.servicelocator;

import org.springframework.stereotype.Component;

@Component
public class STDIOReadWriteService implements IReadWriteService {

	public void read() {	
		System.out.println("Reading from STDIO.");
	}

	
	public void process() {
		System.out.println("processing STD input.");
	}

	public void write() {
		System.out.println("Writing to STD out.");	
	}
}
