package com.naltel.springapp.servicelocator;

import org.springframework.stereotype.Component;

@Component
public class FileReadWriteService implements IReadWriteService {

	@Override
	public void read() {
		System.out.println("Reading from file.");
		
	}

	@Override
	public void process() {
		System.out.println("Processing from file input.");
		
	}

	@Override
	public void write() {
		System.out.println("Writing to file.");
		
	}

}
