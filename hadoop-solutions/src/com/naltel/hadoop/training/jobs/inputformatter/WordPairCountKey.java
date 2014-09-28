package com.naltel.hadoop.training.jobs.inputformatter;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class WordPairCountKey implements WritableComparable<WordPairCountKey> {

	private String word1;
	private String word2;

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

	@Override
	public void readFields(DataInput in) throws IOException {
		word1 = in.readUTF();
		word2 = in.readUTF();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(word1);
		out.writeUTF(word2);
	}

	@Override
	public int compareTo(WordPairCountKey o) {
		int diff = word1.compareTo(o.word1);
		if (diff == 0) {
			diff = word2.compareTo(o.word2);
		}
		return diff;
	}

	@Override
	public int hashCode() {
		return word1.hashCode() + 31 * word2.hashCode();
	}

	@Override
	public String toString() {
		return "[word1=" + word1 + ", word2=" + word2 + "]";
	}

}
