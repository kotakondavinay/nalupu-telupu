package com.naltel.app.cache;

public class CacheMetric {

	private Long keyRef;
	private Long memRef;

    CacheMetric() {
    	this.keyRef = 0L;
    	this.memRef = 0L;
	}
	public Long getKeyRef() {
		return keyRef;
	}

	public void setKeyRef(Long keyRef) {
		this.keyRef = keyRef;
	}

	public Long getMemRef() {
		return memRef;
	}

	public void setMemRef(Long memRef) {
		this.memRef = memRef;
	}
}
