package com.naltel.hbase.training.mapreduce.job;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxLenthWordReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

	String maxWord;
	int maxLength;

	protected void setup(Context context) throws IOException, InterruptedException {
		maxWord = "";
		maxLength = -1;
	};

	protected void reduce(Text key, Iterable<LongWritable> value, Context context) throws IOException,
			InterruptedException {
		String string = key.toString();
		if (string.length() >= maxLength) {
			maxWord = string;
			maxLength = string.length();
		}
	};

	protected void cleanup(Context context) throws IOException, InterruptedException {
		context.write(new Text(maxWord), new LongWritable(maxLength));
	};
}
