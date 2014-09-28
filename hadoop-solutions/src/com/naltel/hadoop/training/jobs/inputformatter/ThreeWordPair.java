package com.naltel.hadoop.training.jobs.inputformatter;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class ThreeWordPair implements WritableComparable<ThreeWordPair>{
	private String word1;
	private String word2;
	private String word3;
	
	@Override
	public void readFields(DataInput in) throws IOException {
		word1 = in.readUTF();
		word2= in.readUTF();
		word3 = in.readUTF();
	}
	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(word1);
		out.writeUTF(word2);
		out.writeUTF(word3);
	}
	@Override
	public int compareTo(ThreeWordPair tuple) {
		int diff = word1.compareTo(tuple.word1);
		if(diff ==0)
		{
			diff = word2.compareTo(tuple.word2);
			if( diff == 0)
			{
				diff = word3.compareTo(tuple.word3);
			}
		}
		return diff;
	}
	public String getWord1() {
		return word1;
	}
	public void setWord1(String word1) {
		this.word1 = word1;
	}
	public String getWord2() {
		return word2;
	}
	public void setWord2(String word2) {
		this.word2 = word2;
	}
	public String getWord3() {
		return word3;
	}
	public void setWord3(String word3) {
		this.word3 = word3;
	}
	
	
	@Override
	public String toString() {
		return "[word1="+word1+",word2="+word2+" , word3 = "+word3+"]";
	}

}
