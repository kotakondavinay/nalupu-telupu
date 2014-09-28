package com.naltel.hadoop.training.jobs.secondarysort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class URICountMapper extends Mapper<Text, Text, URICountKey, IntWritable> {

	private final IntWritable iw = new IntWritable();

	@Override
	protected void map(Text key, Text value, Context context) throws java.io.IOException, InterruptedException {
		URICountKey uKey = new URICountKey();
		uKey.setUri(key.toString());
		int count;
		try {
			count = Integer.parseInt(value.toString());
		} catch (NumberFormatException nfe) {
			return;
		}
		uKey.setCount(count);
		iw.set(count);
		context.write(uKey, iw);
	};
}
