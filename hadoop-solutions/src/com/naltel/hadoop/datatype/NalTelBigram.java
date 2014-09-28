package com.naltel.hadoop.datatype;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class NalTelBigram implements WritableComparable<NalTelBigram> {
	private String word1;
	private String word2;

	@Override
	public void readFields(DataInput in) throws IOException {
		this.word1 = in.readUTF();
		this.word2 = in.readUTF();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(word1);
		out.writeUTF(word2);
	}

	@Override
	public int compareTo(NalTelBigram bigram) {
		int compare = this.word1.compareTo(bigram.word1);
		if (compare == 0) {
			compare = this.word2.compareTo(bigram.word2);
		}
		return compare;
	}

}
