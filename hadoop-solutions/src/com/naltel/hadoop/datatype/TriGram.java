package com.naltel.hadoop.datatype;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class TriGram implements WritableComparable<TriGram>{

	
	private String word1;
	
	@Override
	public void readFields(DataInput arg0) throws IOException {
		
	}

	@Override
	public void write(DataOutput arg0) throws IOException {
		
	}

	@Override
	public int compareTo(TriGram arg0) {
		return 0;
	}

	public String getWord1() {
		return word1;
	}

	public void setWord1(String word1) {
		this.word1 = word1;
	}

	@Override
	public String toString() {
		return "TriGram [word1=" + word1 + "]";
	}
	
	

}
