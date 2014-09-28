package com.naltel.hadoop.training.jobs.secondarysort;

import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class URICountReducer extends Reducer<URICountKey, IntWritable, Text, DoubleWritable> {

	private final Text URI = new Text();

	@Override
	protected void reduce(URICountKey uri, java.lang.Iterable<IntWritable> values, Context context)
			throws java.io.IOException, InterruptedException {
		URI.set(uri.getUri());
		int sum = 0;
		int numVisits = 0;
		for (IntWritable value : values) {
			sum += value.get();
			numVisits++;
		}
		context.write(URI, new DoubleWritable(sum / (double) numVisits));
	};

}
