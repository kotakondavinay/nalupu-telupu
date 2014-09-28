package com.naltel.hadoop.training.jobs.inputformatter;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class WordCountPerFileKey implements WritableComparable<WordCountPerFileKey> {

	private String fileName;
	private String word;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		fileName = in.readUTF();
		word = in.readUTF();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(fileName);
		out.writeUTF(word);
	}

	@Override
	public int compareTo(WordCountPerFileKey o) {
		int diff = fileName.compareTo(o.fileName);
		if (diff == 0) {
			diff = word.compareTo(o.word);
		}
		return diff;
	}

	@Override
	public String toString() {
		return "[fileName=" + fileName + ", word=" + word + "]";
	}

}
