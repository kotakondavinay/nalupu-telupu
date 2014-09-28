package com.naltel.hadoop.training.jobs;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxLengthWordReducer extends Reducer<Text, LongWritable, Text, NullWritable> {

	String maxWord;

	protected void setup(Context context) throws java.io.IOException, InterruptedException {
		maxWord = new String();
	};

	protected void reduce(Text key, Iterable<LongWritable> value, Context context) throws java.io.IOException,
			InterruptedException {
		if (key.toString().length() > maxWord.length()) {
			maxWord = key.toString();
		}

	};

	protected void cleanup(Context context) throws IOException, InterruptedException {
		context.write(new Text(maxWord), NullWritable.get());
	};

}
