package com.naltel.springapp.servicelocator;

import org.springframework.stereotype.Component;

@Component
public class STDIOReadWriteService implements IReadWriteService {

	@Override
	public void read() {
		System.out.println("Reading from STDIO.");
	}

	@Override
	public void process() {
		System.out.println("processing STD input.");
	}

	@Override
	public void write() {
		System.out.println("Writing to STD out.");	
	}
}
