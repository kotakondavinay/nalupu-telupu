package com.naltel.hadoop.training.jobs;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MaxLengthWordMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

	String maxWord;

	protected void setup(Context context) throws IOException, InterruptedException {
		maxWord = new String();
	};

	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		StringTokenizer st = new StringTokenizer(value.toString(), " ");
		while (st.hasMoreTokens()) {
			String nextToken = st.nextToken();
			if (nextToken.length() > maxWord.length()) {
				maxWord = nextToken;
			}
		}

	};

	protected void cleanup(Context context) throws IOException, InterruptedException {
		context.write(new Text(maxWord), new LongWritable(maxWord.length()));
	};
}
