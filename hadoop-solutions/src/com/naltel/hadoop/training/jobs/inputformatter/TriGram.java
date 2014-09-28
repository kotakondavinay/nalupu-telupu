package com.naltel.hadoop.training.jobs.inputformatter;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class TriGram implements WritableComparable<TriGram>{
	String word1;
	String word2;
	String word3;

	@Override
	public void readFields(DataInput in) throws IOException {
       word1 = in.readUTF();
       word2 = in.readUTF();
       word3 = in.readUTF();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(word1);
		out.writeUTF(word2);
		out.writeUTF(word3);
	}

	@Override
	public int compareTo(TriGram triGram) {
		// TODO Auto-generated method stub
		return 0;
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
		return "TriGram [word1=" + word1 + ", word2=" + word2 + ", word3="
				+ word3 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((word1 == null) ? 0 : word1.hashCode());
		result = prime * result + ((word2 == null) ? 0 : word2.hashCode());
		result = prime * result + ((word3 == null) ? 0 : word3.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TriGram other = (TriGram) obj;
		if (word1 == null) {
			if (other.word1 != null)
				return false;
		} else if (!word1.equals(other.word1))
			return false;
		if (word2 == null) {
			if (other.word2 != null)
				return false;
		} else if (!word2.equals(other.word2))
			return false;
		if (word3 == null) {
			if (other.word3 != null)
				return false;
		} else if (!word3.equals(other.word3))
			return false;
		return true;
	}
	
	

}
