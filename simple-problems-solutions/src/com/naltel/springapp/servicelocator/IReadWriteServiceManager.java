package com.naltel.springapp.servicelocator;

public interface IReadWriteServiceManager {
	IReadWriteService getIReadWriteService(String serviceName);
}
