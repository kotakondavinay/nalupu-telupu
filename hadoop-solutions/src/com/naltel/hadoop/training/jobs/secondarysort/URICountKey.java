package com.naltel.hadoop.training.jobs.secondarysort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class URICountKey implements WritableComparable<URICountKey> {

	private String uri;

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "URICountKey [count=" + count + ", uri=" + uri + "]";
	}

	private int count;

	@Override
	public void readFields(DataInput in) throws IOException {
		uri = in.readUTF();
		count = in.readInt();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(uri);
		out.writeInt(count);
	}

	@Override
	public int compareTo(URICountKey o) {
		int diff = uri.compareTo(o.uri);
		if (diff == 0) {
			diff = (count == o.count ? 0 : (count < o.count ? -1 : 1));
		}
		return diff;
	}

	@Override
	public int hashCode() {
		return uri.hashCode() + 31 * count;
	}

	public static class Comparator extends WritableComparator {
		public Comparator() {
			super(URICountKey.class);
		}

		public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
			return compareBytes(b1, s1, l1, b2, s2, l2);
		}
	}

	static {
		WritableComparator.define(URICountKey.class, new Comparator());
	}
}
